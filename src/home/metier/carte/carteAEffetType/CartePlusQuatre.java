package home.metier.carte.carteAEffetType;

import home.enumeration.ECarteCouleur;
import home.enumeration.ECarteValeur;
import home.interfaces.carte.IAjouterCarte;
import home.interfaces.carte.IChangerCouleur;
import home.metier.Partie;
import home.metier.carte.CarteAEffet;

import java.util.Scanner;

public class CartePlusQuatre extends CarteAEffet implements IAjouterCarte, IChangerCouleur {
    private static final int nbCarteADonner = 4;

    public CartePlusQuatre(ECarteCouleur carteCouleur, ECarteValeur carteValeur) {
        super(carteCouleur, carteValeur);
    }

    @Override
    public void ajouterCarte(Partie partieEnCours) {
        /* @TODO ajouterCarte (+4) */
        int i;

        for (i = 0; i < nbCarteADonner; i++) {
            //joueurCible. // appel fonction pioche carte avec comment paramètre la carte retir à partir de la fonction de la carte de tête de pioche
        }
    }

    @Override
    public void changerCouleur(Partie partieEnCours) {
        String saisie;
        Scanner entree = new Scanner(System.in);

        do {
            System.out.println("""
                    Donner la couleur de la carte pour le prochain joueur.
                     - R : Rouge
                     - B : Bleu
                     - V : Vert
                     - J : Jaune""");
            saisie = entree.next();

            switch (saisie.toLowerCase()) {
                case "r", "rouge" -> partieEnCours.setCouleurDernierCarte(ECarteCouleur.ROUGE);
                case "b", "bleu" -> partieEnCours.setCouleurDernierCarte(ECarteCouleur.BLEU);
                case "v", "vert" -> partieEnCours.setCouleurDernierCarte(ECarteCouleur.VERT);
                case "j", "jaune" -> partieEnCours.setCouleurDernierCarte(ECarteCouleur.JAUNE);
            }

        } while (partieEnCours.getCouleurDernierCarte().equals(ECarteCouleur.NOIR));
    }
}
