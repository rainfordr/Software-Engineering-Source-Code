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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.randLimit;
        hash = 89 * hash + this.st1;
        hash = 89 * hash + this.st2;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Flip other = (Flip) obj;
        if (this.randLimit != other.randLimit) {
            return false;
        }
        if (this.st1 != other.st1) {
            return false;
        }
        if (this.st2 != other.st2) {
            return false;
        }
        return true;
    }
    public final int randLimit;
    public final int st1;
    public final int st2;
    public Flip(int randLimit, int st1, int st2){
        this.randLimit = randLimit;
        this.st1 = st1;
        this.st2 = st2;
    }
    
}
