package home.matier.carte.carteAEffetType;

import home.enumeration.ECarteCouleur;
import home.interfaces.carte.IChangerCouleur;
import home.matier.carte.CarteAEffet;

public class CarteChangerCouleur extends CarteAEffet implements IChangerCouleur {

    @Override
    public ECarteCouleur changerCouleur() {
        // faire un setter pour changer la couleur
        return ECarteCouleur.BLEU; // example pour ne pas avoir d'erreur
    }
}
