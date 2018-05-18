package com.example.apoh.schoolboard.telas;

import android.app.Dialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apoh.schoolboard.R;
import com.example.apoh.schoolboard.banco.DisciplinaDAO;
import com.example.apoh.schoolboard.model.Disciplina;

import java.util.ArrayList;


public class TelaPrincipal extends AppCompatActivity {

    EditText campoDisciplina,campoProfessor, campoContador;
    Button botaoSalvar = null;
    FloatingActionButton botaoAdd = null;
    DisciplinaDAO vrbancoDados = null;

    ArrayList<Disciplina> dataSource = null;
    RecyclerView lista = null;


    private RecyclerView listaMaterias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tela_principal);

        botaoAdd = (FloatingActionButton)findViewById(R.id.btnNovaMateria);
        botaoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayInputDialog();
            }
        });

    }

    //DISPLAY INPUT DIALOG
    private void displayInputDialog()
    {
        Dialog d=new Dialog(this);
        d.setTitle("Insira a Disciplina");
        d.setContentView(R.layout.input_dialog);

        campoDisciplina = d.findViewById(R.id.inputDialogEditDisciplina);
        campoProfessor = d.findViewById(R.id.inputDialogEditProfessor);
        botaoSalvar = d.findViewById(R.id.inputDialogBotaoSalvar);

        vrbancoDados = new DisciplinaDAO(this, "BDDisciplina", 1);

        //SAVE
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues dados = new ContentValues();
                dados.put("nomeDisciplina", campoDisciplina.getText().toString());
                dados.put("nomeProfessor",campoProfessor.getText().toString());
                vrbancoDados.inserirDisciplina(dados);

                Log.i("Info", dados.toString());

/*
                //SIMPLE VALIDATION
                if(campoDisciplina != null && campoDisciplina.length()>0) {
                    //THEN SAVE
                    if(helper.save(dados)) {
                        //IF SAVED CLEAR EDITXT
                        campoDisciplina.setText("");
                        campoProfessor.setText("");

                        com.example.apoh.schoolboard.adapter=new MyAdapter(MainActivity.this,helper.retrieve());
                        rv.setAdapter(com.example.apoh.schoolboard.adapter);

                    }
                }else {
                    Toast.makeText(TelaPrincipal.this, "Nome não pode ser vazio", Toast.LENGTH_SHORT).show();
                }*/

            }
        });

        d.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.lista) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
