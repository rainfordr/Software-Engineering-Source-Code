/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.antgame;

/**
 *
 * @author owner
 */
public class RandNumGen2 {
    private int s;
    public RandNumGen2(int seed){
        s = seed;
        for(int i = 0; i < 3; i++){
            s = ((s * 22695477) + 1);
        }
    }
    
    public int randInt(int n){
        s = ((s * 22695477) + 1);
        return ((s >>> 16) & (16383)) % (n);
    }
}
