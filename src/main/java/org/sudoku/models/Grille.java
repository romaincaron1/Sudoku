package org.sudoku.models;

import org.sudoku.exceptions.ElementInterditException;
import org.sudoku.exceptions.HorsBornesException;
import org.sudoku.exceptions.ValeurImpossibleException;
import org.sudoku.exceptions.ValeurInitialeModificationException;

public interface Grille {
    /**
     * Returns the size of the grid.
     *
     * @return the size of the grid
     */
    int getSize();

    /**
     * Returns the element of the grid at the specified coordinates.
     *
     * @param x
     * @param y 
     * @return the element of the grid at the specified coordinates
     * @throws HorsBornesException if the coordinates (x, y) are out of bounds of the grid
     */
    ElementDeGrille getValue(int x, int y) throws HorsBornesException;

    /**
     * Sets the value of the grid at the specified coordinates.
     *
     * @param x
     * @param y
     * @param value
     * @throws HorsBornesException                   if the coordinates (x, y) are out of bounds of the grid
     * @throws ValeurImpossibleException             if the specified value is not a valid value for the grid
     * @throws ElementInterditException              if the specified element is not a valid element for the grid
     * @throws ValeurInitialeModificationException   if the specified element is an initial value and cannot be modified
     */
    void setValue(int x, int y, ElementDeGrille value) throws HorsBornesException, ValeurImpossibleException, ElementInterditException, ValeurInitialeModificationException;
}

