package vueTexte;

import java.io.IOException;

/**
 * La classe Outil contient des méthodes utilitaires pour les interactions en mode texte.
 * Elle facilite la saisie d'un caractère par l'utilisateur via la console.
 */
public class Outil {

    /**
     * Lit un caractère depuis l'entrée standard (console).
     * Si plusieurs caractères sont saisis, seule la première lettre est prise en compte,
     * et le reste est consommé (supprimé du buffer).
     *
     * @return Le premier caractère saisi par l'utilisateur.
     */
    public static char lireCaractere() {
        int rep = ' '; // Valeur par défaut (espace) si une erreur survient.
        int buf;
        try {
            // Lecture du premier caractère
            rep = System.in.read();
            buf = rep;
            // Élimination des caractères restants jusqu'à la fin de la ligne
            while (buf != '\n') {
                buf = System.in.read();
            }
        } catch (IOException e) {
            // En cas d'erreur d'entrée/sortie, on ignore et retourne le caractère par défaut.
        }
        return (char) rep;
    }
}
