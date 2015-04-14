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
        
        
        testInstructions.add("Sense LeftAhead 0 85 FoeHome");

        
        String [] instructions = new String[testInstructions.size()];
        String [] parsing = testInstructions.toArray(instructions);
        
        

        Instruction[] result = new Instruction[bp.parseBrain(parsing).length];
        System.arraycopy(bp.parseBrain(instructions), 0, result, 0, bp.parseBrain(instructions).length);
        
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
            
        }
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
