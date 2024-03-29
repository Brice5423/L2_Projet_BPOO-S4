package home.exception;

import home.metier.Joueur;

/**
 * Exception quand un joueur ne joue pas.
 */
public class JoueurJouePasException extends Exception {
    private final Joueur joueurException;

    public JoueurJouePasException(String msg, Joueur joueurException) {
        super(msg);
        this.joueurException = joueurException;
    }

    public Joueur getJoueurException() {
        return this.joueurException;
    }
}
