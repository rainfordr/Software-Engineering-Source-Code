/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.antgame;

import com.sun.org.apache.bcel.internal.generic.D2F;
import group20.Conditions.Condition;
import group20.Conditions.Foe;
import group20.Conditions.FoeHome;
import group20.Conditions.FoeMarker;
import group20.Conditions.FoeWithFood;
import group20.Conditions.Food;
import group20.Conditions.Friend;
import group20.Conditions.FriendWithFood;
import group20.Conditions.Home;
import group20.Conditions.Marked;
import group20.Conditions.Rock;
import group20.Instructions.*;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.*;

/**
 *
 * @author owner
 */
public class BrainParser {

    static String conditionsRegex = ("(Foe|FoeHome|FoeMarker|FoeWithFood|Food|Friend|FriendWithFood|Home|Marker(i)|Rock)");

    public static enum instructionType {

        //HERE, AHEAD, LEFT_AHEAD, RIGHT_AHEAD;
        Move("Move ([0-9]*) ([0-9]*)"),
        PickUp("pickUp ([0-9]*) ([0-9]*)"),
        Drop("Drop ([0-9]*)"),
        Turn("Turn (Left|Right) ([0-9]*)"),
        Flip("Flip ([0-9]*) ([0-9]*) ([0-9]*)"),
        Mark("Mark ([0-9]*) ([0-9]*)"),
        Unmark("Unmark ([0-9]*) ([0-9]*)"),
        Sense("Sense (Here|Ahead|LeftAhead|RightAhead) ([0-9]*) ([0-9*]) " + conditionsRegex);

        public final String pattern;

        private instructionType(String pattern) {
            this.pattern = pattern;
        }

    }

    public Instruction parseMarker(int markerNum, int stateNum) {

        Instruction returnedInstruction = null;

        switch (markerNum) {
            case 0:
                returnedInstruction = new Mark(group20.Instructions.Mark.Marker.MARKER0, stateNum);
                break;
            case 1:
                returnedInstruction = new Mark(group20.Instructions.Mark.Marker.MARKER1, stateNum);
                break;
            case 2:
                returnedInstruction = new Mark(group20.Instructions.Mark.Marker.MARKER2, stateNum);
                break;
            case 3:
                returnedInstruction = new Mark(group20.Instructions.Mark.Marker.MARKER3, stateNum);
                break;
            case 4:
                returnedInstruction = new Mark(group20.Instructions.Mark.Marker.MARKER4, stateNum);
                break;
            case 5:
                returnedInstruction = new Mark(group20.Instructions.Mark.Marker.MARKER5, stateNum);
                break;
        }

        return returnedInstruction;

    }

    public Condition parseCondition(String condition) {
        Condition con = null;

        switch (condition) {
            case "Foe":
                con = new Foe();
                break;
            case "FoeHome":
                con = new FoeHome();
                break;
            case "FoeMarker":
                con = new FoeMarker();
                break;
            case "FoeWithFood":
                con = new FoeWithFood();
                break;
            case "Food":
                con = new Food();
                break;
            case "Friend":
                con = new Friend();
                break;
            case "FriendWithFood":
                con = new FriendWithFood();
                break;
            case "Home":
                con = new Home();
                break;
            case "Marked":
                throw new UnsupportedOperationException("Not implemented yet");
            case "Rock":
                con = new Rock();

        }
        return con;
    }

