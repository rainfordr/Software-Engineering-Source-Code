/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.Instructions;
import group20.Instructions.*;
import group20.Conditions.Condition;

/**
 *
 * @author owner
 */
public class Sense implements Instruction{
    
    public static SenseDir senseDir;
    public static int st1;
    public static int st2;
    public static Condition condition;
    public Sense(SenseDir sensedir, int st1, int st2, Condition condition){
        this.senseDir = sensedir;
        this.st1 = st1;
        this.st2 = st2;
        this.condition = condition;
    }

    public SenseDir getSenseDir() {
        return senseDir;
    }

    public int getSt1() {
        return st1;
    }

    public int getSt2() {
        return st2;
    }

    public Condition getCondition() {
        return condition;
    }
    
    
    public enum SenseDir {
        HERE, AHEAD, LEFT_AHEAD, RIGHT_AHEAD;
    }
}
