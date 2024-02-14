public class Professor {
    // Atributos
    String nome;
    int idade;
    int tempoTrabalho;

    // Método toString personalizado
    @Override
    public String toString() {
        return "Professor: " + nome + ", Idade: " + idade + ", Tempo de Trabalho: " + tempoTrabalho + " anos";
    }
}

