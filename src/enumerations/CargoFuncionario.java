package enumerations;

public enum CargoFuncionario {
    INICIANTE(1),
    EXPERIENTE(2),
    AVANCADO(3);

    private final int indiceCargo;

    CargoFuncionario(int indiceCargo) {
        this.indiceCargo = indiceCargo;
    }

    public int getIndiceCargo() {
        return indiceCargo;
    }
}
