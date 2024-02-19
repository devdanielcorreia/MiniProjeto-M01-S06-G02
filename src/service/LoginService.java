package service;

import database.DadosAlunos;
import database.DadosDiretores;
import database.DadosProfessores;
import model.Aluno;
import model.Diretor;
import model.Professor;

import java.util.List;
import java.util.Scanner;

import static utils.ConsoleUtils.*;
import static utils.ValidaEntradaUtils.validaInputUsuarioRangeOpcoes;

public class LoginService {

    private Object usuarioLogado;

    private static final String TIPO_ALUNO = "aluno";
    private static final String TIPO_DIRETOR = "diretor";

    private DadosAlunos dadosAlunos = new DadosAlunos();
    private DadosProfessores dadosProfessores = new DadosProfessores();
    private DadosDiretores dadosDiretores = new DadosDiretores();

    public void iniciaFluxoLogin(Scanner scanner) {

        boolean loginBemSucedido = false;

        while (!loginBemSucedido) {

            menuPrincipalLogin();

            int tipoUsuario = validaInputUsuarioRangeOpcoes(scanner, 0, 2);

            switch (tipoUsuario) {
                case 0:
                    encerrarPrograma(scanner);
                    return;
                case 1:
                    fluxoLoginFuncionario(scanner);
                    loginBemSucedido = true;
                    break;
                case 2:
                    fluxoLoginAluno(scanner);
                    loginBemSucedido = true;
                    break;
                default:
                    System.out.println("Comando inválido. Use um dos comandos informados anteriormente. \n");
            }

        }
    }

    private void encerrarPrograma(Scanner scanner) {
        System.out.println("Saindo do programa. \n");
        scanner.close();
        System.exit(0);
    }

    private void fluxoLoginAluno(Scanner scanner) {
        boolean loginBemSucedido = false;

        while (!loginBemSucedido) {
            limparConsole();
            menuLoginAluno();

            int operacao = validaInputUsuarioRangeOpcoes(scanner, 0, 2);

            switch (operacao) {
                case 1:
                    usuarioLogado = dadosAlunos.adicionarAluno();
                    loginBemSucedido = true;
                    break;
                case 2:
                    loginBemSucedido = fluxoLoginUsuarioExistente(scanner, dadosAlunos.getListaAlunos(), "aluno");
                    break;
                case 0:
                    return;
            }
        }
    }

    private void fluxoLoginFuncionario(Scanner scanner) {
        while (true) {
            limparConsole();
            menuLoginFuncionario();

            int opcao = validaInputUsuarioRangeOpcoes(scanner, 0, 2);

            switch (opcao) {
                case 1:
                    fluxoLoginDiretor(scanner);
                    return;
                case 2:
                    fluxoLoginProfessor(scanner);
                    return;
                case 0:
                    return;
            }
        }
    }

    private void fluxoLoginDiretor(Scanner scanner) {
        boolean loginBemSucedido = false;

        while (!loginBemSucedido) {
            limparConsole();
            menuLoginDiretor();

            int operacao = validaInputUsuarioRangeOpcoes(scanner, 0, 2);

            switch (operacao) {
                case 1:
                    usuarioLogado = dadosDiretores.adicionarDiretor();
                    loginBemSucedido = true;
                    break;
                case 2:
                    loginBemSucedido = fluxoLoginUsuarioExistente(scanner, dadosDiretores.getListaDiretores(), "diretor");
                    break;
                case 0:
                    return;
            }
        }
    }

    private void fluxoLoginProfessor(Scanner scanner) {
        boolean loginBemSucedido = false;

        while (!loginBemSucedido) {
            limparConsole();
            menuLoginProfessor();

            int operacao = validaInputUsuarioRangeOpcoes(scanner, 0, 2);

            switch (operacao) {
                case 1:
                    usuarioLogado = dadosProfessores.adicionarProfessor();
                    loginBemSucedido = true;
                    break;
                case 2:
                    loginBemSucedido = fluxoLoginUsuarioExistente(scanner, dadosProfessores.getListaProfessores(), "professor");
                    break;
                case 0:
                    return;
            }
        }
    }

    private boolean fluxoLoginUsuarioExistente(Scanner scanner, List<?> listaUsuarios, String tipoUsuario) {
        if(listaUsuarios.isEmpty()){
            System.out.println("Não há nenhum " + tipoUsuario + " cadastrado no sistema");
            return false;
        }
        exibirUsuariosExistente(listaUsuarios, tipoUsuario);

        while (true) {
            System.out.println("Digite o número do " + tipoUsuario + " que deseja selecionar (ou '0' para voltar): ");
            int opcao = validaInputUsuarioRangeOpcoes(scanner, 0, listaUsuarios.size());

            if (opcao == 0) {
                return false;
            }

            System.out.println("Login realizado com sucesso para o " + tipoUsuario + ": " + listaUsuarios.get(opcao - 1) + "\n");
            usuarioLogado = listaUsuarios.get(opcao - 1);
            return true;
        }
    }

    private void exibirUsuariosExistente(List<?> listaUsuarios, String tipoUsuario) {
        System.out.println("Selecione o usuário para logar no sistema: \n");
        if (tipoUsuario.equals(TIPO_ALUNO)) {
            List<Aluno> listaAlunos = (List<Aluno>) listaUsuarios;
            for (int i = 0; i < listaAlunos.size(); i++) {
                System.out.println((i + 1) + " - " + listaAlunos.get(i).getNome() +
                        " - " + listaAlunos.get(i).getIdade() + " anos" +
                        " - Matrícula " + listaAlunos.get(i).getStatusMatricula());
            }
        } else if (tipoUsuario.equals(TIPO_DIRETOR)) {
            List<Diretor> listaDiretores = (List<Diretor>) listaUsuarios;
            for (int i = 0; i < listaDiretores.size(); i++) {
                System.out.println((i + 1) + " - " + listaDiretores.get(i).getNome() +
                        " - " + listaDiretores.get(i).getTempoDeCargo() + " ano de cargo" +
                        " - R$ " + listaDiretores.get(i).getSalario());
            }
        } else {
            List<Professor> listaProfessores = (List<Professor>) listaUsuarios;
            for (int i = 0; i < listaUsuarios.size(); i++) {
                System.out.println((i + 1) + " - " + listaProfessores.get(i).getNome() +
                        " - " + listaProfessores.get(i).getIdade() + " anos" +
                        " - " + listaProfessores.get(i).getTempoTrabalho() + " ano de cargo" +
                        " - R$ " + listaProfessores.get(i).getSalario());
            }
        }

    }

    public Object getUsuarioLogado() {
        return usuarioLogado;
    }


}
