package exceptions;

/**
 * Exception if any error happens during data creation.
 * @author irati
 */
public class EmailAlreadyExistsException extends Exception {
    /**
     * Creates a new instance without message.
     */
    public EmailAlreadyExistsException() {
    }
    /**
     * Creates a new instance with message.
     * 
     * @param message The message of the error.
     */
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
    
}
