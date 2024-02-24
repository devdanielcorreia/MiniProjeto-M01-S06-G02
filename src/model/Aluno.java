package model;

import enumerations.StatusMatricula;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Aluno implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // Atributos
    private String nome;
    private int idade;
    private StatusMatricula statusMatricula;

    // Construtor
    public Aluno(String nome, int idade, StatusMatricula statusMatricula) {
        this.nome = nome;
        this.idade = idade;
        this.statusMatricula = statusMatricula;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public StatusMatricula getStatusMatricula() {
        return statusMatricula;
    }

    public void setStatusMatricula(StatusMatricula statusMatricula) {
        this.statusMatricula = statusMatricula;
    }

    // Metodos
    @Override
    public String toString() {
        // Inicia um StringBuilder para construir a string de forma eficiente
        StringBuilder result = new StringBuilder("Aluno: " + nome + "\nIdade: " + idade + "\nStatus de Matrícula: " + statusMatricula);
        // Converte o StringBuilder para String e o retorna
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return idade == aluno.idade && Objects.equals(nome, aluno.nome) && statusMatricula == aluno.statusMatricula;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, idade, statusMatricula);
    }
}
