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
public class UtilsTest {
    private String tinyWorld =  "10\n" +
                                "10\n" +
                                "# # # # # # # # # #\n" +
                                " # 9 9 . . . . 3 3 #\n" +
                                "# 9 # . - - - - - #\n" +
                                " # . # - - - - - - #\n" +
                                "# . . 5 - - - - - #\n" +
                                " # + + + + + 5 . . #\n" +
                                "# + + + + + + # . #\n" +
                                " # + + + + + . # 9 #\n" +
                                "# 3 3 . . . . 9 9 #\n" +
                                " # # # # # # # # # #";
    
    private String[] tinyWorldArray = new String[12];
    
    public UtilsTest() {
        tinyWorldArray[0] = "10";
        tinyWorldArray[1] = "10";
        tinyWorldArray[2] = "# # # # # # # # # #";
        tinyWorldArray[3] = " # 9 9 . . . . 3 3 #";
        tinyWorldArray[4] = "# 9 # . - - - - - #";
        tinyWorldArray[5] = " # . # - - - - - - #";
        tinyWorldArray[6] = "# . . 5 - - - - - #";
        tinyWorldArray[7] = " # + + + + + 5 . . #";
        tinyWorldArray[8] = "# + + + + + + # . #";
        tinyWorldArray[9] = " # + + + + + . # 9 #";
        tinyWorldArray[10] = "# 3 3 . . . . 9 9 #";
        tinyWorldArray[11] = " # # # # # # # # # #";
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
     * Test of fileToString method, of class Utils.
     */
    @Test
    public void testFileToString() throws Exception {
        System.out.println("fileToString");
        String pathname = "C:\\Users\\owner\\Documents\\NetBeansProjects\\Software-Engineering-Source-Code\\AntGame\\src\\test\\java\\tinyWorldSimTest\\tiny.world";
        String expResult = tinyWorld;
        String result = Utils.fileToString(pathname);
        assertEquals(expResult, result);
    }

    /**
     * Test of fileToStringArray method, of class Utils.
     */
    @Test
    public void testFileToStringArray() throws Exception {
        System.out.println("fileToStringArray");
        String pathname = "C:\\Users\\owner\\Documents\\NetBeansProjects\\Software-Engineering-Source-Code\\AntGame\\src\\test\\java\\tinyWorldSimTest\\tiny.world";
        String[] expResult = tinyWorldArray;
        String[] result = Utils.fileToStringArray(pathname);
        assertArrayEquals(expResult, result);
    }
    
}
