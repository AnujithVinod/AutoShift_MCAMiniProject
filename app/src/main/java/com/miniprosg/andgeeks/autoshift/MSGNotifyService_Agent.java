package com.miniprosg.andgeeks.autoshift;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MSGNotifyService_Agent extends Service {

    Timer mTimer;
    public MSGNotifyService_Agent() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Intent StartServiceIntent=new Intent(getApplicationContext(),this.getClass());
        StartServiceIntent.setPackage(getPackageName());
        startService(StartServiceIntent);
        Toast.makeText(getApplicationContext(),"This is a Service",Toast.LENGTH_LONG).show();

        return START_STICKY;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {

        Intent restartServiceIntent=new Intent(getApplicationContext(),this.getClass());
        restartServiceIntent.setPackage(getPackageName());
        startService(restartServiceIntent);
        super.onTaskRemoved(rootIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mTimer=new Timer();

    }
    TimerTask timerTask=new TimerTask() {
        @Override
        public void run() {
            Log.e("Log","Running");
            notify();
        }
    };

    @Override
    public void onDestroy() {
try{
    mTimer.cancel();
    timerTask.cancel();
}catch (Exception e)
{
    e.printStackTrace();
}
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
