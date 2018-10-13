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

public class Fragment_AddCarSaftey extends Fragment {

    View myview;
    EditText eodetails;
    MaterialSpinner salock,sbassist,sslocks,sclock,spsensor,stalarm,sdabag,spabag;
    String st_alock="No",st_bassist="No",st_slocks="No",st_clock="No",st_psensor="No",st_talarm="No",st_dabag="No",st_pabag="No";
    Button btnback,btnnext;


    public AgentAddCars agentAddCars;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {



        myview=inflater.inflate(R.layout.fragment_add_carsafety,container,false);


        agentAddCars =(AgentAddCars) getActivity();
        btnback=(Button)myview.findViewById(R.id.back);
        btnnext=(Button)myview.findViewById(R.id.next);
        eodetails=(EditText)myview.findViewById(R.id.csodetails);
        eodetails.setText(agentAddCars.globalState_cars.gcs_odetails);
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


        salock = (MaterialSpinner) myview.findViewById(R.id.spinn_al);
        sbassist = (MaterialSpinner) myview.findViewById(R.id.spinn_ba);
        sslocks = (MaterialSpinner) myview.findViewById(R.id.spinn_sl);
        sclock = (MaterialSpinner) myview.findViewById(R.id.spinn_cl);
        spsensor = (MaterialSpinner) myview.findViewById(R.id.spinn_ps);
        stalarm = (MaterialSpinner) myview.findViewById(R.id.spinn_ta);
        sdabag = (MaterialSpinner) myview.findViewById(R.id.spinn_da);
        spabag = (MaterialSpinner) myview.findViewById(R.id.spinn_pa);

        salock.setItems("No", "Yes");
        salock.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                st_alock=item;
            }
        });

        sbassist.setItems("No", "Yes");
        sbassist.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                st_bassist=item;
            }
        });

        sslocks.setItems("No", "Yes");
        sslocks.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                st_slocks=item;
            }
        });

        sclock.setItems("No", "Yes");
        sclock.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                st_clock=item;
            }
        });

        spsensor.setItems("No", "Yes");
        spsensor.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                st_psensor=item;
            }
        });

        stalarm.setItems("No", "Yes");
        stalarm.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                st_talarm=item;
            }
        });

        sdabag.setItems("No", "Yes");
        sdabag.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                st_dabag=item;
            }
        });

        spabag.setItems("No", "Yes");
        spabag.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                st_pabag=item;
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
                    fragmentTransaction.replace(R.id.addcar_frame, new Fragment_AddCarFuel());
                    fragmentTransaction.commit();

                }

            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fillsuperclass();


                FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.addcar_frame, new Fragment_AddCarBrkStr());
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

        agentAddCars.globalState_cars.gcs_odetails=eodetails.getText().toString();
        agentAddCars.globalState_cars.gcs_antilock=st_alock;
        agentAddCars.globalState_cars.gcs_alarm=st_talarm;
        agentAddCars.globalState_cars.gcs_bassist=st_bassist;
        agentAddCars.globalState_cars.gcs_clock=st_clock;
        agentAddCars.globalState_cars.gcs_dabag=st_dabag;
        agentAddCars.globalState_cars.gcs_pabag=st_pabag;
        agentAddCars.globalState_cars.gcs_psensor=st_psensor;
        agentAddCars.globalState_cars.gcs_slock=st_alock;

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
