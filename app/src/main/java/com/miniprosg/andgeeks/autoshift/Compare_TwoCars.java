package com.miniprosg.andgeeks.autoshift;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
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

public class Compare_TwoCars extends AppCompatActivity {

    TextView 
            c_manf1,
            c_warper1,
            c_warkm1,
            c_colour1,
            c_pexshow1,
            c_ponroad1,
    
            cd_bodytype1,
            cd_length1,
            cd_width1,
            cd_height1,
            cd_kweight1,
            cd_gweight1,
            cd_seatcap1,
            cd_volume1,
    
            cte_type1,
            cte_gear1,
            cte_desc1,
            cte_disp1,
            cte_cyl_no1,
            cte_max_pow1,
            cte_max_torq1,
            cte_topspeed1,
            cte_acceleration1,
    
            cbs_fbtype1,
            cbs_rbtype1,
            cbs_strtype1,
            cbs_trad1,
    
            cf_mileage1,
            cf_type1,
            cf_tcap1,
    
            cs_antilock1,
            cs_bassist1,
            cs_slock1,
            cs_clock1,
            cs_psensor1,
            cs_alarm1,
            cs_dabag1,
            cs_pabag1;

    TextView
            c_manf2,
            c_warper2,
            c_warkm2,
            c_colour2,
            c_pexshow2,
            c_ponroad2,

            cd_bodytype2,
            cd_length2,
            cd_width2,
            cd_height2,
            cd_kweight2,
            cd_gweight2,
            cd_seatcap2,
            cd_volume2,

            cte_type2,
            cte_gear2,
            cte_desc2,
            cte_disp2,
            cte_cyl_no2,
            cte_max_pow2,
            cte_max_torq2,
            cte_topspeed2,
            cte_acceleration2,

            cbs_fbtype2,
            cbs_rbtype2,
            cbs_strtype2,
            cbs_trad2,

            cf_mileage2,
            cf_type2,
            cf_tcap2,

            cs_antilock2,
            cs_bassist2,
            cs_slock2,
            cs_clock2,
            cs_psensor2,
            cs_alarm2,
            cs_dabag2,
            cs_pabag2;

    LinearLayout set1,set2;

    String get_brand1,get_brand2,get_model1,get_model2;

    MaterialSpinner spinnerbrand1,spinnermodel1,spinnerbrand2,spinnermodel2;

    PredifValues predifValues=new PredifValues();
    String base_url=predifValues.returnipaddressurl();
    private ProgressDialog pDialog;
    InputStream inputStream=null;

