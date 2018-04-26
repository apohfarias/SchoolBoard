package com.example.apoh.schoolboard.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.apoh.schoolboard.model.Aula;

import java.util.ArrayList;
import java.util.List;

public class AulaDAO extends SQLiteOpenHelper{
    public AulaDAO(Context context) {
        super(context, "schoolboard", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Aula (id INTEGER PRIMARY KEY, " +
                "nome TEXT NOT NULL, " +
                "dataCriacao DATE);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Aula";
        db.execSQL(sql); //Executando sql
        //Executando criando bd
        onCreate(db);
    }

    //METODO DE INSERÇÃO DA AULA
    public void inserirDisciplina(Aula a) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dadosAula = pegarAulas(a);

        //Executando sql
        db.insert("Aula", null, dadosAula);

    }

    //MÉTODO QUE PEGA  OS DADOS DA AULA
    private ContentValues pegarAulas(Aula a) {
        ContentValues dadosAula  = new ContentValues();
        dadosAula.put("Nome", a.getNome_aula());
        dadosAula.put("dataCriacao", a.getDataCriacao());
        return dadosAula;
    }

    //METODO QUE LISTA AS AULAS
    public List<Aula> buscarAulas() {
        String sql = "SELECT * FROM Aula;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Aula> aulas = new ArrayList<Aula>();

        // loop  de todas as linhas e adicionando à lista
        if (c.moveToFirst()) {
            do {
                Aula a = new Aula();

                a.setId(c.getString(c.getColumnIndex("id")));
                a.setNome_aula(c.getString(c.getColumnIndex("nome")));
                a.setDataCriacao(c.getString(c.getColumnIndex("dataCriacao")));

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
        db.delete("Aula", "id = ?", params);
    }

    //METODO QUE ALTERA/EDITA AULA
    public void alterarAula(Aula a) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dadosAula = pegarAulas(a);
        String[] params = {a.getId().toString()};

        //Executando sql
        db.update("Aula", dadosAula, "id = ?", params);
    }

}
