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
import com.example.guestapp8126.Activities.CancelOrderActivity;
import com.example.guestapp8126.Models.RequestOrder;
import com.example.guestapp8126.R;

import java.util.List;

public class RequestOrderAdapter extends RecyclerView.Adapter<RequestOrderAdapter
        .MyViewHolder> {

    Context mContext;
    List<RequestOrder> mData;

    public RequestOrderAdapter(Context mContext, List<RequestOrder> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(mContext).inflate(R.layout.row_request_order,
                parent, false);

        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_key.setText(mData.get(position).getOrderKey());
        holder.tv_Layanan.setText(mData.get(position).getPaketLayanan());
        holder.tv_deskripsi.setText(mData.get(position).getDeskripsi());
        holder.tv_namaLaundry.setText(mData.get(position).getNamaLaundry());
        holder.tv_status.setText(mData.get(position).getStatus());
        Glide.with(mContext).load(mData.get(position).getPhotoPelapak())
                .into(holder.imgv_photoPelapak);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_Layanan, tv_deskripsi, tv_key, tv_namaLaundry, tv_status;
        ImageView imgv_photoPelapak;

        public MyViewHolder(View itemView) {
            super(itemView);


            tv_deskripsi = itemView.findViewById(R.id.tv_desc_rro);
            tv_key = itemView.findViewById(R.id.tv_key_rro);
            tv_Layanan = itemView.findViewById(R.id.tv_layanan_rro);
            tv_namaLaundry = itemView.findViewById(R.id.tv_namaLaundry_rro);
            tv_status = itemView.findViewById(R.id.tv_status_rro);

            imgv_photoPelapak = itemView.findViewById(R.id.imgv_photoPelapak_rro);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent cancelOrder = new Intent(mContext, CancelOrderActivity.class);
                    int position = getAdapterPosition();

                    cancelOrder.putExtra("orderKey", mData.get(position).getOrderKey());
                    cancelOrder.putExtra("idLaundry", mData.get(position).getIdLaundry());
                    cancelOrder.putExtra("layanan", mData.get(position).getPaketLayanan());
                    cancelOrder.putExtra("deskripsi", mData.get(position).getDeskripsi());
                    cancelOrder.putExtra("setrika", mData.get(position).getSetrika());
                    cancelOrder.putExtra("antarJemput", mData.get(position).getAntarJemput());
                    cancelOrder.putExtra("namaLaundry", mData.get(position).getNamaLaundry());
                    cancelOrder.putExtra("namaPemilik", mData.get(position).getNamaPelapak());
                    cancelOrder.putExtra("photoPemilik", mData.get(position).getPhotoPelapak());

                    mContext.startActivity(cancelOrder);


                }
            });

        }
    }
}
