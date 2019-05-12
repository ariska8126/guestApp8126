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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.guestapp8126.Activities.ReOrderActivity;
import com.example.guestapp8126.Models.RequestOrder;
import com.example.guestapp8126.Models.Transaksi;
import com.example.guestapp8126.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

                    int position = getAdapterPosition();

                    final String status = mData.get(position).getStatus();
                    final String transkey = mData.get(position).getOrderKey();
                    final String namaPelapak = mData.get(position).getNamaPelapak();
                    final String photoGuest = mData.get(position).getPhotoGuest();

                    final String longitudeLaundry = mData.get(position).getLongitudeLaundry();
                    final String latitudeLaundry = mData.get(position).getLatitudeLaundry();
                    final String longitudeGuest = mData.get(position).getLongitudeGuest();
                    final String latitudeGuest = mData.get(position).getLatitudeGuest();
                    final Double lonG = Double.parseDouble(longitudeGuest);
                    final Double latG = Double.parseDouble(latitudeGuest);
                    final Double lonL = Double.parseDouble(longitudeLaundry);
                    final Double latL = Double.parseDouble(latitudeLaundry);

                    final String namaGuest = mData.get(position).getNamaGuest();
                    final String idGuest = mData.get(position).getIdGuest();
                    final String idLaundry = mData.get(position).getIdLaundry();
                    final String setrika = mData.get(position).getSetrika();
                    final String antarJemput = mData.get(position).getAntarJemput();
                    final String deskripsi = mData.get(position).getDeskripsi();
                    final String paketLayanan = mData.get(position).getPaketLayanan();
                    final String photoPelapak = mData.get(position).getPhotoPelapak();
                    final String alamatPelapak = mData.get(position).getAlamatPelapak();
                    final String namaLaundry = mData.get(position).getNamaLaundry();

                    String proses = "Masuk Antrian";

                    if (status.equals("Sedang Di Jemput")
                            ||status.equals("Menunggu Dijemput")){

                        Transaksi transaksi = new Transaksi(namaPelapak,photoGuest,longitudeLaundry,
                        latitudeLaundry,longitudeGuest,latitudeGuest, namaGuest,idGuest,idLaundry,
                        setrika,antarJemput,deskripsi,paketLayanan,transkey,photoPelapak,
                        alamatPelapak,namaLaundry,getCurrentTimeStamp(),proses);

                        FirebaseDatabase firebaseDatabase;
                        firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference transRef, requestRef;

                        transRef = firebaseDatabase.getReference("Transaksi");
                        requestRef = firebaseDatabase.getReference("RequestOrder");

                        transRef.child(transkey).setValue(transaksi).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                showMessage("Transaksi Diterima");
                            }
                        });

                        requestRef.child(transkey).removeValue();

                    } else if (status.equals("Di Tolak")
                            || status.equals("Menunggu Konfirmasi")){

                        Intent cancelOrder = new Intent(mContext, ReOrderActivity.class);

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

                }
            });

        }
    }

    private void showMessage(String s) {

        Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
    }

    private String getCurrentTimeStamp() {
        Long timeStamp = System.currentTimeMillis()/1000;
        return timeStamp.toString();
    }
}
