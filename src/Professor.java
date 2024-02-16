public class Professor extends Funcionario {
    // Atributos
    private int idade;
    private int tempoTrabalho;

    // Construtor
    public Professor(String nome, double salario, CargoFuncionario cargo, int idade, int tempoTrabalho) {
        super(nome, salario, cargo);
        this.idade = idade;
        this.tempoTrabalho = tempoTrabalho;
    }

    // Getters e Setters
    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getTempoTrabalho() {
        return tempoTrabalho;
    }

    public void setTempoTrabalho(int tempoTrabalho) {
        this.tempoTrabalho = tempoTrabalho;
    }

    // Método toString personalizado
    @Override
    public String toString() {
        return "Professor: " + getNome() + ", Idade: " + idade + ", Tempo de Trabalho: " + tempoTrabalho + " anos";
    }
}

