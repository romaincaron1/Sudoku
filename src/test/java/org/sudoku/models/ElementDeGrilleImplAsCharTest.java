package org.sudoku.models;

import org.junit.Assert;
import org.junit.Test;
import org.sudoku.exceptions.ValeurInitialeModificationException;

public class ElementDeGrilleImplAsCharTest {

    @Test
    public void testGetValue() {
        ElementDeGrilleImplAsChar element = new ElementDeGrilleImplAsChar('5');
        char value = element.getValue();
        Assert.assertEquals('5', value);
    }

    @Test(expected = ValeurInitialeModificationException.class)
    public void testSetValue() throws ValeurInitialeModificationException {
        ElementDeGrilleImplAsChar element = new ElementDeGrilleImplAsChar('5');
        element.setValue('6');
    }
}
