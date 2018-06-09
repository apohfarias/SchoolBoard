package com.example.apoh.schoolboard.banco;
import com.example.apoh.schoolboard.model.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/*Responsável por controlar as manipulações ao banco*/
public class ControlaBanco {
    private SQLiteDatabase db;
    private CriaBanco banco;

    // tag Logcat
    private static final String LOG = "DatabaseHelper";

    public ControlaBanco(Context context) {
        banco = new CriaBanco(context);
        try {
            db = banco.getReadableDatabase();
            int version = db.getVersion();
            Log.i("----BANCO----", "abriu o banco, versao " + version);
        } catch (SQLException ex) {
            Log.i("----BANCO----", "não abriu, erro " + ex.toString());
        }
        db.close();
    }

// ------------------------ métodos para a tabela disciplina ----------------//

    /*
     * Criando uma disciplina
     */
    public long criarDisciplina (Disciplina disciplina, long[] aula_ids){
        long id_disc = -1;
        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(CriaBanco.getNomeDisciplina(), disciplina.getNome_disciplina());
        valores.put(CriaBanco.getPROFESSOR(), disciplina.getProfessor());
        valores.put(CriaBanco.getKeyCriadoEm(), disciplina.getDataCriacao());

        //Inserir linha
        try {
            id_disc = db.insert(CriaBanco.getTabelaDisciplina(), null, valores);
            Log.i("---BANCO---", "Inserido!, id = " + id_disc);
        } catch (SQLException ex) {
            Log.i("---BANCO---", "Não foi.., erro " + ex.toString());
        }

        // inserir id da aula
        for (long aula_id : aula_ids) {
            criar_aula_disciplina(aula_id, id_disc);
        }

        return  id_disc;
    }

    //PEGAR DISCIPLINA E INSERE DADOS
    public Disciplina getDisciplina (long id_disc){
        SQLiteDatabase db = banco.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + CriaBanco.getTabelaDisciplina() + " WHERE "
                + CriaBanco.getKeyId() + " = " + id_disc;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Disciplina disciplina = new Disciplina();
        //disciplina.setId(c.getInt(c.getColumnIndex(banco.getKeyId())));
        disciplina.setId(c.getInt(c.getColumnIndex(CriaBanco.getKeyId())));
        disciplina.setNome_disciplina(c.getString(c.getColumnIndex(CriaBanco.getNomeDisciplina())));
        disciplina.setProfessor(c.getString(c.getColumnIndex(CriaBanco.getPROFESSOR())));
        disciplina.setDataCriacao(c.getString(c.getColumnIndex(CriaBanco.getKeyCriadoEm())));

        return disciplina;
    }

/*
    // INSERIR DADOS
    public long inserirDados(String nome, String professor) {
        long i = -1;
        db = banco.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(CriaBanco.getKeyAulaId(), nome);
        valores.put(CriaBanco.getPROFESSOR(), professor);
        try {
            i = db.insertOrThrow(CriaBanco.getTabelaDisciplina(), null, valores);
            Log.i("---BANCO---", "Inserido!, id = " + i);
        } catch (SQLException ex) {
            Log.i("---BANCO---", "Não foi.., erro " + ex.toString());
        }

        db.close();
        return i;

    }
    */
    public List<Disciplina> listarDisciplinas() {
        List<Disciplina> disciplinas = new ArrayList<Disciplina>();
        String selectQuery = "SELECT  * FROM " + banco.getTabelaDisciplina();

        //Log.e(LOG, selectQuery);

        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // loop  de todas as linhas e adicionando à lista
        if (c.moveToFirst()) {
            do {
                Disciplina disciplina = new Disciplina();

                disciplina.setId(c.getInt(c.getColumnIndex(CriaBanco.getKeyId())));
                disciplina.setNome_disciplina(c.getString(c.getColumnIndex(CriaBanco.getNomeDisciplina())));
                disciplina.setProfessor(c.getString(c.getColumnIndex(CriaBanco.getPROFESSOR())));
                disciplina.setDataCriacao(c.getString(c.getColumnIndex(CriaBanco.getKeyCriadoEm())));

                // adicionando a lista de disciplinas
                disciplinas.add(disciplina);
            } while (c.moveToNext());
        }

        return disciplinas;

    }

    /*
    // LISTAR DADOS
    public List<Disciplina> carregaDados() {
        Cursor cursor;

        db = banco.getWritableDatabase();
        cursor = db.query(CriaBanco.getTABELA(), null, null, null, null, null, null);

        List<Disciplina> itens = new ArrayList<>();

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                itens.add(new Disciplina(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
                Log.i("---Banco---", "itens id = " + cursor.getLong(0) + ", por string = " + cursor.getString(0));
            } while (cursor.moveToNext());
        }

        return itens;
    }*/

    /*
     * retorna a quantidade de disciplinas
     */
    public int getQuantDisciplina() {
        String countQuery = "SELECT  * FROM " + CriaBanco.getTabelaDisciplina();
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }
    /*

	/*
	 * Atualizando uma disciplina
	 */
    public int atualizarDisciplina(Disciplina disciplina) {
        SQLiteDatabase db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put(CriaBanco.getNomeDisciplina(), disciplina.getNome_disciplina());
        valores.put(CriaBanco.getPROFESSOR(), disciplina.getProfessor());

        // atualiza linha
        return db.update(CriaBanco.getTabelaDisciplina(), valores, CriaBanco.getKeyId() + " = ?",
                new String[] { String.valueOf(disciplina.getId()) });
    }

