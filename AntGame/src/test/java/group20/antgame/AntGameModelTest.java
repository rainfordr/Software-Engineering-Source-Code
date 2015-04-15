/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.antgame;

import group20.Conditions.Condition;
import group20.Conditions.*;
import static group20.Instructions.Dir.*;
import group20.Instructions.Dir;
import group20.Instructions.Instruction;
import group20.Instructions.Mark;
import static group20.Instructions.Mark.Marker.*;
import group20.Instructions.Move;
import group20.Instructions.Sense;
import static group20.Instructions.Sense.SenseDir.*;

import group20.Instructions.Turn;
import static group20.Instructions.Turn.LeftOrRight.*;
import static group20.antgame.Ant.Colour.*;
import group20.exceptions.InvalidMapSyntaxException;
import group20.exceptions.InvalidStateException;
import java.io.IOException;
import static group20.antgame.AntGameModel.TestStatus.*;


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
    MapCell[][] testMapSurround;
    Instruction[] brain;
    Pos centre;
    Pos redAntPos;
    Pos blackAntPos;
    Map mapClass;
    MapParser mapParser;
    
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
        testMap[3][1] = new MapCell(new Pos(3,1), false, 0, BLACK);
        testMap[4][1] = new MapCell(new Pos(4,1), true, 0, null);
        testMap[0][2] = new MapCell(new Pos(0,2), true, 0, null);
        testMap[1][2] = new MapCell(new Pos(1,2), false, 0, null);
        testMap[2][2] = new MapCell(new Pos(2,2), false, 0, null);
        testMap[3][2] = new MapCell(new Pos(3,2), false, 0, null);
        testMap[4][2] = new MapCell(new Pos(4,2), true, 0, null);
        testMap[0][3] = new MapCell(new Pos(0,3), true, 0, null);
        testMap[1][3] = new MapCell(new Pos(1,3), false, 0, null);
        testMap[2][3] = new MapCell(new Pos(2,3), false, 0, null);
        testMap[3][3] = new MapCell(new Pos(3,3), false, 0, RED);
        testMap[4][3] = new MapCell(new Pos(4,3), true, 0, null);
        testMap[0][4] = new MapCell(new Pos(0,4), true, 0, null);
        testMap[1][4] = new MapCell(new Pos(1,4), true, 0, null);
        testMap[2][4] = new MapCell(new Pos(2,4), true, 0, null);
        testMap[3][4] = new MapCell(new Pos(3,4), true, 0, null);
        testMap[4][4] = new MapCell(new Pos(4,4), true, 0, null);
        centre = new Pos (2,2);
        redAntPos = new Pos (3,3);
        blackAntPos = new Pos(3,1);
        brain = new Instruction[]{new Sense(HERE, 1, 2, new Food()), new Move(3, 0),new Turn(LEFT, 0)};
        testMapSurround = new MapCell[5][5];
        testMapSurround[0][0] = new MapCell(new Pos(0,0), true, 0, null);
        testMapSurround[1][0] = new MapCell(new Pos(1,0), true, 0, null);
        testMapSurround[2][0] = new MapCell(new Pos(2,0), true, 0, null);
        testMapSurround[3][0] = new MapCell(new Pos(3,0), true, 0, null);
        testMapSurround[4][0] = new MapCell(new Pos(4,0), true, 0, null);
        testMapSurround[0][1] = new MapCell(new Pos(0,1), true, 0, null);
        testMapSurround[1][1] = new MapCell(new Pos(1,1), false, 0, BLACK);
        testMapSurround[2][1] = new MapCell(new Pos(2,1), false, 0, BLACK);
        testMapSurround[3][1] = new MapCell(new Pos(3,1), false, 0, null);
        testMapSurround[4][1] = new MapCell(new Pos(4,1), true, 0, null);
        testMapSurround[0][2] = new MapCell(new Pos(0,2), true, 0, null);
        testMapSurround[1][2] = new MapCell(new Pos(1,2), false, 0, BLACK);
        testMapSurround[2][2] = new MapCell(new Pos(2,2), false, 0, RED);
        testMapSurround[3][2] = new MapCell(new Pos(3,2), false, 0, BLACK);
        testMapSurround[4][2] = new MapCell(new Pos(4,2), true, 0, null);
        testMapSurround[0][3] = new MapCell(new Pos(0,3), true, 0, null);
        testMapSurround[1][3] = new MapCell(new Pos(1,3), false, 0, BLACK);
        testMapSurround[2][3] = new MapCell(new Pos(2,3), false, 0, BLACK);
        testMapSurround[3][3] = new MapCell(new Pos(3,3), false, 0, RED);
        testMapSurround[4][3] = new MapCell(new Pos(4,3), true, 0, null);
        testMapSurround[0][4] = new MapCell(new Pos(0,4), true, 0, null);
        testMapSurround[1][4] = new MapCell(new Pos(1,4), true, 0, null);
        testMapSurround[2][4] = new MapCell(new Pos(2,4), true, 0, null);
        testMapSurround[3][4] = new MapCell(new Pos(3,4), true, 0, null);
        testMapSurround[4][4] = new MapCell(new Pos(4,4), true, 0, null);
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
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        Pos expResult = new Pos(3,2);
        Pos result = instance.adjacentCell(p, d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testAdjacentCellDir0FailsIncorrectEven() {
        System.out.println("adjacentCell");
        Pos p = new Pos(2,2);
        Dir d = ZERO;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        Pos expResult = new Pos(2,3);
        Pos result = instance.adjacentCell(p, d);
        assertFalse(expResult.equals(result));
    }
    
    @Test
    public void testAdjacentCellDir1PassesCorrectEven() {
        System.out.println("adjacentCell");
        Pos p = new Pos(2,2);
        Dir d = ONE;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        Pos expResult = new Pos(2,3);
        Pos result = instance.adjacentCell(p, d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testAdjacentCellDir2PassesCorrectEven() {
        System.out.println("adjacentCell");
        Pos p = new Pos(2,2);
        Dir d = TWO;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        Pos expResult = new Pos(1,3);
        Pos result = instance.adjacentCell(p, d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testAdjacentCellDir3PassesCorrectEven() {
        System.out.println("adjacentCell");
        Pos p = new Pos(2,2);
        Dir d = THREE;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        Pos expResult = new Pos(1,2);
        Pos result = instance.adjacentCell(p, d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testAdjacentCellDir4PassesCorrectEven() {
        System.out.println("adjacentCell");
        Pos p = new Pos(2,2);
        Dir d = FOUR;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        Pos expResult = new Pos(1,1);
        Pos result = instance.adjacentCell(p, d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testAdjacentCellDir5PassesCorrectEven() {
        System.out.println("adjacentCell");
        Pos p = new Pos(2,2);
        Dir d = FIVE;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
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
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
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
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
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
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
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
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
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
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
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
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
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
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
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
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
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
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        Dir expResult = FIVE;
        Dir result = instance.turn(lr, d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testTurnLeftDir1() {
        System.out.println("turn");
        Turn.LeftOrRight lr = LEFT;
        Dir d = ONE;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        Dir expResult = ZERO;
        Dir result = instance.turn(lr, d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testTurnLeftDir2() {
        System.out.println("turn");
        Turn.LeftOrRight lr = LEFT;
        Dir d = TWO;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        Dir expResult = ONE;
        Dir result = instance.turn(lr, d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testTurnLeftDir3() {
        System.out.println("turn");
        Turn.LeftOrRight lr = LEFT;
        Dir d = THREE;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        Dir expResult = TWO;
        Dir result = instance.turn(lr, d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testTurnLeftDir4() {
        System.out.println("turn");
        Turn.LeftOrRight lr = LEFT;
        Dir d = FOUR;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        Dir expResult = THREE;
        Dir result = instance.turn(lr, d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testTurnLeftDir5() {
        System.out.println("turn");
        Turn.LeftOrRight lr = LEFT;
        Dir d = FIVE;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        Dir expResult = FOUR;
        Dir result = instance.turn(lr, d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testTurnRightDir0() {
        System.out.println("turn");
        Turn.LeftOrRight lr = RIGHT;
        Dir d = ZERO;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        Dir expResult = ONE;
        Dir result = instance.turn(lr, d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testTurnRightDir1() {
        System.out.println("turn");
        Turn.LeftOrRight lr = RIGHT;
        Dir d = ONE;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        Dir expResult = TWO;
        Dir result = instance.turn(lr, d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testTurnRightDir2() {
        System.out.println("turn");
        Turn.LeftOrRight lr = RIGHT;
        Dir d = TWO;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        Dir expResult = THREE;
        Dir result = instance.turn(lr, d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testTurnRightDir3() {
        System.out.println("turn");
        Turn.LeftOrRight lr = RIGHT;
        Dir d = THREE;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        Dir expResult = FOUR;
        Dir result = instance.turn(lr, d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testTurnRightDir4() {
        System.out.println("turn");
        Turn.LeftOrRight lr = RIGHT;
        Dir d = FOUR;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        Dir expResult = FIVE;
        Dir result = instance.turn(lr, d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testTurnRightDir5() {
        System.out.println("turn");
        Turn.LeftOrRight lr = RIGHT;
        Dir d = FIVE;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
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
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
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
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
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
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
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
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
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
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
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
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
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
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
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
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
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
        Pos p = new Pos(0,0);
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        boolean expResult = true;
        boolean result = instance.rocky(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of state method, of class AntGameModel.
     */
    @Test
    public void testState() throws InvalidStateException {
        System.out.println("state");
        Ant a;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        instance.populateAntHills();
        a = instance.antAt(redAntPos);
        int expResult = 0;
        int result = instance.state(a);
        assertEquals(expResult, result);
        a.setState(4);
        result = instance.state(a);
        expResult = 4;
        assertEquals(expResult, result);
    }

    /**
     * Test of colour method, of class AntGameModel.
     */
    @Test
    public void testColour() {
        System.out.println("colour");
        Ant a = new Ant(RED, 0, centre);
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        Ant.Colour expResult = RED;
        Ant.Colour result = instance.colour(a);
        assertEquals(expResult, result);
    }

    /**
     * Test of resting method, of class AntGameModel.
     */
    @Test
    public void testResting() {
        System.out.println("resting");
        Ant a;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        instance.populateAntHills();
        a = instance.antAt(redAntPos);
        int expResult = 0;
        int result = instance.resting(a);
        assertEquals(expResult, result);
        a.setResting(4);
        result = instance.resting(a);
        expResult = 4;
        assertEquals(expResult, result);
    }

    /**
     * Test of direction method, of class AntGameModel.
     */
    @Test
    public void testDirection() {
        System.out.println("direction");
        Ant a;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        instance.populateAntHills();
        a = instance.antAt(redAntPos);
        Dir expResult = ZERO;
        Dir result = instance.direction(a);
        assertEquals(expResult, result);
        a.setDirection(ONE);
        result = instance.direction(a);
        expResult = ONE;
        assertEquals(expResult, result);
    }

    /**
     * Test of hasFood method, of class AntGameModel.
     */
    @Test
    public void testHasFood() {
        System.out.println("hasFood");
        Ant a = new Ant(RED, 0, centre);
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        boolean expResult = false;
        boolean result = instance.hasFood(a);
        assertEquals(expResult, result);
    }

    /**
     * Test of setState method, of class AntGameModel.
     */
    @Test
    public void testSetState() throws Exception {
        System.out.println("setState");
        Ant a = new Ant(RED, 0, centre);
        int state = 4;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        assertEquals(a.state(), 0);
        instance.setState(a, state);
        assertEquals(a.state(), 4);
    }

    /**
     * Test of setResting method, of class AntGameModel.
     */
    @Test
    public void testSetResting() {
        System.out.println("setResting");
        Ant a = new Ant(RED, 0, centre);
        int Resting = 9;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        instance.setResting(a, Resting);
        assertEquals(a.resting(),Resting );
    }

    /**
     * Test of setDirection method, of class AntGameModel.
     */
    @Test
    public void testSetDirection() {
        System.out.println("setDirection");
        Ant a = new Ant(RED, 0, centre);
        Dir d = ONE;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        instance.setDirection(a, d);
        assertEquals(a.direction(), d);
    }

    /**
     * Test of setHasFood method, of class AntGameModel.
     */
    @Test
    public void testSetHasFood() {
        System.out.println("setHasFood");
        Ant a = new Ant(RED, 0, centre);
        Boolean hasFood = true;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        instance.setHasFood(a, hasFood);
        boolean expResult = true;
        boolean result = instance.hasFood(a);
        assertEquals(expResult, result);
    }

    /**
     * Test of someAntIsAt method, of class AntGameModel.
     */
    @Test
    public void testSomeAntIsAt() {
        System.out.println("someAntIsAt");
        Ant a = new Ant(RED, 0, centre);
        Pos p = centre;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        instance.setAntAt(p, a);
        boolean expResult = true;
        boolean result = instance.someAntIsAt(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of antAt method, of class AntGameModel.
     */
    @Test
    public void testAntAtSetAntAt() {
        System.out.println("antAtSetAntAt");
        Ant a = new Ant(RED, 0, centre);
        Pos p = centre;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        instance.setAntAt(p, a);
        Ant expResult = a;
        Ant result = instance.antAt(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of clearAntAt method, of class AntGameModel.
     */
    @Test
    public void testClearAntAtSomeAntat() {
        System.out.println("clearAntAt");
        Ant a = new Ant(RED, 0, centre);
        Pos p = centre;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        instance.setAntAt(p, a);
        Ant expResult = a;
        Ant result = instance.antAt(p);
        assertEquals(expResult, result);
        instance.clearAntAt(p);
        assertFalse(instance.someAntIsAt(p));
    }

    /**
     * Test of antIsAlive method, of class AntGameModel.
     */
    @Test
    public void testAntIsAlive() {
        System.out.println("antIsAlive");
        int id = 0;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        boolean expResult = false;
        boolean result = instance.antIsAlive(id);
        assertEquals(expResult, result);
        instance.populateAntHills();
        expResult = true;
        result = instance.antIsAlive(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of findAnt method, of class AntGameModel.
     */
    @Test
    public void testFindAnt() {
        System.out.println("findAnt");
        int id0 = 0;
        int id1 = 1;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        instance.populateAntHills();
        Pos expResult = blackAntPos;
        Pos result = instance.findAnt(id0);
        assertEquals(expResult, result);
        expResult = redAntPos;
        result = instance.findAnt(id1);
        assertEquals(expResult, result);
    }

    /**
     * Test of KillAntAt method, of class AntGameModel.
     */
    @Test
    public void testKillAntAt() {
        System.out.println("KillAntAt");
        Pos p = redAntPos;
        int id = 1;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        instance.populateAntHills();
        boolean expResult = true;
        boolean result = instance.antIsAlive(id);
        assertEquals(expResult, result);
        instance.KillAntAt(p);
        expResult = false;
        result = instance.antIsAlive(id);
        assertEquals(expResult, result);
        result = instance.someAntIsAt(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of setMarkerAt method, of class AntGameModel.
     */
    @Test
    public void testCheckSetClearMarkerAt() {
        System.out.println("setMarkerAt");
        Pos p = centre;
        Ant.Colour c = RED;
        Mark.Marker m = MARKER1;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        boolean expResult = false;
        boolean result = instance.checkMarkerAt(p, c, m);
        assertEquals(expResult, result);
        instance.setMarkerAt(p, c, m);
        expResult = true;
        result = instance.checkMarkerAt(p, c, m);
        assertEquals(expResult, result);
        instance.clearMarkerAt(p, c, m);
        expResult = false;
        result = instance.checkMarkerAt(p, c, m);
        assertEquals(expResult, result);
    }

    /**
     * Test of setMarkerAt method, of class AntGameModel.
     */
    @Test
    public void testCheckSetClearAnyMarkerAt() {
        System.out.println("setMarkerAt");
        Pos p = centre;
        Ant.Colour c = RED;
        Mark.Marker m = MARKER1;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        boolean expResult = false;
        boolean result = instance.checkAnyMarkerAt(p, c);
        assertEquals(expResult, result);
        instance.setMarkerAt(p, c, m);
        expResult = true;
        result = instance.checkAnyMarkerAt(p, c);
        assertEquals(expResult, result);
        instance.clearMarkerAt(p, c, m);
        expResult = false;
        result = instance.checkAnyMarkerAt(p, c);
        assertEquals(expResult, result);
    }

    /**
     * Test of foodAt method, of class AntGameModel.
     */
    @Test
    public void testSetFoodAtFoodAt() {
        System.out.println("foodAt");
        Pos p = centre;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        int expResult = 0;
        int result = instance.foodAt(p);
        assertEquals(expResult, result);
        int food = 5;
        instance.setFoodAt(p, food);
        expResult = 5;
        result = instance.foodAt(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of antHillAt method, of class AntGameModel.
     */
    @Test
    public void testAntHillAt() {
        System.out.println("antHillAt");
        Pos notHill = centre;
        Pos isHill = redAntPos;
        Ant.Colour c = RED;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        boolean expResult = false;
        boolean result = instance.antHillAt(notHill, c);
        assertEquals(expResult, result);
        expResult = true;
        result = instance.antHillAt(isHill, c);
        assertEquals(expResult, result);
    }

    /**
     * Test of cellMatches method, of class AntGameModel.
     */
    @Test
    public void testCellMatchesFriendREDPassesCorrectly() {
        System.out.println("cellMatches");
        Pos p = redAntPos;
        Ant.Colour c = RED;
        Condition cond = new Friend();
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        instance.populateAntHills();
        boolean expResult = true;
        boolean result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of cellMatches method, of class AntGameModel.
     */
    @Test
    public void testCellMatchesFriendBLACKPassesCorrectly() {
        System.out.println("cellMatches");
        Pos p = blackAntPos;
        Ant.Colour c = BLACK;
        Condition cond = new Friend();
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        instance.populateAntHills();
        boolean expResult = true;
        boolean result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of cellMatches method, of class AntGameModel.
     */
    @Test
    public void testCellMatchesFriendBLACKFailsCorrectly() {
        System.out.println("cellMatches");
        Pos p = redAntPos;
        Ant.Colour c = BLACK;
        Condition cond = new Friend();
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        instance.populateAntHills();
        boolean expResult = false;
        boolean result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of cellMatches method, of class AntGameModel.
     */
    @Test
    public void testCellMatchesFriendREDFailsCorrectly() {
        System.out.println("cellMatches");
        Pos p = blackAntPos;
        Ant.Colour c = RED;
        Condition cond = new Friend();
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        instance.populateAntHills();
        boolean expResult = false;
        boolean result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of cellMatches method, of class AntGameModel.
     */
    @Test
    public void testCellMatchesFoeREDPassesCorrectly() {
        System.out.println("cellMatches");
        Pos p = blackAntPos;
        Ant.Colour c = RED;
        Condition cond = new Foe();
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        instance.populateAntHills();
        boolean expResult = true;
        boolean result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of cellMatches method, of class AntGameModel.
     */
    @Test
    public void testCellMatchesFoeREDFailsCorrectly() {
        System.out.println("cellMatches");
        Pos p = redAntPos;
        Ant.Colour c = RED;
        Condition cond = new Foe();
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        instance.populateAntHills();
        boolean expResult = false;
        boolean result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
    }
    /**
     * Test of cellMatches method, of class AntGameModel.
     */
    @Test
    public void testCellMatchesFoeBLACKPassesCorrectly() {
        System.out.println("cellMatches");
        Pos p = redAntPos;
        Ant.Colour c = BLACK;
        Condition cond = new Foe();
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        instance.populateAntHills();
        boolean expResult = true;
        boolean result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of cellMatches method, of class AntGameModel.
     */
    @Test
    public void testCellMatchesFoeBLACKFailsCorrectly() {
        System.out.println("cellMatches");
        Pos p = blackAntPos;
        Ant.Colour c = BLACK;
        Condition cond = new Foe();
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        instance.populateAntHills();
        boolean expResult = false;
        boolean result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of cellMatches method, of class AntGameModel.
     */
    @Test
    public void testCellMatchesFriendWithFood() {
        System.out.println("cellMatches");
        Pos p = blackAntPos;
        Ant.Colour c = BLACK;
        Condition cond = new FriendWithFood();
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        instance.populateAntHills();
        boolean expResult = false;
        boolean result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
        instance.antAt(p).setHasFood(true);
        expResult = true;
        result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
        p = redAntPos;
        c = RED;
        expResult = false;
        result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
        instance.antAt(p).setHasFood(true);
        expResult = true;
        result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
        p = blackAntPos;
        c = RED;
        expResult = false;
        result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
        p = redAntPos;
        c = BLACK;
        expResult = false;
        result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of cellMatches method, of class AntGameModel.
     */
    @Test
    public void testCellMatchesFoeWithFood() {
      System.out.println("cellMatches");
        Pos p = blackAntPos;
        Ant.Colour c = BLACK;
        Condition cond = new FoeWithFood();
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        instance.populateAntHills();
        boolean expResult = false;
        boolean result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
        instance.antAt(p).setHasFood(true);
        expResult = false;
        result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
        p = redAntPos;
        c = RED;
        expResult = false;
        result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
        instance.antAt(p).setHasFood(true);
        expResult = false;
        result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
        p = blackAntPos;
        c = RED;
        expResult = true;
        result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
        p = redAntPos;
        c = BLACK;
        expResult = true;
        result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of cellMatches method, of class AntGameModel.
     */
    @Test
    public void testCellMatchesFood() {
        System.out.println("cellMatches");
        Pos p = centre;
        Ant.Colour c = BLACK;
        Condition cond = new Food();
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        boolean expResult = false;
        boolean result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
        int food = 5;
        instance.setFoodAt(p, food);
        expResult = true;
        result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of cellMatches method, of class AntGameModel.
     */
    @Test
    public void testCellMatchesRock() {
        System.out.println("cellMatches");
        Pos p = centre;
        Ant.Colour c = BLACK;
        Condition cond = new Rock();
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        boolean expResult = false;
        boolean result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
        p = new Pos(0,0);
        expResult = true;
        result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of cellMatches method, of class AntGameModel.
     */
    @Test
    public void testCellMatchesMarker() {
        System.out.println("cellMatches");
        Pos p = centre;
        Ant.Colour c = BLACK;
        Condition cond = MARKER1;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        boolean expResult = false;
        boolean result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
        instance.setMarkerAt(p, c, MARKER1);
        expResult = true;
        result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
        cond = MARKER0;
        expResult = false;
        result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
        instance.setMarkerAt(p, c, MARKER0);
        expResult = true;
        result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
        c = RED;
        expResult = false;
        result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of cellMatches method, of class AntGameModel.
     */
    @Test
    public void testCellMatchesFoeMarker() {
        System.out.println("cellMatches");
        Pos p = centre;
        Ant.Colour foe = BLACK;
        Ant.Colour friend = RED;
        Condition cond = new FoeMarker();
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        boolean expResult = false;
        boolean result = instance.cellMatches(p, foe, cond);
        assertEquals(expResult, result);
        instance.setMarkerAt(p, foe, MARKER1);
        expResult = false;
        result = instance.cellMatches(p, foe, cond);
        assertEquals(expResult, result);
        instance.setMarkerAt(p, friend, MARKER1);
        expResult = true;
        result = instance.cellMatches(p, friend, cond);
        assertEquals(expResult, result);      
    }
    
    /**
     * Test of cellMatches method, of class AntGameModel.
     */
    @Test
    public void testCellMatchesHome() {
        System.out.println("cellMatches");
        Pos p = blackAntPos;
        Ant.Colour c = BLACK;
        Condition cond = new Home();
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        boolean expResult = true;
        boolean result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
        p = redAntPos;
        expResult = false;
        result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
        c = RED;
        expResult = true;
        result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
        p = blackAntPos;
        expResult = false;
        result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of cellMatches method, of class AntGameModel.
     */
    @Test
    public void testCellMatchesFoeHome() {
        System.out.println("cellMatches");
        Pos p = blackAntPos;
        Ant.Colour c = BLACK;
        Condition cond = new FoeHome();
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        boolean expResult = false;
        boolean result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
        p = redAntPos;
        expResult = true;
        result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
        c = RED;
        expResult = false;
        result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
        p = blackAntPos;
        expResult = true;
        result = instance.cellMatches(p, c, cond);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getInstruction method, of class AntGameModel.
     */
    @Test
    public void testGetInstruction() {
        System.out.println("getInstruction");
        Ant.Colour c = RED;
        int state = 0;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMap, brain, brain);
        Instruction result = instance.getInstruction(c, state);
        assertTrue(result instanceof Sense);
        state = 1;
        result = instance.getInstruction(c, state);
        assertTrue(result instanceof Move);
        state = 2;
        result = instance.getInstruction(c, state);
        assertTrue(result instanceof Turn);
    }

    /**
     * Test of adjacentAnts method, of class AntGameModel.
     */
    @Test
    public void testAdjacentAnts() {
        System.out.println("adjacentAnts");
        Pos p = centre;
        Ant.Colour c = BLACK;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMapSurround, brain, brain);
        instance.populateAntHills();
        int expResult = 6;
        int result = instance.adjacentAnts(p, c);
        assertEquals(expResult, result);
        p = redAntPos;
        expResult = 2;
        result = instance.adjacentAnts(p, c);
        assertEquals(expResult, result);
        c = RED;
        p = new Pos(2,3);
        expResult = 2;
        result = instance.adjacentAnts(p, c);
        assertEquals(expResult, result);
        p = new Pos(3,2);
        expResult = 2;
        result = instance.adjacentAnts(p, c);
        assertEquals(expResult, result);
        p = new Pos(1,3);
        expResult = 1;
        result = instance.adjacentAnts(p, c);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkForSurroundedAntAt method, of class AntGameModel.
     */
    @Test
    public void testCheckForSurroundedAntAt() {
        System.out.println("checkForSurroundedAntAt");
        Pos p = centre;
        Ant.Colour c = BLACK;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMapSurround, brain, brain);
        instance.populateAntHills();
        assertTrue(instance.someAntIsAt(p));
        int id = instance.antAt(p).getID();
        instance.checkForSurroundedAntAt(p);
        assertFalse(instance.someAntIsAt(p));
        assertFalse(instance.antIsAlive(id));
        
    }

    /**
     * Test of checkForSurroundedAnts method, of class AntGameModel.
     */
    @Test
    public void testCheckForSurroundedAnts() {
        System.out.println("checkForSurroundedAnts");
        Pos p = new Pos(3,2);
        Ant.Colour c = BLACK;
        AntGameModel instance = new AntGameModel(new AntGameController(), testMapSurround, brain, brain);
        instance.populateAntHills();
        assertTrue(instance.someAntIsAt(centre));
        int id = instance.antAt(centre).getID();
        instance.checkForSurroundedAnts(p);
        assertFalse(instance.someAntIsAt(centre));
        assertFalse(instance.antIsAlive(id));
    }

    /**
     * Test of playRound method, of class AntGameModel. Compares provided dump files
     * to the dump files from this game.
     */
    @Test
    public void testPlayRoundFor1000RoundsMatchesDump() throws IOException, InvalidMapSyntaxException, BrainParser.InvalidBrainSyntaxException {
        System.out.println("playRound1000");
        mapParser = new MapParser();
        String[] mapStringArray = Utils.fileToStringArray("C:\\Users\\owner\\Documents\\NetBeansProjects\\Software-Engineering-Source-Code\\AntGame\\src\\test\\java\\tinyWorldSimTest\\tiny.world");
        char[][] charMap = mapParser.parseMap(mapStringArray, false);
        mapClass = new Map(charMap);
        MapCell[][] cellMap = mapClass.getCellMap();
        BrainParser bpr = new BrainParser();
        String[] brainStringArray = Utils.fileToStringArray("C:\\Users\\owner\\Documents\\NetBeansProjects\\Software-Engineering-Source-Code\\AntGame\\src\\test\\java\\tinyWorldSimTest\\sample.ant");
        brain = bpr.parseBrain(brainStringArray);
        AntGameModel instance = new AntGameModel(new AntGameController(), cellMap, brain, brain);
        instance.populateAntHills();
        instance.playGame(1000, true);
        String[] expResult = Utils.fileToStringArray("C:\\Users\\owner\\Documents\\NetBeansProjects\\Software-Engineering-Source-Code\\AntGame\\src\\test\\java\\tinyWorldSimTest\\testDump\\dump0-1000.0-1000");
        String[] result = Utils.fileToStringArray("C:\\Users\\owner\\Documents\\NetBeansProjects\\Software-Engineering-Source-Code\\AntGame\\src\\test\\java\\tinyWorldSimTest\\myDump\\myDump1000");
        assertArrayEquals(expResult, result);
    }   
    
    /**
     * Test of playRound method, of class AntGameModel. Compares provided dump files
     * to the dump files from this game.
     */
    @Test
    public void testPlayRoundFor10000RoundsMatchesDump() throws IOException, InvalidMapSyntaxException, BrainParser.InvalidBrainSyntaxException {
        System.out.println("playRound10000");
        mapParser = new MapParser();
        String[] mapStringArray = Utils.fileToStringArray("C:\\Users\\owner\\Documents\\NetBeansProjects\\Software-Engineering-Source-Code\\AntGame\\src\\test\\java\\tinyWorldSimTest\\tiny.world");
        char[][] charMap = mapParser.parseMap(mapStringArray, false);
        mapClass = new Map(charMap);
        MapCell[][] cellMap = mapClass.getCellMap();
        BrainParserRob bpr = new BrainParserRob();
        String[] brainStringArray = Utils.fileToStringArray("C:\\Users\\owner\\Documents\\NetBeansProjects\\Software-Engineering-Source-Code\\AntGame\\src\\test\\java\\tinyWorldSimTest\\sample.ant");
        brain = bpr.parseBrain(brainStringArray);
        AntGameModel instance = new AntGameModel(new AntGameController(), cellMap, brain, brain);
        instance.populateAntHills();
        instance.playGame(10000, false);
        String[] expResult = Utils.fileToStringArray("C:\\Users\\owner\\Documents\\NetBeansProjects\\Software-Engineering-Source-Code\\AntGame\\src\\test\\java\\tinyWorldSimTest\\testDump\\dumpall");
        String[] result = Utils.fileToStringArray("C:\\Users\\owner\\Documents\\NetBeansProjects\\Software-Engineering-Source-Code\\AntGame\\src\\test\\java\\tinyWorldSimTest\\myDump\\myMassiveDump");
        assertArrayEquals(expResult, result);
    }
    
    /**
     * Test of playRound method, of class AntGameModel. Compares provided dump files
     * to the dump files from this game.
     */
    @Test
    public void testPlayRoundFor300000RoundsFinishes() throws IOException, InvalidMapSyntaxException, BrainParser.InvalidBrainSyntaxException {
        System.out.println("playRound300000");
        mapParser = new MapParser();
        String[] mapStringArray = Utils.fileToStringArray("C:\\Users\\owner\\Documents\\NetBeansProjects\\Software-Engineering-Source-Code\\AntGame\\src\\test\\java\\tinyWorldSimTest\\tiny.world");
        char[][] charMap = mapParser.parseMap(mapStringArray, false);
        mapClass = new Map(charMap);
        MapCell[][] cellMap = mapClass.getCellMap();
        BrainParserRob bpr = new BrainParserRob();
        String[] brainStringArray = Utils.fileToStringArray("C:\\Users\\owner\\Documents\\NetBeansProjects\\Software-Engineering-Source-Code\\AntGame\\src\\test\\java\\tinyWorldSimTest\\sample.ant");
        brain = bpr.parseBrain(brainStringArray);
        AntGameModel instance = new AntGameModel(new AntGameController(), cellMap, brain, brain);
        instance.populateAntHills();
        instance.playGame(300000);
    }
}