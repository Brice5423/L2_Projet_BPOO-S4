package tests;

import tests.controleur.CompteurTest;

public class CoupsIllegauxCartesSimples {
    /**
     * Tous ces tests vont lancer des exceptions.
     * Toutes ces anomalies sont (en principe !) sanctionnées par 2 cartes de punitions,
     * et fin du tour automatique si c’est le joueur courant qui commet la faute.
     * C’est dans le catch de l’exception que doivent être réalisées ou non ces deux actions.
     * Mais pour l’instant, il n’est pas utile de gérer la « punition ».
     * Nous le ferons dans d’autres tests par la suite (une chose après l’autre).
     * Pour chacun des tests de cette partie,
     * il faut réinitialiser la partie pour se retrouver dans les conditions des tests précédents
     * ________________________________________________________________________________
     * Test d’une carte illégale
     *
     * 1) Alice pose le « 6 jaune »
     * 2) Vérifier dans le catch approprié qu'Alice possède toujours 3 cartes dont le « 6 Jaune »
     * ________________________________________________________________________________
     * Test d’un joueur qui pose deux cartes légales de suite
     *
     * 1) Alice pose le « 2 Vert » et finit son tour
     * 2) Bob pose le « 2 Bleu » et finit son tour
     * 3) Charles pose le « 6 Bleu » (RAS, c’est correct, mais Charles ne finit pas le tour)
     * 4) Vérifier que Charles possède 2 cartes
     * 5) Charles pose le « 7 Bleu » (Carte légale, mais il a déjà posé…)
     * 6) Vérifier dans le catch approprié que Charles possède toujours 2 cartes dont le « 2 Bleu »
     * ________________________________________________________________________________
     * Test d’un joueur qui finit son tour sans rien faire
     *
     * 1) Alice finit son tour
     * 2) Vérifier dans le catch approprié qu'Alice possède toujours 3 cartes
     * ________________________________________________________________________________
     * Test d’un joueur qui joue puis pioche
     *
     * 1) Alice joue le « 2 Vert » (RAS, le coup est légal)
     * 2) Alice pioche
     * 3) Vérifier dans le catch approprié qu'Alice possède toujours 2 cartes
     * 4) Vérifier que la carte de la pioche est toujours le « 6 jaune »
     *
     * @return true si succès est à 100% sinon false
     */
    public static boolean executionDuTest() {
        System.out.println("\n\t\t----- Tests coups légaux avec des cartes simples -----\n");

        /* ***** ***** initialisation des compteurs du test ***** ***** */
        CompteurTest compteurTest = new CompteurTest("coups illégaux avec des cartes simples");

        /* ***** ***** Initialiser le fichier test ***** ***** */


        /* ***** ***** Debut test : Test d’une carte illégale ***** ***** */


        /* ***** ***** Debut test : Test d’un joueur qui pose deux cartes légales de suite ***** ***** */


        /* ***** ***** Debut test : Test d’un joueur qui finit son tour sans rien faire ***** ***** */


        /* ***** ***** Debut test : Test d’un joueur qui joue puis pioche ***** ***** */


        return compteurTest.afficheResultatsTest();
    }
}
