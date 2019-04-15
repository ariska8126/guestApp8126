package com.example.guestapp8126.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guestapp8126.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CancelOrderActivity extends AppCompatActivity {

    TextView tv_layanan, tv_nama_laundry, tv_key, tv_desc;
    Button btn_yes, btn_no;

    String layanan, nama, key, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_order);

        getSupportActionBar().setTitle("Batalkan Order");

        //init view
        tv_desc = findViewById(R.id.tv_desc_co);
        tv_key = findViewById(R.id.tv_keyOrder_co);
        tv_layanan = findViewById(R.id.tv_layanan_co);
        tv_nama_laundry = findViewById(R.id.tv_nama_laundry_co);
        btn_yes = findViewById(R.id.btn_no_co);

        //get intent
        key = getIntent().getExtras().getString("orderKey");
        nama = getIntent().getExtras().getString("idLaundry");
        layanan = getIntent().getExtras().getString("layanan");
        desc = getIntent().getExtras().getString("deskripsi");

        //bind view
        tv_nama_laundry.setText(nama);
        tv_layanan.setText(layanan);
        tv_desc.setText(desc);
        tv_key.setText(key);

        //onclick
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cancelOrder(key);
            }
        });

    }

    private void cancelOrder(String key) {

        DatabaseReference deleteReff = FirebaseDatabase.getInstance()
                .getReference("RequestOrder").child(key);
        
        deleteReff.removeValue();

        Toast.makeText(this, "Pesanan Dibatalkan", Toast.LENGTH_SHORT).show();


    }

    private void updateUI() {
        Intent homeAct = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(homeAct);
        finish();
    }
}
