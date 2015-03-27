/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.Instructions;

import group20.Instructions.*;

/**
 *
 * @author owner
 */
public class Flip implements Instruction{
    public final int randLimit;
    public final int st1;
    public final int st2;
    public Flip(int randLimit, int st1, int st2){
        this.randLimit = randLimit;
        this.st1 = st1;
        this.st2 = st2;
    }
}
