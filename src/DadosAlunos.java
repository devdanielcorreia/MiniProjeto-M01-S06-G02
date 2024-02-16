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
        listaAlunos.add(new Aluno(nomeNovoAluno, idadeNovoAluno, listaCursosNovoAluno));

        //FEEDBACK AO USUÁRIO
        System.out.println("*" + nomeNovoAluno.toUpperCase() + " foi adicionado à lista* \n");
    }

    public void removerAluno(int indexAluno) {
        listaAlunos.remove(indexAluno);
    }

    public void buscarAluno(int idAluno) {
        if (idAluno >= 0 && idAluno < listaAlunos.size()) {
            System.out.println("Resultado da busca para o ID " + idAluno + ":");
            System.out.println(listaAlunos.get(idAluno));
        } else {
            System.out.println("Não foi possível localizar um aluno com a ID " + idAluno + " em nosso sistema.");
        }
    }
    @Override
    public String toString() {
        String result = "DadosAlunos{\n";
        for (Aluno aluno : listaAlunos) { // Correção aplicada aqui
            result += aluno.toString() + "\n";
        }
        result += "}";
        return result;
    }
}
