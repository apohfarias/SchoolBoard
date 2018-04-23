package com.example.apoh.schoolboard.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*SQLiteOpenHelper: Classe responsável pela criação do banco
e também responsável pelo versionamento do mesmo.*/
public class CriaBanco extends SQLiteOpenHelper {
    //VERSÃO DO BANCO DE DADOS
    private static final int VERSAO = 1;

    //NOME DO BANCO DE DADOS
    private static final String NOME_BANCO = "schoolboard.db";

    //NOMES DAS TABELAS
    private static final String TABELA_DISCIPLINA = "disciplina";
    private static final String TABELA_AULA = "aula";
    private static final String TABELA_AULA_DISCIPLINA = "aula_disciplina";

    // NOME COLUNA EM COMUN
    private static final String KEY_ID = "id";
    private static final String KEY_CRIADO_EM = "criado_em";

    //NOMES DAS COLUNAS - TABELA disciplina
    private static final String NOME_DISCIPLINA = "nome_disciplina";
    private static final String PROFESSOR = "professor";

    //NOMES DAS COLUNAS - TABELA aula
    private static final String NOME_AULA = "nome_aula";

    // TABELA DISCIPLINA_AULA - nome das colunas
    private static final String KEY_DISCIPLINA_ID = "disciplina_id";
    private static final String KEY_AULA_ID = "aula_id";


    // SQL PARA CRIAR TABELAS
    // Sql criando TABELA_DISCIPLINA
    private static final String CRIAR_TABELA_DISCIPLINA = "CREATE TABLE "
            + TABELA_DISCIPLINA + "(" + KEY_ID + " INTEGER PRIMARY KEY," + NOME_DISCIPLINA
            + " TEXT," + PROFESSOR + " TEXT," + KEY_CRIADO_EM
            + " DATETIME" + ")";

    // Sql criando TABELA_AULA
    private static final String CRIAR_TABELA_AULA = "CREATE TABLE " + TABELA_AULA
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + NOME_AULA + " TEXT,"
            + KEY_CRIADO_EM + " DATETIME" + ")";

    // Sql criando TABELA_AULA_DISCIPLINA
    private static final String CRIAR_TABELA_AULA_DISCIPLINA = "CREATE TABLE "
            + TABELA_AULA_DISCIPLINA + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_DISCIPLINA_ID + " INTEGER," + KEY_AULA_ID + " INTEGER,"
            + KEY_CRIADO_EM + " DATETIME" + ")";

    //METODOS DE ACESSO
    public static String getNomeBanco() {
        return NOME_BANCO;
    }


    public static int getVERSAO() {
        return VERSAO;
    }

    public static String getTabelaDisciplina() {
        return TABELA_DISCIPLINA;
    }

    public static String getTabelaAula() {
        return TABELA_AULA;
    }

    public static String getTabelaAulaDisciplina() {
        return TABELA_AULA_DISCIPLINA;
    }

    public static String getKeyId() {
        return KEY_ID;
    }

    public static String getKeyCriadoEm() {
        return KEY_CRIADO_EM;
    }

    public static String getNomeDisciplina() {
        return NOME_DISCIPLINA;
    }

    public static String getPROFESSOR() {
        return PROFESSOR;
    }

    public static String getNomeAula() {
        return NOME_AULA;
    }

    public static String getKeyDisciplinaId() {
        return KEY_DISCIPLINA_ID;
    }

    public static String getKeyAulaId() {
        return KEY_AULA_ID;
    }

    public static String getCriarTabelaDisciplina() {
        return CRIAR_TABELA_DISCIPLINA;
    }

    public static String getCriarTabelaAula() {
        return CRIAR_TABELA_AULA;
    }

    public static String getCriarTabelaAulaDisciplina() {
        return CRIAR_TABELA_AULA_DISCIPLINA;
    }

    //METODO CONSTRUTOR DA CLASSE
    public CriaBanco(Context context) {
        super(context, getNomeBanco(), null, getVERSAO());
    }

    /*
     * SQLiteDatabase: Classe que contém os métodos de manipulação dos dados no banco;*/
    /*Método onCreate(): é chamado quando a aplicação cria o banco de dados pela primeira vez.*/
    @Override
    public void onCreate(SQLiteDatabase db) {
      /*  String sql = "CREATE TABLE IF NOT EXISTS " + getTABELA() + "("
                + getID() + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                + getNOME() + " VARCHAR (40),"
                + getPROFESSOR() + " VARCHAR(40),"
                + ");";
        db.execSQL(sql);*/

        // Criando tabelas
        db.execSQL(CRIAR_TABELA_DISCIPLINA);
        db.execSQL(CRIAR_TABELA_AULA);
        db.execSQL(CRIAR_TABELA_AULA_DISCIPLINA);



    }

    /*Método onUpgrade(): responsável por atualizar o banco de dados com
    alguma informação estrutural que tenha sido alterada.*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*db.execSQL("DROP TABLE IF EXISTS" + getTABELA());
        onCreate(db);*/

        //Atualiza as tabelas mais antigas
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_DISCIPLINA);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_AULA);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_AULA_DISCIPLINA);

        // Cria novas tabelas
        onCreate(db);

    }
}
