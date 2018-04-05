package com.example.adriana.piggybank_moviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityNuevoGasto extends AppCompatActivity {
Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevogasto);
        ok = findViewById(R.id.activity_nuevogasto_button);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityNuevoGasto.this, ActivityGastos.class);
                startActivity(intent);
            }
        });
    }
}
