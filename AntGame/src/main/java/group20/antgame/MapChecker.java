/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.antgame;

import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jw468
 */
public class MapChecker {

    boolean finalcheck = false;
    //Counters
    int plushill;
    int minushill;
    int food;
    int rocks;
    int sizeX;
    int sizeY;
    char[][] map;
    boolean adjacentCorrect = true;
    boolean amounts = false;
    boolean[][] checked;
    //HillCheck
    boolean hillCorrect = true;
    static int counter;
    int hillX;
    int hillY;
    boolean even;
    //FoodCheck
    boolean foodCorrect = true;
    static int foodCounter;
    int foodX;
    int foodY;
    boolean foodEven;
    boolean foodLeft = false;

    public MapChecker(char[][] map) {
        this.map = map;
        checked = new boolean[map.length][map.length];
        plushill = 1;
        minushill = 1;
        food = 11;
        rocks = 14;

        sizeX = map.length;
        sizeY = map.length;

        Checker();
    }

    public void Checker() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (checked[j][i] != true) {
                    if (map[j][i] == '+' || map[j][i] == '-') {
                        hillX = j;
                        hillY = i;
                        even = (hillY % 2 == 0);
                        HillChecker(map, j, i, 0);
                    }
                    if (map[j][i] == '5') {
                        foodX = j;
                        foodY = i;
                        foodEven = (foodY % 2 == 0);
                        FoodChecker(j, i, 0);
                    }
                }
            }
        }

        AdjacentChecker();
        AmountsCheck();
        FinalCheck();
    }

    public boolean HillChecker(char[][] map, int posX, int posY, int counter2) {
        this.counter = counter2;
        char hillColour = map[hillX][hillY];


        if (counter <= 6) {

            char[] row;

            for (int i = 0; i < 7 + counter; i++) {
                row = new char[i + counter];
                if (map[posX + i][posY] == hillColour) {
                    checked[posX + i][posY] = true;
                } else {
                    hillCorrect = false;
                    return hillCorrect;
                }
            }

            counter++;
            if (even) {
                if (counter % 2 == 0) {
                    HillChecker(map, posX, posY + 1, counter);
                } else {
                    HillChecker(map, posX - 1, posY + 1, counter);
                }
            } else {
                if (counter % 2 == 0) {
                    HillChecker(map, posX - 1, posY + 1, counter);
                } else {
                    HillChecker(map, posX, posY + 1, counter);
                }
            }


        }

        if (counter == 7) {
            char[] row;
            posX++;

            for (int i = 0; i < 12; i++) {
                row = new char[i];
                if (map[posX + i][posY] == hillColour) {
                    checked[posX + i][posY] = true;
                } else {
                    hillCorrect = false;
                    return hillCorrect;
                }
            }
            counter++;
            if (even) {
                HillChecker(map, posX + 1, posY + 1, counter);
            } else {
                HillChecker(map, posX, posY + 1, counter);
            }

        }



        if (counter > 7 && counter < 13) {
            char[] row;

            for (int i = 0; i < 13 - counter + 6; i++) {
                row = new char[i + counter];
                if (map[posX + i][posY] == hillColour) {
                    checked[posX + i][posY] = true;
                } else {
                    hillCorrect = false;
                    return hillCorrect;
                }
            }

            counter++;

            if (even) {
                if (counter % 2 == 0) {
                    HillChecker(map, posX + 1, posY + 1, counter);
                } else {
                    HillChecker(map, posX, posY + 1, counter);
                }
            } else {
                if (counter % 2 == 0) {
                    HillChecker(map, posX, posY + 1, counter);
                } else {
                    HillChecker(map, posX + 1, posY + 1, counter);
                }
            }
        }

        return hillCorrect;
    }

    public boolean FoodChecker(int x, int y, int counter2) {
        foodCounter = counter2;
        foodLeft = (map[foodX][foodY + 2] == '5');

        if (foodCounter == 0) {
            for (int i = 0; i < 5; i++) {
                checked[x + i][y] = true;
                if (map[x + i][y] != '5') {
                    foodCorrect = false;
                    return foodCorrect;
                }
            }
            foodCounter++;
            if (foodLeft) {
                if (foodEven) {
                    FoodChecker(x - 1, y + 1, foodCounter);
                } else {
                    FoodChecker(x, y + 1, foodCounter);
                }
            } else {
                if (foodEven) {
                    FoodChecker(x, y + 1, foodCounter);
                } else {
                    FoodChecker(x + 1, y + 1, foodCounter);
                }
            }
        }

        if (foodCounter > 0 && foodCounter < 5) {
            for (int i = 0; i < 5; i++) {
                checked[x + i][y] = true;
                if (map[x + i][y] != '5') {
                    foodCorrect = false;
                    return foodCorrect;
                }
            }
            foodCounter++;
            if (foodLeft) {
                if (foodEven) {
                    if (foodCounter % 2 == 0) {
                        FoodChecker(x, y + 1, foodCounter);
                    } else {
                        FoodChecker(x - 1, y + 1, foodCounter);
                    }
                } else {
                    if (foodCounter % 2 == 0) {
                        FoodChecker(x - 1, y + 1, foodCounter);
                    } else {
                        FoodChecker(x, y + 1, foodCounter);
                    }
                }

            } else {
                if (foodEven) {
                    if (foodCounter % 2 == 0) {
                        FoodChecker(x + 1, y + 1, foodCounter);
                    } else {
                        FoodChecker(x, y + 1, foodCounter);
                    }
                } else {
                    if (foodCounter % 2 == 0) {
                        FoodChecker(x, y + 1, foodCounter);
                    } else {
                        FoodChecker(x + 1, y + 1, foodCounter);
                    }
                }
            }
        }


        return foodCorrect;
    }

    public boolean AdjacentChecker() {

        if (map.length == sizeX) {
        } else {
            adjacentCorrect = false;
            return adjacentCorrect;
        }
        for (int i = 1; i < sizeX - 1; i++) {
            for (int j = 1; j < sizeY - 1; j++) {
                if (map[i][j] != '.') {
                    if (map[i][j] == '#') {
                        boolean upleft = (map[i - 1][j - 1] != '.' && map[i - 1][j - 1] != '#');
                        boolean up = (map[i - 1][j] != '.' && map[i - 1][j] != '#');
                        boolean upright = (map[i - 1][j + 1] != '.' && map[i - 1][j + 1] != '#');
                        boolean left = (map[i][j - 1] != '.' && map[i][j - 1] != '#');
                        boolean right = (map[i][j + 1] != '.' && map[i][j + 1] != '#');
                        boolean downleft = (map[i + 1][j - 1] != '.' && map[i + 1][j - 1] != '#');
                        boolean down = (map[i + 1][j] != '.' && map[i + 1][j] != '#');
                        boolean downright = (map[i + 1][j + 1] != '.' && map[i + 1][j + 1] != '#');;

                        if (upleft || up || upright || left || right || downleft || down || downright) {
                            adjacentCorrect = false;
                            return adjacentCorrect;
                        }
                    }
                    if (map[i][j] == '5') {
                        boolean upleft = (map[i - 1][j - 1] != '.' && map[i - 1][j - 1] != '5');
                        boolean up = (map[i - 1][j] != '.' && map[i - 1][j] != '5');
                        boolean upright = (map[i - 1][j + 1] != '.' && map[i - 1][j + 1] != '5');
                        boolean left = (map[i][j - 1] != '.' && map[i][j - 1] != '5');
                        boolean right = (map[i][j + 1] != '.' && map[i][j + 1] != '5');
                        boolean downleft = (map[i + 1][j - 1] != '.' && map[i + 1][j - 1] != '5');
                        boolean down = (map[i + 1][j] != '.' && map[i + 1][j] != '5');
                        boolean downright = (map[i + 1][j + 1] != '.' && map[i + 1][j + 1] != '5');

                        if (upleft || up || upright || left || right || downleft || down || downright) {
                            adjacentCorrect = false;
                            return adjacentCorrect;
                        }
                    }

                    if (map[i][j] == '+' || map[i][j] == '-') {
                        char colour = map[i][j];
                        boolean upleft = (map[i - 1][j - 1] != '.' && map[i - 1][j - 1] != colour);
                        boolean up = (map[i - 1][j] != '.' && map[i - 1][j] != colour);
                        boolean upright = (map[i - 1][j + 1] != '.' && map[i - 1][j + 1] != colour);
                        boolean left = (map[i][j - 1] != '.' && map[i][j - 1] != colour);
                        boolean right = (map[i][j + 1] != '.' && map[i][j + 1] != colour);
                        boolean downleft = (map[i + 1][j - 1] != '.' && map[i + 1][j - 1] != colour);
                        boolean down = (map[i + 1][j] != '.' && map[i + 1][j] != colour);
                        boolean downright = (map[i + 1][j + 1] != '.' && map[i + 1][j + 1] != colour);

                        if (upleft || up || upright || left || right || downleft || down || downright) {
                            adjacentCorrect = false;
                            return adjacentCorrect;
                        }
                    }
                }
            }
        }
        return adjacentCorrect;
    }

    public boolean AmountsCheck() {
        int rocks1 = 0;
        int plushill1 = 0;
        int minushill1 = 0;
        int food1 = 0;

        for (int i = 1; i < sizeX - 1; i++) {
            for (int j = 1; j < sizeY - 1; j++) {
                if (map[i][j] == '#') {
                    rocks1++;
                }

                if (map[i][j] == '5') {
                    food1++;
                }

                if (map[i][j] == '+') {
                    plushill1++;
                }

                if (map[i][j] == '-') {
                    minushill1++;
                }
            }
        }
        if (plushill1 == 127) {
            plushill1 = 1;
        }
        if (minushill1 == 127) {
            minushill1 = 1;
        }
        if (food1 == 275) {
            food1 = 11;
        }


        if (rocks1 == 14 && plushill1 == 1 && minushill1 == 1 && food1 == 11) {
            amounts = true;
            return amounts;
        }
        return amounts;
    }

    public boolean FinalCheck() {

        if (amounts && foodCorrect && hillCorrect && adjacentCorrect) {
            finalcheck = true;
        }
        return finalcheck;
    }
}

