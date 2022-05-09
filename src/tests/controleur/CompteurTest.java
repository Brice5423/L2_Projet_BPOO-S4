package tests.controleur;

public class CompteurTest {
    private final String nomTest;
    private int nbTestTotal;
    private int nbTestOk;

    public CompteurTest(String nomTest) {
        this.nomTest = nomTest;
        this.nbTestTotal = 0;
        this.nbTestOk = 0;
    }

    public void testOK() {
        this.nbTestTotal++;
        this.nbTestOk++;
    }

    public void testFaux() {
        this.nbTestTotal++;
    }

    public boolean afficheResultatsTest() {
        System.out.println("\n\tRésultats du test : " + this.nomTest);
        System.out.println("Nb test total : " + this.nbTestTotal);
        System.out.println("Nb test Ok : " + this.nbTestOk);
        //System.out.println("Nb test faux : " + (this.nbTestTotal - this.nbTestOk));
        System.out.println("Taux de réussite : " + (this.nbTestOk / this.nbTestTotal * 100) + "%");

        return (this.nbTestOk == this.nbTestTotal);
    }
}
