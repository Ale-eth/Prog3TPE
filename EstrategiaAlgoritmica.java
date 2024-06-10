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
    /*En backtracking la métrica de costo va a ser la cantidad de estados generados por el algorítmo, mientras que en greedy van a ser las posibles soluciones (N*M)*/
    protected int metricaDeCosto;
    protected int maximoSinRefrigeracion;
    public EstrategiaAlgoritmica(ArrayList<Tarea> tareas, ArrayList<Procesador> procesadores,int maximoSinRefrigeracion) {
        this.tareas = tareas;
        this.procesadores = procesadores;
        this.maximoSinRefrigeracion = maximoSinRefrigeracion;
        this.asignaciones = new ArrayList<>();
    }
    public List<Tarea> getTareas() {
        return tareas;
    }
    public List<Procesador> getProcesadores() {
        return procesadores;
    }
    public boolean esAsignable(Tarea t, Procesador p,ArrayList<Asignacion> asignaciones){ // Verificar que el procesador no refrigerado no exceda el tiempo max permitid
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
        if (t.esCritica()&&tareasCriticas == p.getCantTareasCriticasPermitidas()){
            return false;
        }
        if (!p.esRefrigerado()&&(tiempoTotal+t.getTiempoEjecucion()>maximoSinRefrigeracion)){
            return false;
        }
        return true;
    }
    public void imprimirAsignaciones(ArrayList<Asignacion> asignaciones) {
        Map<Procesador, List<Tarea>> asignacionesPorProcesador = new HashMap<>();
        if (asignaciones.size()!= tareas.size()){
            System.out.println("No fue posible resolver las asignaciones.");
        }else{
            for (Asignacion asignacion : asignaciones) {
                asignacionesPorProcesador
                        .computeIfAbsent(asignacion.procesador, k -> new ArrayList<>())
                        .add(asignacion.tarea);
            }
            int tiempoMaximo = calcularTiempoMaximo(asignaciones);
            for (Map.Entry<Procesador, List<Tarea>> entry : asignacionesPorProcesador.entrySet()) {
                Procesador procesador = entry.getKey();
                List<Tarea> tareas = entry.getValue();
                System.out.println("Procesador " + procesador.getIdProcesador()+":");
                System.out.println(procesador.esRefrigerado() ? "Refrigerado" : "No refrigerado");
                for(Tarea t : tareas){
                    System.out.println("    Tarea ID: "+t.getId()+ " || Nombre: "+t.getNombreTarea()+" || Tiempo de ejecucion: "+t.getTiempoEjecucion() +" || Es critica: "+t.esCritica()+ " || Prioridad: "+t.getPrioridad());
                }
            }
            System.out.println(" --------------------------------");
            System.out.println("Tiempo máximo de ejecución: " + tiempoMaximo);
            System.out.println("Costo: " + metricaDeCosto);
            System.out.println(" --------------------------------");
        }
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
