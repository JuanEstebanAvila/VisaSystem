package consulado.legacy;

/**
 * HEREDADO (antipatron: Copy-Paste Programming).
 */
public class CalculadoraDeTarifas {

    private double baseTurista = 50;
    private double baseEstudiante = 50;
    private double baseTrabajo = 50;
    private int ediciones = 0;

    public double visaTurista(int meses) {
        double total = baseTurista * meses;
        total = total + 10;
        return total;
    }

    public double visaEstudiante(int meses) {
        double total = baseEstudiante * meses;
        total = total + 10;
        return total * 0.5;
    }

    public double visaTrabajo(int meses) {
        double total = baseTrabajo * meses;
        total = total + 10;
        return total * 1.5;
    }

    public void cambiarBaseTurista(double nueva) 
    { baseTurista = nueva; ediciones++; }

    public void cambiarBaseEstudiante(double nueva) 
    { baseEstudiante = nueva; ediciones++; }

    public void cambiarBaseTrabajo(double nueva) 
    { baseTrabajo = nueva; ediciones++; }

    public int getEdiciones() 
    { return ediciones; }
}
