package service;

import database.DadosAlunos;
import database.DadosTurmas;
import enumerations.StatusMatricula;
import model.Aluno;
import model.Curso;
import model.Professor;
import model.Turma;

import java.util.*;

import static utils.ConsoleUtils.listarAlunos;
import static utils.ConsoleUtils.menuPrincipalAcoesProfessor;
import static utils.ValidacaoUtils.validaInputUsuarioRangeOpcoes;
import static utils.ValidacaoUtils.validaSeAlunoJaTemMatricula;

public class AcoesProfessorService {

    private DadosAlunos dadosAlunos;
    private DadosTurmas dadosTurmas;
    private String nomeProfessor = "";

    public AcoesProfessorService(DadosAlunos dadosAlunos, DadosTurmas dadosTurmas) {
        this.dadosAlunos = dadosAlunos;
        this.dadosTurmas = dadosTurmas;
    }

    public void iniciaFluxoAcoesProfessor(Scanner scanner, Professor professorLogado) {

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
                case 4:
                    formarAlunoTurma(scanner, professorLogado);
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
                Aluno alunoMatricular = dadosAlunos.buscarAluno(validaInputUsuarioRangeOpcoes(scanner, 1, dadosAlunos.getListaAlunos().size()) - 1);
                if(!validaSeAlunoJaTemMatricula(turma, alunoMatricular)){
                    turma.adicionarAluno(alunoMatricular);
                    dadosTurmas.atualizarDados(turma);
                    System.out.println("\nAluno " + alunoMatricular.getNome() + " matriculado na Turma " + turma.getAno() + " do Curso " + turma.getCurso().getNome());
                } else {
                    System.out.println("\nAluno já está matriculado na Turma " + turma.getAno() + " do Curso " + turma.getCurso().getNome());
                }

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

    private void formarAlunoTurma(Scanner scanner, Professor professor) {
        List<Turma> listaTurmasProfessor = recuperaTurmasProfessor(professor);
        Map<Turma, List<Aluno>> mapAlunoTurma = new LinkedHashMap<>();

        for (Turma turma : listaTurmasProfessor) {
            List<Aluno> alunos = turma.getListaAlunos();
            for (Aluno aluno : alunos) {
                if (!mapAlunoTurma.containsKey(turma)) {
                    mapAlunoTurma.put(turma, new ArrayList<>());
                }
                mapAlunoTurma.get(turma).add(aluno);
            }
        }

        if (!mapAlunoTurma.isEmpty()) {
            int indexPrint = 1;
            for (Map.Entry<Turma, List<Aluno>> entry : mapAlunoTurma.entrySet()) {
                Turma turma = entry.getKey();
                List<Aluno> alunos = entry.getValue();
                for (Aluno aluno : alunos) {
                    System.out.println(indexPrint + " - " + aluno.getNome() + ", Turma: " + turma.getAno() + ", Curso:" + turma.getCurso().getNome());
                    indexPrint++;
                }
            }
            System.out.println("\nDigite o ID do Aluno que será formado");

            int indiceSelecionado = validaInputUsuarioRangeOpcoes(scanner, 1, indexPrint - 1);

            Turma turmaAlunoFormar = null;
            Aluno alunoFormar = null;
            int indexAux = 1;
            for (Map.Entry<Turma, List<Aluno>> entry : mapAlunoTurma.entrySet()) {
                Turma turma = entry.getKey();
                List<Aluno> alunos = entry.getValue();
                for (Aluno aluno : alunos) {
                    if (indexAux == indiceSelecionado) {
                        turmaAlunoFormar = turma;
                        alunoFormar = aluno;
                        break;
                    }
                    indexAux++;
                }
                if (turmaAlunoFormar != null && alunoFormar != null) {
                    break;
                }
            }


            alunoFormar.setStatusMatricula(StatusMatricula.FORMADO);
            dadosAlunos.atualizarDados(alunoFormar);
            turmaAlunoFormar.removerAluno(alunoFormar);
            dadosTurmas.atualizarDados(turmaAlunoFormar);
            System.out.println("\nAluno " + alunoFormar.getNome() + " da Turma " + turmaAlunoFormar.getAno() + " do Curso " + turmaAlunoFormar.getCurso().getNome() + " foi formado com sucesso!");
        } else {
            System.out.println("Não há nenhum aluno matriculado em Turmas do professor " + nomeProfessor);
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
                Professor professorCurso = curso.getProfessor();
                if ((curso.getProfessor() != null) && professorCurso.equals(professor)) {
                    listaTurmasProfessor.add(turma);
                }
            }
        }
        return listaTurmasProfessor;
    }
}
