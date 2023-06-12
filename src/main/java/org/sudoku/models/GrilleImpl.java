package org.sudoku.models;

import org.sudoku.exceptions.ElementInterditException;
import org.sudoku.exceptions.HorsBornesException;
import org.sudoku.exceptions.ValeurImpossibleException;
import org.sudoku.exceptions.ValeurInitialeModificationException;

public class GrilleImpl implements Grille {
    private int size;
    private ElementDeGrille[][] grid;

    public GrilleImpl(int size) {
        this.size = size;
        this.grid = new ElementDeGrille[size][size];
    }

    public GrilleImpl(int size, ElementDeGrille[][] grid) {
        this.size = size;
        this.grid = grid;

        for (ElementDeGrille[] elementDeGrilles : grid) {
            for (ElementDeGrille elementDeGrille : elementDeGrilles) {
                elementDeGrille.setInitialValue(true);
            }
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public ElementDeGrille getValue(int x, int y) throws HorsBornesException {
        if (isOutOfBounds(x, y)) {
            throw new HorsBornesException("Position out of bounds");
        }
        return grid[x][y];
    }

    @Override
    public void setValue(int x, int y, ElementDeGrille element) throws HorsBornesException, ValeurImpossibleException, ElementInterditException, ValeurInitialeModificationException {
        if (this.getValue(x, y).isInitialValue()) {
            throw new ValeurInitialeModificationException("Cannot modify initial value");
        }
        
        if (isOutOfBounds(x, y)) {
            throw new HorsBornesException("Position out of bounds");
        }

        if (!(element instanceof ElementDeGrilleImplAsChar)) {
            throw new ElementInterditException("Invalid element");
        }

        char charValue = element.getValue();

        if (!isValidValue(charValue)) {
            throw new ValeurImpossibleException("Invalid value");
        }

        grid[x][y] = element;
    }

    private boolean isOutOfBounds(int x, int y) {
        return x < 0 || x >= size || y < 0 || y >= size;
    }

    private boolean isValidValue(char value) {
        // Check if the value is a valid digit between '1' and '9'
        return value >= '1' && value <= '9';
    }
}

