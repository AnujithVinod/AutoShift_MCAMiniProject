package com.miniprosg.andgeeks.autoshift.helper;

import android.app.Application;

public class GlobalState_Cars{

    // TABLE CAR MAIN

    public String gc_name;
    public String gc_brand;
    public String gc_reldate;
    public String gc_manf;
    public String gc_colour;
    public String gc_ofeatures;
    public String gc_sercost;
    public String gc_warranty;
    public String gc_warkm;
    public String gc_freeservice;
    public String gc_pexshow;
    public String gc_ponroad;

//    public float gc_sercost;
//    public float gc_warranty;
//    public float gc_warkm;
//    public float gc_freeservice;
//    public float gc_pexshow;
//    public float gc_ponroad;



    // TABLE BREAK AND STEERING

    public String gcbs_fbtype;
    public String gcbs_rbtype;
    public String gcbs_strtype;
    public String gcbs_odetails;
    public String gcbs_trad;

//    public float gcbs_trad;


    // TABLE TRANSMISSION AND ENGINE

    public String gcte_type;
    public String gcte_gear;
    public String gcte_drive;
    public String gcte_desc;
    public String gcte_odetails;
    public String gcte_disp;
    public String gcte_cyl_no;
    public String gcte_max_pow;
    public String gcte_max_torq;
    public String gcte_topspeed;
    public String gcte_acceleration;

//    public float gcte_disp;
//    public float gcte_cyl_no;
//    public float gcte_max_pow;
//    public float gcte_max_torq;
//    public float gcte_topspeed;
//    public float gcte_acceleration;

    // TABLE CAR DIMENSION

    public String gcd_odetails;
    public String gcd_length;
    public String gcd_width;
    public String gcd_height;
    public String gcd_gclear;
    public String gcd_wbase;
    public String gcd_kweight;
    public String gcd_gweight;
    public String gcd_door;
    public String gcd_seatcap;
    public String gcd_volume;
    public String gcd_bodytype;

//    public float gcd_length;
//    public float gcd_width;
//    public float gcd_height;
//    public float gcd_gclear;
//    public float gcd_wbase;
//    public float gcd_kweight;
//    public float gcd_gweight;
//    public int gcd_door;
//    public int gcd_seatcap;
//    public float gcd_volume;

    // TABLE CAR SAFETY

    public String gcs_antilock;
    public String gcs_bassist;
    public String gcs_slock;
    public String gcs_clock;
    public String gcs_psensor;
    public String gcs_alarm;
    public String gcs_dabag;
    public String gcs_pabag;
    public String gcs_odetails;

    // TABLE CAR FUEL


    public String gcf_type;
    public String gcf_enorm;
    public String gcf_odetails;
    public String gcf_tcap;
    public String gcf_mileage;

//    public float gcf_tcap;
//    public float gcf_mileage;
    
    // TO SET DATA CONENT OF ALL TO EMPTY
    
    public void ResetValues()
    {
        
        gc_name="";
        gc_brand="";
        gc_reldate="";
        gc_sercost="";
        gc_manf="";
        gc_warranty="";
        gc_warkm="";
        gc_freeservice="";
        gc_colour="";
        gc_ofeatures="";
        gc_pexshow="";
        gc_ponroad="";

        gcbs_fbtype="";
        gcbs_rbtype="";
        gcbs_strtype="";
        gcbs_trad="";
        gcbs_odetails="";

        gcte_type="";
        gcte_gear="";
        gcte_drive="";
        gcte_desc="";
        gcte_disp="";
        gcte_cyl_no="";
        gcte_max_pow="";
        gcte_max_torq="";
        gcte_topspeed="";
        gcte_acceleration="";
        gcte_odetails="";

        gcd_bodytype="";
        gcd_length="";
        gcd_width="";
        gcd_height="";
        gcd_gclear="";
        gcd_wbase="";
        gcd_kweight="";
        gcd_gweight="";
        gcd_door="";
        gcd_seatcap="";
        gcd_volume="";
        gcd_odetails="";

        gcs_antilock="";
        gcs_bassist="";
        gcs_slock="";
        gcs_clock="";
        gcs_psensor="";
        gcs_odetails="";
        gcs_alarm="";
        gcs_dabag="";
        gcs_pabag="";

        gcf_mileage="";
        gcf_type="";
        gcf_tcap="";
        gcf_enorm="";
        gcf_odetails="";
        
    }

