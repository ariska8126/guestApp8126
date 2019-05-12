package com.example.guestapp8126.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.guestapp8126.Activities.DetailLayananActivity;
import com.example.guestapp8126.Activities.RequestOrderActivity;
import com.example.guestapp8126.Models.Layanan;
import com.example.guestapp8126.R;

import java.util.List;

public class TabLayananAdapter extends RecyclerView.Adapter<TabLayananAdapter.MyViewHolder> {

    Context mContext;
    List<Layanan> mData;

    public TabLayananAdapter(Context mContext, List<Layanan> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(mContext).inflate(R.layout.row_layanan,
                parent, false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_nama_layanan.setText(mData.get(position).getNamaLayanan());
        holder.tv_desc.setText(mData.get(position).getDeskripsi());
        holder.tv_biaya.setText(mData.get(position).getBiayaPerkilo());
        Glide.with(mContext).load(mData.get(position).getUserPhoto()).into(holder.imgv_userphoto);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_nama_layanan, tv_desc, tv_biaya;
        ImageView imgv_userphoto;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_biaya = itemView.findViewById(R.id.tv_biaya_dtl_rv);
            tv_desc = itemView.findViewById(R.id.tv_desc_dtl_rv);
            tv_nama_layanan = itemView.findViewById(R.id.tv_nama_layanan_dtl_rv);
            imgv_userphoto = itemView.findViewById(R.id.imgv_user_photo_dtl_rv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent detailLayanan = new Intent(mContext, DetailLayananActivity.class);

                    int position = getAdapterPosition();

                    detailLayanan.putExtra("idLaundry", mData.get(position).getUserId());
                    detailLayanan.putExtra("idLayanan", mData.get(position).getUserId());
                    detailLayanan.putExtra("layanan", mData.get(position).getNamaLayanan());
                    detailLayanan.putExtra("desc", mData.get(position).getDeskripsi());
                    detailLayanan.putExtra("harga", mData.get(position).getBiayaPerkilo());


                    mContext.startActivity(detailLayanan);

                }
            });

        }
    }
}
