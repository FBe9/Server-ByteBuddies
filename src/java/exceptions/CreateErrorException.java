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
public class CreateErrorException extends Exception {

    /**
     * Creates a new instance of <code>CreateErrorException</code> without
     * detail message.
     */
    public CreateErrorException() {
    }

    /**
     * Constructs an instance of <code>CreateErrorException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CreateErrorException(String msg) {
        super(msg);
    }
}
