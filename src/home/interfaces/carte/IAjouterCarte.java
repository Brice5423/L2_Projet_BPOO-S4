package home.interfaces.carte;

import home.metier.Joueur;
import home.metier.carte.Carte;

import java.util.ArrayList;

public interface IAjouterCarte {
    void ajouterCarte(Joueur joueurCible, ArrayList<Carte> pioche);
}
