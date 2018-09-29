package com.miniprosg.andgeeks.autoshift;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class RedirectReg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redirect_reg);


    }

    public void NewUser(View view) {

        Intent i= new Intent(getApplicationContext(),UserRegister.class);
        startActivity(i);
    }

    public void NewAgent(View view) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(RedirectReg.this);
        builder1.setMessage("What is Your Showroom?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Car Showroom",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Intent i= new Intent(getApplicationContext(),AgentRegister.class);
                        i.putExtra("car_bike", "CAR");
                        startActivity(i);

                    }
                });

        builder1.setNegativeButton(
                "Bike Showroom",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Intent i= new Intent(getApplicationContext(),AgentRegister.class);
                        i.putExtra("car_bike", "BIKE");
                        startActivity(i);

                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }
}
