public enum StatusMatricula {
    ATIVO(1),
    TRANCADO(2),
    FORMADO(3);

    public final int indice;

    StatusMatricula(int indice) {
        this.indice = indice;
    }

    public int getIndice() {
        return indice;
    }
}
