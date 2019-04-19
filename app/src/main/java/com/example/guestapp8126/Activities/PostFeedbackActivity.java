package com.example.guestapp8126.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guestapp8126.Models.Feedback;
import com.example.guestapp8126.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostFeedbackActivity extends AppCompatActivity {

    RatingBar rb_rate;
    TextView tv_namaLaundry, tv_alamat, tv_layanan;
    EditText edt_komentar;
    ImageView imgv_laundry;
    Button btn_submit;

    FirebaseUser user;

    DatabaseReference laundrReff;
    DatabaseReference feedBackReff;
    DatabaseReference deleteTransReff;

    String idLaundry, idGuest, komentar, orderKey, namaGuest, namaLaundry, alamatLaundry;

    Float rateValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_feedback);

        //init view
        tv_alamat = findViewById(R.id.tv_alamatLaundry_pf);
        tv_layanan = findViewById(R.id.tv_layanan_pf);
        tv_namaLaundry = findViewById(R.id.tv_namaLaundry_pf);
        imgv_laundry = findViewById(R.id.imgv_photoLaundry_pf);
        rb_rate = findViewById(R.id.rb_rate_pf);
        rb_rate.setMax(5);

        edt_komentar = findViewById(R.id.edt_komentar_pf);
        btn_submit = findViewById(R.id.btn_submit_pf);

        user = FirebaseAuth.getInstance().getCurrentUser();


        rb_rate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rateValue = v;
                Toast.makeText(PostFeedbackActivity.this, "Starts:" +rateValue, Toast.LENGTH_SHORT).show();
            }
        });

        //get value
        idLaundry = "LoliPp1q6aXtkLHVlFdVwbbPVU43";
        idGuest = user.getUid();
        orderKey = "0987654321";
        namaGuest = user.getDisplayName();

        //on click
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rb_rate != null && !edt_komentar.getText().toString().isEmpty()){

                    Feedback feedback = new Feedback(idLaundry, idGuest,
                            edt_komentar.getText().toString(), orderKey, rateValue, namaGuest);
                    postFeedBack(feedback);
                    deleteTrans(orderKey);
                }
            }
        });

    }

    private void deleteTrans(String orderKey) {

        deleteTransReff = FirebaseDatabase.getInstance()
                .getReference("Transaksi").child(orderKey);
        deleteTransReff.removeValue();

    }

    private void postFeedBack(Feedback feedback) {

        feedBackReff = FirebaseDatabase.getInstance().getReference("Feedback");
        String key = feedBackReff.push().getKey();
        feedback.setFeedbackKey(key);

        feedBackReff.child(key).setValue(feedback)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                showMessage("Terimakasih atas tanggapan anda!");
            }
        });
    }

    private void showMessage(String s) {

        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        updateUI();
    }

    private void updateUI() {

        Intent homeAct = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(homeAct);
    }
}
