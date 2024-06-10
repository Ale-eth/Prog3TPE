package TPE;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Greedy extends EstrategiaAlgoritmica{
    public Greedy(ArrayList<Tarea> tareas, ArrayList<Procesador> procesadores, int maximoSinRefrigeracion) {
        super(tareas,procesadores,maximoSinRefrigeracion);
    }
    public void asignar(){
        greedy();

        super.imprimirAsignaciones(asignaciones);
    }

    private void greedy(){
        for (Tarea tarea : super.getTareas()) {
            Procesador mejorProcesador = null;
            int mejorTiempoTotal = Integer.MAX_VALUE;
            for (Procesador procesador : super.getProcesadores()) {
                if (super.esAsignable(tarea,procesador,asignaciones)) {
                    metricaDeCosto++;
                    int tiempoTotal = calcularTiempoTotal(procesador, tarea);
                    if (tiempoTotal < mejorTiempoTotal) {
                        mejorTiempoTotal = tiempoTotal;
                        mejorProcesador = procesador;
                    }
                }
            }
            if (mejorProcesador != null) {
                asignaciones.add(new Asignacion(mejorProcesador, tarea));
            }
        }
    }
    private int calcularTiempoTotal(Procesador procesador, Tarea tarea) {
        int tiempoTotal = tarea.getTiempoEjecucion();
        for (Asignacion asignacion : asignaciones) {
            if (asignacion.procesador.equals(procesador)) {
                tiempoTotal += asignacion.tarea.getTiempoEjecucion();
            }
        }
        return tiempoTotal;
    }
}

