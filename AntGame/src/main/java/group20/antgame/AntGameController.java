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
import java.util.List;
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
    private List<Instruction[]> parsedBrains = new ArrayList<>();

    public static void main(String args[]) {
        AntGameController controller = new AntGameController();
    }

    public AntGameController() {
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

    public void setAntBrains(File[] brains) {
        File failedBrain;
        if ((failedBrain = parseBrain(brains)) != null) {
            mapGui.makeWarningWindow("The brain \"" + failedBrain.getName() + "\" is not a syntactically correct brain. Please select a different brain.");
        } else {
            playerBrains = brains;
        }
    }
    
    public void startGame(){
        model = new AntGameModel(currentMap, parsedBrains.get(0), parsedBrains.get(1));
        for(int i = 0; i < 300000; i++){
            model.playRound();
             if(i % 1000 == 0){
                 mapGui.drawMap();
                 System.out.println("1000 rounds played");
             }
        }
    }

    public int getAntBrainCount() {
        if (playerBrains == null) {
            return 0;
        }
        return playerBrains.length;
    }

    public File parseBrain(File[] brains) {
        for (File brain : brains) {
            try {
                parsedBrains.add(brainParser.parseBrain(Utils.fileToStringArray(brain)));
            } catch (IOException ex) {
                Logger.getLogger(AntGameController.class.getName()).log(Level.SEVERE, null, ex);
                return brain;
            } catch (BrainParser.InvalidBrainSyntaxException ex) {
                Logger.getLogger(AntGameController.class.getName()).log(Level.SEVERE, null, ex);
                return brain;
            }
        }
        return null;
    }

    public void setCurrentMapFromFile(String filePath) throws IOException, FileNotFoundException, InvalidWorldException {
        currentMap = new Map(new File(filePath)).getCellMap();
    }

    public void setRandomMap() {
        char[][] charMap = worldGen.generateMap();
        currentMap = Map.getCellMap(charMap);
    }

    public MapCell[][] getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(MapCell[][] map) {
        currentMap = map;
    }
}
