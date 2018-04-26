package com.example.apoh.schoolboard.model;

public class Aula {
    private String id;
    private String nome_aula;
    private String dataCriacao;

    //CONTRUTORES
    public Aula() {
    }

    public Aula(String nome) {
        this.nome_aula = nome;

    }

    public Aula(String nome, String dataCriacao) {

        this.nome_aula = nome;
        this.dataCriacao = dataCriacao;
    }


    public Aula(String id, String nome, String dataCriacao) {
        this.setId(id);
        this.nome_aula = nome;
        this.dataCriacao = dataCriacao;
    }

    //GETTERS E SETTERS
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome_aula() {
        return nome_aula;
    }

    public void setNome_aula(String nome_aula) {
        this.nome_aula = nome_aula;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
