package group20.antgame;

import group20.*;
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

    private MapCell[][] map;
    private int width = 150;
    private int height = 150;

    /**
     * This constructor for the Maze class constructs an array of null cells
     *
     * @param x This is the variable that holds the width of the map
     * @param y This is the variable that holds the height of the map
     */
    public Map(int x, int y) {
        map = new MapCell[x][y];
        width = x;
        height = y;
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
                            map[col][row] = new MapCell(new Pos(col, row), true, 0, false, false);
                        }
                        if (cell.equals(".")) {
                            map[col][row] = new MapCell(new Pos(col, row), false, 0, false, false);
                        }
                        if (cell.equals("+")) {
                            map[col][row] = new MapCell(new Pos(col, row), false, 0, true, false);
                        }
                        if (cell.equals("-")) {
                            map[col][row] = new MapCell(new Pos(col, row), false, 0, false, true);
                        }
                        if (cell.matches("[0-9]")) {
                            System.out.println("found food: " + Integer.parseInt(cell));
                            map[col][row] = new MapCell(new Pos(col, row), false, Integer.parseInt(cell), false, false);
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
