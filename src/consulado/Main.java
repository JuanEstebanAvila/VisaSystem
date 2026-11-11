package consulado;

import consulado.cancelacion.CitasObserver;
import consulado.cancelacion.CorreoObserver;
import consulado.cancelacion.PagosObserver;
import consulado.cancelacion.Solicitud;
import consulado.evaluacion.EvaluadorDeSolicitudes;
import consulado.evaluacion.ResultadoEvaluacion;
import consulado.evaluacion.TipoVisa;
import consulado.legacy.CalculadoraDeTarifas;
import consulado.legacy.EvaluadorMagico;
import consulado.legacy.OficinaDeTodo;
import consulado.legacy.SolicitudAcoplada;
import consulado.tarifas.CalculadoraTarifas;
import consulado.tarifas.TarifaBase;
import consulado.tramite.ArchivoExpedientes;
import consulado.tramite.CajaCobro;
import consulado.tramite.NotificadorCorreo;
import consulado.tramite.TramiteVisa;
import consulado.tramite.ValidadorPasaporte;

public class Main {

    private static final String NOMBRE_VIAJERO = "Ana Perez";
    private static final String PASAPORTE_VIAJERO = "AB123456";
    private static final double BASE_ORIGINAL = 50;
    private static final double BASE_NUEVA = 60;
    private static final int MESES_PRUEBA = 3;
    private static final int CODIGO_ESTUDIANTE = 2;
    private static final int CODIGO_INEXISTENTE = 9;
    private static final int EDAD_PRUEBA = 20;

    public static void main(String[] args) {
        caso1GodObject();
        caso2CopyPaste();
        caso3MagicNumbers();
        caso4Acoplamiento();
        caso5Integracion();
    }

    private static TramiteVisa crearTramite() {
        return new TramiteVisa(new ValidadorPasaporte(), new CajaCobro(),
                new ArchivoExpedientes(), new NotificadorCorreo());
    }

    private static void caso1GodObject() {
        System.out.println("\nCASO 1 - God Object");
        double precio = new CalculadoraTarifas(new TarifaBase(BASE_ORIGINAL))
                .calcular(TipoVisa.TURISTA, MESES_PRUEBA);

        System.out.println("-- HEREDADA (una sola clase hace todo) --");
        boolean legado = new OficinaDeTodo().procesar(NOMBRE_VIAJERO, PASAPORTE_VIAJERO, precio);

        System.out.println("-- RESCATADA (una clase por responsabilidad) --");
        boolean nuevo = crearTramite().tramitar(NOMBRE_VIAJERO, PASAPORTE_VIAJERO, precio);

        System.out.println("Resultado: heredada aprueba=" + legado + " | rescatada aprueba=" + nuevo);
    }

    private static void caso2CopyPaste() {
        System.out.println("\nCASO 2 - Copy-Paste Programming (base " + (int) BASE_ORIGINAL + " -> " + (int) BASE_NUEVA + ")");

        CalculadoraDeTarifas clonada = new CalculadoraDeTarifas();
        System.out.println("-- HEREDADA (calculo clonado 3 veces) --");
        clonada.cambiarBaseTurista(BASE_NUEVA);
        System.out.println("Edicion 1/3: visaTurista");
        clonada.cambiarBaseEstudiante(BASE_NUEVA);
        System.out.println("Edicion 2/3: visaEstudiante");
        System.out.println("  (si aqui se detienen, visaTrabajo queda con el precio viejo: "
                + clonada.visaTrabajo(MESES_PRUEBA) + ")");
        clonada.cambiarBaseTrabajo(BASE_NUEVA);
        System.out.println("Edicion 3/3: visaTrabajo");
        System.out.println("Lugares editados en la clonada: " + clonada.getEdiciones());

        System.out.println("-- RESCATADA (Strategy) --");
        TarifaBase base = new TarifaBase(BASE_ORIGINAL);
        CalculadoraTarifas strategy = new CalculadoraTarifas(base);
        base.setValorPorMes(BASE_NUEVA);
        System.out.println("Lugares editados en Strategy: " + base.getCambios());

        System.out.println("-- Precios finales a " + MESES_PRUEBA + " meses --");
        comparar("TURISTA", clonada.visaTurista(MESES_PRUEBA),
                strategy.calcular(TipoVisa.TURISTA, MESES_PRUEBA));
        comparar("ESTUDIANTE", clonada.visaEstudiante(MESES_PRUEBA),
                strategy.calcular(TipoVisa.ESTUDIANTE, MESES_PRUEBA));
        comparar("TRABAJO", clonada.visaTrabajo(MESES_PRUEBA),
                strategy.calcular(TipoVisa.TRABAJO, MESES_PRUEBA));
    }

