/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.Instructions;

import group20.Instructions.*;
import java.util.Objects;

/**
 *
 * @author owner
 */
public class Turn implements Instruction{
    public LeftOrRight turnDir;
    public int state;

    /**
     * COnstructor for Turn Instruction token
     * @param turnDir the direction to turn
     * @param state the state to proceed to
     */
    public Turn(LeftOrRight turnDir, int state){
        this.turnDir = turnDir;
        this.state = state;
    }
    public enum LeftOrRight{
        LEFT, RIGHT;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.turnDir);
        hash = 29 * hash + this.state;
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
        final Turn other = (Turn) obj;
        if (this.turnDir != other.turnDir) {
            return false;
        }
        if (this.state != other.state) {
            return false;
        }
        return true;
    }
    
    
}
