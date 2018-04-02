package com.example.adriana.piggybank_moviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityMain extends AppCompatActivity {

    EditText usuario,contrasena;
    TextView crearperfil,olvidecontrasena;
    Button iniciarSesion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = findViewById(R.id.activity_main_user);
        contrasena = findViewById(R.id.activity_main_password);
        crearperfil = findViewById(R.id.activity_main_create);
        olvidecontrasena = findViewById(R.id.activity_main_forgotPWD);
        iniciarSesion = findViewById(R.id.activity_main_login);

        crearperfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMain.this,ActivityCreateprofile.class);
                startActivity(intent);
                finish();
            }
        });

        olvidecontrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Correo con contraseña
            }
        });

        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMain.this,ActivityMenu.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
