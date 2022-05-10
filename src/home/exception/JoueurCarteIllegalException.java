package home.exception;

import home.metier.Joueur;

public class JoueurCarteIllegalException extends Exception {
    private final Joueur joueurException;

    public JoueurCarteIllegalException(String msg, Joueur joueurException) {
        super(msg);
        this.joueurException = joueurException;
    }

    public Joueur getJoueurException() {
        return this.joueurException;
    }
}
