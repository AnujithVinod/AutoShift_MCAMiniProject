package com.miniprosg.andgeeks.autoshift;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import com.miniprosg.andgeeks.autoshift.helper.GlobalState_Bikes;

import java.io.BufferedReader;

public class SecondFragment extends Fragment {
    View myview;
    CardView bikeBrand,bikeBudget,bikeBody,bikeFuel;

    public MainActivity mainActivity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.second_layout,container,false);

        mainActivity =(MainActivity)getActivity();

        bikeBrand=(CardView)myview.findViewById(R.id.bikeBrand);
        bikeBudget=(CardView)myview.findViewById(R.id.bikeBudget);
        bikeBody=(CardView)myview.findViewById(R.id.bikeRide);
        bikeFuel=(CardView)myview.findViewById(R.id.bikeFuel);



        bikeBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), BikeView_Brand.class);
                startActivity(i);
            }
        });

        bikeBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), BikeView_Budget.class);
                startActivity(i);
            }
        });

        bikeBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), BikeView_Ride.class);
                startActivity(i);
            }
        });

        bikeFuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), BikeView_Fuel.class);
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