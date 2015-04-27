package domini;

import graf.GrafWikipedia;

import java.util.Scanner;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard
 * Data: 4/25/15
 */
public class DriverCercaHistorial {

    private GrafWikipedia g;
    private Historial h;

    private DriverCercaHistorial () {
        super();
    }

    public DriverCercaHistorial(GrafWikipedia g) {
        this.g = g;
    }

    public void main (String[] args) {
        h = Historial.getInstance();
        System.out.println("Escull una opció: 1. Buscar     2. Historial de cerques");
        Scanner sc = new Scanner(System.in);
        int opcio= sc.nextInt();
        switch (opcio) {
            case 1:
                System.out.println("Escriu un nom que vulguis buscar a la Wikipedia:");
                sc = new Scanner(System.in);
                String paraula = sc.nextLine();
                InfoCerca i = Cerca.cercarWikipedia(g, paraula);
                if (i.getResultat() != null) {
                    System.out.println("S'ha trobat el node" + i.getResultat() + "amb data" + i.getDataCerca());
                    h.afegirCerca(i);
                }
                else System.out.println("No s'ha trobat el resultat dins la Wikipedia");
                break;
            case 2:
                System.out.println(h);
                break;
        }
    }
}
