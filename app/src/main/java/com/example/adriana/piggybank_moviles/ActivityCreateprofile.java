package com.example.adriana.piggybank_moviles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by adriana on 02/04/2018.
 */

public class ActivityCreateprofile extends AppCompatActivity{

    EditText nombre, correo, fecha_nac, genero, usuario, psw, pswconf;
    ImageButton next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createprofile);

        nombre = findViewById(R.id.activity_createprofile_name);
        correo = findViewById(R.id.activity_createprofile_mail);
        fecha_nac = findViewById(R.id.activity_createprofile_birth);
        genero = findViewById(R.id.activity_createprofile_gender);
        usuario = findViewById(R.id.activity_createprofile_user);
        psw = findViewById(R.id.activity_createprofile_pwd);
        pswconf = findViewById(R.id.activity_createprofile_checkpwd);
        next = findViewById(R.id.activity_createprofile_arrow);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityCreateprofile.this,ActivityInit.class);
                startActivity(intent);
                finish();
            }
        });

    }

}
