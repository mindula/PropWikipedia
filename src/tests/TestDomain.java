package tests;

import org.grupwiki.graf.Graf;

import java.util.Scanner;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 29/04/15
 */
public class TestDomain {

    public static void main(String args[]) throws Exception {
        Graf g = new Graf();
        System.out.println("Escull una opcio:\n" +
                "1. Test GrafWikipedia\n" +
                "2. Test GrafParser\n" +
                "3. Test Algoritme\n" +
                "4. Test ConjuntComunitatsWiki i ComunitatWiki\n" +
                "5. Test CercaHistorial\n" +
                "6. Finalitzar Test\n");
        Scanner sc = new Scanner(System.in);
        int opcio = sc.nextInt();
        //String[] arguments = new String[]; no volem arguments null
        while (opcio != 6) {
            switch (opcio) {
                case 1:

                    break;
                case 2:
                    TestGrafParser.main(null);
                    break;
                case 3:
                    TestGrafAlgorisme.main(null);
                    break;
                case 4:
                    TestComunitatCjtComunitats.main(null);
                    break;
                case 5:
                    TestCercaHistorial.main(null);
                    break;
            }
        }
    }
}
