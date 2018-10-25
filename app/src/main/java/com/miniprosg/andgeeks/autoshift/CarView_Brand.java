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

public class CarView_Brand extends AppCompatActivity {
    TextView
            c_reldate,
            c_sercost,
            c_manf,
            c_warper,
            c_warkm,
            c_freeservice,
            c_colour,
            c_pexshow,
            c_ponroad,
            c_odetails,

            cd_bodytype,
            cd_length,
            cd_width,
            cd_height,
            cd_gclear,
            cd_wbase,
            cd_kweight,
            cd_gweight,
            cd_door,
            cd_seatcap,
            cd_volume,
            cd_odetails,

            cte_type,
            cte_gear,
            cte_drive,
            cte_desc,
            cte_disp,
            cte_cyl_no,
            cte_max_pow,
            cte_max_torq,
            cte_topspeed,
            cte_acceleration,
            cte_odetails,

            cbs_fbtype,
            cbs_rbtype,
            cbs_strtype,
            cbs_trad,
            cbs_odetails,

            cf_mileage,
            cf_type,
            cf_tcap,
            cf_enorm,
            cf_odetails,

            cs_antilock,
            cs_bassist,
            cs_slock,
            cs_clock,
            cs_psensor,
            cs_alarm,
            cs_dabag,
            cs_pabag,
            cs_odetails;
    
    LinearLayout layoutset;

    String get_brand,get_model;

    MaterialSpinner spinnerbrand,spinnermodel;

    PredifValues predifValues=new PredifValues();
    String base_url=predifValues.returnipaddressurl();
    private ProgressDialog pDialog;
    InputStream inputStream=null;

    ArrayList<String> brandList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_view_brand);

        layoutset=(LinearLayout)findViewById(R.id.linear_set);

        c_manf=(TextView)findViewById(R.id.c_manf);
        c_warper=(TextView)findViewById(R.id.c_warper);
        c_warkm=(TextView)findViewById(R.id.c_warkm);
        c_colour=(TextView)findViewById(R.id.c_colour);
        c_pexshow=(TextView)findViewById(R.id.c_pexshow);
        c_ponroad=(TextView)findViewById(R.id.c_ponroad);
        c_reldate=(TextView)findViewById(R.id.c_reldate);
        c_sercost=(TextView)findViewById(R.id.c_sercost);
        c_freeservice=(TextView)findViewById(R.id.c_freeservice);
        c_odetails=(TextView)findViewById(R.id.c_odetails);

        cd_bodytype=(TextView)findViewById(R.id.cd_bodytype);
        cd_length=(TextView)findViewById(R.id.cd_length);
        cd_width=(TextView)findViewById(R.id.cd_width);
        cd_height=(TextView)findViewById(R.id.cd_height);
        cd_kweight=(TextView)findViewById(R.id.cd_kweight);
        cd_gweight=(TextView)findViewById(R.id.cd_gweight);
        cd_seatcap=(TextView)findViewById(R.id.cd_seatcap);
        cd_volume=(TextView)findViewById(R.id.cd_volume);
        cd_gclear=(TextView)findViewById(R.id.cd_gclear);
        cd_wbase=(TextView)findViewById(R.id.cd_wbase);
        cd_door=(TextView)findViewById(R.id.cd_door);
        cd_odetails=(TextView)findViewById(R.id.cd_odetails);

        cte_type=(TextView)findViewById(R.id.cte_type);
        cte_gear=(TextView)findViewById(R.id.cte_gear);
        cte_desc=(TextView)findViewById(R.id.cte_desc);
        cte_disp=(TextView)findViewById(R.id.cte_disp);
        cte_cyl_no=(TextView)findViewById(R.id.cte_cyl_no);
        cte_max_pow=(TextView)findViewById(R.id.cte_max_pow);
        cte_max_torq=(TextView)findViewById(R.id.cte_max_torq);
        cte_topspeed=(TextView)findViewById(R.id.cte_topspeed);
        cte_acceleration=(TextView)findViewById(R.id.cte_acceleration);
        cte_drive=(TextView)findViewById(R.id.cte_drive);
        cte_odetails=(TextView)findViewById(R.id.cte_odetails);

        cbs_fbtype=(TextView)findViewById(R.id.cbs_fbtype);
        cbs_rbtype=(TextView)findViewById(R.id.cbs_rbtype);
        cbs_strtype=(TextView)findViewById(R.id.cbs_strtype);
        cbs_trad=(TextView)findViewById(R.id.cbs_trad);
        cbs_odetails=(TextView)findViewById(R.id.cbs_odetails);

        cf_mileage=(TextView)findViewById(R.id.cf_mileage);
        cf_type=(TextView)findViewById(R.id.cf_type);
        cf_tcap=(TextView)findViewById(R.id.cf_tcap);
        cf_enorm=(TextView)findViewById(R.id.cf_enorm);
        cf_odetails=(TextView)findViewById(R.id.cf_odetails);

        cs_antilock=(TextView)findViewById(R.id.cs_antilock);
        cs_bassist=(TextView)findViewById(R.id.cs_bassist);
        cs_slock=(TextView)findViewById(R.id.cs_slock);
        cs_clock=(TextView)findViewById(R.id.cs_clock);
        cs_psensor=(TextView)findViewById(R.id.cs_psensor);
        cs_alarm=(TextView)findViewById(R.id.cs_alarm);
        cs_dabag=(TextView)findViewById(R.id.cs_dabag);
        cs_pabag=(TextView)findViewById(R.id.cs_pabag);
        cs_odetails=(TextView)findViewById(R.id.cs_odetails);



        CarView_Brand.BackTaskBrand backTaskBrand=new CarView_Brand.BackTaskBrand();
        backTaskBrand.execute();


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
            layoutset.setVisibility(View.INVISIBLE);

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


                    listbrand.add(jsonObject.getString("c_brand"));

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
                    get_brand=item;
                    layoutset.setVisibility(View.INVISIBLE);
                    CarView_Brand.BackTaskModel backTaskModel1=new CarView_Brand.BackTaskModel();
                    backTaskModel1.execute();

                }
            });

            



