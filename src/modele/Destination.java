package modele;

/**
 * La classe Destination représente les cases objectifs (destinations)
 * sur la carte du jeu. Une destination est une case traversable 
 * et est représentée par le symbole '.'.
 */
public class Destination extends Element {

    /**
     * Constructeur par défaut.
     * Initialise une destination avec son symbole spécifique '.'.
     */
    public Destination() {
        super('.'); // Appelle le constructeur de la classe parente Element avec le symbole '.'
    }

    /**
     * Vérifie si la destination peut être traversée.
     * Une destination peut toujours être traversée.
     * @return true car une destination est traversable.
     */
    @Override
    public boolean peutTraverser() {
        return true;
    }
}
