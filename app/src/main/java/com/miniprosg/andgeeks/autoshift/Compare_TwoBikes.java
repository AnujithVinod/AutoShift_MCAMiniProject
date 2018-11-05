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

public class Compare_TwoBikes extends AppCompatActivity {

    TextView
            b_manf1,
            b_warper1,
            b_warkm1,
            b_colour1,
            b_pexshow1,
            b_ponroad1,

            bd_ridetype1,
            bd_length1,
            bd_width1,
            bd_height1,
            bd_kweight1,
            bd_sadheight1,
            bd_seatcap1,
            bd_svolume1,

            bte_type1,
            bte_gear1,
            bte_desc1,
            bte_disp1,
            bte_cyl_no1,
            bte_max_pow1,
            bte_max_torq1,
            bte_max_speed1,
            bte_acceleration1,

            btb_fbtype1,
            btb_rbtype1,
            btb_wlsize1,
            btb_trad1,

            bf_mileage1,
            bf_type1,
            bf_tcap1,

            bse_batcap1,
            bse_battype1,
            bse_abs1,
            bse_ignitype1,
            bse_speedom1,
            bse_odom1,
            bse_tripm1,
            bse_twinind1;

    TextView
            b_manf2,
            b_warper2,
            b_warkm2,
            b_colour2,
            b_pexshow2,
            b_ponroad2,

            bd_ridetype2,
            bd_length2,
            bd_width2,
            bd_height2,
            bd_kweight2,
            bd_sadheight2,
            bd_seatcap2,
            bd_svolume2,

            bte_type2,
            bte_gear2,
            bte_desc2,
            bte_disp2,
            bte_cyl_no2,
            bte_max_pow2,
            bte_max_torq2,
            bte_max_speed2,
            bte_acceleration2,

            btb_fbtype2,
            btb_rbtype2,
            btb_wlsize2,
            btb_trad2,

            bf_mileage2,
            bf_type2,
            bf_tcap2,

            bse_batcap2,
            bse_battype2,
            bse_abs2,
            bse_ignitype2,
            bse_speedom2,
            bse_odom2,
            bse_tripm2,
            bse_twinind2;

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
        setContentView(R.layout.activity_compare_twobikes);


        set1=(LinearLayout)findViewById(R.id.linear_set1);
        set2=(LinearLayout)findViewById(R.id.linear_set2);

        b_manf1=(TextView)findViewById(R.id.b_manf1);
        b_warper1=(TextView)findViewById(R.id.b_warper1);
        b_warkm1=(TextView)findViewById(R.id.b_warkm1);
        b_colour1=(TextView)findViewById(R.id.b_colour1);
        b_pexshow1=(TextView)findViewById(R.id.b_pexshow1);
        b_ponroad1=(TextView)findViewById(R.id.b_ponroad1);

        bd_ridetype1=(TextView)findViewById(R.id.bd_ridetype1);
        bd_length1=(TextView)findViewById(R.id.bd_length1);
        bd_width1=(TextView)findViewById(R.id.bd_width1);
        bd_height1=(TextView)findViewById(R.id.bd_height1);
        bd_kweight1=(TextView)findViewById(R.id.bd_kweight1);
        bd_sadheight1=(TextView)findViewById(R.id.bd_sadheight1);
        bd_seatcap1=(TextView)findViewById(R.id.bd_seatcap1);
        bd_svolume1=(TextView)findViewById(R.id.bd_svolume1);

        bte_type1=(TextView)findViewById(R.id.bte_type1);
        bte_gear1=(TextView)findViewById(R.id.bte_gear1);
        bte_desc1=(TextView)findViewById(R.id.bte_desc1);
        bte_disp1=(TextView)findViewById(R.id.bte_disp1);
        bte_cyl_no1=(TextView)findViewById(R.id.bte_cyl_no1);
        bte_max_pow1=(TextView)findViewById(R.id.bte_max_pow1);
        bte_max_torq1=(TextView)findViewById(R.id.bte_max_torq1);
        bte_max_speed1=(TextView)findViewById(R.id.bte_max_speed1);
        bte_acceleration1=(TextView)findViewById(R.id.bte_acceleration1);

        btb_fbtype1=(TextView)findViewById(R.id.btb_fbtype1);
        btb_rbtype1=(TextView)findViewById(R.id.btb_rbtype1);
        btb_wlsize1=(TextView)findViewById(R.id.btb_wlsize1);
        btb_trad1=(TextView)findViewById(R.id.btb_trad1);

