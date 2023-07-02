package org.sudoku.models;

import java.util.HashSet;
import java.util.Set;
import org.sudoku.exceptions.ElementInterditException;
import org.sudoku.exceptions.HorsBornesException;
import org.sudoku.exceptions.ValeurImpossibleException;
import org.sudoku.exceptions.ValeurInitialeModificationException;

public class GrilleImpl implements Grille {
  private ElementDeGrille[][] grille;
  private Set<ElementDeGrille> elementsDeGrille;
  private int taille;

  public GrilleImpl(
    final ElementDeGrille[] elementDeGrilles,
    final ElementDeGrille[][] grilleTab
  ) {
    this.elementsDeGrille = new HashSet<>();
    this.grille = grilleTab;
    this.taille = this.grille.length;
    for (ElementDeGrille elementDeGrille : elementDeGrilles) {
      this.elementsDeGrille.add(elementDeGrille);
    }
  }

  @Override
  /**
   * Renvoie l'ensemble des éléments de grille
   *
   * @return ensemble des éléments de grille
   */
  public Set<ElementDeGrille> getElements() {
    return elementsDeGrille;
  }

  @Override
  /**
   * Renvoie la taille de la grille
   *
   * @return taille de la grille
   */
  public int getDimension() {
    return taille;
  }

  @Override
  /**
   * Modifie la valeur d'un élément de grille
   *
   * @param x     position x de l'élément de grille
   * @param y     position y de l'élément de grille
   * @param value valeur de l'élément de grille
   * @throws HorsBornesException                   si la position est hors bornes
   * @throws ValeurImpossibleException             si la valeur est impossible à placer à cette position
   * @throws ElementInterditException              si la valeur est interdite
   * @throws ValeurInitialeModificationException   si la valeur est une valeur initiale
   */
  public void setValue(final int x, final int y, final ElementDeGrille value)
    throws HorsBornesException, ValeurImpossibleException, ElementInterditException, ValeurInitialeModificationException {
    if (x < 0 || x >= taille || y < 0 || y >= taille) {
      throw new HorsBornesException("Position hors bornes");
    }

    if (isValeurInitiale(x, y)) {
      throw new ValeurInitialeModificationException(
        "Impossible de modifier une valeur initiale"
      );
    }

    if (value != null && !isPossible(x, y, value)) {
      throw new ValeurImpossibleException(
        "Impossible de placer cette valeur à cette position"
      );
    }

    grille[x][y] = value;
    if (value != null) {
      value.setInitialValue(false);
    }
  }

  @Override
  /**
   * Renvoie la valeur d'un élément de grille
   *
   * @param x position x de l'élément de grille
   * @param y position y de l'élément de grille
   *
   * @return valeur de l'élément de grille
   */
  public ElementDeGrille getValue(final int x, final int y)
    throws HorsBornesException {
    if (x < 0 || x >= taille || y < 0 || y >= taille) {
      throw new HorsBornesException("Position hors bornes");
    }

    return grille[x][y];
  }

  @Override
  /**
   * Renvoie si la grille est complète ou non
   *
   * @return true si la grille est complète, false sinon
   */
  public boolean isComplete() {
    for (int x = 0; x < taille; x++) {
      for (int y = 0; y < taille; y++) {
        if (grille[x][y] == null) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  /**
   * Renvoie si la valeur est possible à cette position ou non
   *
   * @param x     position x de l'élément de grille
   * @param y     position y de l'élément de grille
   * @param value valeur de l'élément de grille
   *
   * @return true si la valeur est possible à cette position, false sinon
   */
  public boolean isPossible(
    final int x,
    final int y,
    final ElementDeGrille value
  )
    throws HorsBornesException, ElementInterditException {
    // Vérifier si la position est hors bornes
    if (x < 0 || x >= taille || y < 0 || y >= taille) {
      throw new HorsBornesException("Position hors bornes");
    }

    // Vérifier si la valeur est autorisée
    if (!elementsDeGrille.contains(value)) {
      throw new ElementInterditException("Valeur non autorisée");
    }

    // Vérification de la ligne et de la colonne
    for (int i = 0; i < taille; i++) {
      if (
        grille[x][i] != null &&
        grille[x][i].equals(value) ||
        grille[i][y] != null &&
        grille[i][y].equals(value)
      ) {
        return false;
      }
    }

    // Vérification du bloc
    int tailleBloc = (int) Math.sqrt(taille);
    int debutBlocX = (x / tailleBloc) * tailleBloc;
    int debutBlocY = (y / tailleBloc) * tailleBloc;

    for (int ligne = debutBlocX; ligne < debutBlocX + tailleBloc; ligne++) {
      for (
        int colonne = debutBlocY;
        colonne < debutBlocY + tailleBloc;
        colonne++
      ) {
        if (
          grille[ligne][colonne] != null && grille[ligne][colonne].equals(value)
        ) {
          return false;
        }
      }
    }

    return true;
  }

  @Override
  /**
   * Renvoie si la valeur est une valeur initiale ou non
   *
   * @param x position x de l'élément de grille
   * @param y position y de l'élément de grille
   *
   * @return true si la valeur est une valeur initiale, false sinon
   */
  public boolean isValeurInitiale(final int x, final int y) {
    return grille[x][y] != null && grille[x][y].isInitialValue();
  }

  /**
   * Renvoie la grille sous forme de chaîne de caractères
   *
   * @return la grille sous forme de chaîne de caractères avec un retour à la ligne à la fin de chaque ligne
   */
  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    for (int ligne = 0; ligne < taille; ligne++) {
      for (int col = 0; col < taille; col++) {
        ElementDeGrille element = grille[ligne][col];
        if (element != null) {
          stringBuilder.append(element.getValue());
        } else {
          stringBuilder.append("-");
        }
      }
      stringBuilder.append("\n");
    }
    return stringBuilder.toString();
  }
}
