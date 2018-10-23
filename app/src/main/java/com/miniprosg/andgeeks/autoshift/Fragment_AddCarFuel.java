package com.miniprosg.andgeeks.autoshift;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.miniprosg.andgeeks.autoshift.helper.PredifValues;

import org.json.JSONException;
import org.json.JSONObject;

import static com.facebook.FacebookSdk.getApplicationContext;

public class Fragment_AddCarFuel extends Fragment {

    private ProgressDialog pDialog;
    PredifValues predifValues=new PredifValues();
    String base_url=predifValues.returnipaddressurl();
    
    View myview;
    String  c_name, c_brand, c_reldate, c_colour, c_ofeatures,c_manf,
            cbs_fbtype, cbs_rbtype,cbs_strtype,cbs_odetails,
            cte_type, cte_drive, cte_desc, cte_odetails,
            cd_odetails,cd_bodytype,
            cs_antilock, cs_bassist, cs_slock, cs_clock, cs_psensor, cs_odetails, cs_alarm, cs_dabag, cs_pabag,
            cf_type,cf_enorm, cf_odetails;




    float   c_sercost,c_warranty,c_warkm,c_freeservice,c_pexshow,c_ponroad,
            cbs_trad,
            cte_disp, cte_cyl_no, cte_max_pow, cte_max_torq, cte_topspeed, cte_acceleration, cte_gear,
            cd_length, cd_width, cd_height, cd_gclear, cd_wbase, cd_kweight, cd_gweight, cd_door, cd_seatcap, cd_volume,
            cf_mileage,cf_tcap;

    int uid;

    EditText efmileage,eftype,efcapacity,efenorm,efodetails;
    Button btnback,btnnext;


