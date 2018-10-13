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

public class Fragment_AddCarBrkStr extends Fragment {


    View myview;
    EditText efbtype,erbtype,estype,etrad,eodetails;
    Button btnback,btnnext;


    public AgentAddCars agentAddCars;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.fragment_add_carbrkstr,container,false);


        agentAddCars =(AgentAddCars) getActivity();
        btnback=(Button)myview.findViewById(R.id.back);
        btnnext=(Button)myview.findViewById(R.id.next);

        efbtype=(EditText)myview.findViewById(R.id.cbsfbtype);
        erbtype=(EditText)myview.findViewById(R.id.cbsrbtype);
        estype=(EditText)myview.findViewById(R.id.cbsstrtype);
        etrad=(EditText)myview.findViewById(R.id.cbstrad);
        eodetails=(EditText)myview.findViewById(R.id.cbsodetails);

        efbtype.setText(agentAddCars.globalState_cars.gcbs_fbtype);
        erbtype.setText(agentAddCars.globalState_cars.gcbs_rbtype);
        estype.setText(agentAddCars.globalState_cars.gcbs_strtype);
        etrad.setText(agentAddCars.globalState_cars.gcbs_trad);
        eodetails.setText(agentAddCars.globalState_cars.gcbs_odetails);

        // test.setText(agentAddCars.globalState_cars.gc_name);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validateInputs())
                {
                    fillsuperclass();

                    FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.addcar_frame, new Fragment_AddCarSaftey());
                    fragmentTransaction.commit();
                }


                //agentAddCars.globalState_cars.gc_name=test.getText().toString();
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fillsuperclass();

                FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.addcar_frame, new Fragment_AddCarTransEng());
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
        if("".equals(estype.getText().toString())){
            estype.setError("Field cannot be empty");
            estype.requestFocus();
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

        agentAddCars.globalState_cars.gcbs_fbtype=efbtype.getText().toString();
        agentAddCars.globalState_cars.gcbs_rbtype=erbtype.getText().toString();
        agentAddCars.globalState_cars.gcbs_strtype=estype.getText().toString();
        agentAddCars.globalState_cars.gcbs_trad=etrad.getText().toString();
        agentAddCars.globalState_cars.gcbs_odetails=eodetails.getText().toString();
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
