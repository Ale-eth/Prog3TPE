package TPE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Backtracking{
    private int cantidadEstadosGenerados;
    private List<Tarea> tareas;
    private List<Procesador> procesadores;
    public Backtracking(List<Tarea> tareas,List<Procesador> procesadores) {
        this.tareas=tareas;
        this.procesadores=procesadores;
    }

    public int getCantidadEstadosGenerados() {
        return cantidadEstadosGenerados;
    }

    public void resolverAsignaciones(int tiempoMaximoNoRefrigerado) {
        cantidadEstadosGenerados = 0;
        ArrayList<Asignacion> asignaciones = new ArrayList<>();
        backtrack(0, tiempoMaximoNoRefrigerado,asignaciones);
        imprimir(asignaciones);
    }

    private boolean backtrack(int tareaActual, int tiempoMaximoNoRefrigerado,ArrayList<Asignacion> asignaciones){
        if (tareaActual== tareas.size()){
            return true;
        }
        Tarea tarea = tareas.get(tareaActual); // Me guardo la tarea que quiero procesar
        for (Procesador p:procesadores){
            cantidadEstadosGenerados++;
            if (esAsignable(tarea,p,tiempoMaximoNoRefrigerado,asignaciones)){
                asignaciones.add(new Asignacion(p,tarea));
                if (backtrack(tareaActual+1,tiempoMaximoNoRefrigerado,asignaciones)){
                    return true;
                }
                asignaciones.remove(asignaciones.size()-1);
            }
        }
        return false;
    }

    private boolean esAsignable(Tarea t, Procesador p, int tiempoMaximoNoRefrigerado,List<Asignacion> asignaciones){ // Verificar que el procesador no refrigerado no exceda el tiempo max permitid
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
        private void imprimir(List<Asignacion> asignaciones){
            Map<Procesador,List<Tarea>> asignacionesProcesador = new HashMap<>();
            for (Asignacion a:asignaciones){
                asignacionesProcesador.computeIfAbsent(a.procesador,k->new ArrayList<>()).add(a.tarea);
            }
            int tiempoMaximo = 0;
            for (Map.Entry<Procesador,List<Tarea>> entry:asignacionesProcesador.entrySet()){
                Procesador p = entry.getKey();
                List<Tarea> tareas = entry.getValue();
                int tiempoTotal = tareas.stream().mapToInt(Tarea::getTiempoEjecucion).sum();
                tiempoMaximo=Math.max(tiempoMaximo,tiempoTotal);
                System.out.println("Procesador: "+p.getIdProcesador() + tareas);
            }
            System.out.println("Tiempo maximo: "+tiempoMaximo);
            System.out.println("Estados generados: "+cantidadEstadosGenerados);

        }
    private static class Asignacion {
        Procesador procesador;
        Tarea tarea;

        Asignacion(Procesador procesador, Tarea tarea) {
            this.procesador = procesador;
            this.tarea = tarea;
        }
    }
}