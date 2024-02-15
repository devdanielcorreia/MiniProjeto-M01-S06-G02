public class Funcionario {

    // Atributos
    private String nome;
    private double salario;

    // Construtor
    public Funcionario(String nome, double salario) {
        this.nome = nome;
        this.salario = salario;
    }

    // Método para retornar o nome do funcionário
    public String getNome() {
        return nome;
    }

    // Método para retornar o salário do funcionário
    public double getSalario() {
        return salario;
    }
}

