package modele;

/**
 * La classe Mur représente un obstacle infranchissable dans le jeu.
 * Les murs sont caractérisés par un symbole spécifique et ne peuvent pas être traversés.
 */
public class Mur extends Element {

    /**
     * Constructeur par défaut de la classe Mur.
     * Initialise le mur avec le symbole '#' pour le représenter.
     */
    public Mur() {
        super('#');
    }

    /**
     * Indique si un mur peut être traversé. 
     * Par définition, un mur est infranchissable.
     *
     * @return false, car un mur ne peut pas être traversé.
     */
    @Override
    public boolean peutTraverser() {
        return false;
    }
}
