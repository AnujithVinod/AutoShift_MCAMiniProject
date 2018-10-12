package com.miniprosg.andgeeks.autoshift;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Fragment_AddCarMain extends Fragment {
    View myview;
    EditText ename,esercost,emanf,ewarranty,ewarkm,efreeservice,ecolour,epexshow,eponroad,eofeatures;
    TextView treldate,tbrand;
    int mYear, mMonth, mDay;
//    String  streldate,sename,sebrand,sesercost,semanf,sewarranty,sewarkm,sefreeservice,secolour,sepexshow,seponroad,seofeatures;
    Button btnback,btnnext;


    public AgentAddCars agentAddCars;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.fragment_add_carmain,container,false);

        agentAddCars = (AgentAddCars) getActivity();

        btnback = (Button) myview.findViewById(R.id.back);
        btnnext = (Button) myview.findViewById(R.id.next);


        tbrand=(TextView)myview.findViewById(R.id.cbrand);

        tbrand.setText(agentAddCars.loggedbrand);

        ename=(EditText)myview.findViewById(R.id.cname);
        esercost=(EditText)myview.findViewById(R.id.csercost);
        emanf=(EditText)myview.findViewById(R.id.cmanf);
        ewarranty=(EditText)myview.findViewById(R.id.cwarper);
        ewarkm=(EditText)myview.findViewById(R.id.cwarkm);
        efreeservice=(EditText)myview.findViewById(R.id.cfreeservice);
        ecolour=(EditText)myview.findViewById(R.id.ccolour);
        epexshow=(EditText)myview.findViewById(R.id.cpexshow);
        eponroad=(EditText)myview.findViewById(R.id.cponroad);
        eofeatures=(EditText)myview.findViewById(R.id.cofeature);

        treldate=(TextView) myview.findViewById(R.id.creldate);

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
        ename.setText(agentAddCars.globalState_cars.gc_name);

        esercost.setText(agentAddCars.globalState_cars.gc_sercost);
        emanf.setText(agentAddCars.globalState_cars.gc_manf);
        ewarranty.setText(agentAddCars.globalState_cars.gc_warranty);
        ewarkm.setText(agentAddCars.globalState_cars.gc_warkm);
        efreeservice.setText(agentAddCars.globalState_cars.gc_freeservice);
        ecolour.setText(agentAddCars.globalState_cars.gc_colour);
        epexshow.setText(agentAddCars.globalState_cars.gc_pexshow);
        eponroad.setText(agentAddCars.globalState_cars.gc_ponroad);
        eofeatures.setText(agentAddCars.globalState_cars.gc_ofeatures);

        treldate.setText(agentAddCars.globalState_cars.gc_reldate);

            // test.setText(agentAddCars.globalState_cars.gc_name);
            btnnext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if(validateInputs())
                    {
                        fillsuperclass();

                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.addcar_frame, new Fragment_AddCarDimen());
                        fragmentTransaction.commit();
                    }

                    //agentAddCars.globalState_cars.gc_name=test.getText().toString();
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


                    //agentAddCars.globalState_cars.gc_name=test.getText().toString();
                }
            });


        return myview;
    }

    private void fillsuperclass() {

        agentAddCars.globalState_cars.gc_name=ename.getText().toString();
        agentAddCars.globalState_cars.gc_sercost=esercost.getText().toString();
        agentAddCars.globalState_cars.gc_manf=emanf.getText().toString();
        agentAddCars.globalState_cars.gc_warranty=ewarranty.getText().toString();
        agentAddCars.globalState_cars.gc_warkm=ewarkm.getText().toString();
        agentAddCars.globalState_cars.gc_freeservice=efreeservice.getText().toString();
        agentAddCars.globalState_cars.gc_colour=ecolour.getText().toString();
        agentAddCars.globalState_cars.gc_pexshow=epexshow.getText().toString();
        agentAddCars.globalState_cars.gc_ponroad=eponroad.getText().toString();
        agentAddCars.globalState_cars.gc_ofeatures=eofeatures.getText().toString();
        agentAddCars.globalState_cars.gc_reldate=treldate.getText().toString();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //
                ScrollView scrollView = (ScrollView)view.findViewById(R.id.frameadd_cardimen);
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
