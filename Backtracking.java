package TPE;

import java.util.ArrayList;

public class Backtracking extends EstrategiaAlgoritmica{
    private ArrayList<Asignacion> mejorAsignacion;
    int mejorTiempoTotal;
    public Backtracking(ArrayList<Tarea> tareas, ArrayList<Procesador> procesadores,int maximoSinRefrigeracion) {
        super(tareas, procesadores,maximoSinRefrigeracion);
        mejorAsignacion = new ArrayList<>();
        mejorTiempoTotal = Integer.MAX_VALUE;
        ArrayList<Asignacion> asignaciones = new ArrayList<>();
    }
    public void asignar() {
        ArrayList<Asignacion> asignaciones = new ArrayList<>();
        backtrack(0,asignaciones);
        super.imprimirAsignaciones(mejorAsignacion);
    }
    private void backtrack(int tareaActual, ArrayList<Asignacion> asignaciones){
        metricaDeCosto++;
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
            if (super.esAsignable(tarea, procesador)) {
                asignaciones.add(new Asignacion(procesador, tarea));
                backtrack(tareaActual + 1, asignaciones);
                asignaciones.remove(asignaciones.size() - 1);
            }
        }
    }

}