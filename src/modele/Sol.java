package modele;

/**
 * La classe Sol représente une case traversable dans le jeu.
 * Elle est caractérisée par un symbole vide (' ') et peut être traversée
 * par d'autres éléments (comme un robot ou un objet).
 */
public class Sol extends Element {

    /**
     * Constructeur de la classe Sol.
     * Initialise le sol avec le symbole espace (' ') pour le représenter.
     */
    public Sol() {
        super(' ');
    }

    /**
     * Indique si le sol peut être traversé.
     * Par définition, le sol est traversable.
     *
     * @return true, car un sol peut être traversé.
     */
    @Override
    public boolean peutTraverser() {
        return true;
    }
}
