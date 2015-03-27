/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.Instructions;

import group20.Conditions.Condition;



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
}

