package com.example.adriana.piggybank_moviles;

import android.annotation.SuppressLint;
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

/**
 * Created by adriana on 11/05/2018.
 */

public class FragmentEstadisticasWeek extends android.support.v4.app.Fragment {

    String id;
    RecyclerView recyclerView;
    private AdapterEstadistica mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<itemEstadistica> estadistica = new ArrayList<>();
    Date fecha,date,date2;
    String stringFecha;
    DatabaseReference databaseReference;
    String ids;
    ArrayList<Usuario> usersList = new ArrayList<>();
    ArrayList<String> userIDList = new ArrayList<>();
    ArrayList<Movimiento> movimientosList = new ArrayList<>();
    ArrayList<String> movimientosIDList = new ArrayList<>();
    ArrayList<Categoria> categoriasList = new ArrayList<>();
    ArrayList<String> categoriasIDList = new ArrayList<>();

    public FragmentEstadisticasWeek() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public FragmentEstadisticasWeek(String id) {
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_estadisticas_week, container, false);
        recyclerView = (RecyclerView)
                view.findViewById(R.id.fragment_estadisticas_week_recycler_view);

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
        calendar.add(Calendar.DATE, -7);
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
                for(DataSnapshot snapshot : dataSnapshot.child("user").getChildren()){
                    Usuario value = snapshot.getValue(Usuario.class);
                    ids = snapshot.getKey();
                    usersList.add(value);
                    userIDList.add(ids);
                }

                for(DataSnapshot snapshot : dataSnapshot.child("movimiento").getChildren()){
                    Movimiento value = snapshot.getValue(Movimiento.class);
                    ids = snapshot.getKey();
                    movimientosList.add(value);
                    movimientosIDList.add(ids);
                }

                for(DataSnapshot snapshot : dataSnapshot.child("categoria").getChildren()){
                    Categoria value = snapshot.getValue(Categoria.class);
                    ids = snapshot.getKey();
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

    Usuario user;
    HashMap<String,Boolean> movimiento;
    ArrayList<Movimiento> movimientox = new ArrayList<>();

    public void doThings(){
        float ingresos = 0f;
        float totalGastos = 0f;

        for(int i = 0; i < userIDList.size(); i++) {
            if (id.equals(userIDList.get(i))) {
                user = usersList.get(i);
                Log.e("USUARIO ACTUAL:",user.toString());
                break;
            }
        }

        if(user.getMovimiento() == null) {
            Toast.makeText(getContext(),"No tienes movimientos.\nNo hay estadísticas para mostrar.",Toast.LENGTH_LONG).show();
            estadistica = new ArrayList<>();
        } else {
            movimiento = user.getMovimiento();
            ingresos = user.getSalario().floatValue();
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

            if (movimientox != null) {
                for (int i = 0; i < movimientox.size(); i++) {
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

                estadistica = new ArrayList<>();
                estadistica.clear();

                float []porcentaje = new float[movimientox.size()];
                String []categorias = new String[categoriax.size()];

                float comidaCount = 0f;
                float transporteCount = 0f;
                float vestimentaCount = 0f;
                float saludCount = 0f;
                float entretenimientoCount = 0f;
                float hogarCount = 0f;
                float otrosCount = 0f;
                float cosmeticosCount = 0f;
                float viajesCount = 0f;

                for (int b = 0; b < movimientox.size(); b++) {
                    categorias[b] = categoriax.get(b).getNombre();
                    porcentaje[b] = movimientox.get(b).getMonto().floatValue();
                    totalGastos = totalGastos + movimientox.get(b).getMonto().floatValue();
                }

                for(int b = 0; b < categorias.length; b++){
                    switch (categorias[b]){
                        case "Comida":
                            comidaCount = comidaCount + porcentaje[b];
                            break;
                        case "Transporte":
                            transporteCount = transporteCount + porcentaje[b];
                            break;
                        case "Vestimenta":
                            vestimentaCount = vestimentaCount + porcentaje[b];
                            break;
                        case "Salud":
                            saludCount = saludCount + porcentaje[b];
                            break;
                        case "Entretenimiento":
                            entretenimientoCount = entretenimientoCount + porcentaje[b];
                            break;
                        case "Hogar":
                            hogarCount = hogarCount + porcentaje[b];
                            break;
                        case "Cosmeticos":
                            cosmeticosCount = cosmeticosCount + porcentaje[b];
                            break;
                        case "Viajes":
                            viajesCount = viajesCount + porcentaje[b];
                            break;
                        case "Otros":
                            otrosCount = otrosCount + porcentaje[b];
                            break;
                        default:
                            comidaCount = comidaCount + porcentaje[b];
                            break;
                    }
                }

                ArrayList<String> newCategories = new ArrayList<>();
                ArrayList<Float> newPorcentajes = new ArrayList<>();

                if(comidaCount!= 0){
                    newCategories.add("Comida");
                    newPorcentajes.add(comidaCount);
                }
                if(transporteCount!= 0){
                    newCategories.add("Transporte");
                    newPorcentajes.add(transporteCount);
                }
                if(vestimentaCount!= 0){
                    newCategories.add("Vestimenta");
                    newPorcentajes.add(vestimentaCount);
                }
                if(saludCount!= 0){
                    newCategories.add("Salud");
                    newPorcentajes.add(saludCount);
                }
                if(entretenimientoCount!= 0){
                    newCategories.add("Entretenimiento");
                    newPorcentajes.add(entretenimientoCount);
                }
                if(hogarCount!= 0){
                    newCategories.add("Hogar");
                    newPorcentajes.add(hogarCount);
                }
                if(cosmeticosCount!= 0){
                    newCategories.add("Cosméticos");
                    newPorcentajes.add(cosmeticosCount);
                }
                if(viajesCount!= 0){
                    newCategories.add("Viajes");
                    newPorcentajes.add(viajesCount);
                }
                if(otrosCount!= 0){
                    newCategories.add("Otros");
                    newPorcentajes.add(otrosCount);
                }

                float[] porcentajesFinal = new float[newPorcentajes.size()];
                String[] categoriasFinal = new String[newCategories.size()];

                for(int i = 0; i < porcentajesFinal.length; i++){
                    porcentajesFinal[i] = newPorcentajes.get(i).floatValue();
                    categoriasFinal[i] = newCategories.get(i).toString();
                }

                float ahorroGenerado = (ingresos / 4 - totalGastos);
                if (ahorroGenerado < 0) {
                    ahorroGenerado = 0f;
                }

                float ahorros[] = {totalGastos, ahorroGenerado};
                String objetos[] = {"Gasto\nrealizado", "Ahorro\ngenerado"};

                estadistica.add(new itemEstadistica(porcentajesFinal, categoriasFinal, "Porcentaje de gasto por categoría", 1));
                estadistica.add(new itemEstadistica(porcentajesFinal, categoriasFinal, "Gasto total por categoría", 2));
                estadistica.add(new itemEstadistica(ahorros, objetos, "Ahorro en cuanto a ingresos", 1));
                //  recyclerView.setHasFixedSize(true);
            }
        }
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new AdapterEstadistica(getActivity(), estadistica);
        recyclerView.setAdapter(mAdapter);

    }
}