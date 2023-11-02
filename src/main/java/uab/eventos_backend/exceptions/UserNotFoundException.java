package uab.eventos_backend.exceptions;

public class UserNotFoundException extends Exception{

    public UserNotFoundException() {
        super("No existe un usuario registrado con ese email !!");
    }

    public UserNotFoundException(String mensaje) {
        super(mensaje);
    }
}
