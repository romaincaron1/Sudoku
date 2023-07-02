package org.sudoku.models;

/**
 * Interface de résolveur de Grille
 * @author Sébastien Choplin <sebastien.choplin@u-picardie.fr>
 */
public interface Solveur {
    /**
     * Résout une grille de Sudoku
     * @param grille grille à résoudre
     * @return statut de la résolution (true si résolue, false sinon)
     * @throws Exception si la grille est invalide
     */
    boolean solve(Grille grille) throws Exception;
}

