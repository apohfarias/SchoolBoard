package com.example.apoh.schoolboard.holder;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apoh.schoolboard.R;

class ItemAulaHolder extends RecyclerView.ViewHolder {

    ImageView campoFoto = null; //Ver como setar a imagem

    ItemAulaHolder(View view){
        super(view);
        campoFoto = (ImageView) itemView.findViewById(R.id.TelaItemAula_ImageView);
        //textoDisciplina = (TextView)view.findViewById(R.id.TelaPrincipalDisciplina);
    }
}

public class ItemAula {
    ImageView imagemV;

    public ItemAula(ImageView imagem){
        imagemV = imagem;

    }

    public ImageView getImagemV() {
        return imagemV;
    }

    public void setImagemV(ImageView imagemV) {
        this.imagemV = imagemV;
    }
}


