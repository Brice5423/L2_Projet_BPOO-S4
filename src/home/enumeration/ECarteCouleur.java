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

    /**
     * Renvoie la couleur sur un format String.
     * @return couleur
     */
    public String getCouleur() {
        return this.couleur;
    }
}
