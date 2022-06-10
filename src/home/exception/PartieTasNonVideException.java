package home.exception;

/**
 * Exception quand le tas de la partie n'est pas vide.
 */
public class PartieTasNonVideException extends Exception {

    public PartieTasNonVideException(String msg) {
        super(msg);
    }
}
