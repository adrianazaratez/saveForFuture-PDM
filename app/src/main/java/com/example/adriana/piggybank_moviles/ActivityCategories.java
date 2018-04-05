package com.example.adriana.piggybank_moviles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by adriana on 02/04/2018.
 */

public class ActivityCategories extends AppCompatActivity{

    ImageButton food, clothing, transport, other, entertainment, health, home, next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        food = findViewById(R.id.activity_categories_food);
        clothing = findViewById(R.id.activity_categories_clothing);
        transport = findViewById(R.id.activity_categories_transport);
        other = findViewById(R.id.activity_categories_other);
        entertainment = findViewById(R.id.activity_categories_entertainment);
        health = findViewById(R.id.activity_categories_health);
        home = findViewById(R.id.activity_categories_home);
        next = findViewById(R.id.activity_categories_next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityCategories.this, ActivityMenu.class);
                startActivity(intent);
                finish();
            }
        });

    }

}
