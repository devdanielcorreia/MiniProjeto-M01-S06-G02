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

    public static void menuPrincipalAcoesDiretor(String nomeDiretor){
        System.out.println("\nBem-vindo: " + nomeDiretor + "!. Selecione uma das opções abaixo:");
        System.out.println("  1 - Listar Alunos;");
        System.out.println("  2 - Adicionar Aluno;");
        System.out.println("  3 - Remover Aluno;");
        System.out.println("  4 - Listar Professores;");
        System.out.println("  5 - Adicionar Professor;");
        System.out.println("  6 - Remover Professor;");
        System.out.println("  7 - Promover um Professor;");
        System.out.println("  8 - Adicionar uma Turma;");
        System.out.println("  9 - Listar Alunos de uma Turma;");
        System.out.println("  10 - Adicionar Aluno a uma Turma;");
        System.out.println("  11 - Remover Aluno de uma Turma;");
        System.out.println("  12 - Listar Todos os Usuários;");
        System.out.println("  0 - Encerrar sessão.");
    }




}
