/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.antgame;

import group20.Conditions.Condition;
import group20.Conditions.*;
import group20.Instructions.Instruction;
import group20.Instructions.*;
import group20.Instructions.Mark.Marker;
import static group20.Instructions.Mark.Marker.*;
import group20.Instructions.Sense;
import group20.Instructions.Sense.SenseDir;
import static group20.Instructions.Sense.SenseDir.*;
import group20.Instructions.Turn.LeftOrRight;
import static group20.Instructions.Turn.LeftOrRight.*;
import java.util.Arrays;

/**
 *
 * @author owner
 */
public class BrainParserRob{
    public Instruction[] parseBrain(String[] brainArray) throws BrainParser.InvalidBrainSyntaxException{
        if(brainArray.length > 10000){
            throw new BrainParser.InvalidBrainSyntaxException("Brains cannot be longer than 10000 lines");
        }
        Instruction[] instructionList = new Instruction[brainArray.length];
        int rowPtr = 0;
        for(String brainRow: brainArray){
            if(brainRow.length() < 6){
                throw new BrainParser.InvalidBrainSyntaxException("Malformed Instruction: " + brainRow);
            }
            String[] row = brainRow.split(" ");
            String token = row[0];
            if(isInstruction(token)){
                switch (token) {
                    case "Sense":
                        instructionList[rowPtr] = parseSense(row);
                        break;
                    case "Mark":
                        instructionList[rowPtr] = parseMark(row);
                        break;
                    case "Unmark":
                        instructionList[rowPtr] = parseUnmark(row);
                        break;
                    case "PickUp":
                        instructionList[rowPtr] = parsePickUp(row);
                        break;
                    case "Drop":
                        instructionList[rowPtr] = parseDrop(row);
                        break;
                    case "Turn":
                        instructionList[rowPtr] = parseTurn(row);
                        break;
                    case "Move":
                        instructionList[rowPtr] = parseMove(row);
                        break;
                    case "Flip":
                        instructionList[rowPtr] = parseFlip(row);
                        break;
                }
            }
            else{
                throw new BrainParser.InvalidBrainSyntaxException("First token must be an Instruction. was " + token);
            }
            rowPtr++;
        }
        return instructionList;
    }
    
    //Method to check if given token string is an Instruction.
    private boolean isInstruction(String token){
        return token.equals("Sense")
            ||token.equals("Mark")
            ||token.equals("Unmark")
            ||token.equals("PickUp")
            ||token.equals("Drop")
            ||token.equals("Turn")
            ||token.equals("Move")
            ||token.equals("Flip");
    }
    
    //Method to check if given token string is a condition.
    private boolean isCondition(String token){
        return token.equals("Friend")
            ||token.equals("Foe")
            ||token.equals("FriendWithFood")
            ||token.equals("FoeWithFood")
            ||token.equals("Food")            
            ||token.equals("Rock")
            ||token.equals("Marker")
            ||token.equals("FoeMarker")
            ||token.equals("Home")
            ||token.equals("FoeHome");
    }
    
    private Condition getCondition(String cond) throws BrainParser.InvalidBrainSyntaxException{
        Condition c;
        switch(cond){
            case "Friend":
                c = new Friend();
                break;
            case "Foe":
                c = new Foe();
                break;
            case "FriendWithFood":
                c = new FriendWithFood();
                break;
            case "FoeWithFood":
                c = new FoeWithFood();
                break;
            case "Food":
                c = new Food();
                break;
            case "Rock":
                c = new Rock();
                break;
            case "FoeMarker":
                c = new FoeMarker();
                break;
            case "Home":
                c = new Home();
                break;
            case "FoeHome":
                c = new FoeHome();
                break;
            default: throw new BrainParser.InvalidBrainSyntaxException("does not contain valid condition: " + cond);
        }
        return c;
    }
    
    private boolean isMarker(String token){
        return token.equals("Marker");
    }
    
    private Condition getMarker(String token){
        switch (token) {
            case "0":
                return MARKER0;
            case "1":
                return MARKER1;
            case "2":
                return MARKER2;
            case "3":
                return MARKER3;
            case "4":
                return MARKER4;
            default:
                return MARKER5;
        }
    }
    
    //Method to check if given token string is a Sense Direction.
    private boolean isSenseDir(String token){
        return token.equals("Here")
            ||token.equals("Ahead")
            ||token.equals("LeftAhead")
            ||token.equals("RightAhead");
    }
    private SenseDir GetSenseDir(String token){
        switch (token) {
            case "LeftAhead":
                return LEFT_AHEAD;
            case "RightAhead":
                return RIGHT_AHEAD;
            case "Ahead":
                return AHEAD;
            default:
                return HERE;
        }
    }
    
    //Method to check if given token is 1 to 6.
    private boolean isMarkNumber(String token){
        if(token.matches("[0-9]*")){
            int marker = Integer.parseInt(token);
            return marker >= 0 && marker <= 5;
        }
        return false;
    }
    
    //Method to check if given token is a positive number.
    private boolean isFlipNumber(String token){
        if(token.matches("[0-9]*")){
            int flip = Integer.parseInt(token);
            return flip > 0;
        }
        return false;
    }

