package exceptions;

public class DuplicatoException extends RuntimeException {
    public DuplicatoException(){}
    public DuplicatoException(String message) {
        super(message);
    }
}
