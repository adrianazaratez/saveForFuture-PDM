package com.example.adriana.piggybank_moviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.adriana.piggybank_moviles.beans.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityMain extends AppCompatActivity {

    EditText usuario,contrasena;
    TextView olvidecontrasena;
    Button crearperfil, iniciarSesion;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = findViewById(R.id.activity_main_user);
        contrasena = findViewById(R.id.activity_main_password);
        crearperfil = findViewById(R.id.activity_main_create);
        olvidecontrasena = findViewById(R.id.activity_main_forgotPWD);
        iniciarSesion = findViewById(R.id.activity_main_login);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        /*
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //     User user = dataSnapshot.getValue(User.class);
                //   message.setText(user.toString());

                for(DataSnapshot snapshot : dataSnapshot.child("user").getChildren()){
                    Usuario value = snapshot.getValue(Usuario.class);
                    Log.e("FIREBASE",value.toString());
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        */
        
        crearperfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMain.this,ActivitySplashScreen.class);
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
