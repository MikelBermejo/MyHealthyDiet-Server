package exceptions;

/**
 *
 * @author haize
 */
public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException() {
        super();
    }
    
    public UserAlreadyExistException(String msg) {
        super(msg);
    }
}
