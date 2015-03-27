package group20.antgame;

import group20.Conditions.Condition;
import group20.Conditions.*;
import group20.Instructions.Mark.Marker;
import group20.antgame.Ant.Colour;

/**
 * This is the cell class
 * @author bryan1495
 */
public class MapCell {

    private final Pos pos;
    private boolean rocky;
    private Ant ant;
    private int foodAmount;
    private boolean //red markers
                    rMarker0 = false, rMarker1 = false, rMarker2 = false, 
                    rMarker3 = false, rMarker4 = false, rMarker5 = false,
                    //black markers
                    bMarker0 = false, bMarker1 = false, bMarker2 = false, 
                    bMarker3 = false, bMarker4 = false, bMarker5 = false;
    private boolean isRedAntHill;
    private boolean isBlackAntHill;
    
    

    /**
     * The constructor of the cell class creates a new cell with the given
     * Pos coordinates and sets whether or not it is rocky.
     *
     * @param pos This is the x and y coordinate of the cell.
     */
    public MapCell(Pos pos, boolean rocky, int foodAmount, boolean isRedAntHill, boolean isBlackAntHill) {
        this.pos = pos;
        this.rocky = rocky;
        this.foodAmount = foodAmount;
        this.isRedAntHill = isRedAntHill;
        this.isBlackAntHill = isBlackAntHill;
    }
    
    public void setRocky(boolean rocky){
        this.rocky = rocky;
    }
    
    public void setAntHillCell(Colour c){
        switch(c){
            case BLACK:
                isBlackAntHill = true;
                break;
            case RED:
                isRedAntHill = true;
                break;
        }
    }

    /**
     * This method tells if the cell is a block or a space.
     *
     * @return true if rocky, false otherwise.
     */
    public boolean isRocky() {
        return rocky;
    }

    public Pos getPos(){
        return pos;
    }
    
    public void putAnt(Ant ant){
        this.ant = ant;
    }
    
    public boolean hasFood(){
        return foodAmount > 0;
    }
    
    public void clearAnt(){
        ant = null;
    }
    
    public boolean hasAnt(){
        return ant != null;
    }
    
    public Ant getAnt(){
        return ant;
    }
    
    public void setFood(int food){
        foodAmount = food;
    }
    
    public int getFood(){
        return foodAmount;
    }
    
    public void setMarker(Marker marker, Colour c){
        switch(c){
            case RED:                
                switch(marker){
                    case MARKER0:
                        rMarker0 = true;
                        break;
                    case MARKER1:
                        rMarker1 = true;
                        break;
                    case MARKER2:
                        rMarker2 = true;
                        break;
                    case MARKER3:
                        rMarker3 = true;
                        break;
                    case MARKER4:
                        rMarker4 = true;
                        break;
                    case MARKER5:
                        rMarker5 = true;
                        break;
                    default: break;
                };
                break;
            case BLACK:
                switch(marker){
                    case MARKER0:
                        bMarker0 = true;
                        break;
                    case MARKER1:
                        bMarker1 = true;
                        break;
                    case MARKER2:
                        bMarker2 = true;
                        break;
                    case MARKER3:
                        bMarker3 = true;
                        break;
                    case MARKER4:
                        bMarker4 = true;
                        break;
                    case MARKER5:
                        bMarker5 = true;
                        break;
                    default: break;
                };
                break;
            default: break;
        }
    }
    
    public void clearMarker(Marker marker, Colour c){
        switch(c){
            case RED:                
                switch(marker){
                    case MARKER0:
                        rMarker0 = false;
                        break;
                    case MARKER1:
                        rMarker1 = false;
                        break;
                    case MARKER2:
                        rMarker2 = false;
                        break;
                    case MARKER3:
                        rMarker3 = false;
                        break;
                    case MARKER4:
                        rMarker4 = false;
                        break;
                    case MARKER5:
                        rMarker5 = false;
                        break;
                    default: break;
                };
                break;
            case BLACK:
                switch(marker){
                    case MARKER0:
                        bMarker0 = false;
                        break;
                    case MARKER1:
                        bMarker1 = false;
                        break;
                    case MARKER2:
                        bMarker2 = false;
                        break;
                    case MARKER3:
                        bMarker3 = false;
                        break;
                    case MARKER4:
                        bMarker4 = false;
                        break;
                    case MARKER5:
                        bMarker5 = false;
                        break;
                    default: break;
                };
                break;
            default: break;
        }
    }

    boolean checkMarker(Marker marker, Colour c) { 
       switch(c){
            case RED:                
                switch(marker){
                    case MARKER0:
                        return rMarker0;                       
                    case MARKER1:
                        return rMarker1;
                    case MARKER2:
                        return rMarker2;
                    case MARKER3:
                        return rMarker3;
                    case MARKER4:
                        return rMarker4;
                    case MARKER5:
                        return rMarker5;
                    default: break;
                };
                break;
            case BLACK:
                switch(marker){
                    case MARKER0:
                        return bMarker0;
                    case MARKER1:
                        return bMarker1;
                    case MARKER2:
                        return bMarker2;
                    case MARKER3:
                        return bMarker3;
                    case MARKER4:
                        return bMarker4;
                    case MARKER5:
                        return bMarker5;
                    default: break;
                }
            default: break;
        }
       return false;
    }

    boolean isMarkedByColour(Colour c) {
        switch(c){
            case RED:                
                return rMarker0 || rMarker1 || rMarker2 ||
                        rMarker3 || rMarker4 || rMarker5;
            case BLACK:
                return bMarker0 || bMarker1 || bMarker2 ||
                        bMarker3 || bMarker4 || bMarker5;
            default: return false;
        }
    }   

    public boolean isAntHillCell(Colour c) {
        switch(c){
            case RED:
                return isRedAntHill;
            case BLACK:
                return isBlackAntHill;
        }
        return false;
    }
    
    
}
