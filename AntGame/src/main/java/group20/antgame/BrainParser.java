/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.antgame;

import group20.Instructions.*;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.*;

/**
 *
 * @author owner
 */
public class BrainParser {

    private String s;
    private Pattern Move = Pattern.compile("Move ([0-9]*) ([0-9]*) ");
    private Pattern PickUp = Pattern.compile("PickUp ([0-9]*) ([0-9]*)");
    private Pattern Drop = Pattern.compile("Drop ([0-9]*)");
    private Pattern Turn = Pattern.compile("Turn (Left|Right) ([0-9]*)");
    private Pattern Flip = Pattern.compile("Flip ([0-9]*) ([0-9]*) ([0-9]*)");
    private Pattern Mark = Pattern.compile("Mark ([0-9]*) ([0-9]*)");
    private Pattern Unmark = Pattern.compile("Unmark ([0-9]*) ([0-9]*)");

    /**
     *
     * @param brain the text/string of the brain file
     * @return the fully initialised brain Instruction[]
     * @throws InvalidBrainSyntaxException if brain not correct syntax
     */

    public BrainParser(String s) {
        this.s = s;
    }

//    public String getInstructionSetFromFile(String fileName) throws FileNotFoundException, IOException {
//        BufferedReader br = new BufferedReader(new FileReader(fileName));
//        StringBuilder sb = new StringBuilder();
//        String line = br.readLine();
//
//        while (line != null) {
//            sb.append(line);
//            sb.append(System.lineSeparator());
//            line = br.readLine();
//        }
//        String allText = sb.toString();
//        System.out.println(allText);
//        return allText;
//    }
    
    

    public Instruction[] parseBrain(String brain) throws InvalidBrainSyntaxException {
        
  
        
        ArrayList<Instruction> instructions = new ArrayList<>();
        
        if (Move.matcher(brain).find()){
            String s = Move.matcher(brain).group(1);
            int st1 = Integer.parseInt(s);
            String s2 = Move.matcher(brain).group(2);
            int st2 = Integer.parseInt(s2);
            instructions.add(new group20.Instructions.Move(st1, st2));
        }
        
        else if(PickUp.matcher(brain).find()){
            String s = PickUp.matcher(brain).group(1);
            int st1 = Integer.parseInt(s);
            String s2 = PickUp.matcher(brain).group(2);
            int st2 = Integer.parseInt(s2);
            instructions.add(new group20.Instructions.PickUp(st1, st2));
        }
        
        else if(Drop.matcher(brain).find()){
            String s = Drop.matcher(brain).group(1);
            int st1 = Integer.parseInt(s);
            instructions.add(new group20.Instructions.Drop(st1));    
        }
       
        else if(Turn.matcher(brain).find()){
            
            String s2 = Turn.matcher(brain).group(2);
            int st = Integer.parseInt(s2);
            
            switch (Turn.matcher(brain).group(1)){
                case "Left": 
                    instructions.add(new group20.Instructions.Turn(group20.Instructions.Turn.LeftOrRight.LEFT, st));
                case "Right":
                    instructions.add(new group20.Instructions.Turn(group20.Instructions.Turn.LeftOrRight.RIGHT, st));      
            }
        }

        else if(Flip.matcher(brain).find()){
            String s= Flip.matcher(brain).group(1);
            String s2 = Flip.matcher(brain).group(2);
            String s3 = Flip.matcher(brain).group(2);
            int p = Integer.parseInt(s);
            int st1 = Integer.parseInt(s2);
            int st2 = Integer.parseInt(s3);
            instructions.add(new group20.Instructions.Flip(p, st1, st2));
        }
        
        else if(Mark.matcher(brain).find()){
            
            String s = Mark.matcher(brain).group(1);
            String s2 = Mark.matcher(brain).group(2);
            int st = Integer.parseInt(s2);
            int i = Integer.parseInt(s);
            switch (i){
                case 0: 
                    instructions.add(new group20.Instructions.Mark(group20.Instructions.Mark.Marker.MARKER0, st));
                case 1:
                    instructions.add(new group20.Instructions.Mark(group20.Instructions.Mark.Marker.MARKER1, st));
                case 2: 
                    instructions.add(new group20.Instructions.Mark(group20.Instructions.Mark.Marker.MARKER2, st));
                case 3:
                    instructions.add(new group20.Instructions.Mark(group20.Instructions.Mark.Marker.MARKER3, st));
                case 4:
                    instructions.add(new group20.Instructions.Mark(group20.Instructions.Mark.Marker.MARKER4, st));
                case 5:
                    instructions.add(new group20.Instructions.Mark(group20.Instructions.Mark.Marker.MARKER5, st));
            }
            
        }
        
        else if(Unmark.matcher(brain).find()){
            String s = Mark.matcher(brain).group(1);
            String s2 = Mark.matcher(brain).group(2);
            int st = Integer.parseInt(s2);
            int i = Integer.parseInt(s);
            switch (i){
                case 0: 
                    instructions.add(new group20.Instructions.Unmark(group20.Instructions.Mark.Marker.MARKER0, st));
                case 1:
                    instructions.add(new group20.Instructions.Unmark(group20.Instructions.Mark.Marker.MARKER1, st));
                case 2: 
                    instructions.add(new group20.Instructions.Unmark(group20.Instructions.Mark.Marker.MARKER2, st));
                case 3:
                    instructions.add(new group20.Instructions.Unmark(group20.Instructions.Mark.Marker.MARKER3, st));
                case 4:
                    instructions.add(new group20.Instructions.Unmark(group20.Instructions.Mark.Marker.MARKER4, st));
                case 5:
                    instructions.add(new group20.Instructions.Unmark(group20.Instructions.Mark.Marker.MARKER5, st));
            }     
        }
        
        else{
            throw new InvalidBrainSyntaxException("Invalid AntBrain Syntax!");
        }
        
        Instruction[] instructionArray = new Instruction[instructions.size()];
                
        return instructions.toArray(instructionArray);
    }

    private static class InvalidBrainSyntaxException extends Exception {

        public InvalidBrainSyntaxException(String str) {
            super(str);
        }
    }
}


