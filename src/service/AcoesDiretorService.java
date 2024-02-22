package service;

import database.DadosAlunos;
import database.DadosProfessores;
import database.DadosTurmas;
import model.*;

import java.util.*;

import static utils.ConsoleUtils.listarAlunos;
import static utils.ConsoleUtils.menuPrincipalAcoesDiretor;
import static utils.ValidacaoUtils.*;

public class AcoesDiretorService {

    private DadosAlunos dadosAlunos;
    private DadosProfessores dadosProfessores;
    private DadosTurmas dadosTurmas;
    private Diretor diretor;

    public AcoesDiretorService(DadosAlunos dadosAlunos, DadosProfessores dadosProfessores, DadosTurmas dadosTurmas, Diretor diretor) {
        this.dadosAlunos = dadosAlunos;
        this.dadosProfessores = dadosProfessores;
        this.dadosTurmas = dadosTurmas;
        this.diretor = diretor;
    }

    public void iniciaFluxoAcoesDiretor(Scanner scanner) {

        while (true) {

            matriculaAlunosSemTurma();

            cadastrarProfessorTurmaSemProfessor();

            menuPrincipalAcoesDiretor(diretor.getNome());

            int acaoDiretor = validaInputUsuarioRangeOpcoes(scanner, 0, 12);
            switch (acaoDiretor) {
                case 0:
                    return;
                case 1:
                    listarAlunos(dadosAlunos);
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
                default:
                    System.out.println("Comando inválido. Use um dos comandos informados anteriormente. \n");
            }
        }
    }

    private void adicionarAluno() {
        dadosAlunos.adicionarAluno();
    }

