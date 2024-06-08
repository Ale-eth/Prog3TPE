package TPE;

public class Asignacion {
        Procesador procesador;
        Tarea tarea;

        Asignacion(Procesador procesador, Tarea tarea) {
            this.procesador = procesador;
            this.tarea = tarea;
        }

    public Procesador getProcesador() {
        return procesador;
    }

    public void setProcesador(Procesador procesador) {
        this.procesador = procesador;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }
}
