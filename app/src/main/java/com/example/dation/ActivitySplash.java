package com.example.dation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.widget.ProgressBar;
import android.os.Bundle;

public class ActivitySplash extends AppCompatActivity {

    ProgressBar splashProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashProgress=findViewById(R.id.splashProgress);
        ObjectAnimator.ofInt(splashProgress,"progress",100).setDuration(5000).start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(ActivitySplash.this,Login.class);
                startActivity(intent);
                finish();
            }
        },5000);
    }
}