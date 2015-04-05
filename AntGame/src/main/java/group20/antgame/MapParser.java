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
    public char[][] parseMap(String[] mapFile, boolean isComp) throws InvalidMapSyntaxException{
        int fileRows = mapFile.length;
        if(fileRows < 3){
            throw new InvalidMapSyntaxException("Invalid File Syntax. Requires two rows of ints followed by Map representation");
        }
        char[][] map; // = new MapCell[150][150];
        int xSize = 0;
        int ySize = 0;
        String xSizeStr = "";
        if(mapFile[0].length() < 1){
            throw new InvalidMapSyntaxException("First row of map should be int X size of map");
        }
        else{
            xSizeStr = mapFile[0];
            if(!dimensionOK(xSizeStr)||!isPosInt(xSizeStr)){
                throw new InvalidMapSyntaxException("First row of map should be int X size of map");
            }
            xSize = Integer.parseInt(xSizeStr);
        }
        String ySizeStr = "";
        if(mapFile[1].length() < 1){
            throw new InvalidMapSyntaxException("Second row of map should be int Y size of map");
        }
        else{
            ySizeStr = mapFile[1];
            if(!dimensionOK(ySizeStr)||!isPosInt(ySizeStr)){
                throw new InvalidMapSyntaxException("Second row of map should be int Y size of map");
            }
            ySize = Integer.parseInt(ySizeStr);
        }
        if(isComp && (ySize != 150 || xSize != 150)){
            throw new InvalidMapSyntaxException("Competition Map standards must be 150 x 150. Map is " + xSize +" x " + ySize + ".");
        }
        map = new char[xSize][ySize];
        if(fileRows - 2 < ySize){
            throw new InvalidMapSyntaxException("Stated and actual Y values differ. Stated is " + ySize +", actual is " +(fileRows - 2)+ ".");
        }
        if(!edgeOK(mapFile[2], ySize)){
            throw new InvalidMapSyntaxException("The edges of the map should all be rock(# char)");
        }
        else{
            String row = mapFile[2].replaceAll(" ", "");
            for(int cell = 0; cell < row.length(); cell++){
                map[cell][0] = row.charAt(cell);
            }
        }
        for(int rowNum = 3, rowEnd = mapFile.length - 1; rowNum < rowEnd; rowNum++){
            if(!bodyRowOK(mapFile[rowNum], ySize)){
                throw new InvalidMapSyntaxException("rows must only contain '#', '.' '+', '-' or [1-9] seperated by a single space char");
            }
            else{
                String row = mapFile[rowNum].replaceAll(" ", "");
                for(int cell = 0; cell < row.length(); cell++){
                    map[cell][rowNum - 2] = row.charAt(cell);                        
                }
            }
        }
        int finalRow = mapFile.length - 1;
        if (edgeOK(mapFile[finalRow], ySize)) {
            String row = mapFile[finalRow].replaceAll(" ", "");
            for (int cell = 0; cell < row.length(); cell++) {
                map[cell][finalRow - 2] = row.charAt(cell);
            }
        } 
        else {
            throw new InvalidMapSyntaxException("The edges of the map should all be rock(# char)");
        }
        return map;
    }
    
    public boolean dimensionOK(String row){
        return row.matches("(\\d+)(\\s*)");
    }
    
    public boolean edgeOK(String row, int rowSize){
        return row.matches("( ?# )(# ){" + (rowSize -2) + "}(# *)");
    }
    
    public boolean bodyRowOK(String row, int rowSize){
        return row.matches("( ?)# ((# )|(\\+ )|(\\- )|([1-9] )|(. )){" + (rowSize-2) + "}# *");
    }
    
    //Method to check if given String is an integer.
    public boolean isPosInt(String row){
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