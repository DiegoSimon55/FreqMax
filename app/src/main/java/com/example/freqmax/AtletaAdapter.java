package com.example.freqmax;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AtletaAdapter extends BaseAdapter {
    LayoutInflater inflater;
    List<Atleta> atletas;

    public AtletaAdapter (List<Atleta> atletas, Context ctx) {
        this.atletas = atletas;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return atletas.size();
    }

    @Override
    public Object getItem(int position) {
        return atletas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        View v = convertview;
        if (v == null) {
            v = inflater.inflate(R.layout.atleta_item, parent, false);
        }

        TextView txtNome = v.findViewById(R.id.txtNomeitem);
        TextView txtFCM = v.findViewById(R.id.txtFCMitem);

        Atleta p = atletas.get(position);
        txtNome.setText(p.getNome());

        // Verifica se a FCM já foi calculada para exibi-la
        if (p.getFCM() != 0) {
            txtFCM.setText("FCM: " + String.valueOf(p.getFCM()));
        } else {
            txtFCM.setText("FCM: -"); // Caso a FCM não tenha sido calculada ainda
        }
        return v;
    }
}
