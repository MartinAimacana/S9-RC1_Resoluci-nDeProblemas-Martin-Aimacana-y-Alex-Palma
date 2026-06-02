package modelo;

/**
 * Representa productos sin fecha de vencimiento cercana, por ejemplo arroz, cuadernos o limpieza.
 * Integrantes: Martin Aimacaña y Alex Palma.
 */
public class ProductoNoPerecedero extends Producto {
    private String categoria;

    public ProductoNoPerecedero(int id, String nombre, int cantidad, double precioCompra,
                                double precioVenta, int stockMinimo, double espacioUnidad,
                                String categoria) {
        super(id, nombre, cantidad, precioCompra, precioVenta, stockMinimo, espacioUnidad);
        this.categoria = categoria;
    }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    @Override
    public boolean estaVencido() {
        return false;
    }

    @Override
    public String getTipo() {
        return "No perecedero (" + categoria + ")";
    }
}
