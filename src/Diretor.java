public class Diretor extends Funcionario {
    // Atributos
    int tempoDeCargo; // Será adotado em anos

    // Construtor
    public Diretor(String nome, double salario, int tempoDeCargo) {
        super(nome, salario);
        this.tempoDeCargo = tempoDeCargo;
    }

    // Métodos estáticos omitidos para brevidade

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
        return "Diretor: " + getNome() + ", Salário: " + getSalario() + ", Tempo de Cargo: " + tempoDeCargo + " anos";
    }
}

