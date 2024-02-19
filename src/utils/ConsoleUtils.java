package utils;

public class ConsoleUtils {

    public static void printLogoFMT() {
        System.out.println(" ███████████ ██████   ██████ ███████████");
        System.out.println("░░███░░░░░░█░░██████ ██████ ░█░░░███░░░█");
        System.out.println(" ░███   █ ░  ░███░█████░███ ░   ░███  ░ ");
        System.out.println(" ░███████    ░███░░███ ░███     ░███    ");
        System.out.println(" ░███░░░█    ░███ ░░░  ░███     ░███    ");
        System.out.println(" ░███  ░     ░███      ░███     ░███    ");
        System.out.println(" █████       █████     █████    █████   ");
        System.out.println("░░░░░       ░░░░░     ░░░░░    ░░░░░    ");
        System.out.println("\n");
    }

    public static void printLogoFullStack() {
        System.out.println("███████╗██╗   ██╗██╗     ██╗         ███████╗████████╗ █████╗  ██████╗██╗  ██╗");
        System.out.println("██╔════╝██║   ██║██║     ██║         ██╔════╝╚══██╔══╝██╔══██╗██╔════╝██║ ██╔╝");
        System.out.println("█████╗  ██║   ██║██║     ██║         ███████╗   ██║   ███████║██║     █████╔╝ ");
        System.out.println("██╔══╝  ██║   ██║██║     ██║         ╚════██║   ██║   ██╔══██║██║     ██╔═██╗ ");
        System.out.println("██║     ╚██████╔╝███████╗███████╗    ███████║   ██║   ██║  ██║╚██████╗██║  ██╗");
        System.out.println("╚═╝      ╚═════╝ ╚══════╝╚══════╝    ╚══════╝   ╚═╝   ╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝");
        System.out.println("                                                                              ");
    }

    public static void limparConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void menuPrincipalLogin(){
        System.out.println("\nBem-vindo ao sistema. Fazer login como funcionário ou aluno?");
        System.out.println("  1 - Login de Funcionários;");
        System.out.println("  2 - Login de Aluno;");
        System.out.println("  0 - Encerrar o programa.");
    }

    public static void menuLoginAluno(){
        System.out.println("\nLOGIN DO ALUNO\n");
        System.out.println("Selecione uma das opções abaixo:");
        System.out.println("  1 - Cadastrar novo aluno.");
        System.out.println("  2 - Entrar com aluno existente.");
        System.out.println("  0 - Voltar ao menu inicial.");
    }

    public static void menuLoginFuncionario(){
        System.out.println("\nLOGIN DO FUNCIONARIO\n");
        System.out.println("Selecione uma das opções abaixo:");
        System.out.println("  1 - Login do Diretor.");
        System.out.println("  2 - Login do Professor.");
        System.out.println("  0 - Voltar ao menu inicial.");
    }

    public static void menuLoginProfessor(){
        System.out.println("\nLOGIN DO PROFESSOR\n");
        System.out.println("Selecione uma das opções abaixo:");
        System.out.println("  1 - Cadastrar novo professor.");
        System.out.println("  2 - Entrar com professor existente.");
        System.out.println("  0 - Voltar ao menu inicial.");
    }

    public static void menuLoginDiretor(){
        System.out.println("LOGIN DO DIRETOR\n");
        System.out.println("Selecione uma das opções abaixo: \n");
        System.out.println("  1 - Cadastrar novo diretor.");
        System.out.println("  2 - Entrar com diretor existente.");
        System.out.println("  0 - Voltar ao menu inicial.");
    }

    public static void menuPrincipalAcoesAluno(String nomeAluno){
        System.out.println("\nBem-vindo: " + nomeAluno + "!. Selecione uma das opções abaixo:");
        System.out.println("  1 - Listar Cursos;");
        System.out.println("  2 - Adicionar Curso;");
        System.out.println("  3 - Remover Curso;");
        System.out.println("  4 - Alterar Status da Matrícula;");
        System.out.println("  0 - Encerrar sessão.");
    }




}