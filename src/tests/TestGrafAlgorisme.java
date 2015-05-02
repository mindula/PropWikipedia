package tests;

import graf.NodeCategoria;
import graf.NodeWiki;
import org.grupwiki.graf.Graf;

import java.util.Scanner;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard
 * Data: 4/25/15
 */
public class TestGrafAlgorisme {

    private Graf<NodeWiki> graf;

    public TestGrafAlgorisme(Graf<NodeWiki> graf) {
        this.graf = graf;
    }

    public static void main (String[] args) {
        System.out.println("Escull una opció:\n" +
                "1.  Afegir Node\n"+
                "2.  Afegir Arc\n"+
                "3.  Eliminar Node\n"+
                "4.  Eliminar Arc\n"+
                "5.  Obtenir tots els nodes\n"+
                "6.  Obtenir tots els arcs\n"+
                "7.  Obtenir nodes adjacents\n"+
                "8.  Obtenir l'arc entre dos nodes\n"+
                "9.  Existeix aquest node?\n"+
                "10. Consultar mida\n"+
                "11. Consultar ordre\n"+
                "12. Acabar test\n");
        Scanner sc = new Scanner(System.in);
        int opcio = sc.nextInt();
        while (opcio != 12) {
            switch (opcio) {
                case 1:
                    print("Escriu el nom del node");
                    String nom = sc.nextLine();
                    NodeCategoria n = new NodeCategoria(nom);
                    //graf.afegirNode(n);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    break;
            }
            opcio = sc.nextInt();
        }
    }

    private static void print(String txt) {
        System.out.println(txt);
    }
}
