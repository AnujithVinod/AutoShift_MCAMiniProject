package com.miniprosg.andgeeks.autoshift;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdpater extends PagerAdapter {
private Context context;
private  LayoutInflater layoutInflater;

public SliderAdpater(Context context){
    this.context=context;
}


    public int[] slide_images={R.drawable.ico_carob_new,R.drawable.ico_bikeob_new,R.drawable.ico_carob_compare};
    public String[] slide_headings={"Browse Cars","Browse Bikes","Compare Favs"};
    public String[] slide_descs={

            "AutoShift has launched many innovative features to ensure that users get an immersive experience of the car model before visiting a dealer showroom.\n\nThe platform also has used car classifieds wherein users can upload their cars for sale, and find used cars for buying from individuals and used car dealers.",
            "We love what we do and our passion for motorcycles and people is what drives us to constantly better ourselves to help you.\n\nInnovation, Reliability and Client-friendliness are the key values that we hold dear.\n\nAutoShift provides you with all the information you need to make a well informed buying decision.",
            "Choices and Oppinions Make us Human. We understand this. Browse from your choices and start comparing. We value your needs.\n\n\n\nBrowse Your Dreams.\n\n\nClick on Finish and lets start Exploring...."
    };

    @Override
    public boolean isViewFromObject( View view,  Object object) {
        return view==object;
    }
    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public Object instantiateItem( ViewGroup container, int position) {
        layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slidelayout,container,false);
        container.addView(view);
        ImageView slideImageView=(ImageView)view.findViewById(R.id.slideimage);
        TextView slideHeading=(TextView) view.findViewById(R.id.slideheading);
        TextView slideDescription=(TextView) view.findViewById(R.id.slidedescription);
        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);


        return view;
    }

    @Override
    public void destroyItem( ViewGroup container, int position,  Object object) {
        container.removeView((RelativeLayout)object);
    }



}
