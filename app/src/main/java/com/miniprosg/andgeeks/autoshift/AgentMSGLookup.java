package com.miniprosg.andgeeks.autoshift;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.miniprosg.andgeeks.autoshift.helper.ListAdapter.MyListAdapter;
import com.miniprosg.andgeeks.autoshift.helper.ListAdapter.MyQueryListAdapter;
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
import java.util.ArrayList;
import java.util.List;

public class AgentMSGLookup extends AppCompatActivity {



    String[] Uname={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""},
             Umsg={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""},
             Utime={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""},
             Udate={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""},
             Usid={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};

    ArrayList<String> datalist=new ArrayList<>();

    private ProgressDialog pDialog;
    private ListView queryList;
    PredifValues predifValues=new PredifValues();
    String base_url=predifValues.returnipaddressurl();
    InputStream inputStream=null;
    String carorbike,v_name,v_brand,v_type,u_id,u_name,s_name,s_id;
    LinearLayout layoutspinner;
    String state="",city="",brand="",name,email,get_model,confPwd,mobile,secans,agent,address,carOrbike;
    MyQueryListAdapter adapter;
    MaterialSpinner spinnerModel,spinnercity;

    ArrayList<String> stateList=new ArrayList<>();
    ArrayList<String> listmodel=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_msglookup);

        queryList=(ListView)findViewById(R.id.list_query);
        Bundle extras = getIntent().getExtras();
        s_id = extras.getString("s_id");
        v_type = extras.getString("v_type");
        spinnerModel=(MaterialSpinner)findViewById(R.id.spinQModel) ;
        AgentMSGLookup.BackTaskModel backTaskModel=new AgentMSGLookup.BackTaskModel();
        backTaskModel.execute();
        layoutspinner=(LinearLayout)findViewById(R.id.spinner_layout);
        queryList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                if(Usid[position].equals(""))
                {

                }
                else
                {
                    Intent i = new Intent(getApplicationContext(), Agent_UserInteraction.class);
                    i.putExtra("u_name",Uname[position]);
                    i.putExtra("u_id",Usid[position]);
                    i.putExtra("s_id",s_id);
                    i.putExtra("s_name",R.string.loggeduname);
                    startActivity(i);
                }
            }
        });
    }

    public class BackTaskModel extends AsyncTask<Void,Void,Void> {

        protected void onPreExecute() {
            super.onPreExecute();
            listmodel= new ArrayList<String>();
            listmodel.clear();
            String[] Uname={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""},
                    Umsg={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""},
                    Utime={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""},
                    Udate={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""},
                    Usid={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};

        }

        @Override
        protected Void doInBackground(Void... voids) {
            InputStream is = null;
            String result = "";
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(base_url+"spinnerload/fetchmsgsmodel.php");
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("s_id", s_id));
                nameValuePairs.add(new BasicNameValuePair("v_type", "CAR"));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
//              Get our response as a String.
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
                while ((line = reader.readLine()) != null)
                {
                    result += line;
                }
                is.close();
//              result=sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                JSONArray jArray = new JSONArray(result);
                for (int i = 0; i < jArray.length(); i++)
                {
                    JSONObject jsonObject = jArray.getJSONObject(i);

                    listmodel.add(jsonObject.getString("c_name"));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            spinnerModel = (MaterialSpinner) findViewById(R.id.spinQModel );
            spinnerModel.setItems(listmodel);

            spinnerModel.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

                @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                    get_model=item;
                    //spinnermodel.setVisibility(View.VISIBLE);
                    layoutspinner.setVisibility(View.VISIBLE);
                    AgentMSGLookup.BackTaskQuery backTaskQuery=new AgentMSGLookup.BackTaskQuery();
                    backTaskQuery.execute();

                }
            });

        }
    }
    public class BackTaskQuery extends AsyncTask<Void,Void,Void>{


        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            InputStream is = null;
            String result = "";
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(base_url+"extras/fetchquerymsgs.php");
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("s_id", s_id));
                nameValuePairs.add(new BasicNameValuePair("model", get_model));
                nameValuePairs.add(new BasicNameValuePair("v_type", "CAR"));
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
                    Uname[i]=jsonObject.getString("uname");
                    Umsg[i]=jsonObject.getString("message");
                    Utime[i]=jsonObject.getString("msgtime");
                    Udate[i]=jsonObject.getString("msgdate");
                    Usid[i]=jsonObject.getString("uid");


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            adapter=new MyQueryListAdapter(AgentMSGLookup.this,Uname,Umsg,Utime,Udate,Usid);
            queryList.setAdapter(adapter);
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();

        }
    }


}
