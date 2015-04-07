/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.antgame;
import group20.exceptions.InvalidStateException;
import group20.Conditions.*;
import group20.Instructions.*;
import group20.Instructions.Mark.Marker;
import group20.Instructions.Sense.SenseDir;
import group20.Instructions.Turn.LeftOrRight;
import static group20.Instructions.Turn.LeftOrRight.*;
import group20.antgame.Ant.Colour;
import static group20.antgame.Ant.Colour.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author owner
 */
public class AntGameModel {
    private MapCell[][] map;
    private Instruction[] redBrain;
    private Instruction[] blackBrain;
    private HashMap<Integer, Ant> ants;
    private BrainParser brainParser;
    private MapParser mapParser;
    
   private RandomGenerator rand;

    
    public AntGameModel(){
        map = new MapCell[150][150];
        redBrain = new Instruction[1000];
        blackBrain = new Instruction[1000];
        brainParser = new BrainParser();
        mapParser = new MapParser();
        ants = new HashMap<>();
        
        rand = new RandomGenerator(12345);
    }
    
    public Pos adjacentCell(Pos p, Dir d){
        int x = p.x;
        int y = p.y;
        boolean evenY = (y % 2 == 0);
        
        switch(d){
            case ZERO:
                return new Pos(x+1, y);
            case ONE:
                if(evenY){
                    return new Pos(x, y+1);
                }
                else{
                    return new Pos(x+1, y+1);
                }
            case TWO:
                if(evenY){
                    return new Pos(x-1, y+1);
                }
                else{
                    return new Pos(x, y+1);
                }
            case THREE:
                return new Pos(x-1, y);
            case FOUR:
                if(evenY){
                    return new Pos(x-1, y-1);
                }
                else{
                    return new Pos(x, y-1);
                }
            case FIVE:
                if(evenY){
                    return new Pos(x, y-1);
                }
                else{
                    return new Pos(x+1, y-1);
                }
        }
        return null;
    }
    
    public Dir turn(LeftOrRight lr, Dir d){
        switch(lr){
            case LEFT:
                return Dir.values[(d.ordinal() + 5) % 6];
            case RIGHT:
                return Dir.values[(d.ordinal() + 1) % 6];
            default: return null;
        }
    }
    
    private MapCell mapCell(Pos p){
        return map[p.x][p.y];
    }
    
    public Pos sensedCell(Pos p, Dir d, SenseDir sd){ //should probably return MapCell at returned Pos
        switch(sd){
            case HERE:
                return p;
            case AHEAD:
                return adjacentCell(p, d);
            case LEFT_AHEAD:
                return adjacentCell(p, turn(LEFT, d));
            case RIGHT_AHEAD:
                return adjacentCell(p, turn(RIGHT, d));
        }
        return null;
    }
    
    public Colour otherColour(Colour c){
        return c.otherColour(c);
    }
    
    public boolean rocky(Pos p){
        return mapCell(p).isRocky();
    }
    
    public int state(Ant a){
        return a.state();
    }
    
    public Colour colour(Ant a){
        return a.colour();
    }
    
    public int resting(Ant a){
        return a.resting();
    }
    
    public Dir direction(Ant a){
        return a.direction();
    }
    
    public boolean hasFood(Ant a){
        return a.hasFood();
    }
    
    public void setState(Ant a, int state) throws InvalidStateException{
        a.setState(state);
    }
    
    public void setResting(Ant a, int Resting){
        a.setResting(Resting);
    }
    
    public void setDirection(Ant a, Dir d){
        a.setDirection(d);
    }
    
    public void setHasFood(Ant a, Boolean hasFood){
        a.setHasFood(hasFood);
    }
    
    public boolean someAntIsAt(Pos p){
        return mapCell(p).hasAnt();
    }

    public Ant antAt(Pos p){
        return mapCell(p).getAnt();
    }
    
    public void setAntAt(Pos p, Ant a){
        mapCell(p).putAnt(a);
    }
    
    public void clearAntAt(Pos p){
        mapCell(p).clearAnt();
    }
    
