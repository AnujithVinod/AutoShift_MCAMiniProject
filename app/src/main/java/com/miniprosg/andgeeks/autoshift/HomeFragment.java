package com.miniprosg.andgeeks.autoshift;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.facebook.FacebookSdk.getApplicationContext;

public class HomeFragment extends Fragment {
    View myview;
    CardView cardBike,cardCar,cardCompare,cardDashboard;
    SharaedPrefernceConfig config;
    String UTYPE="user";
    public MainActivity mainActivity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.home_layout,container,false);

        mainActivity =(MainActivity)getActivity();
        config=new SharaedPrefernceConfig(getApplicationContext());
        cardBike=(CardView)myview.findViewById(R.id.cardBike);
        cardCar=(CardView)myview.findViewById(R.id.cardCar);
        cardCompare=(CardView)myview.findViewById(R.id.cardCompare);
        cardDashboard=(CardView)myview.findViewById(R.id.cardDashboard);
        String[] logged_userdata=config.readLoggedUser();
        if (logged_userdata[9]!=null)
        {
            UTYPE="agent";
        }
        else
        {
            UTYPE="user";
        }
        if(!config.readStatus())
        {
            cardDashboard.setVisibility(View.INVISIBLE);
        }

        cardBike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, new SecondFragment());
                fragmentTransaction.commit();
            }
        });

        cardCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, new FirstFragment());
                fragmentTransaction.commit();
            }
        });

        cardCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, new ThirdFragment());
                fragmentTransaction.commit();
            }
        });

        cardDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ProfileActivity.class);
                i.putExtra("utype", UTYPE);
                startActivity(i);
            }
        });

        return myview;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }




}
