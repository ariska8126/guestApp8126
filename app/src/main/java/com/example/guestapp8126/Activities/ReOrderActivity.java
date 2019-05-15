package com.example.guestapp8126.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.guestapp8126.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReOrderActivity extends AppCompatActivity {

    TextView tv_namaLaundry, tv_namaPemilik, tv_layanan, tv_setrika, tv_antarJemput, tv_desc;
    Button btn_yes, btn_no;
    ImageView imgv_photoPelapak;

    String orderKey, idLaundry, layanan, deskripsi,
            setrika, antarJemput, namaLaundry, namaPemilik, photoPemilik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_order);

        getSupportActionBar().setTitle("Batalkan Order");

        //init view
        tv_namaLaundry = findViewById(R.id.tv_namaLaundry_co);
        tv_namaPemilik = findViewById(R.id.tv_namaPemilik_co);
        tv_desc = findViewById(R.id.tv_deskripsi_co);
        tv_layanan = findViewById(R.id.tv_pakat_layanan_co);
        tv_setrika = findViewById(R.id.tv_setrika_co);
        tv_antarJemput = findViewById(R.id.tv_antarJemput_co);
        btn_yes = findViewById(R.id.btn_yes_co);
        btn_no = findViewById(R.id.btn_no_co);
        imgv_photoPelapak = findViewById(R.id.imgv_photoPelapak_co);

        //get intent
        orderKey = getIntent().getExtras().getString("orderKey");
        idLaundry = getIntent().getExtras().getString("idLaundry");
        layanan = getIntent().getExtras().getString("layanan");
        deskripsi = getIntent().getExtras().getString("deskripsi");
        setrika = getIntent().getExtras().getString("setrika");
        antarJemput = getIntent().getExtras().getString("antarJemput");
        namaLaundry = getIntent().getExtras().getString("namaLaundry");
        namaPemilik = getIntent().getExtras().getString("namaPemilik");
        photoPemilik = getIntent().getExtras().getString("photoPemilik");

        //bind view
        tv_namaLaundry.setText(namaLaundry);
        tv_namaPemilik.setText(namaPemilik);
        tv_layanan.setText(layanan);
        tv_setrika.setText(setrika);
        tv_antarJemput.setText(antarJemput);
        tv_desc.setText(deskripsi);
        Glide.with(this).load(photoPemilik).into(imgv_photoPelapak);

        //onclick
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelOrder(orderKey);
                finish();
            }
        });

        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reference = FirebaseDatabase.getInstance()
                        .getReference("RequestOrder").child(orderKey);
                reference.child("status").setValue("Menunggu Konfirmasi");
                updateUI();
            }
        });

    }

    private void cancelOrder(String key) {

        DatabaseReference deleteReff = FirebaseDatabase.getInstance()
                .getReference("RequestOrder").child(key);
        
        deleteReff.removeValue();

        Toast.makeText(this, "Pesanan Dibatalkan", Toast.LENGTH_SHORT).show();

        updateUI();
    }

    private void updateUI() {
        Intent homeAct = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(homeAct);
        finish();
    }
}
