package com.example.apoh.schoolboard.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.apoh.schoolboard.R;

class ItemHolder extends RecyclerView.ViewHolder {

    TextView textoDisciplina = null;
    TextView textoProfessor = null;
    TextView textoContador = null;

    ItemHolder(View view){
        super(view);

        textoDisciplina = (TextView)view.findViewById(R.id.TelaPrincipalDisciplina);
        textoProfessor = (TextView)view.findViewById(R.id.TelaPrincipalProfessor);
        textoContador = (TextView)view.findViewById(R.id.TelaPrincipalContador);

    }
}

public class ItemListaPrincipal {
    String disciplina;
    String professor;
    String contador;

    public ItemListaPrincipal(String sDisciplina, String sProfessor, String sContador){

        disciplina = sDisciplina;
        professor = sProfessor;
        contador = sContador;

    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getContador() {
        return contador;
    }

    public void setContador(String contador) {
        this.contador = contador;
    }
}

