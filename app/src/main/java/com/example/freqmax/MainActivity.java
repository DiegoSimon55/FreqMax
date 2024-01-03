package com.example.freqmax;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.Collections;
import java.util.Comparator;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText txtNome;
    EditText TextNumber;
    Button btAdicionarCalcular;
    ListView listAtletas;
    List<Atleta> atletas = new ArrayList<>();
    AtletaAdapter adapter;

    View.OnClickListener btClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            Log.d("EVT", "EVT Click no botão!");
            CliqueAdicionarCalcular(v);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNome = findViewById(R.id.txtNome);
        TextNumber = findViewById(R.id.TextNumber);
        btAdicionarCalcular = findViewById(R.id.btAdicionarCalcular);
        btAdicionarCalcular.setOnClickListener(btClickListener);
        listAtletas = findViewById(R.id.listAtletas);
        adapter = new AtletaAdapter(atletas, getBaseContext());
        listAtletas.setAdapter(adapter);
    }

    public void CliqueAdicionarCalcular (View v){
        Atleta a = new Atleta();
        a.setNome(txtNome.getText().toString());
        a.setIdade(Integer.parseInt(TextNumber.getText().toString()));

        // Calculando a FCM e definindo-a no objeto Atleta
        int frequenciaCardiacaMaxima = 220 - a.getIdade();
        a.setFCM(frequenciaCardiacaMaxima);

        atletas.add(a);

        // Ordena a lista de atletas com base na FCM (do mais alto para o mais baixo)
        Collections.sort(atletas, new Comparator<Atleta>() {
            @Override
            public int compare(Atleta a1, Atleta a2) {
                return Integer.compare(a2.getFCM(), a1.getFCM());
            }
        });

        // Notifica o adapter sobre as mudanças na lista
        adapter.notifyDataSetChanged();
    }
}