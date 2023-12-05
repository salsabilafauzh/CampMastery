package com.example.CampMastery.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.CampMastery.Model.Bootcamp;
import com.example.CampMastery.R;
import com.example.CampMastery.db.DbHelper_User;

public class DetailBootcampActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imageCover;
    TextView nama_bootcamp, desc_bootcamp, startDate, endDate;
    Button btn_bookmark, btn_apply;
    DbHelper_User db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bootcamp);

        nama_bootcamp = findViewById(R.id.nama_bootcamp);
        desc_bootcamp = findViewById(R.id.deskripsi_bootcamp);
        startDate = findViewById(R.id.timeline_start);
        endDate = findViewById(R.id.timeline_end);
        imageCover = findViewById(R.id.image_cover);

//        btn_bookmark = findViewById(R.id.btn_bookmark);
        btn_apply = findViewById(R.id.btn_apply);
        db = new DbHelper_User(this);

        Bundle idBootcampExtra = getIntent().getExtras();
        if (idBootcampExtra != null) {
            int idBootcamp = idBootcampExtra.getInt("BOOTCAMP_ID");
            Bootcamp data = db.getBootcamp(idBootcamp);

            if (data != null) {
                nama_bootcamp.setText(data.getTitle());
                desc_bootcamp.setText(data.getDeskripsi());
                startDate.setText(data.getStart());
                endDate.setText(data.getEnd());
                Glide.with(this).load(data.getCover()).into(imageCover);

                // Set the initial state of the bookmark button
//                setBookmarkIcon(btn_bookmark, checkIfBookmarked(data.getId()));
            }
        }

//        btn_bookmark.setOnClickListener(this);
        btn_apply.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        if (v.getId() == R.id.btn_bookmark) {
//            // Assuming you have a Bootcamp object or a way to get its ID in your DetailBootcampActivity
//            int idBootcamp = getIntent().getIntExtra("BOOTCAMP_ID", -1);
//            Bootcamp selectedBootcamp = db.getBootcamp(idBootcamp);
//
//            if (selectedBootcamp != null) {
//                // Check if the bootcamp is bookmarked
//                boolean isBookmarked = checkIfBookmarked(selectedBootcamp.getId());
//
//                // Toggle bookmark state
//                isBookmarked = !isBookmarked;
//
//                // Perform actions based on bookmark state
//                if (isBookmarked) {
//                    // Add to database
//                    showToast("Booked : " + selectedBootcamp.getTitle());
//                    db.addBookmark(selectedBootcamp.getId());
//                } else {
//                    // Remove from database
//                    showToast("UnBooked : " + selectedBootcamp.getTitle());
//                    db.removeBookmark(selectedBootcamp.getId());
//                }
//
//                // Set the appropriate icon based on bookmark state
//                setBookmarkIcon(btn_bookmark, isBookmarked);
//            }
//        } else
        if (v.getId() == R.id.btn_apply){
            String url = "https://www.dicoding.com/bangun-negeri/bootcamp";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
    }

//    private void showToast(String message) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//    }
//
//    private void setBookmarkIcon(Button button, boolean isBookmarked) {
//        // Set the appropriate icon based on the bookmark state
//        int iconResId = isBookmarked ? R.drawable.ic_unbookmark : R.drawable.ic_bookmark;
//
//        // Set the icon to the button
//        button.setCompoundDrawablesWithIntrinsicBounds(0, 0, iconResId, 0);
//    }
//
//    private boolean checkIfBookmarked(int bootcampId) {
//        // Implement logic to check if the bootcamp is bookmarked in the database
//        // Return true if bookmarked, false otherwise
//        return db.isBootcampBookmarked(bootcampId);
//    }
}
