import java.util.List;

public class Aluno {
    // Atributos
    String nome;
    int idade;
    List<Curso> listaCursos;

    // Metodos
    public void listarCursos() {
        System.out.println("Lista de Cursos: ");
        for (int i = 0; i < listaCursos.size(); i++) {
            System.out.println("Nome do curso: " + listaCursos.get(i).nome);
        }
    }

    public void adicionarCurso(Curso curso) {
        listaCursos.add(curso);
    }

    public void removerCurso(Curso curso) {
        listaCursos.remove(curso);
    }
}
