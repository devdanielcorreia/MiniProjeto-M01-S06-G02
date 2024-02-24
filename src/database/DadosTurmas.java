package database;

import model.Aluno;
import model.Curso;
import model.Turma;
import utils.ManipularDadosUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DadosTurmas {

    private final String arquivoDados = "dados_turmas.bin";
    List<Turma> listaTurmas;

    public DadosTurmas() {
        listaTurmas = new ArrayList<>();
        carregarDados();
    }

    public List<Turma> getlistaTurmas() {
        return listaTurmas;
    }

    // MÉTODOS DA CLASSE
    public void adicionarTurma(Turma novaTurma) {
        listaTurmas.add(novaTurma);
        salvarDados();
    }

    public void removerTurma(int indexTurma) {
        try {
            listaTurmas.remove(indexTurma);
            salvarDados();
        } catch (IndexOutOfBoundsException e) {
            System.err.println("O índice ndice fornecido está fora do intervalo. Não foi possível remover a turma.");
        }
    }

    public void buscarTurma(int idTurma) {
        try {
            if (idTurma >= 0 && idTurma < listaTurmas.size()) {
                System.out.println("Resultado da busca para o ID " + idTurma + ":");
                System.out.println(listaTurmas.get(idTurma));
            } else {
                throw new IllegalArgumentException("ID fornecido é inválido.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Não foi possível localizar um turma com a ID " + idTurma + " em nosso sistema.");
        }
    }

    public List<Curso> getCursosMatriculados(Aluno aluno) {
        List<Curso> cursosMatriculados = new ArrayList<>();

        for (Turma turma : listaTurmas) {
            if (turma.getListaAlunos().contains(aluno)) {
                cursosMatriculados.add(turma.getCurso());
            }
        }

        return cursosMatriculados;
    }

    private void carregarDados() {
        try {
            ManipularDadosUtils.carregarDados(arquivoDados, listaTurmas);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de dados de turmas não encontrado. Será criado um novo arquivo.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar os dados das turmas:" + e.getMessage());
        }
    }

    private void salvarDados() {
        try {
            ManipularDadosUtils.salvarDados(arquivoDados, listaTurmas);
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados das turmas: " + e.getMessage());
        }
    }

    public void atualizarDados(Turma turmaAtualizada) {
        try {
            ManipularDadosUtils.atualizarDados(arquivoDados, listaTurmas, turmaAtualizada);
        } catch (IOException e) {
            // Tratamento de exceção de IO
            System.err.println("Erro ao atualizar os dados do professor: " + turmaAtualizada + " - Causa: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        // Inicia a construção de uma string com um StringBuilder
        StringBuilder result = new StringBuilder("DadosTurmas:\n");

        // Itera sobre cada turma na lista de turmas
        for (Turma turma : listaTurmas) {
            // Adiciona a representação em string da turma atual ao resultado
            // O método toString da turma é chamado automaticamente
            result.append(turma.toString()).append("\n");
        }
        // Converte o StringBuilder para String e retorna o resultado
        // Isso inclui a representação de todos as turmas na lista, cada um em uma nova linha
        return result.toString();
    }
}