    public AgentAddCars agentAddCars;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.fragment_add_carfuel,container,false);


        agentAddCars =(AgentAddCars) getActivity();
        btnback=(Button)myview.findViewById(R.id.back);
        btnnext=(Button)myview.findViewById(R.id.next);

        efmileage=(EditText)myview.findViewById(R.id.cmileage);
        eftype=(EditText)myview.findViewById(R.id.cftype);
        efcapacity=(EditText)myview.findViewById(R.id.ctcap);
        efenorm=(EditText)myview.findViewById(R.id.cenorm);
        efodetails=(EditText)myview.findViewById(R.id.codetails);

        efmileage.setText(agentAddCars.globalState_cars.gcf_mileage);
        eftype.setText(agentAddCars.globalState_cars.gcf_type);
        efcapacity.setText(agentAddCars.globalState_cars.gcf_tcap);
        efenorm.setText(agentAddCars.globalState_cars.gcf_enorm);
        efodetails.setText(agentAddCars.globalState_cars.gcf_odetails);

        // test.setText(agentAddCars.globalState_cars.gc_name);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                
                if(validateInputs())
                {
                    fillsuperclass();
                    setlocal_fromsuperclass();

                    addcartoDB();

                }
               
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fillsuperclass();

                FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.addcar_frame, new Fragment_AddCarSaftey());
                fragmentTransaction.commit();
            }
        });

        return myview;
    }
    private boolean validateInputs() {
        if("".equals(efmileage.getText().toString())){
            efmileage.setError("Field cannot be empty");
            efmileage.requestFocus();
            return false;
        }

        if("".equals(eftype.getText().toString())){
            eftype.setError("Field cannot be empty");
            eftype.requestFocus();
            return false;
        }
        if("".equals(efcapacity.getText().toString())){
            efcapacity.setError("Field cannot be empty");
            efcapacity.requestFocus();
            return false;
        }
        if("".equals(efenorm.getText().toString())){
            efenorm.setError("Field cannot be empty");
            efenorm.requestFocus();
            return false;
        }
        if("".equals(efodetails.getText().toString())){
            efodetails.setError("Field cannot be empty");
            efodetails.requestFocus();
            return false;
        }

        return true;
    }
    private void setlocal_fromsuperclass() {



        uid=agentAddCars.loggeduid;

        //Filling String Variables from Global

                c_name=agentAddCars.globalState_cars.gc_name;
                c_brand=agentAddCars.loggedbrand;
                c_reldate=agentAddCars.globalState_cars.gc_reldate;
                c_colour=agentAddCars.globalState_cars.gc_colour;
                c_ofeatures=agentAddCars.globalState_cars.gc_ofeatures;
                c_manf=agentAddCars.globalState_cars.gc_manf;
                        
                cbs_fbtype=agentAddCars.globalState_cars.gcbs_fbtype;
                cbs_rbtype=agentAddCars.globalState_cars.gcbs_rbtype;
                cbs_strtype=agentAddCars.globalState_cars.gcbs_strtype;
                cbs_odetails=agentAddCars.globalState_cars.gcbs_odetails;
                        
                cte_type=agentAddCars.globalState_cars.gcte_type;
                cte_drive=agentAddCars.globalState_cars.gcte_drive;
                cte_desc=agentAddCars.globalState_cars.gcte_desc;
                cte_odetails=agentAddCars.globalState_cars.gcte_odetails;
                        
                cd_odetails=agentAddCars.globalState_cars.gcd_odetails;
                cd_bodytype=agentAddCars.globalState_cars.gcd_bodytype;

                cs_antilock=agentAddCars.globalState_cars.gcs_antilock;
                cs_bassist=agentAddCars.globalState_cars.gcs_bassist;
                cs_slock=agentAddCars.globalState_cars.gcs_slock;
                cs_clock=agentAddCars.globalState_cars.gcs_clock;
                cs_psensor=agentAddCars.globalState_cars.gcs_psensor;
                cs_odetails=agentAddCars.globalState_cars.gcs_odetails;
                cs_alarm=agentAddCars.globalState_cars.gcs_alarm;
                cs_dabag=agentAddCars.globalState_cars.gcs_dabag;
                cs_pabag=agentAddCars.globalState_cars.gcs_pabag;
                        
                cf_type=agentAddCars.globalState_cars.gcf_type;
                cf_enorm=agentAddCars.globalState_cars.gcf_enorm;
                cf_odetails=agentAddCars.globalState_cars.gcf_odetails;


        //Filling Float Variables from Global

                        c_sercost=Float.parseFloat(agentAddCars.globalState_cars.gc_sercost);
                        c_warranty=Float.parseFloat(agentAddCars.globalState_cars.gc_warranty);
                        c_warkm=Float.parseFloat(agentAddCars.globalState_cars.gc_warkm);
                        c_freeservice=Float.parseFloat(agentAddCars.globalState_cars.gc_freeservice);
                        c_pexshow=Float.parseFloat(agentAddCars.globalState_cars.gc_pexshow);
                        c_ponroad=Float.parseFloat(agentAddCars.globalState_cars.gc_ponroad);

                        cbs_trad=Float.parseFloat(agentAddCars.globalState_cars.gcbs_trad);

                        cte_disp=Float.parseFloat(agentAddCars.globalState_cars.gcte_disp);
                        cte_gear=Float.parseFloat(agentAddCars.globalState_cars.gcte_gear);
                        cte_cyl_no=Float.parseFloat(agentAddCars.globalState_cars.gcte_cyl_no);
                        cte_max_pow=Float.parseFloat(agentAddCars.globalState_cars.gcte_max_pow);
                        cte_max_torq=Float.parseFloat(agentAddCars.globalState_cars.gcte_max_torq);
                        cte_topspeed=Float.parseFloat(agentAddCars.globalState_cars.gcte_topspeed);
                        cte_acceleration=Float.parseFloat(agentAddCars.globalState_cars.gcte_acceleration);

                        cd_length=Float.parseFloat(agentAddCars.globalState_cars.gcd_length);
                        cd_width=Float.parseFloat(agentAddCars.globalState_cars.gcd_width);
                        cd_height=Float.parseFloat(agentAddCars.globalState_cars.gcd_height);
                        cd_gclear=Float.parseFloat(agentAddCars.globalState_cars.gcd_gclear);
                        cd_wbase=Float.parseFloat(agentAddCars.globalState_cars.gcd_wbase);
                        cd_kweight=Float.parseFloat(agentAddCars.globalState_cars.gcd_kweight);
                        cd_gweight=Float.parseFloat(agentAddCars.globalState_cars.gcd_gweight);
                        cd_door=Float.parseFloat(agentAddCars.globalState_cars.gcd_door);
                        cd_seatcap=Float.parseFloat(agentAddCars.globalState_cars.gcd_seatcap);
                        cd_volume=Float.parseFloat(agentAddCars.globalState_cars.gcd_volume);

                        cf_mileage=Float.parseFloat(agentAddCars.globalState_cars.gcf_mileage);
                        cf_tcap=Float.parseFloat(agentAddCars.globalState_cars.gcf_tcap);

        
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        super.onViewCreated(view, savedInstanceState);
        //


    }
    private void displayLoader()
    {
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Contacting Our Server....Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }

    private void fillsuperclass() {

        agentAddCars.globalState_cars.gcf_mileage=efmileage.getText().toString();
        agentAddCars.globalState_cars.gcf_type=eftype.getText().toString();
        agentAddCars.globalState_cars.gcf_tcap=efcapacity.getText().toString();
        agentAddCars.globalState_cars.gcf_enorm=efenorm.getText().toString();
        agentAddCars.globalState_cars.gcf_odetails=efodetails.getText().toString();
    }
    public void addcartoDB()
    {
        displayLoader();
        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters

            request.put("uid", uid);

            request.put("c_name", c_name);
            request.put("c_brand", c_brand);
            request.put("c_reldate",c_reldate);
            request.put("c_colour", c_colour);
            request.put("c_ofeatures", c_ofeatures);
            request.put("c_manf", c_manf);
            request.put("c_sercost", c_sercost);
            request.put("c_warranty", c_warranty);
            request.put("c_warkm", c_warkm);
            request.put("c_freeservice", c_freeservice);
            request.put("c_pexshow", c_pexshow);
            request.put("c_ponroad", c_ponroad);

            request.put("cbs_fbtype",cbs_rbtype);
            request.put("cbs_rbtype", cbs_rbtype);
            request.put("cbs_strtype", cbs_strtype);
            request.put("cbs_odetails", cbs_odetails);
            request.put("cbs_trad", cbs_trad);

            request.put("cte_type", cte_type);
            request.put("cte_drive", cte_drive);
            request.put("cte_desc", cte_desc);
            request.put("cte_odetails", cte_odetails);
            request.put("cte_disp", cte_disp);
            request.put("cte_cyl_no",cte_cyl_no);
            request.put("cte_max_pow", cte_max_pow);
            request.put("cte_max_torq", cte_max_torq);
            request.put("cte_topspeed", cte_topspeed);
            request.put("cte_acceleration", cte_acceleration);
            request.put("cte_gear", cte_gear);

            request.put("cd_odetails", cd_odetails);
            request.put("cd_length", cd_length);
            request.put("cd_width", cd_width);
            request.put("cd_height", cd_height);
            request.put("cd_gclear",cd_gclear);
            request.put("cd_wbase", cd_wbase);
            request.put("cd_kweight", cd_kweight);
            request.put("cd_gweight", cd_gweight);
            request.put("cd_door", cd_door);
            request.put("cd_seatcap", cd_seatcap);
            request.put("cd_volume", cd_volume);
            request.put("cd_bodytype", cd_bodytype);

            request.put("cs_antilock", cs_antilock);
            request.put("cs_bassist", cs_bassist);
            request.put("cs_slock", cs_slock);
            request.put("cs_clock",cs_clock);
            request.put("cs_psensor", cs_psensor);
            request.put("cs_odetails", cs_odetails);
            request.put("cs_alarm", cs_alarm);
            request.put("cs_dabag", cs_dabag);
            request.put("cs_pabag", cs_pabag);

            request.put("cf_type", cf_type);
            request.put("cf_enorm", cf_enorm);
            request.put("cf_odetails", cf_odetails);
            request.put("cf_mileage", cf_mileage);
            request.put("cf_tcap", cf_tcap);


            //Toast.makeText(getApplicationContext(),request.toString(),Toast.LENGTH_LONG).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, base_url+"carfetcherfiles/addnewcar.php", request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pDialog.dismiss();
                        try {
                            //Check if user got logged in successfully
                            //Toast.makeText(getApplicationContext(),"HIHIHI",Toast.LENGTH_LONG).show();
                            if (response.getInt("status") == 1) {
                                Toast.makeText(getApplicationContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                getActivity().finish();


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
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsArrayRequest);

    }

    public void SnackbarShow(View view)
    {
        final ScrollView scrollView = (ScrollView)view.findViewById(R.id.sv_first);
        scrollView.findViewById(R.id.bankcardId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //globalState_cars.testdataCarname(test.getText().toString());
                Snackbar snackbar = Snackbar.make(scrollView, "Missing Mandatory Parameters", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
    }


}
