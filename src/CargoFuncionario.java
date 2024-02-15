public enum CargoFuncionario {
    INICIANTE(1, "Primeiro Cargo"),
    EXPERIENTE(2, "Segundo Cargo" ),
    AVANÇADO(3, "Terceiro Cargo");

    private final int indiceCargo;
    private final String numCargo;

    CargoFuncionario(int indiceCargo, String numCargo) {
        this.indiceCargo = indiceCargo;
        this.numCargo = numCargo;
    }

    public int getIndiceCargo() {
        return indiceCargo;
    }

    public String getNumCargo() {
        return numCargo;
    }
}