        bf_mileage1=(TextView)findViewById(R.id.bf_mileage1);
        bf_type1=(TextView)findViewById(R.id.bf_type1);
        bf_tcap1=(TextView)findViewById(R.id.bf_tcap1);

        bse_batcap1=(TextView)findViewById(R.id.bse_batcap1);
        bse_battype1=(TextView)findViewById(R.id.bse_battype1);
        bse_abs1=(TextView)findViewById(R.id.bse_abs1);
        bse_ignitype1=(TextView)findViewById(R.id.bse_ignitype1);
        bse_speedom1=(TextView)findViewById(R.id.bse_speedom1);
        bse_odom1=(TextView)findViewById(R.id.bse_odom1);
        bse_tripm1=(TextView)findViewById(R.id.bse_tripm1);
        bse_twinind1=(TextView)findViewById(R.id.bse_twinind1);





        b_manf2=(TextView)findViewById(R.id.b_manf2);
        b_warper2=(TextView)findViewById(R.id.b_warper2);
        b_warkm2=(TextView)findViewById(R.id.b_warkm2);
        b_colour2=(TextView)findViewById(R.id.b_colour2);
        b_pexshow2=(TextView)findViewById(R.id.b_pexshow2);
        b_ponroad2=(TextView)findViewById(R.id.b_ponroad2);

        bd_ridetype2=(TextView)findViewById(R.id.bd_ridetype2);
        bd_length2=(TextView)findViewById(R.id.bd_length2);
        bd_width2=(TextView)findViewById(R.id.bd_width2);
        bd_height2=(TextView)findViewById(R.id.bd_height2);
        bd_kweight2=(TextView)findViewById(R.id.bd_kweight2);
        bd_sadheight2=(TextView)findViewById(R.id.bd_sadheight2);
        bd_seatcap2=(TextView)findViewById(R.id.bd_seatcap2);
        bd_svolume2=(TextView)findViewById(R.id.bd_svolume2);

        bte_type2=(TextView)findViewById(R.id.bte_type2);
        bte_gear2=(TextView)findViewById(R.id.bte_gear2);
        bte_desc2=(TextView)findViewById(R.id.bte_desc2);
        bte_disp2=(TextView)findViewById(R.id.bte_disp2);
        bte_cyl_no2=(TextView)findViewById(R.id.bte_cyl_no2);
        bte_max_pow2=(TextView)findViewById(R.id.bte_max_pow2);
        bte_max_torq2=(TextView)findViewById(R.id.bte_max_torq2);
        bte_max_speed2=(TextView)findViewById(R.id.bte_max_speed2);
        bte_acceleration2=(TextView)findViewById(R.id.bte_acceleration2);

        btb_fbtype2=(TextView)findViewById(R.id.btb_fbtype2);
        btb_rbtype2=(TextView)findViewById(R.id.btb_rbtype2);
        btb_wlsize2=(TextView)findViewById(R.id.btb_wlsize2);
        btb_trad2=(TextView)findViewById(R.id.btb_trad2);

        bf_mileage2=(TextView)findViewById(R.id.bf_mileage2);
        bf_type2=(TextView)findViewById(R.id.bf_type2);
        bf_tcap2=(TextView)findViewById(R.id.bf_tcap2);

        bse_batcap2=(TextView)findViewById(R.id.bse_batcap2);
        bse_battype2=(TextView)findViewById(R.id.bse_battype2);
        bse_abs2=(TextView)findViewById(R.id.bse_abs2);
        bse_ignitype2=(TextView)findViewById(R.id.bse_ignitype2);
        bse_speedom2=(TextView)findViewById(R.id.bse_speedom2);
        bse_odom2=(TextView)findViewById(R.id.bse_odom2);
        bse_tripm2=(TextView)findViewById(R.id.bse_tripm2);
        bse_twinind2=(TextView)findViewById(R.id.bse_twinind2);


        Compare_TwoBikes.BackTaskBrand backTaskBrand=new Compare_TwoBikes.BackTaskBrand();
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
                nameValuePairs.add(new BasicNameValuePair("carorbike", "CBIKE"));
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


