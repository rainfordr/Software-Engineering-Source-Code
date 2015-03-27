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
}
