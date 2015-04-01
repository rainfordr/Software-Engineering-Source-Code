/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.antgame;

import group20.exceptions.InvalidMapSyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author owner
 */
public class MapParserIT {
    
    public MapParserIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of parseMap method, of class MapParser.
     */
//    @Test
//    public void testParseMap(){
//        System.out.println("parseMap");
//        String[] mapFile = null;
//        char lineSep = ' ';
//        boolean isComp = false;
//        MapParser instance = new MapParser();
//        char[][] expResult = null;
//        char[][] result;
//        try {
//            result = instance.parseMap(mapFile, lineSep, isComp);
//            assertArrayEquals(expResult, result);
//        catch(InvalidMapSyntaxException ex){
//                
//        }
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of topRowOK method, of class MapParser.
     */
    @Test
    public void testTopRowOK() {
        System.out.println("topRowOK");
        String row = "# # # # # # # # # #";
        int rowSize = 10;
        MapParser instance = new MapParser();
        boolean expResult = true;
        boolean result = instance.edgeOK(row, rowSize);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of topRowOK method, of class MapParser.
     */
    @Test
    public void testBodyRowOKPassesWithValidInput() {
        System.out.println("bodyRowOK");
        String row = "# . . + . 9 - # . #";
        int rowSize = 10;
        MapParser instance = new MapParser();
        boolean expResult = true;
        boolean result = instance.bodyRowOK(row, rowSize);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of topRowOK method, of class MapParser.
     */
    @Test
    public void testBodyRowOKPassesWithValidInputTrailingSpaces() {
        System.out.println("bodyRowOK");
        String row = "# . . + . 9 - # . #     ";
        int rowSize = 10;
        MapParser instance = new MapParser();
        boolean expResult = true;
        boolean result = instance.bodyRowOK(row, rowSize);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of topRowOK method, of class MapParser.
     */
    @Test
    public void testBodyRowOKFailsWithInvalidInput() {
        System.out.println("bodyRowOK");
        String row = "# . . + . 9 - # . .";
        int rowSize = 10;
        MapParser instance = new MapParser();
        boolean expResult = false;
        boolean result = instance.bodyRowOK(row, rowSize);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of topRowOK method, of class MapParser.
     */
    @Test
    public void testBodyRowOKFailsWithInvalidInput2() {
        System.out.println("bodyRowOK");
        String row = "# . . + .9 - # . #";
        int rowSize = 10;
        MapParser instance = new MapParser();
        boolean expResult = false;
        boolean result = instance.bodyRowOK(row, rowSize);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of topRowOK method, of class MapParser.
     */
    @Test
    public void testBodyRowOKFailsWithInvalidInput3() {
        System.out.println("bodyRowOK");
        String row = "# . . + . 9 - # . # #";
        int rowSize = 10;
        MapParser instance = new MapParser();
        boolean expResult = false;
        boolean result = instance.bodyRowOK(row, rowSize);
        assertEquals(expResult, result);
    }

    /**
     * Test of isPosInt method, of class MapParser.
     */
    @Test
    public void testIsPosIntTrueFor456() {
        System.out.println("isPosInt");
        String currentChar = "456";
        MapParser instance = new MapParser();
        boolean expResult = true;
        boolean result = instance.isPosInt(currentChar);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isPosInt method, of class MapParser.
     */
    @Test
    public void testIsPosIntFalseForNegOne() {
        System.out.println("isPosInt");
        String currentChar = "-1";
        MapParser instance = new MapParser();
        boolean expResult = false;
        boolean result = instance.isPosInt(currentChar);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isPosInt method, of class MapParser.
     */
    @Test
    public void testIsPosIntFalseForZero() {
        System.out.println("isPosInt");
        String currentChar = "0";
        MapParser instance = new MapParser();
        boolean expResult = false;
        boolean result = instance.isPosInt(currentChar);
        assertEquals(expResult, result);
    }
    /**
     * Test of isPosInt method, of class MapParser.
     */
    @Test
    public void testIsPosIntTrueFor150AndTriailingSpaces() {
        System.out.println("isPosInt");
        String currentChar = "150    ";
        MapParser instance = new MapParser();
        boolean expResult = true;
        boolean result = instance.isPosInt(currentChar);
        assertEquals(expResult, result);
    }
    /**
     * Test of isPosInt method, of class MapParser.
     */
    @Test
    public void testIsPosIntFalseForPosNumWithChars() {
        System.out.println("isPosInt");
        String currentChar = "150X";
        MapParser instance = new MapParser();
        boolean expResult = false;
        boolean result = instance.isPosInt(currentChar);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of dimensionOK method, of class MapParser.
     */
    @Test
    public void dimensionOKPassesWithValidInput() {
        System.out.println("isPosInt");
        String row = "150";
        MapParser instance = new MapParser();
        boolean expResult = true;
        boolean result = instance.dimensionOK(row);
        assertEquals(expResult, result);
    }
    /**
     * Test of dimensionOK method, of class MapParser.
     */
    @Test
    public void dimensionOKPassesWithValidInput2() {
        System.out.println("isPosInt");
        String row = "150       ";
        MapParser instance = new MapParser();
        boolean expResult = true;
        boolean result = instance.dimensionOK(row);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of dimensionOK method, of class MapParser.
     */
    @Test
    public void dimensionOKfailsWithinValidInput() {
        System.out.println("isPosInt");
        String row = "150X";
        MapParser instance = new MapParser();
        boolean expResult = false;
        boolean result = instance.dimensionOK(row);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of dimensionOK method, of class MapParser.
     */
    @Test
    public void dimensionOKfailsWithinValidInput2() {
        System.out.println("isPosInt");
        String row = "150       x";
        MapParser instance = new MapParser();
        boolean expResult = false;
        boolean result = instance.dimensionOK(row);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of dimensionOK method, of class MapParser.
     */
    @Test
    public void dimensionOKfailsWithinValidInput3() {
        System.out.println("isPosInt");
        String row = " 150";
        MapParser instance = new MapParser();
        boolean expResult = false;
        boolean result = instance.dimensionOK(row);
        assertEquals(expResult, result);
    }
    
}
