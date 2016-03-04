package com.mebigfatguy.stringb;

import org.junit.Assert;
import org.junit.Test;

public class StringBTest {

    @Test
    public void testAppend() {
        
        StringB sb = new StringB();
        
        sb.append("foo").append("bar").append("baz").append(1);
        
        Assert.assertEquals("foobarbaz1", sb.toString());
    }
    
    @Test
    public void testCharAt() {
        
        StringB sb = new StringB();
        
        sb.append("foo").append("bar").append("baz").append(1);
        
        String v = "foobarbaz1";
        for (int i = 0; i < v.length(); i++) {
            Assert.assertEquals(v.charAt(i), sb.charAt(i));
        }
        
        try {
            sb.charAt(-1);
            Assert.assertFalse(true);
        } catch (StringIndexOutOfBoundsException e) {
            Assert.assertTrue(true);
        }
        
        try {
            sb.charAt(20);
            Assert.assertFalse(true);
        } catch (StringIndexOutOfBoundsException e) {
            Assert.assertTrue(true);
        }
    }
}
