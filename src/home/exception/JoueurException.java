package home.exception;

import home.metier.Joueur;

public class JoueurException extends Exception {
    private Joueur joueur;

    public JoueurException(String msg, Joueur joueur) {
        super(msg);
        this.joueur = joueur;
    }
}