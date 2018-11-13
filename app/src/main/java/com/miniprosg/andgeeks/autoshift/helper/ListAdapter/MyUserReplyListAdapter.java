package com.miniprosg.andgeeks.autoshift.helper.ListAdapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.miniprosg.andgeeks.autoshift.R;

public class MyUserReplyListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] Ulshr_id,Ulsname,Ulv_id,Ulc_name;


    public MyUserReplyListAdapter(Activity context, String[] Ushr_id,String[] Usname, String[] Uv_id,String[] Uc_name)

    {
        super(context, R.layout.userreply_listview, Usname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.Ulshr_id=Ushr_id;
        this.Ulsname=Usname;
        this.Ulv_id=Uv_id;
        this.Ulc_name=Uc_name;


    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.userreply_listview, null,true);

        TextView ushr_id = (TextView) rowView.findViewById(R.id.ushr_id);
        TextView uv_id = (TextView) rowView.findViewById(R.id.uv_id);
        TextView uc_name = (TextView) rowView.findViewById(R.id.uc_name);
        TextView usname = (TextView) rowView.findViewById(R.id.usname);

        ushr_id.setText(Ulshr_id[position]);
        uv_id.setText(Ulv_id[position]);
        uc_name.setText(Ulc_name[position]);
        usname.setText(Ulsname[position]);

        return rowView;

    }


}
