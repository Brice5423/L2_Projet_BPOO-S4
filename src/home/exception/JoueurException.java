package home.exception;

import home.metier.Joueur;

public class JoueurException extends Exception {
    private final Joueur joueurException;
    public JoueurException(String msg, Joueur joueurException) {
        super(msg);
        this.joueurException = joueurException;
    }

    public Joueur getJoueurException() {
        return this.joueurException;
    }
}
