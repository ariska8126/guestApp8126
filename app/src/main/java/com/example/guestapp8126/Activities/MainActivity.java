package com.example.guestapp8126.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.example.guestapp8126.Fragments.AboutFragment;
import com.example.guestapp8126.Fragments.GuideFragment;
import com.example.guestapp8126.Fragments.HomeFragment;
import com.example.guestapp8126.Fragments.ProfileFragment;
import com.example.guestapp8126.Fragments.RequestOrderFragment;

import com.example.guestapp8126.R;
import com.example.guestapp8126.Fragments.SearchFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FloatingActionButton fab;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab_search);
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.setVisibility(View.INVISIBLE);

                getSupportActionBar().setTitle("Search");
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new SearchFragment()).commit();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        updateNavHeader();

        //set home as main
        getSupportActionBar().setTitle("Home");
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
    }

    private void updateNavHeader() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        TextView nav_username = headerView.findViewById(R.id.nav_name_hd);
        TextView nav_email = headerView.findViewById(R.id.nav_email_hd);
        ImageView nav_photo = headerView.findViewById(R.id.nav_photo_hd);

        nav_email.setText(currentUser.getEmail());
        nav_username.setText(currentUser.getDisplayName());
        Glide.with(this).load(currentUser.getPhotoUrl()).into(nav_photo);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            fab.setVisibility(View.VISIBLE);

            getSupportActionBar().setTitle("Home");
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();

        } else if (id == R.id.nav_profile) {
            fab.setVisibility(View.INVISIBLE);

            getSupportActionBar().setTitle("Profile");
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new ProfileFragment()).commit();

        } else if (id == R.id.nav_guide) {
            fab.setVisibility(View.INVISIBLE);

            getSupportActionBar().setTitle("Petunjuk");
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new GuideFragment()).commit();

        } else if (id == R.id.nav_about) {
            fab.setVisibility(View.INVISIBLE);

            getSupportActionBar().setTitle("About");
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new AboutFragment()).commit();

        } else if(id == R.id.nav_request_order){
            fab.setVisibility(View.INVISIBLE);
            getSupportActionBar().setTitle("Menunggu Persetujuan");
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new RequestOrderFragment()).commit();

        } else if (id == R.id.nav_signout){
            fab.setVisibility(View.INVISIBLE);

            FirebaseAuth.getInstance().signOut();
            Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(loginActivity);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
