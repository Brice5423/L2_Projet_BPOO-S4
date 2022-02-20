package home.matier.carte.carteAEffetType;

import home.interfaces.carte.IAjouterCarte;
import home.matier.Joueur;
import home.matier.carte.Carte;
import home.matier.carte.CarteAEffet;

import java.util.ArrayList;

public class CartePlusQuatre extends CarteAEffet implements IAjouterCarte {
    private final int nbCarteADonner = 4;

    @Override
    public void ajouterCarte(Joueur joueurCible, ArrayList<Carte> pioche) {
        int i;

        for (i = 0; i < nbCarteADonner; i++) {
            //joueurCible. // appel fonction pioche carte avec comment paramètre la carte retir à partir de la fonction de la carte de tête de pioche
        }
    }

    // prendre ne compte qu'elle change la couleur
}
