import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // System.out.println("Commit inicial branch feature.");

        // Criando objetos para poder testar algumas funcionalidades
        Scanner entrada = new Scanner(System.in);
        List<Curso> listaCursos = new ArrayList<>();
        List<Aluno> listaAlunos = new ArrayList<>();

        Professor professor1 = new Professor("Gabriel", 38, 4);
        Professor professor2 = new Professor("André", 33, 6);

        Curso curso1 = new Curso("Java", professor1);
        Curso curso2 = new Curso("Python", professor2);

        Aluno aluno1 = new Aluno("Rafael", 20, listaCursos);
        Aluno aluno2 = new Aluno("Miguel", 24, listaCursos);

        listaAlunos.add(aluno1);
        listaAlunos.add(aluno2);

        Turma turma1 = new Turma(listaAlunos, 2022, curso1);

        aluno1.adicionarCurso(curso1);
        aluno2.adicionarCurso(curso2);

        aluno1.listarCursos();
        aluno1.listarCursosComIndice();

        aluno2.listarCursos();
        aluno2.removerCurso(entrada); // usando o método sobrecarregado
        aluno2.listarCursosComIndice();

        turma1.listarAlunos();
        turma1.listarAlunosComIndice();
    }
}