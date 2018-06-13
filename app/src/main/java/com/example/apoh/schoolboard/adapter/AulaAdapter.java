package com.example.apoh.schoolboard.adapter;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.apoh.schoolboard.BuildConfig;
import com.example.apoh.schoolboard.R;
import com.example.apoh.schoolboard.banco.AulaDAO;
import com.example.apoh.schoolboard.holder.AulaHolder;
import com.example.apoh.schoolboard.model.Aula;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;


public class AulaAdapter extends RecyclerView.Adapter<AulaHolder> {

    Context contexto = null;
    ArrayList<Aula> lista = null;
    AdapterListener listener = null;
    AulaDAO vrbancoDados = null;
    public ImageView imagem;


    public AulaAdapter(Context contexto, ArrayList<Aula> lista) {
        this.contexto = contexto;
        this.lista = lista;
        //this.listener = listener;
    }

    @NonNull
    @Override
    public AulaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View celula = LayoutInflater.from(contexto).inflate(R.layout.layout_item_aula, parent,false );
        AulaHolder item = new AulaHolder(celula);
        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull AulaHolder holder, int position) {
        Aula item = lista.get(position);
        int resID = contexto.getResources().getIdentifier(holder.imageViewFoto.toString(), "drawable", contexto.getPackageName());


        if (item.getFoto().isEmpty()) {
            holder.getImageViewFoto().setImageResource(R.mipmap.ic_launcher);
        } else{
            Picasso.with(contexto).load(item.getFoto()).error(R.mipmap.ic_launcher).into(holder.getImageViewFoto());
        }
        //imagem = findViewById (R.id.TelaItemAula_ImageView);
        //imagem.setImageResource(resID);
    }

    @Override
    public int getItemCount() {
        return (lista != null) ? lista.size() : 0;
    }

}