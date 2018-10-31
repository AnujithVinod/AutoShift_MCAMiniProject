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
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.miniprosg.andgeeks.autoshift.helper.PredifValues;

import org.json.JSONException;
import org.json.JSONObject;

import static com.facebook.FacebookSdk.getApplicationContext;

public class Fragment_AddBikeFuel extends Fragment {

    private ProgressDialog pDialog;
    PredifValues predifValues=new PredifValues();
    String base_url=predifValues.returnipaddressurl();

    View myview;
    String  b_name, b_brand, b_reldate, b_colour, b_odetails,b_manf,
            bte_startm, bte_coolsys, bte_drive, bte_transtype, bte_clutchtype, bte_odetails, bte_type,
            btb_fbtype, btb_rbtype, btb_trtype, btb_wltype, btb_odetails,
            bd_odetails,bd_ridetype,
            bse_speedom, bse_tripm, bse_frest, bse_odom, bse_abs, bse_odetails, bse_batcap, bse_ignitype, bse_battype, bse_prohl, bse_twinind,
            bf_type,bf_enorm, bf_odetails,st_fuel="Petrol";




    float   b_sercost,b_warper,b_warkm,b_freeservice,b_pexshow,b_ponroad,
            bte_disp, bte_maxtorque,bte_acceleration, bte_maxspeed, bte_maxpow, bte_gearno, bte_cylno,
            btb_trad,btb_wlsize,
            bd_length, bd_width, bd_height, bd_sadheight, bd_wbase, bd_kweight,bd_seatcap, bd_svolume,
            bf_mileage,bf_tcap;

    int uid;

    EditText efmileage,efcapacity,efenorm,efodetails;
    Button btnback,btnnext;


