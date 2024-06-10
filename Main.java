package TPE;

import java.util.ArrayList;

public class Main {
        public static void main(String args[]) {
//            Servicios servicios = new Servicios("./src/tpe/datasets/Procesadores.csv", "./src/tpe/datasets/Tareas.csv");
            Servicios servicios = new Servicios("datasets/Procesadores.csv", "datasets/Tareas.csv");
            System.out.println(servicios.servicio1("T2"));
            System.out.println(servicios.servicio2(false));
            System.out.println(servicios.servicio3(25,50));

            Backtracking b = new Backtracking(servicios.getTareasList(),servicios.getProcesadoresList(),500);
            b.asignar();
            Greedy g = new Greedy(servicios.getTareasList(),servicios.getProcesadoresList(),500);
            g.asignar();
        }
    }