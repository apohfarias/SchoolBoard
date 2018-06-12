package com.example.apoh.schoolboard.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.apoh.schoolboard.model.Disciplina;

import java.util.ArrayList;

public class DisciplinaDAO extends SQLiteOpenHelper {
    String[] scriptCriaBanco = {
            "create table disciplina(_id integer primary key autoincrement not null," +
                    "nomeDisciplina text not null," +
                    " nomeProfessor text not null," +
                    " dataCriacao date);",

            "create table aula(_id integer primary key autoincrement," +
                    " nomeAula text not null," +
                    " caminhoFoto text not null," +
                    " dataCriacao date);"

    };

    public final String scriptApagaDB = "DROP TABLE IF EXISTS disciplina";


    Context vrContexto = null;

    public DisciplinaDAO(Context context, String nome, int versao) {
        super(context, nome, null, 1);
        vrContexto = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        for(int iIndex=0; iIndex < scriptCriaBanco.length; iIndex++){
            db.execSQL(scriptCriaBanco[iIndex]);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(scriptApagaDB);

    }

    //METODO DE INSERÇÃO DA DISCIPLINA
    public void inserirDisciplina(ContentValues  dados) {
        SQLiteDatabase db = getWritableDatabase();
        db.insert("disciplina", null, dados);
        Toast.makeText( vrContexto, "Inserção realizada com sucesso!", Toast.LENGTH_SHORT).show();

    }


    //MÉTODO QUE PEGA  OS DADOS DA DISCIPLINA
    private ContentValues pegarDisciplinas(Disciplina d) {
        ContentValues dadosDisciplina = new ContentValues();
        dadosDisciplina.put("Nome", d.getNome_disciplina());
        dadosDisciplina.put("Professor", d.getProfessor());
        dadosDisciplina.put("dataCriacao", d.getDataCriacao());
        return dadosDisciplina;
    }

    //METODO QUE LISTA AS DISCIPLINAS
    public ArrayList<Disciplina> buscarDisciplinas() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query("disciplina", new String[]{"_id","nomeDisciplina", "nomeProfessor"},null,null,null,null,null);

        ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();

        // loop  de todas as linhas e adicionando à lista
        if (c.moveToFirst()) {
            do {
                Disciplina d = new Disciplina();

                d.setId(c.getInt(c.getColumnIndex("_id")));
                d.setNome_disciplina(c.getString(c.getColumnIndex("nomeDisciplina")));
                d.setProfessor(c.getString(c.getColumnIndex("nomeProfessor")));
                //d.setDataCriacao(c.getString(c.getColumnIndex("dataCriacao")));

                // adicionando a lista de disciplinas
                disciplinas.add(d);
            } while (c.moveToNext());

            //Fechando o cursor do sql
            c.close();
        }

        return disciplinas;

    }

    //METODO QUE DELETA DISCIPLINA
    public void deletarDisciplina(Disciplina d) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {Integer.toString(d.getId())};
        //Executando sql
        db.delete("disciplina", "_id=?", params);

    }

    //METODO QUE ALTERA/EDITA DISCIPLINA
    public void alterarDisciplina(Disciplina d) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dadosDisciplina = pegarDisciplinas(d);
        String[] params = {d.getId().toString()};

        //Executando sql
        db.update("disciplina", dadosDisciplina, "id=?", params);
    }
}