    ArrayList<String> brandList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_twocars);


        set1=(LinearLayout)findViewById(R.id.linear_set1);
        set2=(LinearLayout)findViewById(R.id.linear_set2);

                c_manf1=(TextView)findViewById(R.id.c_manf1);
                c_warper1=(TextView)findViewById(R.id.c_warper1);
                c_warkm1=(TextView)findViewById(R.id.c_warkm1);
                c_colour1=(TextView)findViewById(R.id.c_colour1);
                c_pexshow1=(TextView)findViewById(R.id.c_pexshow1);
                c_ponroad1=(TextView)findViewById(R.id.c_ponroad1);

                cd_bodytype1=(TextView)findViewById(R.id.cd_bodytype1);
                cd_length1=(TextView)findViewById(R.id.cd_length1);
                cd_width1=(TextView)findViewById(R.id.cd_width1);
                cd_height1=(TextView)findViewById(R.id.cd_height1);
                cd_kweight1=(TextView)findViewById(R.id.cd_kweight1);
                cd_gweight1=(TextView)findViewById(R.id.cd_gweight1);
                cd_seatcap1=(TextView)findViewById(R.id.cd_seatcap1);
                cd_volume1=(TextView)findViewById(R.id.cd_volume1);

                cte_type1=(TextView)findViewById(R.id.cte_type1);
                cte_gear1=(TextView)findViewById(R.id.cte_gear1);
                cte_desc1=(TextView)findViewById(R.id.cte_desc1);
                cte_disp1=(TextView)findViewById(R.id.cte_disp1);
                cte_cyl_no1=(TextView)findViewById(R.id.cte_cyl_no1);
                cte_max_pow1=(TextView)findViewById(R.id.cte_max_pow1);
                cte_max_torq1=(TextView)findViewById(R.id.cte_max_torq1);
                cte_topspeed1=(TextView)findViewById(R.id.cte_topspeed1);
                cte_acceleration1=(TextView)findViewById(R.id.cte_acceleration1);

                cbs_fbtype1=(TextView)findViewById(R.id.cbs_fbtype1);
                cbs_rbtype1=(TextView)findViewById(R.id.cbs_rbtype1);
                cbs_strtype1=(TextView)findViewById(R.id.cbs_strtype1);
                cbs_trad1=(TextView)findViewById(R.id.cbs_trad1);

                cf_mileage1=(TextView)findViewById(R.id.cf_mileage1);
                cf_type1=(TextView)findViewById(R.id.cf_type1);
                cf_tcap1=(TextView)findViewById(R.id.cf_tcap1);

                cs_antilock1=(TextView)findViewById(R.id.cs_antilock1);
                cs_bassist1=(TextView)findViewById(R.id.cs_bassist1);
                cs_slock1=(TextView)findViewById(R.id.cs_slock1);
                cs_clock1=(TextView)findViewById(R.id.cs_clock1);
                cs_psensor1=(TextView)findViewById(R.id.cs_psensor1);
                cs_alarm1=(TextView)findViewById(R.id.cs_alarm1);
                cs_dabag1=(TextView)findViewById(R.id.cs_dabag1);
                cs_pabag1=(TextView)findViewById(R.id.cs_pabag1);

        c_manf2=(TextView)findViewById(R.id.c_manf2);
        c_warper2=(TextView)findViewById(R.id.c_warper2);
        c_warkm2=(TextView)findViewById(R.id.c_warkm2);
        c_colour2=(TextView)findViewById(R.id.c_colour2);
        c_pexshow2=(TextView)findViewById(R.id.c_pexshow2);
        c_ponroad2=(TextView)findViewById(R.id.c_ponroad2);

        cd_bodytype2=(TextView)findViewById(R.id.cd_bodytype2);
        cd_length2=(TextView)findViewById(R.id.cd_length2);
        cd_width2=(TextView)findViewById(R.id.cd_width2);
        cd_height2=(TextView)findViewById(R.id.cd_height2);
        cd_kweight2=(TextView)findViewById(R.id.cd_kweight2);
        cd_gweight2=(TextView)findViewById(R.id.cd_gweight2);
        cd_seatcap2=(TextView)findViewById(R.id.cd_seatcap2);
        cd_volume2=(TextView)findViewById(R.id.cd_volume2);

        cte_type2=(TextView)findViewById(R.id.cte_type2);
        cte_gear2=(TextView)findViewById(R.id.cte_gear2);
        cte_desc2=(TextView)findViewById(R.id.cte_desc2);
        cte_disp2=(TextView)findViewById(R.id.cte_disp2);
        cte_cyl_no2=(TextView)findViewById(R.id.cte_cyl_no2);
        cte_max_pow2=(TextView)findViewById(R.id.cte_max_pow2);
        cte_max_torq2=(TextView)findViewById(R.id.cte_max_torq2);
        cte_topspeed2=(TextView)findViewById(R.id.cte_topspeed2);
        cte_acceleration2=(TextView)findViewById(R.id.cte_acceleration2);

        cbs_fbtype2=(TextView)findViewById(R.id.cbs_fbtype2);
        cbs_rbtype2=(TextView)findViewById(R.id.cbs_rbtype2);
        cbs_strtype2=(TextView)findViewById(R.id.cbs_strtype2);
        cbs_trad2=(TextView)findViewById(R.id.cbs_trad2);

        cf_mileage2=(TextView)findViewById(R.id.cf_mileage2);
        cf_type2=(TextView)findViewById(R.id.cf_type2);
        cf_tcap2=(TextView)findViewById(R.id.cf_tcap2);

        cs_antilock2=(TextView)findViewById(R.id.cs_antilock2);
        cs_bassist2=(TextView)findViewById(R.id.cs_bassist2);
        cs_slock2=(TextView)findViewById(R.id.cs_slock2);
        cs_clock2=(TextView)findViewById(R.id.cs_clock2);
        cs_psensor2=(TextView)findViewById(R.id.cs_psensor2);
        cs_alarm2=(TextView)findViewById(R.id.cs_alarm2);
        cs_dabag2=(TextView)findViewById(R.id.cs_dabag2);
        cs_pabag2=(TextView)findViewById(R.id.cs_pabag2);


        Compare_TwoCars.BackTaskBrand backTaskBrand=new Compare_TwoCars.BackTaskBrand();
        backTaskBrand.execute();




    }














    public class BackTaskBrand extends AsyncTask<Void,Void,Void> {
        ArrayList<String> listbrand1,listbrand2;

        protected void onPreExecute() {
            super.onPreExecute();
            listbrand1= new ArrayList<String>();
            listbrand1.clear();

            listbrand2= new ArrayList<String>();
            listbrand2.clear();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            InputStream is = null;
            String result = "";
            set1.setVisibility(View.INVISIBLE);
            set2.setVisibility(View.INVISIBLE);

            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(base_url+"spinnerload/getbrand.php");
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("carorbike", "CCAR"));
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


                        listbrand1.add(jsonObject.getString("c_brand"));
                        listbrand2.add(jsonObject.getString("c_brand"));

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


            spinnerbrand1 = (MaterialSpinner) findViewById(R.id.spinBrand1 );
            spinnerbrand1.setItems(listbrand1);

            spinnerbrand1.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

                @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                    get_brand1=item;
                    set1.setVisibility(View.INVISIBLE);
                    Compare_TwoCars.BackTaskModel1 backTaskModel1=new Compare_TwoCars.BackTaskModel1();
                    backTaskModel1.execute();

                }
            });

            spinnerbrand2 = (MaterialSpinner) findViewById(R.id.spinBrand2 );
            spinnerbrand2.setItems(listbrand1);

            spinnerbrand2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

                @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                    set2.setVisibility(View.INVISIBLE);
                    get_brand2=item;

                    Compare_TwoCars.BackTaskModel2 backTaskModel2=new Compare_TwoCars.BackTaskModel2();
                    backTaskModel2.execute();

                }
            });




