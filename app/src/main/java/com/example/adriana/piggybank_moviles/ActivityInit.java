package com.example.adriana.piggybank_moviles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by adriana on 02/04/2018.
 */

public class ActivityInit extends AppCompatActivity {

    ImageButton next;
    EditText monto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        next = findViewById(R.id.activity_init_button);
        monto = findViewById(R.id.activity_init_monto);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = getIntent().getExtras().getString("nombre");
                String mail = getIntent().getExtras().getString("correo");
                String fechaNac = getIntent().getExtras().getString("fechaNac");
                String genero = getIntent().getExtras().getString("genero");
                String nombreUsuario  = getIntent().getExtras().getString("nombreUsuario");
                String contrasena = getIntent().getExtras().getString("contrasena");
                Intent intent = new Intent(ActivityInit.this, ActivityCategories.class);
                intent.putExtra("nombre",name);
                intent.putExtra("correo",mail);
                intent.putExtra("fechaNac",fechaNac);
                intent.putExtra("genero",genero);
                intent.putExtra("nombreUsuario",nombreUsuario);
                intent.putExtra("contrasena",contrasena);
                Long montos = Long.parseLong(monto.getText().toString());
                intent.putExtra("monto", montos);
                startActivity(intent);
                finish();
            }
        });

    }

}
