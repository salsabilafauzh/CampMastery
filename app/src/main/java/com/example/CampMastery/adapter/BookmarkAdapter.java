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
import com.example.CampMastery.Model.Bookmark;
import com.example.CampMastery.Model.Bootcamp;
import com.example.CampMastery.R;

import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.ViewHolder> {

    List<Bootcamp> result;

    Activity activity;

    public BookmarkAdapter(List<Bootcamp> result, Activity activity){
        this.result = result;
        this.activity = activity;
    }

    @NonNull
    @Override
    public BookmarkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new BookmarkAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bootcamp,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkAdapter.ViewHolder holder, int position) {
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
                   showToast("Clicked Book");
                }
            });

            see.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showToast("Clicked See");
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
}
