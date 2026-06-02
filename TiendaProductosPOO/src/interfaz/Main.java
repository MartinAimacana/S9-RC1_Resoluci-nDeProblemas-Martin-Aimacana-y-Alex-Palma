package interfaz;

import modelo.Producto;
import modelo.ProductoNoPerecedero;
import modelo.ProductoPerecedero;
import negocio.Inventario;
import negocio.ValidacionException;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Interfaz de consola para probar el sistema de tienda de productos.
 * Integrantes del grupo: Martin Aimacaña y Alex Palma.
 * En IntelliJ IDEA se ejecuta desde este archivo: src/interfaz/Main.java.
 */
public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static Inventario inventario = new Inventario(500.00, 200.00);

    public static void main(String[] args) {
        System.out.println("Integrantes: Martin Aimacana y Alex Palma");
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opcion: ");
            try {
                switch (opcion) {
                    case 1 -> registrarProducto();
                    case 2 -> listarProductos();
                    case 3 -> actualizarProducto();
                    case 4 -> eliminarProducto();
                    case 5 -> comprarProducto();
                    case 6 -> venderProducto();
                    case 7 -> mostrarAlertas();
                    case 0 -> System.out.println("Sistema finalizado.");
                    default -> System.out.println("Opcion no válida.");
                }
            } catch (ValidacionException e) {
                System.out.println("ERROR: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("ERROR inesperado: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("\n===== TIENDA DE PRODUCTOS - INVENTARIO =====");
        System.out.println("Grupo: Martin Aimacana y Alex Palma");
        System.out.println("1. Registrar producto");
        System.out.println("2. Listar productos");
        System.out.println("3. Actualizar producto");
        System.out.println("4. Eliminar producto");
        System.out.println("5. Comprar producto (aumentar stock)");
        System.out.println("6. Vender producto (reducir stock)");
        System.out.println("7. Ver alertas de inventario");
        System.out.println("0. Salir");
    }

    private static void registrarProducto() throws ValidacionException {
        System.out.println("\n--- Registro de producto ---");
        String nombre = leerTexto("Nombre: ");
        int cantidad = leerEntero("Cantidad inicial: ");
        double precioCompra = leerDouble("Precio de compra: ");
        double precioVenta = leerDouble("Precio de venta: ");
        int stockMinimo = leerEntero("Stock minimo: ");
        double espacioUnidad = leerDouble("Espacio por unidad: ");
        int tipo = leerEntero("Tipo 1=Perecedero, 2=No perecedero: ");

        int id = inventario.generarId();
        Producto producto;
        if (tipo == 1) {
            String fecha = leerTexto("Fecha de vencimiento (AAAA-MM-DD): ");
            producto = new ProductoPerecedero(id, nombre, cantidad, precioCompra, precioVenta,
                    stockMinimo, espacioUnidad, LocalDate.parse(fecha));
        } else {
            String categoria = leerTexto("Categoria: ");
            producto = new ProductoNoPerecedero(id, nombre, cantidad, precioCompra, precioVenta,
                    stockMinimo, espacioUnidad, categoria);
        }

        inventario.registrarProducto(producto);
        System.out.println("Producto registrado correctamente con ID " + id);
    }

    private static void listarProductos() {
        System.out.println("\n--- Inventario actual ---");
        List<Producto> productos = inventario.listarProductos();
        if (productos.isEmpty()) {
            System.out.println("No existen productos registrados.");
        } else {
            for (Producto producto : productos) {
                System.out.println(producto);
            }
        }
        System.out.printf("Valor total del inventario: $%.2f%n", inventario.calcularValorTotalInventario());
        System.out.printf("Presupuesto disponible: $%.2f%n", inventario.getPresupuestoDisponible());
        System.out.printf("Espacio usado: %.2f / %.2f%n", inventario.calcularEspacioUsado(), inventario.getCapacidadBodega());
    }

    private static void actualizarProducto() throws ValidacionException {
        int id = leerEntero("ID del producto a actualizar: ");
        String nombre = leerTexto("Nuevo nombre: ");
        double precioCompra = leerDouble("Nuevo precio de compra: ");
        double precioVenta = leerDouble("Nuevo precio de venta: ");
        int stockMinimo = leerEntero("Nuevo stock minimo: ");
        inventario.actualizarProducto(id, nombre, precioCompra, precioVenta, stockMinimo);
        System.out.println("Producto actualizado correctamente.");
    }

    private static void eliminarProducto() throws ValidacionException {
        int id = leerEntero("ID del producto a eliminar: ");
        inventario.eliminarProducto(id);
        System.out.println("Producto eliminado correctamente.");
    }

    private static void comprarProducto() throws ValidacionException {
        int id = leerEntero("ID del producto comprado: ");
        int unidades = leerEntero("Unidades compradas: ");
        inventario.comprarProducto(id, unidades);
        System.out.println("Compra registrada correctamente.");
    }

    private static void venderProducto() throws ValidacionException {
        int id = leerEntero("ID del producto vendido: ");
        int unidades = leerEntero("Unidades vendidas: ");
        inventario.venderProducto(id, unidades);
        System.out.println("Venta registrada correctamente.");
    }

    private static void mostrarAlertas() {
        System.out.println("\n--- Productos con bajo stock ---");
        inventario.obtenerProductosBajoStock().forEach(System.out::println);
        System.out.println("\n--- Productos vencidos ---");
        inventario.obtenerProductosVencidos().forEach(System.out::println);
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero entero valido.");
            }
        }
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un valor decimal valido.");
            }
        }
    }
}
