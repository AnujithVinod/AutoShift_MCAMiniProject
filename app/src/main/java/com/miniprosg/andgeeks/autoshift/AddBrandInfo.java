package com.miniprosg.andgeeks.autoshift;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.inputmethod.InputMethodManager;
import android.view.MotionEvent;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.miniprosg.andgeeks.autoshift.helper.PredifValues;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class AddBrandInfo extends AppCompatActivity {
    String sbname, sbfound,sbhead,sbparent,sbwebsite,sbodetails,carOrbike;
    EditText ebname,ebhead,ebparent,ebwebsite,ebodetails;
    TextView ebfound;
    PredifValues predifValues=new PredifValues();
    private ProgressDialog pDialog;
    int mYear, mMonth, mDay;
    String base_url=predifValues.returnipaddressurl();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_brand_info);
        findViewById(R.id.agentLayout).setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return false;
            }
        });
        Bundle extras = getIntent().getExtras();
        carOrbike = extras.getString("car_bike");
        Toast.makeText(getApplicationContext(),carOrbike,Toast.LENGTH_LONG).show();
        ebname = (EditText) findViewById(R.id.brandName);
        ebfound = (TextView) findViewById(R.id.brandFound);
        ebhead = (EditText) findViewById(R.id.brandHead);
        ebparent = (EditText) findViewById(R.id.brandParent);
        ebwebsite = (EditText) findViewById(R.id.brandWebsite);
        ebodetails = (EditText) findViewById(R.id.brandODetails);
    }

    public boolean validatePwd() {
        if("".equals(sbfound))
        {
            ebfound.setError("Found Date cannot be empty");
            ebfound.requestFocus();
            return false;

        }

        if ("".equals(sbname)) {
            ebname.setError("Brand Name cannot be empty");
            ebname.requestFocus();
            return false;
        }
        if ("".equals(sbhead)) {
            ebhead.setError("Head Quaters cannot be empty");
            ebhead.requestFocus();
            return false;
        }
        if ("".equals(sbparent)) {
            sbparent="Self Owned";
        }
        if ("".equals(sbwebsite)) {
            ebwebsite.setError("WebSite cannot be empty");
            ebwebsite.requestFocus();
            return false;
        }
        if ("".equals(sbodetails)) {
            ebodetails.setError("WebSite cannot be empty");
            ebodetails.requestFocus();
            return false;
        }

        return true;

    }
    private void displayLoader1() {
        pDialog = new ProgressDialog(AddBrandInfo.this);
        pDialog.setMessage("Contacting Our Server....Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }
    public void OnFoundDate(View view) {

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        ebfound.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
        sbfound = ebfound.getText().toString();
    }
    public void onBrandAdd(View view) {
        sbname = ebname.getText().toString();
        sbhead = ebhead.getText().toString();
        sbparent = ebparent.getText().toString();
        sbwebsite = ebwebsite.getText().toString();
        sbfound = ebfound.getText().toString();
        sbodetails=ebodetails.getText().toString();
        if (validatePwd())
        {
            if ("CAR".equals(carOrbike))
            {
                addCarBrand();
            }
            else if ("BIKE".equals(carOrbike))
            {
                addBikeBrand();
            }
        }
    }
    public void addCarBrand()
    {
        displayLoader1();
        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters

            request.put("bname", sbname);
            request.put("bfound", sbfound);
            request.put("bhead", sbhead);
            request.put("bparent", sbparent);
            request.put("bwebsite", sbwebsite);
            request.put("bodetails", sbodetails);

            //Toast.makeText(getApplicationContext(),request.toString(),Toast.LENGTH_LONG).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, base_url+"extras/addcar_brandinfo.php", request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pDialog.dismiss();
                        try {
                            //Check if user got logged in successfully
                            //Toast.makeText(getApplicationContext(),"HIHIHI",Toast.LENGTH_LONG).show();
                            if (response.getInt("status") == 1) {
                                Toast.makeText(getApplicationContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                Intent i= new Intent(getApplicationContext(),AgentRegister.class);
                                i.putExtra("car_bike", carOrbike);
                                startActivity(i);

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
   public void addBikeBrand()
    {
        displayLoader1();
        JSONObject request = new JSONObject();
       try {
            //Populate the request parameters
           //Toast.makeText(getApplicationContext(),"HIHIHI",Toast.LENGTH_LONG).show();
            request.put("bname", sbname);
            request.put("bfound", sbfound);
            request.put("bhead", sbhead);
            request.put("bparent", sbparent);
            request.put("bwebsite", sbwebsite);
            request.put("bodetails", sbodetails);
            //request.put(KEY_PASSWORD, password);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, base_url+"extras/addbike_brandinfo.php", request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pDialog.dismiss();
                        try {
                            if (response.getInt("status") == 1) {
                                Toast.makeText(getApplicationContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                Intent i= new Intent(getApplicationContext(),AgentRegister.class);
                                i.putExtra("car_bike", carOrbike);
                                startActivity(i);

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
                       // Toast.makeText(getApplicationContext(),"HIHIHI",Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_SHORT).show();

                   }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(AddBrandInfo.this).addToRequestQueue(jsArrayRequest);
   }



}
