package com.miniprosg.andgeeks.autoshift;

import android.app.Dialog;
import android.content.Intent;
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


    }
}
