package com.example.adriana.piggybank_moviles;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.adriana.piggybank_moviles.beans.Meta;
import com.example.adriana.piggybank_moviles.beans.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class ActivityMetas extends AppCompatActivity {

    private AdapterMetas mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<itemMeta> metas;
    RecyclerView recyclerView;

    DatabaseReference databaseReference;
    String id;
    String idfirebase;
    Usuario user;

    ArrayList<Usuario> usersList = new ArrayList<>();
    ArrayList<String> fbUserIDList = new ArrayList<>();
    HashMap<String,Boolean> meta= new HashMap<>();
    ArrayList<Meta> metasList = new ArrayList<>();
    ArrayList<String> fbmetaIDList = new ArrayList<>();
    ArrayList<Meta> metax = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metas);
        metas = new ArrayList<>();
        id = getIntent().getExtras().getString("ID");
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.containerMetas);
        Log.e("ID",id);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                usersList.clear();
                fbUserIDList.clear();
                meta.clear();
                fbmetaIDList.clear();
                for(DataSnapshot snapshot : dataSnapshot.child("user").getChildren()){
                    Usuario value = snapshot.getValue(Usuario.class);
                    idfirebase = snapshot.getKey();
                    Log.e("FIREBASE", idfirebase+" "+value.toString());
                    usersList.add(value);
                    fbUserIDList.add(idfirebase);
                }
                for(DataSnapshot snapshot : dataSnapshot.child("meta").getChildren()){
                    Meta value = snapshot.getValue(Meta.class);
                    idfirebase = snapshot.getKey();
                    Log.e("FIREBASE", idfirebase +" "+value.toString());
                    metasList.add(value);
                    fbmetaIDList.add(idfirebase);
                }

                doThings();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityMetas.this, ActivityNewgoal.class);
                intent.putExtra("ID",id);
                startActivity(intent);
                finish();
            }
        });

    }
    public void doThings() {

        for (int i = 0; i < fbUserIDList.size(); i++) {
            if (id.equals(fbUserIDList.get(i))) {
                user = usersList.get(i);
                break;
            }
        }

        meta = user.getMeta();
        if(meta == null){
            Toast.makeText(this, "No tienes metas registradas", Toast.LENGTH_LONG).show();
        }
        else {

            for(Map.Entry<String,Boolean>entry : meta.entrySet()){
                String key = entry.getKey();
                for(int i = 0; i < fbmetaIDList.size(); i++) {
                    if (key.equals(fbmetaIDList.get(i))){
                        metax.add(metasList.get(i));
                        break;
                    }
                }
            }

            for(int i = 0; i<metax.size(); i++){
                Random rand = new Random();
                int valrand= rand.nextInt(100);
                metas.add(new itemMeta(metax.get(i).getNombre(),""+valrand+"%", valrand));
            }}
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new AdapterMetas(this,metas);
        recyclerView.setAdapter(mAdapter);
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
                Intent intent2= new Intent(ActivityMetas.this,ActivityMain.class); startActivity(intent2);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        itemMeta meta = data.getParcelableExtra("Detalle Meta");
        Iterator<itemMeta> iterator = metas.iterator();
        int position = 0;
        while(iterator.hasNext()){
            itemMeta item = iterator.next();
            if(item.getName() == meta.getName()){
                metas.set(position, meta);
                break;
            }
            position++;
        }
        mAdapter.notifyDataSetChanged();

    }
}

