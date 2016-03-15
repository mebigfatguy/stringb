package com.mebigfatguy.stringb;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class TimeTest {

    private static long SEED = 1223592037235412351L;
    
    private Random r;
    
    @Before
    public void before() {
        r = new Random(SEED);
    }
    
    @Test 
    public void testStringBuilder() {
        for (int i = 0; i < 1000; i++) {
            runStringBuilder();
        }
        
        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            runStringBuilder();
        }
        long end = System.nanoTime();
        System.out.println("StringBuilder: " + (end - start));
    }
    
    @Test 
    public void testStringB() {
        for (int i = 0; i < 1000; i++) {
            runStringB();
        }
        
        
        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            runStringB();
        }
        long end = System.nanoTime();
        System.out.println("StringB:       " + (end - start));
    }
    
    
    public String runStringBuilder() {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < 1000; i++) {
            sb.append("Val: ").append(r.nextInt()).append(",");
        }
        
        return sb.toString();
    }
    
    public String runStringB() {
        StringB sb = new StringB();
        
        for (int i = 0; i < 1000; i++) {
            sb.append("Val: ").append(r.nextInt()).append(",");
        }
        
        return sb.toString();
    }
}
