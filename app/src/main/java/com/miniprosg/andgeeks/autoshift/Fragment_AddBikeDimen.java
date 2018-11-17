package com.miniprosg.andgeeks.autoshift;


import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import com.jaredrummler.materialspinner.MaterialSpinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_AddBikeDimen extends Fragment {


    View myview;
    EditText elength,ewidth,eheight,esadheight,ewbase,ekweight,esvolume,eseatcap,eodetails;
    MaterialSpinner sride;
    Button btnback,btnnext;
    String  st_ride="Street";


    public AgentAddBikes agentAddBikes;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.fragment_add_bikedimen,container,false);


        agentAddBikes =(AgentAddBikes) getActivity();
        btnback=(Button)myview.findViewById(R.id.back);
        btnnext=(Button)myview.findViewById(R.id.next);

        elength=(EditText)myview.findViewById(R.id.blength);
        ewidth=(EditText)myview.findViewById(R.id.bwidth);
        eheight=(EditText)myview.findViewById(R.id.bheight);
        esadheight=(EditText)myview.findViewById(R.id.bsadheight);
        ewbase=(EditText)myview.findViewById(R.id.bwbase);
        ekweight=(EditText)myview.findViewById(R.id.bkweight);
        esvolume=(EditText)myview.findViewById(R.id.bsvolume);
        eseatcap=(EditText)myview.findViewById(R.id.bseatcap);
        eodetails=(EditText)myview.findViewById(R.id.bodetails);
//        eofeatures=(EditText)myview.findViewById(R.id.bofeature);
//
//        treldate=(TextView) myview.findViewById(R.id.breldate);
        getActivity().setTitle("Dimension Details");
        elength.setText(agentAddBikes.globalState_bikes.gbd_length);
        ewidth.setText(agentAddBikes.globalState_bikes.gbd_width);
        eheight.setText(agentAddBikes.globalState_bikes.gbd_height);
        esadheight.setText(agentAddBikes.globalState_bikes.gbd_sadheight);
        ewbase.setText(agentAddBikes.globalState_bikes.gbd_wbase);
        ekweight.setText(agentAddBikes.globalState_bikes.gbd_kweight);
        esvolume.setText(agentAddBikes.globalState_bikes.gbd_svolume);
        eseatcap.setText(agentAddBikes.globalState_bikes.gbd_seatcap);
        eodetails.setText(agentAddBikes.globalState_bikes.gbd_odetails);

//        eofeatures.setText(agentAddBikes.globalState_bikes.gc_ofeatures);
//
//        treldate.setText(agentAddBikes.globalState_bikes.gc_reldate);


        // test.setText(agentAddBikes.globalState_bikes.gc_name);

        sride = (MaterialSpinner) myview.findViewById(R.id.spinn_bt);

        sride.setItems("Street","Cruiser", "Scooter", "Sport");
        sride.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                st_ride=item;
            }
        });

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validateInputs())
                {
                    fillsuperclass();
                    FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.addbike_frame, new Fragment_AddBikeTransEng());
                    fragmentTransaction.commit();
                }



                //agentAddBikes.globalState_bikes.gc_name=test.getText().toString();
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fillsuperclass();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.addbike_frame, new Fragment_AddBikeMain());
                fragmentTransaction.commit();
            }
        });

        return myview;
    }
    private void fillsuperclass() {

        agentAddBikes.globalState_bikes.gbd_length=elength.getText().toString();
        agentAddBikes.globalState_bikes.gbd_width=ewidth.getText().toString();
        agentAddBikes.globalState_bikes.gbd_height=eheight.getText().toString();
        agentAddBikes.globalState_bikes.gbd_sadheight=esadheight.getText().toString();
        agentAddBikes.globalState_bikes.gbd_wbase=ewbase.getText().toString();
        agentAddBikes.globalState_bikes.gbd_kweight=ekweight.getText().toString();
        agentAddBikes.globalState_bikes.gbd_svolume=esvolume.getText().toString();
        agentAddBikes.globalState_bikes.gbd_seatcap=eseatcap.getText().toString();
        agentAddBikes.globalState_bikes.gbd_odetails=eodetails.getText().toString();
        agentAddBikes.globalState_bikes.gbd_ridetype=st_ride;
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
        if("".equals(esadheight.getText().toString())){
            esadheight.setError("Field cannot be empty");
            esadheight.requestFocus();
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
        if("".equals(eseatcap.getText().toString())){
            eseatcap.setError("Field cannot be empty");
            eseatcap.requestFocus();
            return false;
        }
        if("".equals(esvolume.getText().toString())){
            esvolume.setError("Field cannot be empty");
            esvolume.requestFocus();
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



}
