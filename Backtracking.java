package TPE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Backtracking extends EstrategiaAlgoritmica{
    private int cantidadEstadosGenerados;
    public Backtracking(List<Tarea> tareas,List<Procesador> procesadores) {
        super(tareas,procesadores);
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
        if (tareaActual== super.getTareas().size()){
            return true;
        }
        Tarea tarea = super.getTareas().get(tareaActual); // Me guardo la tarea que quiero procesar
        for (Procesador p:super.getProcesadores()){
            cantidadEstadosGenerados++;
            if (super.esAsignable(tarea,p,tiempoMaximoNoRefrigerado,asignaciones)){
                asignaciones.add(new Asignacion(p,tarea));
                if (backtrack(tareaActual+1,tiempoMaximoNoRefrigerado,asignaciones)){
                    return true;
                }
                asignaciones.remove(asignaciones.size()-1);
            }
        }
        return false;
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
}