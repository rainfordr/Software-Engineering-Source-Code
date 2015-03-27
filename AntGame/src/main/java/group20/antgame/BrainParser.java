/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.antgame;

import group20.Instructions.Instruction;

/**
 *
 * @author owner
 */
public class BrainParser {
    
    /**
     *
     * @param brain the text/string of the brain file
     * @return the fully initialised brain Instruction[]
     * @throws InvalidBrainSyntaxException if brain not correct syntax
     */
    public Instruction[] parseBrain(String brain) throws InvalidBrainSyntaxException{
        return null;        
    }

    private static class InvalidBrainSyntaxException extends Exception {

        public InvalidBrainSyntaxException(String str) {
            super(str);
        }
    }
}
