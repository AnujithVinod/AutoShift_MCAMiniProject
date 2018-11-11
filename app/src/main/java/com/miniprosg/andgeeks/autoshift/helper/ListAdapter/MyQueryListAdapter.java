package com.miniprosg.andgeeks.autoshift.helper.ListAdapter;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.miniprosg.andgeeks.autoshift.R;


public class MyQueryListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] Ulname,Ulmsg,Ultime,Uldate,Mlid;

    public MyQueryListAdapter(Activity context, String[] Uname,String[] Umsg, String[] Utime,String[] Udate, String[] Mid)

    {
        super(context, R.layout.query_listview, Uname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.Ulname=Uname;
        this.Ulmsg=Umsg;
        this.Ultime=Utime;
        this.Uldate=Udate;
        this.Mlid=Mid;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.query_listview, null,true);

        TextView UnameText = (TextView) rowView.findViewById(R.id.quname);
        TextView UtimeText = (TextView) rowView.findViewById(R.id.qutime);
        TextView UmsgText = (TextView) rowView.findViewById(R.id.qumsg);
        TextView UdateText = (TextView) rowView.findViewById(R.id.qudate);
        TextView MidText = (TextView) rowView.findViewById(R.id.qmsgid);

        UnameText.setText(Ulname[position]);
        UmsgText.setText(Ulmsg[position]);
        UtimeText.setText(Ultime[position]);
        UdateText.setText(Uldate[position]);
        MidText.setText(Mlid[position]);


        return rowView;

    }

}
