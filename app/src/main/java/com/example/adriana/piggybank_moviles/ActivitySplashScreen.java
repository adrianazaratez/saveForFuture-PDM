package com.example.adriana.piggybank_moviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class ActivitySplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent createProfile = new Intent().setClass(ActivitySplashScreen.this, ActivityCreateprofile.class);
                startActivity(createProfile);
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 2000);
    }
}
