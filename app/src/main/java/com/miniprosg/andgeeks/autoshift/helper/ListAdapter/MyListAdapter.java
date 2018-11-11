package com.miniprosg.andgeeks.autoshift.helper.ListAdapter;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.miniprosg.andgeeks.autoshift.R;

public class MyListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] Slname,Slemail,Sladdress,Slphone,Slagent,Slid;

    public MyListAdapter(Activity context, String[] Sname,String[] Semail, String[] Saddress,String[] Sphone, String[] Sagent, String[] Sid) {
        super(context, R.layout.showroom_listview, Sname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.Slname=Sname;
        this.Slemail=Semail;
        this.Sladdress=Saddress;
        this.Slphone=Sphone;
        this.Slagent=Sagent;
        this.Slid=Sid;


    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.showroom_listview, null,true);

        TextView SnameText = (TextView) rowView.findViewById(R.id.lsname);
        TextView SemailText = (TextView) rowView.findViewById(R.id.lsemail);
        TextView SaddressText = (TextView) rowView.findViewById(R.id.lsaddress);
        TextView SagentText = (TextView) rowView.findViewById(R.id.lsagent);
        TextView SphoneText = (TextView) rowView.findViewById(R.id.lsphone);
        TextView SidText = (TextView) rowView.findViewById(R.id.lsid);

        SnameText.setText(Slname[position]);
        SemailText.setText(Slemail[position]);
        SaddressText.setText(Sladdress[position]);
        SagentText.setText(Slagent[position]);
        SphoneText.setText(Slphone[position]);
        SidText.setText(Slid[position]);


        return rowView;

    }
}
