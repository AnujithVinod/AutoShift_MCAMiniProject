package com.miniprosg.andgeeks.autoshift;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.util.Calendar;

public class UserRegister extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText selectName,selectEmail,selectPwd,selectConfPwd,selectMobile,selectLocation,selectSecans;
    TextView selectDate;
    CheckBox selectTandC;
    int mYear, mMonth, mDay;
    String edGetDOB;
    String sUname;
    String sPwd;
    String sConfPwd;
    String sEmail;
    String sPhone;
    String sGender="";
    String slocation;
    String sSecAns;
    PredifValues predifValues=new PredifValues();
    String base_url=predifValues.returnipaddressurl();
    private ProgressDialog pDialog;
    String[] spingender={"Male","Female","Declined to State"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
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
        selectDate=(TextView) findViewById(R.id.dob);
        selectConfPwd=(EditText)findViewById(R.id.confirmPassword);
        selectEmail=(EditText)findViewById(R.id.userEmailId);
        selectLocation=(EditText)findViewById(R.id.location);
        selectMobile=(EditText)findViewById(R.id.mobileNumber);
        selectName=(EditText)findViewById(R.id.fullName);
        selectPwd=(EditText)findViewById(R.id.password);
        selectSecans=(EditText)findViewById(R.id.secans);
        selectTandC=(CheckBox)findViewById(R.id.terms_conditions);

        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.spinner);
        spinner.setItems("What is your Nick Name?", "Where is your birth place?", "What is you pets name?", "What is your Fathers Name?", "What is the name of your Favorite Teacher?");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
            }
        });

        MaterialSpinner spinnergender = (MaterialSpinner) findViewById(R.id.gender);
        spinnergender.setItems("Male", "Female", "Declined to State");
        spinnergender.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                sGender=item;
            }
        });

//        Spinner spin = (Spinner) findViewById(R.id.simpleSpinner);
//        spin.setOnItemSelectedListener(this);
//        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,spingender);
//        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spin.setAdapter(aa);



    }
    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
        sGender=spingender[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
// TODO Auto-generated method stub

    }


    public void signup(View view) {

        sUname=selectName.getText().toString();
        sPhone=selectMobile.getText().toString();
        sConfPwd=selectConfPwd.getText().toString();
        sPwd=selectPwd.getText().toString();
        sEmail=selectEmail.getText().toString();
        edGetDOB=selectDate.getText().toString();
        slocation=selectLocation.getText().toString();
        sSecAns=selectSecans.getText().toString();

        if (validateInputs()&&(selectTandC.isChecked())) {

            registerMe();


        }

    }
    private void displayLoader1() {
        pDialog = new ProgressDialog(UserRegister.this);
        pDialog.setMessage("Contacting Our Server....Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }

    public void registerMe()
    {
        displayLoader1();
        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters

            request.put("uname", sUname);
            request.put("uemail", sEmail);
            request.put("upass", sPwd);
            request.put("uphone", sPhone);
            request.put("ugender", sGender);
            request.put("udob", edGetDOB);
            request.put("ulocation", slocation);
            request.put("usecans", sSecAns);
            request.put("utype","user");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, base_url+"registerUser.php", request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pDialog.dismiss();
                        try {
                            //Check if user got logged in successfully
                            //Toast.makeText(getApplicationContext(),"HIHIHI",Toast.LENGTH_LONG).show();
                            if (response.getInt("status") == 1) {
                                Toast.makeText(getApplicationContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                Intent i= new Intent(getApplicationContext(),login_activity.class);
                                startActivity(i);


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
        if("".equals(sEmail)){
            selectEmail.setError("Email Address cannot be empty");
            selectEmail.requestFocus();
            return false;
        }
        if("".equals(sPhone)){
            selectMobile.setError("Phone Number cannot be empty");
            selectMobile.requestFocus();
            return false;
        }
        if("".equals(sPwd)){
            selectPwd.setError("Password cannot be empty");
            selectPwd.requestFocus();
            return false;
        }
        if(!sPwd.equals(sConfPwd))
        {
            selectConfPwd.setError("Password Mismatch");
            selectConfPwd.setText("");
            selectConfPwd.requestFocus();
            return false;
        }
        if("".equals(edGetDOB)){
            selectDate.setError("Date Of Birth cannot be empty");
            selectDate.requestFocus();
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
        if("".equals(sGender)){
            sGender="Male";
        }
        if(!selectTandC.isChecked())
        {
            selectTandC.setError("You Must Agree to our Terms and Conditions");
            return false;
        }
        if(selectTandC.isChecked()) {
            selectTandC.setError(null);
        }
        return true;
    }

    public void toLoginPage(View view) {
        Intent i= new Intent(getApplicationContext(),login_activity.class);
        startActivity(i);
    }

    public void OnDateSelect(View view) {

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        selectDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
        edGetDOB=selectDate.getText().toString();
    }


}
