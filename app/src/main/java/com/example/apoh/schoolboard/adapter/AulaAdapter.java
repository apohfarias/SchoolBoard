package com.example.apoh.schoolboard.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apoh.schoolboard.R;
import com.example.apoh.schoolboard.holder.AulaHolder;
import com.example.apoh.schoolboard.model.Aula;
import com.example.apoh.schoolboard.telas.TelaAula;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AulaAdapter extends RecyclerView.Adapter<AulaHolder>{
    private static final String TAG = "RecyclerViewAdapterAula";

    Context contexto = null;
    ArrayList<Aula> lista = null;

    public AulaAdapter(Context contexto, ArrayList<Aula> lista) {
        this.contexto = contexto;
        this.lista = lista;
    }

    @NonNull
    @Override
    public AulaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View celula = LayoutInflater.from(contexto).inflate(R.layout.activity_tela_aula, parent,false );
        AulaHolder item = new AulaHolder(celula);
        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull AulaHolder holder, int position) {
        Aula item = lista.get(position);
        Log.d(TAG, "onBindViewHolder: called.");

        holder.getTextoNome().setText(item.getNomeAula());
        holder.getTextoDataCriacao().setText(item.getDataCriacao());

    }

    @Override
    public int getItemCount() {
        return (lista != null) ? lista.size() : 0;
    }

}