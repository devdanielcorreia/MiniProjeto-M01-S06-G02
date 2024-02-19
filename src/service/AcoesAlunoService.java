package service;

import database.DadosAlunos;
import database.DadosProfessores;
import database.DadosTurmas;
import enumerations.StatusMatricula;
import model.Aluno;
import model.Curso;
import model.Professor;
import model.Turma;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utils.ConsoleUtils.menuPrincipalAcoesAluno;
import static utils.ValidaEntradaUtils.validaInputStringNaoVazia;
import static utils.ValidaEntradaUtils.validaInputUsuarioRangeOpcoes;

public class AcoesAlunoService {

    private DadosAlunos dadosAlunos = new DadosAlunos();
    private DadosProfessores dadosProfessores = new DadosProfessores();
    private DadosTurmas dadosTurmas = new DadosTurmas();

    public void iniciaFluxoAcoesAluno(Scanner scanner, Aluno alunoLogado) {

        while (true) {

            selecionarTurma(scanner, alunoLogado);

            menuPrincipalAcoesAluno(alunoLogado.getNome());

            int acaoAluno = validaInputUsuarioRangeOpcoes(scanner, 0, 4);
            switch (acaoAluno) {
                case 0:
                    return;
                case 1:
                    listaCursosAluno(alunoLogado);
                    break;
                case 2:
                    adicionaCurso(scanner, alunoLogado);
                    break;
                case 3:
                    removeCurso(scanner, alunoLogado);
                    break;
                case 4:
                    alteraStatusMatricula(scanner, alunoLogado);
                    break;
                default:
                    System.out.println("Comando inválido. Use um dos comandos informados anteriormente. \n");
            }
        }


    }

    private void listaCursosAluno(Aluno aluno) {
        List<Curso> listaCursos = aluno.getListaCursos();
        if (listaCursos.isEmpty()) {
            System.out.println("Você não está matriculado em nenhum curso no momento.");
        } else {
            System.out.println("LISTA DE CURSOS.");
            for (int i = 0; i < listaCursos.size(); i++) {
                System.out.println(i + " - " + listaCursos.get(i).getNome() +
                        " - Professor: " + (listaCursos.get(i).getProfessor() != null ? listaCursos.get(i).getProfessor().getNome() : "Não Cadastrado"));
            }
        }
    }

    private void adicionaCurso(Scanner scanner, Aluno aluno) {
        System.out.println("Digite as informações do curso a ser adicionado.");
        System.out.println("Nome do curso: ");
        String nomeCurso = validaInputStringNaoVazia(scanner);
        List<Professor> listaProfessores = dadosProfessores.getListaProfessores();
        if (listaProfessores.isEmpty()) {
            System.out.println("\nNão há nenhum professor cadastrado no momento. O curso será cadastrado sem um professor.");
        } else {
            System.out.println("\nSelecione o professor que leciona o curso: ");
            for (int i = 0; i < listaProfessores.size(); i++) {
                System.out.println(i + " - Professor " + listaProfessores.get(i).getNome());
            }
        }
        Curso novoCurso = new Curso(nomeCurso, listaProfessores.get(validaInputUsuarioRangeOpcoes(scanner, 0, listaProfessores.size() - 1)));
        aluno.adicionarCurso(novoCurso);
        dadosAlunos.atualizarDados(aluno);
    }

    private void removeCurso(Scanner scanner, Aluno aluno) {
        listaCursosAluno(aluno);
        List<Curso> listaCursos = aluno.getListaCursos();
        if (!listaCursos.isEmpty()) {
            aluno.removerCurso(scanner);
        }
        dadosAlunos.atualizarDados(aluno);
    }

    private void alteraStatusMatricula(Scanner scanner, Aluno aluno) {
        System.out.println("ALTERAÇÃO DE STATUS DO ALUNO.");
        System.out.println("Seu status atual é: " + aluno.getStatusMatricula());
        System.out.println("Gostaria de altera seu status para qual dos status abaixo:");

        List<StatusMatricula> statusOptions = new ArrayList<>(Arrays.asList(StatusMatricula.values()));
        statusOptions.remove(2);

        for (int i = 0; i < statusOptions.size(); i++) {
            if (statusOptions.get(i).name().equals(aluno.getStatusMatricula().name())) {
                statusOptions.remove(i);
            }
        }

        for (int i = 0; i < statusOptions.size(); i++) {
            System.out.println((i + 1) + ". " + statusOptions.get(i).name());
        }

        int opcao = validaInputUsuarioRangeOpcoes(scanner, 1, statusOptions.size());
        StatusMatricula novoStatus = statusOptions.get(opcao - 1);

        aluno.setStatusMatricula(novoStatus);

        System.out.println("Status de matrícula atualizado para: " + novoStatus);

        dadosAlunos.atualizarDados(aluno);
    }

    private void selecionarTurma(Scanner scanner, Aluno aluno) {
        List<Turma> listaTurmas = dadosTurmas.getlistaTurmas();
        if (listaTurmas.isEmpty()) {
            System.out.println("Não há nenhuma turma cadastrada no sistema.");
        } else {
            System.out.println("Selecione uma turma para se matricular:");
            System.out.println("LISTA DE TURMAS.");
            for (int i = 0; i < listaTurmas.size(); i++) {
                System.out.println(i + " - " + listaTurmas.get(i).getCurso().getNome() +
                        " - Professor: " + (listaTurmas.get(i).getCurso().getProfessor() != null ? listaTurmas.get(i).getCurso().getProfessor().getNome() : "Não Cadastrado"));
            }
            Turma turmaSelecionada = listaTurmas.get(validaInputUsuarioRangeOpcoes(scanner, 0, listaTurmas.size() - 1));
            turmaSelecionada.adicionarAluno(aluno);
        }
    }

}
