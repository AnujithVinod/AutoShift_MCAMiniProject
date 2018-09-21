package com.miniprosg.andgeeks.autoshift;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ProfileActivity extends AppCompatActivity {
    SharaedPrefernceConfig config;
    TextView uname,uemail,udob,uphone,ulocation;
    Button adsagent;
    String adsagentString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        config=new SharaedPrefernceConfig(getApplicationContext());
        String[] logged_userdata=config.readLoggedUser();
        uname=findViewById(R.id.tvuname);
        uemail=findViewById(R.id.tvuemail);
        udob=findViewById(R.id.tvudob);
        ulocation=findViewById(R.id.tvulocation);
        uphone=findViewById(R.id.tvuphone);
        uname.setText(logged_userdata[1]);
        uemail.setText(logged_userdata[2]);
        uphone.setText(logged_userdata[3]);
        udob.setText(logged_userdata[5]);
        ulocation.setText(logged_userdata[6]);
        adsagentString=logged_userdata[8];
        //Toast.makeText(getApplicationContext(), adsagentString, Toast.LENGTH_SHORT).show();
        adsagent=(Button) findViewById(R.id.myads);
        if(adsagentString.equals("agent"))
        {
            adsagent.setText("Explore");
        }
        else if (adsagentString.equals("admin"))
        {
            adsagent.setVisibility(View.INVISIBLE);
        }
        else
        {
            adsagent.setText("My Ads");
        }
        Toast.makeText(getApplicationContext(), "Welcome "+uname.getText().toString(), Toast.LENGTH_SHORT).show();



    }

    public void logout(View view) {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(ProfileActivity.this);
        builder1.setMessage("Are you sure to logout? Any unsaved changes will be lost!");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        //ARE YOU LOGGED IN ALREADY? USING SHARED PREFERENCES


                        config.writeLoginStatus(false);
                        config.writeLoggedUser(null,null,null,null,null,null,null,null,null,null);
                        Toast.makeText(getApplicationContext(),"You have been logged out",Toast.LENGTH_SHORT).show();
                        finish();
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



    }

    public void myads(View view) {

        if(adsagent.getText().toString().equals("Explore"))
        {
            Toast.makeText(getApplicationContext(), "Agent", Toast.LENGTH_SHORT).show();

        }

        else if(adsagent.getText().toString().equals("My Ads"))
        {
            Toast.makeText(getApplicationContext(), "User", Toast.LENGTH_SHORT).show();
        }


    }

    public void skip(View view) {

        finish();
    }
}
