package com.example.apoh.schoolboard.telas;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.apoh.schoolboard.R;
import com.example.apoh.schoolboard.adapter.DisciplinaAdapter;
import com.example.apoh.schoolboard.holder.ItemListaPrincipal;
import com.example.apoh.schoolboard.model.Disciplina;

import java.util.ArrayList;


public class TelaPrincipal extends AppCompatActivity {
    EditText nomeEditTxt,profTxt;


    ArrayList<Disciplina> dataSource = null;
    RecyclerView lista = null;
    FloatingActionButton btnNovaDisciplina = null;

    private RecyclerView listaMaterias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tela_principal);

        FloatingActionButton botaoAdd = (FloatingActionButton)findViewById(R.id.btnNovaMateria);
        botaoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayInputDialog();
            }
        });


        //Adicionando fixamente os itens, exemplo


        lista = (RecyclerView)findViewById(R.id.lista);
        lista.setLayoutManager(new LinearLayoutManager(this));
        lista.setItemAnimator(new DefaultItemAnimator());
        lista.setHasFixedSize(true);

        DisciplinaAdapter adapt = new DisciplinaAdapter(this, dataSource);
        lista.setAdapter(adapt);

        lista = (RecyclerView) findViewById(R.id.lista);

        //return view;

        //btnNovaDisciplina = (FloatingActionButton)view.findViewById(R.id.salvarBtn);


    }

    //DISPLAY INPUT DIALOG
    private void displayInputDialog()
    {
        Dialog d=new Dialog(this);
        d.setTitle("Insira a Disciplina");
        d.setContentView(R.layout.input_dialog);

        nomeEditTxt = (EditText) d.findViewById(R.id.nomeEditText);
        profTxt = (EditText) d.findViewById(R.id.professorEditText);
        Button salvarBtn = (Button) d.findViewById(R.id.salvarBtn);

        //SAVE
        salvarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //PEGA DADOS
                String nome = nomeEditTxt.getText().toString();
                String professor = profTxt.getText().toString();

                //SETA DADOS
                Disciplina d = new Disciplina();
                d.setNome_disciplina(nome);
                d.setProfessor(professor);

                dataSource.add(new Disciplina(nome, professor));

/*
                //SIMPLE VALIDATION
                if(nome != null && nome.length()>0)
                {
                    //THEN SAVE
                    if(helper.save(d))
                    {
                        //IF SAVED CLEAR EDITXT
                        nomeEditTxt.setText("");
                        profTxt.setText("");

                        com.example.apoh.schoolboard.adapter=new MyAdapter(MainActivity.this,helper.retrieve());
                        rv.setAdapter(com.example.apoh.schoolboard.adapter);


                    }
                }else
                {
                    Toast.makeText(MainActivity.this, "Name Must Not Be Empty", Toast.LENGTH_SHORT).show();
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
