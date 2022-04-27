package home.metier.carte.carteAEffetType;

import home.enumeration.ECarteCouleur;
import home.enumeration.ECarteValeur;
import home.interfaces.carte.IAjouterCarte;
import home.metier.Partie;
import home.metier.carte.CarteAEffet;

public class CartePlusDeux extends CarteAEffet implements IAjouterCarte {
    private static final int nbCarteADonner = 2;

    public CartePlusDeux(ECarteCouleur carteCouleur) {
        super(carteCouleur, ECarteValeur.PLUS_QUATRE);
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
