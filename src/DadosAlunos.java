import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DadosAlunos {
    Scanner scn = new Scanner(System.in);
    List<Aluno> listaAlunos = new ArrayList<>();

    // MÉTODOS DA CLASSE
    public void adicionarAluno() {
        try {
            String nomeNovoAluno = receberNome();
            int idadeNovoAluno = receberIdade();
            StatusMatricula statusMatriculaNovoAluno = receberStatusMatricula();

            List<Curso> listaCursosNovoAluno = new ArrayList<>();

            // INSTANCIANDO OBJETO e ADICIONANDO À LISTA
            listaAlunos.add(new Aluno(nomeNovoAluno, idadeNovoAluno, listaCursosNovoAluno, statusMatriculaNovoAluno));

            // FEEDBACK AO USUÁRIO
            System.out.println("*" + nomeNovoAluno.toUpperCase() + " foi adicionado à lista* \n");
        } catch (InputMismatchException e) {
            System.err.println("Erro de entrada. Por favor, insira um número para a idade.");
            scn.nextLine();
        } finally {
        }
    }

    private String receberNome() {
        System.out.print("Nome do novo aluno: ");
        return scn.nextLine();
    }

    private int receberIdade(){
        System.out.print("Idade do novo aluno: ");
        int idadeNovoAluno = scn.nextInt();
        scn.nextLine(); // consome quebra de linha

        return idadeNovoAluno;
    }

    private StatusMatricula receberStatusMatricula() {
        // Receber o Status da Matricula do aluno por String
        System.out.println("Status da matrícula do aluno (ATIVO, TRANCADO, FORMADO): ");
        String statusMatriculaString = scn.nextLine();

        // Loop em values StatusMatricula
        for (StatusMatricula status : StatusMatricula.values()) {
            // Verificação se o que o usuário digitou existe em values()
            if (status.name().equalsIgnoreCase(statusMatriculaString)) {
                try {
                    // Converter a string em um Enum CargoFuncionario utilizando o valueOf()
                    return StatusMatricula.valueOf(statusMatriculaString.toUpperCase());
                } catch (IllegalArgumentException e) {
                    // Se ocorrer uma exceção, informar ao usuário e continuar o loop
                    System.out.println("Status matrícula inválido. Por favor, digite um status válido.");
                    break;
                }
            }
        }

        // Se não encontrar correspondência, informar ao usuário que o status é inválido e retornar null
        System.out.println("Status matrícula inválido. Por favor, digite um status válido.");
        return null;
    }

    public void removerAluno(int indexAluno) {
        try {
            listaAlunos.remove(indexAluno);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("O índice ndice fornecido está fora do intervalo. Não foi possível remover o aluno.");
        }
    }

    public void buscarAluno(int idAluno) {
        try {
            if (idAluno >= 0 && idAluno < listaAlunos.size()) {
                System.out.println("Resultado da busca para o ID " + idAluno + ":");
                System.out.println(listaAlunos.get(idAluno));
            } else {
                throw new IllegalArgumentException("ID fornecido é inválido.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Não foi possível localizar um aluno com a ID " + idAluno + " em nosso sistema.");
        }
    }

    @Override
    public String toString() {
        // Inicia a construção de uma string com um StringBuilder
        StringBuilder result = new StringBuilder("DadosAlunos:\n");

        // Itera sobre cada aluno na lista de alunos
        for (Aluno aluno : listaAlunos) {
            // Adiciona a representação em string do aluno atual ao resultado
            // O método toString do aluno é chamado automaticamente
            result.append(aluno.toString()).append("\n");
        }
        // Converte o StringBuilder para String e retorna o resultado
        // Isso inclui a representação de todos os alunos na lista, cada um em uma nova linha
        return result.toString();
    }
}

