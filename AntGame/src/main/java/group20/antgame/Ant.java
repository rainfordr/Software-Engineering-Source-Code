/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.antgame;

import group20.exceptions.InvalidStateException;
import group20.Instructions.Dir;
import static group20.Instructions.Dir.*;

/**
 *
 * @author owner
 */
public class Ant {
    private final int id;
    private final Colour colour;
    private int state;
    private int resting;
    private Dir direction;
    private boolean hasFood;
    private Pos position;    
    
    public Ant(Colour colour, int id, Pos position){
        this.colour = colour;
        this.id = id;
        state = 0;
        resting = 0;
        this.direction = ZERO;
        this.hasFood = false;
        this.position = position;
    }
    
    public int state(){
        return state;
    }
    
    public Colour colour(){
        return colour;
    }
    
    public int resting(){
        return resting;
    }
    
    public Dir direction(){
        return direction;
    }
    
    public boolean hasFood(){
        return hasFood;
    }
    
    public void setState(int state)throws InvalidStateException{
        if(0 > state || state > 9999){
            throw new InvalidStateException("Value for state must be >= 0 && <= 9999");
        }
        this.state = state;
    }
    
    public void setResting(int resting){
        this.resting = resting;
    }
    
    public void setDirection(Dir direction){
        this.direction = direction;
    }
    
    public void setHasFood(boolean hasFood){
        this.hasFood = hasFood;
    }

    public Pos position() {
        return position;
    }
    
    public void setPosition(Pos position){
        this.position = position;
    }

    public int getID() {
        return id;
    }
    
    public enum Colour{
        RED, BLACK;
        public Colour otherColour(Colour c){
            if(c == RED){
                return BLACK;
            }
            return RED;
        }
    }
}
