package home.enumeration;

public enum ECarteValeur {
    ZERO("0"),
    UN("1"),
    DEUX("2"),
    TROIS("3"),
    QUATRE("4"),
    CINQ("5"),
    SIX("6"),
    SEPT("7"),
    HUIT("8"),
    NEUF("9");

    private final String valeur;

    ECarteValeur(String valeur) {
        this.valeur = valeur;
    }

    /**
     * Renvoie la couleur sur un format String.
     * @return valeur
     */
    public String getValeur() {
        return this.valeur;
    }
}
