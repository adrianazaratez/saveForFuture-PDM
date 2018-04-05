package com.example.adriana.piggybank_moviles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.widget.Button;
import android.widget.TextView;

public class ActivityIngresos extends AppCompatActivity {

    TextView ingresos;

    Button addIngresos, addAhorros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresos);

        ingresos = findViewById(R.id.activity_ingresos_ingreso);
        addIngresos = findViewById(R.id.activity_ingresos_btningreso);
        addAhorros = findViewById(R.id.activity_ingresos_btnahorro);

        ingresos.setText("$ 20,000.00");
    }
}
