package home.exception;

import home.metier.Joueur;

/**
 * Exception pour indiquer quand un joueur dit "UNO !" trop tôt.
 * Le joueur le dit trop tôt quand il a 2 cartes ou plus.
 */
public class JoueurDireUnoTropTotException extends Exception {
    private final Joueur joueurException;

    public JoueurDireUnoTropTotException(String msg, Joueur joueurException) {
        super(msg);
        this.joueurException = joueurException;
    }

    public Joueur getJoueurException() {
        return joueurException;
    }
}
