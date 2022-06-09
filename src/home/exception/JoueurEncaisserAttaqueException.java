package home.exception;

import home.metier.Joueur;

/**
 * Exception quand un joueur doit encaisser une attaque provoquer par un "+2", "+4", etc.
 */
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
