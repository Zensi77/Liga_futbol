import java.util.Scanner;

public class App {
    static Scanner sc = new Scanner(System.in);

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
                opcion = sc.nextInt();
            } while (opcion < 1 || opcion > 7);

            switch (opcion) {
                case 1:
                    añadirEquipo();
                    break;
                case 2:

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

    public static void añadirEquipo() {
        System.out.print("Introduce el nombre del equipo: ");
        String nombre = sc.nextLine();
        System.out.print("Introduce el numero de partidos jugados: ");
        int partidosJugados = sc.nextInt();
        System.out.print("Introduce el numero de partidos ganados: ");
        int partidosGanados = sc.nextInt();
        System.out.print("Partidos empatados: ");
        int partidosEmpatados = sc.nextInt();

        // Creamos la clase ficheroFutbol
        ficheroFutbol ff = new ficheroFutbol();
        ff.guardar(nombre, partidosJugados, partidosGanados, partidosEmpatados);
    }
}
