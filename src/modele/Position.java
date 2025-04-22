package modele;

/**
 * La classe Position représente les coordonnées (x, y) d'un élément dans le jeu.
 * Elle permet d'effectuer des comparaisons entre positions et peut être utilisée
 * comme clé dans des structures de données telles que des ensembles ou des maps
 * grâce à la redéfinition des méthodes `equals` et `hashCode`.
 */
public class Position {

    /**
     * Coordonnée x de la position.
     */
    public int x;

    /**
     * Coordonnée y de la position.
     */
    public int y;

    /**
     * Constructeur de la classe Position.
     *
     * @param x La coordonnée x.
     * @param y La coordonnée y.
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Vérifie si deux objets Position sont égaux.
     * Deux positions sont considérées comme égales si elles ont les mêmes coordonnées x et y.
     *
     * @param o L'objet à comparer avec cette position.
     * @return true si les deux positions ont les mêmes coordonnées, false sinon.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Position))
            return false;
        Position p = (Position) o;
        return this.x == p.x && this.y == p.y;
    }

    /**
     * Génère un code de hachage unique pour cette position.
     *
     * @return Le code de hachage basé sur les coordonnées x et y.
     */
    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}
