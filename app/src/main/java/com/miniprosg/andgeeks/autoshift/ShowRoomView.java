package com.miniprosg.andgeeks.autoshift;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.miniprosg.andgeeks.autoshift.helper.PredifValues;

import org.json.JSONException;
import org.json.JSONObject;

public class ShowRoomView extends AppCompatActivity {
    String SID,UID,SNAME,UNAME,VID;
    TextView
            v_brand,
            v_email,
            v_phone,
            v_agent,
            v_address,
            v_gstin;
    PredifValues predifValues=new PredifValues();
    String base_url=predifValues.returnipaddressurl();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_showroom_view);
        Bundle extras = getIntent().getExtras();
        SID= extras.getString("s_id");
        VID= extras.getString("v_id");
        UID=extras.getString("u_id");
        SNAME=extras.getString("s_name");
        UNAME=extras.getString("u_name");
        setTitle(SNAME);
        v_brand=(TextView)findViewById(R.id.v_brand);
        v_email=(TextView)findViewById(R.id.v_email);
        v_phone=(TextView)findViewById(R.id.v_phone);
        v_agent=(TextView)findViewById(R.id.v_agent);
        v_address=(TextView)findViewById(R.id.v_address);
        v_gstin=(TextView)findViewById(R.id.v_gstin);

        fillContentView();


    }

    private void fillContentView() {

        JSONObject request = new JSONObject();
        try {

            request.put("s_id", SID);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, base_url+"extras/getShowroomDetails.php", request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            if (response.getInt("status") == 1) {
                                v_brand.setText(response.getString("sbrand"));
                                v_address.setText(response.getString("saddress"));
                                v_agent.setText(response.getString("sagent"));
                                v_email.setText(response.getString("semail"));
                                v_phone.setText(response.getString("sphone"));
                                v_gstin.setText(response.getString("sgstin"));
                            }else{
                                Toast.makeText(getApplicationContext(),
                                        response.getString("message"), Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //Display error message whenever an error occurs
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsArrayRequest);
    }


    public void messageAgent(View view) {

        Intent i = new Intent(getApplicationContext(), User_AgentInteraction.class);
        i.putExtra("s_id",SID);
        i.putExtra("v_id",VID);
        i.putExtra("u_name", UNAME);
        i.putExtra("s_name",SNAME);
        i.putExtra("u_id", UID);
        startActivity(i);
    }
}
