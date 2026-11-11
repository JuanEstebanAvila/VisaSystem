package consulado.cancelacion;

import java.util.ArrayList;
import java.util.List;

public class Solicitud {

    private final String id;
    private EstadoSolicitud estado = EstadoSolicitud.ACTIVA;
    private final List<SolicitudObserver> observers = new ArrayList<>();

    public Solicitud(String id) {
        this.id = id;
    }

    public void agregarObserver(SolicitudObserver observer) {
        observers.add(observer);
    }

    public void cancelar() {
        if (estado == EstadoSolicitud.CANCELADA) {
            return;
        }
        estado = EstadoSolicitud.CANCELADA;
        for (SolicitudObserver observer : observers) {
            observer.alCancelar(id);
        }
    }

    public String getId() { return id; }

    public EstadoSolicitud getEstado() { return estado; }
}
