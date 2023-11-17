package com.example.CampMastery.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.CampMastery.Model.Bootcamp;
import com.example.CampMastery.R;
import com.example.CampMastery.adapter.ExploreAdapter;
import com.example.CampMastery.db.DbHelper_Bootcamp;
import com.example.CampMastery.db.DbHelper_User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ExploreActivity extends AppCompatActivity {

    DbHelper_User dbBootcamp;
    private RecyclerView rv;
    private ArrayList<Bootcamp> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        dbBootcamp = new DbHelper_User(this);

        // Use SharedPreferences to check if data has been added before
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        boolean dataAdded = sharedPreferences.getBoolean("data_added", false);

        if (!dataAdded) {
            // Add data only if it hasn't been added before
            dbBootcamp.addBootcamp("Dicoding", "Ini Deskripsi", "10-11-2023"
                    , "11-11-2023", R.drawable.cover_bootcamp1);
            dbBootcamp.addBootcamp("Dicoding 2", "Ini Deskripsi 2", "10-11-2023", "11-11-2023"
                    , R.drawable.cover_bootcamp1);

            // Set the flag to indicate that data has been added
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("data_added", true);
            editor.apply();
        }

        list.addAll(dbBootcamp.getAllBootcamps());
        rv = findViewById(R.id.rv_card);
        rv.setHasFixedSize(true);

        rv.setLayoutManager(new LinearLayoutManager(this));
        ExploreAdapter adapter = new ExploreAdapter(list, this);
        rv.setAdapter(adapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.explore);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.dashboard) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                    finish();
                } else if (item.getItemId() == R.id.explore) {
                    // Handle explore if needed
                } else if (item.getItemId() == R.id.profile) {
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                    finish();
                }

                return true; // Return true to indicate that the item selection is handled
            }
        });
    }
}
