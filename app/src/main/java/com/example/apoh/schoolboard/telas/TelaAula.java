package com.example.apoh.schoolboard;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

class AdaptadorAula extends RecyclerView.Adapter<ItemHolderAula>{
    Context contexto = null;

    @NonNull
    @Override
    public ItemHolderAula onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View celula = LayoutInflater.from(contexto).inflate(R.layout.item_lista_aula, parent,false );
        ItemHolderAula item = new ItemHolderAula(celula);
        return item;

    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolderAula holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}


public class TelaAula extends AppCompatActivity {
    Button btnCapturar, btnGaleria;
    //ImageView imageView;
    private ImageView foto = null;

    final int REQUEST_CODE_GALLERY = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_aula);

        foto = (ImageView)findViewById(R.id.imageView2);

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
