package org.sudoku.models;

/**
 * @author Sébastien Choplin <sebastien.choplin@u-picardie.fr>
 */
public interface ElementDeGrille {
    boolean isInitialValue();

    void setInitialValue(boolean isInitialValue);

    char getValue();
}
