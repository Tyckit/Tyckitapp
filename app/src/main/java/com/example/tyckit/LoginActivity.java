package com.example.tyckit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.animation_left, R.anim.animation_out_right);
    }
}