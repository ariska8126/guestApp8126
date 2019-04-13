package com.example.guestapp8126.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.guestapp8126.R;

public class RequestOrderActivity extends AppCompatActivity {

    TextView tv_nama_layanan;

    String idLaundry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_order);

        //init view
        tv_nama_layanan = findViewById(R.id.tv_paket_layanan_ro);

        //bind value
        idLaundry = getIntent().getExtras().getString("idLaundry");
        String namaLayanan = getIntent().getExtras().getString("namaLayanan");
        tv_nama_layanan.setText(namaLayanan);




    }
}
