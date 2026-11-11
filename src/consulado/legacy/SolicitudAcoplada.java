package consulado.legacy;

/**
 * HEREDADO (antipatron: Acoplamiento fuerte).
 */
public class SolicitudAcoplada {

    private String estado = "ACTIVA";
    private CorreoServicio correo = new CorreoServicio();
    private CitasServicio citas = new CitasServicio();
    private PagosServicio pagos = new PagosServicio();

    public void cancelar() {
        estado = "CANCELADA";
        correo.avisarCancelacion();
        citas.liberarCupo();
    }

    public String getEstado() { return estado; }

    public boolean reembolsoEmitido() { return pagos.isReembolsado(); }
}

class CorreoServicio {
    void avisarCancelacion() {
        System.out.println("[CorreoServicio] Aviso de cancelacion enviado");
    }
}

class CitasServicio {
    void liberarCupo() {
        System.out.println("[CitasServicio] Cupo de cita liberado");
    }
}

class PagosServicio {
    private boolean reembolsado = false;

    void reembolsar() {
        reembolsado = true;
        System.out.println("[PagosServicio] Reembolso emitido");
    }

    boolean isReembolsado() 
    { return reembolsado; }
}
