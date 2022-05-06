package home.metier.carte.carteAEffetType;

import home.enumeration.ECarteCouleur;
import home.metier.carte.CarteAEffet;
import home.interfaces.carte.IAjouterCarte;
import home.metier.Partie;

public class CartePlusDeux extends CarteAEffet implements IAjouterCarte {
    private static final int nbCarteADonner = 2;

    public CartePlusDeux(ECarteCouleur carteCouleur) {
        super(carteCouleur);
    }

    @Override
    public void ajouterCarte(Partie partieEnCours) {
        /* @TODO ajouterCarte (+2) */
        int i;

        for (i = 0; i < nbCarteADonner; i++) {
            //joueurCible. // appel fonction pioche carte avec comment paramètre la carte retire à partir de la fonction de la carte de tête de pioche
        }
    }

    @Override
    public void appliquerEffet(Partie partieEnCours) {
        // @TODO appliquerEffet
    }
}
