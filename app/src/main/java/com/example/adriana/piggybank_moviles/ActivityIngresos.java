package com.example.adriana.piggybank_moviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.TextureView;
import android.widget.Button;
import android.widget.TextView;

public class ActivityIngresos extends AppCompatActivity {

    TextView ingresos;

    Button addIngresos, addAhorros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresos);

        ingresos = findViewById(R.id.activity_ingresos_ingreso);
        addIngresos = findViewById(R.id.activity_ingresos_btningreso);
        addAhorros = findViewById(R.id.activity_ingresos_btnahorro);

        ingresos.setText("$ 20,000.00");
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
                Intent intent2= new Intent(ActivityIngresos.this,ActivityMain.class); startActivity(intent2);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
