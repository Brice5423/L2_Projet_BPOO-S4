package home.enumeration;

public enum ECarteCouleur {
    NOIR("Noir"),
    ROUGE("Rouge"),
    BLEU("Bleu"),
    VERT("Vert"),
    JAUNE("Jaune");

    private final String couleur;

    ECarteCouleur(String couleur) {
        this.couleur = couleur;
    }

    /**
     * Renvoie la couleur sur un format String.
     *
     * @return couleur
     */
    public String getCouleur() {
        return this.couleur;
    }
}
