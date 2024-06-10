package TPE;

/*
Funcionamiento de la clase Backtracking:
- Primero hereda la clase EstrategiaAlgoritmica con:
    - Atributos:
        - Arreglo de Tareas
        - Arreglo de Procesadores
        - Arreglo de Asignaciones
        - Metrica de costo
        - Maximo sin refrigeracio
    - Metodos:
        -

 */
import java.util.ArrayList;

public class Backtracking extends EstrategiaAlgoritmica{
    private ArrayList<Asignacion> mejorAsignacion;
    int mejorTiempoTotal;
    public Backtracking(ArrayList<Tarea> tareas, ArrayList<Procesador> procesadores,int maximoSinRefrigeracion) {
        super(tareas, procesadores,maximoSinRefrigeracion);
        mejorAsignacion = new ArrayList<>();
        mejorTiempoTotal = Integer.MAX_VALUE;
    }
    public void asignar() {
        ArrayList<Asignacion> asignaciones = new ArrayList<>();
        backtrack(0,asignaciones);
        super.imprimirAsignaciones(mejorAsignacion);
    }
    /*El algoritmo de backtracking va a tener una complejidad computacional de O(T^P . P). Este peor caso se daria*/
    private void backtrack(int tareaActual, ArrayList<Asignacion> asignaciones){
        if (tareaActual == super.getTareas().size()) {
            int tiempoTotalActual = calcularTiempoMaximo(asignaciones);
            if (tiempoTotalActual < mejorTiempoTotal) {
                mejorTiempoTotal = tiempoTotalActual;
                mejorAsignacion = new ArrayList<>(asignaciones);
            }
            return;
        }
        Tarea tarea = super.getTareas().get(tareaActual);
        for (Procesador procesador : super.getProcesadores()) {
            if (super.esAsignable(tarea, procesador,asignaciones)) {
                metricaDeCosto++;
                asignaciones.add(new Asignacion(procesador, tarea));
                backtrack(tareaActual + 1, asignaciones);
                asignaciones.remove(asignaciones.size() - 1);
            }
        }
    }

}