package com.example.guestapp8126.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.guestapp8126.Adapters.LayananAdapter;
import com.example.guestapp8126.Adapters.TabLayananAdapter;
import com.example.guestapp8126.Models.Layanan;
import com.example.guestapp8126.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TabLayananFragment extends Fragment {

    private EditText edt_cari;
    private RecyclerView rv_hasil;

    private DatabaseReference layananRef;
    private FirebaseUser user;
    private List<Layanan> layananList;
    private TabLayananAdapter layananAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup
            container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_layanan,
                container, false);
        edt_cari = view.findViewById(R.id.edt_search_ftl);
        rv_hasil = view.findViewById(R.id.rv_layanan_ftl);
        rv_hasil.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_hasil.setHasFixedSize(true);

        layananRef = FirebaseDatabase.getInstance().getReference("LaundryService");
        user = FirebaseAuth.getInstance().getCurrentUser();

//        layananList = new ArrayList<>();

        edt_cari.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()){
                    search(s.toString());
                } else {
                    search("");
                }
            }
        });
        return view;
    }

    private void search(String toString) {
        layananRef.orderByChild("deskripsi").startAt(toString)
                .endAt(toString+"\u8f8f").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()){

                    layananList = new ArrayList<>();
                    for (DataSnapshot layananSnap: dataSnapshot.getChildren()){

                        Layanan layanan = layananSnap.getValue(Layanan.class);
                        layananList.add(layanan);
                    }
                    layananAdapter = new TabLayananAdapter(getActivity(), layananList);
                    rv_hasil.setAdapter(layananAdapter);
                } else {
                    layananList.clear();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();

        layananRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                layananList = new ArrayList<>();
                for (DataSnapshot layananSnap: dataSnapshot.getChildren()){

                    Layanan layanan = layananSnap.getValue(Layanan.class);
                    layananList.add(layanan);
                }
                layananAdapter = new TabLayananAdapter(getActivity(), layananList);
                rv_hasil.setAdapter(layananAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
