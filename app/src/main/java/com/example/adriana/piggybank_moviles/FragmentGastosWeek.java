package com.example.adriana.piggybank_moviles;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FragmentGastosWeek extends android.support.v4.app.Fragment {
    private AdapterGastos mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<itemGasto> gastos;
    RecyclerView recyclerView;

    public FragmentGastosWeek() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gastos_week, container, false);
        recyclerView = (RecyclerView)
                view.findViewById(R.id.fragment_gastos_week_recycler_view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        gastos = new ArrayList<itemGasto>();
//        gastos.add(new itemGasto("Comida", 0));
//        gastos.add(new itemGasto("Transporte", 1));
//        gastos.add(new itemGasto("Ropa", 2));
//        gastos.add(new itemGasto("Casa", 3));
//        gastos.add(new itemGasto("Entretenimiento", 4));
//        gastos.add(new itemGasto("Salud", 5));
//        gastos.add(new itemGasto("Cosmeticos", 6));
//        gastos.add(new itemGasto("Viajes", 7));
//        gastos.add(new itemGasto("Otros", 8));

        mAdapter = new AdapterGastos(getActivity(), gastos);
        recyclerView.setAdapter(mAdapter);
    }
    }
