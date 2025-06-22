import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    // Listas inmutables de platos disponibles
    private static final List<Plato> ENTRADAS = List.of(
            new Plato("Paella", 200),
            new Plato("Gazpacho", 150),
            new Plato("Pasta", 300),
            new Plato("Ensalada César", 180),
            new Plato("Sopa de verduras", 120)
    );

    private static final List<Plato> PRINCIPALES = List.of(
            new Plato("Filete de cerdo", 400),
            new Plato("Pollo asado", 280),
            new Plato("Bistec a lo pobre", 500),
            new Plato("Trucha", 160),
            new Plato("Bacalao", 300),
            new Plato("Salmón a la plancha", 350),
            new Plato("Lasaña", 450)
    );

    private static final List<Plato> POSTRES = List.of(
            new Plato("Flan", 200),
            new Plato("Naranja", 50),
            new Plato("Nueces", 500),
            new Plato("Yogur", 100),
            new Plato("Helado", 250)
    );

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        procesarEntrada(sc);
        sc.close();
    }

    // Procesa la entrada del usuario en un bucle continuo
    private static void procesarEntrada(Scanner sc) {
        while (true) {
            mostrarMenu();
            int opc = obtenerOpcion(sc);
            
            if (opc == 3) {
                System.out.println("Cerrando aplicación...");
                break;
            }
            
            procesarOpcion(opc, sc);
        }
    }

    // Muestra el menú principal de opciones
    private static void mostrarMenu() {
        System.out.println("¿Qué desea hacer?");
        System.out.println("1. Calcular calorías de un menú");
        System.out.println("2. Ver combinaciones bajas en calorías");
        System.out.println("3. Salir");
    }

    // Obtiene y valida la opción seleccionada por el usuario
    private static int obtenerOpcion(Scanner sc) {
        int opc = sc.nextInt();
        sc.nextLine();
        return opc;
    }

    // Procesa la opción seleccionada por el usuario
    private static void procesarOpcion(int opc, Scanner sc) {
        switch (opc) {
            case 1 -> calcularCalorias(sc);
            case 2 -> mostrarCombinacionesBajas(sc);
            default -> System.out.println("Opción no válida, intente de nuevo.");
        }
    }

    // Calcula las calorías de un menú personalizado
    private static void calcularCalorias(Scanner sc) {
        Plato ent = seleccionarPlato(sc, ENTRADAS, "Entradas", "entrada");
        Plato prin = seleccionarPlato(sc, PRINCIPALES, "Platos principales", "plato principal");
        Plato post = seleccionarPlato(sc, POSTRES, "Postres", "postre");

        int total = calcularTotalCalorias(List.of(ent, prin, post));
        System.out.println("El total de calorías es: " + total);
    }

    // Muestra las combinaciones de menús que están por debajo del límite de calorías
    private static void mostrarCombinacionesBajas(Scanner sc) {
        System.out.println("Ingrese el límite máximo de calorías:");
        int maxCal = sc.nextInt();
        sc.nextLine();

        List<List<Plato>> menusBajos = generarMenus()
                .stream()
                .filter(menu -> calcularTotalCalorias(menu) <= maxCal)
                .collect(Collectors.toList());

        mostrarMenusBajos(menusBajos, maxCal);
    }

    // Genera todas las combinaciones posibles de menús
    private static List<List<Plato>> generarMenus() {
        return ENTRADAS.stream()
                .flatMap(ent -> PRINCIPALES.stream()
                        .flatMap(prin -> POSTRES.stream()
                                .map(post -> List.of(ent, prin, post))))
                .collect(Collectors.toList());
    }

    // Calcula el total de calorías de un menú
    private static int calcularTotalCalorias(List<Plato> menu) {
        return menu.stream()
                .mapToInt(Plato::calorias)
                .sum();
    }

    // Muestra los menús que cumplen con el límite de calorías
    private static void mostrarMenusBajos(List<List<Plato>> menus, int maxCal) {
        if (menus.isEmpty()) {
            System.out.println("No hay combinaciones que cumplan con el límite de calorías.");
            return;
        }

        System.out.println("Combinaciones con menos de " + maxCal + " calorías:");
        menus.forEach(menu -> {
            System.out.println("Entrada: " + menu.get(0).nombre() + " (" + menu.get(0).calorias() + ")");
            System.out.println("Plato principal: " + menu.get(1).nombre() + " (" + menu.get(1).calorias() + ")");
            System.out.println("Postre: " + menu.get(2).nombre() + " (" + menu.get(2).calorias() + ")");
            System.out.println("Total: " + calcularTotalCalorias(menu) + " calorías");
            System.out.println();
        });
    }

    // Permite al usuario seleccionar un plato de una categoría
    private static Plato seleccionarPlato(Scanner sc, List<Plato> platos, String cat, String msg) {
        mostrarPlatos(platos, cat);
        return obtenerSeleccion(sc, platos, msg);
    }

    // Muestra la lista de platos disponibles
    private static void mostrarPlatos(List<Plato> platos, String cat) {
        System.out.println(cat + " disponibles:");
        IntStream.range(0, platos.size())
                .forEach(i -> {
                    Plato p = platos.get(i);
                    System.out.println((i + 1) + ". " + p.nombre() + " - " + p.calorias() + " calorías");
                });
    }

    // Obtiene y valida la selección del usuario
    private static Plato obtenerSeleccion(Scanner sc, List<Plato> platos, String msg) {
        while (true) {
            System.out.println("Seleccione el número de la " + msg + ":");
            int sel = sc.nextInt();
            sc.nextLine();

            if (sel >= 1 && sel <= platos.size()) {
                return platos.get(sel - 1);
            }
            System.out.println("Número no válido, intente de nuevo.");
        }
    }
}

// Record que representa un plato con su nombre y calorías
record Plato(String nombre, int calorias) {}