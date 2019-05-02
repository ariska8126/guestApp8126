package com.example.guestapp8126.Activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.guestapp8126.Adapters.DistanceAdapter;
import com.example.guestapp8126.Fragments.HomeFragment;
import com.example.guestapp8126.Models.Distance;
import com.example.guestapp8126.Models.OwnerLaundry;
import com.example.guestapp8126.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference laundryRef, guestRef, distanceRef;

    List<Distance> distanceList;

    DistanceAdapter distanceAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //firebase auth
        user = FirebaseAuth.getInstance().getCurrentUser();

        //firebase database
        database = FirebaseDatabase.getInstance();

        //database reference
        laundryRef = database.getReference("OwnerLaundry");
        guestRef = database.getReference("GuestLaundry")
                .child(user.getUid());
        distanceRef = database.getReference("Distance").child(user.getUid());

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();

    }

    @Override
    protected void onStart() {
        super.onStart();

//        guestRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                final String guestId = dataSnapshot.child("guestId").getValue().toString();
//                final double gLat = (double) dataSnapshot.child("guestLatitude").getValue();
//                final double gLong = (double) dataSnapshot.child("guestLongitude").getValue();
//
//                laundryRef.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        for (DataSnapshot lsnapshot: dataSnapshot.getChildren()){
//                            OwnerLaundry ownerLaundry = lsnapshot.getValue(OwnerLaundry.class);
//
//                            final String idLaundry = ownerLaundry.getUserId();
//                            final String photoLaundry = ownerLaundry.getOwnerPhoto();
//                            final String namaLaundry = ownerLaundry.getNamaLaundry();
//                            final String alamat = ownerLaundry.getAlamat();
//                            final double launLat = ownerLaundry.getLatitude();
//                            final double launLong = ownerLaundry.getLongitude();
//                            float rate = ownerLaundry.getRate();
//                            final String statusBuka = ownerLaundry.getStatusBuka();
//
//                            final double jarak = haversine(gLat, gLong, launLat, launLong);
//
//                            Distance distance = new Distance(photoLaundry, namaLaundry,
//                                    idLaundry, guestId, rate, alamat, jarak, statusBuka);
//
//                            saveToDatabase(distance);
//
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }

    private void saveToDatabase(Distance distance) {

//        distanceRef.child(distance.getIdLaundry()).setValue(distance);
    }

    private double haversine(double gLat, double gLong, double launLat, double launLong) {

        double R = 6372800; //meter

        double dlat = toRadian(launLat-gLat);
        double dlon = toRadian(launLong-gLong);
        double lat1 = toRadian(gLat);
        double lat2 = toRadian(launLat);

        double a = Math.sin(dlat/2) * Math.sin(dlat/2) + Math.sin(dlon/2) * Math.sin(dlon/2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));

        return R * 2 * Math.asin(Math.sqrt(a));

    }

    private double toRadian(double v) {
        return Math.PI*v/180;
    }
}
