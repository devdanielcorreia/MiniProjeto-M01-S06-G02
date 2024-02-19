package model;

import enumerations.StatusMatricula;

import java.io.Serial;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Aluno implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // Atributos
    private String nome;
    private int idade;
    private List<Curso> listaCursos;
    private StatusMatricula statusMatricula;

    // Construtor
    public Aluno(String nome, int idade, List<Curso> listaCursos, StatusMatricula statusMatricula) {
        this.nome = nome;
        this.idade = idade;
        this.listaCursos = listaCursos;
        this.statusMatricula = statusMatricula;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public List<Curso> getListaCursos() {
        return listaCursos;
    }

    public StatusMatricula getStatusMatricula() {
        return statusMatricula;
    }

    public void setStatusMatricula(StatusMatricula statusMatricula) {
        this.statusMatricula = statusMatricula;
    }

    // Metodos
    @Override
    public String toString() {
        // Inicia um StringBuilder para construir a string de forma eficiente
        StringBuilder result = new StringBuilder("Aluno: " + nome + "\nIdade: " + idade + "\nStatus de Matrícula: " + statusMatricula + "\nCursos: ");
        // Verifica se a lista de cursos está vazia
        if (listaCursos.isEmpty()) {
            // Adiciona uma mensagem indicando que nenhum curso está cadastrado
            result.append("Nenhum curso cadastrado.");
        } else {
            // Para cada curso na lista, adiciona o nome do curso ao resultado
            listaCursos.forEach(curso -> result.append("\n- ").append(curso.getNome()));
        }
        // Converte o StringBuilder para String e o retorna
        return result.toString();
    }


    public void listarCursos() {
        if (listaCursos.isEmpty()) {
            System.out.println("\nNenhum curso encontrado.");
        } else {
            System.out.println("\nLista de Cursos de " + getNome() + ": ");
            for (Curso curso : listaCursos) {
                System.out.println("Curso: " + curso.getNome());
            }
        }
    }

    public void listarCursosComIndice() {
        if (listaCursos.isEmpty()) {
            System.out.println("\nNenhum curso encontrado.");
        } else {
            System.out.println("\nLista de Cursos de " + getNome() + ": ");
            for (int i = 0; i < listaCursos.size(); i++) {
                System.out.println(i + " - " + listaCursos.get(i).getNome());
            }
        }
    }

    public void adicionarCurso(Curso curso) {
        listaCursos.add(curso);
    }

    public void removerCurso(Curso curso) {
        listaCursos.remove(curso);
    }

    // sobrecarga do método removerCurso
    public void removerCurso(Scanner entrada) {
        // Exibe a lista de cursos com índices para o usuário
        System.out.print("\nDigite o índice do curso que deseja remover: ");
        try {
            // Tenta ler o índice do curso como um inteiro
            int indice = entrada.nextInt();
            // Verifica se o índice está dentro do intervalo válido da lista
            if (indice >= 0 && indice < listaCursos.size()) {
                // Remove o curso com o índice especificado
                listaCursos.remove(indice);
                System.out.println("Curso removido com sucesso.");
            } else {
                // Informa ao usuário que o índice é inválido
                System.out.println("Índice inválido.");
            }
        } catch (InputMismatchException e) {
            // Captura exceções caso o usuário não digite um inteiro
            System.out.println("Por favor, insira um número inteiro válido.");
        } finally {
            // Sempre limpa o buffer do scanner para evitar erros na próxima leitura
            entrada.nextLine();
        }
    }

}
