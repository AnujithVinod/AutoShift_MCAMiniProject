package com.miniprosg.andgeeks.autoshift;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.miniprosg.andgeeks.autoshift.helper.ListAdapter.MyQueryListAdapter;
import com.miniprosg.andgeeks.autoshift.helper.ListAdapter.MyUserReplyListAdapter;
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

public class UserMSGLookup extends AppCompatActivity {



    String[] Ushr_id={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""},
           Usname={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""},
            Uv_id={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""},
            Uc_name={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
            //Usid={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};

    ArrayList<String> datalist=new ArrayList<>();

    private ProgressDialog pDialog;
    private ListView queryList;
    PredifValues predifValues=new PredifValues();
    String base_url=predifValues.returnipaddressurl();
    InputStream inputStream=null;
    String carorbike,v_name,v_brand,v_type,u_id,u_name,s_name,s_id;
    LinearLayout layoutspinner;
    String state="",city="",brand="",name,email,get_model,confPwd,mobile,secans,agent,address,carOrbike;
    MyUserReplyListAdapter adapter;
    MaterialSpinner spinnerModel,spinnercity;

    ArrayList<String> stateList=new ArrayList<>();
    ArrayList<String> listmodel=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_msglookup);

        queryList=(ListView)findViewById(R.id.list_query);
        Bundle extras = getIntent().getExtras();
        u_id = extras.getString("u_id");
        u_name = extras.getString("u_name");
        //spinnerModel=(MaterialSpinner)findViewById(R.id.spinQModel) ;
        layoutspinner=(LinearLayout)findViewById(R.id.spinner_layout);
        UserMSGLookup.BackTaskQuery backTaskQuery=new UserMSGLookup.BackTaskQuery();
        backTaskQuery.execute();
        queryList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                if(Ushr_id[position].equals(""))
                {

                }
                else
                {
                    Intent i = new Intent(getApplicationContext(), User_AgentInteraction.class);
                    i.putExtra("u_name",u_name);
                    i.putExtra("u_id",u_id);
                    i.putExtra("v_id",Uv_id[position]);
                    i.putExtra("s_id",Ushr_id[position]);
                    i.putExtra("s_name",Usname[position]);
                    startActivity(i);
                }
            }
        });
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
                HttpPost httppost = new HttpPost(base_url+"extras/fetchuser_replymsgs.php");
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("u_id", u_id));
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
                    Ushr_id[i]=jsonObject.getString("shr_id");
                    Usname[i]=jsonObject.getString("sname");
                    Uv_id[i]=jsonObject.getString("v_id");
                    Uc_name[i]=jsonObject.getString("c_name");

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            adapter=new MyUserReplyListAdapter(UserMSGLookup.this,Ushr_id,Usname,Uv_id,Uc_name);
            queryList.setAdapter(adapter);
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();

        }
    }


}