/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.antgame;

import group20.GUI.MapGui;
import group20.Instructions.Instruction;
import group20.exceptions.InvalidMapSyntaxException;
import group20.exceptions.InvalidWorldException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author owner
 */
public class AntGameController {
    private AntGameModel model;
    private MapGui mapGui;
    private MapCell[][] currentMap;
    private BrainParser brainParser;
    private MapParser mapParser;
    private MapChecker mapChecker;
    private WorldGenerator worldGen;
    private Instruction[][] players;
    private int[] playerScores;
    private File[] playerBrains;
    
    public static void main(String args[]){
        AntGameController controller = new AntGameController();
        
    }
    
    public AntGameController(){
        try {
            setCurrentMapFromFile("./src/main/resources/worlds/1.world");
        } catch (IOException ex) {
            mapGui.makeWarningWindow("File not found, please choose another file");
        } catch (InvalidWorldException ex) {
            mapGui.makeWarningWindow("Invalid world.");
        }
        mapGui = new MapGui(this);
        mapGui.loadImages();
        mapGui.drawMap();
        brainParser = new BrainParser();
        mapParser = new MapParser();
        worldGen = new WorldGenerator();
    }
    
    public void setAntBrains(File[] brains){
        playerBrains = brains;
    }
    
    public boolean parseBrain(){
        return false;
    }
    
    public void setCurrentMapFromFile(String filePath) throws IOException, FileNotFoundException, InvalidWorldException{
        currentMap = new Map(new File(filePath)).getCellMap();
    }
    
    public void setRandomMap(){
        char[][] charMap = worldGen.generateMap();
        currentMap = Map.getCellMap(charMap);
    }

    public MapCell[][] getCurrentMap() {
        return currentMap;
    }
    
    public void setCurrentMap(MapCell[][] map){
        currentMap = map;
    }
}
