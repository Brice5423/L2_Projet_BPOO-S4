package home.exception;

import home.metier.Joueur;

public class JoueurNonCourantException extends Exception {
    private final Joueur joueurException;

    public JoueurNonCourantException(String msg, Joueur joueurException) {
        super(msg);
        this.joueurException = joueurException;
    }

    public Joueur getJoueurException() {
        return this.joueurException;
    }
}
