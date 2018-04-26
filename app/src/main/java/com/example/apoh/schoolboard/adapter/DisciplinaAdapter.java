package com.example.apoh.schoolboard.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apoh.schoolboard.R;
import com.example.apoh.schoolboard.TelaAula;
import com.example.apoh.schoolboard.holder.DisciplinaHolder;
import com.example.apoh.schoolboard.model.Disciplina;

import java.util.ArrayList;

public class DisciplinaAdapter extends RecyclerView.Adapter<DisciplinaHolder> {
    Context contexto = null;
    ArrayList<Disciplina> lista = null;

    public DisciplinaAdapter(Context contexto, ArrayList<Disciplina> lista) {

        this.contexto = contexto;
        this.lista = lista;

    }

    @NonNull
    @Override
    public DisciplinaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View celula = LayoutInflater.from(contexto).inflate(R.layout.layout_item_lista_principal, parent,false );
        DisciplinaHolder item = new DisciplinaHolder(celula);
        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull DisciplinaHolder holder, final int position) {
        Disciplina item = lista.get(position);


        holder.getTextoDisciplina().setText(item.getNome_disciplina());
        holder.getTextoProfessor().setText(item.getProfessor());
/*
        holder.getTextoContador().setText(item.getDataCriacao());
*/

        //Quando o item é clicado
        holder.getTextoProfessor().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                contexto = view.getContext();
                Intent intent = new Intent(contexto, TelaAula.class);
                String posicao = Integer.toString(position);
                Log.d("posicao", posicao);

                intent.putExtra("Disciplina", lista.get(position).getNome_disciplina());

                contexto.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (lista != null) ? lista.size() : 0;

    }
}