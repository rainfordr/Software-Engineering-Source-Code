/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.antgame;

import group20.Instructions.Mark;
import group20.Instructions.Mark.Marker;
import static group20.Instructions.Mark.Marker.*;
import group20.antgame.Ant.Colour;
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
public class MapCellIT {
    MapCell clearCell;
    MapCell rockyCell;
    MapCell startCell, moveToCell, redHillCell, blackHillCell;
    Pos startPos;
    Ant testAnt, blackAnt, redAnt;
    
    public MapCellIT() {
        clearCell = new MapCell(new Pos(1,2));
        rockyCell = new MapCell(new Pos(2,3));
        startCell = new MapCell(startPos);
        moveToCell = new MapCell(new Pos(1,1));
        redHillCell = new MapCell(new Pos(3,3));
        blackHillCell = redHillCell = new MapCell(new Pos(4,4));
        startPos = new Pos(0,0);
        testAnt = new Ant(RED, 0, startPos);
        redAnt = new Ant(RED, 0, startPos);
        blackAnt = new Ant(BLACK, 0, startPos);
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
     * Test of isRocky method, of class MapCell.
     */
    @Test
    public void testIsRockyOnRockyCellReturnsTrue() {
        System.out.println("isRocky");
        MapCell instance = rockyCell;
        boolean expResult = true;
        boolean result = instance.isRocky();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isRocky method, of class MapCell.
     */
    @Test
    public void testIsRockyOnClearCellReturnsFalse() {
        System.out.println("isRocky");
        MapCell instance = clearCell;
        boolean expResult = false;
        boolean result = instance.isRocky();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPos method, of class MapCell.
     */
    @Test
    public void testGetPosReturnsCorrectPositions() {
        Pos pos12 = new Pos(1,2);
        Pos pos23 = new Pos(2,3);
        System.out.println("getPos");
        MapCell instance = clearCell;
        Pos expResult = pos12;
        Pos result = instance.getPos();
        assertEquals(expResult, result);
        instance = rockyCell;
        expResult = pos23;
        result = instance.getPos();
        assertEquals(expResult, result);
    }

    /**
     * Test of putAnt method, of class MapCell.
     */
    @Test
    public void testPutAnt() {
        System.out.println("putAnt");
        Ant ant = testAnt;
        MapCell instance = moveToCell;
        assertFalse(instance.hasAnt());
        instance.putAnt(ant);
    }

    /**
     * Test of clearAnt method, of class MapCell.
     */
    @Test
    public void testClearAntWorksAfterPlacingAnt() {
        System.out.println("clearAnt");
        MapCell instance = startCell;
        assertFalse(instance.hasAnt());
        instance.putAnt(testAnt);
        assertTrue(instance.hasAnt());
        instance.clearAnt();
        assertFalse(instance.hasAnt());
    }

    /**
     * Test of hasAnt method, of class MapCell.
     */
    @Test
    public void testHasAntFalseBeforeMovingAntAndTrueAfter() {
        System.out.println("hasAnt");
        MapCell instance = startCell;
        boolean expResult = false;
        boolean result = instance.hasAnt();
        assertEquals(expResult, result);
        instance.putAnt(testAnt);
        expResult = true;
        result = instance.hasAnt();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAnt method, of class MapCell.
     */
    @Test
    public void testGetAnt() {
        System.out.println("getAnt");
        MapCell instance = startCell;
        Ant expResult = testAnt;
        assertFalse(instance.hasAnt());
        instance.putAnt(testAnt);
        assertTrue(instance.hasAnt());
        Ant result = instance.getAnt();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFood method, of class MapCell.
     */
    @Test
    public void testGetFoodSetFood() {
        System.out.println("setFood");
        int food = 0;
        MapCell instance = startCell;
        int result = instance.getFood();
        assertEquals(food, result);
        food = 3;
        instance.setFood(food);
        result = instance.getFood();
        assertEquals(food, result);
    }

    /**
     * Test of setMarker method, of class MapCell.
     */
    @Test
    public void testSetMarker1RedCheckMarker1() {
        System.out.println("setMarker1");
        Mark.Marker marker = MARKER1;
        Ant.Colour c = redAnt.colour();
        MapCell instance = startCell;
        assertFalse(instance.checkMarker(marker, c));
        instance.setMarker(marker, c);
        boolean result = instance.checkMarker(marker, c);
        assertTrue(result);
    }
    
    /**
     * Test of setMarker method, of class MapCell.
     */
    @Test
    public void testSetMarker2BlackCheckMarker2() {
        System.out.println("setMarker2");
        Mark.Marker marker = MARKER2;
        Ant.Colour c = blackAnt.colour();
        MapCell instance = startCell;
        assertFalse(instance.checkMarker(marker, c));
        instance.setMarker(marker, c);
        boolean result = instance.checkMarker(marker, c);
        assertTrue(result);
    }
    
    /**
     * Test of setMarker method, of class MapCell.
     */
    @Test
    public void testSetMarker1BlackCheckMarker2NotFound() {
        System.out.println("setMarker1");
        Mark.Marker marker = MARKER1;
        Marker marker2 = MARKER2;
        Ant.Colour c = testAnt.colour();
        MapCell instance = startCell;
        assertFalse(instance.checkMarker(marker, c));
        instance.setMarker(marker, c);
        boolean result = instance.checkMarker(marker2, c);
        assertFalse(result);
    }
    
    /**
     * Test of setMarker method, of class MapCell.
     */
    @Test
    public void testSetMarker2BlackClearMarkerCheckMarker2() {
        System.out.println("setMarker2");
        Mark.Marker marker = MARKER2;
        Ant.Colour c = blackAnt.colour();
        MapCell instance = startCell;
        assertFalse(instance.checkMarker(marker, c));
        instance.setMarker(marker, c);
        boolean result = instance.checkMarker(marker, c);
        assertTrue(result);
        instance.clearMarker(marker, c);
        result = instance.checkMarker(marker, c);
        assertFalse(instance.checkMarker(marker, c));
    }
    
    /**
     * Test of setMarker method, of class MapCell.
     */
    @Test
    public void testSetMarker1BlackClearMarkerCheckMarker1DoesntAffectRedMarkers() {
        System.out.println("setMarker2");
        Marker marker0 = MARKER0;
        Marker marker1 = MARKER1;
        Ant.Colour black = blackAnt.colour();
        Colour red = redAnt.colour();
        MapCell instance = startCell;
        assertFalse(instance.checkMarker(marker0, black) || instance.checkMarker(marker0, red) || instance.checkMarker(marker1, red) || instance.checkMarker(marker1, black));
        instance.setMarker(marker0, black);
        instance.setMarker(marker0, red);
        instance.setMarker(marker1, red);
        instance.setMarker(marker1, black);
        boolean result = instance.checkMarker(marker0, black);
        assertTrue(result);
        assertTrue(instance.checkMarker(marker0, red));
        instance.clearMarker(marker0, black);
        assertFalse(instance.checkMarker(marker0, black));
        assertTrue(instance.checkMarker(marker0, red));
        assertTrue(instance.checkMarker(marker1, red));
        assertTrue(instance.checkMarker(marker1, black));
    }

    /**
     * Test of clearMarker method, of class MapCell.
     */
    @Test
    public void testClearMarker() {
        System.out.println("clearMarker");
        Mark.Marker marker = MARKER4;
        Ant.Colour c = blackAnt.colour();
        MapCell instance = clearCell;
        instance.setMarker(marker, c);
        assertTrue(instance.checkMarker(marker, c));
        instance.clearMarker(marker, c);
        assertFalse(instance.checkMarker(marker, c));
    }

    /**
     * Test of isMarkedByColour method, of class MapCell.
     */
    @Test
    public void testIsMarkedByColour() {
        System.out.println("isMarkedByColour");
        Ant.Colour black = blackAnt.colour();
        Colour red = redAnt.colour();
        MapCell instance = clearCell;
        boolean expResult = false;
        boolean result = instance.isMarkedByColour(black);
        assertEquals(expResult, result);
        instance.setMarker(MARKER0, red);
        expResult = true;
        result = instance.isMarkedByColour(red);
        assertEquals(expResult, result);
        expResult = false;
        result = instance.isMarkedByColour(black);
        assertEquals(expResult, result);
    }

    /**
     * Test of isAntHillCell method, of class MapCell.
     */
    @Test
    public void testIsBlackAntHillCell() {
        System.out.println("isAntHillCell");
        Ant.Colour c = BLACK;
        MapCell instance = blackHillCell;
        boolean expResult = false;
        boolean result = instance.isAntHillCell(c);
        assertEquals(expResult, result);
        instance.setAntHillCell(c);
        expResult = true;
        result = instance.isAntHillCell(c);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isAntHillCell method, of class MapCell.
     */
    @Test
    public void testIsRedAntHillCell() {
        System.out.println("isAntHillCell");
        Ant.Colour c = RED;
        MapCell instance = redHillCell;
        boolean expResult = false;
        boolean result = instance.isAntHillCell(c);
        assertEquals(expResult, result);
        instance.setAntHillCell(c);
        expResult = true;
        result = instance.isAntHillCell(c);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isAntHillCell method, of class MapCell.
     */
    @Test
    public void testIsRedAntHillCellNotFoundWhenBlackIsSet() {
        System.out.println("isAntHillCell");
        Ant.Colour c = BLACK;
        Colour red = RED;
        MapCell instance = blackHillCell;
        boolean expResult = false;
        boolean result = instance.isAntHillCell(c);
        assertEquals(expResult, result);
        instance.setAntHillCell(c);
        expResult = false;
        result = instance.isAntHillCell(red);
        assertEquals(expResult, result);
    }
    
}
