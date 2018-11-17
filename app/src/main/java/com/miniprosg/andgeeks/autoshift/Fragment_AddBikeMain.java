package com.miniprosg.andgeeks.autoshift;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import static android.content.Context.INPUT_METHOD_SERVICE;
/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_AddBikeMain extends Fragment {

    View myview;
    EditText ename,esercost,emanf,ewarranty,ewarkm,efreeservice,ecolour,epexshow,eponroad,eofeatures;
    TextView treldate,tbrand;
    int mYear, mMonth, mDay;
//  String  streldate,sename,sebrand,sesercost,semanf,sewarranty,sewarkm,sefreeservice,secolour,sepexshow,seponroad,seofeatures;
    Button btnback,btnnext;


    public AgentAddBikes agentAddBikes;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.fragment_add_bikemain,container,false);

        agentAddBikes = (AgentAddBikes) getActivity();

        btnback = (Button) myview.findViewById(R.id.back);
        btnnext = (Button) myview.findViewById(R.id.next);
        getActivity().setTitle("Bike OverView");

        tbrand=(TextView)myview.findViewById(R.id.bbrand);

        tbrand.setText(agentAddBikes.loggedbrand);

        ename=(EditText)myview.findViewById(R.id.bname);
        esercost=(EditText)myview.findViewById(R.id.bsercost);
        emanf=(EditText)myview.findViewById(R.id.bmanf);
        ewarranty=(EditText)myview.findViewById(R.id.bwarper);
        ewarkm=(EditText)myview.findViewById(R.id.bwarkm);
        efreeservice=(EditText)myview.findViewById(R.id.bfreeservice);
        ecolour=(EditText)myview.findViewById(R.id.bcolour);
        epexshow=(EditText)myview.findViewById(R.id.bpexshow);
        eponroad=(EditText)myview.findViewById(R.id.bponroad);
        eofeatures=(EditText)myview.findViewById(R.id.bofeature);

        treldate=(TextView) myview.findViewById(R.id.breldate);

        treldate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                treldate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });
        ename.setText(agentAddBikes.globalState_bikes.gb_name);
        esercost.setText(agentAddBikes.globalState_bikes.gb_sercost);
        emanf.setText(agentAddBikes.globalState_bikes.gb_manf);
        ewarranty.setText(agentAddBikes.globalState_bikes.gb_warper);
        ewarkm.setText(agentAddBikes.globalState_bikes.gb_warkm);
        efreeservice.setText(agentAddBikes.globalState_bikes.gb_freeservice);
        ecolour.setText(agentAddBikes.globalState_bikes.gb_color);
        epexshow.setText(agentAddBikes.globalState_bikes.gb_pexshow);
        eponroad.setText(agentAddBikes.globalState_bikes.gb_ponroad);
        eofeatures.setText(agentAddBikes.globalState_bikes.gb_odetails);
        treldate.setText(agentAddBikes.globalState_bikes.gb_reldate);

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(validateInputs())
                {
                    fillsuperclass();

                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.addbike_frame, new Fragment_AddBikeDimen());
                    fragmentTransaction.commit();
                }

            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                builder1.setMessage("Are you sure to Exit to Previous Menu? Any unsaved changes will be lost!");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                getActivity().finish();
                                dialog.cancel();

                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();


                //agentAddBikes.globalState_bikes.gb_name=test.getText().toString();
            }
        });


        return myview;
    }

    private void fillsuperclass() {

        agentAddBikes.globalState_bikes.gb_name=ename.getText().toString();
        agentAddBikes.globalState_bikes.gb_sercost=esercost.getText().toString();
        agentAddBikes.globalState_bikes.gb_manf=emanf.getText().toString();
        agentAddBikes.globalState_bikes.gb_warper=ewarranty.getText().toString();
        agentAddBikes.globalState_bikes.gb_warkm=ewarkm.getText().toString();
        agentAddBikes.globalState_bikes.gb_freeservice=efreeservice.getText().toString();
        agentAddBikes.globalState_bikes.gb_color=ecolour.getText().toString();
        agentAddBikes.globalState_bikes.gb_pexshow=epexshow.getText().toString();
        agentAddBikes.globalState_bikes.gb_ponroad=eponroad.getText().toString();
        agentAddBikes.globalState_bikes.gb_odetails=eofeatures.getText().toString();
        agentAddBikes.globalState_bikes.gb_reldate=treldate.getText().toString();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        super.onViewCreated(view, savedInstanceState);
        //
        ScrollView scrollView = (ScrollView)view.findViewById(R.id.frameadd_bikemain);
        Snackbar snackbar = Snackbar.make(scrollView, "Note: Please Click Next Once Finished.\nExiting will Discard any Changes", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private boolean validateInputs() {
        if("".equals(ename.getText().toString())){
            ename.setError("Field cannot be empty");
            ename.requestFocus();
            return false;
        }

        if("".equals(tbrand.getText().toString())){
            tbrand.setError("Field cannot be empty");
            tbrand.requestFocus();
            return false;
        }
        if("".equals(treldate.getText().toString())){
            treldate.setError("Field cannot be empty");
            treldate.requestFocus();
            return false;
        }
        if("".equals(esercost.getText().toString())){
            esercost.setError("Field cannot be empty");
            esercost.requestFocus();
            return false;
        }
        if("".equals(emanf.getText().toString())){
            emanf.setError("Field cannot be empty");
            emanf.requestFocus();
            return false;
        }
        if("".equals(ewarranty.getText().toString())){
            ewarranty.setError("Field cannot be empty");
            ewarranty.requestFocus();
            return false;
        }
        if("".equals(ewarkm.getText().toString())){
            ewarkm.setError("Field cannot be empty");
            ewarkm.requestFocus();
            return false;
        }
        if("".equals(efreeservice.getText().toString())){
            efreeservice.setError("Field cannot be empty");
            efreeservice.requestFocus();
            return false;
        }
        if("".equals(ecolour.getText().toString())){
            ecolour.setError("Field cannot be empty");
            ecolour.requestFocus();
            return false;
        }
        if("".equals(epexshow.getText().toString())){
            epexshow.setError("Field cannot be empty");
            epexshow.requestFocus();
            return false;
        }
        if("".equals(eponroad.getText().toString())){
            eponroad.setError("Field cannot be empty");
            eponroad.requestFocus();
            return false;
        }
        if("".equals(eofeatures.getText().toString())){
            eofeatures.setError("Field cannot be empty");
            eofeatures.requestFocus();
            return false;
        }

        return true;
    }

}
