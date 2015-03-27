/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.Instructions;

import group20.Instructions.*;

/**
 *
 * @author froup 20
 */
public class Move implements Instruction{
    public int st1;
    public int st2;
    
    /**
     * constructor for Move Instruction token
     * @param st1 first state transition option
     * @param st2 second state transition option if way ahead blocked
     */
    public Move(int st1, int st2){
        this.st1 = st1;
        this.st2 = st2;
    }
}
