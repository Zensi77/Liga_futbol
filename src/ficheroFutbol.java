import java.io.*;

public class ficheroFutbol {

    public ficheroFutbol() {
        File f;
        try {
            f = new File("src/liga.txt");
            if (!f.exists()) {
                f.createNewFile();
            }
            FileWriter fw = new FileWriter("src/liga.txt");
            fw.write("Nombre;Partidos Jugados;Partidos Ganados;Partidos Perdidos;Partidos Empatados;Puntos");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int guardar(String nombre, int partidosJugados, int partidosGanados, int partidosEmpatados) {
        int salida;
        int partidosPerdidos = partidosEmpatados + partidosGanados - partidosJugados;
        int ptos = partidosGanados * 3 + partidosEmpatados * 1;
        try {
            FileWriter fw = new FileWriter("src/liga.txt");
            fw.write(nombre + ";" + partidosJugados + ";" + partidosGanados + ";" + partidosPerdidos + ";"
                    + partidosEmpatados + ";" + ptos);
            salida = 1;
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            salida = -1;
        }
        return salida;
    }

    public int leerDatos() {
        int salida;
        try {

        } catch (IOException e) {
            e.printStackTrace();
            salida = -1;
        }
    }

}
