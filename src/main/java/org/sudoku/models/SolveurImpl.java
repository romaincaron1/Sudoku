package org.sudoku.models;

import java.util.Set;
import org.sudoku.exceptions.HorsBornesException;

public class SolveurImpl implements Solveur {

  @Override
  /**.
   * Résout une grille de sudoku
   * @param grille grille à résoudre
   * @return true si la grille a été résolue, false sinon
   * @throws Exception
   */
  public boolean solve(final Grille grille) throws Exception {
    return solveSudoku(grille, 0, 0);
  }

  /**.
   * Résout une grille de sudoku
   * @param grille grille à résoudre
   * @param row ligne courante
   * @param col colonne courante
   * @throws Exception
   * @return true si la grille a été résolue, false sinon
   */
  private boolean solveSudoku(
    final Grille grille,
    final int row,
    final int col
  )
    throws Exception {
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
      if (
        !isValueInRow(grille, row, element) &&
        !isValueInColumn(grille, col, element) &&
        grille.isPossible(row, col, element)
      ) {
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

  /**.
   * Teste si une valeur est présente dans une ligne
   * @param grille grille à tester
   * @param row ligne à tester
   * @param value valeur à tester
   * @return
   * @throws HorsBornesException
   * @return true si la valeur est présente dans la ligne, false sinon
   */
  private boolean isValueInRow(
    final Grille grille,
    final int row,
    final ElementDeGrille value
  )
    throws HorsBornesException {
    for (int col = 0; col < grille.getDimension(); col++) {
      try {
        if (
          grille.getValue(row, col) != null &&
          grille.getValue(row, col).equals(value)
        ) {
          return true;
        }
      } catch (HorsBornesException e) {
        return false;
      }
    }
    return false;
  }

  /**.
   * Teste si une valeur est présente dans une colonne
   * @param grille grille à tester
   * @param col colonne à tester
   * @param value valeur à tester
   * @return
   * @throws HorsBornesException
   * @return true si la valeur est présente dans la colonne, false sinon
   */
  private boolean isValueInColumn(
    final Grille grille,
    final int col,
    final ElementDeGrille value
  )
    throws HorsBornesException {
    for (int row = 0; row < grille.getDimension(); row++) {
      try {
        if (
          grille.getValue(row, col) != null &&
          grille.getValue(row, col).equals(value)
        ) {
          return true;
        }
      } catch (HorsBornesException e) {
        return false;
      }
    }
    return false;
  }
}
