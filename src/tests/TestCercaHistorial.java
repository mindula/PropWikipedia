package tests;

import domini.Cerca;
import domini.Historial;
import domini.InfoCerca;
import domini.Sessio;
import graf.GrafWikipedia;

import java.util.Scanner;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard.gascons
 * Data: 25/04/15
 */
public class TestCercaHistorial {

    public static void main (String[] args) {
        GrafWikipedia g = Sessio.getInstance().getGrafWiki();
        Historial h = Historial.getInstance();
        System.out.println("Escull una opció:\n" +
                "1. Buscar categoria\n" +
                "2. Buscar pàgina\n" +
                "3. Historial de cerques\n" +
                "4. Finalitzar el Test\n");
        Scanner sc = new Scanner(System.in);
        int opcio = sc.nextInt();
        while (opcio != 4) {
            switch (opcio) {
                case 1:
                    System.out.println("Escriu la cateogoria que vulguis buscar a la Wikipedia:");
                    String paraulaC = sc.nextLine();
                    InfoCerca iC;
                    try {
                        iC = Cerca.cercarWikipediaC(g, paraulaC);
                        System.out.println("S'ha trobat la categoria "
                                + iC.getResultats() + " amb data " + iC.getDataCerca());
                        h.afegirCerca(iC);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Escriu la cateogoria que vulguis buscar a la Wikipedia:");
                    String paraulaP = sc.nextLine();
                    InfoCerca iP;
                    try {
                        iP = Cerca.cercarWikipediaC(g, paraulaP);
                        System.out.println("S'ha trobat la categoria "
                                + iP.getResultats() + " amb data " + iP.getDataCerca());
                        h.afegirCerca(iP);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println(h);
                    break;
            }
            opcio = sc.nextInt();
        }
    }
}
