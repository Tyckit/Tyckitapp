package com.example.tyckit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class OnBoarding1 extends AppCompatActivity {

    TextView next_pg_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding1);

        next_pg_btn = findViewById(R.id.next_button);

        next_pg_btn.setOnClickListener(view -> openOnBoarding2());
    }

    public void openOnBoarding2(){
        Intent intent = new Intent(this, OnBoarding2.class);
        startActivity(intent);

        overridePendingTransition(R.anim.animation_right,R.anim.animation_out_left);
    }
}
