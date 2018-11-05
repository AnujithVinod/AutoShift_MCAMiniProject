package com.miniprosg.andgeeks.autoshift;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.nfc.cardemulation.CardEmulation;
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

import com.miniprosg.andgeeks.autoshift.helper.GlobalState_Cars;

import java.io.BufferedReader;

public class FirstFragment extends Fragment {
View myview;
CardView cardBrand,cardBudget,cardBody,cardFuel;

    public MainActivity mainActivity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       myview=inflater.inflate(R.layout.first_layout,container,false);

        mainActivity =(MainActivity)getActivity();

        cardBrand=(CardView)myview.findViewById(R.id.carBrand);
        cardBudget=(CardView)myview.findViewById(R.id.carBudget);
        cardBody=(CardView)myview.findViewById(R.id.carBody);
        cardFuel=(CardView)myview.findViewById(R.id.carFuel);



        cardBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), CarView_Brand.class);
                startActivity(i);
            }
        });

        cardBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), CarView_Budget.class);
                startActivity(i);
            }
        });

        cardBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), CarView_Body.class);
                startActivity(i);
            }
        });

        cardFuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), CarView_Fuel.class);
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
