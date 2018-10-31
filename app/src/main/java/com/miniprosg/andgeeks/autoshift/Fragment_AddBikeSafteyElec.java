package com.miniprosg.andgeeks.autoshift;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import com.jaredrummler.materialspinner.MaterialSpinner;

public class Fragment_AddBikeSafteyElec extends Fragment {

    View myview;
    EditText eodetails,ebatcap;
    MaterialSpinner sbatt,sspeed,sodo,strip,sfoot,sabs,signi,sphead,stwin;
    String st_batt="DC",st_speed="Analog",st_odo="Analog",st_trip="No",st_foot="Yes",st_abs="No",st_igni="Mono Spark",st_phead="No",st_twin="Yes";
    Button btnback,btnnext;


    public AgentAddBikes agentAddBikes;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {



        myview=inflater.inflate(R.layout.fragment_add_bikesafteyelec,container,false);


        agentAddBikes =(AgentAddBikes) getActivity();
        btnback=(Button)myview.findViewById(R.id.back);
        btnnext=(Button)myview.findViewById(R.id.next);
        eodetails=(EditText)myview.findViewById(R.id.bodetails);
        ebatcap=(EditText)myview.findViewById(R.id.bbatcap);
        eodetails.setText(agentAddBikes.globalState_bikes.gbse_odetails);
        ebatcap.setText(agentAddBikes.globalState_bikes.gbse_batcap);
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setMessage("Please Reselect the Options....");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();


        sbatt = (MaterialSpinner) myview.findViewById(R.id.spinn_bbt);
        sspeed = (MaterialSpinner) myview.findViewById(R.id.spinn_spt);
        sodo = (MaterialSpinner) myview.findViewById(R.id.spinn_sot);
        strip = (MaterialSpinner) myview.findViewById(R.id.spinn_tpm);
        sfoot = (MaterialSpinner) myview.findViewById(R.id.spinn_frt);
        sabs = (MaterialSpinner) myview.findViewById(R.id.spinn_abs);
        signi = (MaterialSpinner) myview.findViewById(R.id.spinn_igt);
        sphead = (MaterialSpinner) myview.findViewById(R.id.spinn_phl);
        stwin = (MaterialSpinner) myview.findViewById(R.id.spinn_twi);

        sbatt.setItems("DC", "AC");
        sbatt.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                st_batt=item;
            }
        });

        sspeed.setItems("Analog", "Digital");
        sspeed.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                st_speed=item;
            }
        });

        sodo.setItems("Analog", "Digital");
        sodo.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                st_odo=item;
            }
        });

        strip.setItems("No", "Yes");
        strip.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                st_trip=item;
            }
        });

        sfoot.setItems("Yes", "No");
        sfoot.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                st_foot=item;
            }
        });

        sabs.setItems("No", "Yes");
        sabs.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                st_abs=item;
            }
        });

        signi.setItems("Mono Spark", "Twin Spark");
        signi.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                st_igni=item;
            }
        });

        sphead.setItems("No", "Yes");
        sphead.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                st_phead=item;
            }
        });

        stwin.setItems("No", "Yes");
        sphead.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                st_twin=item;
            }
        });



        // test.setText(agentAddBikes.globalState_bikes.gc_name);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateInputs())
                {
                    fillsuperclass();

                    FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.addbike_frame, new Fragment_AddBikeFuel());
                    fragmentTransaction.commit();

                }

            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fillsuperclass();


                FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.addbike_frame, new Fragment_AddBikeTyrBrk());
                fragmentTransaction.commit();
            }
        });



        return myview;
    }


    private boolean validateInputs() {

        if("".equals(eodetails.getText().toString())){
            eodetails.setError("Field cannot be empty");
            eodetails.requestFocus();
            return false;
        }
        return true;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        super.onViewCreated(view, savedInstanceState);
        //



    }

    private void fillsuperclass() {

        agentAddBikes.globalState_bikes.gbse_odetails=eodetails.getText().toString();
        agentAddBikes.globalState_bikes.gbse_batcap=ebatcap.getText().toString();
        agentAddBikes.globalState_bikes.gbse_battype=st_batt;
        agentAddBikes.globalState_bikes.gbse_speedom=st_speed;
        agentAddBikes.globalState_bikes.gbse_odom=st_odo;
        agentAddBikes.globalState_bikes.gbse_frest=st_trip;
        agentAddBikes.globalState_bikes.gbse_abs=st_abs;
        agentAddBikes.globalState_bikes.gbse_ignitype=st_igni;
        agentAddBikes.globalState_bikes.gbse_prohl=st_phead;
        agentAddBikes.globalState_bikes.gbse_twinind=st_twin;

    }


}
