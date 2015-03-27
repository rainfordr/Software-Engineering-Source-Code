/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.Conditions;
import group20.Conditions.*;
import group20.Instructions.Mark.Marker;

/**
 *
 * @author owner
 */
public class Marked implements Condition{
    public Marker marker;
    public Marked(Marker marker){
        this.marker = marker;
    }
}
