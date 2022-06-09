package home.exception;

import home.metier.Joueur;

/**
 * Exception quand un joueur choisi une carte qui n'a pas dans sa main.
 */
public class JoueurMauvaiseCarteException extends Exception {
    private final Joueur joueurException;

    public JoueurMauvaiseCarteException(String msg, Joueur joueurException) {
        super(msg);
        this.joueurException = joueurException;
    }

    public Joueur getJoueurException() {
        return this.joueurException;
    }
}
