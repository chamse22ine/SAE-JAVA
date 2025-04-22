package vueGraphique;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;
import modele.Carte;
import modele.Direction;
import modele.Element;
import modele.GestionnaireDeLevels;
import modele.Lecture;
import modele.Position;

/**
 * La classe VueSokoban gère l'affichage graphique et les interactions utilisateur
 * pour le jeu Sokoban. Elle affiche une carte, gère les mouvements du robot,
 * et permet de charger ou de redémarrer les niveaux.
 */
public class VueSokoban extends JPanel implements KeyListener {

    /** Carte actuelle affichée dans le jeu. */
    private Carte carte;

    /** Map associant les caractères/symboles des éléments aux images correspondantes. */
    private final Map<Character, BufferedImage> images;

    /** Taille d'une case (en pixels) dans la vue graphique. */
    private final int tailleCase = 32;

    /** Gestionnaire des niveaux pour naviguer entre les niveaux. */
    private final GestionnaireDeLevels gestionnaireDeLevels;

    /** Référence à la fenêtre contenant cette vue (parent JFrame). */
    private final JFrame parent;

    /** Bouton permettant de redémarrer le niveau actuel. */
    private final JButton restartButton;

    /** Panneau servant à afficher graphiquement la carte du jeu. */
    private JPanel gamePanel;

    /**
     * Retourne le panneau de jeu (gamePanel).
     *
     * @return gamePanel, le panneau où la carte est dessinée.
     */
    public JPanel getGamePanel() {
        return gamePanel;
    }

