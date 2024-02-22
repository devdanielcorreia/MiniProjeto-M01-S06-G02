package service;

import database.DadosAlunos;
import database.DadosProfessores;
import database.DadosTurmas;
import enumerations.StatusMatricula;
import model.Aluno;
import model.Curso;
import model.Turma;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utils.ConsoleUtils.menuPrincipalAcoesAluno;
import static utils.ValidacaoUtils.*;

public class AcoesAlunoService {

    private DadosAlunos dadosAlunos;
    private DadosProfessores dadosProfessores;
    private DadosTurmas dadosTurmas;

    public AcoesAlunoService(DadosAlunos dadosAlunos, DadosProfessores dadosProfessores, DadosTurmas dadosTurmas) {
        this.dadosAlunos = dadosAlunos;
        this.dadosProfessores = dadosProfessores;
        this.dadosTurmas = dadosTurmas;
    }

    public void iniciaFluxoAcoesAluno(Scanner scanner, Aluno alunoLogado) {


        matriculaSeAindaNaoMatriculado(scanner, alunoLogado);

        while (true) {

            menuPrincipalAcoesAluno(alunoLogado.getNome());

            int acaoAluno = validaInputUsuarioRangeOpcoes(scanner, 0, 4);
            switch (acaoAluno) {
                case 0:
                    return;
                case 1:
                    listaCursosAluno(alunoLogado);
                    break;
                case 2:
                    adicionarCurso(scanner, alunoLogado);
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
        List<Curso> listaCursos = dadosTurmas.getCursosMatriculados(aluno);
        if (listaCursos.isEmpty()) {
            System.out.println("Você não está matriculado em nenhum curso no momento.");
        } else {
            System.out.println("LISTA DE CURSOS.");
            for (int i = 0; i < listaCursos.size(); i++) {
                System.out.println((i + 1) + " - " + listaCursos.get(i).getNome() +
                        " - Professor: " + (listaCursos.get(i).getProfessor() != null ? listaCursos.get(i).getProfessor().getNome() : "Não Cadastrado"));
            }
        }
    }

    private void adicionarCurso(Scanner scanner, Aluno aluno) {
        List<Turma> listaTurmas = dadosTurmas.getlistaTurmas();
        if (listaTurmas.isEmpty()) {
            System.out.println("Não há nenhuma turma cadastrada no sistema.");
        } else {
            System.out.println("Selecione uma turma para se matricular:");
            System.out.println("LISTA DE TURMAS.");
            for (int i = 0; i < listaTurmas.size(); i++) {
                System.out.println((i + 1) + " - Curso: " + listaTurmas.get(i).getCurso().getNome() +
                        " - Turma: " + listaTurmas.get(i).getAno() +
                        " - Professor: " + (listaTurmas.get(i).getCurso().getProfessor() != null ? listaTurmas.get(i).getCurso().getProfessor().getNome() : "Não Cadastrado"));
            }
            Turma turmaSelecionada = listaTurmas.get(validaInputUsuarioRangeOpcoes(scanner, 1, listaTurmas.size()) - 1);
            if(!validaSeAlunoJaTemMatricula(turmaSelecionada, aluno)){
                turmaSelecionada.adicionarAluno(aluno);
                dadosTurmas.atualizarDados(turmaSelecionada);
            } else {
                System.out.println("Aluno já possui matrícula na Turma " + turmaSelecionada.getAno() + " do Curso " + turmaSelecionada.getCurso().getNome());
            }

        }
    }

    public void removeCurso(Scanner scanner, Aluno aluno) {
        List<Turma> listaTurmas = dadosTurmas.getlistaTurmas();
        List<Turma> turmasMatriculado = new ArrayList<>();

        for (Turma turma : listaTurmas) {
            if (turma.getListaAlunos().contains(aluno)) {
                turmasMatriculado.add(turma);
            }
        }

        listaCursosAluno(aluno);

        if(!turmasMatriculado.isEmpty()){
            System.out.println("Digite o número do curso do qual deseja sair:");
            int opcao = validaInputUsuarioRangeOpcoes(scanner, 1, listaTurmas.size()) - 1;

            Turma turmaSelecionada = turmasMatriculado.get(opcao);
            turmaSelecionada.removerAluno(aluno);
            System.out.println("Você foi removido do curso " + turmaSelecionada.getCurso().getNome() + " com sucesso.");
            dadosTurmas.atualizarDados(turmaSelecionada);
        }


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

    private void matriculaSeAindaNaoMatriculado(Scanner scanner, Aluno alunoLogado){
        if(dadosTurmas.getCursosMatriculados(alunoLogado).isEmpty()){
            System.out.println("\nVocê ainda não está matriculado em nenhum Curso. ");
            System.out.println("Gostaria de verificar os Cursos disponíveis e realizar sua matrícula agora? (S/N)");
            String entrada =validaInputStringNaoVazia(scanner);
            if(entrada.equalsIgnoreCase("S")){
                adicionarCurso(scanner, alunoLogado);
            }
        }
    }

}
