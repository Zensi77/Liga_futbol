import java.io.*;
import java.util.ArrayList;

public class ficheroFutbol {

    // public ficheroFutbol() {
    // File f;
    // try {
    // // f = new File("src/liga.txt");
    // // f.delete();
    // // if (!f.exists())
    // // f.createNewFile();

    // // FileWriter fw = new FileWriter("src/liga.txt", true);
    // // fw.write("Nombre;Partidos Jugados;Partidos Ganados;Partidos Perdidos;Parti
    // os Empatados;Puntos" + "\n");
    // // fw.close();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }

    // }

    public int guardar(String nombre, int partidosJugados, int partidosGanados, int partidosEmpatados) {
        int salida;
        int partidosPerdidos = partidosJugados - partidosEmpatados - partidosGanados;
        int ptos = partidosGanados * 3 + partidosEmpatados * 1;
        try {
            FileWriter fw = new FileWriter("src/liga.txt", true);
            fw.write(nombre + ";" + partidosJugados + ";" + partidosGanados + ";" + partidosPerdidos + ";"
                    + partidosEmpatados + ";" + ptos + "\n");
            salida = 1;
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            salida = -1;
        }
        return salida;
    }

    public String mostrarDatos() {
        StringBuilder salida = new StringBuilder();
        String linea;
        try {
            FileReader fr = new FileReader("src/liga.txt");
            BufferedReader br = new BufferedReader(fr); // Para leer linea a linea
            while ((linea = br.readLine()) != null) {
                salida.append(linea + "\n");
            }
            br.close(); // Cerramos el fichero
        } catch (IOException e) {
            e.printStackTrace();
        }

        return salida.toString();
    }

    public String buscarDatos(String name) {
        String encontrado = "";
        String datos = mostrarDatos();
        String[] lineas = datos.split("\n");
        for (String a : lineas) {
            String[] nombre = a.split(";");
            if (nombre[0].equals(name)) {
                encontrado = a;
            }
        }
        return encontrado;
    }

    public int ordenacion(int parametro, int asc) {
        int salida = 1;
        int i = 0;
        // Cada datos del equipo en 1 posicion del array
        String[] equipos = mostrarDatos().split("\n");
        String[] posicionEq; // Para guardar los datos de cada equipo
        int[] ordenar = new int[equipos.length];
        for (String a : equipos) {
            posicionEq = a.split(";");
            ordenar[i] = Integer.parseInt(posicionEq[parametro]); // Array segun lo que quiero ordenar
            i++;
        }
        // Llamo al metodo de la burbuja para ordenar los equipos a la vez
        bubbleSort(ordenar, equipos);
        for (int a = 0; a < equipos.length; a++) {
            System.out.println(equipos[a]);
            System.out.println("ordenar: " + ordenar[a]);
        }
        if (asc == 2) { // Si lo quiero descendente le doy la vuelta al array
            for (int j = 0; j < equipos.length; j++) {
                String temp = equipos[equipos.length - 1 - j];
                equipos[equipos.length - 1 - j] = equipos[j];
                equipos[j] = temp;
            }
        }
        // Borro el fichero para reescribirlo
        borrarFichero();
        // ahora los guardo en el fichero
        for (String a : equipos) {
            String[] palabras = a.split(";"); // Para dividir cada linea y pasar los parametros correctos
            guardar(palabras[0], Integer.parseInt(palabras[1]),
                    Integer.parseInt(palabras[2]),
                    Integer.parseInt(palabras[4]));
        }
        return salida;
    }

    public int borrar(String name) {
        int salida = -1;
        if (!buscarDatos(name).equals("")) { // Valida que este el nombre en la lista
            String[] contenido = mostrarDatos().split("\n");
            ArrayList<String> borradoLineas = new ArrayList<String>();
            for (var a : contenido) {
                String[] palabras = a.split(";");
                if (!palabras[0].equalsIgnoreCase(name)) {
                    borradoLineas.add(a);
                }
            }
            salida = 1;
            // Se borra el fichero para reescribirlo
            borrarFichero();
            // Vamos a escribir el fichero
            for (String a : borradoLineas) {
                String[] palabras = a.split(";"); // Para dividir cada linea y pasar los parametros correctos
                guardar(palabras[0], Integer.parseInt(palabras[1]), Integer.parseInt(palabras[2]),
                        Integer.parseInt(palabras[4]));
            }

        }
        return salida;
    }

    public int modify(String name, int[] valores) {
        int salida = -1;
        String busqueda = buscarDatos(name);
        if (!busqueda.equals("")) {
            String[] equipo = busqueda.split(";");
            borrar(name);
            salida = -1; // pq se va a poner a 1 en el borrar
            // Ahora vamos a modificar los valores que queremos del equipo
            for (int i = 0; i < valores.length; i++) {
                // Si el valor es 0 no se modificara
                if (valores[i] != 0) {
                    equipo[i + 1] = String.valueOf(valores[i]); // +1 pq el nombre esta en la posicion 0
                }
            }
            // Ahora vamos a guardar el equipo modificado
            guardar(equipo[0], Integer.parseInt(equipo[1]), Integer.parseInt(equipo[2]), Integer.parseInt(equipo[3]));
        } else {
            System.out.println("No se ha encontrado el equipo");
        }
        return salida;
    }

    public void bubbleSort(int[] arr, String[] equipos) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Cambio array de indices
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    // Cambio array de equipos a la vez
                    String tempEquipo = equipos[j];
                    equipos[j] = equipos[j + 1];
                    equipos[j + 1] = tempEquipo;
                }
            }
        }
    }

    public void borrarFichero() {
        File f;
        try {
            f = new File("src/liga.txt");
            f.delete();
            f.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}