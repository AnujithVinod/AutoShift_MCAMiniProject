package com.miniprosg.andgeeks.autoshift;

import android.content.Context;
import android.content.SharedPreferences;

public class SharaedPrefernceConfig {
    SharedPreferences sharedPreferences;
    Context context;

    public SharaedPrefernceConfig(Context context){
        this.context=context;
        sharedPreferences=context.getSharedPreferences(context.getResources().getString(R.string.login_preference),Context.MODE_PRIVATE);

    }
    public void writeLoginStatus(boolean status)
    {
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putBoolean(context.getResources().getString(R.string.login_preference_status),status);
        editor.commit();

    }
    public void writeLoggedUser(String uid,String uname,String uemail,String upass,String uphone,String ugender,String udob,String ulocation,String usecans,String utype)
    {
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString(context.getResources().getString(R.string.loggeduid),uid);
        editor.putString(context.getResources().getString(R.string.loggeduname),uname);
        editor.putString(context.getResources().getString(R.string.loggeduemail),uemail);
        editor.putString(context.getResources().getString(R.string.loggedupass),upass);
        editor.putString(context.getResources().getString(R.string.loggeduphone),uphone);
        editor.putString(context.getResources().getString(R.string.loggedugender),ugender);
        editor.putString(context.getResources().getString(R.string.loggedudob),udob);
        editor.putString(context.getResources().getString(R.string.loggedulocation),ulocation);
        editor.putString(context.getResources().getString(R.string.loggedusecans),usecans);
        editor.putString(context.getResources().getString(R.string.loggedutype),utype);
        editor.commit();

    }
    public boolean readStatus()
    {
        boolean status=false;
        status=sharedPreferences.getBoolean(context.getResources().getString(R.string.login_preference_status),false);
        return status;
    }

    public String[] readLoggedUser () {

        String uid=sharedPreferences.getString(context.getResources().getString(R.string.loggeduid),"null");
        String uname=sharedPreferences.getString(context.getResources().getString(R.string.loggeduname),"null");
        String uemail=sharedPreferences.getString(context.getResources().getString(R.string.loggeduemail),"null");
        String uphone=sharedPreferences.getString(context.getResources().getString(R.string.loggeduphone),"null");
        String ugender=sharedPreferences.getString(context.getResources().getString(R.string.loggedugender),"null");
        String udob=sharedPreferences.getString(context.getResources().getString(R.string.loggedudob),"null");
        String ulocation=sharedPreferences.getString(context.getResources().getString(R.string.loggedulocation),"null");
        String usecans=sharedPreferences.getString(context.getResources().getString(R.string.loggedusecans),"null");
        String utype=sharedPreferences.getString(context.getResources().getString(R.string.loggedutype),"null");
        String[] logged_userdata={uid,uname,uemail,uphone,ugender,udob,ulocation,usecans,utype};

        return logged_userdata;
    }
    public String readLoggedPassword () {


        String upass=sharedPreferences.getString(context.getResources().getString(R.string.loggedupass),"null");
        return upass;
    }
    public void writeOnBoardingStatus(boolean status)
    {
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putBoolean(context.getResources().getString(R.string.onboardingstatus),status);
        editor.commit();

    }

    public boolean readOnBoardingStatus () {

        boolean status=false;
        status=sharedPreferences.getBoolean(context.getResources().getString(R.string.onboardingstatus),false);
        return status;

    }

}