    public void Set_CarMain(
            String c_name,
            String c_brand,
            String c_reldate,
            String c_sercost,
            String c_manf,
            String c_warranty,
            String c_warkm,
            String c_freeservice,
            String c_colour,
            String c_pexshow,
            String c_ponroad,
            String c_ofeatures)
    {

         gc_name=c_name;
         gc_brand=c_brand;
         gc_reldate=c_reldate;
         gc_sercost=c_sercost;
         gc_manf=c_manf;
         gc_warranty=c_warranty;
         gc_warkm=c_warkm;
         gc_freeservice=c_freeservice;
         gc_colour=c_colour;
         gc_pexshow=c_pexshow;
         gc_ponroad=c_ponroad;
         gc_ofeatures=c_ofeatures;

    }

    public void Set_CarBreakSteering(
            String cbs_fbtype,
            String cbs_rbtype,
            String cbs_strtype,
            String cbs_trad,
            String cbs_odetails)
    {
        gcbs_fbtype=cbs_fbtype;
        gcbs_rbtype=cbs_rbtype;
        gcbs_strtype=cbs_strtype;
        gcbs_trad=cbs_trad;
        gcbs_odetails=cbs_odetails;
    }

    public void Set_CarTransEng(
             String cte_type,
             String cte_gear,
             String cte_drive,
             String cte_desc,
             String cte_disp,
             String cte_cyl_no,
             String cte_max_pow,
             String cte_max_torq,
             String cte_topspeed,
             String cte_acceleration,
             String cte_odetails)
    {
        gcte_type=cte_type;
        gcte_gear=cte_gear;
        gcte_drive=cte_drive;
        gcte_desc=cte_desc;
        gcte_disp=cte_disp;
        gcte_cyl_no=cte_cyl_no;
        gcte_max_pow=cte_max_pow;
        gcte_max_torq=cte_max_torq;
        gcte_topspeed=cte_topspeed;
        gcte_acceleration= cte_acceleration;
        gcte_odetails=cte_odetails;

    }

    public void Set_CarDimension(
            String cd_length
            ,String cd_width
            ,String cd_height
            ,String cd_gclear
            ,String cd_wbase
            ,String cd_kweight
            ,String cd_gweight
            ,String cd_door
            ,String cd_seatcap
            ,String cd_volume
            ,String cd_odetails)
    {
        gcd_length=cd_length;
        gcd_width=cd_width;
        gcd_height=cd_height;
        gcd_gclear=cd_gclear;
        gcd_wbase=cd_wbase;
        gcd_kweight=cd_kweight;
        gcd_gweight=cd_gweight;
        gcd_door=cd_door;
        gcd_seatcap=cd_seatcap;
        gcd_volume=cd_volume;
        gcd_odetails=cd_odetails;
    }

    public void Set_CarSafety(
            String cs_antilock,
            String cs_bassist,
            String cs_slock,
            String cs_clock,
            String cs_psensor,
            String cs_odetails,
            String cs_alarm,
            String cs_dabag,
            String cs_pabag)
    {
        gcs_antilock=cs_antilock;
        gcs_bassist=cs_bassist;
        gcs_slock=cs_slock;
        gcs_clock=cs_clock;
        gcs_psensor=cs_psensor;
        gcs_alarm=cs_alarm;
        gcs_dabag=cs_dabag;
        gcs_pabag=cs_pabag;
        gcs_odetails=cs_odetails;
    }

    public void Set_CarFuel(
            String cf_mileage,
            String cf_type,
            String cf_tcap,
            String cf_enorm,
            String cf_odetails)
    {
        gcf_mileage=cf_mileage;
        gcf_type=cf_type;
        gcf_tcap=cf_tcap;
        gcf_enorm=cf_enorm;
        gcf_odetails=cf_odetails;
    }

public void testdataCarname(String cname)
{
    gc_name=cname;
}



}
