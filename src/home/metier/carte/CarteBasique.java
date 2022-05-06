package home.metier.carte;

import home.enumeration.ECarteCouleur;
import home.enumeration.ECarteValeur;

public class CarteBasique extends Carte {
    private ECarteValeur carteValeur;

    public CarteBasique(ECarteCouleur carteCouleur, ECarteValeur carteValeur) {
        super(carteCouleur);
        this.carteValeur = carteValeur;
    }

    public ECarteValeur getCarteValeur() {
        return this.carteValeur;
    }

    public void setCarteValeur(ECarteValeur carteValeur) {
        this.carteValeur = carteValeur;
    }
}
