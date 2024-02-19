package service;

import database.DadosAlunos;
import database.DadosTurmas;
import model.Aluno;
import model.Curso;
import model.Professor;
import model.Turma;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static utils.ConsoleUtils.listarAlunos;
import static utils.ConsoleUtils.menuPrincipalAcoesProfessor;
import static utils.ValidaEntradaUtils.validaInputUsuarioRangeOpcoes;

public class AcoesProfessorService {

    private DadosAlunos dadosAlunos = new DadosAlunos();
    private DadosTurmas dadosTurmas = new DadosTurmas();
    private String nomeProfessor = "";

    public void iniciaFluxoAcoesDiretor(Scanner scanner, Professor professorLogado) {

        nomeProfessor = professorLogado.getNome();
        while (true) {

            menuPrincipalAcoesProfessor(nomeProfessor);

            int acaoDiretor = validaInputUsuarioRangeOpcoes(scanner, 0, 12);
            switch (acaoDiretor) {
                case 0:
                    return;
                case 1:
                    listarAlunosTurma(scanner, professorLogado);
                    break;
                case 2:
                    adicionarAlunoTurma(scanner, professorLogado);
                    break;
                case 3:
                    removerAlunoTurma(scanner, professorLogado);
                    break;
                default:
                    System.out.println("Comando inválido. Use um dos comandos informados anteriormente. \n");
            }
        }
    }

    private void adicionarAlunoTurma(Scanner scanner, Professor professor) {
        listarTurmasProfessor(professor);
        List<Turma> listaTurmasProfessor = recuperaTurmasProfessor(professor);
        if (!listaTurmasProfessor.isEmpty()) {
            System.out.println("\nDigite o ID da Turma que o Aluno será matriculado.");
            Turma turma = listaTurmasProfessor.get(validaInputUsuarioRangeOpcoes(scanner, 1, listaTurmasProfessor.size()) - 1);
            listarAlunos(dadosAlunos);
            if (!dadosAlunos.getListaAlunos().isEmpty()) {
                System.out.println("\nDigite o ID do Aluno que será matriculado na Turma: " + turma.getAno() + " do Curso " + turma.getCurso().getNome());
                Aluno alunoMatricular = dadosAlunos.getListaAlunos().get(validaInputUsuarioRangeOpcoes(scanner, 1, dadosAlunos.getListaAlunos().size()) - 1);
                turma.adicionarAluno(alunoMatricular);
                dadosTurmas.atualizarDados(turma);
                System.out.println("\nAluno " + alunoMatricular.getNome() + " matriculado na Turma " + turma.getAno() + " do Curso " + turma.getCurso().getNome());
            }
        }
    }

    private void removerAlunoTurma(Scanner scanner, Professor professor) {
        List<Turma> listaTurmasProfessor = recuperaTurmasProfessor(professor);
        listarTurmasProfessor(professor);
        if (!listaTurmasProfessor.isEmpty()) {
            System.out.println("\nDigite o ID da Turma do em que o Aluno está matriculado.");
            Turma turma = listaTurmasProfessor.get(validaInputUsuarioRangeOpcoes(scanner, 1, listaTurmasProfessor.size()) - 1);
            if (!turma.getListaAlunos().isEmpty()) {
                turma.listarAlunosComIndice();
                System.out.println("\nDigite o ID do Aluno que será removido da Turma: " + turma.getAno() + " do Curso " + turma.getCurso().getNome());
                Aluno alunoRemover = turma.getListaAlunos().get(validaInputUsuarioRangeOpcoes(scanner, 1, turma.getListaAlunos().size()) - 1);
                turma.removerAluno(alunoRemover);
                dadosTurmas.atualizarDados(turma);
                System.out.println("\nAluno " + alunoRemover.getNome() + " removido da Turma " + turma.getAno() + " do Curso " + turma.getCurso().getNome());
            } else {
                System.out.println("Turma selecionada não tem nenhum aluno matrículado.");
            }
        }
    }

    private void listarAlunosTurma(Scanner scanner, Professor professor) {
        listarTurmasProfessor(professor);
        List<Turma> listaTurmasProfessor = recuperaTurmasProfessor(professor);
        if (!listaTurmasProfessor.isEmpty()) {
            System.out.println("\nDigite o ID de uma Turma para listar os alunos matriculados.");
            Turma turma = listaTurmasProfessor.get(validaInputUsuarioRangeOpcoes(scanner, 1, listaTurmasProfessor.size()) - 1);
            turma.listarAlunosComIndice();
        }
    }

    private void listarTurmasProfessor(Professor professor) {
        List<Turma> listaTurmasProfessor = recuperaTurmasProfessor(professor);
        if (dadosTurmas.getlistaTurmas().isEmpty()) {
            System.out.println("Não há nenhuma turma cadastrado no sistema.");
        } else if (listaTurmasProfessor.isEmpty()) {
            System.out.println("Não há nenhuma turma associada ao Professor " + nomeProfessor);
        } else {
            System.out.println("LISTA DE TURMAS do Professor " + nomeProfessor);
            for (int i = 0; i < listaTurmasProfessor.size(); i++) {
                System.out.println("ID " + (i + 1) + " - " + listaTurmasProfessor.get(i).toString());
            }
        }
    }

    private List<Turma> recuperaTurmasProfessor(Professor professor) {
        List<Turma> listaTurmas = dadosTurmas.getlistaTurmas();
        List<Turma> listaTurmasProfessor = new ArrayList<>();
        if (!listaTurmas.isEmpty()) {
            for (Turma turma : listaTurmas) {
                Curso curso = turma.getCurso();
                if ((curso.getProfessor() != null) && curso.getProfessor().getNome().equals(professor.getNome())
                        && curso.getProfessor().getSalario() == professor.getSalario()
                        && curso.getProfessor().getCargo().equals(professor.getCargo())
                        && curso.getProfessor().getIdade() == professor.getIdade()) {
                    listaTurmasProfessor.add(turma);
                }
            }
        }
        return listaTurmasProfessor;
    }
}
