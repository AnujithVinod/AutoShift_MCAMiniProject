package com.miniprosg.andgeeks.autoshift;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;

public class Fragment_AddCarDimen extends Fragment {

    View myview;
    EditText elength,ewidth,eheight,egclear,ewbase,ekweight,egweight,edoorno,evolume,eseatcap,eodetails;
    MaterialSpinner sbody;
    Button btnback,btnnext;
    String  st_body="Sedan";


    public AgentAddCars agentAddCars;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.fragment_add_cardimen,container,false);


        agentAddCars =(AgentAddCars) getActivity();
        btnback=(Button)myview.findViewById(R.id.back);
        btnnext=(Button)myview.findViewById(R.id.next);

        elength=(EditText)myview.findViewById(R.id.clength);
        ewidth=(EditText)myview.findViewById(R.id.cwidth);
        eheight=(EditText)myview.findViewById(R.id.cheight);
        egclear=(EditText)myview.findViewById(R.id.cgclear);
        ewbase=(EditText)myview.findViewById(R.id.cwbase);
        ekweight=(EditText)myview.findViewById(R.id.ckweight);
        egweight=(EditText)myview.findViewById(R.id.cgweight);
        edoorno=(EditText)myview.findViewById(R.id.cdoor);
        evolume=(EditText)myview.findViewById(R.id.cvolume);
        eseatcap=(EditText)myview.findViewById(R.id.cseatcap);
        eodetails=(EditText)myview.findViewById(R.id.codetails);
//        eofeatures=(EditText)myview.findViewById(R.id.cofeature);
//
//        treldate=(TextView) myview.findViewById(R.id.creldate);

        elength.setText(agentAddCars.globalState_cars.gcd_length);
        ewidth.setText(agentAddCars.globalState_cars.gcd_width);
        eheight.setText(agentAddCars.globalState_cars.gcd_height);
        egclear.setText(agentAddCars.globalState_cars.gcd_gclear);
        ewbase.setText(agentAddCars.globalState_cars.gcd_wbase);
        ekweight.setText(agentAddCars.globalState_cars.gcd_kweight);
        egweight.setText(agentAddCars.globalState_cars.gcd_gweight);
        edoorno.setText(agentAddCars.globalState_cars.gcd_door);
        evolume.setText(agentAddCars.globalState_cars.gcd_volume);
        eseatcap.setText(agentAddCars.globalState_cars.gcd_seatcap);
        eodetails.setText(agentAddCars.globalState_cars.gcd_odetails);

//        eofeatures.setText(agentAddCars.globalState_cars.gc_ofeatures);
//
//        treldate.setText(agentAddCars.globalState_cars.gc_reldate);


        // test.setText(agentAddCars.globalState_cars.gc_name);

        sbody = (MaterialSpinner) myview.findViewById(R.id.spinn_bt);

        sbody.setItems("Sedan","Compact Sedan", "Coupe", "Hatchback"
                     , "Minivan", "Van", "Convertible"
                     , "SUV/MUV", "Truck", "Station Wagon");
        sbody.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                st_body=item;
            }
        });

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validateInputs())
                {
                    fillsuperclass();
                    FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.addcar_frame, new Fragment_AddCarTransEng());
                    fragmentTransaction.commit();
                }



                //agentAddCars.globalState_cars.gc_name=test.getText().toString();
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    fillsuperclass();
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.addcar_frame, new Fragment_AddCarMain());
                    fragmentTransaction.commit();
            }
        });

        return myview;
    }
    private void fillsuperclass() {

        agentAddCars.globalState_cars.gcd_length=elength.getText().toString();
        agentAddCars.globalState_cars.gcd_width=ewidth.getText().toString();
        agentAddCars.globalState_cars.gcd_height=eheight.getText().toString();
        agentAddCars.globalState_cars.gcd_gclear=egclear.getText().toString();
        agentAddCars.globalState_cars.gcd_wbase=ewbase.getText().toString();
        agentAddCars.globalState_cars.gcd_kweight=ekweight.getText().toString();
        agentAddCars.globalState_cars.gcd_gweight=egweight.getText().toString();
        agentAddCars.globalState_cars.gcd_door=edoorno.getText().toString();
        agentAddCars.globalState_cars.gcd_volume=evolume.getText().toString();
        agentAddCars.globalState_cars.gcd_seatcap=eseatcap.getText().toString();
        agentAddCars.globalState_cars.gcd_odetails=eodetails.getText().toString();
        agentAddCars.globalState_cars.gcd_bodytype=st_body;
    }


    private boolean validateInputs() {
        if("".equals(elength.getText().toString())){
            elength.setError("Field cannot be empty");
            elength.requestFocus();
            return false;
        }

        if("".equals(ewidth.getText().toString())){
            ewidth.setError("Field cannot be empty");
            ewidth.requestFocus();
            return false;
        }
        if("".equals(eheight.getText().toString())){
            eheight.setError("Field cannot be empty");
            eheight.requestFocus();
            return false;
        }
        if("".equals(egclear.getText().toString())){
            egclear.setError("Field cannot be empty");
            egclear.requestFocus();
            return false;
        }
        if("".equals(ewbase.getText().toString())){
            ewbase.setError("Field cannot be empty");
            ewbase.requestFocus();
            return false;
        }
        if("".equals(ekweight.getText().toString())){
            ekweight.setError("Field cannot be empty");
            ekweight.requestFocus();
            return false;
        }
        if("".equals(egweight.getText().toString())){
            egweight.setError("Field cannot be empty");
            egweight.requestFocus();
            return false;
        }
        if("".equals(edoorno.getText().toString())){
            edoorno.setError("Field cannot be empty");
            edoorno.requestFocus();
            return false;
        }
        if("".equals(eseatcap.getText().toString())){
            eseatcap.setError("Field cannot be empty");
            eseatcap.requestFocus();
            return false;
        }
        if("".equals(evolume.getText().toString())){
            evolume.setError("Field cannot be empty");
            evolume.requestFocus();
            return false;
        }
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
