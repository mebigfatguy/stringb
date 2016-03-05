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
    
    @Test
    public void testSubString() {
        StringB sb = new StringB();
        
        sb.append("foo").append("bar").append("baz").append(1);
        
        String v = "foobarbaz1";
        
        for (int i = 0; i < v.length() - 1; i++) {
            for (int j = i; j < v.length(); j++) {
                Assert.assertEquals(v.substring(i, j), sb.substring(i, j));
            }
        }
    }
    
    @Test
    public void testInsert() {
        StringB sb = new StringB();
        
        sb.append("foo").append("bar").append("baz").append(1);
        
        sb.insert(10, 2);
        sb.insert(9, "bap");
        sb.insert(2, "t");
        
        Assert.assertEquals("fotobarbazbap12", sb.toString());
    }
}
