package com.example.guestapp8126.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.guestapp8126.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText edt_email_login, edt_password_login;
    private Button btn_sign_login, btn_daftar_login;
    private ProgressBar pb_login;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_email_login = findViewById(R.id.edt_guest_mail_login);
        edt_password_login = findViewById(R.id.edt_guest_password_login);
        btn_sign_login = findViewById(R.id.btn_login_login);
        btn_daftar_login = findViewById(R.id.btn_daftar_login);
        pb_login = findViewById(R.id.progressBar_login);
        auth = FirebaseAuth.getInstance();

        pb_login.setVisibility(View.INVISIBLE);

        btn_daftar_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
                finish();
            }
        });

        btn_sign_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btn_daftar_login.setVisibility(View.INVISIBLE);
                btn_sign_login.setVisibility(View.INVISIBLE);
                pb_login.setVisibility(View.INVISIBLE);

                final String mail = edt_email_login.getText().toString();
                final String password = edt_password_login.getText().toString();

                if (mail.isEmpty() || password.isEmpty()){

                    showMessage("passtikan semua field sudah diisi!");
                    btn_daftar_login.setVisibility(View.VISIBLE);
                    btn_sign_login.setVisibility(View.VISIBLE);
                    pb_login.setVisibility(View.INVISIBLE);
                }

                else
                {

                    signIn(mail, password);
                }

            }
        });

    }

    private void signIn(String mail, String password) {

        auth.signInWithEmailAndPassword(mail, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            updateUI();
                        }

                        showMessage(task.getException().getMessage());
                        btn_sign_login.setVisibility(View.VISIBLE);
                        btn_daftar_login.setVisibility(View.VISIBLE);
                        pb_login.setVisibility(View.INVISIBLE);
                    }
                });
    }

    private void updateUI() {
        Intent homeInten = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(homeInten);
        finish();

    }

    private void showMessage(String s) {

        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}
