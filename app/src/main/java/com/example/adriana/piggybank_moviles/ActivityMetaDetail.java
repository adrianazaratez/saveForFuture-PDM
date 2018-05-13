package com.example.adriana.piggybank_moviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
                title.setText(Meta.getName());
                progressBar.setProgress(Meta.getProgress());
            }
        }
        int cantM = 100;
        int ahorroaux = (int) (cantM * progressBar.getProgress());
        cantAhorro.setText(""+ ahorroaux);

        /**
        Intent intent = new Intent(ActivityMetaDetail.this, ActivityMetas.class);
        intent.putExtra("ID",id);
        startActivity(intent);
        finish();
         */
    }

}
