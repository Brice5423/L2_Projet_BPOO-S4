package home.exception;

import home.expert.Expert;
import home.metier.carte.Carte;

public class ExpertManquantException extends Exception {
    private final Carte carteJoueur;
    private final Carte carteDepot;
    private final int nbCarteAPiocher;

    public ExpertManquantException(Carte carteJoueur, Carte carteDepot, int nbCarteAPiocher) {
        super("Il doit manqué un expert");
        this.carteJoueur = carteJoueur;
        this.carteDepot = carteDepot;
        this.nbCarteAPiocher = nbCarteAPiocher;
    }

    public Carte getCarteJoueur() {
        return this.carteJoueur;
    }

    public Carte getCarteDepot() {
        return this.carteDepot;
    }

    public int getNbCarteAPiocher() {
        return this.nbCarteAPiocher;
    }
}
