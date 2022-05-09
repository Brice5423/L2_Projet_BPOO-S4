package home.exception;

import home.expert.Expert;
import home.metier.carte.Carte;

public class ExpertManquantException extends Exception {
    private final Expert expert;
    private final Carte carteJoueur;
    private final Carte carteDepot;

    public ExpertManquantException(Expert expert, Carte carteJoueur, Carte carteDepot) {
        super("Il y a un problème avec l'expert");
        this.expert = expert;
        this.carteJoueur = carteJoueur;
        this.carteDepot = carteDepot;
    }

    public Expert getExpert() {
        return this.expert;
    }

    public Carte getCarteJoueur() {
        return this.carteJoueur;
    }

    public Carte getCarteDepot() {
        return this.carteDepot;
    }
}
