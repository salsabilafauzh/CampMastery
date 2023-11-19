package com.example.CampMastery.adapter;

import android.content.Context;
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

import java.util.ArrayList;

public class DashboardBootcampAdapter extends RecyclerView.Adapter<DashboardBootcampAdapter.ViewHolder>{

    private ArrayList<Bootcamp> dataList; // Replace String with your data type
    private Context context;

    public DashboardBootcampAdapter(Context context, ArrayList<Bootcamp> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public DashboardBootcampAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_bootcamp_dashboard, parent, false);
        return new ViewHolder(view, view.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardBootcampAdapter.ViewHolder holder, int position) {
        holder.bindData(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView, desc;
        private ImageView image;
        private Context context;

        public ViewHolder(@NonNull View itemView,Context context) {
            super(itemView);
            this.context = context;
            textView = itemView.findViewById(R.id.judulBookmark);
            image = itemView.findViewById(R.id.image_cover);
            desc = itemView.findViewById(R.id.descBookmark);
        }

        public void bindData(Bootcamp data) {
            textView.setText(data.getTitle());
            desc.setText(data.getDeskripsi());
            Glide.with(context)
                    .load(data.getCover())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(image);
        }
    }
}
