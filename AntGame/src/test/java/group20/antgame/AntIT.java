/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.antgame;

import group20.exceptions.InvalidStateException;
import group20.Instructions.Dir;
import static group20.Instructions.Dir.FOUR;
import static group20.Instructions.Dir.ZERO;
import static group20.antgame.Ant.Colour.BLACK;
import static group20.antgame.Ant.Colour.RED;
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
public class AntIT {
    private Ant blackAnt;
    private Ant redAnt;
    public AntIT() {
        blackAnt = new Ant(BLACK, 0, new Pos(1,1));
        redAnt = new Ant(RED, 0, new Pos(1,1));
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
     * Test of state method, of class Ant.
     */
    @org.junit.Test
    public void testRedStateReturnsExpectedValueWhenFirstInstantiated() {
        System.out.println("Red state");
        Ant instance = new Ant(RED, 5, new Pos(1,1));
        int expResult = 0;
        int result = instance.state();
        assertEquals(expResult, result);        
    }
    
    /**
     * Test of state method, of class Ant.
     */
    @org.junit.Test
    public void testBlackStateReturnsExpectedValueWhenFirstInstantiated() {
        System.out.println("Black state");
        Ant instance = new Ant(BLACK, 6, new Pos(1,1));
        int expResult = 0;
        int result = instance.state();
        assertEquals(expResult, result);        
    }
    
    /**
     * Test of state method, of class Ant.
     */
    @org.junit.Test
    public void testBlackStateDoesntReturnUnexpectedValueWhenFirstInstantiated() {
        System.out.println("Black state");
        Ant instance = new Ant(BLACK, 6, new Pos(1,1));
        int result = instance.state();
        assertFalse(result != 0);  
    }
    
    /**
     * Test of state method, of class Ant.
     */
    @org.junit.Test
    public void testRedStateDoesntReturnUnexpectedValueWhenFirstInstantiated() {
        System.out.println("Black state");
        Ant instance = new Ant(RED, 7, new Pos(1,1));
        int result = instance.state();
        assertFalse(result != 0);  
    }
    
    /**
     * Test of state method, of class Ant.
     */
    @org.junit.Test
    public void testRedStateReturnsExpectedValueAfterSettingState() {
        System.out.println("Black state");
        Ant instance = new Ant(RED, 7, new Pos(1,1));
        int expected = 8;
        try{
        instance.setState(expected);
        }catch(InvalidStateException ex){
            fail("Invalid State Exception Thrown");
        }
        int result = instance.state();
        assertEquals(expected, result);  
    }
    
    /**
     * Test of state method, of class Ant.
     */
    @org.junit.Test
    public void testBlackStateReturnsExpectedValueAfterSettingState() {
        System.out.println("Black state");
        Ant instance = new Ant(BLACK, 7, new Pos(1,1));
        int expected = 9;
        try{
        instance.setState(expected);
        }catch(InvalidStateException ex){
            fail("Invalid State Exception Thrown");
        }
        int result = instance.state();
        assertEquals(expected, result);  
    }

    /**
     * Test of colour method, of class Ant.
     */
    @org.junit.Test
    public void testColourOfBlackAntReturnsBlack() {
        System.out.println("colour");
        Ant instance = blackAnt;
        Ant.Colour expResult = BLACK;
        Ant.Colour result = instance.colour();
        assertEquals(expResult, result);
    }
    
     /**
     * Test of colour method, of class Ant.
     */
    @org.junit.Test
    public void testColourOfRedAntReturnsRed() {
        System.out.println("colour");
        Ant instance = redAnt;
        Ant.Colour expResult = RED;
        Ant.Colour result = instance.colour();
        assertEquals(expResult, result);
    }

    /**
     * Test of resting method, of class Ant.
     */
    @org.junit.Test
    public void testRestingCorrectAtInstantiation() {
        System.out.println("resting");
        Ant instance = blackAnt;
        int expResult = 0;
        int result = instance.resting();
        assertEquals(expResult, result);
    }

    /**
     * Test of direction method, of class Ant.
     */
    @org.junit.Test
    public void testDirectionCorrectAtInstantiation() {
        System.out.println("direction");
        Ant instance = blackAnt;
        Dir expResult = ZERO;
        Dir result = instance.direction();
        assertEquals(expResult, result);
    }

    /**
     * Test of hasFood method, of class Ant.
     */
    @org.junit.Test
    public void testHasFoodCorrectAtInstatiation() {
        System.out.println("hasFood");
        Ant instance = blackAnt;
        boolean expResult = false;
        boolean result = instance.hasFood();
        assertEquals(expResult, result);
    }

    /**
     * Test of setState method, of class Ant.
     */
    @org.junit.Test
    public void testSetStateMethodReturnsFive() {
        System.out.println("setState");
        int state = 5;
        Ant instance = new Ant(BLACK, 0, new Pos(1,1));
        try{
            instance.setState(state);
        }catch(InvalidStateException ex){
            fail("Invalid State Exception Thrown");
        }
        assertEquals(state, instance.state());        
    }
    
    /**
     * Test of setState method, of class Ant.
     */
    @org.junit.Test
    public void testSetStateMethodThrowsInvalidStateExceptionWithInputLTZero() {
        System.out.println("setState");
        int state = -1;
        Ant instance = new Ant(BLACK, 0, new Pos(1,1));
        boolean thrown = false;
        try{
            instance.setState(state);
        }catch(InvalidStateException ex){
            thrown = true;
        }
        assertTrue(thrown);        
    }
    
    /**
     * Test of setState method, of class Ant.
     */
    @org.junit.Test
    public void testSetStateMethodThrowsInvalidStateExceptionWithInputGT9999() {
        System.out.println("setState");
        int state = 10000;
        Ant instance = new Ant(BLACK, 0, new Pos(1,1));
        boolean thrown = false;
        try{
            instance.setState(state);
        }catch(InvalidStateException ex){
            thrown = true;
        }
        assertTrue(thrown);        
    }
    
    

    /**
     * Test of setResting method, of class Ant.
     */
    @org.junit.Test
    public void testSetRestingSetsRestingTo14() {
        System.out.println("setResting");
        int resting = 14;
        Ant instance = blackAnt;
        instance.setResting(resting);
        assertEquals(resting, instance.resting());
    }

    /**
     * Test of setDirection method, of class Ant.
     */
    @org.junit.Test
    public void testSetDirectionSetsDirectionToFOUR() {
        System.out.println("setDirection");
        Dir direction = FOUR;
        Ant instance = blackAnt;
        assertTrue(instance.direction() == ZERO);
        instance.setDirection(direction);
        assertEquals(direction, FOUR);
    }

    /**
     * Test of setHasFood method, of class Ant.
     */
    @org.junit.Test
    public void testSetHasFoodSetsFoodToTrue() {
        System.out.println("setHasFood");
        boolean hasFood = true;
        Ant instance = blackAnt;
        assertFalse(instance.hasFood());
        instance.setHasFood(hasFood);
        assertTrue(instance.hasFood());
    }
    
        /**
     * Test of setHasFood method, of class Ant.
     */
    @org.junit.Test
    public void testSetHasFoodSetsFoodToTrueThenFalseThenTrue() {
        System.out.println("setHasFood");
        boolean hasFood = true;
        Ant instance = blackAnt;
        assertFalse(instance.hasFood());
        instance.setHasFood(hasFood);
        assertTrue(instance.hasFood());
        instance.setHasFood(false);
        assertFalse(instance.hasFood());
        instance.setHasFood(hasFood);
        assertTrue(instance.hasFood());
    }

    /**
     * Test of position method, of class Ant.
     */
    @org.junit.Test
    public void testPositionAtInstantiation() {
        System.out.println("position");
        Ant instance = blackAnt;
        Pos expResult = new Pos(1,1);
        Pos result = instance.position();
        assertEquals(expResult, result);
    }
    
        /**
     * Test of position method, of class Ant.
     */
    @org.junit.Test
    public void testPositionAfterSettingPosition() {
        System.out.println("position");
        Ant instance = blackAnt;
        Pos expResult = new Pos(2,2);
        Pos result = instance.position();
        assertEquals(result, new Pos(1,1));
        instance.setPosition(new Pos(2,2));
        result = instance.position();
        assertEquals(expResult, expResult);
    }

    /**
     * Test of setPosition method, of class Ant.
     */
    @org.junit.Test
    public void testSetPositionToFourFour() {
        System.out.println("setPosition");
        Pos position = new Pos(4,4);
        Ant instance = blackAnt;
        assertFalse(instance.position().equals(position));
        instance.setPosition(position);
        assertEquals(position, instance.position());
    }
    
}
