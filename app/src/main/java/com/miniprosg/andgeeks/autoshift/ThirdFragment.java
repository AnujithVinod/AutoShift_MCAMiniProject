package com.miniprosg.andgeeks.autoshift;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class ThirdFragment extends Fragment {
View myview;
    CardView cardBike,cardCar;
//    BottomNavigationView bottomNavigationView;
//    FragmentManager fragmentManager;


    public MainActivity mainActivity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       myview=inflater.inflate(R.layout.third_layout,container,false);

        mainActivity =(MainActivity)getActivity();

        cardBike=(CardView)myview.findViewById(R.id.cardBike);
        cardCar=(CardView)myview.findViewById(R.id.cardCar);

        cardCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Compare_TwoCars.class);
                startActivity(i);
            }
        });
        cardBike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Compare_TwoBikes.class);
                startActivity(i);
            }
        });

        return myview;



    }
}
