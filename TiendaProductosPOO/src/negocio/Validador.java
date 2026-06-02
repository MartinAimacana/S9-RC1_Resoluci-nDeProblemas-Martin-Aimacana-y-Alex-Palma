package negocio;

/**
 * Clase de apoyo para validar entradas y restricciones del sistema.
 * Integrantes: Martin Aimacaña y Alex Palma.
 */
public class Validador {
    public static void validarTexto(String valor, String campo) throws ValidacionException {
        if (valor == null || valor.trim().isEmpty()) {
            throw new ValidacionException("El campo '" + campo + "' es obligatorio.");
        }
    }

    public static void validarEnteroPositivo(int valor, String campo) throws ValidacionException {
        if (valor < 0) {
            throw new ValidacionException("El campo '" + campo + "' no puede ser negativo.");
        }
    }

    public static void validarPrecio(double valor, String campo) throws ValidacionException {
        if (valor <= 0) {
            throw new ValidacionException("El campo '" + campo + "' debe ser mayor a cero.");
        }
    }
}
