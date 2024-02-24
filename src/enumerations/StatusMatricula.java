package enumerations;

public enum StatusMatricula {
    ATIVA(1),
    TRANCADA(2),
    FORMADO(3);

    public final int indice;

    StatusMatricula(int indice) {
        this.indice = indice;
    }

    public int getIndice() {
        return indice;
    }
}
