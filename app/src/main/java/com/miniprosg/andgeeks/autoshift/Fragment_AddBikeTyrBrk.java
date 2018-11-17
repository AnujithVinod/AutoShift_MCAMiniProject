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

public class Fragment_AddBikeTyrBrk extends Fragment {


    View myview;
    EditText efbtype,erbtype,ettype,ewsize,etrad,eodetails;
    Button btnback,btnnext;


    public AgentAddBikes agentAddBikes;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.fragment_add_biketyrbrk,container,false);


        agentAddBikes =(AgentAddBikes) getActivity();
        btnback=(Button)myview.findViewById(R.id.back);
        btnnext=(Button)myview.findViewById(R.id.next);

        efbtype=(EditText)myview.findViewById(R.id.bbfbtype);
        erbtype=(EditText)myview.findViewById(R.id.bbrbtype);
        ettype=(EditText)myview.findViewById(R.id.bbtyrtype);
        etrad=(EditText)myview.findViewById(R.id.bbtrad);
        ewsize=(EditText)myview.findViewById(R.id.bbwlsize);
        eodetails=(EditText)myview.findViewById(R.id.bbodetails);

        efbtype.setText(agentAddBikes.globalState_bikes.gbtb_fbtype);
        erbtype.setText(agentAddBikes.globalState_bikes.gbtb_rbtype);
        etrad.setText(agentAddBikes.globalState_bikes.gbtb_tyrrad);
        ewsize.setText(agentAddBikes.globalState_bikes.gbtb_wlsize);
        ettype.setText(agentAddBikes.globalState_bikes.gbtb_trtype);
        eodetails.setText(agentAddBikes.globalState_bikes.gbtb_odetails);
        getActivity().setTitle("Brake and Tyre Details");
        // test.setText(agentAddBikes.globalState_bikes.gb_name);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validateInputs())
                {
                    fillsuperclass();

                    FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.addbike_frame, new Fragment_AddBikeSafteyElec());
                    fragmentTransaction.commit();
                }

            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fillsuperclass();

                FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.addbike_frame, new Fragment_AddBikeTransEng());
                fragmentTransaction.commit();
            }
        });

        return myview;
    }


    private boolean validateInputs() {
        if("".equals(efbtype.getText().toString())){
            efbtype.setError("Field cannot be empty");
            efbtype.requestFocus();
            return false;
        }

        if("".equals(erbtype.getText().toString())){
            erbtype.setError("Field cannot be empty");
            erbtype.requestFocus();
            return false;
        }
        if("".equals(ewsize.getText().toString())){
            ewsize.setError("Field cannot be empty");
            ewsize.requestFocus();
            return false;
        }
        if("".equals(ettype.getText().toString())){
            ettype.setError("Field cannot be empty");
            ettype.requestFocus();
            return false;
        }
        if("".equals(etrad.getText().toString())){
            etrad.setError("Field cannot be empty");
            etrad.requestFocus();
            return false;
        }
        if("".equals(eodetails.getText().toString())){
            eodetails.setError("Field cannot be empty");
            eodetails.requestFocus();
            return false;
        }

        return true;
    }
    private void fillsuperclass() {

        agentAddBikes.globalState_bikes.gbtb_fbtype=efbtype.getText().toString();
        agentAddBikes.globalState_bikes.gbtb_rbtype=erbtype.getText().toString();
        agentAddBikes.globalState_bikes.gbtb_tyrrad=etrad.getText().toString();
        agentAddBikes.globalState_bikes.gbtb_wlsize=ewsize.getText().toString();
        agentAddBikes.globalState_bikes.gbtb_trtype=ettype.getText().toString();
        agentAddBikes.globalState_bikes.gbtb_odetails=eodetails.getText().toString();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        super.onViewCreated(view, savedInstanceState);

    }

}
