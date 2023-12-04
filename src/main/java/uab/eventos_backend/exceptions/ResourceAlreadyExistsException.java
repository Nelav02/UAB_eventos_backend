package uab.eventos_backend.exceptions;

public class ResourceAlreadyExistsException extends Exception {

    public ResourceAlreadyExistsException() {
        super("El recurso ya existe");
    }

    public ResourceAlreadyExistsException(String mensaje) {
        super(mensaje);
    }
}
