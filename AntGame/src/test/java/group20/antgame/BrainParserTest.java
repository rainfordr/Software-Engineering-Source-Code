package group20.antgame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import group20.Conditions.Food;
import group20.Instructions.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import group20.antgame.*;
import java.util.ArrayList;

/**
 *
 * @author Patrick
 */
public class BrainParserTest {

    BrainParser bp = new BrainParser();
    ArrayList<String> testInstructions = new ArrayList<>();
    //create new any brain string here to test on


    /**
     *
     * @throws BrainParser.InvalidBrainSyntaxException
     */
    @Test
    public void TestParseAllInstructions() throws Exception {
        
        testInstructions.add("Sense Ahead 1 3 Food ");
        testInstructions.add("pickUp 8 0 ");
        testInstructions.add("Move 2 0 ");
        testInstructions.add("Flip 3 4 5 ");
        testInstructions.add("Drop 4");
        testInstructions.add("Turn Left 0");
        testInstructions.add("Turn Right 0");
        testInstructions.add("Mark 0 1");
        testInstructions.add("Unmark 0 1");
        
        
        String instructions = "";

        for (int i = 0; i < testInstructions.size(); i++) {
            String s = testInstructions.get(i);
            instructions = instructions + s;
        }

        Instruction[] result = new Instruction[bp.parseBrain(instructions).length];
        System.arraycopy(bp.parseBrain(instructions), 0, result, 0, bp.parseBrain(instructions).length);
      
        assertEquals(new Sense(Sense.SenseDir.AHEAD, 1, 3, new Food()), result[0]);
        assertEquals(new PickUp(8, 0), result[1]);
        assertEquals(new Move(2, 0), result[2]);
        assertEquals(new Flip(3, 4, 5), result[3]);
        assertEquals(new Drop(4), result[4]);
        assertEquals(new Turn(Turn.LeftOrRight.LEFT, 0), result[5]);
        assertEquals(new Turn(Turn.LeftOrRight.RIGHT, 0), result[6]);
        assertEquals(new Mark(Mark.Marker.MARKER0, 1), result[7]);
        assertEquals(new Unmark(Mark.Marker.MARKER0, 1), result[8]);
        
        
        
        
        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
