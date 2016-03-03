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
}
