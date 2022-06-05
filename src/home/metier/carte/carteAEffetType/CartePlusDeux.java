package home.metier.carte.carteAEffetType;

import home.enumeration.ECarteCouleur;
import home.metier.carte.CarteAEffet;
import home.interfaces.carte.IAjouterCarte;
import home.metier.Partie;

/**
 * Class qui défini une carte plus deux.
 */
public class CartePlusDeux extends CarteAEffet implements IAjouterCarte {
    /** Constant qui permet de savoir le nombre de cartes à donner */
    private static final int nbCarteADonner = 2;

    /**
     * Constructeur pour une carte plus deux.
     * @param couleur couleur de la carte
     */
    public CartePlusDeux(ECarteCouleur couleur) {
        super(couleur);
    }

    @Override
    public void ajouterCarte(Partie partieEnCours) {
        partieEnCours.ajoutNbCarteAPiocher(nbCarteADonner);
    }

    @Override
    public void appliquerEffet(Partie partieEnCours) {
        this.ajouterCarte(partieEnCours);
    }

    /**
     * Fonction redéfinie (@Override).
     * Renvoie la carte plus deux sous un format de chaine de caractère String.
     * @return la carte basique en String
     */
    @Override
    public String toString() {
        return "CartePlusDeux{" +
                "couleur = " + this.getCouleur() +
                "}";
    }

    /**
     * Fonction redéfinie (@Override).
     * Vérifie l'égalité entre 2 CartePlusDeux.
     * L'égalité entre les deux CartePlusDeux est bonne quand ils ont la même couleur
     * @param o l'objet CartePlusDeux qu'on veut vérifier l'égalité
     * @return true : sont égaux / false : ne sont pas égaux
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        CartePlusDeux that = (CartePlusDeux) o;

        return (this.getCouleur() == that.getCouleur());
    }
}
