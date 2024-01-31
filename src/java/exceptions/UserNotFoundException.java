package exceptions;

/**
 * Exception if any error happens during data creation.
 * @author irati
 */
public class UserNotFoundException extends Exception {
    /**
     * Creates a new instance without message.
     */
    public UserNotFoundException() {
    }
    /**
     * Creates a new instance with message.
     * 
     * @param message The message of the error.
     */
    public UserNotFoundException(String message) {
        super(message);
    }
    
}
