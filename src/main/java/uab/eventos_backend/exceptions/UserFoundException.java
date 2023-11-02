package uab.eventos_backend.exceptions;

public class UserFoundException extends Exception{

    public UserFoundException() {
        super("Ya existe un usuario registrado con el mismo email !!");
    }

    public UserFoundException(String mensaje) {
        super(mensaje);
    }
}