package com.example.adriana.piggybank_moviles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.adriana.piggybank_moviles.beans.Categoria;
import com.example.adriana.piggybank_moviles.beans.Movimiento;
import com.example.adriana.piggybank_moviles.beans.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by adriana on 02/04/2018.
 */

public class ActivityCategories extends AppCompatActivity{

    ImageButton food, clothing, transport, other, entertainment, health, home, next;
    int foodcont;
    int clothingcont, transportcont, othercont, entertainmentcont, healthcont, homecont;
    ArrayList<String> idCategorias = new ArrayList<>();
    ArrayList<Categoria> categoriasEnFirebase = new ArrayList<>();
    String idCategory;
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
        clothingcont=0;
        transportcont=0;
        othercont=0;
        entertainmentcont=0;
        healthcont=0;
        homecont=0;

        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                categoriasEnFirebase.clear();
                idCategorias.clear();
                for(DataSnapshot snapshot : dataSnapshot.child("categoria").getChildren()){
                    Categoria value = snapshot.getValue(Categoria.class);
                    idCategory = snapshot.getKey();
                    idCategorias.add(idCategory);
                    categoriasEnFirebase.add(value);
                    Log.e("FIREBASE",idCategory + " " + value.toString());
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


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

                HashMap<String,Boolean> categoria = new HashMap<>();

                if(foodcont%2 != 0) {
                    for (int i = 0; i < categoriasEnFirebase.size(); i++) {
                        if (categoriasEnFirebase.get(i).getNombre().equals("Comida")) {
                            categoria.put(idCategorias.get(i), true);
                            break;
                        }
                    }
                }
                if(transportcont%2 != 0) {
                    for (int i = 0; i < categoriasEnFirebase.size(); i++) {
                        if (categoriasEnFirebase.get(i).getNombre().equals("Transporte")) {
                            categoria.put(idCategorias.get(i), true);
                            break;
                        }
                    }
                }
                if(clothingcont%2 != 0) {
                    for (int i = 0; i < categoriasEnFirebase.size(); i++) {
                        if (categoriasEnFirebase.get(i).getNombre().equals("Vestimenta")) {
                            categoria.put(idCategorias.get(i), true);
                            break;
                        }
                    }
                }
                if(healthcont%2 != 0) {
                    for (int i = 0; i < categoriasEnFirebase.size(); i++) {
                        if (categoriasEnFirebase.get(i).getNombre().equals("Salud")) {
                            categoria.put(idCategorias.get(i), true);
                            break;
                        }
                    }
                }
                if(entertainmentcont%2 != 0) {
                    for (int i = 0; i < categoriasEnFirebase.size(); i++) {
                        if (categoriasEnFirebase.get(i).getNombre().equals("Entretenimiento")) {
                            categoria.put(idCategorias.get(i), true);
                            break;
                        }
                    }
                }
                if(homecont%2 != 0) {
                    for (int i = 0; i < categoriasEnFirebase.size(); i++) {
                        if (categoriasEnFirebase.get(i).getNombre().equals("Hogar")) {
                            categoria.put(idCategorias.get(i), true);
                            break;
                        }
                    }
                }
                if(othercont%2 != 0) {
                    for (int i = 0; i < categoriasEnFirebase.size(); i++) {
                        if (categoriasEnFirebase.get(i).getNombre().equals("Otros")) {
                            categoria.put(idCategorias.get(i), true);
                            break;
                        }
                    }
                }

                Usuario usuario = new Usuario();
                usuario.setNombre(name);
                usuario.setCorreo(mail);
                usuario.setFechaNac(fechaNac);
                usuario.setGenero(genero);
                usuario.setNombreUsuario(nombreUsuario);
                usuario.setContrasena(contrasena);
                usuario.setSalario(monto);
                usuario.setCategoria(categoria);

                Log.e("USUARIO",usuario.toString());
                String userId = databaseReference.child("user").push().getKey();
                databaseReference.child("user").child(userId).setValue(usuario);


                Toast.makeText(ActivityCategories.this,"Se creó el perfil",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(ActivityCategories.this, ActivityMenu.class);
                intent.putExtra("ID",userId);
                startActivity(intent);
                finish();
            }
        });

    }

    public void onClickFood(View v){
        foodcont++;
        food.setImageResource(R.drawable.foodchecked);
    }

    public void transportonClick(View v){
        transportcont++;
        transport.setImageResource(R.drawable.transportchecked);
    }

    public void clothingonClick(View view){
        clothingcont++;
        clothing.setImageResource(R.drawable.clothechecked);
    }

    public void otheronClick(View view){
        othercont++;
        other.setImageResource(R.drawable.otherschecked);
    }
    public void entertainmentonClick(View view){
        entertainmentcont++;
        entertainment.setImageResource(R.drawable.entretainmentchecked);
    }

    public void healthonClick(View view){
        healthcont++;
        health.setImageResource(R.drawable.healthchecked);
    }

    public void homeonClick(View view){
        homecont++;
        home.setImageResource(R.drawable.homechecked);
    }
}
