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
    public void writeLoggedUser(String uname)
    {
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString(context.getResources().getString(R.string.loggeduser),uname);
        editor.commit();

    }
    public boolean readStatus()
    {
        boolean status=false;
        status=sharedPreferences.getBoolean(context.getResources().getString(R.string.login_preference_status),false);
        return status;
    }

    public String readLoggedUser () {

        String uname=sharedPreferences.getString(context.getResources().getString(R.string.loggeduser),"null");
        return uname;
    }
}
