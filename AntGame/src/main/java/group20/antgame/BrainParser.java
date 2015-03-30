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

    public static enum instructionType {

        Move("Move ([0-9]*) ([0-9]*)"),
        PickUp("pickUp ([0-9]*) ([0-9]*)"),
        Drop("Drop ([0-9]*)"),
        Turn("Turn (Left|Right) ([0-9]*)"),
        Flip("Flip ([0-9]*) ([0-9]*) ([0-9]*)"),
        Mark("Mark ([0-9]*) ([0-9]*)"),
        Unmark("Unmark ([0-9]*) ([0-9]*)");

        public final String pattern;

        private instructionType(String pattern) {
            this.pattern = pattern;
        }

    }

    /**
     *
     * @param brain the text/string of the brain file
     * @return the fully initialised brain Instruction[]
     * @throws InvalidBrainSyntaxException if brain not correct syntax
     */
    public Instruction[] parseBrain(String brain) throws InvalidBrainSyntaxException {
        
        //instructions to be returned
        ArrayList<Instruction> instructions = new ArrayList<>();
        
        //stores all of the 
        StringBuffer PatternsBuffer = new StringBuffer();

        for (instructionType type : instructionType.values()) {
            PatternsBuffer.append(String.format("|(?<%s>%s)", type.name(), type.pattern));
        }
        Pattern tokenPatterns = Pattern.compile(new String(PatternsBuffer.substring(1)));
        
        Matcher m = tokenPatterns.matcher(brain);
        
        
        while(m.find()){
            if (m.group(instructionType.Move.name())!= null){
                String contents = m.group();
                String regex = instructionType.Move.pattern;
                
                Matcher m2 = Pattern.compile(regex).matcher(contents);
                
                while (m2.find()){
                    String s = m2.group(1);
                    String s2 = m2.group(2);
                    int st1 = Integer.parseInt(s);
                    int st2 = Integer.parseInt(s2);
                    instructions.add(new Move(st1, st2));
                            
                }   
                
            }else if (m.group(instructionType.PickUp.name())!=null){
                String contents = m.group();
                String regex = instructionType.PickUp.pattern;
                
                Matcher m2 = Pattern.compile(regex).matcher(contents);
                
                while (m2.find()){
                    String s = m2.group(1);
                    String s2 = m2.group(2);
                    int st1 = Integer.parseInt(s);
                    int st2 = Integer.parseInt(s2);
                    instructions.add(new PickUp(st1, st2));
                            
                }
            }else if(m.group(instructionType.Drop.name())!=null){
                
                String contents = m.group();
                String regex = instructionType.PickUp.pattern;
                
                Matcher m2 = Pattern.compile(regex).matcher(contents);
                
                while (m2.find()){
                    String s = m2.group(1);
                    int st1 = Integer.parseInt(s);
                    instructions.add(new Drop(st1));        
                }
            }else if(m.group(instructionType.Turn.name())!=null){
                
                String contents = m.group();
                String regex = instructionType.PickUp.pattern;
                
                Matcher m2 = Pattern.compile(regex).matcher(contents);
                
                while (m2.find()){
                    String s = m2.group(1);
                    String s2 = m2.group(2);
                    int st1 = Integer.parseInt(s);
                    int st2 = Integer.parseInt(s2);
                    instructions.add(new PickUp(st1, st2));
                    
                    switch (m2.group(1)){
                        case "Left":
                            instructions.add(new Turn(Turn.LeftOrRight.LEFT, st2));
                            break;
                        case  "Right":
                            instructions.add(new Turn(Turn.LeftOrRight.RIGHT, st2));
                        
                    }
                            
                }
                
            }
        }
       
         

//        if (PickUp.matcher(brain).find()) {
//            String s = PickUp.matcher(brain).group(1);
//            int st1 = Integer.parseInt(s);
//            String s2 = PickUp.matcher(brain).group(2);
//            int st2 = Integer.parseInt(s2);
//            instructions.add(new group20.Instructions.PickUp(st1, st2));
//        }
//
//        if (Drop.matcher(brain).find()) {
//            String s = Drop.matcher(brain).group(1);
//            int st1 = Integer.parseInt(s);
//            instructions.add(new group20.Instructions.Drop(st1));
//        }
//
//        if (Turn.matcher(brain).find()) {
//
//            String s2 = Turn.matcher(brain).group(2);
//            int st = Integer.parseInt(s2);
//
//            switch (Turn.matcher(brain).group(1)) {
//                case "Left":
//                    instructions.add(new group20.Instructions.Turn(group20.Instructions.Turn.LeftOrRight.LEFT, st));
//                case "Right":
//                    instructions.add(new group20.Instructions.Turn(group20.Instructions.Turn.LeftOrRight.RIGHT, st));
//            }
//        }
//
//        if (Flip.matcher(brain).find()) {
//            String s = Flip.matcher(brain).group(2);
//            String s2 = Flip.matcher(brain).group(3);
//            String s3 = Flip.matcher(brain).group(4);
//            int p = Integer.parseInt(s);
//            int st1 = Integer.parseInt(s2);
//            int st2 = Integer.parseInt(s3);
//            instructions.add(new group20.Instructions.Flip(p, st1, st2));
//        }
//
//        if (Mark.matcher(brain).find()) {
//
//            String s = Mark.matcher(brain).group(1);
//            String s2 = Mark.matcher(brain).group(2);
//            int st = Integer.parseInt(s2);
//            int i = Integer.parseInt(s);
//            switch (i) {
//                case 0:
//                    instructions.add(new group20.Instructions.Mark(group20.Instructions.Mark.Marker.MARKER0, st));
//                case 1:
//                    instructions.add(new group20.Instructions.Mark(group20.Instructions.Mark.Marker.MARKER1, st));
//                case 2:
//                    instructions.add(new group20.Instructions.Mark(group20.Instructions.Mark.Marker.MARKER2, st));
//                case 3:
//                    instructions.add(new group20.Instructions.Mark(group20.Instructions.Mark.Marker.MARKER3, st));
//                case 4:
//                    instructions.add(new group20.Instructions.Mark(group20.Instructions.Mark.Marker.MARKER4, st));
//                case 5:
//                    instructions.add(new group20.Instructions.Mark(group20.Instructions.Mark.Marker.MARKER5, st));
//            }
//
//        }
//
//        if (Unmark.matcher(brain).find()) {
//            String s = Mark.matcher(brain).group(1);
//            String s2 = Mark.matcher(brain).group(2);
//            int st = Integer.parseInt(s2);
//            int i = Integer.parseInt(s);
//            switch (i) {
//                case 0:
//                    instructions.add(new group20.Instructions.Unmark(group20.Instructions.Mark.Marker.MARKER0, st));
//                case 1:
//                    instructions.add(new group20.Instructions.Unmark(group20.Instructions.Mark.Marker.MARKER1, st));
//                case 2:
//                    instructions.add(new group20.Instructions.Unmark(group20.Instructions.Mark.Marker.MARKER2, st));
//                case 3:
//                    instructions.add(new group20.Instructions.Unmark(group20.Instructions.Mark.Marker.MARKER3, st));
//                case 4:
//                    instructions.add(new group20.Instructions.Unmark(group20.Instructions.Mark.Marker.MARKER4, st));
//                case 5:
//                    instructions.add(new group20.Instructions.Unmark(group20.Instructions.Mark.Marker.MARKER5, st));
//            }
//        } else {
//            throw new InvalidBrainSyntaxException("Invalid AntBrain Syntax!");
//        }

        Instruction[] instructionArray = new Instruction[instructions.size()];

        return instructions.toArray(instructionArray);
    }

    private static class InvalidBrainSyntaxException extends Exception {

        public InvalidBrainSyntaxException(String str) {
            super(str);
        }
    }
}
