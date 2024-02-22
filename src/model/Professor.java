package model;

import enumerations.CargoFuncionario;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Professor extends Funcionario implements Serializable {

    @Serial
    private static final long serialVersionUID = 3L;

    // Atributos
    private int idade;
    private int tempoTrabalho;

    // Construtor
    public Professor(String nome, double salario, CargoFuncionario cargo, int idade, int tempoTrabalho) {
        super(nome, salario, cargo);
        this.idade = idade;
        this.tempoTrabalho = tempoTrabalho;
    }

    // Getters e Setters
    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getTempoTrabalho() {
        return tempoTrabalho;
    }

    public void setTempoTrabalho(int tempoTrabalho) {
        this.tempoTrabalho = tempoTrabalho;
    }

    // Método toString personalizado
    public String toString() {
        // Utiliza o toString da superclasse Funcionario e adiciona as informações específicas do Professor
        return super.toString() + ", Idade=" + idade + ", Tempo de Trabalho=" + tempoTrabalho + " anos";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return idade == professor.idade && tempoTrabalho == professor.tempoTrabalho;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idade, tempoTrabalho);
    }
}

