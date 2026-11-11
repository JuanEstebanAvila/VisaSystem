package consulado.legacy;

/** HEREDADO (antipatron: God Object). Ramiro lo hace todo. */
public class OficinaDeTodo {

    public boolean procesar(String nombre, String pasaporte, double precio) {
        if (pasaporte == null || pasaporte.length() < 6) {
            System.out.println("[OficinaDeTodo/Ramiro] Pasaporte invalido");
            return false;
        }
        System.out.println("[OficinaDeTodo/Ramiro] Cobrando $" + precio + " a " + nombre);
        System.out.println("[OficinaDeTodo/Ramiro] Guardando expediente de " + nombre);
        System.out.println("[OficinaDeTodo/Ramiro] Correo para " + nombre + ": su visa va en camino");
        return true;
    }
}
