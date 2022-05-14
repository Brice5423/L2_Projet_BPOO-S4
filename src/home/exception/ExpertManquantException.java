package home.exception;

import home.expert.Expert;
import home.metier.carte.Carte;

public class ExpertManquantException extends Exception {
    private final Carte carteJoueur;
    private final Carte carteDepot;

    public ExpertManquantException(Carte carteJoueur, Carte carteDepot) {
        super("Il doit manqué un expert");
        this.carteJoueur = carteJoueur;
        this.carteDepot = carteDepot;
    }

    public Carte getCarteJoueur() {
        return carteJoueur;
    }

    public Carte getCarteDepot() {
        return carteDepot;
    }
}
