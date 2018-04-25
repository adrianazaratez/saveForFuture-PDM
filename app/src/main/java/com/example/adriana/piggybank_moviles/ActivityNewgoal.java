package com.example.adriana.piggybank_moviles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.adriana.piggybank_moviles.beans.Meta;
import com.example.adriana.piggybank_moviles.beans.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by adriana on 02/04/2018.
 */

public class ActivityNewgoal extends AppCompatActivity {
Button ok;
Spinner tipo;
EditText nombre;
EditText cantidad;
CalendarView fechaLim;
DatabaseReference databaseReference;
HashMap<String,Boolean> categoria  = new HashMap<>();
String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newgoal);
        id = getIntent().getExtras().getString("ID");
        ok = findViewById(R.id.activity_newgoals_button);
        tipo = findViewById(R.id.activity_newgoals_type);
        nombre = findViewById(R.id.activity_newgoals_name);
        cantidad = findViewById(R.id.activity_newgoals_quantity);
        fechaLim = findViewById(R.id.acivity_newgoals_date);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoria.clear();
                String category;
                switch (tipo.getSelectedItem().toString()){
                    case "Comida":
                        category = "b";
                        break;
                    case "Transporte":
                        category = "c";
                        break;
                    case "Ropa":
                        category = "e";
                        break;
                    case "Salud":
                        category = "f";
                        break;
                    case "Entretenimiento":
                        category = "g";
                        break;
                    case "Casa":
                        category = "h";
                        break;
                    case "Otros":
                        category = "i";
                        break;
                    case "Cosméticos":
                        category = "j";
                        break;
                    case "Viajes":
                        category = "k";
                        break;
                    default:
                        category = "b";
                }
                categoria.put(category,true);

                Meta meta = new Meta();
                meta.setNombre(nombre.getText().toString());
                meta.setCantidad(Long.parseLong(cantidad.getText().toString()));
                meta.setCategoria(categoria);
                meta.setFechaLimite(fechaLim.toString());

                String userid = databaseReference.child("meta").push().getKey();
                databaseReference.child("meta").child(userid).setValue(meta);

                Map<String,Object> newgoal = new HashMap<>();
                newgoal.put(userid,true);
                databaseReference.child("user").child(id).child("meta").updateChildren(newgoal);

                Toast.makeText(ActivityNewgoal.this,"Meta añadida", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ActivityNewgoal.this, ActivityMetas.class);
                intent.putExtra("ID",id);
                startActivity(intent);

            }
        });
    }

}