    public AgentAddBikes agentAddBikes;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.fragment_add_bikefuel,container,false);


        agentAddBikes =(AgentAddBikes) getActivity();
        btnback=(Button)myview.findViewById(R.id.back);
        btnnext=(Button)myview.findViewById(R.id.next);

        efmileage=(EditText)myview.findViewById(R.id.bmileage);
        efcapacity=(EditText)myview.findViewById(R.id.btcap);
        efenorm=(EditText)myview.findViewById(R.id.benorm);
        efodetails=(EditText)myview.findViewById(R.id.bodetails);

        efmileage.setText(agentAddBikes.globalState_bikes.gbf_mileage);
        efcapacity.setText(agentAddBikes.globalState_bikes.gbf_tankcap);
        efenorm.setText(agentAddBikes.globalState_bikes.gbf_enorm);
        efodetails.setText(agentAddBikes.globalState_bikes.gbf_odetails);

        MaterialSpinner spfuelt = (MaterialSpinner) myview.findViewById(R.id.spinn_ft);
        spfuelt.setItems("Petrol", "Electric", "Hybrid");
        spfuelt.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                st_fuel=item;
            }
        });

        // test.setText(agentAddBikes.globalState_bikes.gb_name);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(validateInputs())
                {
                    fillsuperclass();
                    setlocal_fromsuperclass();

                    addbiketoDB();

                }

            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fillsuperclass();

                FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.addbike_frame, new Fragment_AddBikeSafteyElec());
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



        uid=agentAddBikes.loggeduid;

        //Filling String Variables from Global

        b_name=agentAddBikes.globalState_bikes.gb_name;
        b_brand=agentAddBikes.loggedbrand;
        b_reldate=agentAddBikes.globalState_bikes.gb_reldate;
        b_colour=agentAddBikes.globalState_bikes.gb_color;
        b_odetails=agentAddBikes.globalState_bikes.gb_odetails;
        b_manf=agentAddBikes.globalState_bikes.gb_manf;

        btb_fbtype=agentAddBikes.globalState_bikes.gbtb_fbtype;
        btb_rbtype=agentAddBikes.globalState_bikes.gbtb_rbtype;
        btb_odetails=agentAddBikes.globalState_bikes.gbtb_odetails;
        btb_wltype=agentAddBikes.globalState_bikes.gbtb_wltype;
        btb_trtype=agentAddBikes.globalState_bikes.gbtb_trtype;
        
        bte_type=agentAddBikes.globalState_bikes.gbte_type;
        bte_drive=agentAddBikes.globalState_bikes.gbte_drive;
        bte_startm=agentAddBikes.globalState_bikes.gbte_startm;
        bte_odetails=agentAddBikes.globalState_bikes.gbte_odetails;
        bte_coolsys=agentAddBikes.globalState_bikes.gbte_coolsys;
        bte_transtype=agentAddBikes.globalState_bikes.gbte_transtype;
        bte_clutchtype=agentAddBikes.globalState_bikes.gbte_clutchtype;
        

        bd_odetails=agentAddBikes.globalState_bikes.gbd_odetails;
        bd_ridetype=agentAddBikes.globalState_bikes.gbd_ridetype;

        bse_speedom=agentAddBikes.globalState_bikes.gbse_speedom;
        bse_tripm=agentAddBikes.globalState_bikes.gbse_tripm;
        bse_frest=agentAddBikes.globalState_bikes.gbse_frest;
        bse_odom=agentAddBikes.globalState_bikes.gbse_odom;
        bse_abs=agentAddBikes.globalState_bikes.gbse_abs;
        bse_odetails=agentAddBikes.globalState_bikes.gbse_odetails;
        bse_batcap=agentAddBikes.globalState_bikes.gbse_batcap;
        bse_ignitype=agentAddBikes.globalState_bikes.gbse_ignitype;
        bse_battype=agentAddBikes.globalState_bikes.gbse_battype;
        bse_prohl=agentAddBikes.globalState_bikes.gbse_prohl;
        bse_twinind=agentAddBikes.globalState_bikes.gbse_twinind;

        bf_type=agentAddBikes.globalState_bikes.gbf_type;
        bf_enorm=agentAddBikes.globalState_bikes.gbf_enorm;
        bf_odetails=agentAddBikes.globalState_bikes.gbf_odetails;


        //Filling Float Variables from Global

        b_sercost=Float.parseFloat(agentAddBikes.globalState_bikes.gb_sercost);
        b_warper=Float.parseFloat(agentAddBikes.globalState_bikes.gb_warper);
        b_warkm=Float.parseFloat(agentAddBikes.globalState_bikes.gb_warkm);
        b_freeservice=Float.parseFloat(agentAddBikes.globalState_bikes.gb_freeservice);
        b_pexshow=Float.parseFloat(agentAddBikes.globalState_bikes.gb_pexshow);
        b_ponroad=Float.parseFloat(agentAddBikes.globalState_bikes.gb_ponroad);

        btb_trad=Float.parseFloat(agentAddBikes.globalState_bikes.gbtb_tyrrad);
        btb_wlsize=Float.parseFloat(agentAddBikes.globalState_bikes.gbtb_wlsize);

        bte_disp=Float.parseFloat(agentAddBikes.globalState_bikes.gbte_disp);
        bte_gearno=Float.parseFloat(agentAddBikes.globalState_bikes.gbte_gearno);
        bte_cylno=Float.parseFloat(agentAddBikes.globalState_bikes.gbte_cylno);
        bte_maxpow=Float.parseFloat(agentAddBikes.globalState_bikes.gbte_maxpow);
        bte_maxtorque=Float.parseFloat(agentAddBikes.globalState_bikes.gbte_maxtorque);
        bte_maxspeed=Float.parseFloat(agentAddBikes.globalState_bikes.gbte_maxspeed);
        bte_acceleration=Float.parseFloat(agentAddBikes.globalState_bikes.gbte_acceleration);

        bd_length=Float.parseFloat(agentAddBikes.globalState_bikes.gbd_length);
        bd_width=Float.parseFloat(agentAddBikes.globalState_bikes.gbd_width);
        bd_height=Float.parseFloat(agentAddBikes.globalState_bikes.gbd_height);
        bd_sadheight=Float.parseFloat(agentAddBikes.globalState_bikes.gbd_sadheight);
        bd_wbase=Float.parseFloat(agentAddBikes.globalState_bikes.gbd_wbase);
        bd_kweight=Float.parseFloat(agentAddBikes.globalState_bikes.gbd_kweight);
        bd_seatcap=Float.parseFloat(agentAddBikes.globalState_bikes.gbd_seatcap);
        bd_svolume=Float.parseFloat(agentAddBikes.globalState_bikes.gbd_svolume);

        bf_mileage=Float.parseFloat(agentAddBikes.globalState_bikes.gbf_mileage);
        bf_tcap=Float.parseFloat(agentAddBikes.globalState_bikes.gbf_tankcap);


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

        agentAddBikes.globalState_bikes.gbf_mileage=efmileage.getText().toString();
        agentAddBikes.globalState_bikes.gbf_type=st_fuel;
        agentAddBikes.globalState_bikes.gbf_tankcap=efcapacity.getText().toString();
        agentAddBikes.globalState_bikes.gbf_enorm=efenorm.getText().toString();
        agentAddBikes.globalState_bikes.gbf_odetails=efodetails.getText().toString();
    }
    public void addbiketoDB()
    {
        displayLoader();
        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters

            request.put("uid", uid);
            request.put("b_name", b_name);
            request.put("b_brand", b_brand);
            request.put("b_reldate", b_reldate);
            request.put("b_sercost", b_sercost);
            request.put("b_manf", b_manf);
            request.put("b_warper", b_warper);
            request.put("b_warkm", b_warkm);
            request.put("b_freeservice", b_freeservice);
            request.put("b_colour", b_colour);
            request.put("b_pexshow", b_pexshow);
            request.put("b_ponroad", b_ponroad);
            request.put("b_odetails", b_odetails);
                    
            request.put("btb_wlsize", btb_wlsize);
            request.put("btb_fbtype", btb_fbtype);
            request.put("btb_rbtype", btb_rbtype);
            request.put("btb_trad", btb_trad);
            request.put("btb_trtype", btb_trtype);
            //request.put("btb_wltype", btb_wltype);
            request.put("btb_odetails", btb_odetails);
                    
            request.put("bte_acceleration", bte_acceleration);
            request.put("bte_type", bte_type);
            request.put("bte_startm", bte_startm);
            request.put("bte_coolsys", bte_coolsys);
            request.put("bte_drive", bte_drive);
            request.put("bte_disp", bte_disp);
            request.put("bte_maxtorque", bte_maxtorque);
            request.put("bte_maxspeed", bte_maxspeed);
            request.put("bte_maxpow", bte_maxpow);
            request.put("bte_gearno", bte_gearno);
            request.put("bte_cylno", bte_cylno);
            request.put("bte_transtype", bte_transtype);
            request.put("bte_clutchtype", bte_clutchtype);
            request.put("bte_odetails", bte_odetails);

            request.put("bd_svolume", bd_svolume);
            request.put("bd_length", bd_length);
            request.put("bd_seatcap", bd_seatcap);
            request.put("bd_width", bd_width);
            request.put("bd_height", bd_height);
            request.put("bd_sadheight", bd_sadheight);
            request.put("bd_wbase", bd_wbase);
            request.put("bd_kweight", bd_kweight);
            request.put("bd_ridetype", bd_ridetype);
            request.put("bd_odetails", bd_odetails);

            request.put("bse_speedom", bse_speedom);
            request.put("bse_tripm", bse_tripm);
            request.put("bse_frest", bse_frest);
            request.put("bse_odom", bse_odom);
            request.put("bse_abs", bse_abs);
            request.put("bse_odetails", bse_odetails);
            request.put("bse_batcap", bse_batcap);
            request.put("bse_ignitype", bse_ignitype);
            request.put("bse_battype", bse_battype);
            request.put("bse_prohl", bse_prohl);
            request.put("bse_twinind", bse_twinind);

            request.put("bf_mileage", bf_mileage);
            request.put("bf_tcap", bf_tcap);
            request.put("bf_type", bf_type);
            request.put("bf_enorm", bf_enorm);
            request.put("bf_odetails", bf_odetails);
            
            
            //Toast.makeText(getApplicationContext(),request.toString(),Toast.LENGTH_LONG).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, base_url+"bikefetcherfiles/addnewbike.php", request, new Response.Listener<JSONObject>() {
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




}




























