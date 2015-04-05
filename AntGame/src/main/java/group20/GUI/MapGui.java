/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.GUI;

import java.awt.Color;
import group20.antgame.*;
import group20.exceptions.InvalidWorldException;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author bryan1495
 */
public class MapGui extends JFrame {

    Image clearImage;
    Image rockyImage;
    Image redImage;
    Image blackImage;
    Image foodImage;
    ImageIcon clearIcon;
    ImageIcon rockyIcon;
    ImageIcon redIcon;
    ImageIcon blackIcon;
    ImageIcon foodIcon;
    JPanel mapPanel = new JPanel();
    Map mapClass;
    MapCell[][] worldMap;

    public void loadImages() {
        try {
            clearImage = ImageIO.read(new File("./src/main/resources/graphics/clear.png"));
            rockyImage = ImageIO.read(new File("./src/main/resources/graphics/rocky.png"));
            redImage = ImageIO.read(new File("./src/main/resources/graphics/red.png"));
            blackImage = ImageIO.read(new File("./src/main/resources/graphics/black.png"));
            foodImage = ImageIO.read(new File("./src/main/resources/graphics/food.png"));
            clearIcon = new ImageIcon(clearImage);
            rockyIcon = new ImageIcon(rockyImage);
            redIcon = new ImageIcon(redImage);
            blackIcon = new ImageIcon(blackImage);
            foodIcon = new ImageIcon(foodImage);
        } catch (IOException e) {
        }
    }

    public MapGui(Map worldMap) {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        mapClass = worldMap;
        this.worldMap = worldMap.getMap();
    }
    
    public MapGui(MapCell[][] worldMap){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.worldMap = worldMap;
    }

    public void drawMap() {
        mapPanel.setLayout(null);
        int offset = 0;
        for (int y = 0; y < 150; y++) {
            for (int x = 0; x < 150; x++) {
                if (worldMap[x][y].isRocky()) {
                    JLabel label = new JLabel();
                    label.setIcon(rockyIcon);
                    mapPanel.add(label);
                    label.setBounds(x * 6 + offset, y * 5, 5, 6);
                } else if (worldMap[x][y].isAntHillCell(Ant.Colour.RED)) {
                    JLabel label = new JLabel();
                    label.setIcon(redIcon);
                    mapPanel.add(label);
                    label.setBounds(x * 6 + offset, y * 5, 5, 6);
                } else if (worldMap[x][y].isAntHillCell(Ant.Colour.BLACK)) {
                    JLabel label = new JLabel();
                    label.setIcon(blackIcon);
                    mapPanel.add(label);
                    label.setBounds(x * 6 + offset, y * 5, 5, 6);  //label.setBounds(x * 4, y * 5 + offset, 6, 5);
                } else if (worldMap[x][y].getFood() > 0) {
                    JLabel label = new JLabel();
                    label.setIcon(foodIcon);
                    mapPanel.add(label);
                    label.setBounds(x * 6 + offset, y * 5, 5, 6);
                } else if (!worldMap[x][y].isRocky()) {
                    JLabel label = new JLabel();
                    label.setIcon(clearIcon);
                    mapPanel.add(label);
                    label.setBounds(x * 6 + offset, y * 5, 5, 6);
                }
            }
            if (offset == 0) {
                offset = 3;
            } else {
                offset = 0;
            }
        }
        mapPanel.setMinimumSize(new Dimension(600, 750));
        add(mapPanel);
        this.setResizable(false);
        this.setSize(900, 750);
        this.setVisible(true);
    }

    public static void main(String[] args) throws FileNotFoundException, InvalidWorldException {
        MapGui m = new MapGui(new Map(new File("./src/main/resources/worlds/1.world")));
//        WorldGenerator wg = new WorldGenerator();
//        char[][] charMap = wg.getGeneratedCharMap();
//        Map mapClass = new Map(charMap);
//        MapCell[][] map = mapClass.getCellMap();        
//        MapGui m = new MapGui(map);
        m.loadImages();
        m.drawMap();
    }
}
