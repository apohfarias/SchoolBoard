package com.example.apoh.schoolboard.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.apoh.schoolboard.model.Aula;
import com.example.apoh.schoolboard.model.Disciplina;

import java.util.ArrayList;
import java.util.List;

public class AulaDAO extends SQLiteOpenHelper{
/*    String[] scriptCriaBanco = {"create table aula(_id integer primary key autoincrement, nomeAula text not null, caminhoFoto text not null, dataCriacao date);" };
    public final String scriptApagaDB = "DROP TABLE IF EXISTS aula";
    Context vrContexto = null;*/

    public AulaDAO(Context context, String nome, int versao) {
        super(context, nome, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS aula";
        db.execSQL(sql);
        onCreate(db);

    }

    //METODO DE INSERÇÃO DA AULA
    public void inserirAula(ContentValues  dados) {
        SQLiteDatabase db = getWritableDatabase();
        db.insert("aula", null, dados);

        //Toast.makeText( vrContexto, "Inserção realizada com sucesso!", Toast.LENGTH_SHORT).show();

    }


    //MÉTODO QUE PEGA  OS DADOS DA AULA
    private ContentValues pegarAulas(Aula a) {
        ContentValues dadosAula  = new ContentValues();

        dadosAula.put("nome", a.getNomeAula());
       //dadosAula.put("imagem", a.getImagem());
        dadosAula.put("caminhoFoto", a.getCaminhoFoto());

        return dadosAula;
    }


    //METODO QUE LISTA AS AULAS
    public ArrayList<Aula> buscarAulas() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query("aula", new String[]{"nomeAula"},null,null,null,null,null);

        ArrayList<Aula> aulas = new ArrayList<Aula>();

        // loop  de todas as linhas e adicionando à lista
        if (c.moveToFirst()) {
            do {
                Aula a = new Aula();

                //a.setId(c.getString(c.getColumnIndex("id")));
                a.setNomeAula(c.getString(c.getColumnIndex("nomeAula")));
                a.setCaminhoFoto(c.getString(c.getColumnIndex("caminhoFoto")));
               // a.setDataCriacao(c.getString(c.getColumnIndex("dataCriacao")));

                // adicionando a lista de disciplinas
                aulas.add(a);
            } while (c.moveToNext());

            //Fechando o cursor do sql
            c.close();
        }

        return aulas;
    }

    //METODO QUE DELETA AULA
    public void deletarAula(Aula a) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {a.getId().toString()};

        //Executando sql
        db.delete("aula", "_id = ?", params);
    }

    //METODO QUE ALTERA/EDITA AULA
    public void alterarAula(Aula a) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dadosAula = pegarAulas(a);
        String[] params = {a.getId().toString()};

        //Executando sql
        db.update("aula", dadosAula, "_id = ?", params);
    }

}
