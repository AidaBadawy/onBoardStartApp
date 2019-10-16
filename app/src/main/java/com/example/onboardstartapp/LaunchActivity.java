package com.example.onboardstartapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class LaunchActivity extends AppCompatActivity {

    private ImageView logo;
    private static int SPLASH_TIME = 5000;
    private LaunchActivity mContext = LaunchActivity.this;

    //Animation
    Animation myAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //FullScreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_launch);

        logo = findViewById(R.id.logo);

        openNextActivity();
    }

    private void openNextActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(mContext, MainActivity.class);
                ActivityOptions options = ActivityOptions.makeCustomAnimation(mContext, R.anim.fade_in, R.anim.fade_out);
                mContext.startActivity(intent, options.toBundle());
                finish();

            }
        }, SPLASH_TIME);

        myAnim = AnimationUtils.loadAnimation(this, R.anim.mysplashanim);
        logo.startAnimation(myAnim);


    }
}
