package utils;

import java.io.*;
import java.util.List;

public class ManipularDadosUtils {

    public static <T> void carregarDados(String arquivoDados, List<T> lista) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(arquivoDados));
        lista.addAll((List<T>) inputStream.readObject());
    }

    public static <T> void salvarDados(String arquivoDados, List<T> lista) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(arquivoDados));
        outputStream.writeObject(lista);
        System.out.println("Dados salvos com sucesso em: " + arquivoDados);
    }

    public static <T> void atualizarDados(String arquivoDados, List<T> lista, T objetoAtualizado) throws IOException {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).equals(objetoAtualizado)) {
                lista.set(i, objetoAtualizado);
                salvarDados(arquivoDados, lista);
                return;
            }
        }
        System.err.println("Objeto não encontrado na lista.");
    }
}
