package com.example.guestapp8126.Adapters;

import android.content.Context;
import android.media.Rating;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.guestapp8126.Models.Distance;
import com.example.guestapp8126.R;

import java.util.List;

public class DistanceAdapter extends RecyclerView.Adapter<DistanceAdapter
        .MyViewHolder> {

    Context mContext;
    List<Distance> mData;

    public DistanceAdapter(Context mContext, List<Distance> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public DistanceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                           int viewType) {
        View row = LayoutInflater.from(mContext).inflate(R.layout
                .row_distance, parent, false);

        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull DistanceAdapter.MyViewHolder holder, int position) {

        holder.tv_alamat.setText(mData.get(position).getAlamat());
        holder.tv_jarak.setText(String.valueOf(mData.get(position).getJarak()));
        holder.tv_namaLaundry.setText(mData.get(position).getNamaLaundry());
        holder.rb_rate.setRating(mData.get(position).getRate());
        Glide.with(mContext).load(mData.get(position)
                .getPhotoPelapak()).into(holder.imgv_photoPelapak);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_namaLaundry, tv_alamat, tv_jarak;
        RatingBar rb_rate;
        ImageView imgv_photoPelapak;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_alamat = itemView.findViewById(R.id.tv_alamat_rds);
            tv_jarak = itemView.findViewById(R.id.tv_jarak_rds);
            tv_namaLaundry = itemView.findViewById(R.id.tv_namaLaundry_rds);

            rb_rate = itemView.findViewById(R.id.rb_rate_rds);
            imgv_photoPelapak = itemView.findViewById(R.id.imgv_photoPelapak_rds);
        }


    }
}
