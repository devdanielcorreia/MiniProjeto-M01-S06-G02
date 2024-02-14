import java.util.List;

public class Turma {
    // Atributos
    List<Aluno> listaAlunos;
    int ano; // ano de início da turma
    Curso curso;

    // Métodos
    public void listarAlunos() {
        for (int i = 0; i < listaAlunos.size(); i++) {
            System.out.println("Nome do Aluno: " + listaAlunos.get(i).nome);
        }
    }

    public void adicionarAluno(Aluno aluno) {
        listaAlunos.add(aluno);
    }

    public void removerAluno(Aluno aluno) {
        listaAlunos.remove(aluno);
    }

    // Método toString personalizado
    @Override
    public String toString() {
        String result = "Ano de Início: " + ano + ", Curso: " + curso.toString() + ", Total de Alunos: " + listaAlunos.size();
        return result;
    }
}

