package com.example.guestapp8126.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.guestapp8126.Activities.LaundryDetailActivity;
import com.example.guestapp8126.Models.OwnerLaundry;
import com.example.guestapp8126.R;
import com.example.guestapp8126.Models.OwnerLaundry;
import com.example.guestapp8126.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ProfileOwnerAdapter extends RecyclerView.Adapter<ProfileOwnerAdapter
        .MyViewHolder> {

    Context mContext;
    List<OwnerLaundry> mData;

    public ProfileOwnerAdapter(Context mContext, List<OwnerLaundry> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(mContext).inflate(R.layout.row_search_result,
                parent, false);

        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {



        holder.tv_nama_laudnry.setText(mData.get(position).getNamaLaundry());
        holder.tv_alamat.setText(mData.get(position).getAlamat());
        Glide.with(mContext).load(mData.get(position).getOwnerPhoto())
                .into(holder.img_owner_laundry_photo);
        holder.rb_laundry.setRating(mData.get(position).getRate());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        RatingBar rb_laundry;
        TextView tv_nama_laudnry, tv_alamat, tv_jarak;
        ImageView img_owner_laundry_photo;
        DatabaseReference guestRef, laundryRef;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_alamat = itemView.findViewById(R.id.tv_alamat_laundry_sr);
            tv_jarak = itemView.findViewById(R.id.tv_jarak_sr);
            tv_nama_laudnry = itemView.findViewById(R.id.tv_nama_laundry_sr);
            rb_laundry = itemView.findViewById(R.id.rb_laundry_sr);
            img_owner_laundry_photo = itemView.findViewById(R.id.imgv_user_photo_sr);

            guestRef = FirebaseDatabase.getInstance().getReference("GuestLaundry");
            laundryRef = FirebaseDatabase.getInstance().getReference("OwnerLaundry");

            double a = 1;
            double b = 2;
            double c = a*b;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent laundryDetailAct = new Intent(mContext, LaundryDetailActivity.class);
                    
                    int position = getAdapterPosition();

                    laundryDetailAct.putExtra("photo_laundry", mData.get(position)
                            .getLaundryPhoto());
                    laundryDetailAct.putExtra("photo_pemilik", mData.get(position)
                            .getOwnerPhoto());
                    laundryDetailAct.putExtra("namaLaundry", mData.get(position)
                            .getNamaLaundry());
                    laundryDetailAct.putExtra("nama_pemilik", mData.get(position)
                            .getOwnerName());
                    laundryDetailAct.putExtra("rate", mData.get(position)
                            .getRate());
                    laundryDetailAct.putExtra("alamat", mData.get(position)
                            .getAlamat());
                    laundryDetailAct.putExtra("laundryID", mData.get(position)
                            .getUserId());
                    laundryDetailAct.putExtra("latitudeLaundry", mData.get(position)
                            .getLatitude());
                    laundryDetailAct.putExtra("longitudeLaundry", mData.get(position)
                            .getLongitude());

                    mContext.startActivity(laundryDetailAct);
                }
            });

        }
    }
}
