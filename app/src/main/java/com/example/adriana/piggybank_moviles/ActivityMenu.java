package com.example.adriana.piggybank_moviles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by adriana on 02/04/2018.
 */

public class ActivityMenu extends AppCompatActivity {

    ImageButton gastos, metas, ingresos, estadisticas, retoDia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        gastos = findViewById(R.id.activity_menu_gastos);
        metas = findViewById(R.id.activity_menu_metas);
        ingresos = findViewById(R.id.activity_menu_ingresos);
        estadisticas = findViewById(R.id.activity_menu_estadisticas);
        retoDia = findViewById(R.id.activity_menu_reto);

        gastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMenu.this,ActivityCreateprofile.class);
                startActivity(intent);
                finish();
            }
        });

        metas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMenu.this,ActivityCreateprofile.class);
                startActivity(intent);
                finish();
            }
        });

        ingresos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMenu.this,ActivityIngresos.class);
                startActivity(intent);
                finish();
            }
        });

        estadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMenu.this,ActivityCreateprofile.class);
                startActivity(intent);
                finish();
            }
        });

        retoDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMenu.this,ActivityRetoDia.class);
                startActivity(intent);
                finish();
            }
        });

    }

}
