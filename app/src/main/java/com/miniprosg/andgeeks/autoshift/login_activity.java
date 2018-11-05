package com.miniprosg.andgeeks.autoshift;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;
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
import com.miniprosg.andgeeks.autoshift.helper.PredifValues;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class login_activity extends AppCompatActivity{
    private static final String KEY_STATUS = "status";
    private static final String KEY_MESSAGE = "message";
   // private static final String KEY_FULL_NAME = "full_name";
    private static final String KEY_USEREMAIL = "uemail";
    private static final String KEY_PASSWORD = "upass";
    private static final String KEY_EMPTY = "";
    private EditText etUsername;
    private EditText etPassword;
    private String useremail;
    private String password;
    private ProgressDialog pDialog;
    PredifValues predifValues=new PredifValues();
    String base_url=predifValues.returnipaddressurl();
    String userType;
    //private String login_url ="http://192.168.1.3/autoshift_db/login.php";
    private SessionHandler session;
    LoginButton loginbutton;
    TextView textview,textvinfo;

CallbackManager callbackmanager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login_activity);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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

        Bundle extras = getIntent().getExtras();
        userType = extras.getString("utype");

        //Toast.makeText(getApplicationContext(),userType,Toast.LENGTH_LONG).show();

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


        //LOGIN CODE BEGINS HERE
//         session = new SessionHandler(getApplicationContext());
//        if(session.isLoggedIn()){
//            loadDashboard();
//        }
        etUsername = findViewById(R.id.editemail);
        etPassword = findViewById(R.id.editpass);
        Button login = findViewById(R.id.btnlogin);
        textvinfo=(TextView)findViewById(R.id.tvinfo);
        textvinfo.setText((userType+" Login").toUpperCase());


        }

    public void OnLogin(View v) {
        //Retrieve the data entered in the edit texts
        useremail = etUsername.getText().toString();
        password = etPassword.getText().toString();
        if (validateInputs()) {
            login();
        }
    }

