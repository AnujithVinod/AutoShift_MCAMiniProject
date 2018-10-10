package com.miniprosg.andgeeks.autoshift;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.miniprosg.andgeeks.autoshift.helper.GlobalState_Cars;

public class AgentAddCars extends AppCompatActivity {
    public GlobalState_Cars globalState_cars;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globalState_cars=new GlobalState_Cars();
        globalState_cars.ResetValues();
        final FragmentManager fragmentManager= getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.addcar_frame,new Fragment_AddCarMain()).commit();
        setContentView(R.layout.activity_agent_add_cars);



    }

}
