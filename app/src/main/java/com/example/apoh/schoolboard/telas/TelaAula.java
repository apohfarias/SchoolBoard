package com.example.apoh.schoolboard.telas;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

    public static final int CODIGO_CAMERA = 567;

    AulaDAO vrbancoDados = null;
    EditText campoConteudo, campoData;
    public TextView nameProfes, nameDisci;
    private ImageView campoFoto;
    private String caminhoFoto;

    FloatingActionButton botaoAddAula = null;
    RecyclerView lista = null;
    ArrayList<Aula> aulas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_aula);

        campoFoto = (ImageView) findViewById(R.id.TelaItemAulaImage1);
        nameDisci = findViewById(R.id.TelaAulaDisciplina);
        nameProfes = findViewById(R.id.TelaAulaProfessor);

        String Materia = this.getIntent().getStringExtra("Materia");
        String Professor = this.getIntent().getStringExtra("Professor");

        nameDisci.setText(Materia);
        nameProfes.setText(Professor);

        //aulas = vrbancoDados.buscarAulas();

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
        dados.put("caminhoFoto", (String) campoFoto.getTag());

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


    //Captura foto com a camera
    public void capturaImagem(View botao) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    public void onActivityResult(int codigo, int resultado, Intent dados) {
        super.onActivityResult(codigo, resultado, dados);

        if (dados != null) {
            Bundle bundle = dados.getExtras();

            if (resultado == Activity.RESULT_OK) {
                if (codigo == CODIGO_CAMERA) {
                    carregaImagem(caminhoFoto);
                }
            }

/*            if (bundle != null) {
                Bitmap imagem = (Bitmap) bundle.get("data");
                //campoFoto.setImageBitmap(imagem);
                //campoFoto.setTag(caminhoFoto);

            }*/
        }
    }

    public void carregaImagem(String caminhoFoto) {
        if (caminhoFoto != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            campoFoto.setImageBitmap(bitmapReduzido);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
            campoFoto.setTag(caminhoFoto);
        }
    }

}
