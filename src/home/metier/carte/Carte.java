package home.metier.carte;

import home.enumeration.ECarteCouleur;

/**
 * Class abstract pour définir une carte UNO
 */
public abstract class Carte {
    /** Défini la couleur de la carte */
    private ECarteCouleur couleur;

    /**
     * Constructeur pour créer une carte.
     * @param couleur couleur de la carte
     */
    public Carte(ECarteCouleur couleur) {
        this.couleur = couleur;
    }

    /**
     * Getter pour la couleur de la carte
     * @return couleur de la carte
     */
    public ECarteCouleur getCouleur() {
        return this.couleur;
    }
}
