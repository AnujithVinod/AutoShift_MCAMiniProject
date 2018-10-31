package com.miniprosg.andgeeks.autoshift.helper;

public class GlobalState_Bikes {
    
    
        // TABLE BIKE MAIN

        public String gb_id,
                gb_name,
                gb_brand,
                gb_reldate,
                gb_sercost,
                gb_manf,
                gb_warper,
                gb_warkm,
                gb_freeservice,
                gb_color,
                gb_pexshow,
                gb_ponroad,
                gb_odetails;
        

        // TABLE BREAK AND TYRE

        public String gbtb_id,
                gbtb_wlsize,
                gbtb_fbtype,
                gbtb_rbtype,
                gbtb_tyrrad,
                gbtb_trtype,
                gbtb_wltype,
                gbtb_odetails;


        // TABLE TRANSMISSION AND ENGINE

        public String gbte_acceleration,
                gbte_id,
                gbte_type,
                gbte_startm,
                gbte_coolsys,
                gbte_drive,
                gbte_disp,
                gbte_maxtorque,
                gbte_maxspeed,
                gbte_maxpow,
                gbte_gearno,
                gbte_cylno,
                gbte_transtype,
                gbte_clutchtype,
                gbte_odetails;


        // TABLE BIKE DIMENSION

        public String gbd_id,
                gbd_svolume,
                gbd_length,
                gbd_seatcap,
                gbd_width,
                gbd_height,
                gbd_sadheight,
                gbd_wbase,
                gbd_kweight,
                gbd_ridetype,
                gbd_odetails;


        // TABLE BIKE SAFETY

        public String gbse_id,
                gbse_speedom,
                gbse_tripm,
                gbse_frest,
                gbse_odom,
                gbse_abs,
                gbse_odetails,
                gbse_batcap,
                gbse_ignitype,
                gbse_battype,
                gbse_prohl,
                gbse_twinind;


        // TABLE BIKE FUEL


        public String gbf_id,
                gbf_mileage,
                gbf_tankcap,
                gbf_type,
                gbf_enorm,
                gbf_odetails;



        // TO SET DATA CONENT OF ALL TO EMPTY

        public void ResetValues()
        {

            gb_id="";
            gb_brand="";
            gb_name="";
            gb_reldate="";
            gb_sercost="";
            gb_manf="";
            gb_warper="";
            gb_warkm="";
            gb_freeservice="";
            gb_color="";
            gb_pexshow="";
            gb_ponroad="";
            gb_odetails="";

            gbte_id="";
            gbte_type="";
            gbte_startm="";
            gbte_coolsys="";
            gbte_drive="";
            gbte_disp="";
            gbte_maxtorque="";
            gbte_maxspeed="";
            gbte_maxpow="";
            gbte_gearno="";
            gbte_cylno="";
            gbte_transtype="";
            gbte_clutchtype="";
            gbte_acceleration="";
            gbte_odetails="";

            gbtb_id="";
            gbtb_wlsize="";
            gbtb_fbtype="";
            gbtb_rbtype="";
            gbtb_trtype="";
            gbtb_wltype="";
            gbtb_odetails="";

            gbd_id="";
            gbd_length="";
            gbd_width="";
            gbd_height="";
            gbd_sadheight="";
            gbd_wbase="";
            gbd_seatcap="";
            gbd_svolume="";
            gbd_kweight="";
            gbd_ridetype="";
            gbd_odetails="";

            gbse_id="";
            gbse_speedom="";
            gbse_tripm="";
            gbse_frest="";
            gbse_odom="";
            gbse_abs="";
            gbse_odetails="";
            gbse_batcap="";
            gbse_ignitype="" ;
            gbse_battype="";
            gbse_prohl="";
            gbse_twinind="";
            gbse_odetails="";

            gbf_id="";
            gbf_mileage="";
            gbf_tankcap="";
            gbf_type="";
            gbf_enorm="";
            gbf_odetails="";



        }

    }

