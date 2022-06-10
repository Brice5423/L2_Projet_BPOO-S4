package home.exception;

/**
 * Exception provoquer à la gestion de la partie.
 * Récupère une carte vide dans la pioche ou dans le tas.
 * Manque un joueur ou trop de joueur.
 * Etc.
 */
public class PartieException extends Exception {
    public PartieException(String msg) {
        super(msg);
    }
}
