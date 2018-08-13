package com.miniprosg.andgeeks.autoshift;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class NavDrawerActivity extends AppCompatActivity {

    private ActionBarDrawerToggle atogg;
    private DrawerLayout adrawlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        adrawlay=(DrawerLayout)findViewById(R.id.drawer_layout);
        atogg=new ActionBarDrawerToggle(this,adrawlay,R.string.open,R.string.close);
        adrawlay.addDrawerListener(atogg);
        atogg.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);
    }

}
