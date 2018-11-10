package com.miniprosg.andgeeks.autoshift;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
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
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class User_AgentInteraction extends AppCompatActivity {

    private EditText edMessage;
    private ListView messageList;
    private ImageButton btnMessageSent;
    TextView MSGRequest,MSGResponse,AgentName,UserName;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;
    String SID,UID,SNAME,UNAME;
    PredifValues predifValues=new PredifValues();
    String base_url=predifValues.returnipaddressurl();
    private ProgressDialog pDialog;
    StringBuilder sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Bundle extras = getIntent().getExtras();
        SID= extras.getString("s_id");
        UID=extras.getString("u_id");
        SNAME=extras.getString("s_name");
        UNAME=extras.getString("u_name");



        setContentView(R.layout.activity_user_agent_interaction);
        edMessage = (EditText) findViewById(R.id.messages_text);
        btnMessageSent=(ImageButton)findViewById(R.id.messages_sent);
        MSGRequest=(TextView)findViewById(R.id.request);
        AgentName=(TextView)findViewById(R.id.agentName);
        UserName=(TextView)findViewById(R.id.userName);
        MSGResponse=(TextView)findViewById(R.id.response);
        MSGRequest.setText("");
        fillCommunicationMsgs();

        AgentName.setText(SNAME);
        UserName.setText(UNAME+" (YOU)");



        btnMessageSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c);

                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
                String formattedTime = mdformat.format(calendar.getTime());
                String msg= edMessage.getText().toString();

                if(msg.equals(""))
                {

                }
                else
                {   MSGRequest.setText("");
                    fillMsgDB(msg,formattedDate,formattedTime);
                }

            }
        });
    }

    private void fillCommunicationMsgs() {
        User_AgentInteraction.BackTaskFillAgentMsgs backTaskFillAgentMsgs=new User_AgentInteraction.BackTaskFillAgentMsgs();
        backTaskFillAgentMsgs.execute();

        User_AgentInteraction.BackTaskFillUserMsgs backTaskFillUserMsgs=new User_AgentInteraction.BackTaskFillUserMsgs();
        backTaskFillUserMsgs.execute();



    }

    private void displayLoader() {
        pDialog = new ProgressDialog(User_AgentInteraction.this);
        pDialog.setMessage("Contacting Our Server....Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }


    private void fillMsgDB(String message,String msgdate,String msgtime) {
        displayLoader();
        JSONObject request = new JSONObject();
        try {

            request.put("usr_id", UID);
            request.put("shr_id", SID);
            request.put("message", message);
            request.put("msgdate", msgdate);
            request.put("msgtime", msgtime);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, base_url+"extras/fillMsgDB.php", request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pDialog.dismiss();
                        try {

                            if (response.getInt("status") == 1) {
                                edMessage.setText("");
                                fillCommunicationMsgs();

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


    public class BackTaskFillUserMsgs extends AsyncTask<Void,Void,Void> {
        ArrayList<String> listUmsgs;
        ArrayList<String> listSmsgs;


        protected void onPreExecute() {
            super.onPreExecute();
            listUmsgs= new ArrayList<String>();
            listUmsgs.clear();
            listSmsgs= new ArrayList<String>();
            listSmsgs.clear();
            //Toast.makeText(getApplicationContext(),SID+"   "+UID,Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            InputStream is = null;
            String result = "";
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(base_url+"extras/getusermsgs.php");
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("usr_id", UID));
                nameValuePairs.add(new BasicNameValuePair("shr_id", SID));
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
                sb = new StringBuilder();
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject jsonObject = jArray.getJSONObject(i);
//                    Toast.makeText(getApplicationContext(),SID+UID,Toast.LENGTH_LONG).show();
                    sb.append("\n\n"+jsonObject.getString("message")+"\n        ( "+jsonObject.getString("msgdate")+" ; "+jsonObject.getString("msgtime")+")");

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);

            MSGRequest.setText(sb.toString());

        }
    }





    public class BackTaskFillAgentMsgs extends AsyncTask<Void,Void,Void> {
        ArrayList<String> listUmsgs;
        ArrayList<String> listSmsgs;


        protected void onPreExecute() {
            super.onPreExecute();
            listUmsgs= new ArrayList<String>();
            listUmsgs.clear();
            listSmsgs= new ArrayList<String>();
            listSmsgs.clear();
            //Toast.makeText(getApplicationContext(),SID+"   "+UID,Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            InputStream is = null;
            String result = "";
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(base_url+"extras/getagentmsgs.php");
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("usr_id", UID));
                nameValuePairs.add(new BasicNameValuePair("shr_id", SID));
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
                sb = new StringBuilder();
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject jsonObject = jArray.getJSONObject(i);
//                    Toast.makeText(getApplicationContext(),SID+UID,Toast.LENGTH_LONG).show();
                    sb.append("\n\n"+jsonObject.getString("message")+"\n        ( "+jsonObject.getString("msgdate")+" ; "+jsonObject.getString("msgtime")+")");

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);

            MSGResponse.setText(sb.toString());

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.refresh_only_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.refresh:
                fillCommunicationMsgs();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
