package consulado.tarifas;

import consulado.evaluacion.TipoVisa;

import java.util.EnumMap;
import java.util.Map;

/**
 * El subtotal se calcula en UN solo lugar
 */
public class CalculadoraTarifas {

    private static final double CARGO_FIJO = 10;

    private final TarifaBase base;
    private final Map<TipoVisa, TarifaStrategy> estrategias = new EnumMap<>(TipoVisa.class);

    public CalculadoraTarifas(TarifaBase base) {
        this.base = base;
        registrar(TipoVisa.TURISTA, new TarifaTurista());
        registrar(TipoVisa.ESTUDIANTE, new TarifaEstudiante());
        registrar(TipoVisa.TRABAJO, new TarifaTrabajo());
    }

    public final void registrar(TipoVisa tipo, TarifaStrategy estrategia) {
        estrategias.put(tipo, estrategia);
    }

    public double calcular(TipoVisa tipo, int meses) {
        TarifaStrategy estrategia = estrategias.get(tipo);
        if (estrategia == null) {
            throw new IllegalStateException("No hay tarifa registrada para " + tipo);
        }
        double subtotal = base.getValorPorMes() * meses + CARGO_FIJO;
        return estrategia.aplicar(subtotal);
    }
}
