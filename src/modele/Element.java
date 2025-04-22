package modele;

/**
 * La classe abstraite Element représente un élément du jeu.
 * Les éléments peuvent être de différents types (caisse, destination, mur, etc.)
 * et sont caractérisés par un symbole, ainsi que par leurs coordonnées (x, y) 
 * sur une carte.
 */
public abstract class Element {

    /**
     * Symbole utilisé pour représenter visuellement l'élément sur la carte.
     */
    private final char symbole;

    /**
     * Coordonnées X de l'élément sur la carte.
     */
    private int x;

    /**
     * Coordonnées Y de l'élément sur la carte.
     */
    private int y;

    /**
     * Constructeur de la classe Element.
     * Initialise l'élément avec un symbole spécifique.
     * 
     * @param symbole Le caractère représentant cet élément.
     */
    public Element(char symbole) {
        this.symbole = symbole;
    }

    /**
     * Retourne le symbole de l'élément.
     * 
     * @return Le symbole de l'élément sous forme de caractère.
     */
    public char getSymbole() {
        return symbole;
    }

    /**
     * Méthode abstraite qui détermine si l'élément peut être traversé.
     * Les sous-classes doivent définir leur propre implémentation.
     * 
     * @return true si l'élément est traversable, false sinon.
     */
    public abstract boolean peutTraverser();

    /**
     * Retourne la coordonnée X de l'élément.
     * 
     * @return La coordonnée X.
     */
    public int getX() {
        return x;
    }

    /**
     * Définit la coordonnée X de l'élément.
     * 
     * @param x La nouvelle coordonnée X.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Retourne la coordonnée Y de l'élément.
     * 
     * @return La coordonnée Y.
     */
    public int getY() {
        return y;
    }

    /**
     * Définit la coordonnée Y de l'élément.
     * 
     * @param y La nouvelle coordonnée Y.
     */
    public void setY(int y) {
        this.y = y;
    }
}
