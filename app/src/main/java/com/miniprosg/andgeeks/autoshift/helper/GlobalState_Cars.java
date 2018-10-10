package com.miniprosg.andgeeks.autoshift.helper;

import android.app.Application;

public class GlobalState_Cars{

    // TABLE CAR MAIN

    public String gc_name;
    public String gc_brand;
    public String gc_reldate;
    public float gc_sercost;
    public String gc_manf;
    public float gc_warranty;
    public float gc_warkm;
    public float gc_freeservice;
    public String gc_colour;
    public float gc_pexshow;
    public float gc_ponroad;
    public String gc_ofeatures;


    // TABLE BREAK AND STEERING

    public String gcbs_fbtype;
    public String gcbs_rbtype;
    public String gcbs_strtype;
    public float gcbs_trad;
    public String gcbs_odetails;

    // TABLE TRANSMISSION AND ENGINE

    public String gcte_type;
    public String gcte_gear;
    public String gcte_drive;
    public String gcte_desc;
    public float gcte_disp;
    public float gcte_cyl_no;
    public float gcte_max_pow;
    public float gcte_max_torq;
    public float gcte_topspeed;
    public float gcte_acceleration;
    public String gcte_odetails;

    // TABLE CAR DIMENSION

    public float gcd_length;
    public float gcd_width;
    public float gcd_height;
    public float gcd_gclear;
    public float gcd_wbase;
    public float gcd_kweight;
    public float gcd_gweight;
    public int gcd_door;
    public int gcd_seatcap;
    public float gcd_volume;
    public String gcd_odetails;

    // TABLE CAR SAFETY

    public String gcs_antilock;
    public String gcs_bassist;
    public String gcs_slock;
    public String gcs_clock;
    public String gcs_plock;
    public String gcs_alarm;
    public String gcs_dabag;
    public String gcs_pabag;

    // TABLE CAR FUEL

    public float gcf_mileage;
    public String gcf_type;
    public float gcf_tcap;
    public String gcf_enorm;
    public String gcf_odetails;
    
    // TO SET DATA CONENT OF ALL TO EMPTY
    
    public void ResetValues()
    {
        
        gc_name="";
        gc_brand="";
        gc_reldate="";
        gc_sercost=0;
        gc_manf="";
        gc_warranty=0;
        gc_warkm=0;
        gc_freeservice=0;
        gc_colour="";
        gc_ofeatures="";
        gc_pexshow=0;
        gc_ponroad=0;

        gcbs_fbtype="";
        gcbs_rbtype="";
        gcbs_strtype="";
        gcbs_trad=0;
        gcbs_odetails="";

        gcte_type="";
        gcte_gear="";
        gcte_drive="";
        gcte_desc="";
        gcte_disp=0;
        gcte_cyl_no=0;
        gcte_max_pow=0;
        gcte_max_torq=0;
        gcte_topspeed=0;
        gcte_acceleration=0;
        gcte_odetails="";

        gcd_length=0;
        gcd_width=0;
        gcd_height=0;
        gcd_gclear=0;
        gcd_wbase=0;
        gcd_kweight=0;
        gcd_gweight=0;
        gcd_door=0;
        gcd_seatcap=0;
        gcd_volume=0;
        gcd_odetails="";

        gcs_antilock="";
        gcs_bassist="";
        gcs_slock="";
        gcs_clock="";
        gcs_plock="";
        gcs_alarm="";
        gcs_dabag="";
        gcs_pabag="";

        gcf_mileage=0;
        gcf_type="";
        gcf_tcap=0;
        gcf_enorm="";
        gcf_odetails="";
        
    }

    public void Set_CarMain(
            String c_name,
            String c_brand,
            String c_reldate,
            float c_sercost,
            String c_manf,
            float c_warranty,
            float c_warkm,
            float c_freeservice,
            String c_colour,
            float c_pexshow,
            float c_ponroad,
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
            float cbs_trad,
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
             float cte_disp,
             float cte_cyl_no,
             float cte_max_pow,
             float cte_max_torq,
             float cte_topspeed,
             float cte_acceleration,
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
            float cd_length
            ,float cd_width
            ,float cd_height
            ,float cd_gclear
            ,float cd_wbase
            ,float cd_kweight
            ,float cd_gweight
            ,int cd_door
            ,int cd_seatcap
            ,float cd_volume
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
            String cs_plock,
            String cs_alarm,
            String cs_dabag,
            String cs_pabag)
    {
        gcs_antilock=cs_antilock;
        gcs_bassist=cs_bassist;
        gcs_slock=cs_slock;
        gcs_clock=cs_clock;
        gcs_plock=cs_plock;
        gcs_alarm=cs_alarm;
        gcs_dabag=cs_dabag;
        gcs_pabag=cs_pabag;
    }

    public void Set_CarFuel(
            float cf_mileage,
            String cf_type,
            float cf_tcap,
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
