package tests;

import domini.Controladors.CtrlWikipedia;
import graf.GrafParser;
import graf.GrafWikipedia;

import java.io.IOException;
import java.util.Scanner;

/**
 * Grup 3: Wikipedia
 * Usuari: aleix.paris
 * Data: 24/04/15
 */

public class TestGrafParser {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escriu:\n" +
                "0 per provar un test per defecte\n" +
                "1 per escriure el path d'un fitxer en concret\n" +
                "2 per acabar el test\n");
        int opcio = sc.nextInt();
        if(opcio == 2) return;
        String path = "";
        switch(opcio) {
            case 0: // test default
                System.out.println("Escriu el numero del test:");
                System.out.println("test 0: misc/cats_test.txt");
                System.out.println("test 1: misc/cats_small_test.txt");
                System.out.println("test 2: misc/cats.txt (fitxer molt gran)");
                System.out.println("test 3: misc/cats_10000.txt");
                opcio = sc.nextInt();
                switch (opcio) {
                    case 0:
                        path = "misc/cats_test.txt";
                        break;
                    case 1:
                        path = "misc/cats_small_test.txt";
                        break;
                    case 2:
                        path = "misc/cats.txt";
                        break;
                    case 3:
                        path = "misc/cats_10000.txt";
                }
                break;
            case 1: // test fitxer concret
                System.out.println("Escriu el path del fitxer:");
                sc.nextLine();
                path = sc.nextLine();
                break;
        }


        long startTime = System.currentTimeMillis();

        GrafWikipedia g = CtrlWikipedia.getInstance().getGrafWiki();
        GrafParser grafParser = new GrafParser(g);
        try {
            grafParser.parse(path);
            long elapsedTime = System.currentTimeMillis()-startTime;
            System.out.println("S'han trigat: "+elapsedTime+"ms");

            System.out.println("Vols mostrar el graf resultat? 1 - Si, 0 - No");
            opcio = sc.nextInt();
            if (opcio == 1) System.out.println(g);
        } catch (IOException e) {
            System.out.println("Error, no s'ha trobat el fitxer " + e.getMessage());
        }
    }
}
