package org.sudoku.models;

import org.junit.Assert;
import org.junit.Test;

public class ElementDeGrilleImplAsCharTest {

    @Test
    public void testGetValue() {
        ElementDeGrilleImplAsChar element = new ElementDeGrilleImplAsChar('5');
        Assert.assertEquals('5', element.getValue());
    }

    @Test
    public void testEquals() {
        ElementDeGrilleImplAsChar element1 = new ElementDeGrilleImplAsChar('5');
        ElementDeGrilleImplAsChar element2 = new ElementDeGrilleImplAsChar('5');
        ElementDeGrilleImplAsChar element3 = new ElementDeGrilleImplAsChar('6');

        Assert.assertEquals(element1, element2);
        Assert.assertNotEquals(element1, element3);
    }

    @Test
    public void testIsInitialValue() {
        ElementDeGrilleImplAsChar element = new ElementDeGrilleImplAsChar('5');
        Assert.assertTrue(element.isInitialValue());
        
        element.setInitialValue(false);
        Assert.assertFalse(element.isInitialValue());
    }
}

