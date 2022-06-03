package home.metier.carte;

import home.enumeration.ECarteCouleur;
import home.enumeration.ECarteValeur;

public class CarteBasique extends Carte {
    private ECarteValeur valeur;

    public CarteBasique(ECarteCouleur couleur, ECarteValeur valeur) {
        super(couleur);
        this.valeur = valeur;
    }

    public ECarteValeur getValeur() {
        return this.valeur;
    }

    @Override
    public String toString() {
        return "CarteBasique{" +
                "valeur = " + this.valeur + ", " +
                "couleur = " + this.getCouleur() +
                '}';
    }

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
