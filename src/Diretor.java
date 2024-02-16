import java.util.List;

// Classe diretor herdando de funcionário
public class Diretor extends Funcionario {
    // Atributos
    int tempoDeCargo; // Será adotado em anos

    // Construtor
    public Diretor(String nome, double salario, CargoFuncionario cargo, int tempoDeCargo) {
        super(nome, salario, cargo);
        this.tempoDeCargo = tempoDeCargo;
    }

    // Professor deve ter acesso às funcionalidades listarTurma, adicionarAluno e removerAluno (já existente em Turma)
    // Métodos para criar uma nova turma (não existente em outra classe)
    public Turma criarTurma(List<Aluno> listaAlunos, int ano, Curso curso) {
        Turma novaTurma = new Turma(listaAlunos, ano, curso);
        return novaTurma;
    }

    // Método para listar alunos de uma turma
    public void listarAlunosTurma(Turma turma) {
        turma.listarAlunos();
    }

    // Método para adicionar aluno à turma
    public void adicionarAlunoTurma(Turma turma, Aluno aluno) {
        turma.adicionarAluno(aluno);
    }

    // Método para remover aluno da turma
    public void removerAlunoTurma(Turma turma, Aluno aluno) {
        turma.removerAluno(aluno);
    }

    // Getters e Setters para tempoDeCargo
    public int getTempoDeCargo() {
        return tempoDeCargo;
    }

    public void setTempoDeCargo(int tempoDeCargo) {
        this.tempoDeCargo = tempoDeCargo;
    }

    // Método toString personalizado
    @Override
    public String toString() {
        return "Diretor: " + getNome() + ", Salário: " + getSalario() + ", Tempo de Cargo: " + tempoDeCargo + " anos.";
    }
}
