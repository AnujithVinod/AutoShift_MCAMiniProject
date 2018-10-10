package com.miniprosg.andgeeks.autoshift;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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
EditText test;
Button testb;
//    GlobalState_Cars globalState_cars;
//    EditText test;
//    BottomNavigationView bottomNavigationView;
//    FragmentManager fragmentManager;

    public MainActivity mainActivity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       myview=inflater.inflate(R.layout.first_layout,container,false);
//        Button btntest=(Button)myview.findViewById(R.id.btntestcarname);
//        btntest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                globalState_cars.testdataCarname(test.getText().toString());
//            }
//        });

        mainActivity =(MainActivity)getActivity();

        return myview;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        test=(EditText)view.findViewById(R.id.testedit);
        testb=(Button)view.findViewById(R.id.testbutton);
        test.setText(mainActivity.globalState_cars.gc_name);
        testb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.globalState_cars.gc_name=test.getText().toString();
            }
        });

      final ScrollView scrollView = (ScrollView)view.findViewById(R.id.sv_first);
        scrollView.findViewById(R.id.bankcardId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //globalState_cars.testdataCarname(test.getText().toString());
                Snackbar snackbar = Snackbar.make(scrollView, "Missing Mandatory Parameters", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
//        test=(EditText)view.findViewById(R.id.testcarname);
//        globalState_cars= new GlobalState_Cars();
//        globalState_cars.ResetValues();
//        test.setText(((GlobalState_Cars) this.getApplication()).gc_name.toString());
    }




}
