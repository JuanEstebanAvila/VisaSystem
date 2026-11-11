package consulado.tarifas;

public class TarifaTrabajo implements TarifaStrategy {

    private static final double FACTOR_RECARGO_TRABAJO = 1.5;

    @Override
    public double aplicar(double subtotal) {
        return subtotal * FACTOR_RECARGO_TRABAJO;
    }
}
