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
    }
}