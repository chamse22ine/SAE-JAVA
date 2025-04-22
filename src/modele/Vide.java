package modele;

/**
 * La classe Vide représente une case vide dans le jeu.
 * Un espace vide est représenté visuellement par un symbole espace (' ') 
 * et peut être traversé, tout comme le sol.
 */
public class Vide extends Element {

    /**
     * Constructeur de la classe Vide.
     * Initialise le vide avec le symbole espace (' ').
     */
    public Vide() {
        super(' ');
    }

    /**
     * Indique si le vide peut être traversé.
     * Par définition, le vide est traversable.
     *
     * @return true, car un espace vide peut être traversé.
     */
    @Override
    public boolean peutTraverser() {
        return true;
    }
}
