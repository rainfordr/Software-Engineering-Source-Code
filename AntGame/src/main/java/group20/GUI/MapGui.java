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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
<<<<<<< HEAD
    Map mapClass;
    MapCell[][] worldMap;
=======
    JMenuBar menuBar = new JMenuBar();
    JMenu gameMenu = new JMenu("Game");
    JMenuItem setAntBrains = new JMenuItem("Set Ant Brains");
    JMenuItem selectWorld = new JMenuItem("Select World");
    JMenuItem resetButton = new JMenuItem("Reset");
    JMenuItem exitButton = new JMenuItem("Exit");
    Map worldMap;
    JFrame gameOptionsFrame = new JFrame("Set Game Options..");
    JPanel optionsPanel = new JPanel();
    JLabel selectBrain1 = new JLabel("Select Brain 1:");
    JLabel selectBrain2 = new JLabel("Select Brain 2:");
    JButton selectBrain1Button = new JButton("Browse...");
    JButton selectBrain2Button = new JButton("Browse...");
    JButton okButton = new JButton("OK");
    JButton cancelButton = new JButton("Cancel");
    MapMouseListener listener = new MapMouseListener();
    JFileChooser chooseBrain1 = new JFileChooser();
    JFileChooser chooseBrain2 = new JFileChooser();
    JFileChooser chooseWorld = new JFileChooser();
>>>>>>> origin/master

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
<<<<<<< HEAD
        mapClass = worldMap;
        this.worldMap = worldMap.getMap();
    }
    
    public MapGui(MapCell[][] worldMap){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
=======
        gameMenu.add(setAntBrains);
        setAntBrains.addMouseListener(listener);
        gameMenu.add(selectWorld);
        selectWorld.addMouseListener(listener);
        gameMenu.add(resetButton);
        resetButton.addMouseListener(listener);
        gameMenu.add(exitButton);
        exitButton.addMouseListener(listener);
        menuBar.add(gameMenu);
        this.setJMenuBar(menuBar);
        optionsPanel.setLayout(new GridLayout(0,2));
        optionsPanel.add(selectBrain1);
        optionsPanel.add(selectBrain1Button);
        selectBrain1Button.addMouseListener(listener);
        optionsPanel.add(selectBrain2);
        optionsPanel.add(selectBrain2Button);
        selectBrain2Button.addMouseListener(listener);
        optionsPanel.add(cancelButton);
        cancelButton.addMouseListener(listener);
        optionsPanel.add(okButton);
        okButton.addMouseListener(listener);
        gameOptionsFrame.add(optionsPanel);
        gameOptionsFrame.pack();
>>>>>>> origin/master
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

    class MapMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getSource() == exitButton) {
                System.exit(0);
            }
            if (e.getSource() == resetButton) {
                
            }
            if (e.getSource() == cancelButton) {
                gameOptionsFrame.setVisible(false);                
            }
            if (e.getSource() == setAntBrains) {
                gameOptionsFrame.setVisible(true);
            }
            if (e.getSource() == selectBrain1Button) {
                chooseBrain1.showOpenDialog(gameMenu);
            }
            if (e.getSource() == selectBrain2Button) {
                chooseBrain2.showOpenDialog(gameMenu);
            }
            if (e.getSource() == selectWorld) {
                chooseWorld.showOpenDialog(gameMenu);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

}
