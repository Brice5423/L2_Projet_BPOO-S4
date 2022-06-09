package home.exception;

import home.metier.Joueur;

/**
 * Exception quand un joueur joue plusieurs fois.
 * Action cible : poserCarte, piocher...
 */
public class JoueurJoueMultipleException extends Exception {
    private final Joueur joueurException;

    public JoueurJoueMultipleException(String msg, Joueur joueurException) {
        super(msg);
        this.joueurException = joueurException;
    }

    public Joueur getJoueurException() {
        return this.joueurException;
    }
}
