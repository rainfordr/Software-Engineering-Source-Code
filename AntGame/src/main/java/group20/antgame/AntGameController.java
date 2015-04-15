/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.antgame;

import group20.GUI.MapGui;
import group20.Instructions.Instruction;
import group20.exceptions.InvalidMapSyntaxException;
import java.io.File;
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
    
    
    public AntGameController(){
        mapGui = new MapGui(this);
        brainParser = new BrainParser();
        mapParser = new MapParser();
        worldGen = new WorldGenerator();
        try {
            setCurrentMapFromFile("./src/main/resources/worlds/1.world");
            mapGui.setMap();
        } catch (IOException ex) {
            mapGui.makeWarningWindow("File not found, please choose another file");
        }
    }
    
    public void setAntBrains(File[] brains){
        playerBrains = brains;
    }
    
    public void setCurrentMapFromFile(String filePath) throws IOException{
        String[] mapArray = Utils.fileToStringArray(filePath);
        char[][] charMap;
        try {
            charMap = mapParser.parseMap(mapArray, true);
        mapChecker = new MapChecker(charMap);
        if(!mapChecker.FinalCheck()){
            mapGui.makeWarningWindow("This map does not meet competition standards, please choose another file");
        }
        currentMap = Map.getCellMap(charMap);
        } catch (InvalidMapSyntaxException ex) {
            String warning = ex.getMessage();
            mapGui.makeWarningWindow("Invalid map syntax: " + warning +" Please choose another file");
        }
    }
    
    public void setRandomMap(){
        char[][] charMap = worldGen.generateMap();
        currentMap = Map.getCellMap(charMap);
    }

    public MapCell[][] getCurrentMap() {
        return currentMap;
    }
}
