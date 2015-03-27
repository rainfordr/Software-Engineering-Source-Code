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
        boolean result = instance.oddEdgeOK(row, rowSize);
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
        boolean expResult = false;
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
    
}
