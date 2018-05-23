package com.example.apoh.schoolboard.telas;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apoh.schoolboard.R;

public class TelaAula extends AppCompatActivity {

    TextView setarProfessor, setarDisciplina;
    private ImageView setarFoto = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_aula);

        setarFoto = findViewById(R.id.TelaItemAulaImage1);
        setarDisciplina = findViewById(R.id.TelaAulaDisciplina);
        setarProfessor = findViewById(R.id.TelaAulaProfessor);

    }

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
