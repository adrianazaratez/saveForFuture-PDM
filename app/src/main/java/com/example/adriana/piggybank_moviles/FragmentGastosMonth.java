package com.example.adriana.piggybank_moviles;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.adriana.piggybank_moviles.beans.Categoria;
import com.example.adriana.piggybank_moviles.beans.Movimiento;
import com.example.adriana.piggybank_moviles.beans.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class FragmentGastosMonth extends android.support.v4.app.Fragment{
    private AdapterGastos mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<itemGasto> gastos = new ArrayList<>();
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

    Date date,date2;

    public FragmentGastosMonth() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public FragmentGastosMonth(String id) {
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gastos_month, container, false);
        recyclerView = (RecyclerView)
                view.findViewById(R.id.fragment_gastos_month_recycler_view);


        fecha = new Date();
        stringFecha = new SimpleDateFormat("MM-dd-yyyy").format(fecha);
        Log.e("FECHA",stringFecha);
        Log.e("ID",id);

        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        date = null;
        try {
            date = formatter.parse(stringFecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance(); //obtiene la fecha de hoy
        calendar.add(Calendar.DATE, -30);
        date2 = calendar.getTime();

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
                gastos.clear();
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
                Log.e("USUARIO ACTUAL:",user.toString());
                break;
            }
        }

        movimiento = user.getMovimiento();

        if(movimiento == null){
            Toast.makeText(getContext(),"No tienes gastos de este mes",Toast.LENGTH_LONG).show();
            gastos = new ArrayList<>();
        } else {
            for (Map.Entry<String, Boolean> entry : movimiento.entrySet()) {
                String key = entry.getKey();
                for (int i = 0; i < movimientosIDList.size(); i++) {
                    if (key.equals(movimientosIDList.get(i))) {
                        if (movimientosList.get(i).getTipo().equals("gasto")) {
                            SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
                            Date datex = null;
                            try {
                                datex = formatter.parse(movimientosList.get(i).getFecha());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            if ((datex.after(date2) && datex.before(date)) || movimientosList.get(i).getFecha().equals(stringFecha)) {
                                movimientox.add(movimientosList.get(i));
                            }
                        }
                        break;
                    }
                }
            }

            ArrayList<Categoria> categoriax = new ArrayList<>();

            ArrayList<HashMap<String, Boolean>> category = new ArrayList<>();

            for (int i = 0; i < movimientox.size(); i++) {
                Log.e("Movimientox", movimientox.get(i).toString());
                category.add(movimientox.get(i).getCategoria());
                for (Map.Entry<String, Boolean> entry : movimientox.get(i).getCategoria().entrySet()) {
                    String key = entry.getKey();
                    for (int j = 0; j < categoriasIDList.size(); j++) {
                        if (key.equals(categoriasIDList.get(j))) {
                            categoriax.add(categoriasList.get(j));
                            break;
                        }
                    }
                }
            }

            for (int i = 0; i < categoriax.size(); i++) {
                Log.e("CATEGORIAX", categoriax.get(i).toString());
            }

            Log.e("PRUEBA", "PRUEBA");

            gastos = new ArrayList<>();
            gastos.clear();

            //PRUEBA PARA QUE NO SE REPITAN
            String[] categorias = new String[categoriax.size()];
            float[] gasto = new float[movimientox.size()];

            float comidaCount = 0;
            float transporteCount = 0;
            float vestimentaCount = 0;
            float saludCount = 0;
            float entretenimientoCount = 0;
            float hogarCount = 0;
            float otrosCount = 0;
            float cosmeticosCount = 0;
            float viajesCount = 0;
            ////**********************

            for (int i = 0; i < movimientox.size(); i++) {
                //**********
                categorias[i] = categoriax.get(i).getNombre();
                gasto[i] = movimientox.get(i).getMonto().floatValue();
            }

            for (int b = 0; b < categorias.length; b++) {
                switch (categorias[b]) {
                    case "Comida":
                        comidaCount = comidaCount + gasto[b];
                        break;
                    case "Transporte":
                        transporteCount = transporteCount + gasto[b];
                        break;
                    case "Vestimenta":
                        vestimentaCount = vestimentaCount + gasto[b];
                        break;
                    case "Salud":
                        saludCount = saludCount + gasto[b];
                        break;
                    case "Entretenimiento":
                        entretenimientoCount = entretenimientoCount + gasto[b];
                        break;
                    case "Hogar":
                        hogarCount = hogarCount + gasto[b];
                        break;
                    case "Cosmeticos":
                        cosmeticosCount = cosmeticosCount + gasto[b];
                        break;
                    case "Viajes":
                        viajesCount = viajesCount + gasto[b];
                        break;
                    case "Otros":
                        otrosCount = otrosCount + gasto[b];
                        break;
                    default:
                        comidaCount = comidaCount + gasto[b];
                        break;
                }
            }
            //añadir el resumen de gastos por categorias
            ArrayList<String> newCategories = new ArrayList<>();
            ArrayList<Float> newGastos = new ArrayList<>();

            if (comidaCount != 0) {
                newCategories.add("Comida");
                newGastos.add(comidaCount);
            }
            if (transporteCount != 0) {
                newCategories.add("Transporte");
                newGastos.add(transporteCount);
            }
            if (vestimentaCount != 0) {
                newCategories.add("Vestimenta");
                newGastos.add(vestimentaCount);
            }
            if (saludCount != 0) {
                newCategories.add("Salud");
                newGastos.add(saludCount);
            }
            if (entretenimientoCount != 0) {
                newCategories.add("Entretenimiento");
                newGastos.add(entretenimientoCount);
            }
            if (hogarCount != 0) {
                newCategories.add("Hogar");
                newGastos.add(hogarCount);
            }
            if (cosmeticosCount != 0) {
                newCategories.add("Cosméticos");
                newGastos.add(cosmeticosCount);
            }
            if (viajesCount != 0) {
                newCategories.add("Viajes");
                newGastos.add(viajesCount);
            }
            if (otrosCount != 0) {
                newCategories.add("Otros");
                newGastos.add(otrosCount);
            }

            float[] gastosFinal = new float[newGastos.size()];
            String[] categoriasFinal = new String[newCategories.size()];
            float ingresos = user.getSalario().floatValue();
            double setPorcentaje = 0;

            for(int i = 0; i < gastosFinal.length; i++){
                gastosFinal[i] = newGastos.get(i).floatValue();
                categoriasFinal[i] = newCategories.get(i).toString();
            }
            //
            //some changes in for
            for (int i = 0; i < categoriasFinal.length; i++) {
                int image = 0;
                switch (categoriasFinal[i]) {
                    case "Comida":
                        image = 0;
                        setPorcentaje = .20;
                        break;
                    case "Transporte":
                        image = 1;
                        setPorcentaje = .15;
                        break;
                    case "Vestimenta":
                        image = 2;
                        setPorcentaje = .05;
                        break;
                    case "Salud":
                        image = 5;
                        setPorcentaje = .10;
                        break;
                    case "Entretenimiento":
                        image = 4;
                        setPorcentaje = .15;
                        break;
                    case "Hogar":
                        image = 3;
                        setPorcentaje = .15;
                        break;
                    case "Cosmeticos":
                        image = 6;
                        setPorcentaje = .05;
                        break;
                    case "Viajes":
                        image = 7;
                        setPorcentaje = .1;
                        break;
                    case "Otros":
                        setPorcentaje = .05;
                        image = 8;
                        break;
                    default:
                        image = 0;
                        setPorcentaje = .10;
                        break;
                }
                int porcentajefinal = (int) ((gastosFinal[i]*100)/(ingresos*setPorcentaje));
                if(porcentajefinal > 100) {
                    porcentajefinal = 100;
                    Toast.makeText(getContext(),"Has llegado al límite de lo establecido para gastar en " + categoriasFinal[i] + ". " +
                            "Te sugerimos ahorrar en esta categoría.",Toast.LENGTH_LONG).show();
                }
                gastos.add(new itemGasto(categoriasFinal[i], image, Float.toString(gastosFinal[i]), porcentajefinal));
            }
        }

        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new AdapterGastos(getActivity(), gastos);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resutltCode, Intent data){
        super.onActivityResult(requestCode,resutltCode,data);
    }
}