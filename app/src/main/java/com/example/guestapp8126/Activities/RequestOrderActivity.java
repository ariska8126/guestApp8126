package com.example.guestapp8126.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guestapp8126.Models.RequestOrder;
import com.example.guestapp8126.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RequestOrderActivity extends AppCompatActivity {

    TextView tv_nama_layanan;
    Button btn_cancel, btn_order;
    Switch sw_setrika, sw_antar_jemput;
    Spinner sp_pewangi;
    EditText edt_deskripsi;

    Boolean bv_setrika, bv_antar_jemput;

    String idLaundry;


    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_order);


        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        //init view
        tv_nama_layanan = findViewById(R.id.tv_paket_layanan_ro);
        btn_cancel = findViewById(R.id.btn_cancel_ro);
        btn_order = findViewById(R.id.btn_order_ro);
        sw_antar_jemput = findViewById(R.id.sw_anjem_ro);
        sw_setrika = findViewById(R.id.sw_setrika_ro);
        sp_pewangi = findViewById(R.id.spinner_pewangi_ro);
        edt_deskripsi = findViewById(R.id.edt_desc_ro);

        //bind value from fragment
        idLaundry = getIntent().getExtras().getString("idLaundry");
        final String namaLayanan = getIntent().getExtras().getString("namaLayanan");
        tv_nama_layanan.setText(namaLayanan);

        //onclick
        sw_setrika.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b == true){
                    bv_setrika = true;
                } else {
                    bv_setrika = false;
                }
            }
        });

        sw_antar_jemput.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true){

                    bv_antar_jemput = true;
                } else {
                    bv_antar_jemput = false;
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!edt_deskripsi.getText().toString().isEmpty() &&
                                bv_antar_jemput != null &&
                                bv_setrika != null){

                    RequestOrder requestOrder = new RequestOrder(idLaundry, user.getUid(),
                            edt_deskripsi.getText().toString(), namaLayanan, bv_setrika,
                            bv_antar_jemput);

                    saveToDatabase(requestOrder);
                }
            }
        });

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
