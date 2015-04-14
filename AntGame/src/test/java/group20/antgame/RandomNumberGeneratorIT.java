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
public class RandomNumberGeneratorIT {

    int[] expectedList;

    public RandomNumberGeneratorIT() {
        this.expectedList = new int[]{7193, 2932, 10386, 5575, 100, 15976, 430,
            9740, 9449, 1636, 11030, 9848, 13965, 16051, 14483, 6708, 5184,
            15931, 7014, 461, 11371, 5856, 2136, 9139, 1684, 15900, 10236,
            13297, 1364, 6876, 15687, 14127, 11387, 13469, 11860, 15589, 14209,
            16327, 7024, 3297, 3120, 842, 12397, 9212, 5520, 4983, 7205, 7193,
            4883, 7712, 6732, 7006, 10241, 1012, 15227, 9910, 14119, 15124,
            6010, 13191, 5820, 14074, 5582, 5297, 10387, 4492, 14468, 7879,
            8839, 12668, 5436, 8081, 4900, 10723, 10360, 1218, 11923, 3870,
            12071, 3574, 12232, 15592, 12909, 9711, 6638, 2488, 12725, 16145,
            9746, 9053, 5881, 3867, 10512, 4312, 8529, 1576, 15803, 5498, 12730,
            7397};
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
     * Test of randomInt method, of class RandomNumberGenerator.
     */
    @Test
    public void testRandomInt() {
        System.out.println("randomInt");
        int n = 16384;
        RandomNumberGenerator instance = new RandomNumberGenerator(12345);
        int[] expResult = expectedList;
        int[] result = new int[100];
        for (int i = 0; i < 100; i++) {
            result[i] = instance.randomInt(n);
        }
        assertArrayEquals(expResult, result);

    }
    
    @Test
    public void testRandInt(){
        System.out.println("randInt");
        int n = 12345;
        RandNumGen2 instance = new RandNumGen2(12345);
        int[] expResult = expectedList;
        int[] result = new int[100];
        for (int i = 0; i < 100; i++) {
            result[i] = instance.randInt(16385);
        }
        assertArrayEquals(expResult, result);
    }

}
