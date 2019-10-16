package com.example.onboardstartapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;

    //Dots initialize
    private TextView[] mDots;

    private SliderAdapter sliderAdapter;

    //Buttons Initialize
    private Button mNextButton;
    private Button mBackButton;
    private Button mGetStarted;

    private int mCurrentPage;

    //Animation
    Animation btnAnim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //FullScreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (restorePrefData()){
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }


        setContentView(R.layout.activity_main);



        mSlideViewPager = findViewById(R.id.slideViewPager);
        mDotLayout = findViewById(R.id.dotsLayout);

        sliderAdapter = new SliderAdapter(this);

        mSlideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        mSlideViewPager.addOnPageChangeListener(viewListener);

        //Button initialize
        mNextButton = findViewById(R.id.nxtBtn);
        mBackButton = findViewById(R.id.prvBtn);
        mGetStarted = findViewById(R.id.btnGet);

        //OnClickListeners
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideViewPager.setCurrentItem(mCurrentPage + 1);
            }
        });

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideViewPager.setCurrentItem(mCurrentPage -1);
            }
        });

        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);

        openHomeActivity();
    }

    private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        Boolean isIntroOpenedBefore = pref.getBoolean("isOpened", false);
        return isIntroOpenedBefore;
    }

    private void openHomeActivity() {

        mGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);

                savePrefsData();
                finish();
            }
        });
    }

    private void savePrefsData() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isOpened", true);
        editor.commit();
    }

    public void addDotsIndicator(int position){

        mDots = new TextView[3];
        mDotLayout.removeAllViews();

        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            mDotLayout.addView(mDots[i]);

        }

        if (mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int i) {

            addDotsIndicator(i);

            mCurrentPage = i;

            if (i == 0){
                mNextButton.setEnabled(true);
                mBackButton.setEnabled(false);
                mBackButton.setVisibility(View.INVISIBLE);
                mGetStarted.setVisibility(View.INVISIBLE);


                mNextButton.setText("Next");
                mBackButton.setText("");
            }else if (i == mDots.length -1){
                mNextButton.setEnabled(false);
                mBackButton.setEnabled(true);
                mBackButton.setVisibility(View.VISIBLE);
                mNextButton.setVisibility(View.INVISIBLE);
                mGetStarted.setVisibility(View.VISIBLE);

                //mNextButton.setText("Finish");
                mBackButton.setText("Back");

                mGetStarted.setAnimation(btnAnim);

            }else {
                mNextButton.setEnabled(true);
                mBackButton.setEnabled(true);
                mBackButton.setVisibility(View.VISIBLE);
                mNextButton.setVisibility(View.VISIBLE);
                mGetStarted.setVisibility(View.INVISIBLE);

                mNextButton.setText("Next");
                mBackButton.setText("Back");
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}
