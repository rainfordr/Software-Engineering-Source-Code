/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.Instructions;



/**
 *
 * @author owner
 */
public class Drop implements Instruction{
    public final int state;
    public Drop(int state){
        this.state = state;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Drop other = (Drop) obj;
        return this.state == other.state;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.state;
        return hash;
    }
}
