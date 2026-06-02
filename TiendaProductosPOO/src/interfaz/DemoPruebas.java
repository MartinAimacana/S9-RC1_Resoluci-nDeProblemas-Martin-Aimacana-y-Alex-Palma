package interfaz;

import modelo.Producto;
import modelo.ProductoNoPerecedero;
import modelo.ProductoPerecedero;
import negocio.Inventario;
import negocio.ValidacionException;

import java.time.LocalDate;

/**
 * Clase de pruebas automáticas sencillas para generar evidencias de ejecución.
 * Integrantes del grupo: Martin Aimacaña y Alex Palma.
 * En IntelliJ IDEA se ejecuta desde este archivo: src/interfaz/DemoPruebas.java.
 */
public class DemoPruebas {
    public static void main(String[] args) {
        Inventario inventario = new Inventario(500.00, 200.00);
        System.out.println("===== DEMO DE PRUEBAS - TIENDA DE PRODUCTOS =====");
        System.out.println("Integrantes: Martin Aimacana y Alex Palma");

        ejecutarCP1(inventario);
        ejecutarCP2(inventario);
        ejecutarCP3(inventario);
        ejecutarCP4(inventario);
        ejecutarCP5(inventario);
        ejecutarCP6(inventario);
        ejecutarCP7(inventario);
        ejecutarCP8(inventario);
    }

    private static void ejecutarCP1(Inventario inventario) {
        System.out.println("\nCP1: Validacion de registro con campos vacios");
        try {
            Producto p = new ProductoNoPerecedero(inventario.generarId(), "", 10, 0.50, 0.75, 5, 1, "Ferretería");
            inventario.registrarProducto(p);
        } catch (ValidacionException e) {
            System.out.println("Resultado esperado: " + e.getMessage());
        }
    }

    private static void ejecutarCP2(Inventario inventario) {
        System.out.println("\nCP2: Registro correcto de producto perecedero");
        try {
            Producto p = new ProductoPerecedero(inventario.generarId(), "Leche", 20, 0.70, 1.00, 5, 0.5,
                    LocalDate.now().plusDays(10));
            inventario.registrarProducto(p);
            System.out.println("Producto registrado: " + p);
        } catch (ValidacionException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void ejecutarCP3(Inventario inventario) {
        System.out.println("\nCP3: Listado y valor total del inventario");
        for (Producto p : inventario.listarProductos()) {
            System.out.println(p);
        }
        System.out.printf("Valor total: $%.2f%n", inventario.calcularValorTotalInventario());
    }

    private static void ejecutarCP4(Inventario inventario) {
        System.out.println("\nCP4: Compra de producto");
        try {
            inventario.comprarProducto(2, 10);
            System.out.println("Compra registrada. Stock actual: " + inventario.buscarPorId(2).getCantidad());
            System.out.printf("Presupuesto disponible: $%.2f%n", inventario.getPresupuestoDisponible());
        } catch (ValidacionException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void ejecutarCP5(Inventario inventario) {
        System.out.println("\nCP5: Venta con control de stock insuficiente");
        try {
            inventario.venderProducto(2, 100);
        } catch (ValidacionException e) {
            System.out.println("Resultado esperado: " + e.getMessage());
        }
    }

    private static void ejecutarCP6(Inventario inventario) {
        System.out.println("\nCP6: Actualizacion de producto");
        try {
            inventario.actualizarProducto(2, "Leche entera", 0.72, 1.10, 8);
            System.out.println("Producto actualizado: " + inventario.buscarPorId(2));
        } catch (ValidacionException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void ejecutarCP7(Inventario inventario) {
        System.out.println("\nCP7: Eliminacion de producto");
        try {
            Producto arroz = new ProductoNoPerecedero(inventario.generarId(), "Arroz", 8, 0.80, 1.10, 5, 0.2, "Abarrotes");
            inventario.registrarProducto(arroz);
            inventario.eliminarProducto(arroz.getId());
            System.out.println("Producto eliminado correctamente: Arroz");
        } catch (ValidacionException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void ejecutarCP8(Inventario inventario) {
        System.out.println("\nCP8: Alertas de bajo stock y vencimiento");
        try {
            Producto yogurt = new ProductoPerecedero(inventario.generarId(), "Yogurt", 3, 0.60, 0.90, 5, 0.3,
                    LocalDate.now().minusDays(1));
            inventario.registrarProducto(yogurt);
            System.out.println("Bajo stock:");
            inventario.obtenerProductosBajoStock().forEach(System.out::println);
            System.out.println("Vencidos:");
            inventario.obtenerProductosVencidos().forEach(System.out::println);
        } catch (ValidacionException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
