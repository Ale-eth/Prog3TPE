package tpe;

import java.util.*;

public class AsignacionGreedy {
    private List<Tarea> tareas;
    private List<Procesador> procesadores;

    public AsignacionGreedy(List<Tarea> tareas, List<Procesador> procesadores) {
        this.tareas = tareas;
        this.procesadores = procesadores;
    }

    /*
     * Estrategia: Algoritmo Greedy para asignación de tareas minimizando el tiempo de ejecución máximo.
     */
    public void greedyAsignacion(int tiempoMaxSinRefrigeracion) {
        List<Asignacion> asignaciones = new ArrayList<>();
        Collections.sort(tareas, Comparator.comparingInt(Tarea::getTiempoEjecucion).reversed());

        for (Tarea tarea : tareas) {
            Procesador mejorProcesador = null;
            int mejorTiempoTotal = Integer.MAX_VALUE;

            for (Procesador procesador : procesadores) {
                if (puedeAsignar(procesador, tarea, asignaciones, tiempoMaxSinRefrigeracion)) {
                    int tiempoTotal = calcularTiempoTotal(procesador, tarea, asignaciones);
                    if (tiempoTotal < mejorTiempoTotal) {
                        mejorTiempoTotal = tiempoTotal;
                        mejorProcesador = procesador;
                    }
                }
            }

            if (mejorProcesador != null) {
                asignaciones.add(new Asignacion(mejorProcesador, tarea));
            } else {
                System.out.println("No se pudo asignar la tarea " + tarea.getId());
            }
        }

        imprimirAsignaciones(asignaciones);
    }

    private boolean puedeAsignar(Procesador procesador, Tarea tarea, List<Asignacion> asignaciones, int tiempoMaxSinRefrigeracion) {
        int tareasCriticas = 0;
        int tiempoTotal = 0;

        for (Asignacion asignacion : asignaciones) {
            if (asignacion.procesador.equals(procesador)) {
                if (asignacion.tarea.isEsCritica()) {
                    tareasCriticas++;
                }
                tiempoTotal += asignacion.tarea.getTiempoEjecucion();
            }
        }

        if (tarea.isEsCritica() && tareasCriticas >= 2) {
            return false;
        }
        if (!procesador.isEstaRefrigerado() && (tiempoTotal + tarea.getTiempoEjecucion() > tiempoMaxSinRefrigeracion)) {
            return false;
        }
        return true;
    }

    private int calcularTiempoTotal(Procesador procesador, Tarea tarea, List<Asignacion> asignaciones) {
        int tiempoTotal = tarea.getTiempoEjecucion();
        for (Asignacion asignacion : asignaciones) {
            if (asignacion.procesador.equals(procesador)) {
                tiempoTotal += asignacion.tarea.getTiempoEjecucion();
            }
        }
        return tiempoTotal;
    }

    private void imprimirAsignaciones(List<Asignacion> asignaciones) {
        Map<Procesador, List<Tarea>> asignacionesPorProcesador = new HashMap<>();
        for (Asignacion asignacion : asignaciones) {
            asignacionesPorProcesador
                .computeIfAbsent(asignacion.procesador, k -> new ArrayList<>())
                .add(asignacion.tarea);
        }

        int tiempoMaximo = 0;
        for (Map.Entry<Procesador, List<Tarea>> entry : asignacionesPorProcesador.entrySet()) {
            Procesador procesador = entry.getKey();
            List<Tarea> tareas = entry.getValue();
            int tiempoTotal = tareas.stream().mapToInt(Tarea::getTiempoEjecucion).sum();
            tiempoMaximo = Math.max(tiempoMaximo, tiempoTotal);

            System.out.println("Procesador " + procesador.getCodigo() + ": " + tareas);
        }
        System.out.println("Tiempo máximo de ejecución: " + tiempoMaximo);
    }

    private static class Asignacion {
        Procesador procesador;
        Tarea tarea;

        Asignacion(Procesador procesador, Tarea tarea) {
            this.procesador = procesador;
            this.tarea = tarea;
        }
    }

    public static void main(String[] args) {
        Servicios servicios = new Servicios("procesadores.csv", "tareas.csv");
        List<Tarea> tareas = servicios.getTareas();
        List<Procesador> procesadores = servicios.getProcesadores();

        AsignacionGreedy asignacionGreedy = new AsignacionGreedy(tareas, procesadores);
        asignacionGreedy.greedyAsignacion(100); // Ejemplo de tiempo máximo sin refrigeración
    }
}

