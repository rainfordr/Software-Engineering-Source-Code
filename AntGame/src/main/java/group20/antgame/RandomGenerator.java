/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.antgame;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;


/**
 *
 * @author owner
 */
public class RandomGenerator {
    int timesCalled = 0;
    private BigDecimal S;
    private BigDecimal X;
    private BigDecimal sMult;
    private BigDecimal one;
    private BigDecimal sDiv;
    private BigDecimal xMod;
    private MathContext mc;
    
    public RandomGenerator(int seed){
        S = new BigDecimal(seed);
        sMult = new BigDecimal(22695477.0);
        one = new BigDecimal(1);
        sDiv = new BigDecimal(65536.0);
        xMod = new BigDecimal(16384.0);
        //mc = MathContext.UNLIMITED;
        mc = new MathContext(775);
        
        for(int i = 0; i < 3; i++){
            S = S.multiply(sMult, mc);
            S = S.add(one, mc);
        }
    }
    
    public int randomInt(int n){
        timesCalled++;
        BigDecimal N = new BigDecimal(n);
        int result;
        S = S.multiply(sMult);
        S = S.add(one, mc);
        X = S.divide(sDiv, mc);
        X = X.remainder(N);
        result = X.toBigInteger().intValue();
        return result;
    }

}
