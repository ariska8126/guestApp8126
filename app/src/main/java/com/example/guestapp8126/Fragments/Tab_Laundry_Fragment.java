package com.example.guestapp8126.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guestapp8126.R;

public class Tab_Laundry_Fragment extends Fragment {

    private static final String TAG = "TAB 1 FRAGMENT";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab_laundry,
                container, false);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
