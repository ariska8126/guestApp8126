package com.example.guestapp8126.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.guestapp8126.Models.GuestLaundry;
import com.example.guestapp8126.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class GuestProfileAdapter extends RecyclerView.Adapter<GuestProfileAdapter.MyViewHolder> {

    //init
    Context mContext;
    List<GuestLaundry> mData;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;

    public GuestProfileAdapter(Context mContext, List<GuestLaundry> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public GuestProfileAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(mContext).inflate(R.layout.row_profile, parent, false);

        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull GuestProfileAdapter.MyViewHolder holder, int position) {

        holder.tv_uid.setText(mData.get(position).getGuestId());
        holder.tv_nama.setText(mData.get(position).getGuestName());
        holder.tv_email.setText(mData.get(position).getGuestMail());
        holder.tv_alamat.setText(mData.get(position).getGuestAlamat());
        holder.tv_phone.setText(mData.get(position).getGuestPhone());
        Glide.with(mContext).load(mData.get(position).getGuestPhoto()).into(holder.imgv_photo);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_nama, tv_alamat, tv_uid, tv_phone, tv_email;
        ImageView imgv_photo;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_uid = itemView.findViewById(R.id.tv_uid_prof);
            tv_nama = itemView.findViewById(R.id.tv_nama_prof);
            tv_email = itemView.findViewById(R.id.tv_mail_prof);
            tv_alamat = itemView.findViewById(R.id.tv_alamat_prof);
            tv_phone = itemView.findViewById(R.id.tv_phone_prof);
            imgv_photo = itemView.findViewById(R.id.imgv_user_photo_prof);

            mAuth = FirebaseAuth.getInstance();
            currentUser = mAuth.getCurrentUser();
        }
    }
}
