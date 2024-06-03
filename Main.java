package TPE;

import TPE.soluciones.Backtracking;
import TPE.soluciones.Solucion;

import java.util.ArrayList;

public class Main {
        public static void main(String args[]) {
            Servicios servicios = new Servicios("./src/tpe/datasets/Procesadores.csv", "./src/tpe/datasets/Tareas.csv");
            //Servicios servicios = new Servicios("datasets/Procesadores.csv", "datasets/Tareas.csv");
            System.out.println(servicios.servicio1("T2"));
            System.out.println(servicios.servicio2(false));
            System.out.println(servicios.servicio3(0,10000));

            //Integer tiempoMaximo, HashMap<String, Procesador> procesadores
            Backtracking b = new Backtracking(80,servicios.getProcesadoresHashMap());

            Solucion s= b.resolver(1000, servicios.getProcesadoresHashMap(), new ArrayList<>(servicios.getTareasHashMap().values()));
            System.out.println(s.toString());
        }
    }

