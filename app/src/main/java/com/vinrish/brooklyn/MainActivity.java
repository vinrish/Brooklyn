package com.vinrish.brooklyn;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.vinrish.brooklyn.events.Events;
import com.vinrish.brooklyn.fragments.LoginFragment;
import com.vinrish.brooklyn.fragments.ResultFragment;
import com.vinrish.brooklyn.gallery.Gallery;
import com.vinrish.brooklyn.learn.Learn;
import com.vinrish.brooklyn.notices.News;
import com.vinrish.brooklyn.timetable.Timetable;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener{

    CardView noticecard, eventcard, learncard, timecard, resultcard, gallerycard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.show();
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        noticecard = (CardView) findViewById(R.id.news);
        eventcard = findViewById(R.id.events);
        learncard = findViewById(R.id.ebooks);
        timecard = findViewById(R.id.timetables);
        resultcard = findViewById(R.id.results);
        gallerycard = findViewById(R.id.gallery);

        noticecard.setOnClickListener(this);
        eventcard.setOnClickListener(this);
        learncard.setOnClickListener(this);
        timecard.setOnClickListener(this);
        resultcard.setOnClickListener(this);
        gallerycard.setOnClickListener(this);
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

            LoginFragment loginFragment = new LoginFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_frame, loginFragment);
            ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.news:
                Intent n = new Intent(MainActivity.this, News.class);
                startActivity(n);

                break;

            case R.id.events:
                Intent e = new Intent(MainActivity.this, Events.class);
                startActivity(e);

                break;

            case R.id.ebooks:
                Intent l = new Intent(MainActivity.this, Learn.class);
                startActivity(l);

                break;

            case R.id.timetables:
                Intent t = new Intent(MainActivity.this, Timetable.class);
                startActivity(t);

                break;

            case R.id.results:
                ResultFragment resultFragment = new ResultFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_frame, resultFragment);
                //ft.remove(resultFragment);
                ft.addToBackStack(null);
                ft.commit();

                break;

            case R.id.gallery:
                Intent g = new Intent(MainActivity.this, Gallery.class);
                startActivity(g);

                break;
        }

    }
}
