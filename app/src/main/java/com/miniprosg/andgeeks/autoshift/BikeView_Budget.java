package com.miniprosg.andgeeks.autoshift;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import java.util.ArrayList;
import java.util.List;

public class BikeView_Budget extends AppCompatActivity {

    EditText toLimit,fromLimit;

    TextView

            b_reldate, b_sercost, b_manf, b_warper, b_warkm, b_freeservice, b_colour, b_pexshow, b_ponroad, b_odetails,

    bd_length, bd_width, bd_height, bd_sadheight, bd_seatcap, bd_svolume, bd_wbase, bd_kweight, bd_ridetype, bd_odetails,

    bte_type, bte_startm, bte_coolsys, bte_drive, bte_disp, bte_maxtorque, bte_maxspeed, bte_maxpow, bte_acceleration, bte_gearno, bte_cylno, bte_transtype, bte_clutchtype, bte_odetails,

    btb_wlsize, btb_trad,btb_fbtype, btb_rbtype, btb_trtype, btb_odetails,

    bf_mileage, bf_tcap, bf_type, bf_enorm, bf_odetails,

    bse_batcap, bse_speedom, bse_odom, bse_tripm, bse_frest, bse_abs, bse_ignitype, bse_battype, bse_prohl, bse_twinind, bse_odetails;


    LinearLayout layoutset,layoutspinner;

    String get_brand,get_model;
    String tolimitString="0",fromlimitString="0";

    MaterialSpinner spinnerbrand,spinnermodel;

    PredifValues predifValues=new PredifValues();
    String base_url=predifValues.returnipaddressurl();
    private ProgressDialog pDialog;
    InputStream inputStream=null;

