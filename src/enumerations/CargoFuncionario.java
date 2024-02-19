package enumerations;

public enum CargoFuncionario {
    INICIANTE(1, "JUNIOR"),
    EXPERIENTE(2, "PLENO"),
    AVANCADO(3, "SENIOR");

    private final int indiceCargo;
    private final String nomeCargo;

    CargoFuncionario(int indiceCargo, String nomeCargo) {
        this.indiceCargo = indiceCargo;
        this.nomeCargo = nomeCargo;
    }

    public int getIndiceCargo() {
        return indiceCargo;
    }

    public String getNomeCargo() {
        return nomeCargo;
    }
}
