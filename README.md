# SAE-Sokoban

# Projet Sokoban

Jeu Sokoban en Java : version graphique (Swing) et version console

![Sokoban Screenshot](images/Haut.gif)

## 📚 Présentation

**Sokoban** est un jeu de réflexion classique où un joueur dirige un robot pour pousser des caisses jusqu'à des emplacements précis ("destinations"). Ce projet, réalisé dans le cadre d'un TP de Programmation Orientée Objet (POO) à l'Université d'Artois, propose :

- Une version en mode texte (console)
- Une version graphique (avec Swing et images)

Aucune des deux interfaces n’est dupliquée : la logique du jeu est mutualisée grâce à une architecture MVC (Modèle-Vue-Contrôleur).

---

## 🚀 Fonctionnalités

- Chargement dynamique des niveaux depuis des fichiers texte (`map/map1.txt`...)
- Affichage textuel et graphique du plateau
- Gestion des déplacements du robot dans 4 directions
- Prise en charge des collisions : murs, caisses, destinations
- Compteur de mouvements effectués
- Détection de la victoire (toutes les caisses sur les destinations)
- Possibilités d’améliorations (compteur, annulation de coup, chargement de niveaux, reset...)

---

## 🗂️ Structure du projet

.
├── images/ # Images pour l’interface graphique
├── map/ # Fichiers textes des niveaux
├── src/
│ ├── modele/ # Logique du jeu (modèle: Robot, Carte, Caisse...)
│ ├── vueTexte/ # Affichage & interaction console
│ └── vueGraphique/ # Affichage graphique (Swing)
└── README.md

---

## ⚙️ Installation & Lancement

### Prérequis

- **Java 17 ou supérieur** (le projet utilise le switch avec flèches et autres syntaxes modernes).

### Compilation

Dans le dossier du projet :

```bash
javac -d bin src/modele/*.java src/vueTexte/*.java src/vueGraphique/*.java

Lancement du jeu console
java -cp bin vueTexte.SokobanTexte

Lancement du jeu graphique
java -cp bin vueGraphique.Sokoban

🕹️ Contrôles
En mode texte (‘azerty’) :

q: gauche
d: droite
z: haut
s: bas
En mode graphique :

Touches fléchées du clavier
📄 Format des cartes
Exemple de carte (map/map1.txt) :

 #######
 #     #
 #.$@$.#
 #  .  #
 #######

Légende :

# : mur
. : destination
$ : caisse
@ : robot
: sol
✏️ Améliorations possibles
Annulation du dernier mouvement
Redémarrage d’une partie
Enchaînement des niveaux
Ajout d’un timer ou affichage du score
Niveaux personnalisés
🙋️ Auteurs et crédits
Projet guidé par [faculté Jean Perrin, Université d'Artois].
Développé par [Ton nom ici].

📃 Licence
Ce projet est proposé dans un cadre éducatif. Toute utilisation ou modification est permise à des fins pédagogiques.
