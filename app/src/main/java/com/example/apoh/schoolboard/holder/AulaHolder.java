package com.example.apoh.schoolboard.holder;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apoh.schoolboard.R;

public class AulaHolder extends RecyclerView.ViewHolder{
    TextView textoNome = null;
    TextView textoDataCriacao = null;
    ImageView imageFoto = null; //Ver como setar a imagem

    public AulaHolder(View itemView) {
        super(itemView);

        textoNome = (TextView)itemView.findViewById(R.id.textAula);
        textoDataCriacao = (TextView)itemView.findViewById(R.id.textDataAula);

    }

    public TextView getTextoNome() {
        return textoNome;
    }

    public void setTextoNome(TextView textoNome) {
        this.textoNome = textoNome;
    }

    public TextView getTextoDataCriacao() {
        return textoDataCriacao;
    }

    public void setTextoDataCriacao(TextView textoDataCriacao) {
        this.textoDataCriacao = textoDataCriacao;
    }
}