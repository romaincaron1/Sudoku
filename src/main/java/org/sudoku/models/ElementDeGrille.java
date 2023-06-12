package org.sudoku.models;

import org.sudoku.exceptions.ValeurInitialeModificationException;

public interface ElementDeGrille {
    // Get value
    char getValue();

    // Set value
    void setValue(char value) throws ValeurInitialeModificationException;

    // is initial value
    boolean isInitialValue();

    // set initial value
    void setInitialValue(boolean initialValue);
}
