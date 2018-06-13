package com.example.apoh.schoolboard.telas;


import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.apoh.schoolboard.BuildConfig;
import com.example.apoh.schoolboard.R;
import com.example.apoh.schoolboard.adapter.AulaAdapter;
import com.example.apoh.schoolboard.banco.AulaDAO;
import com.example.apoh.schoolboard.model.Aula;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TelaAula extends AppCompatActivity {

    AulaDAO vrbancoDados = null;

    TextView nameProfes = null;
    TextView nameDisci = null;
    ImageView campoFoto = null;
    File imgFile = null;
    String caminhoFoto = null;
    Uri imageUri = null;
    FloatingActionButton botaoFoto = null;
    Picasso picasso = null;

    RecyclerView recyclerViewAulas = null;
    ArrayList<Aula> aulas;
    Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_aula);

        //O erro esta aqui
        //Não tem como acessar esse ImageView porque ele não esta neste layout.
        //Como pega o layout para usar aquele imageview? Sendo que esse imageView é de um recycler

        //campoFoto = findViewById(R.id.TelaItemAula_ImageView);
        //String foto = this.getIntent().getStringExtra("caminhoFoto");


        //Encontrando os texView pra setar os nomes da outra tela
        nameDisci = findViewById(R.id.TelaAulaDisciplina);
        nameProfes = findViewById(R.id.TelaAulaProfessor);

        //Tá pegando as informações da outra tela
        String Materia = this.getIntent().getStringExtra("Materia");
        String Professor = this.getIntent().getStringExtra("Professor");

        //Exibe no text view da telaAula
        nameDisci.setText(Materia);
        nameProfes.setText(Professor);

        //Chamando a funcao de capturar
        botaoFoto = (FloatingActionButton) findViewById(R.id.TelaAula_floatingActionButton);
        botaoFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capturaImagem();
            }
        });

        listarAulas(recyclerViewAulas);
    }



    public void gravarAulas() {
        vrbancoDados = new AulaDAO(this);

        ContentValues dados = new ContentValues();
        dados.put("caminhoFoto", (String) caminhoFoto);
        //campoFoto.setImageBitmap(BitmapFactory.decodeFile(caminhoFoto));

        vrbancoDados.inserirAula(dados);

        Log.i("Info", dados.toString());

    }

    public void listarAulas(View v) {
        vrbancoDados = new AulaDAO(this);

        aulas = vrbancoDados.buscarAulas();

        recyclerViewAulas = findViewById(R.id.recyclerViewAulas);
        recyclerViewAulas.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAulas.setItemAnimator(new DefaultItemAnimator());
        recyclerViewAulas.setHasFixedSize(true);

        AulaAdapter adapt = new AulaAdapter(this, aulas);
        adapt.notifyItemInserted(aulas.size());

        recyclerViewAulas.setAdapter(adapt);

    }


    public void capturaImagem() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        contexto = this;

        builder.setTitle("Escolha a origem de sua foto");
        builder.setItems(new CharSequence[]{"Galeria", "Câmera"}, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:

                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");
                        Intent chooser = Intent.createChooser(intent, "Escolha uma foto");

                        caminhoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                        File foto0 = new File(caminhoFoto);
                        imageUri = FileProvider.getUriForFile(contexto, BuildConfig.APPLICATION_ID + ".provider", foto0);
                        chooser.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

                        startActivityForResult(chooser, 0);

                        break;

                    case 1:

                        Intent intentTirarFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        caminhoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                        File foto1 = new File(caminhoFoto);
                        imageUri = FileProvider.getUriForFile(contexto, BuildConfig.APPLICATION_ID + ".provider", foto1);
                        intentTirarFoto.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        startActivityForResult(intentTirarFoto, 1);

                        break;
                }
            }

        });

        builder.show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {

            switch (requestCode) {
                case 0:

                    Uri selectedImageUri = data.getData();

                    try {
                        campoFoto = findViewById(R.id.TelaItemAula_ImageView); //mudei aqui

                        Uri selectedImage = selectedImageUri;
                        getContentResolver().notifyChange(selectedImage, null);
                        ContentResolver cr = getContentResolver();

                        Bitmap bitmap;
                        bitmap = android.provider.MediaStore.Images.Media.getBitmap(cr, selectedImage);
                        campoFoto.setImageBitmap(bitmap);

                        gravarAulas();
                        //picasso.load(selectedImage).into(campoFoto);
                        Toast.makeText(this, selectedImage.toString(),Toast.LENGTH_LONG).show();


                    } catch (Exception e) {
                        Toast.makeText(this, "Falha ao carregar da galeria!", Toast.LENGTH_SHORT)
                                .show();
                        Log.e("Camera", e.toString());
                    }

                    break;

                case 1:
                    super.onActivityResult(requestCode, resultCode, data);
                    if (resultCode == Activity.RESULT_OK) {
                        Uri selectedImage = imageUri;
                        getContentResolver().notifyChange(selectedImage, null);
                        ContentResolver cr = getContentResolver();
                        Bitmap bitmap;
                        try {
                            bitmap = android.provider.MediaStore.Images.Media.getBitmap(cr, selectedImage);
                            //campoFoto.setImageBitmap(bitmap);

                            gravarAulas();

                            Toast.makeText(this, selectedImage.toString(),
                                    Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Toast.makeText(this, "Falha ao carregar da câmera!", Toast.LENGTH_SHORT)
                                    .show();
                            Log.e("Camera", e.toString());
                        }
                    }
                    break;
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.lista) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
