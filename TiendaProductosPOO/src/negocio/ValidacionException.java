package negocio;

/** Excepción controlada para mostrar errores claros al usuario. */
public class ValidacionException extends Exception {
    public ValidacionException(String mensaje) {
        super(mensaje);
    }
}
