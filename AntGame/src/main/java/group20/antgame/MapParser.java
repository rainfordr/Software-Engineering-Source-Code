/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.antgame;

import group20.exceptions.*;

/**
 * 
 * @author group20
 */
public class MapParser {
    
    /**
     * 
     * @param mapFile - the ASCII String representation of the map file
     * @param lineSep - the line separator character.
     * @param isComp - true if map should conform to competition standards 
     * of 150x150 size, false otherwise.
     * @return the initialised char[][] representation of the map.
     * @throws group20.exceptions.InvalidMapSyntaxException
     */
    public char[][] parseMap(String[] mapFile, char lineSep, boolean isComp) throws InvalidMapSyntaxException{
        int fileRows = mapFile.length;
        if(fileRows < 3){
            throw new InvalidMapSyntaxException("Invalid File Syntax. Requires two rows of ints followed by Map representation");
        }        
        char[][] map; // = new MapCell[150][150];
        int x = 0;
        int y = 0;
        String Xsize = "";
        if(mapFile[0].length() < 1){
            throw new InvalidMapSyntaxException("First row of map should be int X size of map");
        }
        else{
            Xsize = mapFile[0];
            if(!isPosInt(Xsize)){
                throw new InvalidMapSyntaxException("First row of map should be int X size of map");
            }
            x = Integer.parseInt(Xsize);
        }
        String Ysize = "";
        if(mapFile[1].length() < 1){
            throw new InvalidMapSyntaxException("Second row of map should be int Y size of map");
        }
        else{
            Ysize = mapFile[1];
            if(!isPosInt(Ysize)){
                throw new InvalidMapSyntaxException("Second row of map should be int Y size of map");
            }
            y = Integer.parseInt(Ysize);
        }
        if(isComp && (y != 150 || x != 150)){
            throw new InvalidMapSyntaxException("Competition Map standards must be 150 x 150. Map is " + x +" x " + y + ".");
        }
        map = new char[x][y];
        if(fileRows - 2 < y){
            throw new InvalidMapSyntaxException("Stated and actual Y values differ. Stated is " + y +", actual is " +(fileRows - 2)+ ".");
        }
        if(!oddEdgeOK(mapFile[2], y)){
            throw new InvalidMapSyntaxException("The edges of the map should all be rock(# char)");
        }
        else{
            String row = mapFile[2].replaceAll(" ", "");
            for(int i = 0; i < row.length(); i++){
                map[x][i] = row.charAt(i);
            }
        }
        
        
        
        int StringPtr = 0;
        int rowPtr = 0;
        return null;
    }
    
    public boolean oddEdgeOK(String row, int rowSize){
        return row.matches("( ?# ){" +(rowSize-1)+ "}#" );
    }
    
    public boolean evenEdgeOK(String row, int rowSize){
        return row.matches("( ?#){" + (rowSize) + "}");
    }
    
    public boolean EvenBodyRowOK(String row, int rowSize){
        return row.matches("( ?#)(#|+|-|[1-9]|.){" + (rowSize-2) + "}#");
    }
    
    //Method to check if given String is an integer.
    boolean isPosInt(String row){
        row = row.replace(" ", "");
        int result;
        try{
            result = Integer.parseInt(row);
        }catch(NumberFormatException nfe){
            return false;
        }
        return result > 0;
    }  
    
}