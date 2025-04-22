package vueGraphique;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import modele.Carte;
import modele.GestionnaireDeLevels;
import modele.Lecture;

/**
 * La classe Sokoban représente la fenêtre principale de l'application Sokoban.
 * Elle gère l'affichage graphique à l'aide de Swing et contrôle le chargement des niveaux.
 */
public class Sokoban extends JFrame {

    /** La vue graphique associée au Sokoban. */
    private VueSokoban vueSokoban;

    /** Le gestionnaire des niveaux (permet le passage et la gestion des niveaux). */
    private final GestionnaireDeLevels gestionnaireDeLevels;

    /**
     * Constructeur de la classe Sokoban.
     * Initialise la fenêtre principale, le gestionnaire de niveaux,
     * et charge le niveau courant au démarrage.
     */
    public Sokoban() {
        super("Sokoban");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialisation du gestionnaire des niveaux
        gestionnaireDeLevels = new GestionnaireDeLevels();

        // Chargement du premier niveau
        chargerNiveau(gestionnaireDeLevels.getNiveauCourant());

        // Configuration de la fenêtre
        setLocationRelativeTo(null); // Centrer la fenêtre
        setVisible(true);
    }

    /**
     * Charge un niveau à partir d'un fichier.
     *
     * @param cheminFichier Chemin du fichier contenant la carte du niveau.
     */
    private void chargerNiveau(String cheminFichier) {
        Lecture lecture = new Lecture(cheminFichier);
        
        // Vérification que le fichier contient des lignes valides
        if (lecture.getNbLignes() > 0) {
            Carte carte = new Carte(lecture.getLignes());

            // Si vueSokoban n'existe pas encore, on l'initialise
            if (vueSokoban == null) {
                vueSokoban = new VueSokoban(carte, this);
                add(vueSokoban);
                pack(); // Ajuste automatiquement la taille de la fenêtre
            } else {
                // Sinon, on met à jour la carte dans la vue existante
                vueSokoban.setCarte(carte);
            }

            // Demande le focus sur le panneau graphique
            SwingUtilities.invokeLater(() -> {
                if (vueSokoban != null && vueSokoban.getGamePanel() != null) {
                    vueSokoban.getGamePanel().requestFocusInWindow();
                }
            });
        } else {
            // Affiche un message d'erreur si le fichier est invalide
            System.out.println("Erreur : Impossible de lire le fichier de carte: " + cheminFichier);
        }
    }

    /**
     * Charge le niveau suivant, si disponible.
     */
    public void chargerNiveauSuivant() {
        if (gestionnaireDeLevels.passerAuNiveauSuivant()) {
            chargerNiveau(gestionnaireDeLevels.getNiveauCourant());
        }
    }

    /**
     * Réinitialise le niveau actuel en rechargeant la carte depuis son fichier.
     */
    public void reinitialiserNiveauActuel() {
        chargerNiveau(gestionnaireDeLevels.getNiveauCourant());
    }

    /**
     * Point d'entrée principal de l'application.
     * Lance la fenêtre principale de Sokoban dans le thread Event Dispatch Thread.
     *
     * @param args Argument de ligne de commande (non utilisé).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Sokoban::new);
    }
}
