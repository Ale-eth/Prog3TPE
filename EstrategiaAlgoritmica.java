package TPE;

import com.sun.jdi.ArrayReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class EstrategiaAlgoritmica {
    private ArrayList<Tarea> tareas;
    private ArrayList<Procesador> procesadores;
    protected ArrayList<Asignacion> asignaciones;
    protected int metricaDeCosto;
    protected int maximoSinRefrigeracion;

    public EstrategiaAlgoritmica(ArrayList<Tarea> tareas, ArrayList<Procesador> procesadores,int maximoSinRefrigeracion) {
        this.tareas = tareas;
        this.procesadores = procesadores;
        this.maximoSinRefrigeracion = maximoSinRefrigeracion;
        this.asignaciones = new ArrayList<Asignacion>();
    }
    public List<Tarea> getTareas() {
        return tareas;
    }
    public List<Procesador> getProcesadores() {
        return procesadores;
    }
    boolean esAsignable(Tarea t, Procesador p){ // Verificar que el procesador no refrigerado no exceda el tiempo max permitid
        int tareasCriticas = 0;
        int tiempoTotal = 0;
        for (Asignacion a:asignaciones){
            if (a.procesador.equals(p)){
                if (a.tarea.esCritica()){
                    tareasCriticas++;
                }
                tiempoTotal+=a.tarea.getTiempoEjecucion();
            }
        }
        if (t.esCritica()&&tareasCriticas>=2){
            return false;
        }
        if (!p.esRefrigerado()&&(tiempoTotal+t.getTiempoEjecucion()>maximoSinRefrigeracion)){
            return false;
        }
        return true;
    }
    public void imprimirAsignaciones(ArrayList<Asignacion> asignaciones) {
        Map<Procesador, List<Tarea>> asignacionesPorProcesador = new HashMap<>();
        for (Asignacion asignacion : asignaciones) {
            asignacionesPorProcesador
                    .computeIfAbsent(asignacion.procesador, k -> new ArrayList<>())
                    .add(asignacion.tarea);
        }
        int tiempoMaximo = calcularTiempoMaximo(asignaciones);
        for (Map.Entry<Procesador, List<Tarea>> entry : asignacionesPorProcesador.entrySet()) {
            Procesador procesador = entry.getKey();
            List<Tarea> tareas = entry.getValue();
            System.out.println("Procesador " + procesador.getIdProcesador() + ": " + tareas);
        }
        if (asignaciones.isEmpty()){
            System.out.println("No hay posibles soluciones con los datos proporcionados.");
        }
        System.out.println("Tiempo máximo de ejecución: " + tiempoMaximo);
        System.out.println("Costo: " + metricaDeCosto);
    }
    public int calcularTiempoMaximo(ArrayList<Asignacion> asignaciones) {
        Map<Procesador, Integer> tiempoPorProcesador = new HashMap<>();
        for (Asignacion asignacion : asignaciones) {
            Procesador procesador = asignacion.getProcesador();
            Tarea tarea = asignacion.getTarea();
            tiempoPorProcesador.put(procesador, tiempoPorProcesador.getOrDefault(procesador, 0) + tarea.getTiempoEjecucion());
        }
        return tiempoPorProcesador.values().stream().max(Integer::compareTo).orElse(0);
    }
}
