package org.sudoku.models;

import org.junit.Assert;
import org.junit.Test;
import org.sudoku.exceptions.ElementInterditException;
import org.sudoku.exceptions.HorsBornesException;
import org.sudoku.exceptions.ValeurImpossibleException;
import org.sudoku.exceptions.ValeurInitialeModificationException;

public class GrilleTest {

    @Test
    public void testGetSize() {
        GrilleImpl grille = new GrilleImpl(9);
        int size = grille.getSize();
        Assert.assertEquals(9, size);
    }

    @Test
    public void testGetValue() throws HorsBornesException, ValeurImpossibleException, ElementInterditException, ValeurInitialeModificationException {
        GrilleImpl grille = new GrilleImpl(9); 
        ElementDeGrilleImplAsChar element = new ElementDeGrilleImplAsChar('5');
        try {
            grille.setValue(1, 2, element);
        } catch (Exception e) {
            throw e;
        }
        ElementDeGrille result = grille.getValue(1, 2);
        Assert.assertEquals('5', result.getValue());
    }    

    @Test
    public void testSetValue() throws HorsBornesException, ValeurImpossibleException, ElementInterditException, ValeurInitialeModificationException {
        GrilleImpl grille = new GrilleImpl(9);
        ElementDeGrilleImplAsChar element = new ElementDeGrilleImplAsChar('5');
        try {
            grille.setValue(0, 0, element);
        } catch (Exception e) {
            throw e;
        }
        ElementDeGrille result = grille.getValue(0, 0);
        Assert.assertEquals('5', result.getValue());
    }

    @Test(expected = HorsBornesException.class)
    public void testSetValueOutOfBounds() throws HorsBornesException, ValeurImpossibleException, ElementInterditException, ValeurInitialeModificationException {
        GrilleImpl grille = new GrilleImpl(9);
        ElementDeGrilleImplAsChar element = new ElementDeGrilleImplAsChar('5');
        grille.setValue(9, 9, element);
    }

    @Test(expected = ValeurImpossibleException.class)
    public void testSetValueInvalidValue() throws HorsBornesException, ValeurImpossibleException, ElementInterditException, ValeurInitialeModificationException {
        GrilleImpl grille = new GrilleImpl(9);
        ElementDeGrilleImplAsChar element = new ElementDeGrilleImplAsChar('A');
        grille.setValue(0, 0, element);
    } 

    @Test(expected = ValeurInitialeModificationException.class)
    public void testSetValueInitialValueModification() throws HorsBornesException, ValeurImpossibleException, ElementInterditException, ValeurInitialeModificationException {
        ElementDeGrille[][] grid = new ElementDeGrille[9][9];
        ElementDeGrilleImplAsChar element = new ElementDeGrilleImplAsChar('5');
        grid[1][1] = element;
        // Create GrilleImpl with ElementDeGrille array
        GrilleImpl grille = new GrilleImpl(9, grid);
        // Try to modify initial value
        grille.setValue(1, 1, element);
    }
    
}
