package home.exception;

import home.metier.Joueur;

/**
 * Exception quand un joueur joue quand il n'est pas le joueur courant.
 */
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
