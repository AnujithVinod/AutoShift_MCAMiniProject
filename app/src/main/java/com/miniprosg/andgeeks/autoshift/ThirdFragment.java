package com.miniprosg.andgeeks.autoshift;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class ThirdFragment extends Fragment {
View myview;
    CardView cardBike,cardCar;
//    BottomNavigationView bottomNavigationView;
//    FragmentManager fragmentManager;


    public MainActivity mainActivity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       myview=inflater.inflate(R.layout.third_layout,container,false);

        mainActivity =(MainActivity)getActivity();

        cardBike=(CardView)myview.findViewById(R.id.cardBike);
        cardCar=(CardView)myview.findViewById(R.id.cardCar);

//        cardBike.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getActivity(), Compare_TwoBikes.class);
//                i.putExtra("searchby", "Brand");
//                startActivity(i);
//            }
//        });

        cardCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Compare_TwoCars.class);
                startActivity(i);
            }
        });

        return myview;



    }
//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
////        t1=(TextView)view.findViewById(R.id.textView1);
//        fragmentManager= getFragmentManager();
//        bottomNavigationView = (BottomNavigationView)view.findViewById(R.id.navigation);
//        bottomNavigationView.setOnNavigationItemSelectedListener(
//                new BottomNavigationView.OnNavigationItemSelectedListener() {
//                    @Override
//                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                        switch (item.getItemId()) {
//                            case R.id.nav_cars:
//                                Toast.makeText(getActivity(),"1",Toast.LENGTH_LONG).show();
//                                fragmentManager.beginTransaction().replace(R.id.content_frame,new FirstFragment()).commit();
//                                break;
//                            case R.id.nav_bike:
//                                Toast.makeText(getActivity(),"2",Toast.LENGTH_LONG).show();
//                                fragmentManager.beginTransaction().replace(R.id.content_frame,new SecondFragment()).commit();
//                                break;
//                            case R.id.nav_compare:
//                                Toast.makeText(getActivity(),"3",Toast.LENGTH_LONG).show();
//                                fragmentManager.beginTransaction().replace(R.id.content_frame,new ThirdFragment()).commit();
//                                break;
//                        }
//                        return true;
//                    }
//                });
//
//    }
}
