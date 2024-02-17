import java.util.ArrayList;
import java.util.Scanner;

public class DadosAlunos {
    Scanner scn = new Scanner(System.in);
    ArrayList<Aluno> listaAlunos = new ArrayList<>();

    //MÉTODOS DA CLASSE
    public void adicionarAluno() {
        //RECEBENDO INFORMAÇÕES DA NOVA PESSOA
        System.out.print("Nome do novo aluno: ");
        String nomeNovoAluno = scn.nextLine();

        System.out.print("Idade do novo aluno: ");
        int idadeNovoAluno = scn.nextInt();

        ArrayList<Curso> listaCursosNovoAluno = new ArrayList<>();

        //INSTANCIANDO OBJETO e ADICIONANDO À LISTA
        listaAlunos.add(new Aluno(nomeNovoAluno, idadeNovoAluno, listaCursosNovoAluno, StatusMatricula.ATIVO));

        //FEEDBACK AO USUÁRIO
        System.out.println("*" + nomeNovoAluno.toUpperCase() + " foi adicionado à lista* \n");
    }

    public void removerAluno(int indexAluno) {
        try {
            listaAlunos.remove(indexAluno);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Índice fornecido está fora do intervalo. Operação de remoção falhou.");
        }
    }

    public void buscarAluno(int idAluno) {
        try {
            if (idAluno >= 0 && idAluno < listaAlunos.size()) {
                System.out.println("Resultado da busca para o ID " + idAluno + ":");
                System.out.println(listaAlunos.get(idAluno));
            } else {
                throw new IllegalArgumentException("ID fornecido é inválido.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Não foi possível localizar um aluno com a ID " + idAluno + " em nosso sistema.");
        }
    }
}