    //Method to check if given token is LeftOrRight.
    private boolean isLeftOrRight(String token){
        return token.equals("Left") || token.equals("Right");
    }
    
    private LeftOrRight getLeftOrRight(String token){
        if (token.equals("Left")){
            return LEFT;
        }
        return RIGHT;
    }
    
    //Method to check if given token is number between 0 and 9999.
    private boolean isStateInt(String token){
        if(token.matches("[0-9]*")){
            int state = Integer.parseInt(token);
            return state >= 0 && state <= 9999;
        }
        return false;
    }
    
    private Instruction parseSense(String[] row) throws BrainParser.InvalidBrainSyntaxException{
        int elements = row.length;
        Condition c;
        SenseDir sd;
        int st1;
        int st2;
        if(elements >= 5 && isSenseDir(row[1]) && isStateInt(row[2]) && isStateInt(row[3]) && isCondition(row[4])){
            if(isMarker(row[4]) && (elements > 5) && isMarkNumber(row[5])){
                c = getMarker(row[5]);
            }
            else{
                c = getCondition(row[4]);
            }
            sd = GetSenseDir(row[1]);
            st1 = Integer.parseInt(row[2]);
            st2 = Integer.parseInt(row[3]);
            return new Sense(sd, st1, st2, c);
        }
        else{
            throw new BrainParser.InvalidBrainSyntaxException("Malformed instruction row: " + Arrays.toString(row));
        }
    }

    private Instruction parseMark(String[] row) throws BrainParser.InvalidBrainSyntaxException {
        int elements = row.length;
        int markNumber;
        int state;
        if(elements < 3 || !isMarkNumber(row[1]) || !isStateInt(row[2])){
            throw new BrainParser.InvalidBrainSyntaxException("Malformed instruction row: " + Arrays.toString(row));
        }
        markNumber = Integer.parseInt(row[1]);
        state = Integer.parseInt(row[2]);
        return new Mark(Marker.values()[markNumber], state);
    }

    private Instruction parseUnmark(String[] row) throws BrainParser.InvalidBrainSyntaxException {
        int elements = row.length;
        int markNumber;
        int state;
        if(elements < 3 || !isMarkNumber(row[1]) || !isStateInt(row[2])){
            throw new BrainParser.InvalidBrainSyntaxException("Malformed instruction row: " + Arrays.toString(row));
        }
        markNumber = Integer.parseInt(row[1]);
        state = Integer.parseInt(row[2]);
        return new Unmark(Marker.values()[markNumber], state);
    } 

    private Instruction parsePickUp(String[] row) throws BrainParser.InvalidBrainSyntaxException {
        int elements = row.length;
        int st1;
        int st2;
        if(elements < 3 || !isStateInt(row[1]) || !isStateInt(row[2])){
            throw new BrainParser.InvalidBrainSyntaxException("Malformed instruction row: " + Arrays.toString(row));
        }
        st1 = Integer.parseInt(row[1]);
        st2 = Integer.parseInt(row[2]);
        return new PickUp(st1, st2);

    }

    private Instruction parseDrop(String[] row) throws BrainParser.InvalidBrainSyntaxException {
        int elements = row.length;
        int state;
        if(elements < 2 || !isStateInt(row[1])){
            throw new BrainParser.InvalidBrainSyntaxException("Malformed instruction row: " + Arrays.toString(row));
        }
        state = Integer.parseInt(row[1]);

        return new Drop(state);
    }

    private Instruction parseTurn(String[] row) throws BrainParser.InvalidBrainSyntaxException {
        int elements = row.length;
        LeftOrRight lr;
        int state;
        if(elements < 3 || !isLeftOrRight(row[1]) || !isStateInt(row[2])){
            throw new BrainParser.InvalidBrainSyntaxException("Malformed instruction row: " + Arrays.toString(row));
        }
        lr = getLeftOrRight(row[1]);
        state = Integer.parseInt(row[2]);

        return new Turn(lr, state);
    }

    private Instruction parseMove(String[] row) throws BrainParser.InvalidBrainSyntaxException {
        int elements = row.length;
        int st1;
        int st2;
        if(elements < 3 || !isStateInt(row[1]) || !isStateInt(row[2])){
            throw new BrainParser.InvalidBrainSyntaxException("Malformed instruction row: " + Arrays.toString(row));
        }
        st1 = Integer.parseInt(row[1]);
        st2 = Integer.parseInt(row[2]);
        return new Move(st1, st2);
    }

    private Instruction parseFlip(String[] row) throws BrainParser.InvalidBrainSyntaxException {
        int elements = row.length;
        int flip;
        int st1;
        int st2;
        if(elements < 4 || !isFlipNumber(row[1]) || !isStateInt(row[2]) || !isStateInt(row[3])){
            throw new BrainParser.InvalidBrainSyntaxException("Malformed instruction row: " + Arrays.toString(row));
        }
        flip = Integer.parseInt(row[1]);
        st1 = Integer.parseInt(row[2]);
        st2 = Integer.parseInt(row[3]);
        return new Flip(flip, st1, st2);
    }
    
}
