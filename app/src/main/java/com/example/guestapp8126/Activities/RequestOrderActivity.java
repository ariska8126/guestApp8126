package com.example.guestapp8126.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.guestapp8126.Models.RequestOrder;
import com.example.guestapp8126.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RequestOrderActivity extends AppCompatActivity {

    TextView tv_nama_layanan, tv_namaLaundry, tv_namaPelapak;
    ImageView imgv_photoPelapak;
    Button btn_cancel, btn_order;
    Switch sw_setrika, sw_antar_jemput;
    EditText edt_deskripsi;
    String bv_setrika, bv_antar_jemput;

    String idLaundry, layanan, status;

    FirebaseAuth auth;
    FirebaseUser user;

    DatabaseReference guestRef;
    DatabaseReference pelapakRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_order);

        //init view
        tv_nama_layanan = findViewById(R.id.tv_paket_layanan_ro);
        tv_namaLaundry = findViewById(R.id.tv_namaLaundry_ro);
        tv_namaPelapak =findViewById(R.id.tv_namaPelapak_ro);
        imgv_photoPelapak = findViewById(R.id.imgv_photoPelapak_ro);
        btn_cancel = findViewById(R.id.btn_cancel_ro);
        btn_order = findViewById(R.id.btn_order_ro);
        sw_antar_jemput = findViewById(R.id.sw_anjem_ro);
        sw_setrika = findViewById(R.id.sw_setrika_ro);
        edt_deskripsi = findViewById(R.id.edt_desc_ro);

        //getIntent
        idLaundry = getIntent().getExtras().getString("idLaundry");
        layanan = getIntent().getExtras().getString("layanan");

        //bind value from fragment
        tv_nama_layanan.setText(layanan);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        guestRef = FirebaseDatabase.getInstance().getReference("GuestLaundry")
                .child(user.getUid());
        pelapakRef = FirebaseDatabase.getInstance().getReference("OwnerLaundry")
                .child(idLaundry);

        //set value
        status = "Menunggu Konfirmasi";

        //onclick
        sw_setrika.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b == true){
                    bv_setrika = "Ya";
                    Toast.makeText(RequestOrderActivity.this, "Meminta Setrika!", Toast.LENGTH_SHORT).show();
                } else {
                    bv_setrika = "Tidak";
                    Toast.makeText(RequestOrderActivity.this, "Tidak Perlu Setrika!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sw_antar_jemput.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true){

                    bv_antar_jemput = "Ya";
                    Toast.makeText(RequestOrderActivity.this, "Meminta Antar Jemput!", Toast.LENGTH_SHORT).show();

                } else {
                    bv_antar_jemput = "Tidak";
                    Toast.makeText(RequestOrderActivity.this, "Tidak Perlu Antar Jemput!", Toast.LENGTH_SHORT).show();

                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        guestRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final String guestId = dataSnapshot.child("guestId").getValue().toString();
                final String namaGuest = dataSnapshot.child("guestName").getValue().toString();
                final String photoGuest = dataSnapshot.child("guestPhoto").getValue().toString();
                final String latitudeGuest = dataSnapshot.child("guestLatitude").getValue().toString();
                final String longitudeGuest = dataSnapshot.child("guestLongitude").getValue().toString();

                pelapakRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot lapakSnapshot) {

                        final String photoPelapak = lapakSnapshot.child("ownerPhoto").getValue().toString();
                        final String namaPelapak = lapakSnapshot.child("ownerName").getValue().toString();
                        final String namaLaundry = lapakSnapshot.child("namaLaundry").getValue().toString();
                        final String alamatPelapak = lapakSnapshot.child("alamat").getValue().toString();
                        final String latitudeLaundry = lapakSnapshot.child("latitude").getValue().toString();
                        final String longitudeLaundry = lapakSnapshot.child("longitude").getValue().toString();

                        tv_namaLaundry.setText(namaLaundry);
                        tv_namaPelapak.setText(namaPelapak);
                        Glide.with(RequestOrderActivity.this).load(photoPelapak).into(imgv_photoPelapak);

                        if (bv_antar_jemput == null){
                            bv_antar_jemput = "Tidak";
                        }

                        if (bv_setrika == null){
                            bv_setrika = "Tidak";
                        }

                        btn_order.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                if (!edt_deskripsi.getText().toString().isEmpty() &&
                                        bv_antar_jemput != null &&
                                        bv_setrika != null){

                                    RequestOrder requestOrder = new RequestOrder(guestId,namaGuest,photoGuest,
                                            latitudeGuest,longitudeGuest,idLaundry,namaLaundry,photoPelapak,namaPelapak
                                            ,alamatPelapak,latitudeLaundry,longitudeLaundry,layanan,bv_setrika,
                                            bv_antar_jemput,edt_deskripsi.getText().toString(),status, getCurrentTimeStamp());

                                    saveToDatabase(requestOrder);
                                } else {

                                    Toast.makeText(RequestOrderActivity.this, "Isi semua kolom", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private String getCurrentTimeStamp() {
        Long timeStamp = System.currentTimeMillis()/1000;
        return timeStamp.toString();
    }

    private void saveToDatabase(RequestOrder requestOrder) {

        //firebase
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        DatabaseReference requestOrderReff = database.getReference("RequestOrder");

        String key = requestOrderReff.push().getKey();
        requestOrder.setOrderKey(key);


        requestOrderReff.child(key).setValue(requestOrder).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                showMessage("permintaan dikirim");
                updateUI();
            }
        });
    }

    private void updateUI() {

        Intent homeActivity = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(homeActivity);
        finish();

    }

    private void showMessage(String s) {

        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }
}
