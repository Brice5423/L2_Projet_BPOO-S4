package home.metier.carte;

import home.enumeration.ECarteCouleur;

public abstract class Carte {
    private ECarteCouleur carteCouleur;

    public Carte(ECarteCouleur carteCouleur) {
        this.carteCouleur = carteCouleur;
    }

    public ECarteCouleur getCarteCouleur() {
        return this.carteCouleur;
    }

    public void setCarteCouleur(ECarteCouleur carteCouleur) {
        this.carteCouleur = carteCouleur;
    }
}
