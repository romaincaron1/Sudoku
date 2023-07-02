package org.sudoku.models;

import java.util.Set;

import org.sudoku.exceptions.HorsBornesException;

public class SolveurImpl implements Solveur {

    @Override
    public boolean solve(Grille grille) throws Exception {
        return solveSudoku(grille, 0, 0);
    }

    private boolean solveSudoku(Grille grille, int row, int col) throws Exception {
        if (col == grille.getDimension()) {
            return solveSudoku(grille, row + 1, 0);
        }

        if (row == grille.getDimension()) {
            return true;
        }

        ElementDeGrille currentValue = grille.getValue(row, col);
        if (currentValue != null) {
            return solveSudoku(grille, row, col + 1);
        }

        Set<ElementDeGrille> elements = grille.getElements();
        for (ElementDeGrille element : elements) {
            if (!isValueInRow(grille, row, element) && !isValueInColumn(grille, col, element) && grille.isPossible(row, col, element)) {
                grille.setValue(row, col, element);
                boolean isSolved = solveSudoku(grille, row, col + 1);
                if (isSolved) {
                    return true;
                } else {
                    grille.setValue(row, col, null);
                }
            }
        }

        return false;
    }

    private boolean isValueInRow(Grille grille, int row, ElementDeGrille value) throws HorsBornesException {
        for (int col = 0; col < grille.getDimension(); col++) {
            try {
                if (grille.getValue(row, col) != null && grille.getValue(row, col).equals(value)) {
                    return true;
                }
            } catch (HorsBornesException e) {
                return false;
            }
        }
        return false;
    }

    private boolean isValueInColumn(Grille grille, int col, ElementDeGrille value) throws HorsBornesException {
        for (int row = 0; row < grille.getDimension(); row++) {
            try {
                if (grille.getValue(row, col) != null && grille.getValue(row, col).equals(value)) {
                    return true;
                }
            } catch (HorsBornesException e) {
                return false;
            }
        }
        return false;
    }

}
