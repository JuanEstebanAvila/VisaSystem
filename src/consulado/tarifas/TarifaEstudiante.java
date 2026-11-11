package consulado.tarifas;

public class TarifaEstudiante implements TarifaStrategy {

    private static final double FACTOR_DESCUENTO_ESTUDIANTE = 0.5;

    @Override
    public double aplicar(double subtotal) {
        return subtotal * FACTOR_DESCUENTO_ESTUDIANTE;
    }
}
