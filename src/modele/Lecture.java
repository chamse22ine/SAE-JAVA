package modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe Lecture permet de lire un fichier texte contenant la description d'une carte,
 * et de fournir les informations nécessaires, comme le contenu du fichier sous forme de lignes,
 * le nombre total de lignes, et la taille des lignes.
 */
public class Lecture {

    /**
     * Liste contenant toutes les lignes lues depuis le fichier.
     */
    private List<String> lignes;

    /**
     * Nombre total de lignes dans le fichier.
     */
    private final int nbLignes;

    /**
     * Longueur des lignes dans le fichier. 
     * (Suppose que toutes les lignes ont la même taille.)
     */
    private final int tailleLignes;

    /**
     * Constructeur de la classe Lecture. Lit le contenu d'un fichier donné.
     * 
     * @param nomFichier Le chemin vers le fichier à lire.
     */
    public Lecture(String nomFichier) {
        lignes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                lignes.add(ligne);
            }
        } catch (IOException e) {
            System.err.println("Le fichier n'est pas correct : " + e.getMessage());
        }
        nbLignes = lignes.size();
        if (!lignes.isEmpty()) {
            tailleLignes = lignes.get(0).length();
        } else {
            tailleLignes = 0;
        }
    }

    /**
     * Retourne la liste des lignes lues dans le fichier.
     *
     * @return Une liste de chaînes de caractères contenant les lignes du fichier.
     */
    public List<String> getLignes() {
        return lignes;
    }

    /**
     * Retourne le nombre total de lignes dans le fichier.
     *
     * @return Le nombre de lignes.
     */
    public int getNbLignes() {
        return nbLignes;
    }

    /**
     * Retourne la taille des lignes dans le fichier.
     * Supposé constant pour toutes les lignes.
     *
     * @return La taille des lignes (nombre de caractères dans chaque ligne).
     */
    public int getTailleLignes() {
        return tailleLignes;
    }
}