//        Spinner s = (Spinner) findViewById(R.id.spin_state);
//        String[] arr = liststate.toArray(new String[liststate.size()]);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arr );
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        s.setAdapter(adapter);


        }
    }




    public class BackTaskModel1 extends AsyncTask<Void,Void,Void>{
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
                HttpPost httppost = new HttpPost(base_url+"spinnerload/getModel.php");
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("brand", get_brand1));
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

                    listmodel1.add(jsonObject.getString("c_name"));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            spinnermodel1 = (MaterialSpinner) findViewById(R.id.spinModel1 );
            spinnermodel1.setItems(listmodel1);

            spinnermodel1.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

                @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

                    set1.setVisibility(View.VISIBLE);
                    get_model1=item;
                    filLayout1( get_brand1, get_model1);

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


    public class BackTaskModel2 extends AsyncTask<Void,Void,Void>{
        ArrayList<String> listmodel2;

        protected void onPreExecute() {
            super.onPreExecute();
            listmodel2= new ArrayList<String>();
            listmodel2.clear();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            InputStream is = null;
            String result = "";
//            set2.setVisibility(View.VISIBLE);
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(base_url+"spinnerload/getModel.php");
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("brand", get_brand2));
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

                    listmodel2.add(jsonObject.getString("c_name"));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            spinnermodel2 = (MaterialSpinner) findViewById(R.id.spinModel2 );
            spinnermodel2.setItems(listmodel2);

            spinnermodel2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

                @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

                    set2.setVisibility(View.VISIBLE);
                    get_model2=item;
                    filLayout2( get_brand2, get_model2);

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
        pDialog = new ProgressDialog(Compare_TwoCars.this);
        pDialog.setMessage("Contacting Our Server....Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }

    private void filLayout1(String brand,String model) {
        displayLoader();
        JSONObject request = new JSONObject();
        try {

            request.put("c_brand", brand);
            request.put("c_name", model);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, base_url+"carfetcherfiles/comparecar_fetcher.php", request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pDialog.dismiss();
                        try {

                            if (response.getInt("status") == 1) {
//                                Toast.makeText(getApplicationContext(),"message", Toast.LENGTH_SHORT).show();

                          //            c_manf1.setText("Country of Manufacture\n"+response.getString("c_manf"));
                                
                                        c_manf1.setText(Html.fromHtml("Country of Manufacture <br><strong><em>"+response.getString("c_manf")+"</strong></em> "));
                                        c_warper1.setText(Html.fromHtml("Warranty Period <br><strong><em>"+response.getString("c_warper")+"</strong></em> "));
                                        c_warkm1.setText(Html.fromHtml("Warranty KMS <br><strong><em>"+response.getString("c_warkm")+"</strong></em> "));
                                        c_colour1.setText(Html.fromHtml("Colour Varients<br><strong><em>"+response.getString("c_colour")+"</strong></em> "));
                                        c_pexshow1.setText(Html.fromHtml("Ex-Showroom Price <br><strong><em>"+response.getString("c_pexshow")+"</strong></em> "));
                                        c_ponroad1.setText(Html.fromHtml("On-Road Price <br><strong><em>"+response.getString("c_ponroad")+"</strong></em> "));

                                        cd_bodytype1.setText(Html.fromHtml("Body Type<br><strong><em>"+response.getString("cd_bodytype")+"</strong></em> "));
                                        cd_length1.setText(Html.fromHtml("Length<br><strong><em>"+response.getString("cd_length")+"</strong></em> "));
                                        cd_width1.setText(Html.fromHtml("Width<br><strong><em>"+response.getString("cd_width")+"</strong></em> "));
                                        cd_height1.setText(Html.fromHtml("Height<br><strong><em>"+response.getString("cd_height")+"</strong></em> "));
                                        cd_kweight1.setText(Html.fromHtml("Kerb Weight<br><strong><em>"+response.getString("cd_kweight")+"</strong></em> "));
                                        cd_gweight1.setText(Html.fromHtml("Gross Weight<br><strong><em>"+response.getString("cd_gweight")+"</strong></em> "));
                                        cd_seatcap1.setText(Html.fromHtml("Seating Capacity<br><strong><em>"+response.getString("cd_seatcap")+"</strong></em> "));
                                        cd_volume1.setText(Html.fromHtml("Cargo Volume<br><strong><em>"+response.getString("cd_volume")+"</strong></em> "));


                                        cte_type1.setText(Html.fromHtml("Transmission Type<br><strong><em>"+response.getString("cte_type")+"</strong></em> "));
                                        cte_gear1.setText(Html.fromHtml("Gear Box Nos:<br><strong><em>"+response.getString("cte_gear")+"</strong></em> "));
                                        cte_desc1.setText(Html.fromHtml("Engine Description<br><strong><em>"+response.getString("cte_desc")+"</strong></em> "));
                                        cte_disp1.setText(Html.fromHtml("Engine Displacement(CC)<br><strong><em>"+response.getString("cte_disp")+"</strong></em> "));
                                        cte_cyl_no1.setText(Html.fromHtml("Cylinder Nos:<br><strong><em>"+response.getString("cte_cyl_no")+"</strong></em> "));
                                        cte_max_pow1.setText(Html.fromHtml("Maximum Power(BHP)<br><strong><em>"+response.getString("cte_max_pow")+"</strong></em> "));
                                        cte_max_torq1.setText(Html.fromHtml("Maximum Torque(RPM)<br><strong><em>"+response.getString("cte_max_torq")+"</strong></em> "));
                                        cte_topspeed1.setText(Html.fromHtml("TopSpeed(0-100)<br><strong><em>"+response.getString("cte_topspeed")+"</strong></em> "));
                                        cte_acceleration1.setText(Html.fromHtml("Acceleration<br><strong><em>"+response.getString("cte_acceleration")+"</strong></em> "));


                                        cbs_fbtype1.setText(Html.fromHtml("Front Brake Type<br><strong><em>"+response.getString("cbs_fbtype")+"</strong></em> "));
                                        cbs_rbtype1.setText(Html.fromHtml("Rear Brake Type<br><strong><em>"+response.getString("cbs_rbtype")+"</strong></em> "));
                                        cbs_strtype1.setText(Html.fromHtml("Streeing Type<br><strong><em>"+response.getString("cbs_strtype")+"</strong></em> "));
                                        cbs_trad1.setText(Html.fromHtml("Turning Radius<br><strong><em>"+response.getString("cbs_trad")+"</strong></em> "));


                                        cf_mileage1.setText(Html.fromHtml("Mileage(KMPL)<br><strong><em>"+response.getString("cf_mileage")+"</strong></em> "));
                                        cf_type1.setText(Html.fromHtml("Fuel Type<br><strong><em>"+response.getString("cf_type")+"</strong></em> "));
                                        cf_tcap1.setText(Html.fromHtml("Tank Capacity(LTRS)<br><strong><em>"+response.getString("cf_tcap")+"</strong></em> "));


                                        cs_antilock1.setText(Html.fromHtml("AntiLock Braking System<br><strong><em>"+response.getString("cs_antilock")+"</strong></em> "));
                                        cs_bassist1.setText(Html.fromHtml("Break Assist<br><strong><em>"+response.getString("cs_bassist")+"</strong></em> "));
                                        cs_slock1.setText(Html.fromHtml("Safety Lock<br><strong><em>"+response.getString("cs_slock")+"</strong></em> "));
                                        cs_clock1.setText(Html.fromHtml("Child Lock<br><strong><em>"+response.getString("cs_clock")+"</strong></em> "));
                                        cs_psensor1.setText(Html.fromHtml("Parking Sensor<br><strong><em>"+response.getString("cs_psensor")+"</strong></em> "));
                                        cs_alarm1.setText(Html.fromHtml("Anti Theft Alarm<br><strong><em>"+response.getString("cs_alarm")+"</strong></em> "));
                                        cs_dabag1.setText(Html.fromHtml("Driver Airbags<br><strong><em>"+response.getString("cs_dabag")+"</strong></em> "));
                                        cs_pabag1.setText(Html.fromHtml("Passenger Airbags<br><strong><em>"+response.getString("cs_pabag")+"</strong></em> "));




                            }else{
                                Toast.makeText(getApplicationContext(),
                                        response.getString("message"), Toast.LENGTH_SHORT).show();
                                set1.setVisibility(View.INVISIBLE);
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



    private void filLayout2(String brand,String model) {
        displayLoader();
        JSONObject request = new JSONObject();
        try {

            request.put("c_brand", brand);
            request.put("c_name", model);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, base_url+"carfetcherfiles/comparecar_fetcher.php", request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pDialog.dismiss();
                        try {

                            if (response.getInt("status") == 1) {


                                c_manf2.setText(Html.fromHtml("Country of Manufacture <br><strong><em>"+response.getString("c_manf")+"</strong></em> "));
                                c_warper2.setText(Html.fromHtml("Warranty Period <br><strong><em>"+response.getString("c_warper")+"</strong></em> "));
                                c_warkm2.setText(Html.fromHtml("Warranty KMS <br><strong><em>"+response.getString("c_warkm")+"</strong></em> "));
                                c_colour2.setText(Html.fromHtml("Colour Varients<br><strong><em>"+response.getString("c_colour")+"</strong></em> "));
                                c_pexshow2.setText(Html.fromHtml("Ex-Showroom Price <br><strong><em>"+response.getString("c_pexshow")+"</strong></em> "));
                                c_ponroad2.setText(Html.fromHtml("On-Road Price <br><strong><em>"+response.getString("c_ponroad")+"</strong></em> "));

                                cd_bodytype2.setText(Html.fromHtml("Body Type<br><strong><em>"+response.getString("cd_bodytype")+"</strong></em> "));
                                cd_length2.setText(Html.fromHtml("Length<br><strong><em>"+response.getString("cd_length")+"</strong></em> "));
                                cd_width2.setText(Html.fromHtml("Width<br><strong><em>"+response.getString("cd_width")+"</strong></em> "));
                                cd_height2.setText(Html.fromHtml("Height<br><strong><em>"+response.getString("cd_height")+"</strong></em> "));
                                cd_kweight2.setText(Html.fromHtml("Kerb Weight<br><strong><em>"+response.getString("cd_kweight")+"</strong></em> "));
                                cd_gweight2.setText(Html.fromHtml("Gross Weight<br><strong><em>"+response.getString("cd_gweight")+"</strong></em> "));
                                cd_seatcap2.setText(Html.fromHtml("Seating Capacity<br><strong><em>"+response.getString("cd_seatcap")+"</strong></em> "));
                                cd_volume2.setText(Html.fromHtml("Cargo Volume<br><strong><em>"+response.getString("cd_volume")+"</strong></em> "));


                                cte_type2.setText(Html.fromHtml("Transmission Type<br><strong><em>"+response.getString("cte_type")+"</strong></em> "));
                                cte_gear2.setText(Html.fromHtml("Gear Box Nos:<br><strong><em>"+response.getString("cte_gear")+"</strong></em> "));
                                cte_desc2.setText(Html.fromHtml("Engine Description<br><strong><em>"+response.getString("cte_desc")+"</strong></em> "));
                                cte_disp2.setText(Html.fromHtml("Engine Displacement(CC)<br><strong><em>"+response.getString("cte_disp")+"</strong></em> "));
                                cte_cyl_no2.setText(Html.fromHtml("Cylinder Nos:<br><strong><em>"+response.getString("cte_cyl_no")+"</strong></em> "));
                                cte_max_pow2.setText(Html.fromHtml("Maximum Power(BHP)<br><strong><em>"+response.getString("cte_max_pow")+"</strong></em> "));
                                cte_max_torq2.setText(Html.fromHtml("Maximum Torque(RPM)<br><strong><em>"+response.getString("cte_max_torq")+"</strong></em> "));
                                cte_topspeed2.setText(Html.fromHtml("TopSpeed(0-200)<br><strong><em>"+response.getString("cte_topspeed")+"</strong></em> "));
                                cte_acceleration2.setText(Html.fromHtml("Acceleration<br><strong><em>"+response.getString("cte_acceleration")+"</strong></em> "));


                                cbs_fbtype2.setText(Html.fromHtml("Front Brake Type<br><strong><em>"+response.getString("cbs_fbtype")+"</strong></em> "));
                                cbs_rbtype2.setText(Html.fromHtml("Rear Brake Type<br><strong><em>"+response.getString("cbs_rbtype")+"</strong></em> "));
                                cbs_strtype2.setText(Html.fromHtml("Streeing Type<br><strong><em>"+response.getString("cbs_strtype")+"</strong></em> "));
                                cbs_trad2.setText(Html.fromHtml("Turning Radius<br><strong><em>"+response.getString("cbs_trad")+"</strong></em> "));


                                cf_mileage2.setText(Html.fromHtml("Mileage(KMPL)<br><strong><em>"+response.getString("cf_mileage")+"</strong></em> "));
                                cf_type2.setText(Html.fromHtml("Fuel Type<br><strong><em>"+response.getString("cf_type")+"</strong></em> "));
                                cf_tcap2.setText(Html.fromHtml("Tank Capacity(LTRS)<br><strong><em>"+response.getString("cf_tcap")+"</strong></em> "));


                                cs_antilock2.setText(Html.fromHtml("AntiLock Braking System<br><strong><em>"+response.getString("cs_antilock")+"</strong></em> "));
                                cs_bassist2.setText(Html.fromHtml("Break Assist<br><strong><em>"+response.getString("cs_bassist")+"</strong></em> "));
                                cs_slock2.setText(Html.fromHtml("Safety Lock<br><strong><em>"+response.getString("cs_slock")+"</strong></em> "));
                                cs_clock2.setText(Html.fromHtml("Child Lock<br><strong><em>"+response.getString("cs_clock")+"</strong></em> "));
                                cs_psensor2.setText(Html.fromHtml("Parking Sensor<br><strong><em>"+response.getString("cs_psensor")+"</strong></em> "));
                                cs_alarm2.setText(Html.fromHtml("Anti Theft Alarm<br><strong><em>"+response.getString("cs_alarm")+"</strong></em> "));
                                cs_dabag2.setText(Html.fromHtml("Driver Airbags<br><strong><em>"+response.getString("cs_dabag")+"</strong></em> "));
                                cs_pabag2.setText(Html.fromHtml("Passenger Airbags<br><strong><em>"+response.getString("cs_pabag")+"</strong></em> "));



                            }else{
                                Toast.makeText(getApplicationContext(),
                                        response.getString("message"), Toast.LENGTH_SHORT).show();

                                set2.setVisibility(View.INVISIBLE);

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



}
