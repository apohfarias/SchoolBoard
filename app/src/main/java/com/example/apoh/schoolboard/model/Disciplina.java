package com.example.apoh.schoolboard.model;

public class Disciplina {
    private Integer id;
    private String nome_disciplina;
    private String professor;
    private String dataCriacao;

    //CONTRUTORES
    public Disciplina() {
    }

    public Disciplina( String nome, String professor) {

        this.nome_disciplina = nome;
        this.professor = professor;
    }

    public Disciplina(Integer id, String nome, String professor) {
        this.id=id;
        this.nome_disciplina = nome;
        this.professor = professor;
    }

    //GETTERS E SETTERS


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome_disciplina() {
        return nome_disciplina;
    }

    public void setNome_disciplina(String nome_disciplina) {
        this.nome_disciplina = nome_disciplina;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
