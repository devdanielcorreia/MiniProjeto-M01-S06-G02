import java.util.List;
import java.util.Scanner;

public class Aluno {
    // Atributos
    private String nome;
    private int idade;
    private List<Curso> listaCursos;

    // Construtor
    public Aluno(String nome, int idade, List<Curso> listaCursos) {
        this.nome = nome;
        this.idade = idade;
        this.listaCursos = listaCursos;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public List<Curso> getListaCursos() {
        return listaCursos;
    }

    // Metodos
    public void listarCursos() {
        if (listaCursos.isEmpty()) {
            System.out.println("\nNenhum curso encontrado.");
        } else {
            System.out.println("\nLista de Cursos de " + getNome() + ": ");
            for (Curso curso : listaCursos) {
                System.out.println("Curso: " + curso.getNome());
            }
        }
    }

    public void listarCursosComIndice() {
        if (listaCursos.isEmpty()) {
            System.out.println("\nNenhum curso encontrado.");
        } else {
            System.out.println("\nLista de Cursos de " + getNome() + ": ");
            for (int i = 0; i < listaCursos.size(); i++) {
                System.out.println(i + " - " + listaCursos.get(i).getNome());
            }
        }
    }

    public void adicionarCurso(Curso curso) {
        listaCursos.add(curso);
    }

    public void removerCurso(Curso curso) {
        listaCursos.remove(curso);
    }

    // sobrecarga do método removerCurso
    public void removerCurso(Scanner entrada) {
        listarCursosComIndice();
        System.out.print("\nDigite o indice do curso que deseja remover: ");
        listaCursos.remove(entrada.nextInt());
        entrada.nextLine();
    }
}
