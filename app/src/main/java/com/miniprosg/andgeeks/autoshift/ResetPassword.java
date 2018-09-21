package com.miniprosg.andgeeks.autoshift;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.miniprosg.andgeeks.autoshift.helper.PredifValues;

import org.json.JSONException;
import org.json.JSONObject;

public class ResetPassword extends AppCompatActivity {
EditText uemail, uphone,usecans;
String useremail,userphone,usersecans;
int statusVerify=0;
    PredifValues predifValues=new PredifValues();
    private ProgressDialog pDialog;
    String base_url=predifValues.returnipaddressurl();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        uemail=(EditText)findViewById(R.id.uemail1);
        uphone=(EditText)findViewById(R.id.uphone);
        usecans=(EditText)findViewById(R.id.usecans);

    }

    public void onVerifyProfile(View view) {
        useremail=uemail.getText().toString();
        userphone=uphone.getText().toString();
        usersecans=usecans.getText().toString();


        if(validateInput())
        {
            //Toast.makeText(getApplicationContext(),"HIHIHI",Toast.LENGTH_LONG).show();
           verify();

        }

    }
    public boolean validateInput() {

            if ("".equals(useremail)) {
                 uemail.setError("Email Address cannot be empty");
                 uemail.requestFocus();
                return false;
            }
    if ("".equals(userphone)) {
        uphone.setError("Phone Number cannot be empty");
        uphone.requestFocus();
        return false;
    }
    if ("".equals(usersecans)) {
        usecans.setError("Security Answer cannot be empty");
        usecans.requestFocus();
        return false;
    }
//    if ("".equals(userpwd)) {
//        upwd.setError("New Password cannot be empty");
//        upwd.requestFocus();
//        return false;
//    }
//    if(!userpwd.equals(userconfpwd))
//    {
//        uconfpwd.setError("Password Mismatch");
//        uconfpwd.setText("");
//        uconfpwd.requestFocus();
//        return false;
//    }
    return true;


}
    private void displayLoader1()
        {
        pDialog = new ProgressDialog(ResetPassword.this);
        pDialog.setMessage("Contacting Our Server....Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }
public void verify()
{
    displayLoader1();
    JSONObject request = new JSONObject();
    try {
        //Populate the request parameters
       // Toast.makeText(getApplicationContext(),"HIHIHI",Toast.LENGTH_LONG).show();
        request.put("uemail", useremail);
        request.put("usecans", usersecans);
        request.put("uphone", userphone);
        //request.put(KEY_PASSWORD, password);

    } catch (JSONException e) {
        e.printStackTrace();
    }
    JsonObjectRequest jsArrayRequest = new JsonObjectRequest
            (Request.Method.POST, base_url+"verifyPwdReset.php", request, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    pDialog.dismiss();
                    try {
                        Toast.makeText(getApplicationContext(),response.getString("message"),Toast.LENGTH_LONG).show();
                        if(response.getInt("status") == 1)
                        {
                            Intent i= new Intent(getApplicationContext(),ChangePassword.class);
                            i.putExtra("uemail", useremail);
                            startActivity(i);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Account Detail MissMatch. Please provide valid Details",Toast.LENGTH_LONG).show();
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
    MySingleton.getInstance(ResetPassword.this).addToRequestQueue(jsArrayRequest);


}

    public void onUpdatePWD(View view) {
    }
}
