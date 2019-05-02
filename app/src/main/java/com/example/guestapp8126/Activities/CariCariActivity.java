package com.example.guestapp8126.Activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.guestapp8126.Adapters.SectionsPageAdapter;
import com.example.guestapp8126.Fragments.Tab_Laundry_Fragment;
import com.example.guestapp8126.Fragments.Tab_Layanan_Fragment;
import com.example.guestapp8126.R;

public class CariCariActivity extends AppCompatActivity {

    private static final String TAG = "Main Activity";

    private SectionsPageAdapter sectionsPageAdapter;

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_cari);

        Log.d(TAG, "onCreate: Starting.");
        sectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager){

        SectionsPageAdapter adapterTest = new SectionsPageAdapter(getSupportFragmentManager());
        adapterTest.addFragment(new Tab_Laundry_Fragment(), "TAB 1");
        adapterTest.addFragment(new Tab_Layanan_Fragment(), "TAB 2");

        viewPager.setAdapter(adapterTest);
    }
}