package modele;

/**
 * La classe Caisse représente une caisse dans le jeu.
 * Une caisse peut être sur une destination et a un comportement spécifique
 * dans ces conditions.
 * Cette classe hérite de la classe Element.
 */
public class Caisse extends Element {

    /**
     * Indique si la caisse est positionnée sur une destination (objectif).
     */
    private boolean estSurDestination;

    /**
     * Constructeur par défaut.
     * Initialise une caisse avec son symbole par défaut ('$')
     * et indique qu'elle n'est pas sur une destination.
     */
    public Caisse() {
        super('$'); // Appelle le constructeur de la classe parente Element avec '$'.
        this.estSurDestination = false;
    }

    /**
     * Retourne l'état de la caisse concernant sa position sur une destination.
     * @return true si la caisse est sur une destination, false sinon.
     */
    public boolean isEstSurDestination() {
        return estSurDestination;
    }

    /**
     * Modifie l'état de la caisse : elle est ou non sur une destination.
     * @param estSurDestination true si la caisse est sur une destination, false sinon.
     */
    public void setEstSurDestination(boolean estSurDestination) {
        this.estSurDestination = estSurDestination;
    }

    /**
     * Retourne le symbole représentant la caisse.
     * Si la caisse est sur une destination, elle retourne '*'.
     * Sinon, elle retourne son symbole par défaut ('$').
     * @return le caractère représentant la caisse.
     */
    @Override
    public char getSymbole() {
        return estSurDestination ? '*' : super.getSymbole();
    }

    /**
     * Détermine si la caisse peut être traversée par un autre élément.
     * Une caisse est un obstacle, donc elle ne peut pas être traversée.
     * @return false car la caisse ne peut être traversée.
     */
    @Override
    public boolean peutTraverser() {
        return false;
    }
}
