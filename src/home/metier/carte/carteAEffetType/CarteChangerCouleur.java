package home.metier.carte.carteAEffetType;

import home.enumeration.ECarteCouleur;
import home.metier.carte.Carte;
import home.metier.carte.CarteAEffet;
import home.interfaces.carte.IChangerCouleur;
import home.metier.Partie;

import java.util.Scanner;

public class CarteChangerCouleur extends CarteAEffet implements IChangerCouleur {

    public CarteChangerCouleur() {
        super(ECarteCouleur.NOIR);
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
                case "r", "rouge" -> carteReference.setCarteCouleur(ECarteCouleur.ROUGE);
                case "b", "bleu" -> carteReference.setCarteCouleur(ECarteCouleur.BLEU);
                case "v", "vert" -> carteReference.setCarteCouleur(ECarteCouleur.VERT);
                case "j", "jaune" -> carteReference.setCarteCouleur(ECarteCouleur.JAUNE);
            }

        } while (carteReference.getCarteCouleur().equals(ECarteCouleur.NOIR));
    }

    @Override
    public void appliquerEffet(Partie partieEnCours) {
        // @TODO appliquerEffet
    }
}
