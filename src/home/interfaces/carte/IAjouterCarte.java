package home.interfaces.carte;

import home.matier.Joueur;
import home.matier.carte.Carte;

import java.util.ArrayList;

public interface IAjouterCarte {
    void ajouterCarte(Joueur joueurCible, ArrayList<Carte> pioche);
}
