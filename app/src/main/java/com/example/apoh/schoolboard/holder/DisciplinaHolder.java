package com.example.apoh.schoolboard.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.apoh.schoolboard.R;

public class DisciplinaHolder extends RecyclerView.ViewHolder{
    TextView textoDisciplina = null;
    TextView textoProfessor = null;
    TextView textoContador = null;

    public DisciplinaHolder(View itemView) {
        super(itemView);

        textoDisciplina = (TextView)itemView.findViewById(R.id.TelaPrincipalDisciplina);
        textoProfessor = (TextView)itemView.findViewById(R.id.TelaPrincipalProfessor);
        textoContador = (TextView)itemView.findViewById(R.id.TelaPrincipalContador);
    }

    public TextView getTextoDisciplina() {
        return textoDisciplina;
    }

    public void setTextoDisciplina(TextView textoDisciplina) {
        this.textoDisciplina = textoDisciplina;
    }

    public TextView getTextoProfessor() {
        return textoProfessor;
    }

    public void setTextoProfessor(TextView textoProfessor) {
        this.textoProfessor = textoProfessor;
    }

    public TextView getTextoContador() {
        return textoContador;
    }

    public void setTextoContador(TextView textoContador) {
        this.textoContador = textoContador;
    }
}