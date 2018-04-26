package com.example.apoh.schoolboard.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.apoh.schoolboard.model.Disciplina;

import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO extends SQLiteOpenHelper {
    public DisciplinaDAO(Context context) {
        super(context, "schoolboard", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Disciplina (id INTEGER PRIMARY KEY, " +
                "nome TEXT NOT NULL, " +
                "nomeProfessor TEXT, " +
                "dataCriacao DATE);";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Disciplina";
        db.execSQL(sql); //Executando sql
        //Executando criando bd
        onCreate(db);
    }

    //METODO DE INSERÇÃO DA DISCIPLINA
    public void inserirDisciplina(Disciplina d) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dadosDisciplina = pegarDisciplinas(d);

        //Executando sql
        db.insert("Disciplina", null, dadosDisciplina);

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
    public List<Disciplina> buscarDisciplinas() {
        String sql = "SELECT * FROM Disciplina;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Disciplina> disciplinas = new ArrayList<Disciplina>();

        // loop  de todas as linhas e adicionando à lista
        if (c.moveToFirst()) {
            do {
                Disciplina d = new Disciplina();

                d.setId(c.getString(c.getColumnIndex("id")));
                d.setNome_disciplina(c.getString(c.getColumnIndex("nome")));
                d.setProfessor(c.getString(c.getColumnIndex("nomeProfessor")));
                d.setDataCriacao(c.getString(c.getColumnIndex("dataCriacao")));

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
        String[] params = {d.getId().toString()};

        //Executando sql
        db.delete("Disciplina", "id = ?", params);
    }

    //METODO QUE ALTERA/EDITA DISCIPLINA
    public void alterarDisciplina(Disciplina d) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dadosDisciplina = pegarDisciplinas(d);
        String[] params = {d.getId().toString()};

        //Executando sql
        db.update("Disciplina", dadosDisciplina, "id = ?", params);
    }
}