//        INSERT INTO tblbike_dimen (b_id, bd_length, bd_width, bd_height, bd_sadheight, bd_seatcap, bd_svolume, bd_wbase, bd_kweight, bd_ridetype, bd_odetails) VALUES
//                                        (".$b_id.", ".$bd_length.", ".$bd_width.", ".$bd_height.", ".$bd_sadheight.", ".$bd_seatcap.", ".$bd_svolume.", ".$bd_wbase.", ".$bd_kweight.", '".$bd_ridetype."', '".$bd_odetails."')
//
//
//        INSERT INTO btblbike_trans_eng (b_id, bte_type, bte_startm, bte_coolsys, bte_drive, bte_disp, bte_maxtorque, bte_maxspeed, bte_maxpow, bte_acceleration, bte_gearno, bte_cylno, bte_transtype, bte_clutchtype, bte_odetails) VALUES
//         (".$b_id.", '".$bte_type."', '".$bte_startm."', '".$bte_coolsys."', '".$bte_drive."', ".$bte_disp.", ".$bte_maxtorque.", ".$bte_maxspeed.", ".$bte_maxpow.", ".$bte_acceleration.", ".$bte_gearno.", ".$bte_cylno.", '".$bte_transtype."', '".$bte_clutchtype."', '".$bte_odetails."')
//


//        INSERT INTO tblbike_fuel (b_id, bf_mileage, bf_tcap, bf_type, bf_enorm, bf_odetails) VALUES (".$b_id.", ".$bf_mileage.", ".$bf_tankcap.", '".$bf_type."', '".$bf_enorm."', '".$bf_odetails."')


//        INSERT INTO tblbike_tyrebrake (b_id, btb_wlsize, btb_trad,btb_fbtype, btb_rbtype, btb_trtype, btb_odetails) VALUES ('".$b_id."', ".$btb_wlsize.", ".$btb_trad.",'".$btb_fbtype."', '".$btb_rbtype."', '".$btb_trtype."', '".$btb_odetails."')



  //      INSERT INTO tblbike_safety_elec (b_id, bse_batcap, bse_speedom, bse_odom, bse_tripm, bse_frest, bse_abs, bse_ignitype, bse_battype, bse_prohl, bse_twinind, bse_odetails)VALUES (".$b_id.", '".$bse_batcap."', '".$bse_speedom."', '".$bse_odom."', '".$bse_tripm."', '".$bse_frest."', '".$bse_abs."', '".$bse_ignitype."', '".$bse_battype."', '".$bse_prohl."', '".$bse_twinind."', '".$bse_odetails."')