    public boolean antIsAlive(int id){
        return ants.containsKey(id);
    }
    
    public Pos findAnt(int id){
        return ants.get(id).position();
    }
    
    public void KillAntAt(Pos p){
        ants.remove(mapCell(p).getAnt().getID());
        clearAntAt(p);
    }
    
    public void setMarkerAt(Pos p, Colour c, Marker m){
        mapCell(p).setMarker(m, c);
    }
    
    public void clearMarkerAt(Pos p, Colour c, Marker m){
        mapCell(p).clearMarker(m, c);
    }
    
    public boolean checkMarkerAt(Pos p, Colour c, Marker m){
        return mapCell(p).checkMarker(m, c);
    }
    
    public boolean checkAnyMarkerAt(Pos p, Colour c){
        return mapCell(p).isMarkedByColour(c);
    }
    
    public int foodAt(Pos p){
        return mapCell(p).getFood();
    }
    
    public void setFoodAt(Pos p, int food){
        mapCell(p).setFood(food);
    }
    
    public boolean antHillAt(Pos p, Colour c){
        return mapCell(p).isAntHillCell(c);
    }
    
    public boolean cellMatches(Pos p, Colour c, Condition cond){
        if(rocky(p)){
            if(cond instanceof Rock){
                return true;
            }
            return false;            
        }
        else if(cond instanceof Rock){
            return false;
        }
        else if (cond instanceof Friend){
            return someAntIsAt(p) && colour(antAt(p)) == c;
        }
        else if(cond instanceof Foe){
            return someAntIsAt(p) && colour(antAt(p)) != c;
        }
        else if(cond instanceof FriendWithFood){
            return someAntIsAt(p) && 
                    colour(antAt(p)) == c &&
                    hasFood(antAt(p));
        }
        else if(cond instanceof FoeWithFood){
            return someAntIsAt(p) && 
                    colour(antAt(p)) != c &&
                    hasFood(antAt(p));
        }
        else if(cond instanceof Food){
            return foodAt(p) > 0;
        }
        else if(cond instanceof Marker){ // possibly use marked class instead of instanceof Marker, I was trying to be clever and made Marker implement condition.
            Marker m = (Marker)cond;
            return checkMarkerAt(p, c, m);// possibly will be wrong marker enum after casting. If so will use Marked class here and get correct m from that.
        }
        else if(cond instanceof FoeMarker){
            return checkAnyMarkerAt(p, otherColour(c));
        }
        else if(cond instanceof Home){
            return antHillAt(p, c);
        }
        else if(cond instanceof FoeHome){
            return antHillAt(p, otherColour(c));
        } 
             
        return false;
    }
    
    public Instruction getInstruction(Colour c, int state){
        if(c == Colour.RED){
            return redBrain[state];
        }
        else{
            return blackBrain[state];
        }
    }
    
     public int adjacentAnts(Pos p, Colour c){
         int adjAnts = 0;
         for(Dir dir: Dir.values){
             if(someAntIsAt(p) && colour(antAt(p)) == c){
                 adjAnts++;
             }
         }
         return adjAnts;
     }
     
     public void checkForSurroundedAntAt(Pos p){
         if(someAntIsAt(p)){
             Ant ant = antAt(p);
             if(adjacentAnts(p, otherColour(ant.colour())) >= 5){
                 boolean foodCarried = ant.hasFood();
                 KillAntAt(p);
                 setFoodAt(p, foodAt(p) + 3);
                 if(foodCarried){
                     setFoodAt(p, foodAt(p) + 1);
                 }
             }
         }
     }
     
     public void checkForSurroundedAnts(Pos p){
         checkForSurroundedAntAt(p);
         for(Dir dir: Dir.values){
             checkForSurroundedAntAt(adjacentCell(p, dir));
         }
     }
     
