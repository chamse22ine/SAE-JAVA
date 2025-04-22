package vueTexte;

/**
 * La classe SokobanTexte est le point d'entrée pour lancer le jeu Sokoban en mode texte.
 * Elle utilise la classe {@link ModeTexte} pour gérer l'exécution et l'interactivité 
 * en mode console.
 */
public class SokobanTexte {

    /**
     * Méthode principale (point d'entrée) pour exécuter le jeu Sokoban en mode texte.
     * 
     * @param args Les arguments passés en ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {
        // Création d'une instance de ModeTexte pour gérer le jeu en mode console.
        ModeTexte modeTexte = new ModeTexte();
        // Lancement de la partie.
        modeTexte.lancerPartie();
    }
}