//        Spinner s = (Spinner) findViewById(R.id.spin_state);
//        String[] arr = liststate.toArray(new String[liststate.size()]);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arr );
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        s.setAdapter(adapter);


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
                HttpPost httppost = new HttpPost(base_url+"spinnerload/getModel.php");
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("brand", get_brand));
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
        pDialog = new ProgressDialog(CarView_Brand.this);
        pDialog.setMessage("Contacting Our Server....Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }

    private void filLayout(String brand,String model) {
        displayLoader();
        JSONObject request = new JSONObject();
        try {

            request.put("c_brand", brand);
            request.put("c_name", model);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, base_url+"carfetcherfiles/car_fetcher_bybrand.php", request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pDialog.dismiss();
                        try {

                            if (response.getInt("status") == 1) {
//                                Toast.makeText(getApplicationContext(),"message", Toast.LENGTH_SHORT).show();

                                //            c_manf1.setText("Country of Manufacture\n"+response.getString("c_manf"));

                                c_manf.setText(Html.fromHtml("Country of Manufacture <br><strong><em>"+response.getString("c_manf")+"</strong></em> "));
                                c_warper.setText(Html.fromHtml("Warranty Period <br><strong><em>"+response.getString("c_warper")+"</strong></em> "));
                                c_warkm.setText(Html.fromHtml("Warranty KMS <br><strong><em>"+response.getString("c_warkm")+"</strong></em> "));
                                c_colour.setText(Html.fromHtml("Colour Varients<br><strong><em>"+response.getString("c_colour")+"</strong></em> "));
                                c_pexshow.setText(Html.fromHtml("Ex-Showroom Price <br><strong><em>"+response.getString("c_pexshow")+"</strong></em> "));
                                c_ponroad.setText(Html.fromHtml("On-Road Price <br><strong><em>"+response.getString("c_ponroad")+"</strong></em> "));

                                cd_bodytype.setText(Html.fromHtml("Body Type<br><strong><em>"+response.getString("cd_bodytype")+"</strong></em> "));
                                cd_length.setText(Html.fromHtml("Length<br><strong><em>"+response.getString("cd_length")+"</strong></em> "));
                                cd_width.setText(Html.fromHtml("Width<br><strong><em>"+response.getString("cd_width")+"</strong></em> "));
                                cd_height.setText(Html.fromHtml("Height<br><strong><em>"+response.getString("cd_height")+"</strong></em> "));
                                cd_kweight.setText(Html.fromHtml("Kerb Weight<br><strong><em>"+response.getString("cd_kweight")+"</strong></em> "));
                                cd_gweight.setText(Html.fromHtml("Gross Weight<br><strong><em>"+response.getString("cd_gweight")+"</strong></em> "));
                                cd_seatcap.setText(Html.fromHtml("Seating Capacity<br><strong><em>"+response.getString("cd_seatcap")+"</strong></em> "));
                                cd_volume.setText(Html.fromHtml("Cargo Volume<br><strong><em>"+response.getString("cd_volume")+"</strong></em> "));


                                cte_type.setText(Html.fromHtml("Transmission Type<br><strong><em>"+response.getString("cte_type")+"</strong></em> "));
                                cte_gear.setText(Html.fromHtml("Gear Box Nos:<br><strong><em>"+response.getString("cte_gear")+"</strong></em> "));
                                cte_desc.setText(Html.fromHtml("Engine Description<br><strong><em>"+response.getString("cte_desc")+"</strong></em> "));
                                cte_disp.setText(Html.fromHtml("Engine Displacement(CC)<br><strong><em>"+response.getString("cte_disp")+"</strong></em> "));
                                cte_cyl_no.setText(Html.fromHtml("Cylinder Nos:<br><strong><em>"+response.getString("cte_cyl_no")+"</strong></em> "));
                                cte_max_pow.setText(Html.fromHtml("Maximum Power(BHP)<br><strong><em>"+response.getString("cte_max_pow")+"</strong></em> "));
                                cte_max_torq.setText(Html.fromHtml("Maximum Torque(RPM)<br><strong><em>"+response.getString("cte_max_torq")+"</strong></em> "));
                                cte_topspeed.setText(Html.fromHtml("TopSpeed(0-100)<br><strong><em>"+response.getString("cte_topspeed")+"</strong></em> "));
                                cte_acceleration.setText(Html.fromHtml("Acceleration<br><strong><em>"+response.getString("cte_acceleration")+"</strong></em> "));


                                cbs_fbtype.setText(Html.fromHtml("Front Brake Type<br><strong><em>"+response.getString("cbs_fbtype")+"</strong></em> "));
                                cbs_rbtype.setText(Html.fromHtml("Rear Brake Type<br><strong><em>"+response.getString("cbs_rbtype")+"</strong></em> "));
                                cbs_strtype.setText(Html.fromHtml("Streeing Type<br><strong><em>"+response.getString("cbs_strtype")+"</strong></em> "));
                                cbs_trad.setText(Html.fromHtml("Turning Radius<br><strong><em>"+response.getString("cbs_trad")+"</strong></em> "));


                                cf_mileage.setText(Html.fromHtml("Mileage(KMPL)<br><strong><em>"+response.getString("cf_mileage")+"</strong></em> "));
                                cf_type.setText(Html.fromHtml("Fuel Type<br><strong><em>"+response.getString("cf_type")+"</strong></em> "));
                                cf_tcap.setText(Html.fromHtml("Tank Capacity(LTRS)<br><strong><em>"+response.getString("cf_tcap")+"</strong></em> "));


                                cs_antilock.setText(Html.fromHtml("AntiLock Braking System<br><strong><em>"+response.getString("cs_antilock")+"</strong></em> "));
                                cs_bassist.setText(Html.fromHtml("Break Assist<br><strong><em>"+response.getString("cs_bassist")+"</strong></em> "));
                                cs_slock.setText(Html.fromHtml("Safety Lock<br><strong><em>"+response.getString("cs_slock")+"</strong></em> "));
                                cs_clock.setText(Html.fromHtml("Child Lock<br><strong><em>"+response.getString("cs_clock")+"</strong></em> "));
                                cs_psensor.setText(Html.fromHtml("Parking Sensor<br><strong><em>"+response.getString("cs_psensor")+"</strong></em> "));
                                cs_alarm.setText(Html.fromHtml("Anti Theft Alarm<br><strong><em>"+response.getString("cs_alarm")+"</strong></em> "));
                                cs_dabag.setText(Html.fromHtml("Driver Airbags<br><strong><em>"+response.getString("cs_dabag")+"</strong></em> "));
                                cs_pabag.setText(Html.fromHtml("Passenger Airbags<br><strong><em>"+response.getString("cs_pabag")+"</strong></em> "));
                                
                                c_reldate.setText(Html.fromHtml("Release Date<br><strong><em>"+response.getString("c_reldate")+"</strong></em> "));
                                c_sercost.setText(Html.fromHtml("Service Cost<br><strong><em>"+response.getString("c_sercost")+"</strong></em> "));
                                c_freeservice.setText(Html.fromHtml("No: of Freeservice<br><strong><em>"+response.getString("c_freeservice")+"</strong></em> "));
                                c_odetails.setText(Html.fromHtml("Description<br><strong><em>"+response.getString("c_odetails")+"</strong></em> "));

                                cd_gclear.setText(Html.fromHtml("Ground Clearance(CM)<br><strong><em>"+response.getString("cd_gclear")+"</strong></em> "));
                                cd_wbase.setText(Html.fromHtml("Wheel Base<br><strong><em>"+response.getString("cd_wbase")+"</strong></em> "));
                                cd_door.setText(Html.fromHtml("Door Nos<br><strong><em>"+response.getString("cd_door")+"</strong></em> "));
                                cd_odetails.setText(Html.fromHtml("Description<br><strong><em>"+response.getString("cd_odetails")+"</strong></em> "));

                                cte_drive.setText(Html.fromHtml("Drive Type<br><strong><em>"+response.getString("cte_drive")+"</strong></em> "));
                                cte_odetails.setText(Html.fromHtml("Description<br><strong><em>"+response.getString("cte_odetails")+"</strong></em> "));

                                cbs_odetails.setText(Html.fromHtml("Description<br><strong><em>"+response.getString("cbs_odetails")+"</strong></em> "));

                                cf_enorm.setText(Html.fromHtml("Emission Norm<br><strong><em>"+response.getString("cf_enorm")+"</strong></em> "));
                                cf_odetails.setText(Html.fromHtml("Description<br><strong><em>"+response.getString("cf_odetails")+"</strong></em> "));

                                cs_odetails.setText(Html.fromHtml("Description<br><strong><em>"+response.getString("cs_odetails")+"</strong></em> "));

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


}
