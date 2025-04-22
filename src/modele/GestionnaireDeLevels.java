package modele;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe GestionnaireDeLevels permet de gérer les différents niveaux dans le jeu.
 * Elle organise les chemins vers les fichiers des cartes des niveaux et contrôle
 * la navigation entre ceux-ci.
 */
public class GestionnaireDeLevels {

    /**
     * Liste des chemins des fichiers de cartes, représentant les niveaux du jeu.
     */
    private final List<String> cheminsDesCartes;

    /**
     * Numéro du niveau actuellement en cours. L'index commence à 0.
     */
    private int niveauCourant;

    /**
     * Constructeur par défaut du GestionnaireDeLevels.
     * Initialise la liste des niveaux avec les chemins des fichiers correspondants
     * et positionne le niveau courant au tout premier niveau.
     */
    public GestionnaireDeLevels() {
        cheminsDesCartes = new ArrayList<>();
        cheminsDesCartes.add("./src/map/map1.txt");
        cheminsDesCartes.add("./src/map/map2.txt");
        cheminsDesCartes.add("./src/map/map3.txt");
        niveauCourant = 0;
    }

    /**
     * Retourne le chemin du niveau actuellement en cours.
     * 
     * @return Le chemin du fichier représentant le niveau courant, 
     *         ou null si aucun niveau n'est disponible.
     */
    public String getNiveauCourant() {
        if (niveauCourant < cheminsDesCartes.size()) {
            return cheminsDesCartes.get(niveauCourant);
        }
        return null; 
    }

    /**
     * Passe au niveau suivant dans la liste des niveaux.
     * 
     * @return true si un niveau suivant est disponible,
     *         false si aucun niveau suivant ne peut être chargé.
     */
    public boolean passerAuNiveauSuivant() {
        niveauCourant++;
        return niveauCourant < cheminsDesCartes.size();
    }

    /**
     * Vérifie si le niveau courant est le dernier niveau disponible.
     * 
     * @return true si le niveau courant est le dernier niveau, false sinon.
     */
    public boolean estDernierNiveau() {
        return niveauCourant == cheminsDesCartes.size() - 1;
    }

    /**
     * Réinitialise le gestionnaire au tout premier niveau.
     */
    public void reinitialiserNiveaux() {
        niveauCourant = 0;
    }
}
