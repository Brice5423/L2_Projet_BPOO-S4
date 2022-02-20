package home.enumeration;

public enum ECarteCouleur {
    NOIR("noir"),
    ROUGE("rouge"),
    BLEU("bleu"),
    VERT("vert"),
    JAUNE("jaune");

    private final String couleur;

    ECarteCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getCouleur() {
        return this.couleur;
    }
}
