package com.example.adriana.piggybank_moviles;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class ActivityRetoDia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reto_dia);
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

                Intent intent2= new Intent(ActivityRetoDia.this,ActivityMain.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent2);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
