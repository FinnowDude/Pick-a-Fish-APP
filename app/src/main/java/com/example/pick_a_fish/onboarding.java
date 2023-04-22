package com.example.pick_a_fish;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import android.widget.TextView;

public class onboarding extends AppCompatActivity {


    ViewPager mSlideViewPager;
    LinearLayout mDotLayout;
    Button btnskip, btnnext, btnback;

    TextView[]  dots;
    ViewPagerAdapter viewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding1);


        SharedPreferences preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);

        String FirstTime = preferences.getString("FirstTimeInstall","");

        if(FirstTime.equals("Yes")){

            Intent intent = new Intent(onboarding.this, loginActivity.class);
            startActivity(intent);
            finish();

        }else {

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("FirstTimeInstall", "Yes");
            editor.apply();

        }





        btnskip = findViewById(R.id.btnskip);
        btnnext = findViewById(R.id.btnnext);
        btnback = findViewById(R.id.btnback);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(getitem(0)>0){

                    mSlideViewPager.setCurrentItem(getitem(-1), true);

                }

            }
        });

        btnskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(onboarding.this, loginActivity.class);
                startActivity(i);
                finish();



            }
        });


        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(getitem(0) < 2)
                    mSlideViewPager.setCurrentItem(getitem(1),true);
                else{

                    Intent i =  new Intent(onboarding.this, loginActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.indicator_layout);

        viewPagerAdapter = new ViewPagerAdapter(this);
        mSlideViewPager.setAdapter(viewPagerAdapter);

        setUpindicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);
    }


    public void setUpindicator(int position){

        dots = new TextView[3];
        mDotLayout.removeAllViews();

        for(int i = 0; i < dots.length; i++){

            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.inactive, getApplication().getTheme()));
            mDotLayout.addView(dots[i]);


        }

        dots[position].setTextColor(getResources().getColor(R.color.active,getApplication().getTheme()));

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }


        @Override
        public void onPageSelected(int position) {


            setUpindicator(position);
                if(position>0){
                    btnback.setVisibility(View.VISIBLE);

                }else{

                    btnback.setVisibility(View.INVISIBLE);
                }

        }

        @Override
        public void onPageScrollStateChanged(int state) {



        }

    };

    private int getitem(int i){

        return mSlideViewPager.getCurrentItem() + i ;
    }

}