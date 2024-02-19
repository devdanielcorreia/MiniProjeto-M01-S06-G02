package service;

import database.DadosAlunos;
import database.DadosDiretores;
import database.DadosProfessores;
import database.DadosTurmas;
import model.*;

import java.util.List;
import java.util.Scanner;

import static utils.ConsoleUtils.menuPrincipalAcoesDiretor;
import static utils.ValidaEntradaUtils.*;

public class AcoesDiretorService {

    private DadosAlunos dadosAlunos = new DadosAlunos();
    private DadosProfessores dadosProfessores = new DadosProfessores();
    private DadosDiretores dadosDiretores = new DadosDiretores();
    private DadosTurmas dadosTurmas = new DadosTurmas();

    public void iniciaFluxoAcoesDiretor(Scanner scanner, Diretor diretorLogado) {

        while (true) {

            menuPrincipalAcoesDiretor(diretorLogado.getNome());

            int acaoDiretor = validaInputUsuarioRangeOpcoes(scanner, 0, 12);
            switch (acaoDiretor) {
                case 0:
                    return;
                case 1:
                    listarAlunos();
                    break;
                case 2:
                    adicionarAluno();
                    break;
                case 3:
                    removerAluno(scanner);
                    break;
                case 4:
                    listarProfessores();
                    break;
                case 5:
                    adicionarProfessor();
                    break;
                case 6:
                    removerProfessor(scanner);
                    break;
                case 7:
                    promoverProfessor(scanner);
                    break;
                case 8:
                    adicionarTurma(scanner);
                    break;
                case 9:
                    listarAlunosTurma(scanner);
                    break;
                case 10:
                    adicionarAlunoTurma(scanner);
                    break;
                case 11:
                    removerAlunoTurma(scanner);
                    break;
                case 12:
                    listarTodosUsuarios();
                    break;
                default:
                    System.out.println("Comando inválido. Use um dos comandos informados anteriormente. \n");
            }
        }
    }

    private void listarAlunos() {
        List<Aluno> listaAlunos = dadosAlunos.getListaAlunos();
        if (listaAlunos.isEmpty()) {
            System.out.println("Não há nenhum aluno cadastrado no sistema.");
        } else {
            System.out.println("LISTA DE ALUNOS.");
            for (int i = 0; i < listaAlunos.size(); i++) {
                System.out.println("ID " + (i + 1) + " - " + listaAlunos.get(i).toString());
            }
        }
    }

    private void adicionarAluno() {
        dadosAlunos.adicionarAluno();
    }

    private void removerAluno(Scanner scanner) {
        listarAlunos();
        if (!dadosAlunos.getListaAlunos().isEmpty()) {
            System.out.println("\nDigite o ID do aluno a ser removido.");
            dadosAlunos.removerAluno(validaInputInteger(scanner));
        }


    }

    private void listarProfessores() {
        List<Professor> listaProfessores = dadosProfessores.getListaProfessores();
        if (listaProfessores.isEmpty()) {
            System.out.println("Não há nenhum professor cadastrado no sistema.");
        } else {
            System.out.println("LISTA DE PROFESSORES.");
            for (int i = 0; i < listaProfessores.size(); i++) {
                System.out.println("ID " + (i + 1) + " - " + listaProfessores.get(i).toString());
            }
        }
    }

    private void adicionarProfessor() {
        dadosProfessores.adicionarProfessor();
    }

    private void removerProfessor(Scanner scanner) {
        listarProfessores();
        if (!dadosProfessores.getListaProfessores().isEmpty()) {
            System.out.println("\nDigite o ID do professor a ser removido.");
            dadosProfessores.removerProfessor(validaInputInteger(scanner), dadosTurmas);
        }
    }

    private void promoverProfessor(Scanner scanner) {
        listarProfessores();
        if (!dadosProfessores.getListaProfessores().isEmpty()) {
            System.out.println("\nDigite o ID do professor a ser promovido.");
            dadosProfessores.promoverProfessor(validaInputInteger(scanner));
        }
    }

