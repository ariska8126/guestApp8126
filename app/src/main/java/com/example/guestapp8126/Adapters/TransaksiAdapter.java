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
import com.example.guestapp8126.Activities.ConfirmOrderDoneActivity;
import com.example.guestapp8126.Models.Transaksi;
import com.example.guestapp8126.R;

import java.util.List;

public class TransaksiAdapter extends RecyclerView.Adapter<TransaksiAdapter.MyViewHolder> {

    Context mContext;
    List<Transaksi> mData;

    public TransaksiAdapter(Context mContext, List<Transaksi> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(mContext).inflate(R.layout.row_transaksi,
                parent, false);

        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_namaLaundry.setText(mData.get(position).getNamaLaundry());
        holder.tv_layanan.setText(mData.get(position).getLayanan());
        holder.tv_desc.setText(mData.get(position).getDeskripsi());
        holder.tv_status.setText(mData.get(position).getProses());
//        holder.tv_dateIn.setText(mData.get(position).getIdGuest());
        Glide.with(mContext).load(mData.get(position).getPhotoPelapak()).into(holder.imgv_guestPhoto);
        holder.tv_desc.setText(mData.get(position).getDeskripsi());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_dateIn, tv_layanan, tv_namaLaundry, tv_desc, tv_status;
        ImageView imgv_guestPhoto;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_desc = itemView.findViewById(R.id.tv_desc_rtr);
            tv_dateIn = itemView.findViewById(R.id.tv_dateIn_rtr);
            tv_layanan = itemView.findViewById(R.id.tv_layanan_rtr);
            tv_namaLaundry = itemView.findViewById(R.id.tv_namaLaundry_rtr);
            tv_status = itemView.findViewById(R.id.tv_statusCuci_rtr);

            imgv_guestPhoto = itemView.findViewById(R.id.imgv_laundry_rtr);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent confirmOrderDone = new Intent(mContext, ConfirmOrderDoneActivity.class);
                    int position = getAdapterPosition();

                    confirmOrderDone.putExtra("proses", mData.get(position).getProses());
                    confirmOrderDone.putExtra("transKey", mData.get(position).getTransKey());
                    confirmOrderDone.putExtra("namaLaundry", mData.get(position).getNamaLaundry());
                    confirmOrderDone.putExtra("alamatLaundry", mData.get(position).getAlamatPelapak());
                    confirmOrderDone.putExtra("photoLaundry", mData.get(position).getPhotoPelapak());
                    confirmOrderDone.putExtra("statusBayar", mData.get(position).getStatusBayar());
                    confirmOrderDone.putExtra("layanan", mData.get(position).getLayanan());
                    confirmOrderDone.putExtra("deskripsi", mData.get(position).getDeskripsi());
//                    confirmOrderDone.putExtra("timeStamp", mData.get(position).getTimeStamp());
                    confirmOrderDone.putExtra("antarJemput", mData.get(position).getAntarjemput());
                    confirmOrderDone.putExtra("longitudeLaundry", mData.get(position).getLongitudeLaundry());
                    confirmOrderDone.putExtra("latitudeLaundry", mData.get(position).getLatitudeLaundry());
                    confirmOrderDone.putExtra("idLaundry", mData.get(position).getIdLaundry());
                    confirmOrderDone.putExtra("berat", mData.get(position).getBerat());
                    confirmOrderDone.putExtra("biaya", mData.get(position).getBiaya());
                    confirmOrderDone.putExtra("setrika", mData.get(position).getSetrika());
                    confirmOrderDone.putExtra("guestId", mData.get(position).getIdGuest());
                    confirmOrderDone.putExtra("namaGuest", mData.get(position).getNamaGuest());
                    confirmOrderDone.putExtra("photoGuest", mData.get(position).getPhotoGuest());




                    mContext.startActivity(confirmOrderDone);

                }
            });
        }
    }
}
