package home.metier.carte.carteAEffetType;

import home.enumeration.ECarteCouleur;
import home.enumeration.ECarteValeur;
import home.interfaces.carte.IChangerCouleur;
import home.metier.carte.CarteAEffet;

public class CarteChangerCouleur extends CarteAEffet implements IChangerCouleur {

    public CarteChangerCouleur(ECarteCouleur carteCouleur, ECarteValeur carteValeur) {
        super(carteCouleur, carteValeur);
    }

    @Override
    public ECarteCouleur changerCouleur() {
        // faire un setter pour changer la couleur
        return ECarteCouleur.BLEU; // example pour ne pas avoir d'erreur
    }
}