     public void step(int id){
         if(antIsAlive(id)){
             Pos pos = findAnt(id);
             Ant ant = antAt(pos);
             if(resting(ant) > 0){
                 setResting(ant, resting(ant) -1);
             }
             else{try{
                 Colour col = ant.colour();
                 int state = ant.state();
                 Instruction instruction = getInstruction(col, state);
                 if(instruction instanceof Sense){
                     Sense sense = (Sense)instruction;
                     Pos pos2 = sensedCell(pos, ant.direction(), sense.getSenseDir());
                         if (cellMatches(pos, col, sense.getCondition())) {
                             setState(ant, sense.getSt1());
                         } else {
                             setState(ant, sense.getSt2());
                         }
                 }
                 else if(instruction instanceof Mark){
                     Mark mark = (Mark)instruction;
                     Marker marker = mark.markerType;
                     int nextState = mark.state;
                     setMarkerAt(pos, col, marker);
                     setState(ant, nextState);
                 }
                 else if(instruction instanceof Unmark){
                     Unmark unmark = (Unmark)instruction;
                     Marker marker = unmark.marker;
                     int nextState = unmark.state;
                     clearMarkerAt(pos, col, marker);
                     setState(ant, nextState);
                 }
                 else if(instruction instanceof PickUp){
                     PickUp pickUp = (PickUp)instruction;
                     int st1 = pickUp.st1;
                     int st2 = pickUp.st2;
                     if(hasFood(ant)||foodAt(pos) == 0){
                         setState(ant, st2);
                     }
                     else{
                         setFoodAt(pos, foodAt(pos)-1);
                         setHasFood(ant, true);
                         setState(ant, st1);
                     }                     
                 }
                 else if(instruction instanceof Drop){
                     Drop drop = (Drop)instruction;
                     int st1 = drop.state;
                     if(hasFood(ant)){
                         setFoodAt(pos, foodAt(pos)+1);
                         setHasFood(ant, false);
                     }
                     setState(ant, st1);
                 }
                 else if(instruction instanceof Turn){
                     Turn turn = (Turn)instruction;
                     int st1 = turn.state;
                     LeftOrRight lr = turn.turnDir;
                     setDirection(ant, turn(lr, ant.direction()));
                     setState(ant, st1);
                 }
                 else if(instruction instanceof Move){
                     Move move = (Move)instruction;
                     int st1 = move.st1;
                     int st2 = move.st2;
                     Pos newPos = adjacentCell(pos, direction(ant));
                     if(rocky(newPos)||someAntIsAt(newPos)){
                         setState(ant, st2);
                     }
                     else{
                         clearAntAt(pos);
                         setAntAt(newPos, ant);
                         setState(ant, st1);
                         setResting(ant, 14);
                         checkForSurroundedAnts(newPos);
                     }
                 }
                 else{
                     Flip flip = (Flip)instruction;
                     int randLimit = flip.randLimit;
                     int st1 = flip.st1;
                     int st2 = flip.st2;
                     
                     int randInt = rand.randomInt(randLimit);
                     if (randInt == 0){
                         setState(ant, st1);
                     }
                     else{
                         setState(ant, st2);
                     }
                 }
             }catch(InvalidStateException invalidStateException){
                    Logger.getLogger(Map.class.getName()).log(Level.SEVERE, invalidStateException.getMessage(), invalidStateException);
             }
             }
         }
     }
     public void playRound(){
         for(int id: ants.keySet()){
             step(id);
         }
     }
     
     public void populateAntHills(){
         int mapWidth = map[0].length;
         int mapHeight = map.length;
         int id = 0;
         for(int y = 0; y < mapHeight; y++){
             for(int x = 0; x < mapWidth; x++){
                 Pos p = new Pos(x, y);
                 MapCell cell = mapCell(p);
                 if(cell.isAntHillCell(RED)){
                     Ant a = new Ant(RED, id, p);
                     ants.put(id, a);
                     cell.putAnt(a);
                     id++;
                 }
                 else if(cell.isAntHillCell(BLACK)){
                     Ant a = new Ant(BLACK, id, p);
                     ants.put(id, a);
                     cell.putAnt(a);
                     id++;
                 }
             }
         }
     }
}
