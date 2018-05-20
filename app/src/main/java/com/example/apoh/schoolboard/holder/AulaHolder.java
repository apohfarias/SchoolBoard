package com.example.apoh.schoolboard.holder;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apoh.schoolboard.R;

public class AulaHolder extends RecyclerView.ViewHolder{
    TextView textoConteudoAula = null;
    TextView textoDataCriacao = null;
    ImageView imageFoto = null; //Ver como setar a imagem

    public AulaHolder(View itemView) {
        super(itemView);

        textoConteudoAula = (TextView)itemView.findViewById(R.id.TelaItemAulaConteudoAula);
        textoDataCriacao = (TextView)itemView.findViewById(R.id.TelaItemAulaData);
        imageFoto = (ImageView)itemView.findViewById(R.id.imageView2);

    }

    public TextView getTextoNome() {
        return textoConteudoAula;
    }

    public void setTextoNome(TextView textoNome) {
        this.textoConteudoAula = textoNome;
    }

    public TextView getTextoDataCriacao() {
        return textoDataCriacao;
    }

    public void setTextoDataCriacao(TextView textoDataCriacao) {
        this.textoDataCriacao = textoDataCriacao;
    }

}
