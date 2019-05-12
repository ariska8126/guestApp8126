package com.example.guestapp8126.Activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.guestapp8126.Adapters.TabAdapter;
import com.example.guestapp8126.Fragments.SearchFragment;
import com.example.guestapp8126.Fragments.TabLayananFragment;
import com.example.guestapp8126.R;

public class CariCariActivity extends AppCompatActivity {

    private TabAdapter tabAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_cari);

        getSupportActionBar().hide();

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLaout_cc);

        tabAdapter = new TabAdapter(getSupportFragmentManager());
        tabAdapter.addFragment(new SearchFragment(), "Laundry");
        tabAdapter.addFragment(new TabLayananFragment(), "Layanan");

        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}