package com.miniprosg.andgeeks.autoshift;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.miniprosg.andgeeks.autoshift.helper.PredifValues;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
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

public class User_AgentInteraction extends AppCompatActivity {

    private EditText edMessage;
    private ListView messageList;
    private ImageButton btnMessageSent;
    TextView MSGRequest;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;
    String SID,UID;

    PredifValues predifValues=new PredifValues();
    String base_url=predifValues.returnipaddressurl();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Bundle extras = getIntent().getExtras();
        SID= extras.getString("s_id");
        UID=extras.getString("u_id");



        setContentView(R.layout.activity_user_agent_interaction);
        edMessage = (EditText) findViewById(R.id.messages_text);
        edMessage.setText(SID);
        btnMessageSent=(ImageButton)findViewById(R.id.messages_sent);
        MSGRequest=(TextView)findViewById(R.id.request);

        User_AgentInteraction.BackTaskFillMsgs backTaskFillMsgs=new User_AgentInteraction.BackTaskFillMsgs();
        backTaskFillMsgs.execute();



        btnMessageSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                MSGRequest.setText("   "+MSGRequest.getText().toString()+"\n   "+edMessage.getText().toString());
                edMessage.setText("");

                
            }
        });


    }

    public class BackTaskFillMsgs extends AsyncTask<Void,Void,Void> {
        ArrayList<String> listUmsgs;
        ArrayList<String> listSmsgs;

        protected void onPreExecute() {
            super.onPreExecute();
            listUmsgs= new ArrayList<String>();
            listUmsgs.clear();
            listSmsgs= new ArrayList<String>();
            listSmsgs.clear();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            InputStream is = null;
            String result = "";
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(base_url+"extras/fillusermsgs.php");
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("usr_id", UID));
                nameValuePairs.add(new BasicNameValuePair("shr_id", SID));
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
//                    Toast.makeText(getApplicationContext(),SID+UID,Toast.LENGTH_LONG).show();
                    listUmsgs.add(jsonObject.getString("message"));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            String s = "";
            for (int i = 0; i < listUmsgs.size(); i++) {
                s += listUmsgs.get(i);
            }
//
//            StringBuilder sb = new StringBuilder();
//            for (String s : list)
//            {
//                sb.append(s);
//
//            }
            MSGRequest.setText(s.toString());

        }
    }

}
