package com.miniprosg.andgeeks.autoshift;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.miniprosg.andgeeks.autoshift.helper.ListAdapter.MyListAdapter;
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

public class NearShowroom extends AppCompatActivity {
    PredifValues predifValues=new PredifValues();

    String[] Sname={"","","","",""},Semail={"","","","",""},Saddress={"","","","",""},Sphone={"","","","",""},Sagent={"","","","",""},Sid={"","","","",""};
    ArrayList<String> datalist=new ArrayList<>();

    private ProgressDialog pDialog;
    private ListView showroomList;
    String base_url=predifValues.returnipaddressurl();
    InputStream inputStream=null;
    String carorbike,v_name,v_brand,v_type,u_id,u_name,s_name;

    String state="",city="",brand="",name,email,pwd,confPwd,mobile,secans,agent,address,carOrbike;
    MyListAdapter adapter;
    MaterialSpinner spinnerstate,spinnercity;

    ArrayList<String> stateList=new ArrayList<>();
    ArrayList<String> listshowroom=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_near_showroom);

        showroomList=(ListView)findViewById(R.id.list_showroom);


        Bundle extras = getIntent().getExtras();
        v_name= extras.getString("v_name");
        v_brand= extras.getString("v_brand");
        v_type= extras.getString("v_type");
        u_id = extras.getString("u_id");
        u_name = extras.getString("u_name");
        NearShowroom.BackTaskState backTaskState=new NearShowroom.BackTaskState();
        backTaskState.execute();


        showroomList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                if(Sid[position].equals(""))
                {

                }
                else {
                    Intent i = new Intent(getApplicationContext(), ShowRoomView.class);
                    i.putExtra("s_id",Sid[position]);
                    i.putExtra("u_name", u_name);
                    i.putExtra("s_name",Sname[position]);
                    i.putExtra("u_id", u_id);
                    startActivity(i);
                }

            }
        });

    }


    public class BackTaskState extends AsyncTask<Void,Void,Void> {
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


            spinnerstate = (MaterialSpinner) findViewById(R.id.spinstatemet );
            spinnerstate.setItems(liststate);

            spinnerstate.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

                @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                    state=item;
                    Toast.makeText(getApplicationContext(),state.toString(),Toast.LENGTH_LONG).show();
                    NearShowroom.BackTaskCity backTaskCity=new NearShowroom.BackTaskCity();
                    backTaskCity.execute();

                }
            });




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
                    NearShowroom.BackTaskShowroom backTaskShowroom=new NearShowroom.BackTaskShowroom();
                    backTaskShowroom.execute();
                }
            });

        }
    }


    public class BackTaskShowroom extends AsyncTask<Void,Void,Void>{


        protected void onPreExecute() {
            super.onPreExecute();
            listshowroom= new ArrayList<String>();
            listshowroom.clear();
            String[] Sname={"","","","",""},Semail={"","","","",""},Saddress={"","","","",""},Sphone={"","","","",""},Sagent={"","","","",""},Sid={"","","","",""};

        }

        @Override
        protected Void doInBackground(Void... voids) {
            InputStream is = null;
            String result = "";
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(base_url+"extras/getnearshowroom.php");
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("v_type", v_type));
                nameValuePairs.add(new BasicNameValuePair("city", city));
                nameValuePairs.add(new BasicNameValuePair("v_brand", v_brand));
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
//                      listshowroom.add(jsonObject.getString("sname"));

                        Sname[i]=jsonObject.getString("sname");
                        Semail[i]=jsonObject.getString("semail");
                        Sagent[i]=jsonObject.getString("sagent");
                        Saddress[i]=jsonObject.getString("saddress");
                        Sphone[i]=jsonObject.getString("sphone");
                        Sid[i]=jsonObject.getString("sid");

                    }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            adapter=new MyListAdapter(NearShowroom.this, Sname, Semail,Saddress,Sphone,Sagent,Sid);
            showroomList.setAdapter(adapter);
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();

        }
    }

}

