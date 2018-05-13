package com.example.adriana.piggybank_moviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class ActivityMetaDetail extends AppCompatActivity {
    CircularProgressBar progressBar;
    TextView title, cantMeta, cantAhorro, fechaLim;
    itemMeta Meta;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meta_detail);

        progressBar = findViewById(R.id.meta_detail_progressbar);
        title = findViewById(R.id.meta_detail_title);
        cantMeta = findViewById(R.id.meta_detail_cantidadMeta);
        cantAhorro = findViewById(R.id.meta_detail_cantidadAhorro);
        fechaLim = findViewById(R.id.meta_detail_fechaLim);

        if(getIntent().getExtras() != null){
            Meta = getIntent().getParcelableExtra("Detalle Meta");
            if(Meta != null){
                id = Meta.getId();
                title.setText(Meta.getName());
                cantMeta.setText(""+Meta.getCantidad());
                cantAhorro.setText(""+ Meta.getAhorrado());
                fechaLim.setText(Meta.getFechaLimite());
                progressBar.setProgress((float) ((Meta.getAhorrado()/Meta.getCantidad())*100));
            }
        }

    }

}
