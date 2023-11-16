package com.example.CampMastery.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.CampMastery.Fragment.DashboardFragment;
import com.example.CampMastery.Fragment.ExploreFragment;
import com.example.CampMastery.Fragment.ProfileFragment;
import com.example.CampMastery.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity);
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        DashboardFragment mDashBoardFragment = new DashboardFragment();
        mFragmentTransaction.add(R.id.frame_layout,mDashBoardFragment,DashboardFragment.class.getSimpleName());
        mFragmentTransaction.commit();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;


            if (item.getItemId() == R.id.action_dashboard) {
                selectedFragment = new DashboardFragment();
            } else if (item.getItemId() == R.id.action_explore) {
                selectedFragment = new ExploreFragment();
            } else if (item.getItemId() == R.id.action_profile) {
                selectedFragment = new ProfileFragment();
            }


            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, selectedFragment)
                        .commit();
            }

            return true;
        });

//
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
//        bottomNavigationView.setSelectedItemId(R.id.dashboard);
//
//        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                if (item.getItemId() == R.id.dashboard) {
//                    // Do nothing or perform any action for the Dashboard
//                } else if (item.getItemId() == R.id.explore) {
//                    startActivity(new Intent(getApplicationContext(), FragmentActivity.class));
//                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
//                    finish();
//                } else if (item.getItemId() == R.id.profile) {
//                    startActivity(new Intent(getApplicationContext(), FragmentActivity.class));
//                    finish();
//                }
//
//                return true; // Return true to indicate that the item selection is handled
//            }
//        });
    }
}
