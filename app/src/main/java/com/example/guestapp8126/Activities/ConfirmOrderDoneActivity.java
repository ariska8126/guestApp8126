package com.example.guestapp8126.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.guestapp8126.Models.NewUserChat;
import com.example.guestapp8126.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ConfirmOrderDoneActivity extends AppCompatActivity {

    Button btn_confirmDone, btn_chat, btn_navigate;

    TextView tv_namaLaundry, tv_alamatLaundry, tv_proses, tv_Layanan,
            tv_Deskripsi, tv_timeStamp,
            tv_antar_jemput, tv_setrika, tv_berat,
            tv_biaya, tv_statusBayar, tv_transKey;

    ImageView imgv_laundryPhoto;

    String idLaundry, photoLaundry, latitudeLaundry, longitudeLaundry, namaLaundry,
            alamatLaundry, guestId, timeStamp, transKey, layanan, deskripsi,
            antarJemput, setrika, proses, berat, biaya, statusBayar, namaGuest, photoGuest;

    FirebaseUser user;
    DatabaseReference transReference, chatReference, newUserChatRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order_done);

        //firebase
        user = FirebaseAuth.getInstance().getCurrentUser();
        transReference = FirebaseDatabase.getInstance().getReference("Transaksi");
        chatReference = FirebaseDatabase.getInstance().getReference("Chat");
        newUserChatRef = FirebaseDatabase.getInstance().getReference("NewUserChat");

        //init view
        btn_confirmDone = findViewById(R.id.btn_confirm_cod);
        btn_chat = findViewById(R.id.btn_chat_cod);
        btn_navigate = findViewById(R.id.btn_nav_cod);

        tv_proses = findViewById(R.id.tv_proses_cod);
        tv_Layanan = findViewById(R.id.tv_layanan_cod);
        tv_Deskripsi = findViewById(R.id.tv_desc_cod);
        tv_setrika = findViewById(R.id.tv_setrika_cod);
        tv_statusBayar = findViewById(R.id.tv_statusBayar_cod);
        tv_biaya = findViewById(R.id.tv_biaya_cod);
        tv_berat = findViewById(R.id.tv_berat_cod);
        tv_antar_jemput = findViewById(R.id.tv_anJem_cod);
        tv_timeStamp = findViewById(R.id.tv_date_cod);
        tv_transKey = findViewById(R.id.tv_transKey_cod);
        tv_namaLaundry = findViewById(R.id.tv_namaLaundry_cod);
        tv_alamatLaundry = findViewById(R.id.tv_alamatLaundry_cod);

        imgv_laundryPhoto = findViewById(R.id.imgv_photoLaundry_cod);

        //get intent
        transKey = getIntent().getExtras().getString("transKey");
        proses = getIntent().getExtras().getString("proses");
        namaLaundry = getIntent().getExtras().getString("namaLaundry");
        alamatLaundry = getIntent().getExtras().getString("alamatLaundry");
        photoLaundry = getIntent().getExtras().getString("photoLaundry");
        statusBayar = getIntent().getExtras().getString("statusBayar");
        layanan = getIntent().getExtras().getString("layanan");
        deskripsi = getIntent().getExtras().getString("deskripsi");
//        timeStamp = getIntent().getExtras().getString("timeStamp");
        antarJemput = getIntent().getExtras().getString("antarJemput");
        longitudeLaundry = getIntent().getExtras().getString("longitudeLaundry");
        latitudeLaundry = getIntent().getExtras().getString("latitudeLaundry");
        idLaundry = getIntent().getExtras().getString("idLaundry");
        berat = getIntent().getExtras().getString("berat");
        biaya = getIntent().getExtras().getString("biaya");
        setrika = getIntent().getExtras().getString("setrika");
        guestId = getIntent().getExtras().getString("guestId");
        namaGuest = getIntent().getExtras().getString("namaGuest");
        photoGuest = getIntent().getExtras().getString("photoGuest");

        if (antarJemput.equals("Ya")){
            btn_navigate.setVisibility(View.INVISIBLE);
        } else {
            btn_navigate.setVisibility(View.VISIBLE);
        }

        if (proses.equals("Selesai")){

            btn_confirmDone.setVisibility(View.VISIBLE);
        } else {
            btn_confirmDone.setVisibility(View.INVISIBLE);
        }

        //bind view
        tv_namaLaundry.setText(namaLaundry);
        tv_alamatLaundry.setText(alamatLaundry);
        Glide.with(this).load(photoLaundry).into(imgv_laundryPhoto);
        tv_statusBayar.setText(statusBayar);
        tv_transKey.setText(transKey);
        tv_Layanan.setText(layanan);
        tv_Deskripsi.setText(deskripsi);
        tv_antar_jemput.setText(antarJemput);
        tv_setrika.setText(setrika);
        tv_berat.setText(berat);tv_biaya.setText(biaya);
        tv_proses.setText(proses);


        //on click
        btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chatIntent = new Intent(ConfirmOrderDoneActivity.this, ChatActivity.class);
                chatIntent.putExtra("idLaundry", idLaundry);
                chatIntent.putExtra("photoLaundry", photoLaundry);


                startActivity(chatIntent);

            }
        });

        btn_confirmDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (statusBayar.equals("Sudah Dibayar")){

                    Intent feedbackView = new Intent(getApplicationContext(),
                            PostFeedbackActivity.class);

                    feedbackView.putExtra("photoLaundry", photoLaundry);
                    feedbackView.putExtra("namaLaundry", namaLaundry);
                    feedbackView.putExtra("alamatLaundry", alamatLaundry);
                    feedbackView.putExtra("layanan", layanan);
                    feedbackView.putExtra("idLaundry", idLaundry);
                    feedbackView.putExtra("transKey", transKey);
                    feedbackView.putExtra("guestId", guestId);
                    feedbackView.putExtra("namaGuest", namaGuest);
                    feedbackView.putExtra("photoGuest", photoGuest);


                    startActivity(feedbackView);
                } else {

                    showMessage();
                }


            }
        });
    }

    private void showMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Anda belum membayar").setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Sudah Bayar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ConfirmOrderDoneActivity.this, "Kami akan kirimkan notifikasi ke Laundry", Toast.LENGTH_SHORT).show();
                        String msg = "Saya sudah melakukan pembayaran transaksi, mohon update informasi pembayaran cucian saya. terimakasih";

                        sendMessage(guestId, idLaundry, msg);
                        NewUserChat newUserChat = new NewUserChat(guestId, idLaundry, photoGuest
                                ,namaGuest, msg, getCurrentTimeStamp());
                        saveUserNewChat(guestId, idLaundry, newUserChat);

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void sendMessage(String sender, String receiver, String message){

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender", sender);
        hashMap.put("receiver", receiver);
        hashMap.put("message", message);
        hashMap.put("timeStamp", getCurrentTimeStamp());

        chatReference.push().setValue(hashMap);

    }

    private String getCurrentTimeStamp() {
        Long timeStamp = System.currentTimeMillis()/1000;
        return timeStamp.toString();
    }

    private void saveUserNewChat(String uid, String userId, NewUserChat newUserChat) {
        newUserChatRef.child(userId).child(uid).setValue(newUserChat);
    }
}