    ArrayList<String> brandList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_view_budget);

        layoutset=(LinearLayout)findViewById(R.id.linear_set);
        layoutspinner=(LinearLayout)findViewById(R.id.spinner_layout);
        layoutspinner.setVisibility(View.INVISIBLE);
        toLimit=(EditText)findViewById(R.id.to_limit);
        fromLimit=(EditText)findViewById(R.id.from_limit);


        b_reldate=(TextView)findViewById(R.id.b_reldate);
        b_sercost=(TextView)findViewById(R.id.b_sercost);
        b_manf=(TextView)findViewById(R.id.b_manf);
        b_warper=(TextView)findViewById(R.id.b_warper);
        b_warkm=(TextView)findViewById(R.id.b_warkm);
        b_freeservice=(TextView)findViewById(R.id.b_freeservice);
        b_colour=(TextView)findViewById(R.id.b_colour);
        b_pexshow=(TextView)findViewById(R.id.b_pexshow);
        b_ponroad=(TextView)findViewById(R.id.b_ponroad);
        b_odetails=(TextView)findViewById(R.id.b_odetails);

        bd_length=(TextView)findViewById(R.id.bd_length);
        bd_width=(TextView)findViewById(R.id.bd_width);
        bd_height=(TextView)findViewById(R.id.bd_height);
        bd_sadheight=(TextView)findViewById(R.id.bd_sadheight);
        bd_seatcap=(TextView)findViewById(R.id.bd_seatcap);
        bd_svolume=(TextView)findViewById(R.id.bd_volume);
        bd_wbase=(TextView)findViewById(R.id.bd_wbase);
        bd_kweight=(TextView)findViewById(R.id.bd_kweight);
        bd_ridetype=(TextView)findViewById(R.id.bd_ridetype);
        bd_odetails=(TextView)findViewById(R.id.bd_odetails);

        bte_type=(TextView)findViewById(R.id.bte_engtype);
        bte_startm=(TextView)findViewById(R.id.bte_startmode);
        bte_coolsys=(TextView)findViewById(R.id.bte_coolsys);
        bte_drive=(TextView)findViewById(R.id.bte_drive);
        bte_disp=(TextView)findViewById(R.id.bte_disp);
        bte_maxtorque=(TextView)findViewById(R.id.bte_max_torq);
        bte_maxspeed=(TextView)findViewById(R.id.bte_topspeed);
        bte_maxpow=(TextView)findViewById(R.id.bte_max_pow);
        bte_acceleration=(TextView)findViewById(R.id.bte_acceleration);
        bte_gearno=(TextView)findViewById(R.id.bte_gear);
        bte_cylno=(TextView)findViewById(R.id.bte_cyl_no);
        bte_transtype=(TextView)findViewById(R.id.bte_transtype);
        bte_clutchtype=(TextView)findViewById(R.id.bte_clutchtype);
        bte_odetails=(TextView)findViewById(R.id.bte_odetails);

        btb_wlsize=(TextView)findViewById(R.id.btb_wlsize);
        btb_trad=(TextView)findViewById(R.id.btb_trad);
        btb_fbtype=(TextView)findViewById(R.id.btb_fbtype);
        btb_rbtype=(TextView)findViewById(R.id.btb_rbtype);
        btb_trtype=(TextView)findViewById(R.id.btb_tyrtype);
        btb_odetails=(TextView)findViewById(R.id.btb_odetails);

        bf_mileage=(TextView)findViewById(R.id.bf_mileage);
        bf_tcap=(TextView)findViewById(R.id.bf_tcap);
        bf_type=(TextView)findViewById(R.id.bf_type);
        bf_enorm=(TextView)findViewById(R.id.bf_enorm);
        bf_odetails=(TextView)findViewById(R.id.bf_odetails);

        bse_batcap=(TextView)findViewById(R.id.bse_batcap);
        bse_speedom=(TextView)findViewById(R.id.bse_speedom);
        bse_odom=(TextView)findViewById(R.id.bse_odom);
        bse_tripm=(TextView)findViewById(R.id.bse_tripm);
        bse_frest=(TextView)findViewById(R.id.bse_frest);
        bse_abs=(TextView)findViewById(R.id.bse_abs);
        bse_ignitype=(TextView)findViewById(R.id.bse_ignitype);
        bse_battype=(TextView)findViewById(R.id.bse_battype);
        bse_prohl=(TextView)findViewById(R.id.bse_prohl);
        bse_twinind=(TextView)findViewById(R.id.bse_twinind);
        bse_odetails=(TextView)findViewById(R.id.bse_odetails);



    }

    public void fill_model_db(View view)
    {

        tolimitString=toLimit.getText().toString();
        fromlimitString=fromLimit.getText().toString();

        if(tolimitString.equals(""))
        {
            toLimit.setError("Enter A Budget Limit");
            toLimit.requestFocus();

        }
        else
        {
            layoutspinner.setVisibility(View.VISIBLE);
            layoutset.setVisibility(View.INVISIBLE);
            //spinnermodel.setVisibility(View.INVISIBLE);
            BikeView_Budget.BackTaskBrand backTaskBrand = new BikeView_Budget.BackTaskBrand();
            backTaskBrand.execute();

        }


    }


    public class BackTaskBrand extends AsyncTask<Void,Void,Void> {
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
                HttpPost httppost = new HttpPost(base_url+"spinnerload/getbrand_budget.php");
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("carorbike", "CBIKE"));
                nameValuePairs.add(new BasicNameValuePair("toLimit", tolimitString));
                nameValuePairs.add(new BasicNameValuePair("fromLimit", fromlimitString));
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


                    listbrand.add(jsonObject.getString("b_brand"));

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            spinnerbrand = (MaterialSpinner) findViewById(R.id.spinBrand );
            spinnerbrand.setItems(listbrand);

            spinnerbrand.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

                @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                    get_brand=item;
                    //spinnermodel.setVisibility(View.VISIBLE);
                    layoutset.setVisibility(View.INVISIBLE);
                    BikeView_Budget.BackTaskModel backTaskModel1=new BikeView_Budget.BackTaskModel();
                    backTaskModel1.execute();

                }
            });


        }
    }




    public class BackTaskModel extends AsyncTask<Void,Void,Void>{
        ArrayList<String> listmodel1;

        protected void onPreExecute() {
            super.onPreExecute();
            listmodel1= new ArrayList<String>();
            listmodel1.clear();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            InputStream is = null;
            String result = "";
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(base_url+"spinnerload/getmodel_budget.php");
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("brand", get_brand));
                nameValuePairs.add(new BasicNameValuePair("toLimit", tolimitString));
                nameValuePairs.add(new BasicNameValuePair("fromLimit", fromlimitString));
                nameValuePairs.add(new BasicNameValuePair("v_type", "BIKE"));
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

                    listmodel1.add(jsonObject.getString("b_name"));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            spinnermodel = (MaterialSpinner) findViewById(R.id.spinModel );
            spinnermodel.setItems(listmodel1);

            spinnermodel.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

                @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

                    layoutset.setVisibility(View.VISIBLE);
                    get_model=item;
                    filLayout( get_brand, get_model);

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

    private void displayLoader() {
        pDialog = new ProgressDialog(BikeView_Budget.this);
        pDialog.setMessage("Contacting Our Server....Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }

    private void filLayout(String brand,String model) {
        displayLoader();
        JSONObject request = new JSONObject();
        try {

            request.put("b_brand", brand);
            request.put("b_name", model);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, base_url+"bikefetcherfiles/bike_fetcher_bybrand.php", request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pDialog.dismiss();
                        try {

                            if (response.getInt("status") == 1) {

                                b_reldate.setText(Html.fromHtml("Release Date <br><strong><em>"+response.getString("b_reldate")+"</strong></em> "));
                                b_sercost.setText(Html.fromHtml("Service Cost <br><strong><em>"+response.getString("b_sercost")+"</strong></em> "));
                                b_manf.setText(Html.fromHtml("Country of Manufacture <br><strong><em>"+response.getString("b_manf")+"</strong></em> "));
                                b_warper.setText(Html.fromHtml("Warranty Period <br><strong><em>"+response.getString("b_warper")+"</strong></em> "));
                                b_warkm.setText(Html.fromHtml("Warranty KM <br><strong><em>"+response.getString("b_warkm")+"</strong></em> "));
                                b_freeservice.setText(Html.fromHtml("Free Service KM <br><strong><em>"+response.getString("b_freeservice")+"</strong></em> "));
                                b_colour.setText(Html.fromHtml("Available Colors <br><strong><em>"+response.getString("b_colour")+"</strong></em> "));
                                b_pexshow.setText(Html.fromHtml("Ex Showroom Price <br><strong><em>"+response.getString("b_pexshow")+"</strong></em> "));
                                b_ponroad.setText(Html.fromHtml("On Road Price <br><strong><em>"+response.getString("b_ponroad")+"</strong></em> "));
                                b_odetails.setText(Html.fromHtml("Other Details <br><strong><em>"+response.getString("b_odetails")+"</strong></em> "));

                                bd_length.setText(Html.fromHtml("Lenghth(CM) <br><strong><em>"+response.getString("bd_length")+"</strong></em> "));
                                bd_width.setText(Html.fromHtml("Width(CM) <br><strong><em>"+response.getString("bd_width")+"</strong></em> "));
                                bd_height.setText(Html.fromHtml("Height(CM) <br><strong><em>"+response.getString("bd_height")+"</strong></em> "));
                                bd_sadheight.setText(Html.fromHtml("Saddle Height(CM) <br><strong><em>"+response.getString("bd_sadheight")+"</strong></em> "));
                                bd_seatcap.setText(Html.fromHtml("Seatings <br><strong><em>"+response.getString("bd_seatcap")+"</strong></em> "));
                                bd_svolume.setText(Html.fromHtml("Boot Volume <br><strong><em>"+response.getString("bd_svolume")+"</strong></em> "));
                                bd_wbase.setText(Html.fromHtml("Wheel Base <br><strong><em>"+response.getString("bd_wbase")+"</strong></em> "));
                                bd_kweight.setText(Html.fromHtml("Kerb Weight(KG) <br><strong><em>"+response.getString("bd_kweight")+"</strong></em> "));
                                bd_ridetype.setText(Html.fromHtml("Ride Type <br><strong><em>"+response.getString("bd_ridetype")+"</strong></em> "));
                                bd_odetails.setText(Html.fromHtml("Other Details <br><strong><em>"+response.getString("bd_odetails")+"</strong></em> "));

                                bte_type.setText(Html.fromHtml("Engine Type <br><strong><em>"+response.getString("bte_type")+"</strong></em> "));
                                bte_startm.setText(Html.fromHtml("Start Mode <br><strong><em>"+response.getString("bte_startm")+"</strong></em> "));
                                bte_coolsys.setText(Html.fromHtml("Cool System <br><strong><em>"+response.getString("bte_coolsys")+"</strong></em> "));
                                bte_drive.setText(Html.fromHtml("Drive Type <br><strong><em>"+response.getString("bte_drive")+"</strong></em> "));
                                bte_disp.setText(Html.fromHtml("Displacement(CC) <br><strong><em>"+response.getString("bte_disp")+"</strong></em> "));
                                bte_maxtorque.setText(Html.fromHtml("Max Torque(RPM) <br><strong><em>"+response.getString("bte_maxtorque")+"</strong></em> "));
                                bte_maxspeed.setText(Html.fromHtml("Max Speed(KMPH) <br><strong><em>"+response.getString("bte_maxspeed")+"</strong></em> "));
                                bte_maxpow.setText(Html.fromHtml("Max Power <br><strong><em>"+response.getString("bte_maxpow")+"</strong></em> "));
                                bte_acceleration.setText(Html.fromHtml("Acceleration <br><strong><em>"+response.getString("bte_acceleration")+"</strong></em> "));
                                bte_gearno.setText(Html.fromHtml("No: of Gears <br><strong><em>"+response.getString("bte_gearno")+"</strong></em> "));
                                bte_cylno.setText(Html.fromHtml("No: of Cylinders <br><strong><em>"+response.getString("bte_cylno")+"</strong></em> "));
                                bte_transtype.setText(Html.fromHtml("Transmission Type <br><strong><em>"+response.getString("bte_transtype")+"</strong></em> "));
                                bte_clutchtype.setText(Html.fromHtml("Clutch Type <br><strong><em>"+response.getString("bte_clutchtype")+"</strong></em> "));
                                bte_odetails.setText(Html.fromHtml("Other Details <br><strong><em>"+response.getString("bte_odetails")+"</strong></em> "));

                                btb_wlsize.setText(Html.fromHtml("Wheel Size <br><strong><em>"+response.getString("btb_wlsize")+"</strong></em> "));
                                btb_trad.setText(Html.fromHtml("Tyre Radius <br><strong><em>"+response.getString("btb_trad")+"</strong></em> "));
                                btb_fbtype.setText(Html.fromHtml("Front Brake Type <br><strong><em>"+response.getString("btb_fbtype")+"</strong></em> "));
                                btb_rbtype.setText(Html.fromHtml("Rear Brake Type <br><strong><em>"+response.getString("btb_rbtype")+"</strong></em> "));
                                btb_trtype.setText(Html.fromHtml("Tyre Type <br><strong><em>"+response.getString("btb_trtype")+"</strong></em> "));
                                btb_odetails.setText(Html.fromHtml("Other Details <br><strong><em>"+response.getString("btb_odetails")+"</strong></em> "));

                                bf_mileage.setText(Html.fromHtml("Mileage <br><strong><em>"+response.getString("bf_mileage")+"</strong></em> "));
                                bf_tcap.setText(Html.fromHtml("Tank Capacity <br><strong><em>"+response.getString("bf_tcap")+"</strong></em> "));
                                bf_type.setText(Html.fromHtml("Fuel Type <br><strong><em>"+response.getString("bf_type")+"</strong></em> "));
                                bf_enorm.setText(Html.fromHtml("Emmission Norm <br><strong><em>"+response.getString("bf_enorm")+"</strong></em> "));
                                bf_odetails.setText(Html.fromHtml("Other Details <br><strong><em>"+response.getString("bf_odetails")+"</strong></em> "));

                                bse_batcap.setText(Html.fromHtml("Battery Capacity <br><strong><em>"+response.getString("bse_batcap")+"</strong></em> "));
                                bse_speedom.setText(Html.fromHtml("Speedometer Type <br><strong><em>"+response.getString("bse_speedom")+"</strong></em> "));
                                bse_odom.setText(Html.fromHtml("Odometer Type <br><strong><em>"+response.getString("bse_odom")+"</strong></em> "));
                                bse_tripm.setText(Html.fromHtml("Tripmeter <br><strong><em>"+response.getString("bse_tripm")+"</strong></em> "));
                                bse_frest.setText(Html.fromHtml("Foot Rest <br><strong><em>"+response.getString("bse_frest")+"</strong></em> "));
                                bse_abs.setText(Html.fromHtml("ABS <br><strong><em>"+response.getString("bse_abs")+"</strong></em> "));
                                bse_ignitype.setText(Html.fromHtml("Ignition Type <br><strong><em>"+response.getString("bse_ignitype")+"</strong></em> "));
                                bse_battype.setText(Html.fromHtml("Battery Type <br><strong><em>"+response.getString("bse_battype")+"</strong></em> "));
                                bse_prohl.setText(Html.fromHtml("Projector Head Light <br><strong><em>"+response.getString("bse_prohl")+"</strong></em> "));
                                bse_twinind.setText(Html.fromHtml("Twin Indicators <br><strong><em>"+response.getString("bse_twinind")+"</strong></em> "));
                                bse_odetails.setText(Html.fromHtml("Other Details <br><strong><em>"+response.getString("bse_odetails")+"</strong></em> "));


                            }else{
                                Toast.makeText(getApplicationContext(),
                                        response.getString("message"), Toast.LENGTH_SHORT).show();
                                layoutset.setVisibility(View.INVISIBLE);
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
    public void locate_near_showroom(View view) {
        final SharaedPrefernceConfig config;
        config=new SharaedPrefernceConfig(getApplicationContext());
        if(config.readStatus()) {
            Intent i = new Intent(getApplicationContext(), NearShowroom.class);
            i.putExtra("v_type","BIKE");
            i.putExtra("v_brand",get_brand);
            i.putExtra("v_name",get_model);
            i.putExtra("u_id",config.readLoggedUser()[0]);
            i.putExtra("u_name",config.readLoggedUser()[1]);
            startActivity(i);
        }
        else
        {

            Toast.makeText(getApplicationContext(),"You are not logged in. Login to Continue...",Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), login_activity.class);
            i.putExtra("utype","user");
            startActivity(i);
            finish();
        }
    }

}
