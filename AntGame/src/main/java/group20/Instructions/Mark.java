/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.Instructions;

import group20.Conditions.Condition;
import java.util.Objects;



/**
 *
 * @author owner
 */
public class Mark implements Instruction{
    public Marker markerType;
    public int state;
    /**
     * Constructor for Mark Instruction token
     * @param marker the marker type
     * @param state the state to move to
     */
    public Mark(Marker marker, int state){
        this.markerType = marker;
        this.state = state;
    }
    
    /**
     * enum for Marker types
     */
    public enum Marker implements Condition{
        MARKER0, MARKER1, MARKER2, MARKER3, MARKER4, MARKER5;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.markerType);
        hash = 83 * hash + this.state;
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
        final Mark other = (Mark) obj;
        if (this.markerType != other.markerType) {
            return false;
        }
        if (this.state != other.state) {
            return false;
        }
        return true;
    }
    
}

