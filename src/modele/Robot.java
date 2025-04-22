package modele;

/**
 * La classe Robot représente un robot dans le jeu.
 * Le robot est un élément du jeu qui a une direction associée
 * et ne peut pas être traversé.
 */
public class Robot extends Element {

    /**
     * Direction actuelle du robot.
     */
    private Direction direction;

    /**
     * Constructeur de la classe Robot.
     * Le robot est initialisé avec le symbole '@' pour le représenter
     * et une direction par défaut définie comme DROITE.
     */
    public Robot() {
        super('@');
        this.direction = Direction.DROITE; 
    }

    /**
     * Indique si le robot peut être traversé.
     * Par définition, un robot est infranchissable.
     *
     * @return false, car un robot ne peut pas être traversé.
     */
    @Override
    public boolean peutTraverser() {
        return false;
    }

    /**
     * Récupère la direction actuelle du robot.
     *
     * @return La direction actuelle du robot.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Modifie la direction du robot.
     *
     * @param direction La nouvelle direction que le robot doit adopter.
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
