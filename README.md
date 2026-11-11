# La Oficina de Visas del Caos (Java)

Taller para la Casa – Antipatrones · Modelos de Programación · Universidad Distrital Francisco José de Caldas

## Compilar y ejecutar (Java 17+)

```bash
mkdir out
javac -encoding UTF-8 -d out $(find src -name '*.java')
java -cp out consulado.Main
```

La salida de los 5 casos está en `salida_consola.txt`.

## Estructura de paquetes

| Paquete | Contenido |
|---|---|
| `consulado.legacy` | Código heredado (los 4 antipatrones), solo para comparar |
| `consulado.tramite` | `ValidadorPasaporte`, `CajaCobro`, `ArchivoExpedientes`, `NotificadorCorreo`, `TramiteVisa` |
| `consulado.tarifas` | `TarifaStrategy`, 3 estrategias, `TarifaBase`, `CalculadoraTarifas` |
| `consulado.evaluacion` | `TipoVisa` (enum), `EvaluadorDeSolicitudes`, `ResultadoEvaluacion` |
| `consulado.cancelacion` | `Solicitud` (Subject), `SolicitudObserver`, 3 observers, `EstadoSolicitud` |
| `consulado.reporte` | `TablaAntipatrones` |

## Parte 1 – Cacería de antipatrones

| Fragmento | Antipatrón | Síntoma | Consecuencia |
|---|---|---|---|
| La ventanilla de Ramiro | God Object | Una sola clase valida el pasaporte, cobra, guarda el expediente y envía el correo. | Viola la responsabilidad única: no se puede probar ni cambiar una tarea sin tocar las demás, y si la clase falla (Ramiro se enferma) se detiene todo el consulado. |
| La tarifa clonada | Copy-Paste Programming | El mismo cálculo (`50 * meses + 10`) está copiado en 3 métodos con cambios mínimos (×0.5, ×1.5). | Un cambio o corrección debe repetirse en cada copia; si se olvida una, queda con el precio viejo (pasó al subir la tarifa base). |
| Los números misteriosos | Magic Numbers | Valores sin nombre en el código: `tipo == 3`, `0.5`, edades `17`, `31`, `66`. | Resta legibilidad y nadie sabe qué significan ni si son correctos; un tipo inexistente (p. ej. 9) se rechaza en silencio. |
| La cancelación en cadena | Acoplamiento fuerte | `SolicitudAcoplada` crea con `new` sus 3 servicios y los llama a mano uno por uno. | Cualquier cambio en un servicio o un aviso nuevo obliga a editar la solicitud, y es fácil olvidar uno (se olvidó pagos: el viajero perdió su reembolso). |

## Reflexión: cómo se evita caer de nuevo en los antipatrones

Cada clase tiene una sola razón para cambiar (validar, cobrar, archivar, notificar), y `TramiteVisa` solo coordina el orden, sin hacer ese trabajo. El precio base vive en un único objeto `TarifaBase` y el subtotal se calcula en un solo lugar, de modo que cada tipo de visa solo aporta su `TarifaStrategy`. Los números y textos sueltos se reemplazaron por el enum `TipoVisa` y por constantes con nombre, y los tipos inválidos se rechazan con un mensaje claro. Finalmente, `Solicitud` solo conoce la interfaz `SolicitudObserver`: agregar un aviso nuevo es crear un observer y registrarlo, sin tocar la solicitud ni los demás servicios.

## Notas sobre el código heredado

Para poder demostrar los casos se hicieron cambios mínimos al legado: `OficinaDeTodo.procesar` devuelve `boolean` y antepone el nombre de la clase a sus mensajes; `CalculadoraDeTarifas` guarda la base en tres campos independientes (representan el `50` copiado) con un contador de ediciones; `SolicitudAcoplada` expone `reembolsoEmitido()` para dejar a la vista el reembolso olvidado.
