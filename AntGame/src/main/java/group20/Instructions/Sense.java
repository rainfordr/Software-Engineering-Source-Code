/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.Instructions;
import group20.Instructions.*;
import group20.Conditions.Condition;
import java.util.Objects;

/**
 *
 * @author owner
 */
public class Sense implements Instruction{
    
    public SenseDir senseDir;
    public int st1;
    public int st2;
    public  Condition condition;
    public Sense(SenseDir sensedir, int st1, int st2, Condition condition){
        this.senseDir = sensedir;
        this.st1 = st1;
        this.st2 = st2;
        this.condition = condition;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.st1;
        hash = 97 * hash + this.st2;
        hash = 97 * hash + Objects.hashCode(this.condition);
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
        final Sense other = (Sense) obj;
        if (this.st1 != other.st1) {
            return false;
        }
        if (this.st2 != other.st2) {
            return false;
        }
        if (this.condition.getClass() != other.condition.getClass()){
            return false;
        }
        return true;
    }
    
    
  
    public enum SenseDir {
        HERE, AHEAD, LEFT_AHEAD, RIGHT_AHEAD;
    }
    
}
