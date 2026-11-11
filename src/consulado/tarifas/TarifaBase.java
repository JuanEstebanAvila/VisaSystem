package consulado.tarifas;

/** Unico punto de cambio del precio base por mes. */
public class TarifaBase {

    private double valorPorMes;
    private int cambios = 0;

    public TarifaBase(double valorPorMes) {
        this.valorPorMes = valorPorMes;
    }

    public double getValorPorMes() { return valorPorMes; }

    public void setValorPorMes(double nuevoValor) {
        this.valorPorMes = nuevoValor;
        cambios++;
    }

    public int getCambios() 
    { return cambios; }
}