    private void adicionarTurma(Scanner scanner) {
        listarProfessores();
        if (!dadosProfessores.getListaProfessores().isEmpty()) {
            System.out.println("\nDigite o ID do professor que dará aula nesta Turma.");
            Professor professorTurma = dadosProfessores.getListaProfessores().get(validaInputUsuarioRangeOpcoes(scanner, 0, dadosProfessores.getListaProfessores().size()) - 1);
            System.out.println("\nDigite o nome do Curso a ser criado junto da Turma.");
            String nomeCurso = validaInputStringNaoVazia(scanner);
            Curso novoCurso = new Curso(nomeCurso, professorTurma);
            dadosTurmas.adicionarTurma(novoCurso);
        } else {
            System.out.println("Não há nenhum Professor disponível no sistema para lecionar no momento!");
            System.out.println("Para prosseguir com a criação da Turma, realize antes a contratação de um novo Professor.\n");
        }
    }

    private void listarAlunosTurma(Scanner scanner) {
        listarTurmas();
        if (!dadosTurmas.getlistaTurmas().isEmpty()) {
            System.out.println("\nDigite o ID de uma Turma para listar os alunos matriculados.");
            Turma turma = dadosTurmas.getlistaTurmas().get(validaInputUsuarioRangeOpcoes(scanner, 1, dadosTurmas.getlistaTurmas().size()) - 1);
            turma.listarAlunosComIndice();
        }
    }

    private void adicionarAlunoTurma(Scanner scanner) {
        listarTurmas();
        if (!dadosTurmas.getlistaTurmas().isEmpty()) {
            System.out.println("\nDigite o ID da Turma que o Aluno será matriculado.");
            Turma turma = dadosTurmas.getlistaTurmas().get(validaInputUsuarioRangeOpcoes(scanner, 1, dadosTurmas.getlistaTurmas().size()) - 1);
            listarAlunos();
            if (!dadosAlunos.getListaAlunos().isEmpty()) {
                System.out.println("\nDigite o ID do Aluno que será matriculado na Turma: " + turma.getAno() + " do Curso " + turma.getCurso().getNome());
                Aluno alunoMatricular = dadosAlunos.getListaAlunos().get(validaInputUsuarioRangeOpcoes(scanner, 1, dadosAlunos.getListaAlunos().size()) - 1);
                turma.adicionarAluno(alunoMatricular);
                dadosTurmas.atualizarDados(turma);
                System.out.println("\nAluno ." + alunoMatricular.getNome() + " matriculado na Turma " + turma.getAno() + " do Curso " + turma.getCurso().getNome());
            }
        }
    }

    private void removerAlunoTurma(Scanner scanner) {
        listarTurmas();
        if (!dadosTurmas.getlistaTurmas().isEmpty()) {
            System.out.println("\nDigite o ID da Turma do em que o Aluno está matriculado.");
            Turma turma = dadosTurmas.getlistaTurmas().get(validaInputUsuarioRangeOpcoes(scanner, 1, dadosTurmas.getlistaTurmas().size()) - 1);
            if (!turma.getListaAlunos().isEmpty()) {
                turma.listarAlunosComIndice();
                System.out.println("\nDigite o ID do Aluno que será removido da Turma: " + turma.getAno() + " do Curso " + turma.getCurso().getNome());
                Aluno alunoRemover = turma.getListaAlunos().get(validaInputUsuarioRangeOpcoes(scanner, 1, turma.getListaAlunos().size()) - 1);
                turma.removerAluno(alunoRemover);
                dadosTurmas.atualizarDados(turma);
                System.out.println("\nAluno ." + alunoRemover.getNome() + " removido da Turma " + turma.getAno() + " do Curso " + turma.getCurso().getNome());
            } else {
                System.out.println("Turma selecionada não tem nenhum aluno matrículado.");
            }
        }
    }

    private void listarTodosUsuarios() {
        listarAlunos();
        listarProfessores();
    }

    private void listarTurmas() {
        List<Turma> listaTurmas = dadosTurmas.getlistaTurmas();
        if (listaTurmas.isEmpty()) {
            System.out.println("Não há nenhuma turma cadastrado no sistema.");
        } else {
            System.out.println("LISTA DE TURMAS.");
            for (int i = 0; i < listaTurmas.size(); i++) {
                System.out.println("ID " + (i + 1) + " - " + listaTurmas.get(i).toString());
            }
        }
    }
}
