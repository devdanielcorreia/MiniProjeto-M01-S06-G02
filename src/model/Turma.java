package model;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class Turma implements Serializable {
    private static final long serialVersionUID = 4L;
    // Atributos
    private List<Aluno> listaAlunos;
    private int ano; // ano de início da turma
    private Curso curso;

    // Construtor
    public Turma(List<Aluno> listaAlunos, int ano, Curso curso) {
        this.listaAlunos = listaAlunos;
        this.ano = ano;
        this.curso = curso;
    }

    // Getters e Setters
    public List<Aluno> getListaAlunos() {
        return listaAlunos;
    }

    public void setListaAlunos(List<Aluno> listaAlunos) {
        this.listaAlunos = listaAlunos;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    // Métodos
    public void listarAlunos() {
        // Implementação existente
    }

    public void listarAlunosComIndice() {
        // Implementação existente
    }

    public void adicionarAluno(Aluno aluno) {
        listaAlunos.add(aluno);
    }

    public void removerAluno(Aluno aluno) {
        listaAlunos.remove(aluno);
    }

    public void removerAluno(Scanner entrada) {
        // Implementação existente
    }

    // Método toString personalizado
    @Override
    public String toString() {
        String infoCurso = curso != null ? curso.toString() : "Curso não definido";
        return "Turma do Ano: " + ano + ", Curso: " + infoCurso + ", Número de Alunos: " + listaAlunos.size();
    }
}

