package database;

import enumerations.StatusMatricula;
import model.Aluno;
import model.Curso;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static utils.ValidaEntradaUtils.*;

public class DadosAlunos {
    Scanner scn = new Scanner(System.in);

    private final String arquivoDados = "dados_alunos.csv";
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
        try {
            String nomeNovoAluno = receberNome();
            int idadeNovoAluno = receberIdade();
            StatusMatricula statusMatriculaNovoAluno = receberStatusMatricula();

            List<Curso> listaCursosNovoAluno = new ArrayList<>();

            // INSTANCIANDO OBJETO e ADICIONANDO À LISTA
            novoAluno = new Aluno(nomeNovoAluno, idadeNovoAluno, listaCursosNovoAluno, statusMatriculaNovoAluno);
            listaAlunos.add(novoAluno);

            // FEEDBACK AO USUÁRIO
            System.out.println("*" + nomeNovoAluno.toUpperCase() + " foi adicionado ao sistema* \n");
            salvarDados();
            return novoAluno;
        } catch (InputMismatchException e) {
            System.err.println("Erro de entrada. Por favor, insira um número para a idade.");
            scn.nextLine();
        }
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
        System.out.println("Escolha o status da matrícula do aluno:");
        int indiceMax = 0;
        for (StatusMatricula status : StatusMatricula.values()) {
            System.out.println(status.getIndice() + ". " + status.name());
            indiceMax = status.getIndice();
        }
        int indexStatus = validaInputUsuarioRangeOpcoes(scn, 1, indiceMax);

        StatusMatricula statusMatricula;

        statusMatricula = StatusMatricula.values()[indexStatus - 1];

        return statusMatricula;
    }

    public void removerAluno(int indexAluno) {
        try {
            listaAlunos.remove(indexAluno);
            salvarDados();
        } catch (IndexOutOfBoundsException e) {
            System.err.println("O índice ndice fornecido está fora do intervalo. Não foi possível remover o aluno.");
        }
    }

    public void buscarAluno(int idAluno) {
        try {
            if (idAluno >= 0 && idAluno < listaAlunos.size()) {
                System.out.println("Resultado da busca para o ID " + idAluno + ":");
                System.out.println(listaAlunos.get(idAluno));
            } else {
                throw new IllegalArgumentException("ID fornecido é inválido.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Não foi possível localizar um aluno com a ID " + idAluno + " em nosso sistema.");
        }
    }

    private void carregarDados() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(arquivoDados))) {
            listaAlunos = (List<Aluno>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de dados de alunos não encontrado. Será criado um novo arquivo.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar os dados dos alunos:" + e.getMessage());
        }
    }

    private void salvarDados() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(arquivoDados))) {
            outputStream.writeObject(listaAlunos);
            System.out.println("Dados dos alunos salvos com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados dos alunos: " + e.getMessage());
        }
    }

    public void atualizarDados(Aluno alunoAtualizado) {
        for (int i = 0; i < listaAlunos.size(); i++) {
            Aluno aluno = listaAlunos.get(i);
            if ((aluno.getNome().equals(alunoAtualizado.getNome())) &&
                    (aluno.getIdade() == alunoAtualizado.getIdade())) {
                listaAlunos.set(i, alunoAtualizado);
                salvarDados();
                return;
            }
        }
    }

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

