package home.metier.carte;

import home.enumeration.ECarteCouleur;
import home.enumeration.ECarteValeur;

public abstract class Carte {
    ECarteCouleur carteCouleur;
    ECarteValeur carteValeur;

    public Carte(ECarteCouleur carteCouleur, ECarteValeur carteValeur) {
        this.carteCouleur = carteCouleur;
        this.carteValeur = carteValeur;
    }

    public ECarteCouleur getCarteCouleur() {
        return carteCouleur;
    }

    public ECarteValeur getCarteValeur() {
        return carteValeur;
    }

    /**
     * Regarde si la carte peut être poser en fonction de la couleur et la valeur choisi.
     */
    public boolean peutEtrePoser(ECarteCouleur couleurTest, ECarteValeur valeurTest) {
        /* @TODO */
        return true;
    }
}
