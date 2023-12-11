/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author 2dam
 */
public class UpdateErrorException extends Exception {

    /**
     * Creates a new instance of <code>UpdateErrorException</code> without
     * detail message.
     */
    public UpdateErrorException() {
    }

    /**
     * Constructs an instance of <code>UpdateErrorException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UpdateErrorException(String msg) {
        super(msg);
    }
}
