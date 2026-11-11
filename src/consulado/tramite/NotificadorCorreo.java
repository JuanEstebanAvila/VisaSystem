package consulado.tramite;

/** Unica responsabilidad: enviar el correo al viajero. */
public class NotificadorCorreo {

    public void notificarVisaEnCamino(String nombre) {
        System.out.println("[NotificadorCorreo] Correo para " + nombre + ": su visa va en camino");
    }
}
