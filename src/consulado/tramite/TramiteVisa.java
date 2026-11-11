package consulado.tramite;

/**
 * Unica responsabilidad: coordinar el orden del tramite.
 */
public class TramiteVisa {

    private final ValidadorPasaporte validador;
    private final CajaCobro caja;
    private final ArchivoExpedientes archivo;
    private final NotificadorCorreo correo;

    public TramiteVisa(ValidadorPasaporte validador, CajaCobro caja,
                       ArchivoExpedientes archivo, NotificadorCorreo correo) {
        this.validador = validador;
        this.caja = caja;
        this.archivo = archivo;
        this.correo = correo;
    }

    public boolean tramitar(String nombre, String pasaporte, double precio) {
        if (!validador.validar(pasaporte)) {
            return false;
        }
        caja.cobrar(nombre, precio);
        archivo.guardar(nombre);
        correo.notificarVisaEnCamino(nombre);
        return true;
    }
}
