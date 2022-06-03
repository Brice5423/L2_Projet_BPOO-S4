package home.exception;

import home.metier.Joueur;

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
