package com.example.apoh.schoolboard.banco;

public class Disciplina {
    private String id;
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

    public Disciplina(String id, String nome, String professor) {
        this.setId(id);
        this.nome_disciplina = nome;
        this.professor = professor;
    }

    //GETTERS E SETTERS
    public String getId() {
        return id;
    }

    public void setId(String id) {
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
