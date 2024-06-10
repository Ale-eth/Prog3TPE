package TPE;

public class Procesador {
    private String idProcesador;
    private String codigoProcesador;
    private boolean refrigerado;
    private Integer anioFuncionamiento;
    private int cantTareasCriticasPermitidas;
    private final static int MAX_TAREAS_CRITICAS = 2;
    public Procesador(String idProcesador, String codigoProcesador, boolean refrigerado, Integer anioFuncionamiento ) {
        this.idProcesador = idProcesador;
        this.codigoProcesador = codigoProcesador;
        this.refrigerado = refrigerado;
        this.anioFuncionamiento = anioFuncionamiento;
        this.cantTareasCriticasPermitidas = MAX_TAREAS_CRITICAS;
    }
    // Getters
    public String getIdProcesador() {
        return idProcesador;
    }

    public void setIdProcesador(String idProcesador) {
        this.idProcesador = idProcesador;
    }

    public String getCodigoProcesador() {
        return codigoProcesador;
    }

    public void setCodigoProcesador(String codigoProcesador) {
        this.codigoProcesador = codigoProcesador;
    }

    public boolean esRefrigerado() {
        return refrigerado;
    }

    public Integer getAnioFuncionamiento() {
        return anioFuncionamiento;
    }

    public int getCantTareasCriticasPermitidas() {
        return cantTareasCriticasPermitidas;
    }
    public void setAnioFuncionamiento(Integer anioFuncionamiento) {
        this.anioFuncionamiento = anioFuncionamiento;
    }
    @Override
    public String toString() {
        return "Procesador{" +
                "idProcesador='" + idProcesador + '\'' +
                ", codigoProcesador='" + codigoProcesador + '\'' +
                ", refrigerado=" + refrigerado +
                ", anioFuncionamiento=" + anioFuncionamiento +
                '}';
    }
}
