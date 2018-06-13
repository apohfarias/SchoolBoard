package com.example.apoh.schoolboard.model;

import android.graphics.Bitmap;

public class Aula {
    private String id;
    private String nomeAula;
    //private byte[] caminhoFoto;
    String foto;
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


    public Aula(String nome, String dataCriacao, String foto) {
        this.setId(id);
        this.nomeAula = nome;
        this.dataCriacao = dataCriacao;
        this.foto = foto;
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

    public String getCaminhoFoto() {
        return foto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.foto = caminhoFoto;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
