public class Curso {
    // Atributos
    String nome;
    Professor professor;

    // Método toString personalizado
    @Override
    public String toString() {
        return "Curso: " + nome + ", Professor: " + (professor != null ? professor.toString() : "Nenhum");
    }
}

