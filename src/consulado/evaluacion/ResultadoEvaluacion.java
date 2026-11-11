package consulado.evaluacion;

public record ResultadoEvaluacion(Estado estado, String mensaje) {

    public enum Estado { APROBADA, RECHAZADA }

    public static ResultadoEvaluacion aprobada(TipoVisa tipo) {
        return new ResultadoEvaluacion(Estado.APROBADA, "Solicitud de visa " + tipo + " aprobada");
    }

    public static ResultadoEvaluacion rechazada(String motivo) {
        return new ResultadoEvaluacion(Estado.RECHAZADA, motivo);
    }
}
