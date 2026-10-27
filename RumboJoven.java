import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * Rumbo Joven: registro de clientes y calculo del valor de sus viajes.
 * Asignatura: Programacion 1.
 */
public class RumboJoven {
    private static final long PRECIO_MB = 285325;
    private static final long PRECIO_MP = 325650;

    private static final Scanner teclado = new Scanner(System.in);
    private static final ArrayList<Cliente> clientes = new ArrayList<>();

    public static void main(String[] args) {
        boolean continuar = true;

        System.out.println("====================================");
        System.out.println("       BIENVENIDO A RUMBO JOVEN");
        System.out.println("          Tu proximo destino");
        System.out.println("====================================");

        while (continuar) {
            mostrarMenu();
            int opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    registrarCliente();
                    break;
                case 2:
                    seleccionarMaleta();
                    break;
                case 3:
                    registrarAcompanantes();
                    break;
                case 4:
                    calcularValorViaje();
                    break;
                case 5:
                    visualizarInformacion();
                    break;
                case 6:
                    guardarRespaldoTXT();
                    break;
                case 7:
                    continuar = false;
                    System.out.println("Gracias por usar Rumbo Joven. Buen viaje!");
                    break;
                default:
                    System.out.println("Opcion no valida. Seleccione del 1 al 7.");
            }
        }
        teclado.close();
    }

    // Presenta el menu principal.
    private static void mostrarMenu() {
        System.out.println("\n------------- MENU -------------");
        System.out.println("1. Registrar cliente");
        System.out.println("2. Tipo de maleta");
        System.out.println("3. Registrar acompanantes");
        System.out.println("4. Calcular valor del viaje");
        System.out.println("5. Visualizar toda la informacion");
        System.out.println("6. Guardar respaldo TXT");
        System.out.println("7. Salir");
        System.out.println("--------------------------------");
    }

    // Registra un cliente. La palabra vacaciones cancela el registro actual.
    private static void registrarCliente() {
        System.out.println("\n--- REGISTRO DE CLIENTE ---");
        System.out.print("Nombre del cliente (o 'vacaciones' para cancelar): ");
        String nombre = teclado.nextLine().trim();

        if (nombre.equalsIgnoreCase("vacaciones")) {
            System.out.println("Registro de cliente terminado por solicitud.");
            return;
        }
        while (nombre.isEmpty()) {
            System.out.print("El nombre no puede estar vacio. Ingrese el nombre: ");
            nombre = teclado.nextLine().trim();
            if (nombre.equalsIgnoreCase("vacaciones")) {
                return;
            }
        }

        System.out.print("Cedula: ");
        String cedula = teclado.nextLine().trim();
        while (cedula.isEmpty()) {
            System.out.print("La cedula no puede estar vacia. Ingrese la cedula: ");
            cedula = teclado.nextLine().trim();
        }

        System.out.print("Fecha del viaje (ejemplo 15/12/2026): ");
        String fecha = teclado.nextLine().trim();
        while (fecha.isEmpty()) {
            System.out.print("La fecha no puede estar vacia. Ingrese la fecha: ");
            fecha = teclado.nextLine().trim();
        }

        System.out.print("Destino: ");
        String destino = teclado.nextLine().trim();
        while (destino.isEmpty()) {
            System.out.print("El destino no puede estar vacio. Ingrese el destino: ");
            destino = teclado.nextLine().trim();
        }

        clientes.add(new Cliente(nombre, cedula, fecha, destino));
        System.out.println("Cliente registrado correctamente.");
    }

    // Guarda el tipo de maleta para el cliente seleccionado.
    private static void seleccionarMaleta() {
        Cliente cliente = seleccionarCliente();
        if (cliente == null) return;

        System.out.println("MB - Maleta basica: " + dinero(PRECIO_MB));
        System.out.println("MP - Maleta premium: " + dinero(PRECIO_MP));
        System.out.print("Escriba MB o MP: ");
        String tipo = teclado.nextLine().trim().toUpperCase();

        while (!tipo.equals("MB") && !tipo.equals("MP")) {
            System.out.print("Opcion invalida. Escriba MB o MP: ");
            tipo = teclado.nextLine().trim().toUpperCase();
        }
        cliente.tipoMaleta = tipo;
        System.out.println("Tipo de maleta guardado.");
    }

    // Registra el numero de acompanantes.
    private static void registrarAcompanantes() {
        Cliente cliente = seleccionarCliente();
        if (cliente == null) return;

        int cantidad = leerEntero("Cantidad de acompanantes (0 o mas): ");
        while (cantidad < 0) {
            System.out.println("La cantidad no puede ser negativa.");
            cantidad = leerEntero("Cantidad de acompanantes (0 o mas): ");
        }
        cliente.acompanantes = cantidad;
        System.out.println("Acompanantes guardados.");
    }

    // Calcula el valor segun la formula del enunciado: precio de maleta por acompanantes.
    private static void calcularValorViaje() {
    Cliente cliente = seleccionarCliente();
        if (cliente == null) return;

        if (cliente.tipoMaleta.isEmpty()) {
            System.out.println("Primero seleccione una maleta en la opcion 2.");
            return;
        }
        long precio = cliente.tipoMaleta.equals("MB") ? PRECIO_MB : PRECIO_MP;
        cliente.valorViaje = precio * cliente.acompanantes;
        cliente.calculado = true;
        System.out.println("Valor calculado: " + dinero(cliente.valorViaje));
    }

    // Muestra todos los clientes registrados.
    private static void visualizarInformacion() {
        if (clientes.isEmpty()) {
            System.out.println("Todavia no hay clientes registrados.");
            return;
        }
        System.out.println("\n========== INFORMACION DE CLIENTES ==========");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println("\nCliente #" + (i + 1));
            System.out.println(clientes.get(i));
        }
    }

    // Escribe el respaldo de los clientes en un archivo TXT.
    private static void guardarRespaldoTXT() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes para guardar.");
            return;
        }
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("RespaldoRumboJoven.txt"))) {
            escritor.write("RUMBO JOVEN - RESPALDO DE CLIENTES");
            escritor.newLine();
            for (int i = 0; i < clientes.size(); i++) {
                escritor.newLine();
                escritor.write("Cliente #" + (i + 1));
                escritor.newLine();
                escritor.write(clientes.get(i).toString());
                escritor.newLine();
                escritor.write("--------------------------------");
                escritor.newLine();
            }
            System.out.println("Respaldo guardado en RespaldoRumboJoven.txt");
        } catch (IOException e) {
            System.out.println("No se pudo guardar el archivo: " + e.getMessage());
        }
    }

    // Permite elegir a cual cliente se le aplicara una opcion.
    private static Cliente seleccionarCliente() {
        if (clientes.isEmpty()) {
            System.out.println("Primero registre un cliente con la opcion 1.");
            return null;
        }
        System.out.println("\nClientes registrados:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + clientes.get(i).nombre
                    + " - Cedula: " + clientes.get(i).cedula);
        }
        int numero = leerEntero("Seleccione el numero del cliente: ");
        while (numero < 1 || numero > clientes.size()) {
            System.out.println("Numero fuera de rango.");
            numero = leerEntero("Ingrese un numero entre 1 y " + clientes.size() + ": ");
        }
        return clientes.get(numero - 1);
    }

    // Solicita un numero entero y controla entradas no numericas.
    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(teclado.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Escriba un numero entero.");
            }
        }
    }

    // Convierte un valor numerico a una cadena con formato de pesos colombianos.
    private static String dinero(long valor) {
        NumberFormat formato = NumberFormat.getNumberInstance(new Locale("es", "CO"));
        return "$" + formato.format(valor) + " COP";
    }

    // Clase que representa un cliente y los datos de su viaje.
    private static class Cliente {
        private final String nombre;
        private final String cedula;
        private final String fecha;
        private final String destino;
        private String tipoMaleta = "";
        private int acompanantes = 0;
        private long valorViaje = 0;
        private boolean calculado = false;

        private Cliente(String nombre, String cedula, String fecha, String destino) {
            this.nombre = nombre;
            this.cedula = cedula;
            this.fecha = fecha;
            this.destino = destino;
        }

        @Override
        public String toString() {
            return "Nombre: " + nombre + "\n"
                    + "Cedula: " + cedula + "\n"
                    + "Fecha del viaje: " + fecha + "\n"
                    + "Destino: " + destino + "\n"
                    + "Tipo de maleta: " + (tipoMaleta.isEmpty() ? "Sin seleccionar" : tipoMaleta) + "\n"
                    + "Acompanantes: " + acompanantes + "\n"
                    + "Valor del viaje: " + (calculado ? dinero(valorViaje) : "Pendiente de calcular");
        }
    }
}
