package group20.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu {
    /**Create Window*/
    public static void main(String[] args) {
        new Menu();
    }

    /**Window*/
    public Menu() {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("Ant Game");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new TestPane());
                frame.pack();
                frame.setLocation(500,250);
                frame.setSize(512,384);
                frame.setVisible(true);
            }
        });
    }

    /**Main body of the GameInterface*/
    public class TestPane extends JPanel {

        private List<String> menuItems;
        private String selectMenuItem;
        private String focusedItem;

        private MenuItemPainter painter;
        private Map<String, Rectangle> menuBounds;

        public TestPane() {
            setBackground(Color.BLACK);
            painter = new SimpleMenuItemPainter();
            menuItems = new ArrayList<>(25);
            menuItems.add("---Start---");
            menuItems.add("---Exit---");
            menuItems.add("*Info*");
            selectMenuItem = menuItems.get(0);

            /**Mouse*/
            MouseAdapter ma = new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String newItem = null;
                    for (String item : menuItems) {
                        Rectangle bounds = menuBounds.get(item);
                        if (bounds.contains(e.getPoint())) {
                            newItem = item;
                            break;
                        }
                    }
                    if (newItem != null && !newItem.equals(selectMenuItem)) {
                        selectMenuItem = newItem;
                        repaint();
                    }
                }

                @Override
                public void mouseMoved(MouseEvent e) {
                    focusedItem = null;
                    for (String item : menuItems) {
                        Rectangle bounds = menuBounds.get(item);
                        if (bounds.contains(e.getPoint())) {
                            focusedItem = item;
                            repaint();
                            break;
                        }
                    }
                }

            };

            addMouseListener(ma);
            addMouseMotionListener(ma);

            InputMap input = getInputMap(WHEN_IN_FOCUSED_WINDOW);
            input.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
            input.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");
            
            ActionMap action = getActionMap();
            action.put("up", new MenuAction(-1));
            action.put("down", new MenuAction(1));

        }

        @Override
        public void invalidate() {
            menuBounds = null;
            super.invalidate();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            if (menuBounds == null) {
                menuBounds = new HashMap<>(menuItems.size());
                int width = 0;
                int height = 0;
                for (String item : menuItems) {
                    Dimension dim = painter.getPreferredSize(g2d, item);
                    width = Math.max(width, dim.width);
                    height = Math.max(height, dim.height);
                }

                int x = (getWidth() - (width + 10)) / 2;

                int totalHeight = (height + 10) * menuItems.size();
                totalHeight += 5 * (menuItems.size() - 1);

                int y = (getHeight() - totalHeight) / 2;

                for (String item : menuItems) {
                    menuBounds.put(item, new Rectangle(x, y, width + 10, height + 10));
                    y += height + 10 + 5;
                }
            }
            for (String item : menuItems) {
                Rectangle bounds = menuBounds.get(item);
                boolean isSelected = item.equals(selectMenuItem);
                boolean isFocused = item.equals(focusedItem);
                painter.paint(g2d, item, bounds, isSelected, isFocused);
            }
            g2d.dispose();
        }

        /**MenuAction*/
        public class MenuAction extends AbstractAction {
            private final int delta;

            public MenuAction(int delta) {
                this.delta = delta;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                int index = menuItems.indexOf(selectMenuItem);
                if (index < 0) {
                    selectMenuItem = menuItems.get(0);
                }
                index += delta;
                if (index < 0) {
                    selectMenuItem = menuItems.get(menuItems.size() - 1);
                } else if (index >= menuItems.size()) {
                    selectMenuItem = menuItems.get(0);
                } else {
                    selectMenuItem = menuItems.get(index);
                }
                repaint();
            }
        }
    }

    /**SimpleMenuItemPainter + Interface MenuItemPainter*/
    public class SimpleMenuItemPainter implements MenuItemPainter {
        public Dimension getPreferredSize(Graphics2D g2d, String text) {
            return g2d.getFontMetrics().getStringBounds(text, g2d).getBounds().getSize();
        }

        @Override
        public void paint(Graphics2D g2d, String text, Rectangle bounds, boolean isSelected, boolean isFocused) {
            FontMetrics fm = g2d.getFontMetrics();
            if (isSelected) {
                paintBackground(g2d, bounds, Color.BLUE, Color.WHITE);
            } else if (isFocused) {
                paintBackground(g2d, bounds, Color.MAGENTA, Color.BLACK);
            } else {
                paintBackground(g2d, bounds, Color.DARK_GRAY, Color.LIGHT_GRAY);
            }
            int x = bounds.x + ((bounds.width - fm.stringWidth(text)) / 2);
            int y = bounds.y + ((bounds.height - fm.getHeight()) / 2) + fm.getAscent();
            g2d.setColor(isSelected ? Color.WHITE : Color.LIGHT_GRAY);
            g2d.drawString(text, x, y);
        }

        protected void paintBackground(Graphics2D g2d, Rectangle bounds, Color background, Color foreground) {
            g2d.setColor(background);
            g2d.fill(bounds);
            g2d.setColor(foreground);
            g2d.draw(bounds);
        }
    }
    public interface MenuItemPainter {
        public void paint(Graphics2D g2d, String text, Rectangle bounds, boolean isSelected, boolean isFocused);

        public Dimension getPreferredSize(Graphics2D g2d, String text);
    }
}