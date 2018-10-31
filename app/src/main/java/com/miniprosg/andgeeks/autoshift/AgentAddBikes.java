package com.miniprosg.andgeeks.autoshift;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.miniprosg.andgeeks.autoshift.helper.GlobalState_Bikes;
import com.miniprosg.andgeeks.autoshift.helper.GlobalState_Cars;

public class AgentAddBikes extends AppCompatActivity {
    public GlobalState_Bikes globalState_bikes;
    public SharaedPrefernceConfig config;
    String[] logged_showroomdata;
    String loggedbrand;
    int loggeduid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        globalState_bikes=new GlobalState_Bikes();
        globalState_bikes.ResetValues();
        config=new SharaedPrefernceConfig(getApplicationContext());
        logged_showroomdata=config.readLoggedShowroom();
        loggeduid=Integer.parseInt(logged_showroomdata[0]);
        loggedbrand=logged_showroomdata[5];
        final FragmentManager fragmentManager= getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.addbike_frame,new Fragment_AddBikeMain()).commit();
        setContentView(R.layout.activity_agent_add_bikes);




    }

}
