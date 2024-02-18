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

    // Método para promover um funcionário (Professor ou Diretor)
    @Override
    public void promover() {
        try {
            if (cargo == null) {
                throw new Exception("Cargo não pode ser nulo.");
            }

            switch (cargo) {
                case INICIANTE:
                    cargo = CargoFuncionario.EXPERIENTE;
                    System.out.println("O funcionário " + nome + " foi promovido para " + cargo.name() + ".");
                    break;
                case EXPERIENTE:
                    cargo = CargoFuncionario.AVANCADO;
                    System.out.println("O funcionário " + nome + " foi promovido para " + cargo.name() + ".");
                    break;
                case AVANCADO:
                    System.out.println("O funcionário " + nome + " já alcançou o cargo máximo.");
                    break;
                default:
                    throw new Exception("Cargo não encontrado para o funcionário " + nome + ".");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Funcionario{" +  "nome='" + nome + '\'' + ", salario=" + salario + ", cargo=" + cargo + '}';
    }
}

