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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import com.jaredrummler.materialspinner.MaterialSpinner;

public class Fragment_AddBikeTransEng extends Fragment {

    View myview;
    EditText eetype,egear,edesc,edisp,ecylno,emaxpow,emaxtorq,etopspeed,eaccelaration,eodetails;
    Button btnback,btnnext;
    MaterialSpinner sdrivetype,sstart,scool,sclutch;
    String st_drivetype="Manual",st_start="Manual",st_cool="Air Cooled",st_clutch="Wet Clutch";


    public AgentAddBikes agentAddBikes;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.fragment_add_biketranseng,container,false);


        agentAddBikes =(AgentAddBikes) getActivity();
        btnback=(Button)myview.findViewById(R.id.back);
        btnnext=(Button)myview.findViewById(R.id.next);
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setMessage("Please Recheck Details....");
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
        eetype=(EditText)myview.findViewById(R.id.betype);
        egear=(EditText)myview.findViewById(R.id.bgear);
        edesc=(EditText)myview.findViewById(R.id.bdesc);
        edisp=(EditText)myview.findViewById(R.id.bdisp);
        ecylno=(EditText)myview.findViewById(R.id.bcylno);
        emaxpow=(EditText)myview.findViewById(R.id.bmaxpow);
        emaxtorq=(EditText)myview.findViewById(R.id.bmaxtorq);
        etopspeed=(EditText)myview.findViewById(R.id.btopspeed);
        eaccelaration=(EditText)myview.findViewById(R.id.bacceration);
        eodetails=(EditText)myview.findViewById(R.id.bodetails);
        getActivity().setTitle("Transmission and Engine Details");
        eetype.setText(agentAddBikes.globalState_bikes.gbte_type);
        egear.setText(agentAddBikes.globalState_bikes.gbte_gearno);
        edesc.setText(agentAddBikes.globalState_bikes.gbte_drive);
        edisp.setText(agentAddBikes.globalState_bikes.gbte_disp);
        ecylno.setText(agentAddBikes.globalState_bikes.gbte_cylno);
        emaxpow.setText(agentAddBikes.globalState_bikes.gbte_maxpow);
        emaxtorq.setText(agentAddBikes.globalState_bikes.gbte_maxtorque);
        etopspeed.setText(agentAddBikes.globalState_bikes.gbte_maxspeed);
        eaccelaration.setText(agentAddBikes.globalState_bikes.gbte_acceleration);
        eodetails.setText(agentAddBikes.globalState_bikes.gbte_odetails);

        sdrivetype = (MaterialSpinner) myview.findViewById(R.id.spinn_drivetype);
        sstart = (MaterialSpinner) myview.findViewById(R.id.spinn_start);
        scool = (MaterialSpinner) myview.findViewById(R.id.spinn_cool);
        sclutch = (MaterialSpinner) myview.findViewById(R.id.spinn_clutch);

        sdrivetype.setItems("Manual", "Automatic", "Hybrid");
        sdrivetype.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                st_drivetype=item;
            }
        });

        sstart.setItems("Manual", "Electric/Manual", "Electric ");
        sstart.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                st_start=item;
            }
        });

        scool.setItems("Air Cooled", "Oil Cooled", "Water Cooled");
        scool.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                st_cool=item;
            }
        });

        sclutch.setItems("Wet Clutch", "Dry Clutch");
        sclutch.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                st_clutch=item;
            }
        });
        // test.setText(agentAddBikes.globalState_bikes.gb_name);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateInputs())
                {
                    fillsuperclass();

                    FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.addbike_frame, new Fragment_AddBikeTyrBrk());
                    fragmentTransaction.commit();
                }

            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fillsuperclass();

                FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.addbike_frame, new Fragment_AddBikeDimen());
                fragmentTransaction.commit();
            }
        });
        return myview;
    }

    private void fillsuperclass() {

        agentAddBikes.globalState_bikes.gbte_type=eetype.getText().toString();
        agentAddBikes.globalState_bikes.gbte_gearno=egear.getText().toString();
        agentAddBikes.globalState_bikes.gbte_drive=edesc.getText().toString();
        agentAddBikes.globalState_bikes.gbte_disp=edisp.getText().toString();
        agentAddBikes.globalState_bikes.gbte_cylno=ecylno.getText().toString();
        agentAddBikes.globalState_bikes.gbte_maxpow=emaxpow.getText().toString();
        agentAddBikes.globalState_bikes.gbte_maxtorque=emaxtorq.getText().toString();
        agentAddBikes.globalState_bikes.gbte_maxspeed=etopspeed.getText().toString();
        agentAddBikes.globalState_bikes.gbte_acceleration=eaccelaration.getText().toString();
        agentAddBikes.globalState_bikes.gbte_odetails=eodetails.getText().toString();
        agentAddBikes.globalState_bikes.gbte_drive=st_drivetype;
        agentAddBikes.globalState_bikes.gbte_clutchtype=st_clutch;
        agentAddBikes.globalState_bikes.gbte_coolsys=st_cool;
        agentAddBikes.globalState_bikes.gbte_startm=st_start;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }
    private boolean validateInputs() {
        if("".equals(eetype.getText().toString())){
            eetype.setError("Field cannot be empty");
            eetype.requestFocus();
            return false;
        }

        if("".equals(egear.getText().toString())){
            egear.setError("Field cannot be empty");
            egear.requestFocus();
            return false;
        }
        if("".equals(edesc.getText().toString())){
            edesc.setError("Field cannot be empty");
            edesc.requestFocus();
            return false;
        }
        if("".equals(edisp.getText().toString())){
            edisp.setError("Field cannot be empty");
            edisp.requestFocus();
            return false;
        }
        if("".equals(ecylno.getText().toString())){
            ecylno.setError("Field cannot be empty");
            ecylno.requestFocus();
            return false;
        }
        if("".equals(emaxpow.getText().toString())){
            emaxpow.setError("Field cannot be empty");
            emaxpow.requestFocus();
            return false;
        }
        if("".equals(emaxtorq.getText().toString())){
            emaxtorq.setError("Field cannot be empty");
            emaxtorq.requestFocus();
            return false;
        }
        if("".equals(etopspeed.getText().toString())){
            etopspeed.setError("Field cannot be empty");
            etopspeed.requestFocus();
            return false;
        }
        if("".equals(eaccelaration.getText().toString())){
            eaccelaration.setError("Field cannot be empty");
            eaccelaration.requestFocus();
            return false;
        }
        if("".equals(eodetails.getText().toString())){
            eodetails.setError("Field cannot be empty");
            eodetails.requestFocus();
            return false;
        }
        return true;
    }



}
