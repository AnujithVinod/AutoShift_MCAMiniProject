package com.miniprosg.andgeeks.autoshift;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class StartUp extends AppCompatActivity {
    private static int SPLASH=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final SharaedPrefernceConfig config;
        config=new SharaedPrefernceConfig(getApplicationContext());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(config.readOnBoardingStatus()) {
                    Intent i = new Intent(StartUp.this, MainActivity.class);
                    //Intent i = new Intent(StartUp.this, ActivityOnBoarding.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Intent i = new Intent(StartUp.this, ActivityOnBoarding.class);
                    startActivity(i);
                    finish();
                }
            }
        }, SPLASH);
    }
}
