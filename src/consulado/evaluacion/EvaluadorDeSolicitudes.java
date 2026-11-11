package consulado.evaluacion;

public class EvaluadorDeSolicitudes {

    public ResultadoEvaluacion evaluar(TipoVisa tipo, int edad) {
        if (tipo.admiteEdad(edad)) {
            return ResultadoEvaluacion.aprobada(tipo);
        }
        String limiteSuperior = tipo.getEdadMaxima() == Integer.MAX_VALUE ? "sin limite" : String.valueOf(tipo.getEdadMaxima());
        return ResultadoEvaluacion.rechazada("Edad " + edad + " fuera del rango de " + tipo + " (" + tipo.getEdadMinima() + " a " + limiteSuperior + ")");
    }

    /** Puente para entradas con codigo numerico */
    public ResultadoEvaluacion evaluarPorCodigo(int codigo, int edad) {
        try {
            return evaluar(TipoVisa.desdeCodigo(codigo), edad);
        } catch (IllegalArgumentException e) {
            return ResultadoEvaluacion.rechazada(e.getMessage());
        }
    }
}
