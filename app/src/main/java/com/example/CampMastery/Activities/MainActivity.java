package com.example.CampMastery.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.CampMastery.Fragment.DashboardFragment;
import com.example.CampMastery.Fragment.ExploreFragment;
import com.example.CampMastery.Fragment.ProfileFragment;
import com.example.CampMastery.Model.User;
import com.example.CampMastery.R;
import com.example.CampMastery.db.DbHelper_User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity);
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        DashboardFragment mDashBoardFragment = new DashboardFragment();
        mFragmentTransaction.add(R.id.frame_layout, mDashBoardFragment, DashboardFragment.class.getSimpleName());
        mFragmentTransaction.commit();

        Bundle emailUserExtra = getIntent().getExtras();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.action_dashboard) {
                selectedFragment = new DashboardFragment();
            } else if (item.getItemId() == R.id.action_explore) {
                selectedFragment = new ExploreFragment();
            } else if (item.getItemId() == R.id.action_profile) {
                DbHelper_User db = new DbHelper_User(this);
                String email = emailUserExtra.getString("email");

                if (email != null) {
                    User data = db.getUserByEmail(email);

                    if (data != null) {
                        String username = data.getUsername();
                        String userEmail = data.getEmail();
                        selectedFragment = new ProfileFragment(userEmail, username);
                    } else {
                        Toast.makeText(MainActivity.this, "Error mengambil data", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Email is null", Toast.LENGTH_SHORT).show();
                }
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, selectedFragment)
                        .commit();
            }

            return true;
        });
    }
}
