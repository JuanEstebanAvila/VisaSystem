package consulado.cancelacion;

public class CorreoObserver implements SolicitudObserver {

    @Override
    public void alCancelar(String idSolicitud) {
        System.out.println("[CorreoObserver] Aviso de cancelacion enviado (" + idSolicitud + ")");
    }
}
