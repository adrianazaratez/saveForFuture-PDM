package com.example.adriana.piggybank_moviles;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class ActivityMetas extends AppCompatActivity {

    private AdapterMetas mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<itemMeta> metas;
    RecyclerView recyclerView;

    public ActivityMetas(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metas);


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
