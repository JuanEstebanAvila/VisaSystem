package consulado.tramite;

/** Unica responsabilidad: validar el pasaporte. */
public class ValidadorPasaporte {

    private static final int LONGITUD_MINIMA = 6;

    public boolean validar(String pasaporte) {
        boolean valido = pasaporte != null && pasaporte.length() >= LONGITUD_MINIMA;
        System.out.println("[ValidadorPasaporte] Pasaporte " + (valido ? "valido" : "invalido"));
        return valido;
    }
}
