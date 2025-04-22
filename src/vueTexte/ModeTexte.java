package vueTexte;

import java.util.HashMap;
import java.util.Map;
import modele.Carte;
import modele.Direction;
import modele.GestionnaireDeLevels;
import modele.Lecture;

/**
 * La classe ModeTexte permet de jouer au jeu Sokoban en mode console.
 * Elle gère le chargement des niveaux, la saisie des directions,
 * et les interactions avec le joueur via la console.
 */
public class ModeTexte {

    /** Carte actuelle représentant l'état du niveau. */
    private Carte carte;

    /** Map associant les caractères saisis par l'utilisateur à des directions. */
    private Map<Character, Direction> directions;

    /** Gestionnaire des niveaux pour naviguer et gérer les différents niveaux. */
    private final GestionnaireDeLevels gestionnaireDeLevels;

    /**
     * Constructeur de la classe ModeTexte.
     * Initialise le gestionnaire de niveaux, configure les directions,
     * et charge le niveau courant.
     */
    public ModeTexte() {
        this.gestionnaireDeLevels = new GestionnaireDeLevels();
        initialiserDirections();
        chargerNiveauCourant();
    }

    /**
     * Initialise la correspondance des caractères saisis par l'utilisateur 
     * (z, q, s, d) aux directions du jeu.
     */
    private void initialiserDirections() {
        directions = new HashMap<>();
        directions.put('z', Direction.HAUT);
        directions.put('s', Direction.BAS);
        directions.put('q', Direction.GAUCHE);
        directions.put('d', Direction.DROITE);
    }

    /**
     * Charge le niveau courant à partir du fichier correspondant, 
     * en utilisant le gestionnaire de niveaux.
     */
    private void chargerNiveauCourant() {
        String cheminCarte = gestionnaireDeLevels.getNiveauCourant();
        if (cheminCarte != null) {
            Lecture lecture = new Lecture(cheminCarte);
            if (lecture.getNbLignes() > 0) {
                carte = new Carte(lecture.getLignes());
            } else {
                System.out.println("Erreur: Impossible de charger le niveau " + cheminCarte);
            }
        }
    }

    /**
     * Lit une direction saisie par l'utilisateur via la console.
     * L'utilisateur doit entrer l'un des caractères valides (z, q, s, d), 
     * sinon il est invité à réessayer.
     *
     * @return La direction correspondant au caractère saisi.
     */
    public Direction lireDirection() {
        char input;
        do {
            System.out.println("Entrez une direction (z:haut, s:bas, q:gauche, d:droite):");
            input = Outil.lireCaractere();
        } while (!directions.containsKey(input));
        return directions.get(input);
    }

    /**
     * Lance la boucle principale du jeu en mode console.
     * L'utilisateur joue niveau par niveau et peut choisir de passer 
     * au niveau suivant ou d'arrêter à tout moment.
     */
    public void lancerPartie() {
        boolean continuer = true;

        while (continuer) {
            jouerUnNiveau();

            if (gestionnaireDeLevels.estDernierNiveau()) {
                System.out.println("Félicitations! Vous avez terminé tous les niveaux!");
                continuer = false;
            } else {
                System.out.println("Niveau terminé! Voulez-vous passer au niveau suivant? (o/n)");
                char reponse = Outil.lireCaractere();
                if (reponse == 'o') {
                    gestionnaireDeLevels.passerAuNiveauSuivant();
                    chargerNiveauCourant();
                } else {
                    continuer = false;
                }
            }
        }
    }

    /**
     * Joue un niveau en boucle jusqu'à ce qu'il soit terminé.
     * Affiche l'état du niveau à chaque étape et permet à l'utilisateur
     * de saisir des directions pour déplacer le robot.
     */
    private void jouerUnNiveau() {
        System.out.println("Nouveau niveau chargé: " + gestionnaireDeLevels.getNiveauCourant());

        while (!carte.finDePartie()) {
            System.out.println(carte);
            System.out.println("Nombre de mouvements: " + carte.getNbMouvements());
            Direction direction = lireDirection();
            carte.deplacerRobot(direction);
        }

        System.out.println(carte);
        System.out.println("Bravo! Niveau terminé en " + carte.getNbMouvements() + " mouvements!");
    }
}
