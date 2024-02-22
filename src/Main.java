import database.DadosAlunos;
import database.DadosDiretores;
import database.DadosProfessores;
import database.DadosTurmas;
import model.Aluno;
import model.Diretor;
import model.Professor;
import service.AcoesAlunoService;
import service.AcoesDiretorService;
import service.AcoesProfessorService;
import service.LoginService;

import java.util.Scanner;

import static utils.ConsoleUtils.printLogoFMT;
import static utils.ConsoleUtils.printLogoFullStack;

public class Main {
    public static void main(String[] args) {

        DadosAlunos dadosAlunos = new DadosAlunos();
        DadosProfessores dadosProfessores = new DadosProfessores();
        DadosDiretores dadosDiretores = new DadosDiretores();
        DadosTurmas dadosTurmas = new DadosTurmas();

        printLogoFMT();
        printLogoFullStack();
        System.out.println("\nMINI PROJETO DO MODULO 1 - SEMANA 6");

        while (true) {
            Scanner entrada = new Scanner(System.in);

            LoginService loginService = new LoginService(dadosAlunos, dadosProfessores, dadosDiretores);
            loginService.iniciaFluxoLogin(entrada);

            Object usuarioLogado = loginService.getUsuarioLogado();

            if (usuarioLogado instanceof Aluno alunoLogado) {
                AcoesAlunoService alunoService = new AcoesAlunoService(dadosAlunos, dadosProfessores, dadosTurmas);
                alunoService.iniciaFluxoAcoesAluno(entrada, alunoLogado);
            } else if (usuarioLogado instanceof Diretor diretorLogado) {
                AcoesDiretorService acoesDiretorService = new AcoesDiretorService(dadosAlunos, dadosProfessores, dadosTurmas, diretorLogado);
                acoesDiretorService.iniciaFluxoAcoesDiretor(entrada);
            } else if (usuarioLogado instanceof Professor professorLogado) {
                AcoesProfessorService acoesProfessorService = new AcoesProfessorService(dadosAlunos, dadosTurmas);
                acoesProfessorService.iniciaFluxoAcoesProfessor(entrada, professorLogado);
            }
        }

    }
}