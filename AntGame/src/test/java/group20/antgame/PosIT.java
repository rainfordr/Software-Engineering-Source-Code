/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.antgame;

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
public class PosIT {
    private Pos pos11, pos22;
    
    public PosIT() {
        pos11 = new Pos(1,1);
        pos22 = new Pos(2,2);
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
     * Test of getX method, of class Pos.
     */
    @Test
    public void testGetXReturns1() {
        System.out.println("getX");
        Pos instance = pos11;
        int expResult = 1;
        int result = instance.getX();
        assertEquals(expResult, result);
    }

    /**
     * Test of getY method, of class Pos.
     */
    @Test
    public void testGetYReturns2() {
        System.out.println("getY");
        Pos instance = pos22;
        int expResult = 2;
        int result = instance.getY();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Pos.
     */
    @Test
    public void testEqualCorrectWithSameValues() {
        System.out.println("equals");
        Object obj = new Pos(1,1);
        Pos instance = pos11;
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Pos.
     */
    @Test
    public void testEqualIncorrectWithDifferentValues() {
        System.out.println("equals");
        Object obj = new Pos(1,1);
        Pos instance = pos22;
        boolean result = instance.equals(obj);
        assertFalse(result);
    }    
}
