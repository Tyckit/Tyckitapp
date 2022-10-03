package com.example.tyckit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OnBoarding2 extends AppCompatActivity {

    TextView next_pg_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding2);

        next_pg_btn = findViewById(R.id.next_button);
        next_pg_btn.setOnClickListener(view -> openOnBoarding3());
    }

    public void openOnBoarding3(){
        Intent intent = new Intent(this, OnBoarding3.class);
        startActivity(intent);
        overridePendingTransition(R.anim.animation_right,R.anim.animation_out_left);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.animation_left, R.anim.animation_out_right);
    }
}