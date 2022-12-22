package exceptions;

/**
 *
 * @author haize
 */
public class NoDietsForClientException extends Exception {
    public NoDietsForClientException() {
        super();
    }
    
    public NoDietsForClientException(String msg) {
        super(msg);
    }
}
