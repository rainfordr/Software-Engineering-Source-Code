/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.Instructions;



/**
 *
 * @author group20
 */
public class PickUp implements Instruction{
    public final int st1;
    public final int st2;

    /**
     * Constructor for PickUp Instruction Token
     * @param st1 first state option
     * @param st2 second state option
     */
    public PickUp(int st1, int st2){
        this.st1 = st1;
        this.st2 = st2;
    }
}
