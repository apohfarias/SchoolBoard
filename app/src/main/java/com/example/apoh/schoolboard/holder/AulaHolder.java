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
    ImageView campoFoto = null; //Ver como setar a imagem

    TextView nameProfes, nameDisci;
    RelativeLayout parentLayout;

    public AulaHolder(View itemView) {
        super(itemView);

        textoConteudoAula = (TextView)itemView.findViewById(R.id.TelaItemAulaConteudoAula);
        textoDataCriacao = (TextView)itemView.findViewById(R.id.TelaItemAulaData);

        //imageFoto = (ImageView)itemView.findViewById(R.id.TelaItemAulaImage1);

        //dados.put("caminhoFoto", (String) campoFoto.getTag());

        //Para setar o nome da materia na tela de aula
        nameProfes = itemView.findViewById(R.id.TelaAulaProfessor);
        nameDisci = itemView.findViewById(R.id.TelaAulaDisciplina);
        campoFoto = (ImageView) itemView.findViewById(R.id.TelaItemAulaImage1);

    }



    //GETTERS E SETTERS
    public TextView getTextoNome() {
        return textoConteudoAula;
    }

    public void setTextoNome(TextView textoNome) {
        this.textoConteudoAula = textoNome;
    }


    public TextView getTextoConteudoAula() {
        return textoConteudoAula;
    }

    public void setTextoConteudoAula(TextView textoConteudoAula) {
        this.textoConteudoAula = textoConteudoAula;
    }

    public ImageView getCampoFoto() {
        return campoFoto;
    }

    public void setCampoFoto(ImageView campoFoto) {
        this.campoFoto = campoFoto;
    }

    public TextView getTextoDataCriacao() {
        return textoDataCriacao;
    }

    public void setTextoDataCriacao(TextView textoDataCriacao) {
        this.textoDataCriacao = textoDataCriacao;
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

    public RelativeLayout getParentLayout() {
        return parentLayout;
    }

    public void setParentLayout(RelativeLayout parentLayout) {
        this.parentLayout = parentLayout;
    }
}
