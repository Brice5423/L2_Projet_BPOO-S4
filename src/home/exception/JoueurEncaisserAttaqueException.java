package home.exception;

import home.metier.Joueur;

public class JoueurEncaisserAttaqueException extends Exception {
    private final Joueur joueurException;

    public JoueurEncaisserAttaqueException(String msg, Joueur joueurException) {
        super(msg);
        this.joueurException = joueurException;
    }

    public Joueur getJoueurException() {
        return this.joueurException;
    }
}
