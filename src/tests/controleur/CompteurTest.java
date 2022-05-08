package tests.controleur;

public class CompteurTest {
    private String nomTest;
    private int nbTestTotal;
    private int nbTestOk;
    private int nbTestFaux;

    public CompteurTest(String nomTest) {
        this.nomTest = nomTest;
        this.nbTestTotal = 0;
        this.nbTestOk = 0;
        this.nbTestFaux = 0;
    }

    public void testOK() {
        this.nbTestTotal++;
        this.nbTestOk++;
    }

    public void testFaux() {
        this.nbTestTotal++;
        this.nbTestFaux++;
    }

    public boolean afficheResultatsTest() {
        System.out.println("\n\tRésultats du test : " + this.nomTest);
        System.out.println("Nb test total : " + this.nbTestTotal);
        System.out.println("Nb test Ok : " + this.nbTestOk);
        System.out.println("Nb test faux : " + this.nbTestFaux);

        return (this.nbTestOk == this.nbTestTotal);
    }
}
