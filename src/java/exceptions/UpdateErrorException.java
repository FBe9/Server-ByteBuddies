package exceptions;

/**
 * Exception if any error happens during update.
 * @author irati
 */
public class UpdateErrorException extends Exception {
     /**
     * Creates a new instance without message.
     */
    public UpdateErrorException() {
    }
    /**
     * Creates a new instance with message.
     * 
     * @param message The message of the error.
     */
    public UpdateErrorException(String message) {
        super(message);
    }
    
}
