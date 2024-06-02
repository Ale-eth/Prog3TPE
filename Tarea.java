package TPE;

public class Tarea {
    private String id;
    private String nombretarea;
    private Integer tiempoejecucion;
    private boolean critica;
    private Integer prioridad;

    public Tarea(String id, String nombretarea, Integer tiempoejecucion, boolean critica, Integer prioridad){
        this.id = id;
        this.nombretarea = nombretarea;
        this.tiempoejecucion = tiempoejecucion;
        this.critica = critica;
        this.prioridad = prioridad;
    }

    // Getters y Setters
    public String getId(){
        return this.id;
    }
    public String getNombretarea(){
        return this.nombretarea;
    }
    public Integer getTiempoejecucion(){
        return this.tiempoejecucion;
    }
    public boolean getCritica(){
        return this.critica;
    }
    public Integer getPrioridad(){
        return this.prioridad;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "id='" + id + '\'' +
                ", nombretarea='" + nombretarea + '\'' +
                ", tiempoejecucion=" + tiempoejecucion +
                ", critica=" + critica +
                ", prioridad=" + prioridad +
                '}';
    }
}
