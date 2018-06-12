package com.example.apoh.schoolboard.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.apoh.schoolboard.R;
import com.example.apoh.schoolboard.banco.DisciplinaDAO;
import com.example.apoh.schoolboard.holder.ItemListaPrincipal;
import com.example.apoh.schoolboard.telas.TelaAula;
import com.example.apoh.schoolboard.holder.DisciplinaHolder;
import com.example.apoh.schoolboard.model.Disciplina;
import com.example.apoh.schoolboard.telas.TelaPrincipal;

import java.util.ArrayList;

public class DisciplinaAdapter extends RecyclerView.Adapter<DisciplinaHolder> {
    Context contexto = null;
    ArrayList<Disciplina> lista = null;
    AdapterListener listener = null;
    private AlertDialog alerta;

    public DisciplinaAdapter(Context contexto, ArrayList<Disciplina> lista, AdapterListener listener) {
        this.contexto = contexto;
        this.lista = lista;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DisciplinaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View celula = LayoutInflater.from(contexto).inflate(R.layout.layout_item_lista_principal, parent,false );
        DisciplinaHolder item = new DisciplinaHolder(celula);
        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull final DisciplinaHolder holder, final int position) {
        Disciplina item = lista.get(position);


        holder.getTextoDisciplina().setText(item.getNome_disciplina());
        holder.getTextoProfessor().setText(item.getProfessor());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.celulaClicada(holder.itemView, position);

            }
        });


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
                builder.setTitle("Tem certeza disso?");
                //Definição da mensagem
                builder.setMessage("Apagar " + lista.get(position).getNome_disciplina());
                //define um botão como positivo
                builder.setPositiveButton("Apagar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                        DisciplinaDAO vrbancoDados = new DisciplinaDAO(contexto, "BDSchoolBoard",1);
                        vrbancoDados.deletarDisciplina(lista.get(position));
                        notifyItemRemoved(position);
                        Toast.makeText(contexto, lista.get(position).getNome_disciplina() +" Apagada", Toast.LENGTH_SHORT).show();


                    }
                });
                //define um botão como negativo.
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });
                //cria o AlertDialog
                alerta = builder.create();
                //Exibe
                alerta.show();
                return true;
            }

        });

    }




    @Override
    public int getItemCount() {
        return (lista != null) ? lista.size() : 0;

    }
}