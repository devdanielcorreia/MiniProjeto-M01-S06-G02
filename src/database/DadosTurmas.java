package database;

import model.Aluno;
import model.Curso;
import model.Turma;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static utils.ValidaEntradaUtils.validaInputInteger;

public class DadosTurmas {

    Scanner scn = new Scanner(System.in);

    private final String arquivoDados = "dados_turmas.csv";
    List<Turma> listaTurmas;

    public DadosTurmas() {
        listaTurmas = new ArrayList<>();
        carregarDados();
    }

    public List<Turma> getlistaTurmas() {
        return listaTurmas;
    }

    // MÉTODOS DA CLASSE
    public void adicionarTurma(Curso curso) {
        Turma novaTurma = null;
        try {
            int anoTurma = receberAno();

            List<Aluno> listaAlunosTurma = new ArrayList<>();

            // INSTANCIANDO OBJETO e ADICIONANDO À LISTA
            novaTurma = new Turma(listaAlunosTurma, anoTurma, curso);
            listaTurmas.add(novaTurma);

            // FEEDBACK AO USUÁRIO
            System.out.println("*Turma do curso " + curso + " foi adicionado ao sistema* \n");
            salvarDados();
        } catch (InputMismatchException e) {
            System.err.println("Erro de entrada. Por favor, insira um número para o ano.");
            scn.nextLine();
        }
    }

    private int receberAno() {
        System.out.print("Digite o ano da nova turma: ");
        return validaInputInteger(scn);
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

    private void carregarDados() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(arquivoDados))) {
            listaTurmas = (List<Turma>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de dados de turmas não encontrado. Será criado um novo arquivo.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar os dados das turmas:" + e.getMessage());
        }
    }

    private void salvarDados() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(arquivoDados))) {
            outputStream.writeObject(listaTurmas);
            System.out.println("Dados das turmas salvos com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados das turmas: " + e.getMessage());
        }
    }

    public void atualizarDados(Turma turmaAtualizada) {
        for (int i = 0; i < listaTurmas.size(); i++) {
            Turma turma = listaTurmas.get(i);
            if ((turma.getCurso().equals(turmaAtualizada.getCurso())) &&
                    (turma.getAno() == turmaAtualizada.getAno())) {
                listaTurmas.set(i, turmaAtualizada);
                salvarDados();
                return;
            }
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
