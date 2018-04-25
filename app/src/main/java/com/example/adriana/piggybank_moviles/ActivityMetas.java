package com.example.adriana.piggybank_moviles;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class ActivityMetas extends AppCompatActivity {

    private AdapterMetas mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<itemMeta> metas;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metas);

        mLayoutManager = new LinearLayoutManager(this);

       recyclerView = findViewById(R.id.containerMetas);
       recyclerView.setHasFixedSize(true);
       recyclerView.setLayoutManager(mLayoutManager);

        metas = new ArrayList<itemMeta>();
        metas.add(new itemMeta("Viaje a Colombia", "70%", 70));
        metas.add(new itemMeta("iPhone X", "10%",10));

        mAdapter = new AdapterMetas(this,metas);
        recyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityMetas.this, ActivityNewgoal.class);
                startActivity(intent);
            }
        });

    }

}


/**

 @Override
 public void onActivityCreated(@Nullable Bundle savedInstanceState) {
 super.onActivityCreated(savedInstanceState);

 recyclerView.setHasFixedSize(true);

 mLayoutManager = new LinearLayoutManager(getActivity());
 recyclerView.setLayoutManager(mLayoutManager);

 gastos = new ArrayList<itemGasto>();
 gastos.add(new itemGasto("Comida", 0));
 gastos.add(new itemGasto("Transporte", 1));
 gastos.add(new itemGasto("Ropa", 2));
 gastos.add(new itemGasto("Casa", 3));
 gastos.add(new itemGasto("Entretenimiento", 4));
 gastos.add(new itemGasto("Salud", 5));
 gastos.add(new itemGasto("Cosmeticos", 6));
 gastos.add(new itemGasto("Viajes", 7));
 gastos.add(new itemGasto("Otros", 8));

 mAdapter = new AdapterGastos(getActivity(), gastos);
 recyclerView.setAdapter(mAdapter);
 }

 **/