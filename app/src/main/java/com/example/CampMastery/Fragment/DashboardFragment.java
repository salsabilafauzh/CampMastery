package com.example.CampMastery.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.content.Intent;
import android.widget.Button;

import com.example.CampMastery.Activities.BookmarkActivity;
import com.example.CampMastery.Model.Bootcamp;
import com.example.CampMastery.Model.BootcampData;
import com.example.CampMastery.R;
import com.example.CampMastery.adapter.DepthPageTransformer;
import com.example.CampMastery.adapter.ImagePagerAdapter;

import java.util.ArrayList;


public class DashboardFragment extends Fragment {
    private int[] images = BootcampData.getListDataCover();
    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ViewPager viewPager = view.findViewById(R.id.viewPager);
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        ImagePagerAdapter adapter = new ImagePagerAdapter(getContext(), images, viewPager);
        viewPager.setAdapter(adapter);
        Button btnBookmark = view.findViewById(R.id.btn_bookmark);
        btnBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), BookmarkActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}