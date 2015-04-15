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
import static group20.antgame.AntGameModel.TestStatus.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
    private ArrayList<Integer> deadAnts;
    private int randomSeed = 12345;
   private RandNumGen2 rand;
   private int numOfAnts;
   private boolean test1000;
   
   public enum TestStatus{
       TEST1000, TEST10000; 
   }

    
    public AntGameModel(MapCell[][] map, Instruction[] redBrain, Instruction[] blackBrain){
        this.map = map;
        this.redBrain = redBrain;
        this.blackBrain = blackBrain;
        ants = new HashMap<>();
        deadAnts = new ArrayList<>();
        rand = new RandNumGen2(randomSeed);
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
        try{
            return a.resting();
        }catch(NullPointerException ex){
            boolean test = true;
        }
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
        a.setPosition(p);
        mapCell(p).putAnt(a);
    }
    
    public void clearAntAt(Pos p){
        antAt(p).setPosition(null);
        mapCell(p).clearAnt();
    }
    
    public boolean antIsAlive(int id){
        if(ants.get(id) == null){
            return false;
        }
        return ants.get(id).isAlive();
    }
    
    public Pos findAnt(int id){
        return ants.get(id).position();
    }
    
    public void KillAntAt(Pos p){
        Ant a = mapCell(p).getAnt();
        int id = a.getID();
        a.kill();
        clearAntAt(p);
        deadAnts.add(id);
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
            return cond instanceof Rock;            
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
        else if(cond instanceof Marker){
            Marker m = (Marker)cond;
            return checkMarkerAt(p, c, m);
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
             Pos adj = adjacentCell(p, dir);
             if(someAntIsAt(adj) && colour(antAt(adj)) == c){
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
                     Pos pos2 = sensedCell(pos, ant.direction(), sense.senseDir);
                         if (cellMatches(pos2, col, sense.condition)) {
                             setState(ant, sense.st1);
                         } else {
                             setState(ant, sense.st2);
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
                     
                     int randInt = rand.randInt(randLimit);
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
     
     private void removeDeadAnts(){
         for(int id: deadAnts){
             ants.remove(id);
         }
         deadAnts = new ArrayList<>();
     }
     
     public void playRound(){
         for(int id = 0; id < numOfAnts; id++){
             step(id);
         }
         removeDeadAnts();
     }
     
     public void playGameTest(int rounds, boolean ts){
        String filePath;
        if(ts){
           filePath = "C:\\Users\\owner\\Documents\\NetBeansProjects\\Software-Engineering-Source-Code\\AntGame\\src\\test\\java\\tinyWorldSimTest\\myDump\\myDump1000";
        }
        else{
            filePath = "C:\\Users\\owner\\Documents\\NetBeansProjects\\Software-Engineering-Source-Code\\AntGame\\src\\test\\java\\tinyWorldSimTest\\myDump\\myMassiveDump";
        }
        Utils.clearFile(filePath);
        String dump = "random seed: " + randomSeed + "\n";
        dump += dumpRound(0);
        Utils.appendToFile(dump, filePath);
        for(int turn = 1; turn <= rounds; turn ++){
            playRound();
            dump = dumpRound(turn);
            Utils.appendToFile(dump, filePath);
            if(turn % 1000 == 0){
                int test = 1;
                System.out.println("1000 rounds played");
             }
         }
     }
     
     public void playGame(int rounds){
         for(int turn = 1; turn <= rounds; turn ++){
             playRound();
             if(turn % 1000 == 0){
                 int test = 1;
                 System.out.println("1000 rounds played");
             }
         }
     }
     
    public void playGame(int rounds, boolean ts){
        playGameTest(rounds, ts);
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
                     setAntAt(p, a);
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
         numOfAnts = ants.size();
     }
     
     private String dumpRound(int turn){
         String dump = "\n" + "After round " + turn + "...\n";
         for(int y = 0; y < map.length; y++){
             for(int x = 0; x < map[0].length; x++){
                 Pos p = new Pos(x,y);
                 MapCell mc = mapCell(p);
                 dump += "cell (" + x + ", " + y + "): ";
                 if(rocky(p)){
                     dump += "rock";
                 }
                 else {
                    if(foodAt(p) != 0){
                    dump += foodAt(p) + " food; ";
                    }
                    if(antHillAt(p, RED)){
                        dump += "red hill; ";
                    }
                    else if(antHillAt(p, BLACK)){
                        dump += "black hill; ";
                    }
                    if (checkAnyMarkerAt(p, RED)){
                        dump += "red marks: ";
                        for(Marker marker: Marker.values()){
                            if(checkMarkerAt(p, RED, marker)){
                                dump += marker.ordinal();
                            }
                        }
                        dump += "; ";
                    }
                    if (checkAnyMarkerAt(p, BLACK)){
                        dump += "black marks: ";
                        for(Marker marker: Marker.values()){
                            if(checkMarkerAt(p, BLACK, marker)){
                                dump += marker.ordinal();
                            }
                        }
                        dump += "; ";
                    }
                    if (someAntIsAt(p)){
                        Ant a = antAt(p);
                        int food = 0;
                        if(a.hasFood()){
                            food = 1;
                        }
                        String colour;
                        if(colour(a) == RED){
                            colour = "red";
                        }
                        else{
                            colour = "black";
                        }
                        dump += colour + " ant of id " + a.getID() + ", dir " + a.direction().ordinal() + ", food " + food + ", state " + a.state() + ", resting " + a.resting();
                    }
                    
                 }
                 dump += "\n";
             }
         }
         return dump;
     }
}
