package consulado.tarifas;

public class TarifaTurista implements TarifaStrategy {

    private static final double FACTOR_TURISTA = 1.0;

    @Override
    public double aplicar(double subtotal) {
        return subtotal * FACTOR_TURISTA;
    }
}
