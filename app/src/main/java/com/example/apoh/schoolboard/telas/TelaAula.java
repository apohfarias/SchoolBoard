package com.example.apoh.schoolboard.telas;


import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apoh.schoolboard.R;
import com.example.apoh.schoolboard.adapter.AdapterListener;
import com.example.apoh.schoolboard.adapter.AulaAdapter;
import com.example.apoh.schoolboard.adapter.DisciplinaAdapter;
import com.example.apoh.schoolboard.banco.AulaDAO;
import com.example.apoh.schoolboard.banco.DisciplinaDAO;
import com.example.apoh.schoolboard.model.Aula;
import com.example.apoh.schoolboard.model.Disciplina;

import java.util.ArrayList;

public class TelaAula extends AppCompatActivity {
    private static final String TAG = "TelaAula";

    private ImageView setarFoto = null;

    AulaDAO vrbancoDados = null;
    EditText campoConteudo, campoData;
    public TextView nameProfes, nameDisci;
    FloatingActionButton botaoAddAula = null;
    ImageView imagem;
    RecyclerView lista = null;
    ArrayList<Aula> aulas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_aula);

        nameDisci = findViewById(R.id.TelaAulaDisciplina);
        nameProfes = findViewById(R.id.TelaAulaProfessor);

        String Materia = this.getIntent().getStringExtra("Materia");
        String Professor = this.getIntent().getStringExtra("Professor");

        nameDisci.setText(Materia);
        nameProfes.setText(Professor);

        //CRIAR TELA PARA CADASTRO DA AULA E CHAMAR BTNOVAAULA
        /*botaoAddAula = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        botaoAddAula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //displayInputDialog(); CRIAR CADASTRO DA AULA
            }
        });
        listarAulas(lista);*/

    }

    public void gravarAulas(View v) {
        vrbancoDados = new AulaDAO(this, "BDSchoolBoard", 1);

        ContentValues dados = new ContentValues();
        dados.put("nomeAula", campoConteudo.getText().toString());
        dados.put("dataCriacao", campoData.getText().toString());
        //dados.put("imagem", imagem);

        vrbancoDados.inserirAula(dados);

        Log.i("Info", dados.toString());

    }

    public void listarAulas(View v) {
        vrbancoDados = new AulaDAO(this, "BDSchoolBoard", 1);

        aulas = vrbancoDados.buscarAulas();

        lista = findViewById(R.id.recyclerViewAulas);
        lista.setLayoutManager(new LinearLayoutManager(this));
        lista.setItemAnimator(new DefaultItemAnimator());
        lista.setHasFixedSize(true);

        AulaAdapter adapt = new AulaAdapter(this, aulas);
        adapt.notifyDataSetChanged();
        lista.setAdapter(adapt);
    }


/*    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        if(getIntent().hasExtra("TelaPrincipalProfessor") && getIntent().hasExtra("TelaPrincipalDisciplina")){
            Log.d(TAG, "getIncomingIntent: found intent extras.");

            String textProfessor = getIntent().getStringExtra("Materia");
            String textDisciplina = getIntent().getStringExtra("Professor");

            setName(textProfessor, textDisciplina);
        }
    }*/

/*    private void setName(String nomeProfessor, String nomeDisciplina){
        Log.d(TAG, "setName: setting nomes to widgets.");

        TextView nameProfes = findViewById(R.id.TelaAulaProfessor);
        nameProfes.setText(nomeProfessor);

        TextView nameDisci = findViewById(R.id.TelaAulaDisciplina);
        nameDisci.setText(nomeDisciplina);

    }*/


    public void capturaImagem(View botao) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    public void onActivityResult(int codigo, int resultado, Intent dados) {
        super.onActivityResult(codigo, resultado, dados);

        if (dados != null) {
            Bundle bundle = dados.getExtras();

            if (bundle != null) {
                Bitmap imagem = (Bitmap)bundle.get("data");
                setarFoto.setImageBitmap(imagem);

            }
        }
    }

}
