package home.exception;

import home.metier.Joueur;

/**
 * Exception quand un joueur choisi une carte qu'il n'a pas dans sa main.
 */
public class JoueurJoueCarteAbsentMainException extends Exception {
    private final Joueur joueurException;

    public JoueurJoueCarteAbsentMainException(String msg, Joueur joueurException) {
        super(msg);
        this.joueurException = joueurException;
    }

    public Joueur getJoueurException() {
        return this.joueurException;
    }
}
