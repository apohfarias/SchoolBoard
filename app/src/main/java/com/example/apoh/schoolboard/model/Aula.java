package com.example.apoh.schoolboard.model;

public class Aula {
    private String id;
    private String nomeAula;
    private byte[] imagem;
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


    public Aula(String id, String nome, String dataCriacao) {
        this.setId(id);
        this.nomeAula = nome;
        this.dataCriacao = dataCriacao;
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

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
