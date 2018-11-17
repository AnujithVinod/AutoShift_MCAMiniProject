package com.miniprosg.andgeeks.autoshift;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.miniprosg.andgeeks.autoshift.helper.GlobalState_Cars;

public class AgentAddCars extends AppCompatActivity {
    public GlobalState_Cars globalState_cars;
    public SharaedPrefernceConfig config;
    String[] logged_showroomdata;
    String loggedbrand;
    int loggeduid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        globalState_cars=new GlobalState_Cars();
        globalState_cars.ResetValues();
        config=new SharaedPrefernceConfig(getApplicationContext());
        logged_showroomdata=config.readLoggedShowroom();
        loggeduid=Integer.parseInt(logged_showroomdata[0]);
        loggedbrand=logged_showroomdata[5];
        final FragmentManager fragmentManager= getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.addcar_frame,new Fragment_AddCarMain()).commit();
        setContentView(R.layout.activity_agent_add_cars);

    }
    public void onBackPressed() {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(AgentAddCars.this);
        builder1.setMessage("Are you sure to Exit to Previous Menu? Any unsaved changes will be lost!");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AgentAddCars.super.onBackPressed();
                        //ARE YOU LOGGED IN ALREADY? USING SHARED PREFERENCES

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

    }

}