public void skip(View v)
{

    finish();
}

    public void settings(MenuItem item) {
        Intent i= new Intent(getApplicationContext(),SettingsActivity.class);
        startActivity(i);
    }

    public void redirect_reg(View view) {

        Intent i= new Intent(getApplicationContext(),RedirectReg.class);
        startActivity(i);
    }

    /**
     * Launch Dashboard Activity on Successful Login
     */
    private void loadDashboard() {
        Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
        i.putExtra("utype", userType);
        startActivity(i);
        finish();

    }

    /**
     * Display Progress bar while Logging in
     */

    private void displayLoader() {
        pDialog = new ProgressDialog(login_activity.this);
        pDialog.setMessage("Logging In.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }
    private void displayLoader1() {
        pDialog = new ProgressDialog(login_activity.this);
        pDialog.setMessage("Contacting Our Server....Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }

    private void login() {
        displayLoader();
        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters
            //Toast.makeText(getApplicationContext(),"HIHIHI",Toast.LENGTH_LONG).show();
            request.put(KEY_USEREMAIL, useremail);
            request.put(KEY_PASSWORD, password);
            request.put("utype",userType);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, base_url+"login.php", request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pDialog.dismiss();
                        try {
                            //Check if user got logged in successfully
                            if (response.getInt(KEY_STATUS) == 1) {
                                SharaedPrefernceConfig config;
                                config=new SharaedPrefernceConfig(getApplicationContext());
                                config.writeLoggedEmpty();
                                //ARE YOU LOGGED IN ALREADY? USING SHARED PREFERENCES
                                if(config.readStatus())
                                {
                                    config.writeLoginStatus(false);
                                    config.writeLoggedEmpty();
                                    //Toast.makeText(getApplicationContext(),"You have been logged out",Toast.LENGTH_SHORT).show();
                                }
                                if(response.getString("usertype").equals("user"))
                                {
                                    String uid=response.getString("uid");
                                    String uname=response.getString("uname");
                                    String uemail=response.getString("uemail");
                                    String upass=response.getString("upass");
                                    String uphone=response.getString("uphone");
                                    String ugender=response.getString("ugender");
                                    String udob=response.getString("udob");
                                    String ulocation=response.getString("ulocation");
                                    String usecans=response.getString("usecans");
                                    String utype=response.getString("utype");
                                    //config.writeLoggedEmpty();
                                    config.writeLoginStatus(true);
                                    config.writeLoggedUser(uid,uname,uemail,upass,uphone,ugender,udob,ulocation,usecans,utype);
                                    //Toast.makeText(getApplicationContext(), "Welcome "+uname, Toast.LENGTH_SHORT).show();
                                    loadDashboard();
                                }
                                else if (response.getString("usertype").equals("agent"))
                                {
                                    String sid=response.getString("sid");
                                    String sname=response.getString("sname");
                                    String semail=response.getString("semail");
                                    String spass=response.getString("spass");
                                    String sphone=response.getString("sphone");
                                    String sagent=response.getString("sagent");
                                    String sbrand=response.getString("sbrand");
                                    String saddress=response.getString("saddress");
                                    String ssecans=response.getString("ssecans");
                                    String stype=response.getString("stype");
                                    //config.writeLoggedEmpty();
                                    config.writeLoginStatus(true);
                                    config.writeLoggedShowroom(sid,sname,semail,spass,sphone,sagent,sbrand,saddress,ssecans,stype);
                                    //Toast.makeText(getApplicationContext(), "Welcome "+uname, Toast.LENGTH_SHORT).show();
                                    loadDashboard();
                                }


                            }else{
                                Toast.makeText(getApplicationContext(),
                                        response.getString(KEY_MESSAGE), Toast.LENGTH_SHORT).show();


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();

                        //Display error message whenever an error occurs
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsArrayRequest);
    }

    /**
     * Validates inputs and shows error if any
     * @return
     */
    private boolean validateInputs() {
        if(KEY_EMPTY.equals(useremail)){
            etUsername.setError("Email Address cannot be empty");
            etUsername.requestFocus();
            return false;
        }
        if(KEY_EMPTY.equals(password)){
            etPassword.setError("Password cannot be empty");
            etPassword.requestFocus();
            return false;
        }
        return true;
    }

    public void OnForgot(View view) {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(login_activity.this);
        builder1.setMessage("How would you like to get your password?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Email Password",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        emailresend();

                    }
                });

        builder1.setNegativeButton(
                "Reset Password",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        passwordReset();

                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();


    }

    public void passwordReset()
    {
        Intent i = new Intent(getApplicationContext(), ResetPassword.class);
        startActivity(i);
    }

    public void emailresend()
    {
        final EditText input;
        AlertDialog.Builder builder1 = new AlertDialog.Builder(login_activity.this);
        builder1.setMessage("Enter your Email Address");
        builder1.setCancelable(true);
        input=new EditText(this);
        //String inpemail= input.getText().toString();
       // Toast.makeText(getApplicationContext(),inpemail,Toast.LENGTH_LONG).show();
        builder1.setView(input);
        builder1.setPositiveButton(
                "Send",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String inpemail= input.getText().toString();

                        if(KEY_EMPTY.equals(inpemail)) {
                            input.setError("Email Address cannot be empty");
                            input.requestFocus();

                        }
                        else
                            {
                        displayLoader1();
                        JSONObject request = new JSONObject();
                        try {
                            //Populate the request parameters
                            //Toast.makeText(getApplicationContext(),"HIHIHI",Toast.LENGTH_LONG).show();
                            request.put(KEY_USEREMAIL, inpemail);
                            //request.put(KEY_PASSWORD, password);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                                (Request.Method.POST, base_url+"index.php", request, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        pDialog.dismiss();
                                        try {

                                                Toast.makeText(getApplicationContext(),response.getString(KEY_MESSAGE), Toast.LENGTH_SHORT).show();

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, new Response.ErrorListener() {

                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        pDialog.dismiss();

                                        //Display error message whenever an error occurs
                                        Toast.makeText(getApplicationContext(),
                                                error.getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                });

                        // Access the RequestQueue through your singleton class.
                        MySingleton.getInstance(login_activity.this).addToRequestQueue(jsArrayRequest);






                        dialog.cancel();

                    }
                    }
                });

        builder1.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}

//To create An Action bar for this activity or new ativitesadd this in manifest file, activity tag(APP SPECIFIC)
//(Action bar only, back button not available)

//        android:theme="@style/Theme.AppCompat.Light.DarkActionBar"