    private static void comparar(String tipo, double clonado, double strategy) {
        System.out.println(String.format("%-11s clonada=%7.2f  strategy=%7.2f  coincide=%b",
                tipo, clonado, strategy, clonado == strategy));
    }

    private static void caso3MagicNumbers() {
        System.out.println("\nCASO 3 - Magic Numbers");
        EvaluadorMagico magico = new EvaluadorMagico();
        EvaluadorDeSolicitudes evaluador = new EvaluadorDeSolicitudes();

        System.out.println("-- Tipo 2, edad " + EDAD_PRUEBA + " vs TipoVisa.ESTUDIANTE --");
        System.out.println("Heredada  : " + magico.evaluar(CODIGO_ESTUDIANTE, EDAD_PRUEBA));
        System.out.println("Rescatada : " + evaluador.evaluar(TipoVisa.ESTUDIANTE, EDAD_PRUEBA).estado());

        System.out.println("-- Tipo " + CODIGO_INEXISTENTE + " (no existe) --");
        System.out.println("Heredada  : " + magico.evaluar(CODIGO_INEXISTENTE, EDAD_PRUEBA));
        ResultadoEvaluacion nuevo = evaluador.evaluarPorCodigo(CODIGO_INEXISTENTE, EDAD_PRUEBA);
        System.out.println("Rescatada : " + nuevo.estado() + " - " + nuevo.mensaje());
    }

    private static void caso4Acoplamiento() {
        System.out.println("\nCASO 4 - Acoplamiento fuerte");

        System.out.println("-- HEREDADA (SolicitudAcoplada) --");
        SolicitudAcoplada acoplada = new SolicitudAcoplada();
        acoplada.cancelar();
        System.out.println("Estado: " + acoplada.getEstado() + " | reembolso emitido: "
                + acoplada.reembolsoEmitido());
        if (!acoplada.reembolsoEmitido()) {
            System.out.println("!! FALLA: nadie llamo a pagos; el viajero pierde su reembolso");
        }

        System.out.println("-- RESCATADA (Observer) --");
        Solicitud solicitud = new Solicitud("SOL-001");
        solicitud.agregarObserver(new CorreoObserver());
        solicitud.agregarObserver(new CitasObserver());
        solicitud.agregarObserver(new PagosObserver());
        solicitud.cancelar();
        System.out.println("Estado: " + solicitud.getEstado()
                + " | correo, citas y pagos avisados sin que Solicitud los conozca");
    }

    private static void caso5Integracion() {
        System.out.println("\nCASO 5 - Integracion: viaje completo con la version nueva");
        final int mesesEstudio = 6;

        System.out.println("1) Solicitar");
        Solicitud solicitud = new Solicitud("SOL-002");
        solicitud.agregarObserver(new CorreoObserver());
        solicitud.agregarObserver(new CitasObserver());
        solicitud.agregarObserver(new PagosObserver());
        System.out.println("Solicitud " + solicitud.getId() + " creada: " + solicitud.getEstado());

        System.out.println("2) Evaluar (visa ESTUDIANTE, edad " + EDAD_PRUEBA + ")");
        ResultadoEvaluacion resultado = new EvaluadorDeSolicitudes()
                .evaluar(TipoVisa.ESTUDIANTE, EDAD_PRUEBA);
        System.out.println(resultado.estado() + " - " + resultado.mensaje());

        if (resultado.estado() != ResultadoEvaluacion.Estado.APROBADA) {
            return;
        }

        System.out.println("3) Cobrar (" + mesesEstudio + " meses)");
        double precio = new CalculadoraTarifas(new TarifaBase(BASE_ORIGINAL))
                .calcular(TipoVisa.ESTUDIANTE, mesesEstudio);
        crearTramite().tramitar(NOMBRE_VIAJERO, PASAPORTE_VIAJERO, precio);

        System.out.println("4) Cancelar");
        solicitud.cancelar();
        System.out.println("Estado final: " + solicitud.getEstado());

    }
}
