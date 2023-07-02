package org.sudoku.main;

import java.io.File;
import java.io.IOException;
import org.sudoku.exceptions.ElementInterditException;
import org.sudoku.exceptions.HorsBornesException;
import org.sudoku.exceptions.ValeurImpossibleException;
import org.sudoku.exceptions.ValeurInitialeModificationException;
import org.sudoku.models.Grille;
import org.sudoku.models.GrilleParser;
import org.sudoku.models.SolveurImpl;

/**
 * Hello world!
 *
 */
public class App {

  public static void main(String[] args) {
    File fichier = new File("grilles/sudoku16-e.txt");
    try {
      Grille grille = GrilleParser.parse(fichier);
      SolveurImpl solveurImpl = new SolveurImpl();
      System.out.println("Grille à résoudre");
      System.out.println(grille);
      if (solveurImpl.solve(grille)) {
        System.out.println("Grille résolue");
        System.out.println(grille);
      } else {
        System.out.println("Impossible de résoudre la grille.");
      }
    } catch (
      IOException | ElementInterditException | ValeurInitialeModificationException | HorsBornesException | ValeurImpossibleException e
    ) {
      e.printStackTrace();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
