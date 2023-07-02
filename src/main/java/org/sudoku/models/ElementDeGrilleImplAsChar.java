package org.sudoku.models;

public class ElementDeGrilleImplAsChar implements ElementDeGrille {
    private char value;
    private boolean isInitialValue;

    /**
     * Constructeur d'un élément de grille.
     *
     * @param value la valeur de l'élément de grille
     */
    public ElementDeGrilleImplAsChar(char value) {
        this.value = value;
        this.isInitialValue = true;
    }

    /**
     * Renvoie la valeur de l'élément de grille.
     *
     * @return valeur de l'élément de grille
     */
    public char getValue() {
        return value;
    }

    /**
     * Compare l'élément de grille avec un autre objet.
     *
     * @param obj objet à comparer avec l'élément de grille
     * @return true si l'objet est égal à l'élément de grille, false sinon
     */
    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        ElementDeGrilleImplAsChar autre = (ElementDeGrilleImplAsChar) obj;
        return value == autre.value;
    }

    /**
     * Renvoie si l'élément de grille est une valeur initiale ou non.
     *
     * @return true si l'élément de grille est une valeur initiale, false sinon
     */
    public boolean isInitialValue() {
        return isInitialValue;
    }

    /**
     * Si l'élément de grille est une valeur initiale ou non.
     *
     * @param initialValue true si l'élément de grille est une valeur initiale, false sinon
     */
    public void setInitialValue(boolean initialValue) {
        this.isInitialValue = initialValue;
    }
}
