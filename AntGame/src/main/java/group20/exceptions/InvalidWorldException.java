/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.exceptions;

/**
 *
 * @author bryan1495
 */
public class InvalidWorldException extends Exception {
    public InvalidWorldException(){
        
    }
    
    public InvalidWorldException(String message){
        super(message);
    }
}
