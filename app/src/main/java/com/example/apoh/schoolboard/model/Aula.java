package com.example.apoh.schoolboard.model;

import android.graphics.Bitmap;

public class Aula {
    private String id;
    private String nomeAula;
    private byte[] caminhoFoto;
    //String caminhoFoto;
    private String dataCriacao;

    //CONTRUTORES
    public Aula() {
    }

    public Aula(String nome) {
        this.nomeAula = nome;

    }

    public Aula(String nome, String dataCriacao) {

        this.nomeAula = nome;
        this.dataCriacao = dataCriacao;
    }

/*
    public Aula(String nome, String dataCriacao) {
        this.setId(id);
        this.nomeAula = nome;
        this.dataCriacao = dataCriacao;
    }*/


    public Aula(String nome, String dataCriacao, byte[] caminhoFoto) {
        this.setId(id);
        this.nomeAula = nome;
        this.dataCriacao = dataCriacao;
        this.caminhoFoto = caminhoFoto;
    }

    //GETTERS E SETTERS

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeAula() {
        return nomeAula;
    }

    public void setNomeAula(String nomeAula) {
        this.nomeAula = nomeAula;
    }

    public byte[] getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(byte[] caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
