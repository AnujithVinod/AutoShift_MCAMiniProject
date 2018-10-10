package com.miniprosg.andgeeks.autoshift;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AgentRegister extends AppCompatActivity {
    PredifValues predifValues=new PredifValues();
    private ProgressDialog pDialog;
    String base_url=predifValues.returnipaddressurl();
    InputStream inputStream=null;
    String state="",city="",brand="",name,email,pwd,confPwd,mobile,secans,agent,address,carOrbike;
    TextView selectDate,cbbanner;
    CheckBox selectTandC;
    int mYear, mMonth, mDay;
    EditText selectName,selectEmail,selectPwd,selectConfPwd,selectMobile,selectAddress,selectAgent,selectSecans;
    MaterialSpinner spinnerbrand,spinnerstate,spinnercity;

    ArrayList<String> stateList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_register);
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
        AgentRegister.BackTaskState backTaskState=new AgentRegister.BackTaskState();
        backTaskState.execute();
        AgentRegister.BackTaskBrand backTaskBrand=new AgentRegister.BackTaskBrand();
        backTaskBrand.execute();
        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.spinnsecans);
        spinner.setItems("What is your Nick Name?", "Where is your birth place?", "What is you pets name?", "What is your Fathers Name?", "What is the name of your Favorite Teacher?");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
            }
        });

        cbbanner=(TextView) findViewById(R.id.carorbikebanner);
        cbbanner.setText("Create New "+carOrbike+" Showroom Account");
        selectConfPwd=(EditText)findViewById(R.id.confirmPassword);
        selectEmail=(EditText)findViewById(R.id.userEmailId);
        selectAddress=(EditText)findViewById(R.id.address);
        selectMobile=(EditText)findViewById(R.id.mobileNumber);
        selectName=(EditText)findViewById(R.id.fullName);
        selectAgent=(EditText)findViewById(R.id.agentName);
        selectPwd=(EditText)findViewById(R.id.password);
        selectSecans=(EditText)findViewById(R.id.secans);
        selectTandC=(CheckBox)findViewById(R.id.terms_conditions);

    }

    public void toNewBrandinfo(View view) {

        Intent i= new Intent(getApplicationContext(),AddBrandInfo.class);
        i.putExtra("car_bike", carOrbike );
        startActivity(i);
        finish();


    }

    public class BackTaskBrand extends AsyncTask<Void,Void,Void>{
        ArrayList<String> listbrand;

        protected void onPreExecute() {
            super.onPreExecute();
            listbrand= new ArrayList<String>();
            listbrand.clear();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            InputStream is = null;
            String result = "";
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(base_url+"spinnerload/getbrand.php");
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("carorbike", carOrbike));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                // Get our response as a String.
                is = entity.getContent();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    result += line;
                }
                is.close();
                //result=sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                JSONArray jArray = new JSONArray(result);
                if(carOrbike.equals("CAR")) {
                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject jsonObject = jArray.getJSONObject(i);


                        listbrand.add(jsonObject.getString("cbi_name"));

                    }
                }
                else if(carOrbike.equals("BIKE")) {
                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject jsonObject = jArray.getJSONObject(i);


                        listbrand.add(jsonObject.getString("bbi_name"));

                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

//        Spinner spin = (Spinner) findViewById(R.id.spin_state);
//       spin.setOnItemSelectedListener(this);
//      ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,liststate);
//      aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//      spin.setAdapter(aa);


            spinnerbrand = (MaterialSpinner) findViewById(R.id.spinBrand );
            spinnerbrand.setItems(listbrand);

            spinnerbrand.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

                @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                    brand=item;

                }
            });



//        Spinner s = (Spinner) findViewById(R.id.spin_state);
//        String[] arr = liststate.toArray(new String[liststate.size()]);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arr );
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        s.setAdapter(adapter);


        }
    }


    public class BackTaskState extends AsyncTask<Void,Void,Void>{
    ArrayList<String> liststate;

    protected void onPreExecute() {
        super.onPreExecute();
        liststate= new ArrayList<String>();
        liststate.clear();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        InputStream is = null;
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(base_url+"spinnerload/getstate.php");
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            // Get our response as a String.
            is = entity.getContent();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                result += line;
            }
            is.close();
            //result=sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            JSONArray jArray = new JSONArray(result);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jsonObject = jArray.getJSONObject(i);

                liststate.add(jsonObject.getString("city_state"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

//        Spinner spin = (Spinner) findViewById(R.id.spin_state);
//       spin.setOnItemSelectedListener(this);
//      ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,liststate);
//      aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//      spin.setAdapter(aa);


        spinnerstate = (MaterialSpinner) findViewById(R.id.spinstatemet );
        spinnerstate.setItems(liststate);

        spinnerstate.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                state=item;
                Toast.makeText(getApplicationContext(),state.toString(),Toast.LENGTH_LONG).show();
                AgentRegister.BackTaskCity backTaskCity=new AgentRegister.BackTaskCity();
                backTaskCity.execute();

            }
        });



