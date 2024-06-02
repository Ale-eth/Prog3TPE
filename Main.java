package TPE;

import TPE.utils.CSVReader;

public class Main {
        public static void main(String args[]) {
            //Servicios servicios = new Servicios("./src/tpe/datasets/Procesadores.csv", "./src/tpe/datasets/Tareas.csv");
            Servicios servicios = new Servicios("datasets/Procesadores.csv", "datasets/Tareas.csv");
            System.out.println(servicios.servicio1("T2"));
            System.out.println(servicios.servicio2(false));
            System.out.println(servicios.servicio3(0,10000));

        }
    }

