package com.example.tyckit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 4000;
    ImageView background, app_name;
    LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        app_name = findViewById(R.id.app_name);
        lottieAnimationView = findViewById(R.id.lottie);

        lottieAnimationView.animate().translationY(-1600).setDuration(1000).setStartDelay(1500);
        app_name.animate().translationY(1400).setDuration(1000).setStartDelay(1500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,OnBoarding1.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN );
    }
}