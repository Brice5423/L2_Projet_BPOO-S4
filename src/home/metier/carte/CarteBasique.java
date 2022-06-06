package home.metier.carte;

import home.enumeration.ECarteCouleur;
import home.enumeration.ECarteValeur;

/**
 * Class qui défini une carte basique.
 */
public class CarteBasique extends Carte {
    /** Une carte basique a une valeur (0 à 9) */
    private ECarteValeur valeur;

    /**
     * Constructeur pour une CarteBasique.
     * @param couleur couleur de la carte
     * @param valeur valeur de la carte
     */
    public CarteBasique(ECarteCouleur couleur, ECarteValeur valeur) {
        super(couleur);
        this.valeur = valeur;
    }

    /**
     * Getter de la valeur de la carte.
     * @return valeur de la carte
     */
    public ECarteValeur getValeur() {
        return this.valeur;
    }

    /**
     * Fonction redéfinie (@Override).
     * Renvoie la carte basique sous un format de chaine de caractère String.
     * @return la carte basique en String
     */
    @Override
    public String toString() {
        return "CarteBasique{" +
                "valeur = " + this.valeur + ", " +
                "couleur = " + this.getCouleur() +
                '}';
    }

    /**
     * Fonction redéfinie (@Override).
     * Vérifie l'égalité entre 2 CarteBasique.
     * L'égalité entre les deux CarteBasique est bonne s'ils ont la même valeur et la même couleur
     * @param o l'objet CarteBasique qui est vérifié par l'égalité
     * @return true : sont égaux / false : ne sont pas égaux
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        CarteBasique that = (CarteBasique) o;

        return ((this.valeur == that.valeur) && (this.getCouleur() == that.getCouleur()));
    }
}
