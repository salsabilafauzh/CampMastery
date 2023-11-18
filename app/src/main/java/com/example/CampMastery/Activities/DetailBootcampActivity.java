package com.example.CampMastery.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.CampMastery.Model.Bootcamp;
import com.example.CampMastery.R;
import com.example.CampMastery.db.DbHelper_User;

public class DetailBootcampActivity extends AppCompatActivity {

    ImageView imageCover;
    TextView nama_bootcamp, desc_bootcamp, startDate, endDate;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bootcamp);

        nama_bootcamp = findViewById(R.id.nama_bootcamp);
        desc_bootcamp = findViewById(R.id.deskripsi_bootcamp);
        startDate = findViewById(R.id.timeline_start);
        endDate = findViewById(R.id.timeline_end);
        imageCover = findViewById(R.id.image_cover);
        Bundle idBootcampExtra = getIntent().getExtras();
        int idBootcamp = idBootcampExtra.getInt("BOOTCAMP_ID");
        DbHelper_User db = new DbHelper_User(this);
        Bootcamp data = db.getBootcamp(idBootcamp);
        nama_bootcamp.setText(data.getTitle());
        desc_bootcamp.setText(data.getDeskripsi());
        startDate.setText(data.getStart());
        endDate.setText(data.getEnd());
        Glide.with(this).load(data.getCover()).into(imageCover);
    }
}