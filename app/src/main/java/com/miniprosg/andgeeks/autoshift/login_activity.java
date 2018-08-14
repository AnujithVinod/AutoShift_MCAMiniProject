package com.miniprosg.andgeeks.autoshift;


import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


public class login_activity extends AppCompatActivity{
LoginButton loginbutton;
TextView textview;
CallbackManager callbackmanager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login_activity);
        super.onCreate(savedInstanceState);
        findViewById(R.id.mainLayout).setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return false;
            }
        });
            FacebookSdk.sdkInitialize(getApplicationContext());
         AppEventsLogger.activateApp(this);
        loginbutton=(LoginButton)findViewById(R.id.login_button);
        //textview=(TextView)findViewById(R.id.login_status);
callbackmanager=CallbackManager.Factory.create();
        loginbutton.registerCallback(callbackmanager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code

               // textview.setText("Sucess \n"+ loginResult.getAccessToken().getUserId()+"\n"+loginResult.getAccessToken().getToken());
            }

            @Override
            public void onCancel() {
                // App code
              //  textview.setText("Cancelled");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
               // textview.setText("Failed");
            }
        });
        }

public void skip(View v)
{

    finish();
}

    public void settings(MenuItem item) {
        Intent i= new Intent(getApplicationContext(),SettingsActivity.class);
        startActivity(i);
    }

}

//To create An Action bar for this activity or new ativitesadd this in manifest file, activity tag(APP SPECIFIC)
//(Action bar only, back button not available)

//        android:theme="@style/Theme.AppCompat.Light.DarkActionBar"