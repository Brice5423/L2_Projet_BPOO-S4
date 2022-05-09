package home.exception;

import home.metier.Partie;

public class ParteException extends Exception {
    private final Partie partie;

    public ParteException(Partie partie, String msg) {
        super(msg);
        this.partie = partie;
    }

    public Partie getPartie() {
        return this.partie;
    }
}
