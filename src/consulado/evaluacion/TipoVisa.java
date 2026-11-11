package consulado.evaluacion;

public enum TipoVisa {

    TURISTA(1, 18, Integer.MAX_VALUE),
    ESTUDIANTE(2, 16, 30),
    TRABAJO(3, 18, 65);

    private final int codigoLegado;
    private final int edadMinima;
    private final int edadMaxima;

    TipoVisa(int codigoLegado, int edadMinima, int edadMaxima) {
        this.codigoLegado = codigoLegado;
        this.edadMinima = edadMinima;
        this.edadMaxima = edadMaxima;
    }

    public boolean admiteEdad(int edad) {
        return edad >= edadMinima && edad <= edadMaxima;
    }

    public int getEdadMinima() { return edadMinima; }

    public int getEdadMaxima() { return edadMaxima; }

    public static TipoVisa desdeCodigo(int codigo) {
        for (TipoVisa tipo : values()) {
            if (tipo.codigoLegado == codigo) {
                return tipo;
            }
        }
        StringBuilder validos = new StringBuilder();
        for (TipoVisa tipo : values()) {
            if (validos.length() > 0) validos.append(", ");
            validos.append(tipo.codigoLegado).append("=").append(tipo.name());
        }
        throw new IllegalArgumentException(
                "Tipo de visa desconocido: " + codigo + ". Validos: " + validos);
    }
}
