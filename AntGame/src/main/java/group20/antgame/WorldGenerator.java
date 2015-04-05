package group20.antgame;


import java.util.Random;

/**
 *
 * @author jw468
 */
public class WorldGenerator {

    char[][] map = new char[150][150];

    public WorldGenerator() {

        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
                map[i][j] = '.';
            }
        }

        char team;

        Perimeter();

        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                team = '+';
                AntHills(team);
            } else {
                team = '-';
                AntHills(team);
            }
        }
        Food();
        Stones();
        
        CleanUp();
    }

    public void Perimeter() {
        for (int i = 0; i < 150; i++) {
            map[0][i] = '#';
            map[149][i] = '#';
            map[i][0] = '#';
            map[i][149] = '#';
        }
    }

    public void AntHills(char team) {
        Random rnd = new Random();
        int x = rnd.nextInt(133) + 5;
        int y = rnd.nextInt(133) + 2;
        System.out.println(x);
        System.out.println(y);

        if (y % 2 == 0) {
            EvenHill(team, x, y);
        } else {
            OddHill(team, x, y);
        }
    }

    public void EvenHill(char team, int x, int y) {

        boolean free = EvenHillCheck(x, y);
        if (free) {
            for (int i = 0; i < 7; i++) {
                map[x + i][y] = team;
                map[x + i][y - 1] = 'x';
                map[x - 1][y] = 'x';
                map[x + 7][y] = 'x';
            }
            for (int i = 0; i < 8; i++) {
                map[x - 1 + i][y + 1] = team;
                map[x - 1 - 1][y + 1] = 'x';
                map[x + 7][y + 1] = 'x';
            }
            for (int i = 0; i < 9; i++) {
                map[x - 1 + i][y + 2] = team;
                map[x - 2][y + 2] = 'x';
                map[x + 8][y + 2] = 'x';
            }
            for (int i = 0; i < 10; i++) {
                map[x - 2 + i][y + 3] = team;
                map[x - 3][y + 3] = 'x';
                map[x + 8][y + 3] = 'x';
            }
            for (int i = 0; i < 11; i++) {
                map[x - 2 + i][y + 4] = team;
                map[x - 3][y + 4] = 'x';
                map[x + 9][y + 4] = 'x';
            }
            for (int i = 0; i < 12; i++) {
                map[x - 3 + i][y + 5] = team;
                map[x - 4][y + 5] = 'x';
                map[x + 9][y + 5] = 'x';
            }
            for (int i = 0; i < 13; i++) {
                map[x - 3 + i][y + 6] = team;
                map[x - 4][y + 6] = 'x';
                map[x + 10][y + 6] = 'x';
            }
            for (int i = 0; i < 12; i++) {
                map[x - 3 + i][y + 7] = team;
                map[x - 4][y + 7] = 'x';
                map[x + 9][y + 7] = 'x';
            }
            for (int i = 0; i < 11; i++) {
                map[x - 2 + i][y + 8] = team;
                map[x - 3][y + 8] = 'x';
                map[x + 9][y + 8] = 'x';
            }
            for (int i = 0; i < 10; i++) {
                map[x - 2 + i][y + 9] = team;
                map[x - 3][y + 9] = 'x';
                map[x + 8][y + 9] = 'x';
            }
            for (int i = 0; i < 9; i++) {
                map[x - 1 + i][y + 10] = team;
                map[x - 2][y + 10] = 'x';
                map[x + 8][y + 10] = 'x';
            }
            for (int i = 0; i < 8; i++) {
                map[x - 1 + i][y + 11] = team;
                map[x - 2][y + 11] = 'x';
                map[x + 7][y + 11] = 'x';
            }
            for (int i = 0; i < 7; i++) {
                map[x + i][y + 12] = team;
                map[x - 1][y + 12] = 'x';
                map[x + 7][y + 12] = 'x';
                map[x + i][y + 13] = 'x';

            }
        } else {
            Random rnd = new Random();
            int x2 = rnd.nextInt(133) + 5;
            int y2 = rnd.nextInt(133) + 2;
            EvenHill(team, x2, y2);
        }

    }

    public boolean EvenHillCheck(int x, int y) {
        boolean free = true;
        for (int i = 0; i < 7; i++) {
            if (map[x + i][y] != '.') {
                free = false;
            }
        }
        for (int i = 0; i < 8; i++) {
            if (map[x - 1 + i][y + 1] != '.') {
                free = false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (map[x - 1 + i][y + 2] != '.') {
                free = false;
            }
        }
        for (int i = 0; i < 10; i++) {
            if (map[x - 2 + i][y + 3] != '.') {
                free = false;
            }
        }
        for (int i = 0; i < 11; i++) {
            if (map[x - 2 + i][y + 4] != '.') {
                free = false;
            }
        }
        for (int i = 0; i < 12; i++) {
            if (map[x - 3 + i][y + 5] != '.') {
                free = false;
            }
        }
        for (int i = 0; i < 13; i++) {
            if (map[x - 3 + i][y + 6] != '.') {
                free = false;
            }
        }
        for (int i = 0; i < 12; i++) {
            if (map[x - 3 + i][y + 7] != '.') {
                free = false;
            }
        }
        for (int i = 0; i < 11; i++) {
            if (map[x - 2 + i][y + 8] != '.') {
                free = false;
            }
        }
        for (int i = 0; i < 10; i++) {
            if (map[x - 2 + i][y + 9] != '.') {
                free = false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (map[x - 1 + i][y + 10] != '.') {
                free = false;
            }
        }
        for (int i = 0; i < 8; i++) {
            if (map[x - 1 + i][y + 11] != '.') {
                free = false;
            }
        }
        for (int i = 0; i < 7; i++) {
            if (map[x + i][y + 12] != '.') {
                free = false;
            }
        }
        return free;
    }

    private void OddHill(char team, int x, int y) {

        boolean free = OddHillCheck(x, y);

        if (free) {
            for (int i = 0; i < 7; i++) {
                map[x + i][y] = team;
                map[x + i][y - 1] = 'x';
                map[x - 1][y] = 'x';
                map[x + 7][y] = 'x';
            }
            for (int i = 0; i < 8; i++) {
                map[x + i][y + 1] = team;
                map[x - 1][y + 1] = 'x';
                map[x + 8][y + 1] = 'x';
            }
            for (int i = 0; i < 9; i++) {
                map[x - 1 + i][y + 2] = team;
                map[x - 2][y + 2] = 'x';
                map[x + 8][y + 2] = 'x';
            }
            for (int i = 0; i < 10; i++) {
                map[x - 1 + i][y + 3] = team;
                map[x - 2][y + 3] = 'x';
                map[x + 9][y + 3] = 'x';
            }
            for (int i = 0; i < 11; i++) {
                map[x - 2 + i][y + 4] = team;
                map[x - 3][y + 4] = 'x';
                map[x + 9][y + 4] = 'x';
            }
            for (int i = 0; i < 12; i++) {
                map[x - 2 + i][y + 5] = team;
                map[x - 3][y + 5] = 'x';
                map[x + 10][y + 5] = 'x';
            }
            for (int i = 0; i < 13; i++) {
                map[x - 3 + i][y + 6] = team;
                map[x - 4][y + 6] = 'x';
                map[x + 10][y + 6] = 'x';
            }
            for (int i = 0; i < 12; i++) {
                map[x - 2 + i][y + 7] = team;
                map[x - 3][y + 7] = 'x';
                map[x + 10][y + 7] = 'x';
            }
            for (int i = 0; i < 11; i++) {
                map[x - 2 + i][y + 8] = team;
                map[x - 3][y + 8] = 'x';
                map[x + 9][y + 8] = 'x';
            }
            for (int i = 0; i < 10; i++) {
                map[x - 1 + i][y + 9] = team;
                map[x - 2][y + 9] = 'x';
                map[x + 9][y + 9] = 'x';
            }
            for (int i = 0; i < 9; i++) {
                map[x - 1 + i][y + 10] = team;
                map[x - 2][y + 10] = 'x';
                map[x + 8][y + 10] = 'x';
            }
            for (int i = 0; i < 8; i++) {
                map[x + i][y + 11] = team;
                map[x - 1][y + 11] = 'x';
                map[x + 8][y + 11] = 'x';
            }
            for (int i = 0; i < 7; i++) {
                map[x + i][y + 12] = team;
                map[x - 1][y + 12] = 'x';
                map[x + 7][y + 12] = 'x';
                map[x + i][y + 13] = 'x';
            }
        } else {
            Random rnd = new Random();
            int x2 = rnd.nextInt(133) + 5;
            int y2 = rnd.nextInt(133) + 2;
            OddHill(team, x2, y2);
        }
    }

    private boolean OddHillCheck(int x, int y) {
        boolean free = true;

        for (int i = 0; i < 7; i++) {
            if (map[x + i][y] != '.') {
                free = false;
            }
        }
        for (int i = 0; i < 8; i++) {
            if (map[x + i][y + 1] != '.') {
                free = false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (map[x - 1 + i][y + 2] != '.') {
                free = false;
            }
        }
        for (int i = 0; i < 10; i++) {
            if (map[x - 1 + i][y + 3] != '.') {
                free = false;
            }
        }
        for (int i = 0; i < 11; i++) {
            if (map[x - 2 + i][y + 4] != '.') {
                free = false;
            }
        }
        for (int i = 0; i < 12; i++) {
            if (map[x - 2 + i][y + 5] != '.') {
                free = false;
            }
        }
        for (int i = 0; i < 13; i++) {
            if (map[x - 3 + i][y + 6] != '.') {
                free = false;
            }
        }
        for (int i = 0; i < 12; i++) {
            if (map[x - 2 + i][y + 7] != '.') {
                free = false;
            }
        }
        for (int i = 0; i < 11; i++) {
            if (map[x - 2 + i][y + 8] != '.') {
                free = false;
            }
        }
        for (int i = 0; i < 10; i++) {
            if (map[x - 1 + i][y + 9] != '.') {
                free = false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (map[x - 1 + i][y + 10] != '.') {
                free = false;
            }
        }
        for (int i = 0; i < 8; i++) {
            if (map[x + i][y + 11] != '.') {
                free = false;
            }
        }
        for (int i = 0; i < 7; i++) {
            if (map[x + i][y + 12] != '.') {
                free = false;
            }
        }

        return free;
    }

    public void Stones() {
        Random rnd = new Random();
        for (int i = 0; i < 14; i = i) {
            int x = rnd.nextInt(146) + 2;
            int y = rnd.nextInt(146) + 2;

            if (map[x][y] != '.') {
            } else {
                map[x][y] = '#';
                map[x][y + 1] = 'x';
                map[x][y - 1] = 'x';
                map[x - 1][y] = 'x';
                map[x + 1][y] = 'x';
                map[x - 1][y - 1] = 'x';
                map[x - 1][y + 1] = 'x';
                map[x + 1][y - 1] = 'x';
                map[x + 1][y + 1] = 'x';
                i++;
            }

        }
    }

    public void Food() {
        Random rnd = new Random();

        for (int j = 0; j < 11; j = j) {
            int x = rnd.nextInt(139) + 2;
            int y = rnd.nextInt(142) + 2;
            int z = rnd.nextInt(4);

            boolean free = true;


            if (y % 2 == 0) {
                if (z == 0) {
                    for (int i = 0; i < 5; i++) {
                        if (map[x + i][y] != '.') {
                            free = false;
                        }
                    }
                    for (int i = 0; i < 5; i++) {
                        if (map[x - 1 + i][y + 1] != '.') {
                            free = false;
                        }
                    }
                    for (int i = 0; i < 5; i++) {
                        if (map[x - 1 + i][y + 2] != '.') {
                            free = false;
                        }
                    }
                    for (int i = 0; i < 5; i++) {
                        if (map[x - 2 + i][y + 3] != '.') {
                            free = false;
                        }
                    }
                    for (int i = 0; i < 5; i++) {
                        if (map[x - 2 + i][y + 4] != '.') {
                            free = false;
                        }
                    }
                    if (free) {
                        for (int i = 0; i < 5; i++) {
                            map[x + i][y] = '5';
                            map[x + i][y - 1] = 'x';
                            map[x - 1][y] = 'x';
                            map[x + 5][y] = 'x';
                        }
                        for (int i = 0; i < 5; i++) {
                            map[x - 1 + i][y + 1] = '5';
                            map[x - 2][y + 1] = 'x';
                            map[x + 4][y + 1] = 'x';
                        }
                        for (int i = 0; i < 5; i++) {
                            map[x - 1 + i][y + 2] = '5';
                            map[x - 2][y + 2] = 'x';
                            map[x + 4][y + 2] = 'x';
                        }
                        for (int i = 0; i < 5; i++) {
                            map[x - 2 + i][y + 3] = '5';
                            map[x - 3][y + 3] = 'x';
                            map[x + 3][y + 3] = 'x';
                        }
                        for (int i = 0; i < 5; i++) {
                            map[x - 2 + i][y + 4] = '5';
                            map[x - 3][y + 4] = 'x';
                            map[x + 3][y + 4] = 'x';
                            map[x - 2 + i][y + 5] = 'x';
                        }
                        j++;
                    }
                } else {
                    for (int i = 0; i < 5; i++) {
                        if (map[x + i][y] != '.') {
                            free = false;
                        }
                    }
                    for (int i = 0; i < 5; i++) {
                        if (map[x + i][y + 1] != '.') {
                            free = false;
                        }
                    }
                    for (int i = 0; i < 5; i++) {
                        if (map[x + 1 + i][y + 2] != '.') {
                            free = false;
                        }
                    }
                    for (int i = 0; i < 5; i++) {
                        if (map[x + 1 + i][y + 3] != '.') {
                            free = false;
                        }
                    }
                    for (int i = 0; i < 5; i++) {
                        if (map[x + 2 + i][y + 4] != '.') {
                            free = false;
                        }
                    }
                    if (free) {
                        for (int i = 0; i < 5; i++) {
                            map[x + i][y] = '5';
                            map[x + i][y - 1] = 'x';
                            map[x - 1][y] = 'x';
                            map[x + 5][y] = 'x';
                        }
                        for (int i = 0; i < 5; i++) {
                            map[x + i][y + 1] = '5';
                            map[x - 1][y + 1] = 'x';
                            map[x + 5][y + 1] = 'x';
                        }
                        for (int i = 0; i < 5; i++) {
                            map[x + 1 + i][y + 2] = '5';
                            map[x][y + 2] = 'x';
                            map[x + 6][y + 2] = 'x';
                        }
                        for (int i = 0; i < 5; i++) {
                            map[x + 1 + i][y + 3] = '5';
                            map[x][y + 3] = 'x';
                            map[x + 6][y + 3] = 'x';
                        }
                        for (int i = 0; i < 5; i++) {
                            map[x + 2 + i][y + 4] = '5';
                            map[x + 1][y + 4] = 'x';
                            map[x + 7][y + 4] = 'x';
                            map[x + 2 + i][y + 5] = 'x';
                        }
                        j++;
                    }
                }
            } else {
                if (z == 0) {
                    for (int i = 0; i < 5; i++) {
                        if (map[x + i][y] != '.') {
                            free = false;
                        }
                    }
                    for (int i = 0; i < 5; i++) {
                        if (map[x + 1 + i][y + 1] != '.') {
                            free = false;
                        }
                    }
                    for (int i = 0; i < 5; i++) {
                        if (map[x + 1 + i][y + 2] != '.') {
                            free = false;
                        }
                    }
                    for (int i = 0; i < 5; i++) {
                        if (map[x + 2 + i][y + 3] != '.') {
                            free = false;
                        }
                    }
                    for (int i = 0; i < 5; i++) {
                        if (map[x + 2 + i][y + 4] != '.') {
                            free = false;
                        }
                    }
                    if (free) {
                        for (int i = 0; i < 5; i++) {
                            map[x + i][y] = '5';
                            map[x + i][y - 1] = 'x';
                            map[x - 1][y] = 'x';
                            map[x + 5][y] = 'x';
                        }
                        for (int i = 0; i < 5; i++) {
                            map[x + 1 + i][y + 1] = '5';
                            map[x][y + 1] = 'x';
                            map[x + 6][y + 1] = 'x';
                        }
                        for (int i = 0; i < 5; i++) {
                            map[x + 1 + i][y + 2] = '5';
                            map[x][y + 2] = 'x';
                            map[x + 6][y + 2] = 'x';
                        }
                        for (int i = 0; i < 5; i++) {
                            map[x + 2 + i][y + 3] = '5';
                            map[x + 1][y + 3] = 'x';
                            map[x + 7][y + 3] = 'x';
                        }
                        for (int i = 0; i < 5; i++) {
                            map[x + 2 + i][y + 4] = '5';
                            map[x + 1][y + 4] = 'x';
                            map[x + 7][y + 4] = 'x';
                            map[x + 2 + i][y + 5] = 'x';
                        }
                        j++;
                    }
                } else {
                    for (int i = 0; i < 5; i++) {
                        if (map[x + i][y] != '.') {
                            free = false;
                        }
                    }
                    for (int i = 0; i < 5; i++) {
                        if (map[x + i][y + 1] != '.') {
                            free = false;
                        }
                    }
                    for (int i = 0; i < 5; i++) {
                        if (map[x - 1 + i][y + 2] != '.') {
                            free = false;
                        }
                    }
                    for (int i = 0; i < 5; i++) {
                        if (map[x - 1 + i][y + 3] != '.') {
                            free = false;
                        }
                    }
                    for (int i = 0; i < 5; i++) {
                        if (map[x - 2 + i][y + 4] != '.') {
                            free = false;
                        }
                    }
                    if (free) {
                        for (int i = 0; i < 5; i++) {
                            map[x + i][y] = '5';
                            map[x + i][y - 1] = 'x';
                            map[x - 1][y] = 'x';
                            map[x + 5][y] = 'x';
                        }
                        for (int i = 0; i < 5; i++) {
                            map[x + i][y + 1] = '5';
                            map[x - 1][y + 1] = 'x';
                            map[x + 5][y + 1] = 'x';
                        }
                        for (int i = 0; i < 5; i++) {
                            map[x - 1 + i][y + 2] = '5';
                            map[x - 2][y + 2] = 'x';
                            map[x + 4][y + 2] = 'x';
                        }
                        for (int i = 0; i < 5; i++) {
                            map[x - 1 + i][y + 3] = '5';
                            map[x - 2][y + 3] = 'x';
                            map[x + 4][y + 3] = 'x';
                        }
                        for (int i = 0; i < 5; i++) {
                            map[x - 2 + i][y + 4] = '5';
                            map[x - 3][y + 4] = 'x';
                            map[x + 3][y + 4] = 'x';
                            map[x - 2 + i][y + 5] = 'x';
                        }
                        j++;
                    }
                }
            }
        }
    }
    
    public void CleanUp() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[j][i] == 'x') {
                    map[j][i] = '.';
                }
            }
        }
    }
    
    public char[][] getGeneratedCharMap(){
        return map;
    }
}
