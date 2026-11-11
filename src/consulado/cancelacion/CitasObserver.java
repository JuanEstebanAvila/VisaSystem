package consulado.cancelacion;

public class CitasObserver implements SolicitudObserver {

    @Override
    public void alCancelar(String idSolicitud) {
        System.out.println("[CitasObserver] Cupo de cita liberado (" + idSolicitud + ")");
    }
}
