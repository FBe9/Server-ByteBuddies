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
public class DeleteErrorException extends Exception {

    /**
     * Creates a new instance of <code>DeleteErrorException</code> without
     * detail message.
     */
    public DeleteErrorException() {
    }

    /**
     * Constructs an instance of <code>DeleteErrorException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DeleteErrorException(String msg) {
        super(msg);
    }
}
