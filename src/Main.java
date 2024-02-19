import model.Aluno;
import model.Diretor;
import model.Professor;
import service.AcoesAlunoService;
import service.AcoesDiretorService;
import service.LoginService;

import java.util.Scanner;

import static utils.ConsoleUtils.printLogoFMT;
import static utils.ConsoleUtils.printLogoFullStack;

public class Main {
    public static void main(String[] args) {

        printLogoFMT();
        printLogoFullStack();
        System.out.println("\nMINI PROJETO DO MODULO 1 - SEMANA 6");

        Scanner entrada = new Scanner(System.in);

        LoginService loginService = new LoginService();
        loginService.iniciaFluxoLogin(entrada);

        Object usuarioLogado = loginService.getUsuarioLogado();

        if (usuarioLogado instanceof Aluno) {
            Aluno alunoLogado = (Aluno) usuarioLogado;
            AcoesAlunoService alunoService = new AcoesAlunoService();
            alunoService.iniciaFluxoAcoesAluno(entrada, alunoLogado);
        } else if (usuarioLogado instanceof Diretor) {
            Diretor diretorLogado = (Diretor) usuarioLogado;
            AcoesDiretorService acoesDiretorService =  new AcoesDiretorService();
            acoesDiretorService.iniciaFluxoAcoesDiretor(entrada, diretorLogado);
        } else if (usuarioLogado instanceof Professor) {
            Professor professorLogado = (Professor) usuarioLogado;
        } else {
        }
    }
}