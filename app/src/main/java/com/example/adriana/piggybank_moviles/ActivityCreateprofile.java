package com.example.adriana.piggybank_moviles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.adriana.piggybank_moviles.beans.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

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
                if(psw.getText().toString().equals(pswconf.getText().toString())) {
                    Intent intent = new Intent(ActivityCreateprofile.this, ActivityInit.class);
                    intent.putExtra("nombre",nombre.getText().toString());
                    intent.putExtra("correo",correo.getText().toString());
                    intent.putExtra("fechaNac",fecha_nac.getText().toString());
                    intent.putExtra("genero",genero.getText().toString());
                    intent.putExtra("nombreUsuario",usuario.getText().toString());
                    intent.putExtra("contrasena",psw.getText().toString());
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(ActivityCreateprofile.this,"La contraseña no es igual en ambos campos, intente de nuevo. ",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
