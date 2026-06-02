package modelo;

import java.time.LocalDate;

/**
 * Clase base del sistema de inventario.
 * Integrantes: Martin Aimacaña y Alex Palma.
 * Aplica encapsulamiento porque sus atributos son privados y se controlan por métodos.
 */
public abstract class Producto {
    private int id;
    private String nombre;
    private int cantidad;
    private double precioCompra;
    private double precioVenta;
    private int stockMinimo;
    private double espacioUnidad;
    private LocalDate fechaRegistro;

    public Producto(int id, String nombre, int cantidad, double precioCompra,
                    double precioVenta, int stockMinimo, double espacioUnidad) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.stockMinimo = stockMinimo;
        this.espacioUnidad = espacioUnidad;
        this.fechaRegistro = LocalDate.now();
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getCantidad() { return cantidad; }
    public double getPrecioCompra() { return precioCompra; }
    public double getPrecioVenta() { return precioVenta; }
    public int getStockMinimo() { return stockMinimo; }
    public double getEspacioUnidad() { return espacioUnidad; }
    public LocalDate getFechaRegistro() { return fechaRegistro; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecioCompra(double precioCompra) { this.precioCompra = precioCompra; }
    public void setPrecioVenta(double precioVenta) { this.precioVenta = precioVenta; }
    public void setStockMinimo(int stockMinimo) { this.stockMinimo = stockMinimo; }
    public void setEspacioUnidad(double espacioUnidad) { this.espacioUnidad = espacioUnidad; }

    public void aumentarStock(int unidades) {
        this.cantidad += unidades;
    }

    public boolean disminuirStock(int unidades) {
        if (unidades <= cantidad) {
            this.cantidad -= unidades;
            return true;
        }
        return false;
    }

    public double calcularValorInventario() {
        return cantidad * precioCompra;
    }

    public double calcularGananciaUnitaria() {
        return precioVenta - precioCompra;
    }

    public boolean requiereReposicion() {
        return cantidad <= stockMinimo;
    }

    /** Método polimórfico: cada tipo de producto define si esta vencido. */
    public abstract boolean estaVencido();

    /** Método polimórfico: cada subclase entrega su descripción propia. */
    public abstract String getTipo();

    @Override
    public String toString() {
        return String.format("ID:%d | %s | Tipo:%s | Cant:%d | PC:$%.2f | PV:$%.2f | Stock min:%d",
                id, nombre, getTipo(), cantidad, precioCompra, precioVenta, stockMinimo);
    }
}