//        Spinner s = (Spinner) findViewById(R.id.spin_state);
//        String[] arr = liststate.toArray(new String[liststate.size()]);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arr );
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        s.setAdapter(adapter);


    }
}


    public class BackTaskCity extends AsyncTask<Void,Void,Void>{
        ArrayList<String> listcity;

        protected void onPreExecute() {
            super.onPreExecute();
            listcity= new ArrayList<String>();
            listcity.clear();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            InputStream is = null;
            String result = "";
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(base_url+"spinnerload/getcity.php");
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("state", state));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                // Get our response as a String.
                is = entity.getContent();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    result += line;
                }
                is.close();
                //result=sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                JSONArray jArray = new JSONArray(result);
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject jsonObject = jArray.getJSONObject(i);

                    listcity.add(jsonObject.getString("city_name"));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            spinnercity = (MaterialSpinner) findViewById(R.id.spincitymet );
            spinnercity.setItems(listcity);

            spinnercity.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

                @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                    city=item;


                }
            });


//
//        Spinner s = (Spinner) findViewById(R.id.spin_state);
//        String[] arr = liststate.toArray(new String[liststate.size()]);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arr );
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        s.setAdapter(adapter);


        }
    }


    private void displayLoader()
    {
        pDialog = new ProgressDialog(AgentRegister.this);
        pDialog.setMessage("Contacting Our Server....Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }

    private void displayLoader1() {
        pDialog = new ProgressDialog(AgentRegister.this);
        pDialog.setMessage("Contacting Our Server....Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }


    public void signup(View view) {

        name=selectName.getText().toString();
        mobile=selectMobile.getText().toString();
        confPwd=selectConfPwd.getText().toString();
        pwd=selectPwd.getText().toString();
        email=selectEmail.getText().toString();
        agent=selectAgent.getText().toString();
        address=selectAddress.getText().toString();
        secans=selectSecans.getText().toString();
        if (validateInputs()&&(selectTandC.isChecked())) {

            registerMe();


        }

    }


    public void registerMe()
    {
        displayLoader1();
        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters

            request.put("sname", name);
            request.put("semail", email);
            request.put("stype",carOrbike);
            request.put("spass", pwd);
            request.put("sphone", mobile);
            request.put("sbrand", brand);
            request.put("sagent", agent);
            request.put("sstate", state);
            request.put("scity", city);
            request.put("saddress", address);
            request.put("ssecans", secans);
            //Toast.makeText(getApplicationContext(),request.toString(),Toast.LENGTH_LONG).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }

            JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                    (Request.Method.POST, base_url+"registerShowroom.php", request, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            pDialog.dismiss();
                            try {
                                //Check if user got logged in successfully
                                //Toast.makeText(getApplicationContext(),"HIHIHI",Toast.LENGTH_LONG).show();
                                if (response.getInt("status") == 1) {
                                    Toast.makeText(getApplicationContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                    Intent i= new Intent(getApplicationContext(),login_activity.class);
                                    i.putExtra("utype", "agent");
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
    private boolean validateInputs() {
        if("".equals(name)){
            selectName.setError("Name cannot be empty");
            selectName.requestFocus();
            return false;
        }
        if("".equals(email)){
            selectEmail.setError("Email Address cannot be empty");
            selectEmail.requestFocus();
            return false;
        }
        if("".equals(mobile)){
            selectMobile.setError("Phone Number cannot be empty");
            selectMobile.requestFocus();
            return false;
        }
        if("".equals(pwd)){
            selectPwd.setError("Password cannot be empty");
            selectPwd.requestFocus();
            return false;
        }
        if(!pwd.equals(confPwd))
        {
            selectConfPwd.setError("Password Mismatch");
            selectConfPwd.setText("");
            selectConfPwd.requestFocus();
            return false;
        }
        if("".equals(state)){
            selectConfPwd.setError("State cannot be empty");
            selectConfPwd.requestFocus();
            return false;
        }
        if("".equals(city)){
            selectAddress.setError("City cannot be empty");
            selectAddress.requestFocus();
            return false;
        }
        if("".equals(address)){
            selectAddress.setError("Address cannot be empty");
            selectAddress.requestFocus();
            return false;
        }
        if("".equals(secans)){
            selectSecans.setError("Security Answer cannot be empty");
            selectSecans.requestFocus();
            return false;
        }
        if("".equals(brand)){
            selectEmail.setError("Brand Name cannot be empty");
            selectEmail.requestFocus();
            return false;
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


}
