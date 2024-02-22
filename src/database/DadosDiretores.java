package database;

import enumerations.CargoFuncionario;
import model.Diretor;

import java.io.*;
import java.util.*;

import static utils.ValidacaoUtils.*;

public class DadosDiretores {
    Scanner scn = new Scanner(System.in);

    private final String arquivoDados = "dados_diretores.bin";
    List<Diretor> listaDiretores;

    public DadosDiretores() {
        listaDiretores = new ArrayList<>();
        carregarDados();
    }

    public List<Diretor> getListaDiretores() {
        return listaDiretores;
    }

    // MÉTODOS DA CLASSE
    public Diretor adicionarDiretor() {
        Diretor novoDiretor = null;
        try {
            String nomeNovoDiretor = receberNome();

            // INSTANCIANDO OBJETO e ADICIONANDO À LISTA
            novoDiretor = new Diretor(nomeNovoDiretor, receberSalario(), receberCargo(), receberTempoTrabalho());
            listaDiretores.add(novoDiretor);

            // FEEDBACK AO USUÁRIO
            System.out.println("*" + nomeNovoDiretor.toUpperCase() + " foi adicionado à lista* \n");
            salvarDados();
            return novoDiretor;
        } catch (InputMismatchException e) {
            System.err.println("Entrada inválida. Por favor, verifique os dados inseridos.");
            scn.nextLine(); // Consome a entrada incorreta para evitar loops infinitos
        } catch (NoSuchElementException e) {
            System.err.println("Entrada insuficiente fornecida.");
        } catch (Exception e) {
            System.err.println("Ocorreu um erro ao adicionar o diretor: " + e.getMessage());
        }
        return novoDiretor;
    }

    private String receberNome() {
        System.out.print("Nome do novo diretor: ");
        return validaInputStringNaoVazia(scn);
    }

    private int receberSalario() {
        System.out.print("Salário do novo diretor: ");
        return validaInputInteger(scn);
    }

    private int receberTempoTrabalho() {
        System.out.print("Tempo de trabalho do novo diretor: ");
        return validaInputInteger(scn);
    }

    private CargoFuncionario receberCargo() {
        System.out.println("Escolha o cargo do novo diretor:");
        int indiceMax = 0;
        for (CargoFuncionario cargo : CargoFuncionario.values()) {
            System.out.println(cargo.getIndiceCargo() + ". " + cargo.getNomeCargo());
            indiceMax = cargo.getIndiceCargo();
        }
        int escolhaCargo = validaInputUsuarioRangeOpcoes(scn, 1, indiceMax);

        CargoFuncionario cargoNovoDiretor;

        cargoNovoDiretor = CargoFuncionario.values()[escolhaCargo - 1];

        return cargoNovoDiretor;
    }

    public void removerDiretor(int indexDiretor) {
        try {
            listaDiretores.remove(indexDiretor);
            salvarDados();
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Índice fornecido está fora do intervalo. Operação de remoção falhou.");
        }
    }

    public void buscarDiretor(int idDiretor) {
        try {
            if (idDiretor >= 0 && idDiretor < listaDiretores.size()) {
                System.out.println("Resultado da busca para o ID " + idDiretor + ":");
                System.out.println(listaDiretores.get(idDiretor));
            } else {
                throw new IllegalArgumentException("ID fornecido é inválido.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Não foi possível localizar um diretor com a ID " + idDiretor + " em nosso sistema.");
        }
    }

    private void carregarDados() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(arquivoDados))) {
            listaDiretores = (List<Diretor>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de dados de diretores não encontrado. Será criado um novo arquivo.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar os dados dos diretores:" + e.getMessage());
        }
    }

    private void salvarDados() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(arquivoDados))) {
            outputStream.writeObject(listaDiretores);
            System.out.println("Dados dos diretores salvos com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados dos diretores: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("DadosDiretores:\n");
        for (Diretor diretor : listaDiretores) {
            result.append(diretor.toString()).append("\n");
        }
        return result.toString();
    }
}
