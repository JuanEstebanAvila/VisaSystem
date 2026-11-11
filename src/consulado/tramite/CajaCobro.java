package consulado.tramite;

/** Unica responsabilidad: cobrar. */
public class CajaCobro {

    public void cobrar(String nombre, double precio) {
        System.out.println("[CajaCobro] Cobrando $" + precio + " a " + nombre);
    }
}
