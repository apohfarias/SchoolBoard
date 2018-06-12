package com.example.apoh.schoolboard.telas;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apoh.schoolboard.BuildConfig;
import com.example.apoh.schoolboard.R;
import com.example.apoh.schoolboard.adapter.AdapterListener;
import com.example.apoh.schoolboard.adapter.AulaAdapter;
import com.example.apoh.schoolboard.adapter.DisciplinaAdapter;
import com.example.apoh.schoolboard.banco.AulaDAO;
import com.example.apoh.schoolboard.banco.DisciplinaDAO;
import com.example.apoh.schoolboard.model.Aula;
import com.example.apoh.schoolboard.model.Disciplina;

import java.io.File;
import java.util.ArrayList;

public class TelaAula extends AppCompatActivity {
    private static final String TAG = "TelaAula";


    AulaDAO vrbancoDados = null;
    ImageView campoImagem = null;


    EditText campoConteudo, campoData;
    public TextView nameProfes, nameDisci;
    private ImageView campoFoto;

    private String caminhoFoto;
    Uri imageUri;

    //FloatingActionButton botaoAddAula = null;
    FloatingActionButton botaoFoto = null;

    RecyclerView lista = null;
    ArrayList<Aula> aulas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_aula);


        //O erro esta aqui
        //Não tem como acessar esse ImageView porque ele não esta neste layout.
        campoFoto = findViewById(R.id.TelaItemAula_ImageView);

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
        });*/
        listarAulas(lista);

        botaoFoto = (FloatingActionButton) findViewById(R.id.TelaAula_floatingActionButton);
        botaoFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // capturaImagem(v);
                takePhoto();

            }
        });

    }

    public void gravarAulas(View v) {
        vrbancoDados = new AulaDAO(this, "BDSchoolBoard", 1);

        ContentValues dados = new ContentValues();
        dados.put("nomeAula", campoConteudo.getText().toString());
        dados.put("dataCriacao", campoData.getText().toString());
        dados.put("caminhoFoto", (String) campoFoto.getTag());
        //campoFoto.setImageBitmap(BitmapFactory.decodeFile(caminhoFoto));

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
        adapt.notifyItemInserted(aulas.size());
        lista.setAdapter(adapt);
    }

/*






 */
    //Captura foto com a camera
    public void capturaImagem(View botao) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        caminhoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";

       File arquivoFoto = new File(caminhoFoto);
        imageUri = Uri.fromFile(arquivoFoto);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivoFoto));

        startActivityForResult(intent, 100);

        /*Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                caminhoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                File arquivoFoto = new File(caminhoFoto);
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivoFoto));
                startActivityForResult(intentCamera, CODIGO_CAMERA);*/
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


    public void takePhoto() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final Context meuContexto = this;


        builder.setTitle("Escolha a origem de sua foto");
        builder.setItems(new CharSequence[]{"Galeria", "Câmera"}, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:

                        Intent intent = new Intent(
                                Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");

                        Intent chooser = Intent.createChooser(intent, "Choose a Picture");
                        startActivityForResult(chooser, 0);

                        break;

                    case 1:

                        Intent intentTirarFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        caminhoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                        File foto = new File(caminhoFoto);
                        imageUri = FileProvider.getUriForFile(meuContexto, BuildConfig.APPLICATION_ID + ".provider", foto);
                        intentTirarFoto.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        startActivityForResult(intentTirarFoto,1);

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
                        Uri selectedImage = selectedImageUri;
                        //getContentResolver().notifyChange(selectedImage, null);
                        ContentResolver cr = getContentResolver();

                        Bitmap bitmap;
                        bitmap = android.provider.MediaStore.Images.Media
                                .getBitmap(cr, selectedImage);
                        campoFoto.setImageBitmap(bitmap);

                    } catch (Exception e) {
                        Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT)
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
                            bitmap = android.provider.MediaStore.Images.Media
                                    .getBitmap(cr, selectedImage);

                            campoFoto.setImageBitmap(bitmap);
                            Toast.makeText(this, selectedImage.toString(),
                                    Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT)
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
        //noinspection SimplifiableIfStatement
        if (id == R.id.lista) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
