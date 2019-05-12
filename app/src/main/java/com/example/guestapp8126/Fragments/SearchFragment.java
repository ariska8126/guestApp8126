package com.example.guestapp8126.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.guestapp8126.Adapters.LayananAdapter;
import com.example.guestapp8126.Adapters.ProfileOwnerAdapter;
import com.example.guestapp8126.Models.Layanan;
import com.example.guestapp8126.Models.OwnerLaundry;
import com.example.guestapp8126.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    RecyclerView searchLaundry;
    ProfileOwnerAdapter profileOwnerAdapter;
    LayananAdapter layananAdapter;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference laundryRef, guestRef;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    List<OwnerLaundry> ownerLaundryList;

    Query query1;

    EditText edt_search;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View searchView = inflater.inflate(R.layout.fragment_search, container,
                false);
        searchLaundry = searchView.findViewById(R.id.rv_result_fs);
        searchLaundry.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchLaundry.setHasFixedSize(true);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();
        laundryRef = firebaseDatabase.getReference("OwnerLaundry");
        guestRef = firebaseDatabase.getReference("GuestLaundry").child(currentUser.getUid());

        edt_search = searchView.findViewById(R.id.edt_search_fs);

        edt_search.addTextChangedListener(new TextWatcher() {
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

        return searchView;
    }

    private void search(String as) {

        laundryRef.orderByChild("alamat").startAt(as).endAt(as+"\uf8ff")
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()){

                    ownerLaundryList = new ArrayList<>();
                    for (DataSnapshot lapakSnap: dataSnapshot.getChildren()){

                        OwnerLaundry ownerLaundry = lapakSnap.getValue(OwnerLaundry.class);
                        ownerLaundryList.add(ownerLaundry);
                    }

                    profileOwnerAdapter = new ProfileOwnerAdapter(getActivity(), ownerLaundryList);
                    searchLaundry.setAdapter(profileOwnerAdapter);
                } else {
                    ownerLaundryList.clear();
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

        guestRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final String guestId = dataSnapshot.child("guestId").getValue().toString();
                final double gLat = (double) dataSnapshot.child("guestLatitude").getValue();
                final double gLong = (double) dataSnapshot.child("guestLongitude").getValue();

                laundryRef.orderByChild("statusBuka").equalTo("buka")
                        .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        ownerLaundryList = new ArrayList<>();
                        ownerLaundryList.clear();

                        for (DataSnapshot lsnapshot: dataSnapshot.getChildren()){
                            OwnerLaundry ownerLaundry = lsnapshot.getValue(OwnerLaundry.class);

                            final String idLaundry = ownerLaundry.getUserId();
                            final String photoLaundry = ownerLaundry.getOwnerPhoto();
                            final String namaLaundry = ownerLaundry.getNamaLaundry();
                            final String alamat = ownerLaundry.getAlamat();
                            final double launLat = ownerLaundry.getLatitude();
                            final double launLong = ownerLaundry.getLongitude();
                            float rate = ownerLaundry.getRate();
                            final String statusBuka = ownerLaundry.getStatusBuka();

                            final double jarak = haversine(gLat, gLong, launLat, launLong);

                            ownerLaundry.setJarak(jarak);

                            ownerLaundryList.add(ownerLaundry);

                        }

                        Collections.sort(ownerLaundryList, new Comparator<OwnerLaundry>() {
                            @Override
                            public int compare(OwnerLaundry o1, OwnerLaundry o2) {
                                return Double.compare(o1.getJarak(), o2.getJarak());
                            }
                        });

                        profileOwnerAdapter = new ProfileOwnerAdapter(getActivity(), ownerLaundryList);
                        searchLaundry.setAdapter(profileOwnerAdapter);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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