                    listbrand1.add(jsonObject.getString("b_brand"));
                    listbrand2.add(jsonObject.getString("b_brand"));

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
                    Compare_TwoBikes.BackTaskModel1 backTaskModel1=new Compare_TwoBikes.BackTaskModel1();
                    backTaskModel1.execute();

                }
            });

            spinnerbrand2 = (MaterialSpinner) findViewById(R.id.spinBrand2 );
            spinnerbrand2.setItems(listbrand1);

            spinnerbrand2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

                @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                    set2.setVisibility(View.INVISIBLE);
                    get_brand2=item;

                    Compare_TwoBikes.BackTaskModel2 backTaskModel2=new Compare_TwoBikes.BackTaskModel2();
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

                    listmodel2.add(jsonObject.getString("b_name"));

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
        pDialog = new ProgressDialog(Compare_TwoBikes.this);
        pDialog.setMessage("Contacting Our Server....Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }

    private void filLayout1(String brand,String model) {
        displayLoader();
        JSONObject request = new JSONObject();
        try {

            request.put("b_brand", brand);
            request.put("b_name", model);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, base_url+"bikefetcherfiles/comparebike_fetcher.php", request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pDialog.dismiss();
                        try {

                            if (response.getInt("status") == 1) {
//                                Toast.makeText(getApplicationContext(),"message", Toast.LENGTH_SHORT).show();

                                //            b_manf1.setText("Country of Manufacture\n"+response.getString("b_manf"));

                                b_manf1.setText(Html.fromHtml("Country of Manufacture <br><strong><em>"+response.getString("b_manf")+"</strong></em> "));
                                b_warper1.setText(Html.fromHtml("Warranty Period <br><strong><em>"+response.getString("b_warper")+"</strong></em> "));
                                b_warkm1.setText(Html.fromHtml("Warranty KMS <br><strong><em>"+response.getString("b_warkm")+"</strong></em> "));
                                b_colour1.setText(Html.fromHtml("Colour Varients<br><strong><em>"+response.getString("b_colour")+"</strong></em> "));
                                b_pexshow1.setText(Html.fromHtml("Ex-Showroom Price <br><strong><em>"+response.getString("b_pexshow")+"</strong></em> "));
                                b_ponroad1.setText(Html.fromHtml("On-Road Price <br><strong><em>"+response.getString("b_ponroad")+"</strong></em> "));

                                bd_ridetype1.setText(Html.fromHtml("Ride Type<br><strong><em>"+response.getString("bd_ridetype")+"</strong></em> "));
                                bd_length1.setText(Html.fromHtml("Length<br><strong><em>"+response.getString("bd_length")+"</strong></em> "));
                                bd_width1.setText(Html.fromHtml("Width<br><strong><em>"+response.getString("bd_width")+"</strong></em> "));
                                bd_height1.setText(Html.fromHtml("Height<br><strong><em>"+response.getString("bd_height")+"</strong></em> "));
                                bd_kweight1.setText(Html.fromHtml("Kerb Weight<br><strong><em>"+response.getString("bd_kweight")+"</strong></em> "));
                                bd_sadheight1.setText(Html.fromHtml("Saddle height<br><strong><em>"+response.getString("bd_sadheight")+"</strong></em> "));
                                bd_seatcap1.setText(Html.fromHtml("Seating Capacity<br><strong><em>"+response.getString("bd_seatcap")+"</strong></em> "));
                                bd_svolume1.setText(Html.fromHtml("Cargo Volume<br><strong><em>"+response.getString("bd_svolume")+"</strong></em> "));


                                bte_type1.setText(Html.fromHtml("Transmission Type<br><strong><em>"+response.getString("bte_transtype")+"</strong></em> "));
                                bte_gear1.setText(Html.fromHtml("Gear Box Nos:<br><strong><em>"+response.getString("bte_gearno")+"</strong></em> "));
                                bte_desc1.setText(Html.fromHtml("Engine Description<br><strong><em>"+response.getString("bte_type")+"</strong></em> "));
                                bte_disp1.setText(Html.fromHtml("Engine Displacement(CC)<br><strong><em>"+response.getString("bte_disp")+"</strong></em> "));
                                bte_cyl_no1.setText(Html.fromHtml("Cylinder Nos:<br><strong><em>"+response.getString("bte_cylno")+"</strong></em> "));
                                bte_max_pow1.setText(Html.fromHtml("Maximum Power(BHP)<br><strong><em>"+response.getString("bte_maxpow")+"</strong></em> "));
                                bte_max_torq1.setText(Html.fromHtml("Maximum Torque(RPM)<br><strong><em>"+response.getString("bte_maxtorque")+"</strong></em> "));
                                bte_max_speed1.setText(Html.fromHtml("TopSpeed(0-100)<br><strong><em>"+response.getString("bte_maxspeed")+"</strong></em> "));
                                bte_acceleration1.setText(Html.fromHtml("Acceleration<br><strong><em>"+response.getString("bte_acceleration")+"</strong></em> "));


                                btb_fbtype1.setText(Html.fromHtml("Front Brake Type<br><strong><em>"+response.getString("btb_fbtype")+"</strong></em> "));
                                btb_rbtype1.setText(Html.fromHtml("Rear Brake Type<br><strong><em>"+response.getString("btb_rbtype")+"</strong></em> "));
                                btb_wlsize1.setText(Html.fromHtml("Wheel Size<br><strong><em>"+response.getString("btb_wlsize")+"</strong></em> "));
                                btb_trad1.setText(Html.fromHtml("Turning Radius<br><strong><em>"+response.getString("btb_trad")+"</strong></em> "));


                                bf_mileage1.setText(Html.fromHtml("Mileage(KMPL)<br><strong><em>"+response.getString("bf_mileage")+"</strong></em> "));
                                bf_type1.setText(Html.fromHtml("Fuel Type<br><strong><em>"+response.getString("bf_type")+"</strong></em> "));
                                bf_tcap1.setText(Html.fromHtml("Tank Capacity(LTRS)<br><strong><em>"+response.getString("bf_tcap")+"</strong></em> "));


                                bse_batcap1.setText(Html.fromHtml("Battery Capacity(V)<br><strong><em>"+response.getString("bse_batcap")+"</strong></em> "));
                                bse_battype1.setText(Html.fromHtml("Battery Type<br><strong><em>"+response.getString("bse_battype")+"</strong></em> "));
                                bse_abs1.setText(Html.fromHtml("ABS<br><strong><em>"+response.getString("bse_abs")+"</strong></em> "));
                                bse_ignitype1.setText(Html.fromHtml("Ignition Type<br><strong><em>"+response.getString("bse_ignitype")+"</strong></em> "));
                                bse_speedom1.setText(Html.fromHtml("Speedometer Type<br><strong><em>"+response.getString("bse_speedom")+"</strong></em> "));
                                bse_odom1.setText(Html.fromHtml("Odometer Type<br><strong><em>"+response.getString("bse_odom")+"</strong></em> "));
                                bse_tripm1.setText(Html.fromHtml("Tripmeter<br><strong><em>"+response.getString("bse_tripm")+"</strong></em> "));
                                bse_twinind1.setText(Html.fromHtml("Twin Indicator<br><strong><em>"+response.getString("bse_twinind")+"</strong></em> "));




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

            request.put("b_brand", brand);
            request.put("b_name", model);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, base_url+"bikefetcherfiles/comparebike_fetcher.php", request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pDialog.dismiss();
                        try {

                            if (response.getInt("status") == 1) {


                                b_manf2.setText(Html.fromHtml("Country of Manufacture <br><strong><em>"+response.getString("b_manf")+"</strong></em> "));
                                b_warper2.setText(Html.fromHtml("Warranty Period <br><strong><em>"+response.getString("b_warper")+"</strong></em> "));
                                b_warkm2.setText(Html.fromHtml("Warranty KMS <br><strong><em>"+response.getString("b_warkm")+"</strong></em> "));
                                b_colour2.setText(Html.fromHtml("Colour Varients<br><strong><em>"+response.getString("b_colour")+"</strong></em> "));
                                b_pexshow2.setText(Html.fromHtml("Ex-Showroom Price <br><strong><em>"+response.getString("b_pexshow")+"</strong></em> "));
                                b_ponroad2.setText(Html.fromHtml("On-Road Price <br><strong><em>"+response.getString("b_ponroad")+"</strong></em> "));

                                bd_ridetype2.setText(Html.fromHtml("Ride Type<br><strong><em>"+response.getString("bd_ridetype")+"</strong></em> "));
                                bd_length2.setText(Html.fromHtml("Length<br><strong><em>"+response.getString("bd_length")+"</strong></em> "));
                                bd_width2.setText(Html.fromHtml("Width<br><strong><em>"+response.getString("bd_width")+"</strong></em> "));
                                bd_height2.setText(Html.fromHtml("Height<br><strong><em>"+response.getString("bd_height")+"</strong></em> "));
                                bd_kweight2.setText(Html.fromHtml("Kerb Weight<br><strong><em>"+response.getString("bd_kweight")+"</strong></em> "));
                                bd_sadheight2.setText(Html.fromHtml("Saddle height<br><strong><em>"+response.getString("bd_sadheight")+"</strong></em> "));
                                bd_seatcap2.setText(Html.fromHtml("Seating Capacity<br><strong><em>"+response.getString("bd_seatcap")+"</strong></em> "));
                                bd_svolume2.setText(Html.fromHtml("Cargo Volume<br><strong><em>"+response.getString("bd_svolume")+"</strong></em> "));


                                bte_type2.setText(Html.fromHtml("Transmission Type<br><strong><em>"+response.getString("bte_transtype")+"</strong></em> "));
                                bte_gear2.setText(Html.fromHtml("Gear Box Nos:<br><strong><em>"+response.getString("bte_gearno")+"</strong></em> "));
                                bte_desc2.setText(Html.fromHtml("Engine Description<br><strong><em>"+response.getString("bte_type")+"</strong></em> "));
                                bte_disp2.setText(Html.fromHtml("Engine Displacement(CC)<br><strong><em>"+response.getString("bte_disp")+"</strong></em> "));
                                bte_cyl_no2.setText(Html.fromHtml("Cylinder Nos:<br><strong><em>"+response.getString("bte_cylno")+"</strong></em> "));
                                bte_max_pow2.setText(Html.fromHtml("Maximum Power(BHP)<br><strong><em>"+response.getString("bte_maxpow")+"</strong></em> "));
                                bte_max_torq2.setText(Html.fromHtml("Maximum Torque(RPM)<br><strong><em>"+response.getString("bte_maxtorque")+"</strong></em> "));
                                bte_max_speed2.setText(Html.fromHtml("TopSpeed(0-100)<br><strong><em>"+response.getString("bte_maxspeed")+"</strong></em> "));
                                bte_acceleration2.setText(Html.fromHtml("Acceleration<br><strong><em>"+response.getString("bte_acceleration")+"</strong></em> "));


                                btb_fbtype2.setText(Html.fromHtml("Front Brake Type<br><strong><em>"+response.getString("btb_fbtype")+"</strong></em> "));
                                btb_rbtype2.setText(Html.fromHtml("Rear Brake Type<br><strong><em>"+response.getString("btb_rbtype")+"</strong></em> "));
                                btb_wlsize2.setText(Html.fromHtml("Wheel Size<br><strong><em>"+response.getString("btb_wlsize")+"</strong></em> "));
                                btb_trad2.setText(Html.fromHtml("Turning Radius<br><strong><em>"+response.getString("btb_trad")+"</strong></em> "));


                                bf_mileage2.setText(Html.fromHtml("Mileage(KMPL)<br><strong><em>"+response.getString("bf_mileage")+"</strong></em> "));
                                bf_type2.setText(Html.fromHtml("Fuel Type<br><strong><em>"+response.getString("bf_type")+"</strong></em> "));
                                bf_tcap2.setText(Html.fromHtml("Tank Capacity(LTRS)<br><strong><em>"+response.getString("bf_tcap")+"</strong></em> "));


                                bse_batcap2.setText(Html.fromHtml("Battery Capacity(V)<br><strong><em>"+response.getString("bse_batcap")+"</strong></em> "));
                                bse_battype2.setText(Html.fromHtml("Battery Type<br><strong><em>"+response.getString("bse_battype")+"</strong></em> "));
                                bse_abs2.setText(Html.fromHtml("ABS<br><strong><em>"+response.getString("bse_abs")+"</strong></em> "));
                                bse_ignitype2.setText(Html.fromHtml("Ignition Type<br><strong><em>"+response.getString("bse_ignitype")+"</strong></em> "));
                                bse_speedom2.setText(Html.fromHtml("Speedometer Type<br><strong><em>"+response.getString("bse_speedom")+"</strong></em> "));
                                bse_odom2.setText(Html.fromHtml("Odometer Type<br><strong><em>"+response.getString("bse_odom")+"</strong></em> "));
                                bse_tripm2.setText(Html.fromHtml("Tripmeter<br><strong><em>"+response.getString("bse_tripm")+"</strong></em> "));
                                bse_twinind2.setText(Html.fromHtml("Twin Indicator<br><strong><em>"+response.getString("bse_twinind")+"</strong></em> "));



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
