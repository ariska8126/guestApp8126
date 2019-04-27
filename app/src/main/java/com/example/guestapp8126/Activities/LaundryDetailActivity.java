package com.example.guestapp8126.Activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.guestapp8126.Adapters.LayananAdapter;
import com.example.guestapp8126.Models.Layanan;
import com.example.guestapp8126.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LaundryDetailActivity extends AppCompatActivity {

    ImageView imgv_photo_laundry, imgv_photo_user;
    TextView tv_nama_laundry, tv_nama_pemilik, tv_alamat;
    RatingBar rate_laundry;

    String photoLaundry,photoPelapak, namaLaundry, namaPelapak,
            alamatPelapak, idLaundry, latitudeLaundry, longitudeLaundry;

    Float rate;

    //layanan
    RecyclerView rv_layanan;
    LayananAdapter layananAdapter;
    ArrayList<Layanan> layananList;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    //user
    FirebaseAuth auth;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry_detail);

        //init
        imgv_photo_laundry = findViewById(R.id.imgv_photo_laundry_dtl);
        imgv_photo_user = findViewById(R.id.imgv_user_photo_dtl);
        tv_nama_laundry = findViewById(R.id.tv_nama_laundry_dtl);
        tv_nama_pemilik = findViewById(R.id.tv_nama_pemilik_dtl);
        rate_laundry = findViewById(R.id.ratingBar_rate);
        tv_alamat = findViewById(R.id.tv_alamat_dtl_rv);

        rv_layanan = findViewById(R.id.rv_layanan_dtl);
        rv_layanan.setLayoutManager(new LinearLayoutManager(this));
        rv_layanan.setHasFixedSize(true);

        layananList = new ArrayList<Layanan>();

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("LaundryService");

        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();

        //get Intent
        photoLaundry = getIntent().getExtras().getString("photo_laundry");
        photoPelapak = getIntent().getExtras().getString("photo_pemilik");
        namaLaundry = getIntent().getExtras().getString("namaLaundry");
        namaPelapak = getIntent().getExtras().getString("nama_pemilik");
        rate = getIntent().getExtras().getFloat("rate");
        alamatPelapak = getIntent().getExtras().getString("alamat");

        idLaundry = getIntent().getExtras().getString("laundryID");
        latitudeLaundry = getIntent().getExtras().getString("latitudeLaundry");
        longitudeLaundry = getIntent().getExtras().getString("longitudeLaundry");

        //bind intentto view
        Glide.with(this).load(photoPelapak).into(imgv_photo_user);
        Glide.with(this).load(photoLaundry).into(imgv_photo_laundry);
        tv_nama_laundry.setText(namaLaundry);
        tv_nama_pemilik.setText(namaPelapak);
        tv_alamat.setText(alamatPelapak);
        rate_laundry.setRating(rate);


        reference.orderByChild("userId").equalTo(idLaundry).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){

                    Layanan layanan = snapshot.getValue(Layanan.class);
                    layananList.add(layanan);
                }

                layananAdapter = new LayananAdapter(LaundryDetailActivity.this, layananList);
                rv_layanan.setAdapter(layananAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(LaundryDetailActivity.this, "Oppss... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
