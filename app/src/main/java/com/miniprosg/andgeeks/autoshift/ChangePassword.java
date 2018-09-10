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


public class ChangePassword extends AppCompatActivity {
    String userpwd, userconfpwd,useremail;
    EditText upwd, uconfpwd;
    PredifValues predifValues=new PredifValues();
    private ProgressDialog pDialog;
    String base_url=predifValues.returnipaddressurl();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Bundle extras = getIntent().getExtras();
        upwd = (EditText) findViewById(R.id.upass1);
        uconfpwd = (EditText) findViewById(R.id.uconfpwd1);
        useremail = extras.getString("uemail");
        Toast.makeText(getApplicationContext(),useremail,Toast.LENGTH_LONG).show();
    }

    public void onPasswordUpdate(View view) {
        userpwd = upwd.getText().toString();
        userconfpwd = uconfpwd.getText().toString();
        if (validatePwd()) {
            changePassword();
        }

    }
    public boolean validatePwd() {
        if("".equals(useremail))
        {
            Toast.makeText(getApplicationContext(),"Sorry. Were not able to Verify you. Please try again :(",Toast.LENGTH_LONG).show();
            Intent i= new Intent(getApplicationContext(),ResetPassword.class);
            startActivity(i);
            finish();

        }

        if ("".equals(userpwd)) {
            upwd.setError("New Password cannot be empty");
            upwd.requestFocus();
            return false;
        }
        if (!userpwd.equals(userconfpwd)) {
            uconfpwd.setError("Password Mismatch");
            uconfpwd.setText("");
            uconfpwd.requestFocus();
            return false;
        }
        return true;

    }
    private void displayLoader1() {
        pDialog = new ProgressDialog(ChangePassword.this);
        pDialog.setMessage("Contacting Our Server....Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }
    public void changePassword()
    {
        displayLoader1();
        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters
            //Toast.makeText(getApplicationContext(),"HIHIHI",Toast.LENGTH_LONG).show();
            request.put("uemail", useremail);
            request.put("upass", userpwd);
            //request.put(KEY_PASSWORD, password);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, base_url+"changePwdReset.php", request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pDialog.dismiss();
                        try {

                            Toast.makeText(getApplicationContext(),response.getString("message"), Toast.LENGTH_SHORT).show();
                            Intent i= new Intent(getApplicationContext(),login_activity.class);
                            startActivity(i);
                            finish();
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
        MySingleton.getInstance(ChangePassword.this).addToRequestQueue(jsArrayRequest);



    }

}
