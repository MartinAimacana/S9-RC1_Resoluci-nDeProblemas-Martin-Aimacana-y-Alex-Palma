package negocio;

import modelo.Producto;
import modelo.ProductoPerecedero;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Capa de negocio. Gestiona el CRUD de productos y las operaciones de compra/venta.
 * Integrantes: Martin Aimacaña y Alex Palma.
 */
public class Inventario {
    private Map<Integer, Producto> productos;
    private int siguienteId;
    private double presupuestoDisponible;
    private double capacidadBodega;

    public Inventario(double presupuestoDisponible, double capacidadBodega) {
        this.productos = new LinkedHashMap<>();
        this.siguienteId = 1;
        this.presupuestoDisponible = presupuestoDisponible;
        this.capacidadBodega = capacidadBodega;
    }

    public int generarId() {
        return siguienteId++;
    }

    public void registrarProducto(Producto producto) throws ValidacionException {
        validarProducto(producto);
        if (productos.containsKey(producto.getId())) {
            throw new ValidacionException("Ya existe un producto con el ID " + producto.getId());
        }
        validarCapacidad(producto.getCantidad(), producto.getEspacioUnidad());
        productos.put(producto.getId(), producto);
    }

    public Producto buscarPorId(int id) throws ValidacionException {
        Producto producto = productos.get(id);
        if (producto == null) {
            throw new ValidacionException("No existe un producto con el ID " + id);
        }
        return producto;
    }

    public List<Producto> listarProductos() {
        return new ArrayList<>(productos.values());
    }

    public void actualizarProducto(int id, String nuevoNombre, double nuevoPrecioCompra,
                                   double nuevoPrecioVenta, int nuevoStockMinimo) throws ValidacionException {
        Producto producto = buscarPorId(id);
        Validador.validarTexto(nuevoNombre, "nombre");
        Validador.validarPrecio(nuevoPrecioCompra, "precio de compra");
        Validador.validarPrecio(nuevoPrecioVenta, "precio de venta");
        Validador.validarEnteroPositivo(nuevoStockMinimo, "stock minimo");
        producto.setNombre(nuevoNombre);
        producto.setPrecioCompra(nuevoPrecioCompra);
        producto.setPrecioVenta(nuevoPrecioVenta);
        producto.setStockMinimo(nuevoStockMinimo);
    }

    public void eliminarProducto(int id) throws ValidacionException {
        if (productos.remove(id) == null) {
            throw new ValidacionException("No se pudo eliminar. El producto no existe.");
        }
    }

    public void comprarProducto(int id, int unidades) throws ValidacionException {
        Producto producto = buscarPorId(id);
        Validador.validarEnteroPositivo(unidades, "unidades a comprar");
        if (unidades == 0) {
            throw new ValidacionException("La compra debe ser de al menos una unidad.");
        }
        double costoCompra = unidades * producto.getPrecioCompra();
        if (costoCompra > presupuestoDisponible) {
            throw new ValidacionException("Presupuesto insuficiente para comprar. Costo: $" + String.format("%.2f", costoCompra));
        }
        validarCapacidad(unidades, producto.getEspacioUnidad());
        producto.aumentarStock(unidades);
        presupuestoDisponible -= costoCompra;
    }

    public void venderProducto(int id, int unidades) throws ValidacionException {
        Producto producto = buscarPorId(id);
        Validador.validarEnteroPositivo(unidades, "unidades a vender");
        if (unidades == 0) {
            throw new ValidacionException("La venta debe ser de al menos una unidad.");
        }
        if (!producto.disminuirStock(unidades)) {
            throw new ValidacionException("Stock insuficiente. Disponible: " + producto.getCantidad());
        }
        presupuestoDisponible += unidades * producto.getPrecioVenta();
    }

    public double calcularValorTotalInventario() {
        double total = 0;
        for (Producto producto : productos.values()) {
            total += producto.calcularValorInventario();
        }
        return total;
    }

    public double calcularEspacioUsado() {
        double total = 0;
        for (Producto producto : productos.values()) {
            total += producto.getCantidad() * producto.getEspacioUnidad();
        }
        return total;
    }

    public List<Producto> obtenerProductosBajoStock() {
        List<Producto> resultado = new ArrayList<>();
        for (Producto producto : productos.values()) {
            if (producto.requiereReposicion()) {
                resultado.add(producto);
            }
        }
        return resultado;
    }

    public List<Producto> obtenerProductosVencidos() {
        List<Producto> resultado = new ArrayList<>();
        for (Producto producto : productos.values()) {
            if (producto instanceof ProductoPerecedero && producto.estaVencido()) {
                resultado.add(producto);
            }
        }
        return resultado;
    }

    public double getPresupuestoDisponible() {
        return presupuestoDisponible;
    }

    public double getCapacidadBodega() {
        return capacidadBodega;
    }

    private void validarProducto(Producto producto) throws ValidacionException {
        if (producto == null) {
            throw new ValidacionException("El producto no puede ser nulo.");
        }
        Validador.validarTexto(producto.getNombre(), "nombre");
        Validador.validarEnteroPositivo(producto.getCantidad(), "cantidad");
        Validador.validarPrecio(producto.getPrecioCompra(), "precio de compra");
        Validador.validarPrecio(producto.getPrecioVenta(), "precio de venta");
        Validador.validarEnteroPositivo(producto.getStockMinimo(), "stock minimo");
        if (producto.getEspacioUnidad() <= 0) {
            throw new ValidacionException("El espacio por unidad debe ser mayor a cero.");
        }
    }

    private void validarCapacidad(int unidades, double espacioUnidad) throws ValidacionException {
        double espacioNecesario = unidades * espacioUnidad;
        if (calcularEspacioUsado() + espacioNecesario > capacidadBodega) {
            throw new ValidacionException("Capacidad de bodega insuficiente. Espacio disponible: "
                    + String.format("%.2f", capacidadBodega - calcularEspacioUsado()));
        }
    }
}
