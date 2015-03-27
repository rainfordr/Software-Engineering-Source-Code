/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.Instructions;
import group20.Instructions.*;
import group20.Instructions.Mark.Marker;

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
}
