package com.example.adriana.piggybank_moviles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by adriana on 02/04/2018.
 */

public class ActivityNewgoal extends AppCompatActivity {
Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newgoal);
        ok = findViewById(R.id.activity_newgoals_button);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityNewgoal.this, ActivityMetas.class);
                startActivity(intent);
            }
        });
    }

}
