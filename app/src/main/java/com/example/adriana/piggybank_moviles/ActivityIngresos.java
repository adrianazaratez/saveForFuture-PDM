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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adriana.piggybank_moviles.beans.Meta;
import com.example.adriana.piggybank_moviles.beans.Movimiento;
import com.example.adriana.piggybank_moviles.beans.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityIngresos extends AppCompatActivity {

    DatabaseReference databaseReference;

    TextView salario;
    EditText nuevoingreso;
    Button addAhorros;
    Spinner abonoMeta;
    ArrayList<Usuario> usersList = new ArrayList<>();
    ArrayList<String> userIDList = new ArrayList<>();
    ArrayList<Meta> metasList = new ArrayList<>();
    ArrayList<String> metasIDList = new ArrayList<>();
    String id;
    Meta metaAux = new Meta();
    String idMeta="";
    double cantidadAbono =0, acumulado =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresos);
        salario = findViewById(R.id.activity_ingresos_salario);
        addAhorros = findViewById(R.id.activity_ingresos_btnahorro);
        nuevoingreso = findViewById(R.id.activity_ingresos_cantidad);
        abonoMeta = findViewById(R.id.activity_ingresos_meta);
      //  nuevoingreso.setText("0");

        id = getIntent().getExtras().getString("ID");

        SharedPreferences prefs = getSharedPreferences("com.iteso.SAVEFF_USER_PREFERENCES", Context.MODE_PRIVATE);
        Boolean bandActivity = prefs.getBoolean("flag", false);
     //   id = prefs.getString("uID", null);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                usersList.clear();
                userIDList.clear();
                metasList.clear();
                metasIDList.clear();
                for(DataSnapshot snapshot : dataSnapshot.child("user").getChildren()){
                    Usuario value = snapshot.getValue(Usuario.class);
                    String ids = snapshot.getKey();
                    usersList.add(value);
                    userIDList.add(ids);
                }
                for(DataSnapshot snapshot : dataSnapshot.child("meta").getChildren()){
                    Meta value = snapshot.getValue(Meta.class);
                    String ids = snapshot.getKey();
                    metasList.add(value);
                    metasIDList.add(ids);
                }
                doThings();

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

       addAhorros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sumar en Metas

                if(nuevoingreso.getText() != null || nuevoingreso.getText().equals("")){
                    cantidadAbono = Double.parseDouble(nuevoingreso.getText().toString());
                }
                Log.e("idMeta:", idMeta);
                for(int b = 0; b < nameMeta.length; b++){
                    //comparar la meta con el nombre
                    if (nameMeta[b].equals(abonoMeta.getSelectedItem().toString())){
                       acumulado = metax.get(b).getAhorrado()+cantidadAbono;
                       idMeta = idMetas[b];
                       Log.e("acumulado:", acumulado+"");
                        Log.e("idMeta:", idMeta);
                    }
                }
                databaseReference.child("meta").child(idMeta).child("ahorrado").setValue(acumulado);

                Intent intent = new Intent(ActivityIngresos.this, ActivityMenu.class);
                intent.putExtra("ID",id);
                startActivity(intent);
                finish();
            }
        });

    }

    Usuario user;
    HashMap<String,Boolean> meta;
    ArrayList<Meta> metax = new ArrayList<>();
    String[] nameMeta, idMetas;

    public void doThings(){
    float salary = 0f;

        for(int i = 0; i < userIDList.size(); i++) {
            if (id.equals(userIDList.get(i))) {
                user = usersList.get(i);
                Log.e("USUARIO ACTUAL:",user.toString());
                break;
            }
        }

        meta = user.getMeta();
        int aux=0;
        idMetas = new String[meta.entrySet().size()];
        if(user.getMeta() == null) {
            Toast.makeText(this,"No tienes metas a las que abonar",Toast.LENGTH_LONG).show();
            }
            else {
            salary = user.getSalario().floatValue();
            for (Map.Entry<String, Boolean> entry : meta.entrySet()) {
                String key = entry.getKey();
                for (int i = 0; i < metasIDList.size(); i++) {
                    if (key.equals(metasIDList.get(i))) {
                        metax.add(metasList.get(i));
                        Log.e("metasList Name: ", metasList.get(i).getNombre() + metasList.get(i).getId());
                        // break;
                        idMetas[aux]= metasIDList.get(i);
                        Log.e("metasIDList: ", metasIDList.get(i).toString());
                        aux++;
                    }
                }
            }
        }
        Log.e("metax: ", metax.toString());
        nameMeta= new String[metax.size()];
        for (int i = 0; i < metax.size(); i++) {
            nameMeta[i] = metax.get(i).getNombre();
        }

        salario.setText("$"+salary);
        ArrayAdapter<String> metaAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,nameMeta);
        metaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        abonoMeta.setAdapter(metaAdapter);
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
        }
        return super.onOptionsItemSelected(item);
    }

//    public void showDialog(){
//        DialogFragment newDialog = new DialogIngresos();
//        newDialog.show(getFragmentManager(), "ingresos");
//    }

}
