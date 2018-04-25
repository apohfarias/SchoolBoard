package com.example.apoh.schoolboard;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

class ItemHolder extends RecyclerView.ViewHolder {

    TextView textoDisciplina = null;
    TextView textoProfessor = null;
    TextView textoContador = null;

    ItemHolder(View view){
        super(view);

        textoDisciplina = (TextView)view.findViewById(R.id.textViewDisc);
        textoProfessor = (TextView)view.findViewById(R.id.textViewProf);
        textoContador = (TextView)view.findViewById(R.id.textViewCont);

    }
}

public class ItemListaPrincipal {
    String disciplina;
    String professor;
    String contador;

    ItemListaPrincipal( String sDisciplina, String sProfessor, String sContador){

        disciplina = sDisciplina;
        professor = sProfessor;
        contador = sContador;

    }
}

