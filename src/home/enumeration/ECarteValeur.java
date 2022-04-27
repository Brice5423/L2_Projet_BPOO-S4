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
    NEUF("9"),
    CHANGER_SENS("changement de sens"),
    CHANGER_COULEUR("changement de couleur"),
    PASSER_TOUR("passer le tour"),
    PLUS_DEUX("plus 2"),
    PLUS_QUATRE("plus 4");

    private final String valeur;

    ECarteValeur(String valeur) {
        this.valeur = valeur;
    }

    public String getValeur() {
        return this.valeur;
    }
}
