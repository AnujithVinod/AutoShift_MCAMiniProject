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

public class Fragment_AddCarTransEng extends Fragment {

    View myview;
    EditText eetype,egear,edesc,edisp,ecylno,emaxpow,emaxtorq,etopspeed,eaccelaration,eodetails;
    Button btnback,btnnext;
    MaterialSpinner sdrivetype;
    String st_drivetype="Manual";


    public AgentAddCars agentAddCars;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.fragment_add_cartranseng,container,false);


        agentAddCars =(AgentAddCars) getActivity();
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
        eetype=(EditText)myview.findViewById(R.id.ctype);
        egear=(EditText)myview.findViewById(R.id.cgear);
        edesc=(EditText)myview.findViewById(R.id.cdesc);
        edisp=(EditText)myview.findViewById(R.id.cdisp);
        ecylno=(EditText)myview.findViewById(R.id.ccylno);
        emaxpow=(EditText)myview.findViewById(R.id.cmaxpow);
        emaxtorq=(EditText)myview.findViewById(R.id.cmaxtorq);
        etopspeed=(EditText)myview.findViewById(R.id.ctopspeed);
        eaccelaration=(EditText)myview.findViewById(R.id.cacceration);
        eodetails=(EditText)myview.findViewById(R.id.codetails);

        eetype.setText(agentAddCars.globalState_cars.gcte_type);
        egear.setText(agentAddCars.globalState_cars.gcte_gear);
        edesc.setText(agentAddCars.globalState_cars.gcte_desc);
        edisp.setText(agentAddCars.globalState_cars.gcte_disp);
        ecylno.setText(agentAddCars.globalState_cars.gcte_cyl_no);
        emaxpow.setText(agentAddCars.globalState_cars.gcte_max_pow);
        emaxtorq.setText(agentAddCars.globalState_cars.gcte_max_torq);
        etopspeed.setText(agentAddCars.globalState_cars.gcte_topspeed);
        eaccelaration.setText(agentAddCars.globalState_cars.gcte_acceleration);
        eodetails.setText(agentAddCars.globalState_cars.gcte_odetails);

        sdrivetype = (MaterialSpinner) myview.findViewById(R.id.spinn_drivetype);

        sdrivetype.setItems("Manual", "Automatic", "Hybrid");
        sdrivetype.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                st_drivetype=item;
            }
        });
        // test.setText(agentAddCars.globalState_cars.gc_name);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateInputs())
                {
                    fillsuperclass();

                    FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.addcar_frame, new Fragment_AddCarBrkStr());
                    fragmentTransaction.commit();
                }

            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fillsuperclass();

                FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.addcar_frame, new Fragment_AddCarDimen());
                fragmentTransaction.commit();
            }
        });
        return myview;
    }

    private void fillsuperclass() {

        agentAddCars.globalState_cars.gcte_type=eetype.getText().toString();
        agentAddCars.globalState_cars.gcte_gear=egear.getText().toString();
        agentAddCars.globalState_cars.gcte_desc=edesc.getText().toString();
        agentAddCars.globalState_cars.gcte_disp=edisp.getText().toString();
        agentAddCars.globalState_cars.gcte_cyl_no=ecylno.getText().toString();
        agentAddCars.globalState_cars.gcte_max_pow=emaxpow.getText().toString();
        agentAddCars.globalState_cars.gcte_max_torq=emaxtorq.getText().toString();
        agentAddCars.globalState_cars.gcte_topspeed=etopspeed.getText().toString();
        agentAddCars.globalState_cars.gcte_acceleration=eaccelaration.getText().toString();
        agentAddCars.globalState_cars.gcte_odetails=eodetails.getText().toString();
        agentAddCars.globalState_cars.gcte_drive=st_drivetype;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //



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


    public void SnackbarShow(View view)
    {
        final ScrollView scrollView = (ScrollView)view.findViewById(R.id.sv_first);
        scrollView.findViewById(R.id.bankcardId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //globalState_cars.testdataCarname(test.getText().toString());
                Snackbar snackbar = Snackbar.make(scrollView, "Missing Mandatory Parameters", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
    }

}