    private void removerAluno(Scanner scanner) {
        listarAlunos(dadosAlunos);
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
            Professor professorPromovido = dadosProfessores.buscarProfessor(validaInputUsuarioRangeOpcoes(scanner, 1, dadosProfessores.getListaProfessores().size()) - 1);
            dadosProfessores.promoverProfessor(professorPromovido);
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
            List<Aluno> listaAlunosTurma = new ArrayList<>();
            System.out.print("Digite o ano da nova turma: ");
            Turma novaTurma = diretor.criarTurma(listaAlunosTurma, validaInputInteger(scanner), novoCurso);
            dadosTurmas.adicionarTurma(novaTurma);
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
            diretor.listarAlunosTurma(turma);
        }
    }

    private void adicionarAlunoTurma(Scanner scanner) {
        listarTurmas();
        if (!dadosTurmas.getlistaTurmas().isEmpty()) {
            System.out.println("\nDigite o ID da Turma que o Aluno será matriculado.");
            Turma turma = dadosTurmas.getlistaTurmas().get(validaInputUsuarioRangeOpcoes(scanner, 1, dadosTurmas.getlistaTurmas().size()) - 1);
            listarAlunos(dadosAlunos);
            if (!dadosAlunos.getListaAlunos().isEmpty()) {
                System.out.println("\nDigite o ID do Aluno que será matriculado na Turma: " + turma.getAno() + " do Curso " + turma.getCurso().getNome());
                Aluno alunoMatricular = dadosAlunos.buscarAluno(validaInputUsuarioRangeOpcoes(scanner, 1, dadosAlunos.getListaAlunos().size()) - 1);
                diretor.adicionarAlunoTurma(turma, alunoMatricular);
                dadosTurmas.atualizarDados(turma);
                System.out.println("\nAluno " + alunoMatricular.getNome() + " matriculado na Turma " + turma.getAno() + " do Curso " + turma.getCurso().getNome());
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
                diretor.removerAlunoTurma(turma, alunoRemover);
                dadosTurmas.atualizarDados(turma);
                System.out.println("\nAluno " + alunoRemover.getNome() + " removido da Turma " + turma.getAno() + " do Curso " + turma.getCurso().getNome());
            } else {
                System.out.println("Turma selecionada não tem nenhum aluno matrículado.");
            }
        }
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

    private void matriculaAlunosSemTurma(){
        List<Aluno> alunosNaoMatriculados = getAlunosNaoMatriculados();
        if(dadosTurmas.getlistaTurmas().isEmpty() && !alunosNaoMatriculados.isEmpty()){
            System.out.println("\nALERTA!");
            System.out.println("OS ALUNOS ABAIXO NÃO ESTÃO MATRICULADOS EM NENHUMA TURMA E NÃO HÁ NENHUMA TURMA CADASTRADA NO SISTEMA.");
            System.out.println("PARA REGULARIZAR OS REGISTROS, CADASTRE UMA TURMA/CURSO E MATRICULE OS ALUNOS:");
            int index = 1;
            for (Aluno aluno : alunosNaoMatriculados){
                System.out.println(index + " - " +  aluno.getNome());
                index++;
            }
        } else if (!alunosNaoMatriculados.isEmpty()){
            System.out.println("\nALERTA!");
            System.out.println("OS ALUNOS ABAIXO NÃO ESTÃO MATRICULADOS EM NENHUMA TURMA.");
            System.out.println("PARA REGULARIZAR OS REGISTROS, MATRICULE OS ALUNOS EM ALGUMA TURMA/CURSO:");

            int index = 1;
            for (Aluno aluno : alunosNaoMatriculados){
                System.out.println(index + " - " +  aluno.getNome());
                index++;
            }
        }
    }

    public List<Aluno> getAlunosNaoMatriculados() {
        List<Aluno> todosAlunos = dadosAlunos.getListaAlunos();
        List<Turma> listaTurmas = dadosTurmas.getlistaTurmas();
        List<Aluno> alunosNaoMatriculados = new ArrayList<>();
        Map<Turma, List<Aluno>> mapAlunoTurma = new LinkedHashMap<>();

        for (Turma turma : listaTurmas) {
            List<Aluno> alunos = turma.getListaAlunos();
            for (Aluno aluno : alunos) {
                if (!mapAlunoTurma.containsKey(turma)) {
                    mapAlunoTurma.put(turma, new ArrayList<>());
                }
                mapAlunoTurma.get(turma).add(aluno);
            }
        }

        for (Aluno aluno : todosAlunos) {
            boolean alunoMatriculado = false;
            for (List<Aluno> alunosDaTurma : mapAlunoTurma.values()) {
                if (alunosDaTurma.contains(aluno)) {
                    alunoMatriculado = true;
                    break;
                }
            }
            if (!alunoMatriculado) {
                alunosNaoMatriculados.add(aluno);
            }
        }

        return alunosNaoMatriculados;
    }

    private void cadastrarProfessorTurmaSemProfessor(){
        List<Turma> listaTurmasSemProfessorComAlunos = getTurmasSemProfessorComAlunos();

        if(dadosProfessores.getListaProfessores().isEmpty() && !listaTurmasSemProfessorComAlunos.isEmpty()){
            System.out.println("\nALERTA!");
            System.out.println("AS TURMAS ABAIXO NÃO TEM NENHUM PROFESSOR CADASTRADO E NÃO HÁ NENHUM PROFESSOR CADASTRADA NO SISTEMA.");
            System.out.println("PARA REGULARIZAR OS REGISTROS E PARA QUE OS ALUNOS NÃO FIQUEM SEM PROFESSOR, CADASTRE UM PROFESSOR E ADICIONE-O A TURMA:");
            int index = 1;
            for (Turma turma : listaTurmasSemProfessorComAlunos){
                System.out.println(index + " - " +  turma.getCurso().getNome());
                index++;
            }
        } else if (!listaTurmasSemProfessorComAlunos.isEmpty()){
            System.out.println("\nALERTA!");
            System.out.println("AS TURMAS ABAIXO NÃO TEM NENHUM PROFESSOR CADASTRADO.");
            System.out.println("PARA REGULARIZAR OS REGISTROS E PARA QUE OS ALUNOS NÃO FIQUEM SEM PROFESSOR, CADASTRE UM PROFESSOR PARA DAR AULA NA TURMA:");

            int index = 1;
            for (Turma turma : listaTurmasSemProfessorComAlunos){
                System.out.println(index + " - " +  turma.getCurso().getNome());
                index++;
            }
        }

    }

    public List<Turma> getTurmasSemProfessorComAlunos() {
        List<Turma> listaTurmas = dadosTurmas.getlistaTurmas();
        List<Turma> listaTurmasSemProfessor = new ArrayList<>();

        for (Turma turma : listaTurmas) {
            if(turma.getCurso().getProfessor() == null && !turma.getListaAlunos().isEmpty()){
                listaTurmasSemProfessor.add(turma);
            }
        }

        return listaTurmasSemProfessor;
    }

}
