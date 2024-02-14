import java.util.List;
import java.util.Scanner;

public class Turma {
    // Atributos
    private List<Aluno> listaAlunos;
    private int ano; // ano de início da turma
    private Curso curso;

    // Construtor
    public Turma(List<Aluno> listaAlunos, int ano, Curso curso) {
        this.listaAlunos = listaAlunos;
        this.ano = ano;
        this.curso = curso;
    }

    // Getters e Setters
    public List<Aluno> getListaAlunos() {
        return listaAlunos;
    }

    public void setListaAlunos(List<Aluno> listaAlunos) {
        this.listaAlunos = listaAlunos;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    // Metodos
    public void listarAlunos() {
        if (listaAlunos.isEmpty()) {
            System.out.println("\nTurma vazia.");
        } else {
            System.out.println("\nLista de Alunos da Turma: ");
            for (Aluno aluno : listaAlunos) {
                System.out.println("Aluno: " + aluno.getNome());
            }
        }
    }

    public void listarAlunosComIndice() {
        if (listaAlunos.isEmpty()) {
            System.out.println("\nTurma vazia.");
        } else {
            System.out.println("\nLista de Alunos da Turma: ");
            for (int i = 0; i < listaAlunos.size(); i++) {
                System.out.println(i + " - " + listaAlunos.get(i).getNome());
            }
        }
    }

    public void adicionarAluno(Aluno aluno) {
        listaAlunos.add(aluno);
    }

    public void removerAluno(Aluno aluno) {
        listaAlunos.remove(aluno);
    }

    // sobrecarga do método removerAluno
    public void removerAluno(Scanner entrada) {
        listarAlunosComIndice();
        System.out.print("\nDigite o indice do curso que deseja remover: ");
        listaAlunos.remove(entrada.nextInt());
        entrada.nextLine();
    }
}
