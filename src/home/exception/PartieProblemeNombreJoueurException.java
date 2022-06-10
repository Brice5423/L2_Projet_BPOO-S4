package home.exception;

/**
 * Exception quand il y a un problème avec le nombre de joueurs dans la partie.
 * Généralement, une partie à 2 à 10 joueurs dans une partie.
 */
public class PartieProblemeNombreJoueurException extends Exception {

    public PartieProblemeNombreJoueurException(String msg) {
        super(msg);
    }
}
