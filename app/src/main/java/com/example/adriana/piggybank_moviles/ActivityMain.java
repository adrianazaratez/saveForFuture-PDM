package com.example.adriana.piggybank_moviles;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adriana.piggybank_moviles.beans.Usuario;
import com.google.firebase.auth.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityMain extends AppCompatActivity {

    EditText usuario,contrasena;
    TextView olvidecontrasena;
    Button crearperfil, iniciarSesion;

    ArrayList<Usuario> usersList = new ArrayList<>();
    ArrayList<String> usersIDList = new ArrayList<>();
    String ids;

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

        //PRUEBA IF IS LOGGED
        //DEFAULT IS FALSE = NOT LOGGED
        SharedPreferences prefs = getSharedPreferences("com.iteso.SAVEFF_USER_PREFERENCES", Context.MODE_PRIVATE);
        Boolean bandActivity = prefs.getBoolean("flag", false);
        String userID = prefs.getString("uID", null);
        prefs = null;
        if (!bandActivity && userID == null){

            databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    usersList.clear();
                    usersIDList.clear();
                    for(DataSnapshot snapshot : dataSnapshot.child("user").getChildren()){
                        Usuario value = snapshot.getValue(Usuario.class);
                        ids = snapshot.getKey();
                        Log.e("FIREBASE",value.toString());
                        usersList.add(value);
                        usersIDList.add(ids);
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

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
                @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
                @Override
                public void onClick(View v) {
                    int flag = 0;
                    for (int i = 0; i < usersList.size(); i++){
                        if(usersList.get(i).getNombreUsuario().equals(usuario.getText().toString()) && usersList.get(i).getContrasena().equals(contrasena.getText().toString())){
                            flag = 1;
                            //PRUEBA (CAMBIAR A TRUE BANDERA)
                            SharedPreferences prefs = getSharedPreferences("com.iteso.SAVEFF_USER_PREFERENCES", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putBoolean("flag", true);
                            editor.putString("uID", usersIDList.get(i));
                            editor.apply();

                            Intent intent = new Intent(ActivityMain.this,ActivityMenu.class);
                            intent.putExtra("ID",usersIDList.get(i));
                            startActivity(intent);
                            finish();
                            break;
                        }
                    }
                    if(flag==0){
                        Toast.makeText(ActivityMain.this,"Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        else { //O SEA QUE YA ESTA LOGGEADO
            SharedPreferences.Editor editor = getSharedPreferences("com.iteso.SAVEFF_USER_PREFERENCES", Context.MODE_PRIVATE).edit();
            editor.putBoolean("flag", true);
            editor.putString("uID", userID);
            editor.apply();

            Intent intent = new Intent(ActivityMain.this,ActivityMenu.class);
            startActivity(intent);
            finish();
        }
    }
}
