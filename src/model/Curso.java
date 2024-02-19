package model;

public class Curso {
    // Atributos
    private String nome;
    private Professor professor;

    // Construtor
    public Curso(String nome, Professor professor) {
        this.nome = nome;
        this.professor = professor;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    // Método toString personalizado
    @Override
    public String toString() {
        return "Curso: " + nome + ", Professor: " + (professor != null ? professor.getNome() : "Nenhum");
    }
}

