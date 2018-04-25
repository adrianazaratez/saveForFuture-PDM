package com.example.adriana.piggybank_moviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.adriana.piggybank_moviles.beans.Categoria;
import com.example.adriana.piggybank_moviles.beans.Movimiento;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ActivityNuevoGasto extends AppCompatActivity {
    Button ok;
    String id;
    Spinner categoria;
    EditText cantidad;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevogasto);
        ok = findViewById(R.id.activity_nuevogasto_button);
        categoria = findViewById(R.id.activity_nuevogasto_spinner);
        cantidad = findViewById(R.id.activity_nuevogasto_cantidad);

        id = getIntent().getExtras().getString("ID");

        databaseReference = FirebaseDatabase.getInstance().getReference();

        Date fecha = new Date();
        final String stringFecha = new SimpleDateFormat("MM-dd-yyyy").format(fecha);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,Boolean> category = new HashMap<>();
                category.clear();
                String cat;
                switch (categoria.getSelectedItem().toString()){
                    case "Comida":
                        cat = "b";
                        break;
                    case "Transporte":
                        cat = "c";
                        break;
                    case "Ropa":
                        cat = "e";
                        break;
                    case "Salud":
                        cat = "f";
                        break;
                    case "Entretenimiento":
                        cat = "g";
                        break;
                    case "Casa":
                        cat = "h";
                        break;
                    case "Otros":
                        cat = "i";
                        break;
                    case "Cosméticos":
                        cat = "j";
                        break;
                    case "Viajes":
                        cat = "k";
                        break;
                    default:
                        cat = "b";
                }
                category.put(cat,true);

                Movimiento movimiento = new Movimiento();
                movimiento.setCategoria(category);
                movimiento.setTipo("gasto");
                movimiento.setFecha(stringFecha);
                Long x = null;

                try{
                    x = Long.parseLong(cantidad.getText().toString());
                }catch(Exception e){
                    System.out.println(e);
                }

                movimiento.setMonto(x);
                String userId = databaseReference.child("movimiento").push().getKey();
                databaseReference.child("movimiento").child(userId).setValue(movimiento);

                Map<String,Object> mov = new HashMap<>();
                mov.put(userId,true);
                databaseReference.child("user").child(id).child("movimiento").updateChildren(mov);


                Intent intent = new Intent(ActivityNuevoGasto.this, ActivityGastos.class);
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });
    }
}
