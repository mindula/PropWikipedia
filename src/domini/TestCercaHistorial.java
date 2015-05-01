package domini;

import graf.GrafWikipedia;

import java.util.Scanner;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard
 * Data: 4/25/15
 */
public class TestCercaHistorial {

    public static void main (String[] args) {
        GrafWikipedia g = new GrafWikipedia();         //solucionar graf q li passo
        Historial h = Historial.getInstance();
        System.out.println("Escull una opció: 1. Buscar     2. Historial de cerques     3. Finalitzar test");
        Scanner sc = new Scanner(System.in);
        int opcio = sc.nextInt();
        while (opcio != 3) {
            switch (opcio) {
                case 1:
                    System.out.println("Escriu un nom que vulguis buscar a la Wikipedia:");
                    String paraula = sc.nextLine();
                    InfoCerca i;
                    try {
                        i = Cerca.cercarWikipedia(g, paraula);
                        System.out.println("S'ha trobat el node " + i.getResultat() + " amb data " + i.getDataCerca());
                        h.afegirCerca(i);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println(h);
                    break;
            }
            opcio = sc.nextInt();
        }
    }
}
