package database;

import enumerations.StatusMatricula;
import model.Aluno;
import utils.ManipularDadosUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static utils.ValidacaoUtils.*;

public class DadosAlunos {
    Scanner scn = new Scanner(System.in);

    private final String arquivoDados = "dados_alunos.bin";
    List<Aluno> listaAlunos;

    public DadosAlunos() {
        listaAlunos = new ArrayList<>();
        carregarDados();
    }

    public List<Aluno> getListaAlunos() {
        return listaAlunos;
    }

    // MÉTODOS DA CLASSE
    public Aluno adicionarAluno() {
        Aluno novoAluno = null;
        String nomeNovoAluno = receberNome();
        int idadeNovoAluno = receberIdade();
        StatusMatricula statusMatriculaNovoAluno = receberStatusMatricula();

        // INSTANCIANDO OBJETO e ADICIONANDO À LISTA
        novoAluno = new Aluno(nomeNovoAluno, idadeNovoAluno, statusMatriculaNovoAluno);
        listaAlunos.add(novoAluno);

        // FEEDBACK AO USUÁRIO
        System.out.println("*" + nomeNovoAluno.toUpperCase() + " foi adicionado ao sistema* \n");
        salvarDados();
        return novoAluno;
    }

    private String receberNome() {
        System.out.print("Nome do novo aluno: ");
        return validaInputStringNaoVazia(scn);
    }

    private int receberIdade() {
        System.out.print("Idade do novo aluno: ");
        return validaInputInteger(scn);
    }

    private StatusMatricula receberStatusMatricula() {
        // Receber o Status da Matricula do aluno por String
        System.out.println("Status da matrícula do aluno (ATIVA, TRANCADA, FORMADO): ");
        // Loop para validar a entrada do usuário com os values de StatusMatricula
        String statusMatriculaString = validaStatusMatricula(scn);
        // Converter a string em um Enum CargoFuncionario utilizando o valueOf()
        return StatusMatricula.valueOf(statusMatriculaString.toUpperCase());
    }

    public void removerAluno(int indexAluno) {
        try {
            listaAlunos.remove(indexAluno - 1);
            salvarDados();
        } catch (IndexOutOfBoundsException e) {
            System.err.println("O índice ndice fornecido está fora do intervalo. Não foi possível remover o aluno.");
        }
    }

    public Aluno buscarAluno(int idAluno) {
        try {
            if (idAluno >= 0 && idAluno < listaAlunos.size()) {
                System.out.println("Resultado da busca para o ID " + (idAluno + 1) + ":");
                System.out.println(listaAlunos.get(idAluno));
            } else {
                throw new IllegalArgumentException("ID fornecido é inválido.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Não foi possível localizar um aluno com a ID " + idAluno + " em nosso sistema.");
        }
        return listaAlunos.get(idAluno);
    }

    private void carregarDados() {
        try {
            ManipularDadosUtils.carregarDados(arquivoDados, listaAlunos);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de dados de alunos não encontrado. Será criado um novo arquivo.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar os dados dos alunos:" + e.getMessage());
        }
    }

    private void salvarDados() {
        try {
            ManipularDadosUtils.salvarDados(arquivoDados, listaAlunos);
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados dos alunos: " + e.getMessage());
        }
    }

    public void atualizarDados(Aluno alunoAtualizado) {
        try {
            ManipularDadosUtils.atualizarDados(arquivoDados, listaAlunos, alunoAtualizado);
        } catch (IOException e) {
            System.err.println("Erro ao atualizar os dados do aluno: " + alunoAtualizado + " - Causa: " + e.getMessage());
        }
    }

    // Método toString para representar os dados dos alunos como uma String
    @Override
    public String toString() {
        // Inicia a construção de uma string com um StringBuilder
        StringBuilder result = new StringBuilder("DadosAlunos:\n");

        // Itera sobre cada aluno na lista de alunos
        for (Aluno aluno : listaAlunos) {
            // Adiciona a representação em string do aluno atual ao resultado
            // O método toString do aluno é chamado automaticamente
            result.append(aluno.toString()).append("\n");
        }
        // Converte o StringBuilder para String e retorna o resultado
        // Isso inclui a representação de todos os alunos na lista, cada um em uma nova linha
        return result.toString();
    }
}

