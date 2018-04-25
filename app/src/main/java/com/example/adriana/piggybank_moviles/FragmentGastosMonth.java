package com.example.adriana.piggybank_moviles;

import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class FragmentGastosMonth extends android.support.v4.app.Fragment {
    private AdapterGastos mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<itemGasto> gastos;
    RecyclerView recyclerView;
    java.util.Date fecha;
    String sm;

    public FragmentGastosMonth() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gastos_month, container, false);
        recyclerView = (RecyclerView)
                view.findViewById(R.id.fragment_gastos_month_recycler_view);


        fecha = new Date();
        sm = new SimpleDateFormat("MM-dd-yyyy").format(fecha);
        Log.e("FECHA",sm);



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);


        /*0 = comida
        * 1 = transporte
        * 2 = ropa
        * 3 = casa
        * 4 = entretenimiento
        * 5 = salud
        * 6 = cosmeticos
        * 7 = viajes
        * 8 = otros*/

        gastos = new ArrayList<itemGasto>();


        /*gastos.add(new itemGasto("Comida", 0));
        gastos.add(new itemGasto("Transporte", 1));
        gastos.add(new itemGasto("Ropa", 2));
        gastos.add(new itemGasto("Casa", 3));
        gastos.add(new itemGasto("Entretenimiento", 4));
        gastos.add(new itemGasto("Salud", 5));
        gastos.add(new itemGasto("Cosmeticos", 6));
        gastos.add(new itemGasto("Viajes", 7));
        gastos.add(new itemGasto("Otros", 8));*/



        mAdapter = new AdapterGastos(getActivity(), gastos);
        recyclerView.setAdapter(mAdapter);
    }
}
