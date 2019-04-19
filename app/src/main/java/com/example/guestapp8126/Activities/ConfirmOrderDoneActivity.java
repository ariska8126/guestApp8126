package com.example.guestapp8126.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.guestapp8126.R;

public class ConfirmOrderDoneActivity extends AppCompatActivity {

    Button btn_confirmDone;
    TextView tv_proses, tv_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order_done);

        //init view
        btn_confirmDone = findViewById(R.id.btn_confirm_cod);
        tv_proses = findViewById(R.id.tv_proses_cod);
        tv_key = findViewById(R.id.tv_key_cod);

        //get intent
        final String proses = getIntent().getExtras().getString("proses");
        final String key = getIntent().getExtras().getString("transKey");

        //bind view
        tv_proses.setText(proses);
        tv_key.setText(key);

        //on click
        btn_confirmDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent feedbackView = new Intent(getApplicationContext(),
                        PostFeedbackActivity.class);

                feedbackView.putExtra("key", key);

                startActivity(feedbackView);

            }
        });
    }
}
