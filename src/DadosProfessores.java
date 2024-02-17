import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DadosProfessores {
    Scanner scn = new Scanner(System.in);
    ArrayList<Professor> listaProfessores = new ArrayList<>();

    // MÉTODOS DA CLASSE
    public void adicionarProfessor() {
        try {
            // RECEBENDO INFORMAÇÕES DA NOVA PESSOA
            System.out.print("Nome do novo professor: ");
            String nomeNovoProfessor = scn.nextLine();

            System.out.print("Idade do novo professor: ");
            int idadeNovoProfessor = scn.nextInt();

            System.out.print("Tempo de trabalho do novo professor: ");
            int tempoTrabalhoNovoProfessor = scn.nextInt();
            scn.nextLine(); // consome quebra de linha do último input de int

            // INSTANCIANDO OBJETO e ADICIONANDO À LISTA
            listaProfessores.add(new Professor(nomeNovoProfessor, 3000, CargoFuncionario.INICIANTE, idadeNovoProfessor, tempoTrabalhoNovoProfessor));

            // FEEDBACK AO USUÁRIO
            System.out.println("*" + nomeNovoProfessor.toUpperCase() + " foi adicionado à lista* \n");
        } catch (InputMismatchException e) {
            System.err.println("Erro de entrada. Por favor, insira um número inteiro para a idade e o tempo de trabalho.");
            scn.nextLine(); // Consome a entrada incorreta para evitar loops infinitos
        } catch (Exception e) {
            System.err.println("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }

    public void removerProfessor(int indexProfessor) {
        try {
            listaProfessores.remove(indexProfessor);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Índice fornecido está fora do intervalo. Operação de remoção falhou.");
        }
    }

    public void buscarProfessor(int idProfessor) {
        try {
            if (idProfessor >= 0 && idProfessor < listaProfessores.size()) {
                System.out.println("Resultado da busca para o ID " + idProfessor + ":");
                System.out.println(listaProfessores.get(idProfessor));
            } else {
                throw new IllegalArgumentException("ID fornecido é inválido.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Não foi possível localizar um professor com a ID " + idProfessor + " em nosso sistema.");
        }
    }
}

