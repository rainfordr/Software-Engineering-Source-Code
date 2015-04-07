/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.antgame;

import group20.Conditions.Condition;
import static group20.Instructions.Dir.*;
import group20.Instructions.Dir;
import group20.Instructions.Instruction;
import group20.Instructions.Mark;
import group20.Instructions.Sense;
import static group20.Instructions.Sense.SenseDir.*;

import group20.Instructions.Turn;
import static group20.Instructions.Turn.LeftOrRight.*;
import static group20.antgame.Ant.Colour.*;

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
public class AntGameModelTest {
    
    MapCell[][] testMap;
    Pos centre;
    
    public AntGameModelTest() {
        testMap = new MapCell[5][5];
        testMap[0][0] = new MapCell(new Pos(0,0), true, 0, null);
        testMap[1][0] = new MapCell(new Pos(1,0), true, 0, null);
        testMap[2][0] = new MapCell(new Pos(2,0), true, 0, null);
        testMap[3][0] = new MapCell(new Pos(3,0), true, 0, null);
        testMap[4][0] = new MapCell(new Pos(4,0), true, 0, null);
        testMap[0][1] = new MapCell(new Pos(0,1), true, 0, null);
        testMap[1][1] = new MapCell(new Pos(1,1), false, 0, null);
        testMap[2][1] = new MapCell(new Pos(2,1), false, 0, null);
        testMap[3][1] = new MapCell(new Pos(3,1), false, 0, null);
        testMap[4][1] = new MapCell(new Pos(4,1), true, 0, null);
        testMap[0][2] = new MapCell(new Pos(0,2), true, 0, null);
        testMap[1][2] = new MapCell(new Pos(1,2), false, 0, null);
        testMap[2][2] = new MapCell(new Pos(2,2), false, 0, null);
        testMap[3][2] = new MapCell(new Pos(3,2), false, 0, null);
        testMap[4][2] = new MapCell(new Pos(4,2), true, 0, null);
        testMap[0][3] = new MapCell(new Pos(0,3), true, 0, null);
        testMap[1][3] = new MapCell(new Pos(1,3), false, 0, null);
        testMap[2][3] = new MapCell(new Pos(2,3), false, 0, null);
        testMap[3][3] = new MapCell(new Pos(3,3), false, 0, null);
        testMap[4][3] = new MapCell(new Pos(4,3), true, 0, null);
        testMap[0][4] = new MapCell(new Pos(0,4), true, 0, null);
        testMap[1][4] = new MapCell(new Pos(1,4), true, 0, null);
        testMap[2][4] = new MapCell(new Pos(2,4), true, 0, null);
        testMap[3][4] = new MapCell(new Pos(3,4), true, 0, null);
        testMap[4][4] = new MapCell(new Pos(4,4), true, 0, null);
        centre = new Pos (2,2);
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
     * Test of adjacentCell method, of class AntGameModel on even cells
     */
    @Test
    public void testAdjacentCellDir0PassesCorrectEven() {
        System.out.println("adjacentCell");
        Pos p = new Pos(2,2);
        Dir d = ZERO;
        AntGameModel instance = new AntGameModel();
        Pos expResult = new Pos(3,2);
        Pos result = instance.adjacentCell(p, d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testAdjacentCellDir0FailsIncorrectEven() {
        System.out.println("adjacentCell");
        Pos p = new Pos(2,2);
        Dir d = ZERO;
        AntGameModel instance = new AntGameModel();
        Pos expResult = new Pos(2,3);
        Pos result = instance.adjacentCell(p, d);
        assertFalse(expResult.equals(result));
    }
    
    @Test
    public void testAdjacentCellDir1PassesCorrectEven() {
        System.out.println("adjacentCell");
        Pos p = new Pos(2,2);
        Dir d = ONE;
        AntGameModel instance = new AntGameModel();
        Pos expResult = new Pos(2,3);
        Pos result = instance.adjacentCell(p, d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testAdjacentCellDir2PassesCorrectEven() {
        System.out.println("adjacentCell");
        Pos p = new Pos(2,2);
        Dir d = TWO;
        AntGameModel instance = new AntGameModel();
        Pos expResult = new Pos(1,3);
        Pos result = instance.adjacentCell(p, d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testAdjacentCellDir3PassesCorrectEven() {
        System.out.println("adjacentCell");
        Pos p = new Pos(2,2);
        Dir d = THREE;
        AntGameModel instance = new AntGameModel();
        Pos expResult = new Pos(1,2);
        Pos result = instance.adjacentCell(p, d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testAdjacentCellDir4PassesCorrectEven() {
        System.out.println("adjacentCell");
        Pos p = new Pos(2,2);
        Dir d = FOUR;
        AntGameModel instance = new AntGameModel();
        Pos expResult = new Pos(1,1);
        Pos result = instance.adjacentCell(p, d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testAdjacentCellDir5PassesCorrectEven() {
        System.out.println("adjacentCell");
        Pos p = new Pos(2,2);
        Dir d = FIVE;
        AntGameModel instance = new AntGameModel();
        Pos expResult = new Pos(2,1);
        Pos result = instance.adjacentCell(p, d);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of adjacentCell method, of class AntGameModel on odd cells
     */
    @Test
    public void testAdjacentCellDir0PassesCorrectOdd() {
        System.out.println("adjacentCell");
        Pos p = new Pos(3,3);
        Dir d = ZERO;
        AntGameModel instance = new AntGameModel();
        Pos expResult = new Pos(4,3);
        Pos result = instance.adjacentCell(p, d);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of adjacentCell method, of class AntGameModel on odd cells
     */
    @Test
    public void testAdjacentCellDir1PassesCorrectOdd() {
        System.out.println("adjacentCell");
        Pos p = new Pos(3,3);
        Dir d = ONE;
        AntGameModel instance = new AntGameModel();
        Pos expResult = new Pos(4,4);
        Pos result = instance.adjacentCell(p, d);
        assertEquals(expResult, result);
    }
    /**
     * Test of adjacentCell method, of class AntGameModel on odd cells
     */
    @Test
    public void testAdjacentCellDir2PassesCorrectOdd() {
        System.out.println("adjacentCell");
        Pos p = new Pos(3,3);
        Dir d = TWO;
        AntGameModel instance = new AntGameModel();
        Pos expResult = new Pos(3,4);
        Pos result = instance.adjacentCell(p, d);
        assertEquals(expResult, result);
    }
    /**
     * Test of adjacentCell method, of class AntGameModel on odd cells
     */
    @Test
    public void testAdjacentCellDir3PassesCorrectOdd() {
        System.out.println("adjacentCell");
        Pos p = new Pos(3,3);
        Dir d = THREE;
        AntGameModel instance = new AntGameModel();
        Pos expResult = new Pos(2,3);
        Pos result = instance.adjacentCell(p, d);
        assertEquals(expResult, result);
    }
    /**
     * Test of adjacentCell method, of class AntGameModel on odd cells
     */
    @Test
    public void testAdjacentCellDir4PassesCorrectOdd() {
        System.out.println("adjacentCell");
        Pos p = new Pos(3,3);
        Dir d = FOUR;
        AntGameModel instance = new AntGameModel();
        Pos expResult = new Pos(3,2);
        Pos result = instance.adjacentCell(p, d);
        assertEquals(expResult, result);
    }
    /**
     * Test of adjacentCell method, of class AntGameModel on odd cells
     */
    @Test
    public void testAdjacentCellDir5PassesCorrectOdd() {
        System.out.println("adjacentCell");
        Pos p = new Pos(3,3);
        Dir d = FIVE;
        AntGameModel instance = new AntGameModel();
        Pos expResult = new Pos(4,2);
        Pos result = instance.adjacentCell(p, d);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of adjacentCell method, of class AntGameModel on odd cells
     */
    @Test
    public void testAdjacentCellDir5FailsIncorrectOdd() {
        System.out.println("adjacentCell");
        Pos p = new Pos(3,3);
        Dir d = FIVE;
        AntGameModel instance = new AntGameModel();
        Pos expResult = new Pos(3,2);
        Pos result = instance.adjacentCell(p, d);
        assertFalse(expResult.equals(result));
    }
    
    /**
     * Test of turn method, of class AntGameModel.
     */
    @Test
    public void testTurnLeftDir0NotPassForOtherResults() {
        System.out.println("turn");
        Turn.LeftOrRight lr = LEFT;
        Dir d = ZERO;
        AntGameModel instance = new AntGameModel();
        Dir expResult = ONE;
        Dir result = instance.turn(lr, d);
        assertFalse(expResult.equals(result));
    }

    /**
     * Test of turn method, of class AntGameModel.
     */
    @Test
    public void testTurnLeftDir0() {
        System.out.println("turn");
        Turn.LeftOrRight lr = LEFT;
        Dir d = ZERO;
        AntGameModel instance = new AntGameModel();
        Dir expResult = FIVE;
        Dir result = instance.turn(lr, d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testTurnLeftDir1() {
        System.out.println("turn");
        Turn.LeftOrRight lr = LEFT;
        Dir d = ONE;
        AntGameModel instance = new AntGameModel();
        Dir expResult = ZERO;
        Dir result = instance.turn(lr, d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testTurnLeftDir2() {
        System.out.println("turn");
        Turn.LeftOrRight lr = LEFT;
        Dir d = TWO;
        AntGameModel instance = new AntGameModel();
        Dir expResult = ONE;
        Dir result = instance.turn(lr, d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testTurnLeftDir3() {
        System.out.println("turn");
        Turn.LeftOrRight lr = LEFT;
        Dir d = THREE;
        AntGameModel instance = new AntGameModel();
        Dir expResult = TWO;
        Dir result = instance.turn(lr, d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testTurnLeftDir4() {
        System.out.println("turn");
        Turn.LeftOrRight lr = LEFT;
        Dir d = FOUR;
        AntGameModel instance = new AntGameModel();
        Dir expResult = THREE;
        Dir result = instance.turn(lr, d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testTurnLeftDir5() {
        System.out.println("turn");
        Turn.LeftOrRight lr = LEFT;
        Dir d = FIVE;
        AntGameModel instance = new AntGameModel();
        Dir expResult = FOUR;
        Dir result = instance.turn(lr, d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testTurnRightDir0() {
        System.out.println("turn");
        Turn.LeftOrRight lr = RIGHT;
        Dir d = ZERO;
        AntGameModel instance = new AntGameModel();
        Dir expResult = ONE;
        Dir result = instance.turn(lr, d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testTurnRightDir1() {
        System.out.println("turn");
        Turn.LeftOrRight lr = RIGHT;
        Dir d = ONE;
        AntGameModel instance = new AntGameModel();
        Dir expResult = TWO;
        Dir result = instance.turn(lr, d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testTurnRightDir2() {
        System.out.println("turn");
        Turn.LeftOrRight lr = RIGHT;
        Dir d = TWO;
        AntGameModel instance = new AntGameModel();
        Dir expResult = THREE;
        Dir result = instance.turn(lr, d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testTurnRightDir3() {
        System.out.println("turn");
        Turn.LeftOrRight lr = RIGHT;
        Dir d = THREE;
        AntGameModel instance = new AntGameModel();
        Dir expResult = FOUR;
        Dir result = instance.turn(lr, d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testTurnRightDir4() {
        System.out.println("turn");
        Turn.LeftOrRight lr = RIGHT;
        Dir d = FOUR;
        AntGameModel instance = new AntGameModel();
        Dir expResult = FIVE;
        Dir result = instance.turn(lr, d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testTurnRightDir5() {
        System.out.println("turn");
        Turn.LeftOrRight lr = RIGHT;
        Dir d = FIVE;
        AntGameModel instance = new AntGameModel();
        Dir expResult = ZERO;
        Dir result = instance.turn(lr, d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSensedCellHereFailsUnexpected() {
        System.out.println("sensedCell");
        Pos p = centre;
        Dir d = ZERO;
        Sense.SenseDir sd = HERE;
        AntGameModel instance = new AntGameModel();
        Pos expResult = new Pos(2,3);
        Pos result = instance.sensedCell(p, d, sd);
        assertFalse(expResult.equals(result));
    }

    /**
     * Test of sensedCell method, of class AntGameModel.
     */
    @Test
    public void testSensedCellHerePassesExpected() {
        System.out.println("sensedCell");
        Pos p = centre;
        Dir d = ZERO;
        Sense.SenseDir sd = HERE;
        AntGameModel instance = new AntGameModel();
        Pos expResult = new Pos(2,2);
        Pos result = instance.sensedCell(p, d, sd);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of sensedCell method, of class AntGameModel.
     */
    @Test
    public void testSensedCellAheadPassesExpected() {
        System.out.println("sensedCell");
        Pos p = centre;
        Dir d = ZERO;
        Sense.SenseDir sd = AHEAD;
        AntGameModel instance = new AntGameModel();
        Pos expResult = new Pos(3,2);
        Pos result = instance.sensedCell(p, d, sd);
        assertEquals(expResult, result);
    }
    @Test
    public void testSensedCellAheadLeftPassesExpected() {
        System.out.println("sensedCell");
        Pos p = centre;
        Dir d = ZERO;
        Sense.SenseDir sd = LEFT_AHEAD;
        AntGameModel instance = new AntGameModel();
        Pos expResult = new Pos(2,1);
        Pos result = instance.sensedCell(p, d, sd);
        assertEquals(expResult, result);
    }
    @Test
    public void testSensedCellRIGHTAheadPassesExpected() {
        System.out.println("sensedCell");
        Pos p = centre;
        Dir d = ZERO;
        Sense.SenseDir sd = RIGHT_AHEAD;
        AntGameModel instance = new AntGameModel();
        Pos expResult = new Pos(2,3);
        Pos result = instance.sensedCell(p, d, sd);
        assertEquals(expResult, result);
    }

    /**
     * Test of otherColour method, of class AntGameModel.
     */
    @Test
    public void testOtherColourRedReturnsBlack() {
        System.out.println("otherColour");
        Ant.Colour c = RED;
        AntGameModel instance = new AntGameModel();
        Ant.Colour expResult = BLACK;
        Ant.Colour result = instance.otherColour(c);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of otherColour method, of class AntGameModel.
     */
    @Test
    public void testOtherColourBlackReturnsRed() {
        System.out.println("otherColour");
        Ant.Colour c = BLACK;
        AntGameModel instance = new AntGameModel();
        Ant.Colour expResult = RED;
        Ant.Colour result = instance.otherColour(c);
        assertEquals(expResult, result);
    }

    /**
     * Test of rocky method, of class AntGameModel.
     */
    @Test
    public void testRockyFailsOnClearCell() {
        System.out.println("rocky");
        Pos p = centre;
        AntGameModel instance = new AntGameModel();
        boolean expResult = false;
        boolean result = instance.rocky(p);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of rocky method, of class AntGameModel.
     */
    @Test
    public void testRockyPassesOnEdge() {
        System.out.println("rocky");
        Pos p = centre;
        AntGameModel instance = new AntGameModel();
        boolean expResult = false;
        boolean result = instance.rocky(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of state method, of class AntGameModel.
     */
    @Test
    public void testState() {
        System.out.println("state");
        Ant a = null;
        AntGameModel instance = new AntGameModel();
        int expResult = 0;
        int result = instance.state(a);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of colour method, of class AntGameModel.
     */
    @Test
    public void testColour() {
        System.out.println("colour");
        Ant a = null;
        AntGameModel instance = new AntGameModel();
        Ant.Colour expResult = null;
        Ant.Colour result = instance.colour(a);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resting method, of class AntGameModel.
     */
    @Test
    public void testResting() {
        System.out.println("resting");
        Ant a = null;
        AntGameModel instance = new AntGameModel();
        int expResult = 0;
        int result = instance.resting(a);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of direction method, of class AntGameModel.
     */
    @Test
    public void testDirection() {
        System.out.println("direction");
        Ant a = null;
        AntGameModel instance = new AntGameModel();
        Dir expResult = null;
        Dir result = instance.direction(a);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasFood method, of class AntGameModel.
     */
    @Test
    public void testHasFood() {
        System.out.println("hasFood");
        Ant a = null;
        AntGameModel instance = new AntGameModel();
        boolean expResult = false;
        boolean result = instance.hasFood(a);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setState method, of class AntGameModel.
     */
    @Test
    public void testSetState() throws Exception {
        System.out.println("setState");
        Ant a = null;
        int state = 0;
        AntGameModel instance = new AntGameModel();
        instance.setState(a, state);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setResting method, of class AntGameModel.
     */
    @Test
    public void testSetResting() {
        System.out.println("setResting");
        Ant a = null;
        int Resting = 0;
        AntGameModel instance = new AntGameModel();
        instance.setResting(a, Resting);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDirection method, of class AntGameModel.
     */
    @Test
    public void testSetDirection() {
        System.out.println("setDirection");
        Ant a = null;
        Dir d = null;
        AntGameModel instance = new AntGameModel();
        instance.setDirection(a, d);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHasFood method, of class AntGameModel.
     */
    @Test
    public void testSetHasFood() {
        System.out.println("setHasFood");
        Ant a = null;
        Boolean hasFood = null;
        AntGameModel instance = new AntGameModel();
        instance.setHasFood(a, hasFood);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of someAntIsAt method, of class AntGameModel.
     */
    @Test
    public void testSomeAntIsAt() {
        System.out.println("someAntIsAt");
        Pos p = null;
        AntGameModel instance = new AntGameModel();
        boolean expResult = false;
        boolean result = instance.someAntIsAt(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of antAt method, of class AntGameModel.
     */
    @Test
    public void testAntAt() {
        System.out.println("antAt");
        Pos p = null;
        AntGameModel instance = new AntGameModel();
        Ant expResult = null;
        Ant result = instance.antAt(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAntAt method, of class AntGameModel.
     */
    @Test
    public void testSetAntAt() {
        System.out.println("setAntAt");
        Pos p = null;
        Ant a = null;
        AntGameModel instance = new AntGameModel();
        instance.setAntAt(p, a);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearAntAt method, of class AntGameModel.
     */
    @Test
    public void testClearAntAt() {
        System.out.println("clearAntAt");
        Pos p = null;
        AntGameModel instance = new AntGameModel();
        instance.clearAntAt(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of antIsAlive method, of class AntGameModel.
     */
    @Test
    public void testAntIsAlive() {
        System.out.println("antIsAlive");
        int id = 0;
        AntGameModel instance = new AntGameModel();
        boolean expResult = false;
        boolean result = instance.antIsAlive(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAnt method, of class AntGameModel.
     */
    @Test
    public void testFindAnt() {
        System.out.println("findAnt");
        int id = 0;
        AntGameModel instance = new AntGameModel();
        Pos expResult = null;
        Pos result = instance.findAnt(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of KillAntAt method, of class AntGameModel.
     */
    @Test
    public void testKillAntAt() {
        System.out.println("KillAntAt");
        Pos p = null;
        AntGameModel instance = new AntGameModel();
        instance.KillAntAt(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMarkerAt method, of class AntGameModel.
     */
    @Test
    public void testSetMarkerAt() {
        System.out.println("setMarkerAt");
        Pos p = null;
        Ant.Colour c = null;
        Mark.Marker m = null;
        AntGameModel instance = new AntGameModel();
        instance.setMarkerAt(p, c, m);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearMarkerAt method, of class AntGameModel.
     */
    @Test
    public void testClearMarkerAt() {
        System.out.println("clearMarkerAt");
        Pos p = null;
        Ant.Colour c = null;
        Mark.Marker m = null;
        AntGameModel instance = new AntGameModel();
        instance.clearMarkerAt(p, c, m);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkMarkerAt method, of class AntGameModel.
     */
    @Test
    public void testCheckMarkerAt() {
        System.out.println("checkMarkerAt");
        Pos p = null;
        Ant.Colour c = null;
        Mark.Marker m = null;
        AntGameModel instance = new AntGameModel();
        boolean expResult = false;
        boolean result = instance.checkMarkerAt(p, c, m);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkAnyMarkerAt method, of class AntGameModel.
     */
    @Test
    public void testCheckAnyMarkerAt() {
        System.out.println("checkAnyMarkerAt");
        Pos p = null;
        Ant.Colour c = null;
        AntGameModel instance = new AntGameModel();
        boolean expResult = false;
        boolean result = instance.checkAnyMarkerAt(p, c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of foodAt method, of class AntGameModel.
     */
    @Test
    public void testFoodAt() {
        System.out.println("foodAt");
        Pos p = null;
        AntGameModel instance = new AntGameModel();
        int expResult = 0;
        int result = instance.foodAt(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFoodAt method, of class AntGameModel.
     */
    @Test
    public void testSetFoodAt() {
        System.out.println("setFoodAt");
        Pos p = null;
        int food = 0;
        AntGameModel instance = new AntGameModel();
        instance.setFoodAt(p, food);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of antHillAt method, of class AntGameModel.
     */
    @Test
    public void testAntHillAt() {
        System.out.println("antHillAt");
        Pos p = null;
        Ant.Colour c = null;
        AntGameModel instance = new AntGameModel();
        boolean expResult = false;
        boolean result = instance.antHillAt(p, c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cellMatches method, of class AntGameModel.
     */
    @Test
    public void testCellMatches() {
        System.out.println("cellMatches");
        Pos p = null;
        Ant.Colour c = null;
        Condition cond = null;
        AntGameModel instance = new AntGameModel();
        boolean expResult = false;
        boolean result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInstruction method, of class AntGameModel.
     */
    @Test
    public void testGetInstruction() {
        System.out.println("getInstruction");
        Ant.Colour c = null;
        int state = 0;
        AntGameModel instance = new AntGameModel();
        Instruction expResult = null;
        Instruction result = instance.getInstruction(c, state);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of adjacentAnts method, of class AntGameModel.
     */
    @Test
    public void testAdjacentAnts() {
        System.out.println("adjacentAnts");
        Pos p = null;
        Ant.Colour c = null;
        AntGameModel instance = new AntGameModel();
        int expResult = 0;
        int result = instance.adjacentAnts(p, c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkForSurroundedAntAt method, of class AntGameModel.
     */
    @Test
    public void testCheckForSurroundedAntAt() {
        System.out.println("checkForSurroundedAntAt");
        Pos p = null;
        AntGameModel instance = new AntGameModel();
        instance.checkForSurroundedAntAt(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkForSurroundedAnts method, of class AntGameModel.
     */
    @Test
    public void testCheckForSurroundedAnts() {
        System.out.println("checkForSurroundedAnts");
        Pos p = null;
        AntGameModel instance = new AntGameModel();
        instance.checkForSurroundedAnts(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of step method, of class AntGameModel.
     */
    @Test
    public void testStep() {
        System.out.println("step");
        int id = 0;
        AntGameModel instance = new AntGameModel();
        instance.step(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of playRound method, of class AntGameModel.
     */
    @Test
    public void testPlayRound() {
        System.out.println("playRound");
        AntGameModel instance = new AntGameModel();
        instance.playRound();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of populateAntHills method, of class AntGameModel.
     */
    @Test
    public void testPopulateAntHills() {
        System.out.println("populateAntHills");
        AntGameModel instance = new AntGameModel();
        instance.populateAntHills();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
