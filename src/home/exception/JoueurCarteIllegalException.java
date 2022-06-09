package home.exception;

import home.metier.Joueur;

/**
 * Exception pour indiquer que le joueur à poser une carte illegal.
 */
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
