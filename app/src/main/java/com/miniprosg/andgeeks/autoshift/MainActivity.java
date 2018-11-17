package com.miniprosg.andgeeks.autoshift;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.miniprosg.andgeeks.autoshift.helper.CheckNetworkStatus;
import com.miniprosg.andgeeks.autoshift.helper.GlobalState_Cars;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SharaedPrefernceConfig config;
    BottomNavigationView bottomNavigationView;
    public GlobalState_Cars globalState_cars;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globalState_cars=new GlobalState_Cars();
        globalState_cars.ResetValues();
        final FragmentManager fragmentManager= getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame,new HomeFragment()).commit();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.nav_home:
                                //Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_LONG).show();
                                fragmentManager.beginTransaction().replace(R.id.content_frame,new HomeFragment()).commit();
                                break;
                            case R.id.nav_cars:
                                //Toast.makeText(getApplicationContext(),"Cars",Toast.LENGTH_LONG).show();
                                fragmentManager.beginTransaction().replace(R.id.content_frame,new FirstFragment()).commit();
                                break;
                            case R.id.nav_bike:
                                //Toast.makeText(getApplicationContext(),"Bikes",Toast.LENGTH_LONG).show();
                                fragmentManager.beginTransaction().replace(R.id.content_frame,new SecondFragment()).commit();
                                break;
                            case R.id.nav_compare:
                                //Toast.makeText(getApplicationContext(),"Compare",Toast.LENGTH_LONG).show();
                                fragmentManager.beginTransaction().replace(R.id.content_frame,new ThirdFragment()).commit();
                                break;
                            case R.id.nav_dashboard:
                                login_userdashboard();
                                break;
                        }
                        return true;
                    }
                });

        

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        config=new SharaedPrefernceConfig(getApplicationContext());

    }


    private void login_userdashboard() {

        if(config.readStatus())
        {

            Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
            i.putExtra("utype", "user");
            startActivity(i);
        }
        else
        {
            CheckNetworkStatus checkNetworkStatus=new CheckNetworkStatus();
            boolean status=checkNetworkStatus.isNetworkAvailable(MainActivity.this);
            if (status)
            {
                Toast.makeText(getApplicationContext(), "You are not logged in\nPlease Login to Continue", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), login_activity.class);
                i.putExtra("utype", "user");
                startActivity(i);

            }
            else
            {
                Toast.makeText(getApplicationContext(),"No Network Connectivity\nPlease connect to a WIFI Network or turn on Cellular Data",Toast.LENGTH_LONG).show();
            }

        }

    }

    public void login_agentdashboard()
    {

        if(config.readStatus())
        {

            Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
            i.putExtra("utype", "agent");
            startActivity(i);
        }
        else
        {
            CheckNetworkStatus checkNetworkStatus=new CheckNetworkStatus();
            boolean status=checkNetworkStatus.isNetworkAvailable(MainActivity.this);
            if (status)
            {
                Toast.makeText(getApplicationContext(), "You are not logged in\nPlease Login to Continue", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), login_activity.class);
                i.putExtra("utype", "agent");
                startActivity(i);

            }
            else
            {
                Toast.makeText(getApplicationContext(),"No Network Connectivity\nPlease connect to a WIFI Network or turn on Cellular Data",Toast.LENGTH_LONG).show();
            }

        }

    }
    public void login(View v)
    {
        login_userdashboard();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
            builder1.setMessage("Exit the Application? Any unsaved changes will be lost!");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            MainActivity.super.onBackPressed();
                            //ARE YOU LOGGED IN ALREADY? USING SHARED PREFERENCES

                        }
                    });

            builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager= getFragmentManager();
        if (id == R.id.nav_browse_cars) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new FirstFragment()).commit();
            // Handle the camera action
        } else if (id == R.id.nav_browse_bikes) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new SecondFragment()).commit();
        } else if (id == R.id.nav_compare_all) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new ThirdFragment()).commit();
        } else if (id == R.id.nav_home) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new HomeFragment()).commit();
        } else if (id == R.id.nav_merchent) {
            login_agentdashboard();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}