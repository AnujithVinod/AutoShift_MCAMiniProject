package com.miniprosg.andgeeks.autoshift;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.miniprosg.andgeeks.autoshift.helper.PredifValues;

import org.json.JSONException;
import org.json.JSONObject;

public class UpdateProfile_User extends AppCompatActivity {
    EditText selectName,selectMobile,selectLocation,selectSecans;
    TextView selectEmail;
    String sUname;
    String sPwd;
    String sPhone;
    String slocation;
    String sSecAns,UID;
    PredifValues predifValues=new PredifValues();
    String base_url=predifValues.returnipaddressurl();
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile_user);
        findViewById(R.id.userLayout).setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return false;
            }
        });
        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.spinnsecans);
        spinner.setItems("What is your Nick Name?", "Where is your birth place?", "What is you pets name?", "What is your Fathers Name?", "What is the name of your Favorite Teacher?");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
            }
        });

        Bundle extras = getIntent().getExtras();
        UID=extras.getString("u_id");
//
        selectEmail=(EditText)findViewById(R.id.userEmailId);
        selectLocation=(EditText)findViewById(R.id.location);
        selectMobile=(EditText)findViewById(R.id.mobileNumber);
        selectName=(EditText)findViewById(R.id.fullName);
        selectSecans=(EditText)findViewById(R.id.secans);
        fillContentView();


    }

    private void fillContentView() {

        JSONObject request = new JSONObject();
        try {

            request.put("u_id",UID);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, base_url+"extras/getUserProfile_Details.php", request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            if (response.getInt("status") == 1) {
                                selectEmail.setText(response.getString("uemail"));
                                selectLocation.setText(response.getString("ulocation"));
                                selectMobile.setText(response.getString("uphone"));
                                selectName.setText(response.getString("uname"));
                                selectSecans.setText(response.getString("usecans"));
                            }else{
                                Toast.makeText(getApplicationContext(),
                                        response.getString("message"), Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //Display error message whenever an error occurs
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsArrayRequest);
    }


    private void displayLoader() {
        pDialog = new ProgressDialog(UpdateProfile_User.this);
        pDialog.setMessage("Logging In.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }


    public void passwordReset()
    {
        Intent i = new Intent(getApplicationContext(), ResetPassword.class);
        startActivity(i);
    }


    public void emailresend()
    {
        final EditText input;
        AlertDialog.Builder builder1 = new AlertDialog.Builder(UpdateProfile_User.this);
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

                        if("".equals(inpemail)) {
                            input.setError("Email Address cannot be empty");
                            input.requestFocus();

                        }
                        else
                        {
                            displayLoader();
                            JSONObject request = new JSONObject();
                            try {
                                //Populate the request parameters
                                //Toast.makeText(getApplicationContext(),"HIHIHI",Toast.LENGTH_LONG).show();
                                request.put("uemail", inpemail);
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

                                                Toast.makeText(getApplicationContext(),response.getString("message"), Toast.LENGTH_SHORT).show();

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
                            MySingleton.getInstance(UpdateProfile_User.this).addToRequestQueue(jsArrayRequest);

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


    public void changePassword(View view) {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(UpdateProfile_User.this);
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



    public void updateProfile(View view) {

        sUname=selectName.getText().toString();
        sPhone=selectMobile.getText().toString();
        slocation=selectLocation.getText().toString();
        sSecAns=selectSecans.getText().toString();

        if (validateInputs()) {

            updateMe();

        }

    }



    public void updateMe()
    {
        displayLoader();
        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters

            request.put("uname", sUname);
            request.put("uemail", selectEmail.getText().toString());
            request.put("upass", sPwd);
            request.put("uphone", sPhone);
            request.put("ulocation", slocation);
            request.put("usecans", sSecAns);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, base_url+"root_functions/updateUser.php", request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pDialog.dismiss();
                        try {
                            //Check if user got logged in successfully
                            //Toast.makeText(getApplicationContext(),"HIHIHI",Toast.LENGTH_LONG).show();
                            if (response.getInt("status") == 1) {
                                SharaedPrefernceConfig config;
                                config=new SharaedPrefernceConfig(getApplicationContext());
                                config.writeLoggedEmpty();
                                //ARE YOU LOGGED IN ALREADY? USING SHARED PREFERENCES
                                if(config.readStatus())
                                {
                                    config.writeLoginStatus(false);
                                    config.writeLoggedEmpty();
                                    Toast.makeText(getApplicationContext(),"You have been logged out",Toast.LENGTH_SHORT).show();
                                }

                                Toast.makeText(getApplicationContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                finish();


                            }else{
                                Toast.makeText(getApplicationContext(),
                                        response.getString("message"), Toast.LENGTH_SHORT).show();

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
    private boolean validateInputs() {
        if("".equals(sUname)){
            selectName.setError("Name cannot be empty");
            selectName.requestFocus();
            return false;
        }
        if("".equals(sPhone)){
            selectMobile.setError("Phone Number cannot be empty");
            selectMobile.requestFocus();
            return false;
        }
        if("".equals(slocation)){
            selectLocation.setError("Location cannot be empty");
            selectLocation.requestFocus();
            return false;
        }
        if("".equals(sSecAns)){
            selectSecans.setError("Security Answer cannot be empty");
            selectSecans.requestFocus();
            return false;
        }
        return true;
    }


    public void toFinishCancel(View view) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(UpdateProfile_User.this);
        builder1.setMessage("Discard Changes and Go Back?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
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


        finish();
    }
}