    public Instruction parseUnmark(int markerNum, int stateNum) {

        Instruction returnedInstruction = null;

        switch (markerNum) {
            case 0:
                returnedInstruction = new Unmark(Mark.Marker.MARKER0, stateNum);
                break;
            case 1:
                returnedInstruction = new Unmark(Mark.Marker.MARKER1, stateNum);
                break;
            case 2:
                returnedInstruction = new Unmark(Mark.Marker.MARKER2, stateNum);
                break;
            case 3:
                returnedInstruction = new Unmark(Mark.Marker.MARKER3, stateNum);
                break;
            case 4:
                returnedInstruction = new Unmark(Mark.Marker.MARKER4, stateNum);
                break;
            case 5:
                returnedInstruction = new Unmark(Mark.Marker.MARKER5, stateNum);
                break;
        }

        return returnedInstruction;

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

        while (m.find()) {
            if (m.group(instructionType.Move.name()) != null) {
                String contents = m.group();
                String regex = instructionType.Move.pattern;

                Matcher m2 = Pattern.compile(regex).matcher(contents);

                while (m2.find()) {
                    String s = m2.group(1);
                    String s2 = m2.group(2);
                    int st1 = Integer.parseInt(s);
                    int st2 = Integer.parseInt(s2);
                    instructions.add(new Move(st1, st2));

                }

            } else if (m.group(instructionType.PickUp.name()) != null) {
                String contents = m.group();
                String regex = instructionType.PickUp.pattern;

                Matcher m2 = Pattern.compile(regex).matcher(contents);

                while (m2.find()) {
                    String s = m2.group(1);
                    String s2 = m2.group(2);
                    int st1 = Integer.parseInt(s);
                    int st2 = Integer.parseInt(s2);
                    instructions.add(new PickUp(st1, st2));

                }
            } else if (m.group(instructionType.Drop.name()) != null) {

                String contents = m.group();
                String regex = instructionType.Drop.pattern;

                Matcher m2 = Pattern.compile(regex).matcher(contents);

                while (m2.find()) {
                    String s = m2.group(1);
                    int st1 = Integer.parseInt(s);
                    instructions.add(new Drop(st1));
                }
            } else if (m.group(instructionType.Turn.name()) != null) {

                String contents = m.group();
                String regex = instructionType.Turn.pattern;

                Matcher m2 = Pattern.compile(regex).matcher(contents);

                while (m2.find()) {
                    String s = m2.group(1);
                    String s2 = m2.group(2);
                    int st2 = Integer.parseInt(s2);

                    switch (s) {
                        case "Left":
                            instructions.add(new Turn(Turn.LeftOrRight.LEFT, st2));
                            break;

                        case "Right":
                            instructions.add(new Turn(Turn.LeftOrRight.RIGHT, st2));

                    }

                }

            } else if (m.group(instructionType.Flip.name()) != null) {
                String contents = m.group();
                String regex = instructionType.Flip.pattern;

                Matcher m2 = Pattern.compile(regex).matcher(contents);

                while (m2.find()) {
                    String s = m2.group(1);
                    String s2 = m2.group(2);
                    String s3 = m2.group(3);
                    int p = Integer.parseInt(s);
                    int st1 = Integer.parseInt(s2);
                    int st2 = Integer.parseInt(s3);
                    instructions.add(new Flip(p, st1, st2));
                }

            } else if (m.group(instructionType.Mark.name()) != null) {
                String contents = m.group();
                String regex = instructionType.Mark.pattern;
                Matcher m2 = Pattern.compile(regex).matcher(contents);

                while (m2.find()) {
                    String s = m2.group(1);
                    String s2 = m2.group(2);
                    int st = Integer.parseInt(s2);
                    int i = Integer.parseInt(s);
                    instructions.add(parseMarker(i, st));

                }

            } else if (m.group(instructionType.Unmark.name()) != null) {
                String contents = m.group();
                String regex = instructionType.Unmark.pattern;
                Matcher m2 = Pattern.compile(regex).matcher(contents);
                while (m2.find()) {
                    String s = m2.group(1);
                    String s2 = m2.group(2);
                    int st = Integer.parseInt(s2);
                    int i = Integer.parseInt(s);
                    instructions.add(parseUnmark(i, st));

                }

            } else if (m.group(instructionType.Sense.name()) != null) {
                String contents = m.group();
                String regex = instructionType.Sense.pattern;
                Matcher m2 = Pattern.compile(regex).matcher(contents);

                while (m2.find()) {

                    String senseDir = m2.group(1);
                    String s = m2.group(2);
                    int st1 = Integer.parseInt(s);
                    String s2 = m2.group(3);
                    int st2 = Integer.parseInt(s2);
                    String cond = m2.group(4);
                    String markerNum = m2.group(5);
                    Condition con = parseCondition(cond);

                    //(Here|Ahead|LeftAhead|RightAhead)
                    switch (senseDir) {
                        case "Here":
                            instructions.add(new Sense(Sense.SenseDir.HERE, st1, st2, con));
                        case "Ahead":
                            instructions.add(new Sense(Sense.SenseDir.AHEAD, st1, st2, con));
                        case "LeftAhead":
                            instructions.add(new Sense(Sense.SenseDir.LEFT_AHEAD, st1, st2, con) );
                        case "RightAhead":
                            instructions.add(new Sense(Sense.SenseDir.RIGHT_AHEAD, st1, st2, con));
                    }
                }

            } else {
                throw new InvalidBrainSyntaxException("Invalid Antbrain Syntax!!");
            }

        }
        Instruction[] instructionArray = new Instruction[instructions.size()];

        return instructions.toArray(instructionArray);

    }

    public static class InvalidBrainSyntaxException extends Exception {

        public InvalidBrainSyntaxException(String str) {
            super(str);
        }
    }
}
