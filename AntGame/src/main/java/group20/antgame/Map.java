package group20.antgame;

import group20.*;
import static group20.antgame.Ant.Colour.*;
import group20.exceptions.InvalidWorldException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is the map class
 *
 * @author 118481
 */
public class Map {
    private char[][] charMap;
    private MapCell[][] map;
    private int width;
    private int height;

    /**
     * This constructor for the Maze class constructs an array of null cells
     *
     * @param charMap the 2d char array representation of the map to convert to 
     * a cell[][;
     */
    public Map(char[][] charMap) {
        this.charMap = charMap;
        width = charMap[0].length;
        height = charMap.length;
        map = new MapCell[width][height];
    }
    
    public static MapCell[][] getCellMap(char[][] charMap){
        char[][] CHARMAP = charMap;
        int WIDTH = charMap[0].length;
        int HEIGHT = charMap.length;
        return getCellMap(HEIGHT, WIDTH, CHARMAP);
    }

    public Map(File worldFile) throws FileNotFoundException, InvalidWorldException {
        int count150 = 0;
        boolean initialisedMapArray = false;
        BufferedReader buffer = new BufferedReader(new FileReader(worldFile));
        String readLine;
        try {
            int row = 0;
            while ((readLine = buffer.readLine()) != null) {
                if (!readLine.equals("150") && initialisedMapArray) {
                    int col = 0;
                    for (String cell : readLine.trim().split("\\s+")) {
                        if (cell.equals("#")) {
                            map[col][row] = new MapCell(new Pos(col, row), true, 0, null);
                        }
                        if (cell.equals(".")) {
                            map[col][row] = new MapCell(new Pos(col, row), false, 0, null);
                        }
                        if (cell.equals("+")) {
                            map[col][row] = new MapCell(new Pos(col, row), false, 0, RED);
                        }
                        if (cell.equals("-")) {
                            map[col][row] = new MapCell(new Pos(col, row), false, 0, BLACK);
                        }
                        if (cell.matches("[0-9]")) {
                            int foodAmount = Integer.parseInt(cell);
                            System.out.println("found food: " + foodAmount);
                            map[col][row] = new MapCell(new Pos(col, row), false, foodAmount, null);
                        }
                        col++;
                    }
                    row++;
                } else if (readLine.equals("150")) {
                    count150++;
                    if (count150 == 2) {
                        map = new MapCell[150][150];
                        initialisedMapArray = true;
                    }
                } else{
                    throw new InvalidWorldException("Doesn't have world size defined.");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(!isWorldValid(map)){
            throw new InvalidWorldException();
        }
    }

    public boolean isWorldValid(MapCell[][] world) throws InvalidWorldException {
        boolean valid = true;
        for (int x = 0; x < 150; x++) {
            if(x == 0 || x ==149){ //only check the top and bottom rows for a line of rocks.
                for (int y = 0; y < 150; y++) {
                    if(!world[x][y].isRocky()){
                        throw new InvalidWorldException("The border is not rock.");
                    }
                }
            }
        }
        return valid;
    }
    
    public MapCell[][] getCellMap(){
        for(int row = 0; row < height; row++){
            for(int col = 0; col < width; col++){
                char cell = charMap[col][row];
                if (cell == '#') {
                            map[col][row] = new MapCell(new Pos(col, row), true, 0, null);
                }
                if (cell == '.') {
                    map[col][row] = new MapCell(new Pos(col, row), false, 0, null);
                }
                if (cell == '+') {
                    map[col][row] = new MapCell(new Pos(col, row), false, 0, RED);
                }
                if (cell == '-') {
                    map[col][row] = new MapCell(new Pos(col, row), false, 0, BLACK);
                }
                if (Character.isDigit(cell)) {                    
                    int foodAmount = Integer.parseInt(("" + cell));
                    System.out.println("found food: " + foodAmount);
                    map[col][row] = new MapCell(new Pos(col, row), false, foodAmount, null);
                }
            }
        }
        return map;
    }
    
        public static MapCell[][] getCellMap(int height, int width, char[][] cm){
            MapCell[][] MAP = new MapCell[height][width];
            for(int row = 0; row < height; row++){
                for(int col = 0; col < width; col++){
                    char cell = cm[col][row];
                    if (cell == '#') {
                                MAP[col][row] = new MapCell(new Pos(col, row), true, 0, null);
                    }
                    if (cell == '.') {
                        MAP[col][row] = new MapCell(new Pos(col, row), false, 0, null);
                    }
                    if (cell == '+') {
                        MAP[col][row] = new MapCell(new Pos(col, row), false, 0, RED);
                    }
                    if (cell == '-') {
                        MAP[col][row] = new MapCell(new Pos(col, row), false, 0, BLACK);
                    }
                    if (Character.isDigit(cell)) {                    
                        int foodAmount = Integer.parseInt(("" + cell));
                        System.out.println("found food: " + foodAmount);
                        MAP[col][row] = new MapCell(new Pos(col, row), false, foodAmount, null);
                    }
                }
            }
            return MAP;
        }

    /**
     * This method sets a cell to blocked.
     *
     * @param x This is the x coordinate of the blocked cell.
     * @param y This is the y coordinate if the blocked cell.
     */
    public void setRocky(int x, int y, boolean rocky) {
        if (x < width && y < height) {
            map[x][y].setRocky(rocky);
        } else {
            System.out.println("OutOfBounds");
        }
    }

    /**
     * This method gets the width of the map.
     *
     * @return returns the width of the map.
     */
    public int getWidth() {
        return width;
    }

    /**
     * This method gets the height of the map.
     *
     * @return returns the height of the map.
     */
    public int getHeight() {
        return height;
    }

    /**
     * This method gets the map held in the map.
     *
     * @return returns the map held in the map.
     */
    public MapCell[][] getMap() {
        return map;
    }

    /**
     * This method tells whether a cell is blocked or not.
     *
     * @return returns the state of a cell.
     */
    public boolean isBlocked(int x, int y) {
        return map[x][y].isRocky();
    }
}
