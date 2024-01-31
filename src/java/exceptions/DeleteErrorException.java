package exceptions;

/**
 * Exception if any error happens during delete.
 * @author irati
 */
public class DeleteErrorException extends Exception {
     /**
     * Creates a new instance without message.
     */
    public DeleteErrorException() {
    }
    /**
     * Creates a new instance with message.
     * 
     * @param message The message of the error.
     */
    public DeleteErrorException(String message) {
        super(message);
    }
    
}
