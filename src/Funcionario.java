public class Funcionario implements IFuncionario {

    // Atributos
    private String nome;
    private double salario;
    private CargoFuncionario cargo;

    // Construtor
    public Funcionario(String nome, double salario, CargoFuncionario cargo) {
        this.nome = nome;
        this.salario = salario;
        this.cargo = cargo;
    }

    // Getters ----------------------------------------------------------------
    // Método para retornar o nome do funcionário
    public String getNome() {
        return nome;
    }

    // Método para retornar o salário do funcionário
    public double getSalario() {
        return salario;
    }

    // Método para retornar cargo de funcionário
    public CargoFuncionario getCargo() {
        return cargo;
    }

    // Setters ----------------------------------------------------------------
    // Método para set de nome (se útil)
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Método para set de salário de funcionário
    public void setSalario(double salario) {
        this.salario = salario;
    }

    // Método para set de cargo de funcionário
    public void setCargo(CargoFuncionario cargo) {
        this.cargo = cargo;
    }

    // Método para promover um funcionário - NÃO FINALIZADO
    public void promover() {
        CargoFuncionario[] cargos = CargoFuncionario.values();

        for (int i = 0; i < cargos.length - 1; i++) {
            if (cargos[i] == cargo) {
                cargo = cargos[i + 1];
                System.out.println("O funcionário " + nome + " foi promovido para " + cargo.getNumCargo());
                return;
            }
        }

        try {
            throw new IllegalArgumentException("Cargo não encontrado para o funcionário " + nome);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

