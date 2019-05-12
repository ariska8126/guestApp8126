package com.example.guestapp8126.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.guestapp8126.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailLayananActivity extends AppCompatActivity {

    ImageView imgv_photo_laundry, imgv_photo_pelapak;
    TextView tv_nama_laundry, tv_nama_pemilik, tv_alamat, tv_layanan, tv_desc, tv_harga;
    RatingBar ratingBarLaundry;
    Button btn_cancel, btn_order, btn_navigate;

    FirebaseUser user;
    DatabaseReference laundryRef;

    String idLaundry, idLayanan, harga, desc, layanan;

    String googleMap = "com.google.android.apps.maps";
    Uri gmmIntentUri;
    Intent mapIntent;
    String tujuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_layanan);

        //cast view
        imgv_photo_laundry = findViewById(R.id.imgv_photo_laundry_dtla);
        imgv_photo_pelapak = findViewById(R.id.imgv_user_photo_dtla);
        tv_alamat = findViewById(R.id.tv_alamat_dtla);
        tv_desc = findViewById(R.id.tv_desc_dtla);
        tv_harga = findViewById(R.id.tv_biaya_dtla);
        tv_layanan = findViewById(R.id.tv_layanan_dtla);
        tv_nama_laundry = findViewById(R.id.tv_nama_laundry_dtla);
        tv_nama_pemilik = findViewById(R.id.tv_nama_pemilik_dtla);
        ratingBarLaundry = findViewById(R.id.ratingBar_rate_dtla);
        btn_cancel = findViewById(R.id.btn_cancel_dtla);
        btn_order = findViewById(R.id.btn_order_dtla);
        btn_navigate = findViewById(R.id.btn_navigate_dtla);

        user = FirebaseAuth.getInstance().getCurrentUser();
        laundryRef = FirebaseDatabase.getInstance().getReference("OwnerLaundry");

        //get intent
        idLaundry = getIntent().getExtras().getString("idLaundry");
        idLayanan = getIntent().getExtras().getString("idLayanan");
        harga = getIntent().getExtras().getString("harga");
        layanan = getIntent().getExtras().getString("layanan");
        desc = getIntent().getExtras().getString("desc");

        laundryRef.child(idLaundry).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final Double longitude = (Double) dataSnapshot.child("longitude").getValue();
                final Double latitude = (Double) dataSnapshot.child("latitude").getValue();
                final String rate = dataSnapshot.child("rate").getValue().toString();
                Float r = Float.parseFloat(rate);
                final String photoLaundry = dataSnapshot.child("laundryPhoto").getValue().toString();
                final String photoUser = dataSnapshot.child("ownerPhoto").getValue().toString();
                final String alamat = dataSnapshot.child("alamat").getValue().toString();
                final String namaLaundry = dataSnapshot.child("namaLaundry").getValue().toString();
                final String namaPemilik = dataSnapshot.child("ownerName").getValue().toString();

                tv_layanan.setText(layanan);
                tv_alamat.setText(alamat);
                tv_desc.setText(desc);
                tv_harga.setText(harga);
                tv_nama_pemilik.setText(namaPemilik);
                tv_nama_laundry.setText(namaLaundry);
                ratingBarLaundry.setRating(r);
                Glide.with(DetailLayananActivity.this).load(photoLaundry).into(imgv_photo_laundry);
                Glide.with(DetailLayananActivity.this).load(photoUser).into(imgv_photo_pelapak);


                tujuan = latitude+", "+longitude;
                btn_navigate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        gmmIntentUri = Uri.parse("google.navigation:q="+tujuan);
                        mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage(googleMap);

                        if (mapIntent.resolveActivity(getPackageManager()) != null){

                            startActivity(mapIntent);
                        }else {

                            Toast.makeText(DetailLayananActivity.this, "Google Maps Belum Terinstall", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                btn_order.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent orderView = new Intent(DetailLayananActivity.this, RequestOrderActivity.class);
                        orderView.putExtra("idLaundry", idLaundry);
                        orderView.putExtra("layanan", layanan);

                        startActivity(orderView);
                    }
                });

                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        
    }
}
