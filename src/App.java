import java.util.Scanner;

public class App {
    static Scanner sc = new Scanner(System.in);
    static ficheroFutbol ff = new ficheroFutbol();
    static String comodin = "";
    static int comodinInt = -1;

    public static void main(String[] args) {
        int opcion = -1;
        while (opcion != 7) {
            System.out.println("-------------------------");
            System.out.println("| Bienvenido a La Liga   |");
            System.out.println("| 1. Introducir Datos    |");
            System.out.println("| 2. Mostrar Datos       |");
            System.out.println("| 3. Buscar Datos        |");
            System.out.println("| 4. Buscar un equipo    |");
            System.out.println("| 5. Borrar un equipo    |");
            System.out.println("| 6. Modificar un equipo |");
            System.out.println("| 7. Salir del programa  |");
            System.out.println("-------------------------");
            do {
                System.out.println("Introduce una opción: ");
                lecturaDatos(1); // 1 para enteros
                opcion = comodinInt;
            } while (opcion < 1 || opcion > 7);

            switch (opcion) {
                case 1:
                    añadirEquipo();
                    break;
                case 2:
                    System.out.println("Estos son los datos de la liga\n" + mostrarDatos());
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
            }
        }

    }

    public static void lecturaDatos(int num) {
        boolean salida = false;
        while (!salida) {
            if (num == 0) {
                try {
                    comodin = sc.nextLine();
                    salida = true;
                    if (comodin.equals("")) {
                        System.out.println("No puedes dejar el campo vacio");
                        salida = false;
                    }
                } catch (Exception e) {
                    System.out.println("Has escrito algo mal, vuelve a intentarlo");
                }
                // Validamos las entradas de Strings
            } else {
                try {
                    comodinInt = sc.nextInt();
                    sc.nextLine(); // Limpiamos el buffer
                    salida = true;
                } catch (Exception e) {
                    System.out.println("Has escrito algo mal, vuelve a intentarlo");
                    sc.nextLine(); // Limpiamos el buffer
                }
            }
        }
    }

    public static void añadirEquipo() {
        System.out.print("Introduce el nombre del equipo: ");
        lecturaDatos(0); // 0 para Strings
        String nombre = comodin;
        System.out.print("Introduce el numero de partidos jugados: ");
        lecturaDatos(1); // 1 para enteros
        int partidosJugados = comodinInt;
        System.out.print("Introduce el numero de partidos ganados: ");
        lecturaDatos(1);
        int partidosGanados = sc.nextInt();
        System.out.print("Partidos empatados: ");
        lecturaDatos(1);
        int partidosEmpatados = sc.nextInt();

        // Creamos la clase ficheroFutbol
        ff.guardar(nombre, partidosJugados, partidosGanados, partidosEmpatados);
    }

    public static String mostrarDatos() {
        String salida = ff.leerDatos();
        if (salida != null) {
            return salida;
        } else {
            return "No hay datos";
        }
    }
}
