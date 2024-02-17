import java.util.ArrayList;
import java.util.Scanner;

public class DadosDiretores {
    Scanner scn = new Scanner(System.in);
    ArrayList<Diretor> listaDiretores = new ArrayList<>();

    //MÉTODOS DA CLASSE
    public void adicionarDiretor() {
        //RECEBENDO INFORMAÇÕES DA NOVA PESSOA
        System.out.print("Nome do novo diretor: ");
        String nomeNovoDiretor = scn.nextLine();

        System.out.print("Salário do novo diretor: ");
        double salarioNovoDiretor = scn.nextInt();
        scn.nextLine(); //consome quebra de linha do último input de double

        System.out.println("Escolha o cargo do novo diretor:");
        for (CargoFuncionario cargo : CargoFuncionario.values()) {
            System.out.println(cargo.getIndiceCargo() + ". " + cargo.getNumCargo());
        }
        int escolhaCargo = scn.nextInt();
        scn.nextLine(); //consome quebra de linha do último input de int

        CargoFuncionario cargoNovoDiretor;

        if (escolhaCargo >= 1 && escolhaCargo <= CargoFuncionario.values().length) {
            cargoNovoDiretor = CargoFuncionario.values()[escolhaCargo - 1];
        } else {
            System.out.println("Opção inválida. O cargo foi definido como null.");
            cargoNovoDiretor = null;
        }

        System.out.print("Tempo de trabalho do novo diretor: ");
        int tempoTrabalhoNovoDiretor = scn.nextInt();
        scn.nextLine(); // consome quebra de linha do último input de int

        //INSTANCIANDO OBJETO e ADICIONANDO À LISTA
        listaDiretores.add(new Diretor(nomeNovoDiretor, salarioNovoDiretor, cargoNovoDiretor, tempoTrabalhoNovoDiretor));

        //FEEDBACK AO USUÁRIO
        System.out.println("*" + nomeNovoDiretor.toUpperCase() + " foi adicionado à lista* \n");
    }

    public void removerDiretor(int indexDiretor) {
        listaDiretores.remove(indexDiretor);
    }

    public void buscarDiretor(int idDiretor) {
        if (idDiretor >= 0 && idDiretor < listaDiretores.size()) {
            System.out.println("Resultado da busca para o ID " + idDiretor + ":");
            System.out.println(listaDiretores.get(idDiretor));
        } else {
            System.out.println("Não foi possível localizar um diretor com a ID " + idDiretor + " em nosso sistema.");
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
