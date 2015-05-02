package domini;

import org.grupwiki.graf.Graf;

import java.util.Scanner;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 29/04/15
 */
public class TestDomain {

    public static void main(String args[]) {
        Graf g = new Graf();
        System.out.println("Escull una opció:" +
                "1. Test GrafWikipedia" +
                "2. Test GrafParser" +
                "3. Test Algoritme" +
                "4. Test ConjuntComunitatsWiki i ComunitatWiki" +
                "5. Test CercaHistorial" +
                "6. Finalitzar Test");
        Scanner sc = new Scanner(System.in);
        int opcio = sc.nextInt();
        while (opcio != 6) {
            switch (opcio) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
            }
        }
    }
}
