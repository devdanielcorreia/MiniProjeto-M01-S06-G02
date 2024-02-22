package database;

import enumerations.CargoFuncionario;
import model.Curso;
import model.Professor;
import model.Turma;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static utils.ValidacaoUtils.*;

public class DadosProfessores {
    Scanner scn = new Scanner(System.in);

    private final String arquivoDados = "dados_professores.bin";
    List<Professor> listaProfessores;

    public DadosProfessores() {
        listaProfessores = new ArrayList<>();
        carregarDados();
    }

    public List<Professor> getListaProfessores() {
        return listaProfessores;
    }

    // MÉTODOS DA CLASSE
    public Professor adicionarProfessor() {
        Professor novoProfessor = null;
        try {
            String nomeNovoProfessor = receberNome();

            // INSTANCIANDO OBJETO e ADICIONANDO À LISTA
            novoProfessor = new Professor(nomeNovoProfessor, receberSalario(), receberCargo(), receberIdade(), receberTempoTrabalho());
            listaProfessores.add(novoProfessor);

            // FEEDBACK AO USUÁRIO
            System.out.println("*" + nomeNovoProfessor.toUpperCase() + " foi adicionado à lista* \n");
            salvarDados();
            return novoProfessor;
        } catch (InputMismatchException e) {
            System.err.println("Erro de entrada. Por favor, insira um número inteiro para a idade e o tempo de trabalho.");
            scn.nextLine(); // Consome a entrada incorreta para evitar loops infinitos
        } catch (Exception e) {
            System.err.println("Ocorreu um erro inesperado: " + e.getMessage());
        }
        return novoProfessor;
    }

    private String receberNome() {
        System.out.print("Nome do novo professor: ");
        return validaInputStringNaoVazia(scn);
    }

    private int receberSalario() {
        System.out.println("Digite o salário do novo professor: ");
        return validaInputInteger(scn);
    }

    private CargoFuncionario receberCargo() {
        // Receber o Cargo Funcionario do professor por String
        System.out.println("Cargo Funcionário do professor (INICIANTE, EXPERIENTE, AVANÇADO): ");
        // Loop para validar a entrada do usuário com os values de CargoFuncionario
        String cargoFuncionarioString = validaCargoFuncionario(scn);
        // Converter a string em um Enum CargoFuncionario utilizando o valueOf()
        return CargoFuncionario.valueOf(cargoFuncionarioString.toUpperCase());
    }

    private int receberIdade() {
        System.out.print("Idade do novo professor: ");
        return validaInputInteger(scn);
    }

    private int receberTempoTrabalho() {
        System.out.print("Tempo de trabalho do novo professor: ");
        return validaInputInteger(scn);
    }

    public void removerProfessor(int indexProfessor, DadosTurmas dadosTurmas) {
        try {
            List<Turma> listaTurmas = dadosTurmas.getlistaTurmas();
            List<Turma> listaTurmasAtualizadas = new ArrayList<>();
            Professor professorRemovido = listaProfessores.get(indexProfessor - 1);
            for(Turma turma : listaTurmas) {
                Curso curso = turma.getCurso();
                if (curso.getProfessor().equals(professorRemovido)) {
                    curso.setProfessor(null);
                    listaTurmasAtualizadas.add(turma);
                }
            }

            for (Turma turmaAtualizada : listaTurmasAtualizadas){
                dadosTurmas.atualizarDados(turmaAtualizada);
            }

            listaProfessores.remove(indexProfessor - 1);
            salvarDados();
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Índice fornecido está fora do intervalo. Operação de remoção falhou.");
        }
    }

    public void promoverProfessor(Professor professor) {
        try {
            professor.promover();
            atualizarDados(professor);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Índice fornecido está fora do intervalo. Operação de remoção falhou.");
        }
    }

    public Professor buscarProfessor(int idProfessor) {
        try {
            if (idProfessor >= 0 && idProfessor < listaProfessores.size()) {
                System.out.println("Resultado da busca para o ID " + (idProfessor + 1) + ":");
                System.out.println(listaProfessores.get(idProfessor));
            } else {
                throw new IllegalArgumentException("ID fornecido é inválido.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Não foi possível localizar um professor com a ID " + idProfessor + " em nosso sistema.");
        }
        return listaProfessores.get(idProfessor);
    }

    private void carregarDados() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(arquivoDados))) {
            listaProfessores = (List<Professor>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de dados de professores não encontrado. Será criado um novo arquivo.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar os dados dos professores:" + e.getMessage());
        }
    }

    private void salvarDados() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(arquivoDados))) {
            outputStream.writeObject(listaProfessores);
            System.out.println("Dados dos professores salvos com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados dos professores: " + e.getMessage());
        }
    }

    public void atualizarDados(Professor professorAtualizado) {
        for (int i = 0; i < listaProfessores.size(); i++) {
            if (listaProfessores.get(i).equals(professorAtualizado)) {
                listaProfessores.set(i, professorAtualizado);
                salvarDados();
                return;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("database.DadosProfessores:\n");
        for (Professor professor : listaProfessores) {
            result.append(professor.toString()).append("\n");
        }
        return result.toString();
    }
}


