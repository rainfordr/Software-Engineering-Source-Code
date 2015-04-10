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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.st1;
        hash = 97 * hash + this.st2;
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
        final PickUp other = (PickUp) obj;
        if (this.st1 != other.st1) {
            return false;
        }
        if (this.st2 != other.st2) {
            return false;
        }
        return true;
    }
    
}
