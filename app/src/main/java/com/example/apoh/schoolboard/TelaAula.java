package com.example.apoh.schoolboard;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class TelaAula extends AppCompatActivity {
    Button btnCapturar, btnGaleria;
    ImageView imageView;
    private ImageView foto = null;

    final int REQUEST_CODE_GALLERY = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_aula);

        foto = (ImageView)findViewById(R.id.imageView);

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
                foto.setImageBitmap(imagem);

            }
        }
    }



}
