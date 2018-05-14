package com.example.adriana.piggybank_moviles;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.TextureView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActivityIngresos extends AppCompatActivity {

    DatabaseReference databaseReference;

    TextView ingresos;
    EditText nuevoingreso;
    Button addIngresos, addAhorros;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresos);
        ingresos = findViewById(R.id.activity_ingresos_ingreso);
        addIngresos = findViewById(R.id.activity_ingresos_btningreso);
        addAhorros = findViewById(R.id.activity_ingresos_btnahorro);
        nuevoingreso = findViewById(R.id.nuevo_ingreso);

        id = getIntent().getExtras().getString("ID");

        SharedPreferences prefs = getSharedPreferences("com.iteso.SAVEFF_USER_PREFERENCES", Context.MODE_PRIVATE);
        Boolean bandActivity = prefs.getBoolean("flag", false);
     //   id = prefs.getString("uID", null);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d("FIREBASE", "Value is: " + value);
                ingresos.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        addAhorros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = new Usuario();
                usuario.setSalario((long) Integer.parseInt(ingresos.getText().toString()+nuevoingreso.getText().toString()));
                String userId = databaseReference.child("user").push().getKey();
                databaseReference.child("user").child(userId).setValue(usuario);

                Map<String,Object> movi = new HashMap<>();
                movi.put(userId,true);
                databaseReference.child("user").child(id).child("salario").updateChildren(movi);
                //showDialog();

                Intent intent = new Intent(ActivityIngresos.this, ActivityMenu.class);
                intent.putExtra("ID",id);
                startActivity(intent);
                finish();
            }
        });
        addIngresos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = new Usuario();
                usuario.setSalario((long) Integer.parseInt(ingresos.getText().toString()+nuevoingreso.getText().toString()));
                String userId = databaseReference.child("user").push().getKey();
                databaseReference.child("user").child(userId).setValue(usuario);

                Map<String,Object> movi = new HashMap<>();
                movi.put(userId,true);
                databaseReference.child("user").child(id).child("salario").updateChildren(movi);
                //showDialog();

                Intent intent = new Intent(ActivityIngresos.this, ActivityMenu.class);
                intent.putExtra("ID",id);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_logOut:
                SharedPreferences.Editor editor = getSharedPreferences("com.iteso.SAVEFF_USER_PREFERENCES", Context.MODE_PRIVATE).edit();
                editor.clear();
                editor.apply();

                Intent intent2= new Intent(ActivityIngresos.this,ActivityMain.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent2);
                finish();
                return true;
            case R.id.action_about:
                Intent intent3= new Intent(ActivityIngresos.this,ActivityInfoApp.class);
                startActivity(intent3);
                //finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    public void showDialog(){
//        DialogFragment newDialog = new DialogIngresos();
//        newDialog.show(getFragmentManager(), "ingresos");
//    }

}
