package com.miniprosg.andgeeks.autoshift;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActivityOnBoarding extends AppCompatActivity {
ViewPager nSlideViewPager;
LinearLayout mDotLayout;

private TextView[] mDots;
private SliderAdpater sliderAdpater;
Button nextBtn,backBtn;
int mCurrentPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final SharaedPrefernceConfig config;
        config=new SharaedPrefernceConfig(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mDotLayout=findViewById(R.id.layoutDots);
        nSlideViewPager=findViewById(R.id.slideViewPager);
        sliderAdpater= new SliderAdpater(this);
        nSlideViewPager.setAdapter(sliderAdpater);
        addDotsIndicator(0);
        nSlideViewPager.addOnPageChangeListener(viewListner);
        nextBtn=(Button)findViewById(R.id.btn_next);
        backBtn=(Button)findViewById(R.id.btn_back);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nextBtn.getText().toString().equals("Finish"))
                {

                    config.writeOnBoardingStatus(true);
                    Intent i = new Intent(ActivityOnBoarding.this, MainActivity.class);
                    startActivity(i);
                    finish();
                 }

               nSlideViewPager.setCurrentItem(mCurrentPage+1);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nSlideViewPager.setCurrentItem(mCurrentPage-1);
            }
        });
    }

//    public void btnNextClick(View view) {
//
//
//    }
//
//    public void btnBackClick(View view) {
//        Intent i = new Intent(ActivityOnBoarding.this, MainActivity.class);
//        startActivity(i);
//        finish();
//
//    }

    public void addDotsIndicator(int position)
    {
        mDots=new TextView[3];
        mDotLayout.removeAllViews();
        for (int i=0;i<mDots.length;i++)
        {
mDots[i]=new TextView(this);
mDots[i].setText(Html.fromHtml("&#8226"));
mDots[i].setTextSize(35);
mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhitePrimaryOnBoard));
mDotLayout.addView(mDots[i]);
        }

        if(mDots.length>0)
        {
            mDots[position].setTextColor(getResources().getColor(R.color.colorWhiteOnBoard));
        }
    }

    ViewPager.OnPageChangeListener viewListner=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);

            mCurrentPage=position;
            if(position==0)
            {
                nextBtn.setEnabled(true);
                backBtn.setEnabled(false);
                backBtn.setVisibility(View.INVISIBLE);
                nextBtn.setText("Next");
                backBtn.setText("");
            }
            else if(position==mDots.length-1)
            {
                nextBtn.setEnabled(true);
                backBtn.setEnabled(true);
                backBtn.setVisibility(View.VISIBLE);
                nextBtn.setText("Finish");
                backBtn.setText("Back");
            }
            else
            {
                nextBtn.setEnabled(true);
                backBtn.setEnabled(true);
                backBtn.setVisibility(View.VISIBLE);
                nextBtn.setText("Next");
                backBtn.setText("Back");
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}
