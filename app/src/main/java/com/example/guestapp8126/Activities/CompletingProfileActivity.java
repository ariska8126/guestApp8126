package com.example.guestapp8126.Activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.guestapp8126.Models.GuestLaundry;
import com.example.guestapp8126.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CompletingProfileActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_ACCESS_COARSE_LOCATION = 1;
    private Button btn_get_location_cp, btn_submit_cp;
    private ProgressBar pb_get_location_cp, pb_submit_cp;
    private EditText edt_alamat_cp, edt_phone_cp;
    private TextView tv_email_cp, tv_name_cp, tv_location_cp;
    private ImageView imgv_photo_cp;
    private Double latitude, longitude;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    private FusedLocationProviderClient fusedLocationProviderClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completing_profile);

        btn_get_location_cp = findViewById(R.id.btn_fetch);
        btn_submit_cp = findViewById(R.id.btn_submit_cp);

        pb_get_location_cp = findViewById(R.id.progressBar_getlokasi_cp);
        pb_submit_cp = findViewById(R.id.progressBar_submit_cp);

        edt_alamat_cp = findViewById(R.id.edt_alamat_cp);
        edt_phone_cp = findViewById(R.id.edt_phone_cp);

        tv_email_cp = findViewById(R.id.tv_email_cp);
        tv_name_cp = findViewById(R.id.tv_name_cp);
        tv_location_cp = findViewById(R.id.user_location);

        imgv_photo_cp = findViewById(R.id.imgv_user_photo_cp);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        //visibility
        pb_submit_cp.setVisibility(View.INVISIBLE);
        pb_get_location_cp.setVisibility(View.INVISIBLE);

        //bind view from auth
        Glide.with(this).load(currentUser.getPhotoUrl()).into(imgv_photo_cp);
        tv_name_cp.setText(currentUser.getDisplayName());
        tv_email_cp.setText(currentUser.getEmail());

        //onClick
        btn_get_location_cp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btn_get_location_cp.setVisibility(View.INVISIBLE);
                pb_get_location_cp.setVisibility(View.VISIBLE);

                fetchLocation();
            }
        });

        btn_submit_cp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btn_submit_cp.setVisibility(View.INVISIBLE);
                pb_submit_cp.setVisibility(View.VISIBLE);

                if (!edt_alamat_cp.getText().toString().isEmpty()
                        && !edt_phone_cp.getText().toString().isEmpty()
                        && latitude != null
                        && longitude != null){

                    GuestLaundry guestLaundry = new GuestLaundry(currentUser.getUid(),
                            edt_alamat_cp.getText().toString(),
                            edt_phone_cp.getText().toString(),
                            latitude,
                            longitude,
                            currentUser.getPhotoUrl().toString(),
                            currentUser.getDisplayName(),
                            currentUser.getEmail());

                    simpanProfileGuest(guestLaundry);

                }
            }


        });
    }

    private void fetchLocation(){

        if (ContextCompat.checkSelfPermission(CompletingProfileActivity.this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(CompletingProfileActivity.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)){

                new AlertDialog.Builder(this).setTitle("Require Location Permission").setMessage("you have to give this permission to access feature")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                ActivityCompat.requestPermissions(CompletingProfileActivity.this,
                                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_READ_ACCESS_COARSE_LOCATION);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .create().show();
            }
            else
            {
                ActivityCompat.requestPermissions(CompletingProfileActivity.this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_READ_ACCESS_COARSE_LOCATION);
            }
            btn_submit_cp.setVisibility(View.VISIBLE);
            pb_submit_cp.setVisibility(View.INVISIBLE);
        }
        else
        {
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null){
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();

                                pb_get_location_cp.setVisibility(View.INVISIBLE);
                                btn_get_location_cp.setVisibility(View.VISIBLE);

                                tv_location_cp.setText("Latitude= "+latitude+ "\nLongitude= "+longitude);

                            }
                        }
                    });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_READ_ACCESS_COARSE_LOCATION){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            }
            else
            {

            }
        }
    }


    private void simpanProfileGuest(GuestLaundry guestLaundry) {

        DatabaseReference guestRef = FirebaseDatabase.getInstance().getReference().child("GuestLaundry");

        String key = guestRef.getKey();
        guestLaundry.setGuestKey(key);

        guestRef.child(currentUser.getUid()).setValue(guestLaundry).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                showMessage("data anda telah dilengkapi");
                btn_submit_cp.setVisibility(View.VISIBLE);
                pb_submit_cp.setVisibility(View.INVISIBLE);

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

        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}