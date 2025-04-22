package modele;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe Carte représente une carte du jeu.
 * Elle gère la position des éléments (mur, vide, robot, caisse, etc.) 
 * ainsi que les déplacements dans le jeu.
 */
public class Carte {

    /**
     * Grille des éléments de la carte (chaque case contient un élément).
     */
    private Element[][] elements;

    /**
     * Référence au robot présent sur la carte.
     */
    private Robot robot;

    /**
     * Liste des positions correspondant aux destinations.
     */
    private List<Position> destinations;

    /**
     * Nombre de mouvements effectués depuis le début de la partie.
     */
    private int nbMouvements;

    /**
     * Constructeur : initialise une carte à partir d'une liste de chaînes 
     * représentant les lignes de la carte.
     * @param lignes Liste de chaînes, où chaque caractère correspond à un élément.
     */
    public Carte(List<String> lignes) {
        destinations = new ArrayList<>();
        nbMouvements = 0;

        int hauteur = lignes.size();
        int largeur = lignes.get(0).length();

        elements = new Element[hauteur][largeur];

        for (int i = 0; i < hauteur; i++) {
            String ligne = lignes.get(i);
            for (int j = 0; j < largeur; j++) {
                char c = ligne.charAt(j);
                switch (c) {
                    case '#' -> elements[i][j] = new Mur();
                    case ' ' -> elements[i][j] = new Vide();
                    case '.' -> {
                        elements[i][j] = new Destination();
                        destinations.add(new Position(j, i));
                    }
                    case '$' -> elements[i][j] = new Caisse();
                    case '@' -> {
                        robot = new Robot();
                        elements[i][j] = robot;
                    }
                    default -> elements[i][j] = new Sol();
                }
                elements[i][j].setX(j);
                elements[i][j].setY(i);
            }
        }
    }

    /**
     * Retourne l'élément présent à une position donnée.
     * @param x Coordonnée X.
     * @param y Coordonnée Y.
     * @return L'élément à la position donnée ou null si la position est hors de la grille.
     */
    public Element getElement(int x, int y) {
        if (x >= 0 && x < getLargeur() && y >= 0 && y < getHauteur()) {
            return elements[y][x];
        }
        return null;
    }

    /**
     * Retourne la hauteur de la carte (nombre de lignes).
     * @return Hauteur de la carte.
     */
    public int getHauteur() {
        return elements.length;
    }

    /**
     * Retourne la largeur de la carte (nombre de colonnes).
     * @return Largeur de la carte.
     */
    public int getLargeur() {
        return elements[0].length;
    }

    /**
     * Retourne l'objet Robot présent sur la carte.
     * @return Le robot.
     */
    public Robot getRobot() {
        return robot;
    }

    /**
     * Remplace un élément à une position donnée par du sol ou une destination,
     * en fonction de la liste des destinations.
     * @param x Coordonnée X de l'élément.
     * @param y Coordonnée Y de l'élément.
     */
    private void restaurerSolOuDestination(int x, int y) {
        Position pos = new Position(x, y);
        if (destinations.contains(pos)) {
            elements[y][x] = new Destination();
        } else {
            elements[y][x] = new Sol();
        }
    }

    /**
     * Déplace le robot dans une direction donnée, si le mouvement est valide.
     * @param direction La direction dans laquelle le robot doit être déplacé.
     * @return true si le déplacement a réussi, false sinon.
     */
    public boolean deplacerRobot(Direction direction) {
        int dx = 0, dy = 0;
        switch (direction) {
            case HAUT -> dy = -1;
            case BAS -> dy = 1;
            case GAUCHE -> dx = -1;
            case DROITE -> dx = 1;
        }

        int newX = robot.getX() + dx;
        int newY = robot.getY() + dy;

        Element nextElement = getElement(newX, newY);

        if (nextElement != null && nextElement.peutTraverser()) {
            restaurerSolOuDestination(robot.getX(), robot.getY());

            robot.setX(newX);
            robot.setY(newY);
            robot.setDirection(direction);
            elements[newY][newX] = robot;

            nbMouvements++;
            return true;
        } else if (nextElement instanceof Caisse caisse) {
            int nextCaisseX = newX + dx;
            int nextCaisseY = newY + dy;
            Element nextNextElement = getElement(nextCaisseX, nextCaisseY);

            if (nextNextElement != null && nextNextElement.peutTraverser()) {
                boolean caisseEstSurDestination = destinations.stream().anyMatch(
                        dest -> dest.x == nextCaisseX && dest.y == nextCaisseY
                );

                caisse.setEstSurDestination(caisseEstSurDestination);
                elements[nextCaisseY][nextCaisseX] = caisse;
                caisse.setX(nextCaisseX);
                caisse.setY(nextCaisseY);

                restaurerSolOuDestination(robot.getX(), robot.getY());

                robot.setX(newX);
                robot.setY(newY);
                robot.setDirection(direction);
                elements[newY][newX] = robot;

                nbMouvements++;
                return true;
            }
        }
        return false;
    }

    /**
     * Vérifie si la partie est terminée.
     * Une partie est terminée si toutes les destinations sont occupées par des caisses.
     * @return true si la partie est terminée, false sinon.
     */
    public boolean finDePartie() {
        for (Position dest : destinations) {
            Element element = getElement(dest.x, dest.y);
            if (!(element instanceof Caisse)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retourne le nombre de mouvements effectués depuis le début de la partie.
     * @return Nombre de mouvements.
     */
    public int getNbMouvements() {
        return nbMouvements;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de la carte.
     * Chaque caractère correspond à un symbole pour représenter un élément.
     * @return Représentation textuelle de la carte.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getHauteur(); i++) {
            for (int j = 0; j < getLargeur(); j++) {
                sb.append(elements[i][j].getSymbole());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Retourne la liste des positions correspondant aux destinations sur la carte.
     * @return Liste des destinations.
     */
    public List<Position> getDestinations() {
        return destinations;
    }
}
