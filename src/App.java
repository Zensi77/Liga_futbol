import java.util.Scanner;

public class App {
    static Scanner sc = new Scanner(System.in);
    static ficheroFutbol ff = new ficheroFutbol();
    static String comodin = "";
    static int comodinInt = -1;

    public static void main(String[] args) {
        int opcion = -1;
        while (opcion != 9) {
            System.out.println("-------------------------");
            System.out.println("| Bienvenido a La Liga   |");
            System.out.println("| 1. Introducir Datos    |");
            System.out.println("| 2. Mostrar Datos       |");
            System.out.println("| 3. Ordenar Datos       |");
            System.out.println("| 4. Buscar un equipo    |");
            System.out.println("| 5. Borrar un equipo    |");
            System.out.println("| 6. Modificar un equipo |");
            System.out.println("| 7. Reestablecer liga   |");
            System.out.println("| 8. Simular jornada     |");
            System.out.println("| 9. Salir del programa  |");
            System.out.println("-------------------------");
            do {
                System.out.println("Introduce una opción: ");
                lecturaDatos(1); // 1 para enteros
                opcion = comodinInt;
            } while (opcion < 1 || opcion > 9);

            switch (opcion) {
                // Comprobaciones del numero de equipos y de nombre repetido
                case 1:
                    if (mostrarDatos().split("\n").length < 20) {
                        // Si hay menos de 20 equipos
                        añadirEquipo(false);
                    } else {
                        System.out.println("No puedes añadir mas equipos");
                    } // Si hay menos de 20 equipos (20 lineas
                    break;
                case 2:
                System.out.println("Nombre Equipo \t Partidos Jugados \t Partidos Ganados \t Partidos Perdidos \t Partidos Empatados \t Puntos");
                    System.out.println(mostrarDatos().replace(";", "\t\t\t"));
                    break;
                case 3:
                    ordenar();
                    break;
                case 4:
                    buscar();
                    break;
                case 5:
                    borrar();
                    break;
                case 6:
                    modificar();
                    break;
                case 7:
                    System.out.println("Estas seguro de que quieres reestablecer la liga? (S/N)");
                    do {
                        lecturaDatos(0);
                    } while (!comodin.equalsIgnoreCase("S") && !comodin.equalsIgnoreCase("N"));
                    if (comodin.equalsIgnoreCase("S")) {
                        ff.borrarFichero();
                        System.out.println("Liga reestablecida correctamente");
                    }
                    break;
                case 8:
                    simularjorndada();
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

    public static boolean datosEqVal(int jugados, int ganados, int empatados) {
        if (jugados < ganados + empatados) {
            return false;
        } else {
            return true;
        }

    }

    public static int[] añadirEquipo(boolean repe) { // Si name es true, se comprobara que el nombre no esta repetido
        int partidosJugados;
        int partidosGanados;
        int partidosEmpatados;
        String nombre = "";
        boolean escribe = false; // Comprobar si tengo que escribir al leer nombre

        do {
            System.out.println("Introduce los datos del equipo correctamente");
            // Comprobacion de que el nombre del equipo no esta repetido y que entre a
            // leerlo o no
            String[] equipos = mostrarDatos().split("\n");
            while (!repe) {
                repe = true;
                System.out.print("Introduce el nombre del equipo: ");
                lecturaDatos(0); // 0 para Strings
                nombre = comodin;
                for (String a : equipos) { // Recorremos el array de equipos
                    String nombres[] = a.split(";"); // Separamos los datos de cada equipo
                    System.out.println(nombres[0]);
                    System.out.println(repe);
                    if (nombres[0].equals(nombre)) { // Comprobamos si el nombre esta repetido
                        repe = false;
                        System.out.println(repe);
                    }
                }
                escribe = true;
            }

            System.out.print("Introduce el numero de partidos jugados: ");
            lecturaDatos(1); // 1 para enteros
            partidosJugados = comodinInt;
            System.out.print("Introduce el numero de partidos ganados: ");
            lecturaDatos(1); // 1 para enteros
            partidosGanados = comodinInt;
            System.out.print("Partidos empatados: ");
            lecturaDatos(1); // 1 para enteros
            partidosEmpatados = comodinInt;
        } while (!datosEqVal(partidosJugados, partidosGanados, partidosEmpatados)); // Comprobamos que los datos son
                                                                                    // validos

        // Creamos la clase ficheroFutbol
        if (escribe) { // Si escribe es true, guardo el equipo en el fichero
            ff.guardar(nombre, partidosJugados, partidosGanados, partidosEmpatados);
        }
        return new int[] { partidosJugados, partidosGanados, partidosEmpatados };
    }

    public static String mostrarDatos() {
        String salida = ff.mostrarDatos();
        if (salida != null) {
            return salida;
        } else {
            return "No hay datos";
        }
    }

    public static void buscar() {
        System.out.println("Ingresa el nombre que quieras buscar");
        String busqueda = ff.buscarDatos(sc.nextLine());
        if (!busqueda.equals("")) {
            System.out.println("Este es el resultado de tu busqueda");
            System.out.println(busqueda.replace(";", "\t"));
        } else
            System.out.println("No se ha encontrado el equipo");
    }

    public static void ordenar() {
        System.out.println("Segun que quieres ordenar");
        System.out.println(
                "Partidos Jugados(1), Partidos Ganados(2), Partidos Perdidos(3), Partidos Empatados(4), Puntos(5)");
        comodinInt = -1;
        while (comodinInt < 1 || comodinInt > 5) {
            lecturaDatos(1);
        }
        System.out.println("Has elegido ordenacion por ptos, lo quieres ascendente(1) o descendente(2)");
        do {
            lecturaDatos(1); // 1 para enteros
        } while (comodinInt < 1 || comodinInt > 2);
        int asc = comodinInt;
        if (ff.ordenacion(comodinInt, asc) == 1) {
            System.out.println("Ordenacion realizada correctamente ");
            ff.mostrarDatos();
        } else
            System.out.println("La ordenacion ha fallado");
    }

    public static void borrar() {
        System.out.println("Que equipo deseas borrar");
        lecturaDatos(0);
        String nombre = comodin;
        if ((ff.borrar(nombre)) == 1) {

            System.out.println("Borrado realizado correctamente");
        }
    }

    public static void modificar() {
        System.out.println("Dime el nombre del equipo que quieres modificar");
        lecturaDatos(0);
        String nombre = comodin;
        int[] valores = añadirEquipo(true); // false para que no compruebe el nombre
        if (ff.modify(nombre, valores) == 1) {
            System.out.println("Modifciacion realizada correctamente");
        }
    }

    public static void simularjorndada() {
        String[] equipos = ff.mostrarDatos().split("\n"); // Solo para saber el numero de equipos y tenerlos registrados
        int[] resultados;
        // Valido si el numero de equipos es par o impar
        if (equipos.length % 2 != 0) {
            System.out.println("Necesitas un numero par de equipos para simular la jornada");
        } else {
            resultados = new int[equipos.length];
            // Creo el array temp de equipos para sacar los contrarios
            String[] temp = equipos.clone(); // clono equipos para no modificarlo

            int contrario;
            for (int i = 0; i < (resultados.length / 2) - 1; i++) {
                resultados[i] = (int) (Math.random() * 3); // 0 empate, 1 victoria y 2 derrota
                temp[i] = null; // Pongo el equipo a null para que no vuelva a jugar
                do {
                    contrario = (int) (Math.random() * resultados.length-1); // Para sacar el contrario
                } while (temp[contrario] == null); // Si el contrario es null, saco otro pq ya ha jugado
                // Pongo los 2 a null para que no vuelvan a jugar
                temp[contrario] = null;
                // Asigno el resultado a su contrario
                if (resultados[i] == 1) {
                    resultados[contrario] = 2;
                } else if (resultados[i] == 2) {
                    resultados[contrario] = 1;
                } else {
                    resultados[contrario] = 0;
                }
            }

            // Mostramos los resultados de la jornada simulada
            System.out.println("Estos son los resultados de la jornada");
            String resultado = "";
            for (int i = 0; i < equipos.length; i++) {
                String[] datosEquipos = equipos[i].split(";");
                int[] Modificiacion = {Integer.parseInt(datosEquipos[1]) + 1, 0, 0 }; // Array para guardar los datos de los equipos que han jugado
                    if (resultados[i] == 0) { // Escribo los resultados para concatenarlos y guardo el array para
                                              // paasarlos
                                              // a
                                              // la modificacion
                        resultado = "Empate";
                        Modificiacion[2] = Integer.parseInt(datosEquipos[4]) + 1;
                    } else if (resultados[i] == 1) {
                        resultado = "Victoria";
                        Modificiacion[1] = Integer.parseInt(datosEquipos[2]) + 1;
                    } else {
                        resultado = "Derrota";
                    }
                    System.out.println("Equipo: " + datosEquipos[0] + " Resultado: " + resultado);
                    // AAhora vamos a guardar los datos de los equipos aprovechando el bucle para
                    // cada equipo
                    ff.modify(datosEquipos[0], Modificiacion);
                }

            }

        }

    }

