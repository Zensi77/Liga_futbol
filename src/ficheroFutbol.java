import java.io.*;

public class ficheroFutbol {

    public ficheroFutbol() {
        File f;
        try {
            f = new File("src/liga.txt");
            f.delete();
            if (!f.exists())
                f.createNewFile();

            FileWriter fw = new FileWriter("src/liga.txt", true);
            fw.write("Nombre;Partidos Jugados;Partidos Ganados;Partidos Perdidos;Partidos Empatados;Puntos" + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

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

    public static String leerDatos() {
        StringBuilder salida = new StringBuilder();
        String linea;
        try {
            FileReader fr = new FileReader("src/liga.txt");
            BufferedReader br = new BufferedReader(fr);
            while ((linea = br.readLine()) != null) {
                salida.append(linea + "\n");
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return salida.toString();
    }

}
