package TPE;

import java.util.ArrayList;
import java.util.List;

public abstract class EstrategiaAlgoritmica {
    private List<Tarea> tareas;
    private List<Procesador> procesadores;

    public EstrategiaAlgoritmica(List<Tarea> tareas, List<Procesador> procesadores) {
        this.tareas = tareas;
        this.procesadores = procesadores;
    }
    public List<Tarea> getTareas() {
        return tareas;
    }
    public List<Procesador> getProcesadores() {
        return procesadores;
    }
    boolean esAsignable(Tarea t, Procesador p, int tiempoMaximoNoRefrigerado, ArrayList<Asignacion> asignaciones){ // Verificar que el procesador no refrigerado no exceda el tiempo max permitid
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
        if (!p.esRefrigerado()&&(tiempoTotal+t.getTiempoEjecucion()>tiempoMaximoNoRefrigerado)){
            return false;
        }
        return true;
    }
}
