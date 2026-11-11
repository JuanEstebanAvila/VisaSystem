package consulado.cancelacion;

public class PagosObserver implements SolicitudObserver {

    @Override
    public void alCancelar(String idSolicitud) {
        System.out.println("[PagosObserver] Reembolso emitido (" + idSolicitud + ")");
    }
}
