package org.sudoku.models;

import org.sudoku.exceptions.ValeurInitialeModificationException;

public class ElementDeGrilleImplAsChar implements ElementDeGrille {
    private char value;
    private boolean initialValue;

    public ElementDeGrilleImplAsChar(char value) {
        this.value = value;
    }

    @Override
    public char getValue() {
        return value;
    }

    @Override
    public void setValue(char value) throws ValeurInitialeModificationException {
        throw new ValeurInitialeModificationException("Cannot modify initial value");
    }

    @Override
    public boolean isInitialValue() {
        return initialValue;
    }

    @Override
    public void setInitialValue(boolean initialValue) {
        this.initialValue = initialValue;
    }
}
