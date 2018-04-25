package com.example.adriana.piggybank_moviles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.adriana.piggybank_moviles.beans.Movimiento;
import com.example.adriana.piggybank_moviles.beans.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by adriana on 02/04/2018.
 */

public class ActivityCategories extends AppCompatActivity{

    ImageButton food, clothing, transport, other, entertainment, health, home, next;
    int foodcont;
    int clothingcont=0, transportcont=0, othercont=0, entertainmentcont=0, healthcont=0, homecont=0;


    DatabaseReference databaseReference;

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

        foodcont = 0;

        /*databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.child("user").getChildren()){
                    Usuario value = snapshot.getValue(Usuario.class);
                    Log.e("FIREBASE",value.toString());
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = getIntent().getExtras().getString("nombre");
                String mail = getIntent().getExtras().getString("correo");
                String fechaNac = getIntent().getExtras().getString("fechaNac");
                String genero = getIntent().getExtras().getString("genero");
                String nombreUsuario  = getIntent().getExtras().getString("nombreUsuario");
                String contrasena = getIntent().getExtras().getString("contrasena");
                Long monto = getIntent().getExtras().getLong("monto");

                Usuario usuario = new Usuario();
                usuario.setNombre(name);
                usuario.setCorreo(mail);
                usuario.setFechaNac(fechaNac);
                usuario.setGenero(genero);
                usuario.setNombreUsuario(nombreUsuario);
                usuario.setContrasena(contrasena);
                usuario.setSalario(monto);

                Log.e("USUARIO",usuario.toString());

                Intent intent = new Intent(ActivityCategories.this, ActivityMenu.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void onClickFood(View v){
        foodcont++;
    }

    public void transportonClick(View v){
        transportcont++;
    }

    public void clothingonClick(View view){
        clothingcont++;
    }

    public void otheronClick(View view){
        othercont++;
    }
    public void entertainmentonClick(View view){
        entertainmentcont++;
    }

    public void healthonClick(View view){
        healthcont++;
    }

    public void homeonClick(View view){
        homecont++;
    }
}
