package com.example.CampMastery.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.CampMastery.Model.Bootcamp;
import com.example.CampMastery.R;
import com.example.CampMastery.db.DbHelper_User;

import java.util.ArrayList;
import java.util.List;

public class DashboardBookmarkAdapter extends RecyclerView.Adapter<DashboardBookmarkAdapter.ViewHolder> {

    List<Bootcamp> result;
    private DbHelper_User db;
    Activity activity;

    public DashboardBookmarkAdapter(List<Bootcamp> result, Activity activity) {
        this.result = result;
        this.activity = activity;
        db = new DbHelper_User(activity);
    }

    @NonNull
    @Override
    public DashboardBookmarkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DashboardBookmarkAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bookmark_hightlight, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardBookmarkAdapter.ViewHolder holder, int position) {
        holder.bind(result.get(position));
    }

    @Override
    public int getItemCount() {
        return result.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, detail;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.judulBookmark);
            detail = itemView.findViewById(R.id.descBookmark);
            img = itemView.findViewById(R.id.image_cover);

        }

        public void bind(Bootcamp bootcamp) {
            name.setText(bootcamp.getTitle());
            detail.setText(bootcamp.getDeskripsi());

            Glide.with(activity)
                    .load(bootcamp.getCover())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img);
        }

    }



}
