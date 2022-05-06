package home.metier.carte;

import home.enumeration.ECarteCouleur;

public abstract class Carte {
    private ECarteCouleur couleur;

    public Carte(ECarteCouleur couleur) {
        this.couleur = couleur;
    }

    public ECarteCouleur getCouleur() {
        return this.couleur;
    }

    public void setCouleur(ECarteCouleur couleur) {
        this.couleur = couleur;
    }
}
