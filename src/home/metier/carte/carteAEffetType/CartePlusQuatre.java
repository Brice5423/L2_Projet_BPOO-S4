package home.metier.carte.carteAEffetType;

import home.enumeration.ECarteCouleur;
import home.metier.carte.CarteAEffet;
import home.interfaces.carte.IAjouterCarte;
import home.interfaces.carte.IChangerCouleur;
import home.metier.Partie;
import home.metier.carte.Carte;

import java.util.Scanner;

public class CartePlusQuatre extends CarteAEffet implements IAjouterCarte, IChangerCouleur {
    private static final int nbCarteADonner = 4;

    public CartePlusQuatre() {
        super(ECarteCouleur.NOIR);
    }

    @Override
    public void ajouterCarte(Partie partieEnCours) {
        /* @TODO ajouterCarte (+4) */
        int i;

        for (i = 0; i < nbCarteADonner; i++) {
            //joueurCible. // appel fonction pioche carte avec comment paramètre la carte retire à partir de la fonction de la carte de tête de pioche
        }
    }

    @Override
    public void changerCouleur(Partie partieEnCours) {
        Scanner entree = new Scanner(System.in);
        Carte carteReference;

        carteReference = partieEnCours.getCarteReference();

        do {
            System.out.println("""
                    Donner la couleur de la carte pour le prochain joueur.
                    \t- R : Rouge
                    \t- B : Bleu
                    \t- V : Vert
                    \t- J : Jaune""");

            switch (entree.next().toLowerCase()) {
                case "r", "rouge" -> carteReference.setCouleur(ECarteCouleur.ROUGE);
                case "b", "bleu" -> carteReference.setCouleur(ECarteCouleur.BLEU);
                case "v", "vert" -> carteReference.setCouleur(ECarteCouleur.VERT);
                case "j", "jaune" -> carteReference.setCouleur(ECarteCouleur.JAUNE);
            }

        } while (carteReference.getCouleur().equals(ECarteCouleur.NOIR));
    }

    @Override
    public void appliquerEffet(Partie partieEnCours) {
        // @TODO appliquerEffet
    }

    @Override
    public String toString() {
        return "CartePlusQuatre";
    }
}
