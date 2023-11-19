package com.example.CampMastery.Fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.content.Intent;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.CampMastery.Activities.BookmarkActivity;
import com.example.CampMastery.Activities.MainActivity;
import com.example.CampMastery.Model.Bootcamp;
import com.example.CampMastery.Model.BootcampData;
import com.example.CampMastery.R;
import com.example.CampMastery.adapter.DashboardBookmarkAdapter;
import com.example.CampMastery.adapter.DashboardBootcampAdapter;
import com.example.CampMastery.adapter.DepthPageTransformer;
import com.example.CampMastery.adapter.ImagePagerAdapter;
import com.example.CampMastery.db.DbHelper_User;

import java.util.ArrayList;
import java.util.List;


public class DashboardFragment extends Fragment {
    private int[] images = BootcampData.getListDataCover();
    private ArrayList<Bootcamp> bootcampDataList = BootcampData.getListData();
    DashboardBootcampAdapter bootcampAdapter;
    DashboardBookmarkAdapter bookmarkAdapter;
    private ArrayList<Bootcamp> list = new ArrayList<>();
    private DbHelper_User dbBookmark;
    Activity activity;


    public DashboardFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        RelativeLayout clickAbleBookmark = view.findViewById(R.id.container_bookmark);
        activity = getActivity();
        if (activity != null && getContext() != null) {
            //section carousel dahsboard
            ViewPager viewPager = view.findViewById(R.id.viewPager);
            viewPager.setPageTransformer(true, new DepthPageTransformer());
            ImagePagerAdapter adapter = new ImagePagerAdapter(getContext(), images, viewPager);
            viewPager.setAdapter(adapter);

            //section bookmark dashboard
            dbBookmark = new DbHelper_User(getContext());
            list.addAll(dbBookmark.getBookmarkedBootcamps());
            RecyclerView rvBookmark = view.findViewById(R.id.rv_card_bookmark_highlight);
            rvBookmark.setHasFixedSize(true);
            rvBookmark.setLayoutManager(new LinearLayoutManager(getContext()));
            DashboardBookmarkAdapter bookmarkAdapter = new DashboardBookmarkAdapter(list, getActivity());
            rvBookmark.setAdapter(bookmarkAdapter);

            //section bootcamp dashboard
            RecyclerView recyclerView = view.findViewById(R.id.rv_card_bootcamp);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(layoutManager);
            bootcampAdapter = new DashboardBootcampAdapter(getContext(), bootcampDataList);
            RecyclerView rvBootcamp = view.findViewById(R.id.rv_card_bootcamp);
            rvBootcamp.setAdapter(bootcampAdapter);
        }

        clickAbleBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToBookmark = new Intent(getContext(), BookmarkActivity.class);
                startActivity(moveToBookmark);
            }
        });


        return view;
    }

}
