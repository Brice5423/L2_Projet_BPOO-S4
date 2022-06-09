package home.exception;

import home.metier.Joueur;

/**
 * Exception quand un joueur oublie de dire "UNO !" quand il lui reste 1 carte.
 */
public class JoueurOublieDireUnoException extends Exception {
    private final Joueur joueurException;

    public JoueurOublieDireUnoException(String msg, Joueur joueurException) {
        super(msg);
        this.joueurException = joueurException;
    }

    public Joueur getJoueurException() {
        return this.joueurException;
    }
}
