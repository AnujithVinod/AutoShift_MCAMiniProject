package com.miniprosg.andgeeks.autoshift;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.miniprosg.andgeeks.autoshift.helper.PredifValues;

import org.json.JSONException;
import org.json.JSONObject;

public class FeedBack extends AppCompatActivity {
    EditText Email, Name, Phone, Message;
    private ProgressDialog pDialog;
    PredifValues predifValues = new PredifValues();
    String base_url = predifValues.returnipaddressurl();
    String femail, fname, fphone, fmessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        Email = (EditText) findViewById(R.id.femail);
        Phone = (EditText) findViewById(R.id.fphone);
        Name = (EditText) findViewById(R.id.fname);
        Message = (EditText) findViewById(R.id.fcomplaint);
        setTitle("Feedbacks and Suggesions");

    }

    public void feedbackMailer(View view) {

        femail = Email.getText().toString();
        fname = Name.getText().toString();
        fphone = Phone.getText().toString();
        fmessage = Message.getText().toString();
        if (validateInputs()) {

            mailConnect();

        }


    }
    private boolean validateInputs() {
        if("".equals(femail)){
            Email.setError("Email cannot be empty");
            Email.requestFocus();
            return false;
        }
        if("".equals(fname)){
            Name.setError("Name cannot be empty");
            Name.requestFocus();
            return false;
        }
        if("".equals(fphone)){
            Phone.setError("Phone Number cannot be empty");
            Phone.requestFocus();
            return false;
        }
        if("".equals(fmessage)){
            Message.setError("Message cannot be empty");
            Message.requestFocus();
            return false;
        }
        return true;
    }

    private void displayLoader() {
        pDialog = new ProgressDialog(FeedBack.this);
        pDialog.setMessage("Contacting Server.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }

    public void mailConnect() {

        displayLoader();
        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters
            request.put("femail", femail);
            request.put("fname", fname);
            request.put("fphone", fphone);
            request.put("fmessage", fmessage);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, base_url + "extras/feedbackMailer.php", request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pDialog.dismiss();
                        try {
                            //Check if user got logged in successfully
                            //Toast.makeText(getApplicationContext(),"HIHIHI",Toast.LENGTH_LONG).show();
                            if (response.getInt("status") == 1) {

                                Toast.makeText(getApplicationContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                finish();

                            } else {
                                Toast.makeText(getApplicationContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                finish();

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
}
