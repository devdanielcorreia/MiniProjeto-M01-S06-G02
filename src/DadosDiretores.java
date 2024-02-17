import java.util.*;

public class DadosDiretores {
    Scanner scn = new Scanner(System.in);
    List<Diretor> listaDiretores = new ArrayList<>();

    // MÉTODOS DA CLASSE
    public void adicionarDiretor() {
        try {
            String nomeNovoDiretor = receberNome();

            // INSTANCIANDO OBJETO e ADICIONANDO À LISTA
            listaDiretores.add(new Diretor(nomeNovoDiretor, receberSalario(), receberCargo(), receberTempoTrabalho()));

            // FEEDBACK AO USUÁRIO
            System.out.println("*" + nomeNovoDiretor.toUpperCase() + " foi adicionado à lista* \n");
        } catch (InputMismatchException e) {
            System.err.println("Entrada inválida. Por favor, verifique os dados inseridos.");
            scn.nextLine(); // Consome a entrada incorreta para evitar loops infinitos
        } catch (NoSuchElementException e) {
            System.err.println("Entrada insuficiente fornecida.");
        } catch (Exception e) {
            System.err.println("Ocorreu um erro ao adicionar o diretor: " + e.getMessage());
        }
    }

    private String receberNome() {
        System.out.print("Nome do novo diretor: ");
        return scn.nextLine();
    }

    private int receberSalario() {
        System.out.print("Salário do novo diretor: ");
        int salarioNovoDiretor = scn.nextInt();
        scn.nextLine(); // consome quebra de linha do último input de int
        return salarioNovoDiretor;
    }

    private int receberTempoTrabalho() {
        System.out.print("Tempo de trabalho do novo diretor: ");
        int tempoTrabalhoNovoDiretor = scn.nextInt();
        scn.nextLine(); // consome quebra de linha do último input de int

        return tempoTrabalhoNovoDiretor;
    }

    private CargoFuncionario receberCargo() {
        System.out.println("Escolha o cargo do novo diretor:");
        for (CargoFuncionario cargo : CargoFuncionario.values()) {
            System.out.println(cargo.getIndiceCargo() + ". " + cargo.getNumCargo());
        }
        int escolhaCargo = scn.nextInt();
        scn.nextLine(); // consome quebra de linha do último input de int

        CargoFuncionario cargoNovoDiretor;

        if (escolhaCargo >= 1 && escolhaCargo <= CargoFuncionario.values().length) {
            cargoNovoDiretor = CargoFuncionario.values()[escolhaCargo - 1];
        } else {
            System.out.println("Opção inválida. O cargo foi definido como null.");
            cargoNovoDiretor = null;
        }

        return cargoNovoDiretor;
    }

    public void removerDiretor(int indexDiretor) {
        try {
            listaDiretores.remove(indexDiretor);
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
    @Override
    public String toString() {
        String result = "DadosDiretores:\n";
        for (Diretor diretor : listaDiretores) {
            result += diretor.toString();
        }
        return result;
    }
}
