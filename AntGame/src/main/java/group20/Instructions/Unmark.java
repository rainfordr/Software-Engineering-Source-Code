/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.Instructions;
import group20.Instructions.*;
import group20.Instructions.Mark.Marker;
import java.util.Objects;

/**
 *
 * @author group20
 */


public class Unmark implements Instruction{
    public Marker marker;
    public int state;
    /**
    * Constructor for Mark Instruction token
    * @param marker the marker type
    * @param state the state to move to
    */
    public Unmark(Marker marker, int state){
        this.marker = marker;
        this.state = state;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.marker);
        hash = 31 * hash + this.state;
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
        final Unmark other = (Unmark) obj;
        if (this.marker != other.marker) {
            return false;
        }
        if (this.state != other.state) {
            return false;
        }
        return true;
    }
    
    
}
