package com.example.apoh.schoolboard.holder;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.apoh.schoolboard.R;


public class AulaHolder extends RecyclerView.ViewHolder{
    TextView textoConteudoAula = null;
    TextView textoDataCriacao = null;
    TextView nameProfes = null;
    TextView nameDisci = null;

    ImageView imageViewFoto; //Ver como setar a imagem
    byte[] fotoArray;


    public AulaHolder(View itemView) {
        super(itemView);

        /*textoConteudoAula = (TextView)itemView.findViewById(R.id.TelaItemAulaConteudoAula);
        textoDataCriacao = (TextView)itemView.findViewById(R.id.TelaItemAulaData);
        nameProfes = itemView.findViewById(R.id.TelaAulaProfessor);
        nameDisci = itemView.findViewById(R.id.TelaAulaDisciplina);*/

        imageViewFoto = (ImageView) itemView.findViewById(R.id.TelaItemAula_ImageView);
        //fotoArray = carro.getFoto();

    }

    //GETTERS E SETTERS

    public TextView getTextoConteudoAula() {
        return textoConteudoAula;
    }

    public void setTextoConteudoAula(TextView textoConteudoAula) {
        this.textoConteudoAula = textoConteudoAula;
    }

    public TextView getTextoDataCriacao() {
        return textoDataCriacao;
    }

    public void setTextoDataCriacao(TextView textoDataCriacao) {
        this.textoDataCriacao = textoDataCriacao;
    }

    public ImageView getImageViewFoto() {
        return imageViewFoto;
    }

    public void setImageViewFoto(ImageView imageViewFoto) {
        this.imageViewFoto = imageViewFoto;
    }

    public TextView getNameProfes() {
        return nameProfes;
    }

    public void setNameProfes(TextView nameProfes) {
        this.nameProfes = nameProfes;
    }

    public TextView getNameDisci() {
        return nameDisci;
    }

    public void setNameDisci(TextView nameDisci) {
        this.nameDisci = nameDisci;
    }

    public byte[] getFotoArray() {
        return fotoArray;
    }

    public void setFotoArray(byte[] fotoArray) {
        this.fotoArray = fotoArray;
    }
}
