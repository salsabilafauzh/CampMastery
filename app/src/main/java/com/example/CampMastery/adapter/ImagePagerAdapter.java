package com.example.CampMastery.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.CampMastery.R;

public class ImagePagerAdapter extends PagerAdapter {
    private Context context;
    private int[] images;
    private ViewPager viewPager;
    private int currentPosition = 0;
    private static final long SLIDE_DELAY = 5000; // Delay in milliseconds for sliding
    private final Handler handler = new Handler(Looper.getMainLooper());

    private final Runnable slideRunnable = new Runnable() {
        @Override
        public void run() {
            if (currentPosition == images.length - 1) {
                currentPosition = 0;
            } else {
                currentPosition++;
            }
            viewPager.setCurrentItem(currentPosition, true);
            handler.postDelayed(this, SLIDE_DELAY);
        }
    };

    public ImagePagerAdapter(Context context, int[] images, ViewPager viewPager) {
        this.context = context;
        this.images = images;
        this.viewPager = viewPager;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.carousel_item, container, false);
        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageResource(images[position]);
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    // Start automatic sliding when the ViewPager is attached
    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        if (position == 0 && viewPager != null) {
            handler.postDelayed(slideRunnable, SLIDE_DELAY);
        }
    }

    // Stop automatic sliding when the ViewPager is detached
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        if (position == 0 && viewPager != null) {
            handler.removeCallbacks(slideRunnable);
        }
    }
}
