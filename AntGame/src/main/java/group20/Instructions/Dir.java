/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.Instructions;


/**
 *  
 * @author owner
 */
public enum Dir {
    ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);

    /**
     * name values of directions
     */
    public static final Dir values[] = values();
    int val;
    Dir(int val){
        this.val = val;
    }
    int val(){
        return val;
    }
}