    /**
     * Constructeur de VueSokoban.
     * Initialise la vue avec la carte donnée, configure les composants graphiques,
     * et charge les images nécessaires.
     *
     * @param carte La carte à afficher initialement.
     * @param parent La fenêtre contenant cette vue.
     */
    public VueSokoban(Carte carte, JFrame parent) {
        this.carte = carte;
        this.parent = parent;
        this.images = new HashMap<>();
        this.gestionnaireDeLevels = new GestionnaireDeLevels();
        chargerImages();

        setLayout(new BorderLayout());

        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dessinerCarte(g);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(carte.getLargeur() * tailleCase, carte.getHauteur() * tailleCase);
            }
        };

        gamePanel.addKeyListener(this);
        gamePanel.setFocusable(true);

        restartButton = new JButton("Recommencer");
        restartButton.addActionListener(e -> {
            reinitialiserNiveau();
            gamePanel.requestFocusInWindow();
        });

        add(gamePanel, BorderLayout.CENTER);
        add(restartButton, BorderLayout.SOUTH);
    }

    /**
     * Redémarre le niveau actuel.
     */
    public void reinitialiserNiveau() {
        String currentLevel = gestionnaireDeLevels.getNiveauCourant();
        Lecture lecture = new Lecture(currentLevel);
        if (lecture.getNbLignes() > 0) {
            setCarte(new Carte(lecture.getLignes()));
        }
    }

    /**
     * Dessine la carte actuelle sur le panneau graphique.
     *
     * @param g L'objet Graphics utilisé pour dessiner.
     */
    private void dessinerCarte(Graphics g) {
        for (int i = 0; i < carte.getHauteur(); i++) {
            for (int j = 0; j < carte.getLargeur(); j++) {
                Element element = carte.getElement(j, i);

                char baseSymbole = ' ';
                for (Position dest : carte.getDestinations()) {
                    if (dest.x == j && dest.y == i) {
                        baseSymbole = '.';
                        break;
                    }
                }

                BufferedImage baseImage = images.get(baseSymbole);
                if (baseImage != null) {
                    g.drawImage(baseImage, j * tailleCase, i * tailleCase, tailleCase, tailleCase, null);
                }

                BufferedImage elementImage = images.get(element.getSymbole());
                if (elementImage != null && element.getSymbole() != ' ' && element.getSymbole() != '.') {
                    g.drawImage(elementImage, j * tailleCase, i * tailleCase, tailleCase, tailleCase, null);
                }

                if (element == carte.getRobot()) {
                    char dirChar = switch (carte.getRobot().getDirection()) {
                        case HAUT -> 'h';
                        case BAS -> 'b';
                        case GAUCHE -> 'g';
                        case DROITE -> 'd';
                    };
                    BufferedImage robotImage = images.get(dirChar);
                    if (robotImage != null) {
                        g.drawImage(robotImage, j * tailleCase, i * tailleCase, tailleCase, tailleCase, null);
                    }
                }
            }
        }
    }

    /**
     * Retourne la dimension préférée du panneau, basée sur la taille de la carte.
     *
     * @return Dimension des dimensions préférées du panneau.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(carte.getLargeur() * tailleCase, carte.getHauteur() * tailleCase + 40);
    }

    /**
     * Met à jour la carte et redimensionne la fenêtre si nécessaire.
     *
     * @param carte La nouvelle carte à afficher.
     */
    public void setCarte(Carte carte) {
        this.carte = carte;
        if (parent != null) {
            parent.pack();
            parent.setLocationRelativeTo(null);
        }
        repaint();
    }

    /**
     * Charge toutes les images nécessaires pour les éléments et directions.
     */
    private void chargerImages() {
        try {
            images.put('#', ImageIO.read(new File("./src/images/mur.gif")));
            images.put('/', ImageIO.read(new File("./src/images/sol.gif")));
            images.put(' ', ImageIO.read(new File("./src/images/sol.gif")));
            images.put('.', ImageIO.read(new File("./src/images/but.gif")));
            images.put('$', ImageIO.read(new File("./src/images/caisse1.gif")));
            images.put('*', ImageIO.read(new File("./src/images/caisse2.gif")));
            images.put('@', ImageIO.read(new File("./src/images/Bas.gif")));
            images.put('h', ImageIO.read(new File("./src/images/Haut.gif")));
            images.put('b', ImageIO.read(new File("./src/images/Bas.gif")));
            images.put('g', ImageIO.read(new File("./src/images/Gauche.gif")));
            images.put('d', ImageIO.read(new File("./src/images/Droite.gif")));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des images.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Charge le niveau suivant ou affiche un message de fin si aucun niveau
     * n'est disponible.
     */
    private void chargerNiveauSuivant() {
        if (gestionnaireDeLevels.passerAuNiveauSuivant()) {
            String cheminCarte = gestionnaireDeLevels.getNiveauCourant();
            Lecture lecture = new Lecture(cheminCarte);
            if (lecture.getNbLignes() > 0) {
                setCarte(new Carte(lecture.getLignes()));
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors du chargement du niveau suivant.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Félicitations ! Vous avez terminé tous les niveaux !");
            int reponse = JOptionPane.showConfirmDialog(this, 
                    "Voulez-vous recommencer depuis le début ?", 
                    "Jeu terminé", JOptionPane.YES_NO_OPTION);
            if (reponse == JOptionPane.YES_OPTION) {
                gestionnaireDeLevels.reinitialiserNiveaux();
                chargerNiveauSuivant();
            }
        }
    }

    /**
     * Gère les événements de clavier lors de la pression d'une touche.
     *
     * @param e L'événement lié à la touche pressée.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        Direction direction = switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> Direction.HAUT;
            case KeyEvent.VK_DOWN -> Direction.BAS;
            case KeyEvent.VK_LEFT -> Direction.GAUCHE;
            case KeyEvent.VK_RIGHT -> Direction.DROITE;
            case KeyEvent.VK_R -> {
                reinitialiserNiveau();
                yield null;
            }
            default -> null;
        };

        if (direction != null) {
            carte.deplacerRobot(direction);
            repaint();
            if (carte.finDePartie()) {
                JOptionPane.showMessageDialog(this, "Bravo, vous avez gagné avec " + carte.getNbMouvements() + " mouvements !");
                int reponse = JOptionPane.showConfirmDialog(this,
                        "Voulez-vous passer au niveau suivant ?",
                        "Niveau terminé", JOptionPane.YES_NO_OPTION);

                if (reponse == JOptionPane.YES_OPTION) {
                    chargerNiveauSuivant();
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
