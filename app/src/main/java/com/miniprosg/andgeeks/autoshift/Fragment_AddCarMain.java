package com.miniprosg.andgeeks.autoshift;

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
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

public class Fragment_AddCarMain extends Fragment {
    View myview;
  //  EditText test;
    Button btnback,btnnext;


    public AgentAddCars agentAddCars;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.fragment_add_carmain,container,false);

            agentAddCars = (AgentAddCars) getActivity();
            btnback = (Button) myview.findViewById(R.id.back);
            btnnext = (Button) myview.findViewById(R.id.next);

            // test.setText(agentAddCars.globalState_cars.gc_name);
            btnnext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.addcar_frame, new Fragment_AddCarDimen());
                    fragmentTransaction.commit();

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
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
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
