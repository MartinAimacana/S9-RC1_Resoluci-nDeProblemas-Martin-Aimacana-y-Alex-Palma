package modelo;

import java.time.LocalDate;

/**
 * Representa productos con fecha de vencimiento, por ejemplo leche, pan o yogurt.
 * Integrantes: Martin Aimacaña y Alex Palma.
 * Hereda de Producto y sobrescribe métodos para aplicar polimorfismo.
 */
public class ProductoPerecedero extends Producto {
    private LocalDate fechaVencimiento;

    public ProductoPerecedero(int id, String nombre, int cantidad, double precioCompra,
                              double precioVenta, int stockMinimo, double espacioUnidad,
                              LocalDate fechaVencimiento) {
        super(id, nombre, cantidad, precioCompra, precioVenta, stockMinimo, espacioUnidad);
        this.fechaVencimiento = fechaVencimiento;
    }

    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(LocalDate fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }

    @Override
    public boolean estaVencido() {
        return fechaVencimiento.isBefore(LocalDate.now());
    }

    @Override
    public String getTipo() {
        return "Perecedero (vence: " + fechaVencimiento + ")";
    }
}