    /*
     * Excluindo uma disciplina
     */
    public void excluirDisciplina(long id_disc) {
        SQLiteDatabase db = banco.getWritableDatabase();

        db.delete(CriaBanco.getTabelaDisciplina(), CriaBanco.getKeyId() + " = ?",
                new String[] { String.valueOf(id_disc) });
    }


    /*    // EXCLUIR DADOS
    public void excluirDados(String id) {
        db = banco.getWritableDatabase();
        try {
            db.delete(CriaBanco.getTABELA(), CriaBanco.getID() + " = ? ", new String[]{id});
            Log.i("---Banco---", "Deletato ");
        } catch (SQLException ex) {
            Log.i("---Banco---", "Erro ao excluir = " + ex.toString());
        }

        db.close();
    }*/

     /*
    // EDITAR DADOS
    public void editarDados(Disciplina disciplina) {
        db = banco.getWritableDatabase();
        try {
            ContentValues valores = new ContentValues();
            String condicao;

            valores.put(CriaBanco.getNOME(), disciplina.getNome());
            valores.put(CriaBanco.getPROFESSOR(), disciplina.getProfessor());
            condicao = CriaBanco.getID() + " = " + disciplina.getId();
            db.update(CriaBanco.getTABELA(), valores, condicao, null);
        } catch (SQLException ex) {
            Log.i("---Banco---", "Erro ao Editar  = " + ex.toString());
        }

        db.close();
    }
*/
// ------------------------ métodos para a tabela aula ----------------//

    /*
     * Criando aula
     */


    public long criaAula (Aula aula){
        SQLiteDatabase db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(CriaBanco.getNomeAula(), aula.getNomeAula());
        valores.put(CriaBanco.getKeyCriadoEm(), aula.getDataCriacao());

        // insert row
        long aula_id = db.insert(CriaBanco.getTabelaAula(), null, valores);

        return aula_id;

    }

    /*
    * Busca todas as aulas
    * */

    public List<Aula> getAulas() {
        List<Aula> aulas = new ArrayList<Aula>();
        String selectQuery = "SELECT  * FROM " + CriaBanco.getTabelaAula();

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // loop  percorrendo todas as linhas e adicionando à lista
        if (c.moveToFirst()) {
            do {
                Aula aula = new Aula();

                aula.setId(c.getString(c.getColumnIndex(CriaBanco.getKeyId())));
                aula.setNomeAula(c.getString(c.getColumnIndex(CriaBanco.getNomeAula())));
                aula.setDataCriacao(c.getString(c.getColumnIndex(CriaBanco.getKeyCriadoEm())));

                // adicionando a lista de disciplinas
                aulas.add(aula);
            } while (c.moveToNext());
        }

        return aulas;

    }

    /*
     * Atualizado uma aula
     */
    public int atualizarAula(Aula aula) {
        SQLiteDatabase db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(CriaBanco.getNomeAula(), aula.getNomeAula());

        //atualiza linha
        return db.update(CriaBanco.getTabelaAula(), valores, CriaBanco.getKeyId() + " = ?",
                new String[] { String.valueOf(aula.getId()) });
    }

    /*
     * Excluindo uma aula
     */
    public void excluirAula(long id_aula) {
        SQLiteDatabase db = banco.getWritableDatabase();

            // apaga a aula
        db.delete(CriaBanco.getTabelaAula(), CriaBanco.getKeyId() + " = ?",
                new String[] { String.valueOf(id_aula) });

    }



// ------------------------ métodos para a tabela aula_disciplina ----------------//

    /*
     * Criando criar_aula_disciplina
     */
    public long criar_aula_disciplina(long disciplina_id, long aula_id) {
        long id = -1;
        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(CriaBanco.getKeyDisciplinaId(), disciplina_id);
        valores.put(CriaBanco.getKeyAulaId(), aula_id);
        valores.put(CriaBanco.getKeyCriadoEm(), getDateTime());


        id = db.insert(CriaBanco.getCriarTabelaAulaDisciplina(), null, valores);

        return id;
    }

    /*
     * Atualiza uma  aula da disciplina
     */
    public int atualizaAulaDisciplina(long id, long aula_id) {
        SQLiteDatabase db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(CriaBanco.getKeyAulaId(),aula_id );

        // atualiza linha
        return db.update(CriaBanco.getTabelaDisciplina(), valores, CriaBanco.getKeyId() + " = ?",
                new String[] { String.valueOf(id) });
    }

    /*
     * Apaga Aula da disciplina
     */
    public void excluiAulaDisciplina(long id) {
        SQLiteDatabase db = banco.getWritableDatabase();
        db.delete(CriaBanco.getTabelaDisciplina(),  CriaBanco.getKeyId() + " = ?",
                new String[] { String.valueOf(id) });
    }

    // fecha/encerra/close banco de dados
    public void fechaDB() {
        SQLiteDatabase db = banco.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }



    /**
     * pega e retorna a data
     * * */
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd-MM-yyyy", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

}
