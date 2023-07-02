package org.sudoku.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sudoku.exceptions.ElementInterditException;
import org.sudoku.exceptions.HorsBornesException;
import org.sudoku.exceptions.ValeurImpossibleException;
import org.sudoku.exceptions.ValeurInitialeModificationException;

public class GrilleImplTest {
    private GrilleImpl grille;

    @Before
    public void setup() {
        ElementDeGrilleImplAsChar[] elementsDeGrille = {
                new ElementDeGrilleImplAsChar('1'),
                new ElementDeGrilleImplAsChar('2'),
                new ElementDeGrilleImplAsChar('3'),
                new ElementDeGrilleImplAsChar('4'),
                new ElementDeGrilleImplAsChar('5'),
                new ElementDeGrilleImplAsChar('6'),
                new ElementDeGrilleImplAsChar('7'),
                new ElementDeGrilleImplAsChar('8'),
                new ElementDeGrilleImplAsChar('9')
        };

        ElementDeGrilleImplAsChar[][] grilleTab = new ElementDeGrilleImplAsChar[9][9];
        grille = new GrilleImpl(elementsDeGrille, grilleTab);
    }

    @Test
    public void testGetElements() {
        Assert.assertEquals(9, grille.getElements().size());
    }

    @Test
    public void testGetDimension() {
        Assert.assertEquals(9, grille.getDimension());
    }

    @Test(expected = HorsBornesException.class)
    public void testSetValueHorsBornesException() throws HorsBornesException, ValeurImpossibleException, ElementInterditException, ValeurInitialeModificationException {
        ElementDeGrilleImplAsChar element = new ElementDeGrilleImplAsChar('5');
        grille.setValue(10, 0, element);
    }

    @Test(expected = HorsBornesException.class)
    public void testGetValueHorsBornesException() throws HorsBornesException {
        grille.getValue(10, 0);
    }

    @Test(expected = HorsBornesException.class)
    public void testIsPossibleHorsBornesException() throws HorsBornesException, ElementInterditException {
        grille.isPossible(10, 0, new ElementDeGrilleImplAsChar('5'));
    }

    @Test(expected = ElementInterditException.class)
    public void testIsPossibleElementInterditException() throws HorsBornesException, ElementInterditException {
        grille.isPossible(0, 0, new ElementDeGrilleImplAsChar('0'));
    }
}
