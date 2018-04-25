package com.example.adriana.piggybank_moviles;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adriana.piggybank_moviles.beans.Categoria;
import com.example.adriana.piggybank_moviles.beans.Movimiento;
import com.example.adriana.piggybank_moviles.beans.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class FragmentGastosToday extends android.support.v4.app.Fragment{
    private AdapterGastos mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<itemGasto> gastos;
    RecyclerView recyclerView;

    DatabaseReference databaseReference;
    String id;
    String ids;
    Usuario user;

    ArrayList<Usuario> usersList = new ArrayList<>();
    ArrayList<String> userIDList = new ArrayList<>();
    HashMap<String,Boolean> movimiento;
    ArrayList<Movimiento> movimientosList = new ArrayList<>();
    ArrayList<String> movimientosIDList = new ArrayList<>();
    ArrayList<Categoria> categoriasList = new ArrayList<>();
    ArrayList<String> categoriasIDList = new ArrayList<>();
    java.util.Date fecha;
    String stringFecha;
    ArrayList<Movimiento> movimientox = new ArrayList<>();

    public FragmentGastosToday() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public FragmentGastosToday(String id) {
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gastos_today, container, false);
        recyclerView = (RecyclerView)
                view.findViewById(R.id.fragment_gastos_today_recycler_view);

        fecha = new Date();
        stringFecha = new SimpleDateFormat("MM-dd-yyyy").format(fecha);
        Log.e("FECHA",stringFecha);
        Log.e("ID",id);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                usersList.clear();
                userIDList.clear();
                movimientosList.clear();
                movimientosIDList.clear();
                categoriasList.clear();
                categoriasIDList.clear();
                for(DataSnapshot snapshot : dataSnapshot.child("user").getChildren()){
                    Usuario value = snapshot.getValue(Usuario.class);
                    ids = snapshot.getKey();
                    Log.e("FIREBASE",ids+ " " + value.toString());
                    usersList.add(value);
                    userIDList.add(ids);
                }

                for(DataSnapshot snapshot : dataSnapshot.child("movimiento").getChildren()){
                    Movimiento value = snapshot.getValue(Movimiento.class);
                    ids = snapshot.getKey();
                    Log.e("FIREBASE",ids + " "+ value.toString());
                    movimientosList.add(value);
                    movimientosIDList.add(ids);
                }

                for(DataSnapshot snapshot : dataSnapshot.child("categoria").getChildren()){
                    Categoria value = snapshot.getValue(Categoria.class);
                    ids = snapshot.getKey();
                    Log.e("FIREBASE",ids + " "+value.toString());
                    categoriasList.add(value);
                    categoriasIDList.add(ids);
                }

                doThings();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }


    public void doThings(){
        for(int i = 0; i < userIDList.size(); i++) {
            if (id.equals(userIDList.get(i))) {
                user = usersList.get(i);
                break;
            }
        }

        movimiento = user.getMovimiento();

        for (Map.Entry<String, Boolean> entry : movimiento.entrySet()) {
            String key = entry.getKey();
            for(int i = 0; i < movimientosIDList.size(); i++) {
                if (key.equals(movimientosIDList.get(i))){
                    if(movimientosList.get(i).getTipo().equals("gasto")&& movimientosList.get(i).getFecha().equals(stringFecha)) {
                        movimientox.add(movimientosList.get(i));
                    }
                    break;
                }
            }
        }

        ArrayList<Categoria> categoriax = new ArrayList<>();

        ArrayList<HashMap<String,Boolean>> category = new ArrayList<>();

        for (int i = 0; i < movimientox.size(); i++) {
            Log.e("Movimientox", movimientox.get(i).toString());
            category.add(movimientox.get(i).getCategoria());
            for (Map.Entry<String, Boolean> entry : movimientox.get(i).getCategoria().entrySet()) {
                String key = entry.getKey();
                for(int j = 0; j < categoriasIDList.size(); j++) {
                    if (key.equals(categoriasIDList.get(j))){
                        categoriax.add(categoriasList.get(j));
                        break;
                    }
                }
            }
        }


        for(int i = 0; i < categoriax.size(); i++) {
            Log.e("CATEGORIAX", categoriax.get(i).toString());
        }

        Log.e("CATEGORIAX", categoriax.get(0).toString());

        recyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        Log.e("PRUEBA","PRUEBA");

        gastos = new ArrayList<>();

        for(int i = 0; i < movimientox.size(); i++){

            int image = 0;
            switch(categoriax.get(i).getNombre()){
                case "Comida":
                    image = 0;
                    break;
                case "Transporte":
                    image = 1;
                    break;
                case "Vestimenta":
                    image = 2;
                    break;
                case "Salud":
                    image = 5;
                    break;
                case "Entretenimiento":
                    image = 4;
                    break;
                case "Hogar":
                    image = 3;
                    break;
                case "Cosmeticos":
                    image = 6;
                    break;
                case "Viajes":
                    image = 7;
                    break;
                case "Otros":
                    image = 8;
                    break;
                default:
                    image = 0;
                    break;
            }
            Log.e("MONTO",movimientox.get(i).getMonto().toString());
            gastos.add(new itemGasto(categoriax.get(i).getNombre(),image,movimientox.get(i).getMonto().toString()));
        }


        mAdapter = new AdapterGastos(getActivity(), gastos);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resutltCode, Intent data){
        super.onActivityResult(requestCode,resutltCode,data);
    }
}
