package com.example.apoh.schoolboard.telas;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apoh.schoolboard.R;
import com.example.apoh.schoolboard.adapter.DisciplinaAdapter;
import com.example.apoh.schoolboard.banco.DisciplinaDAO;
import com.example.apoh.schoolboard.holder.ItemListaPrincipal;
import com.example.apoh.schoolboard.model.Disciplina;
import com.example.apoh.schoolboard.adapter.*;

import java.util.ArrayList;


public class TelaPrincipal extends AppCompatActivity {

    private Context contexto;
    EditText campoDisciplina, campoProfessor, campoContador;
    Button botaoSalvar = null;
    FloatingActionButton botaoAdd = null;
    DisciplinaDAO vrbancoDados = null;

    RecyclerView lista = null;
    //ArrayList<ItemListaPrincipal> dataSource = null;
    ArrayList<Disciplina> disciplinas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tela_principal);


        botaoAdd = (FloatingActionButton) findViewById(R.id.btnNovaMateria);
        botaoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayInputDialog();
            }
        });

        listarDisciplinas(lista);

    }

    private AdapterListener criaListener(){
        return new AdapterListener(){
            @Override
            public void celulaClicada(View view, int posicao){
                //chama outra tela
                contexto = view.getContext();
                Intent intent = new Intent(contexto, TelaAula.class);
                contexto.startActivity(intent);

            }
        };
    }

    public void gravarDisciplinas(View v) {
        vrbancoDados = new DisciplinaDAO(this, "BDSchoolBoard", 1);
        ContentValues dados = new ContentValues();
        dados.put("nomeDisciplina", campoDisciplina.getText().toString());
        dados.put("nomeProfessor", campoProfessor.getText().toString());
        vrbancoDados.inserirDisciplina(dados);

        Log.i("Info", dados.toString());

    }

    public void listarDisciplinas(View v) {
        vrbancoDados = new DisciplinaDAO(this, "BDSchoolBoard", 1);

        disciplinas = vrbancoDados.buscarDisciplinas();

        lista = findViewById(R.id.lista);
        lista.setLayoutManager(new LinearLayoutManager(this));
        lista.setItemAnimator(new DefaultItemAnimator());
        lista.setHasFixedSize(true);

        DisciplinaAdapter adapt = new DisciplinaAdapter(this, disciplinas, criaListener());
        lista.setAdapter(adapt);
    }

    //DISPLAY INPUT DIALOG
    private void displayInputDialog() {
        Dialog d = new Dialog(this);
        d.setTitle("Insira a Disciplina");
        d.setContentView(R.layout.input_dialog);

        campoDisciplina = d.findViewById(R.id.inputDialogEditDisciplina);
        campoProfessor = d.findViewById(R.id.inputDialogEditProfessor);
        botaoSalvar = d.findViewById(R.id.inputDialogBotaoSalvar);


        //BOTÃO SALVAR
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gravarDisciplinas(v);
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
