/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.antgame;

import group20.Conditions.Condition;
import group20.Instructions.Instruction;
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
public class BrainParserTest {
    
    public BrainParserTest() {
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
     * Test of parseMarker method, of class BrainParser.
     */
    @Test
    public void testParseMarker() {
        System.out.println("parseMarker");
        int markerNum = 0;
        int stateNum = 0;
        BrainParser instance = new BrainParser();
        Instruction expResult = null;
        Instruction result = instance.parseMarker(markerNum, stateNum);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseCondition method, of class BrainParser.
     */
    @Test
    public void testParseCondition() {
        System.out.println("parseCondition");
        String condition = "";
        BrainParser instance = new BrainParser();
        Condition expResult = null;
        Condition result = instance.parseCondition(condition);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseUnmark method, of class BrainParser.
     */
    @Test
    public void testParseUnmark() {
        System.out.println("parseUnmark");
        int markerNum = 0;
        int stateNum = 0;
        BrainParser instance = new BrainParser();
        Instruction expResult = null;
        Instruction result = instance.parseUnmark(markerNum, stateNum);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseBrain method, of class BrainParser.
     */
    @Test
    public void testParseBrain() throws Exception {
        System.out.println("parseBrain");
        String[] brainInsructions = null;
        BrainParser instance = new BrainParser();
        Instruction[] expResult = null;
        Instruction[] result = instance.parseBrain(brainInsructions);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
