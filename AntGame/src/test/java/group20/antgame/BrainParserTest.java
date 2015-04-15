/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.antgame;

import group20.Conditions.Condition;
import group20.Conditions.Food;
import group20.Conditions.Friend;
import group20.Conditions.Home;
import group20.Instructions.Drop;
import group20.Instructions.Flip;
import group20.Instructions.Instruction;
import group20.Instructions.Mark;
import static group20.Instructions.Mark.Marker.*;
import group20.Instructions.Move;
import group20.Instructions.PickUp;
import group20.Instructions.Sense;
import static group20.Instructions.Sense.SenseDir.*;
import group20.Instructions.Turn;
import static group20.Instructions.Turn.LeftOrRight.LEFT;
import group20.Instructions.Unmark;
import static group20.antgame.BrainParser.instructionType.Unmark;
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
    Instruction[] expParseResult = new Instruction[11];
    Instruction flip, turn, pickUp, drop, move, unMark, mark, senseLAM1,senseRAHome, senseHereFood, senseAheadFriend;
    
    public BrainParserTest() {
        flip = new Flip(10, 6, 1);
        turn = new Turn(LEFT, 1);
        pickUp = new PickUp(20, 2);
        drop = new Drop(2);
        move = new Move(0, 28);
        unMark = new Unmark(MARKER5,0);
        mark = new Mark(MARKER2,3);
        senseLAM1 = new Sense(LEFT_AHEAD, 3, 78, MARKER1);
        senseRAHome = new Sense(RIGHT_AHEAD, 4, 77, new Home());
        senseHereFood = new Sense(HERE, 5, 76, new Food());
        senseAheadFriend = new Sense(AHEAD, 6, 75, new Friend());
        
        expParseResult[0] = flip;
        expParseResult[1] = turn;
        expParseResult[2] = pickUp;
        expParseResult[3] = drop;
        expParseResult[4] = move;
        expParseResult[5] = unMark;
        expParseResult[6] = mark;
        expParseResult[7] = senseLAM1;
        expParseResult[8] = senseRAHome;
        expParseResult[9] = senseHereFood;
        expParseResult[10] = senseAheadFriend;
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
        String[] brainInsructions = Utils.fileToStringArray("C:\\Users\\owner\\Documents\\NetBeansProjects\\Software-Engineering-Source-Code\\AntGame\\src\\test\\java\\tinyWorldSimTest\\parserTestBrain.ant");
        BrainParser instance = new BrainParser();
        Instruction[] expResult = expParseResult;
        Instruction[] result = instance.parseBrain(brainInsructions);
        assertArrayEquals(expResult, result);
    }
    
}
