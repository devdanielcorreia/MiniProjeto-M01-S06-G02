import java.util.ArrayList;
import java.util.Scanner;

public class DadosProfessores {
    Scanner scn = new Scanner(System.in);
    ArrayList<Professor> listaProfessores = new ArrayList<>();

    //MÉTODOS DA CLASSE
    public void adicionarProfessor() {
        //RECEBENDO INFORMAÇÕES DA NOVA PESSOA
        System.out.print("Nome do novo professor: ");
        String nomeNovoProfessor = scn.nextLine();

        System.out.print("Idade do novo professor: ");
        int idadeNovoProfessor = scn.nextInt();

        System.out.print("Tempo de trabalho do novo professor: ");
        int tempoTrabalhoNovoProfessor = scn.nextInt();
        scn.nextLine(); // consome quebra de linha do último input de int

        //INSTANCIANDO OBJETO e ADICIONANDO À LISTA
        listaProfessores.add(new Professor(nomeNovoProfessor, 3000, CargoFuncionario.INICIANTE, idadeNovoProfessor, tempoTrabalhoNovoProfessor));

        //FEEDBACK AO USUÁRIO
        System.out.println("*" + nomeNovoProfessor.toUpperCase() + " foi adicionado à lista* \n");
    }

    public void removerProfessor(int indexProfessor) {
        listaProfessores.remove(indexProfessor);
    }

    public void buscarProfessor(int idProfessor) {
        if (idProfessor >= 0 && idProfessor < listaProfessores.size()) {
            System.out.println("Resultado da busca para o ID " + idProfessor + ":");
            System.out.println(listaProfessores.get(idProfessor));
        } else {
            System.out.println("Não foi possível localizar um professor com a ID " + idProfessor + " em nosso sistema.");
        }
    }
    @Override
    public String toString() {
        String result = "DadosProfessores:\n";
        for (Professor professor : listaProfessores) {
            result += professor.toString();
        }
        return result;
    }
}
