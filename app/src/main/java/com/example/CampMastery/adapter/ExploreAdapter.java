package com.example.CampMastery.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.CampMastery.Activities.DetailBootcampActivity;
import com.example.CampMastery.Fragment.ExploreFragment;
import com.example.CampMastery.Model.Bootcamp;
import com.example.CampMastery.R;
import com.example.CampMastery.db.DbHelper_User;

import java.util.List;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder> {

    private DbHelper_User db;
    List<Bootcamp> result;

    Activity activity;

    public ExploreAdapter(List<Bootcamp> result, Activity activity){
        this.result = result;
        this.activity = activity;
        db = new DbHelper_User(activity);
    }

    @NonNull
    @Override
    public ExploreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bootcamp,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExploreAdapter.ViewHolder holder, int position) {
        holder.bind(result.get(position));
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, detail;
        ImageView img;
        Button book, see;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_item_name);
            detail = itemView.findViewById(R.id.tv_item_detail);
            img = itemView.findViewById(R.id.img_item_photo);
            book = itemView.findViewById(R.id.btn_bookmark);
            see = itemView.findViewById(R.id.btn_see);

            book.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Bootcamp selectedBootcamp = result.get(position);

                        // Check if the bootcamp is bookmarked
                        boolean isBookmarked = checkIfBookmarked(selectedBootcamp.getId());

                        // Toggle bookmark state
                        isBookmarked = !isBookmarked;

                        // Perform actions based on bookmark state
                        if (isBookmarked) {
                            // Add to database
                            showToast("Booked : " + selectedBootcamp.getTitle());
                            db.addBookmark(selectedBootcamp.getId());
                        } else {
                            // Remove from database
                            showToast("UnBooked : " + selectedBootcamp.getTitle());
                            db.removeBookmark(selectedBootcamp.getId());
                        }

                        // Set the appropriate icon based on bookmark state
                        setBookmarkIcon(itemView,isBookmarked);
                    }
                }
            });

            see.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Bootcamp selectedBootcamp = result.get(position);
//                        showToast("See clicked for: " + selectedBootcamp.getTitle());
                        // Get the context from the view
                        Context context = v.getContext();

                        // Create an intent to start the DetailBootcampActivity
                        Intent moveDetail = new Intent(context, DetailBootcampActivity.class);

                        // You might want to pass some data to the DetailBootcampActivity
                        moveDetail.putExtra("BOOTCAMP_ID", selectedBootcamp.getId());

                        // Start the activity
                        context.startActivity(moveDetail);

                    }
                }
            });
        }

        public void bind(Bootcamp bootcamp) {
            name.setText(bootcamp.getTitle());
            detail.setText(bootcamp.getDeskripsi());

            Glide.with(activity)
                    .load(bootcamp.getCover())
                    .into(img);
        }

        private void showToast(String message) {
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
        }
    }

    private void setBookmarkIcon(View itemView ,boolean isBookmarked) {
        Button bookmarkButton = itemView.findViewById(R.id.btn_bookmark);

        // Set the appropriate icon based on the bookmark state
        int iconResId = isBookmarked ? R.drawable.ic_unbookmark : R.drawable.ic_bookmark;

        // Set the icon to the button
        bookmarkButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, iconResId, 0);
    }

    private boolean checkIfBookmarked(int bootcampId) {
        // Implement logic to check if the bootcamp is bookmarked in the database
        // Return true if bookmarked, false otherwise
        return db.isBootcampBookmarked(bootcampId);
    }
}

