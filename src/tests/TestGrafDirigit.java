package tests;

import graf.GrafDirigit;
import org.grupwiki.graf.Arc;

import java.util.Scanner;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 02/05/15
 */
public class TestGrafDirigit {
    public static void main(String args[]) {
        GrafDirigit<String> graf = new GrafDirigit<String>();
        String nom;
        String nom2;
        Arc<String> a;
        System.out.println("Escull una opció:\n" +
                "0.  Veure opcions\n" +
                "1.  Afegir Node\n" +
                "2.  Afegir Arc\n" +
                "3.  Eliminar Node\n" +
                "4.  Eliminar Arc\n" +
                "5.  Obtenir tots els nodes\n" +
                "6.  Obtenir tots els arcs\n" +
                "7.  Obtenir nodes adjacents\n" +
                "8.  Obtenir l'arc entre dos nodes\n" +
                "9.  Existeix aquest node?\n" +
                "10. Consultar mida\n" +
                "11. Consultar ordre\n" +
                "12. Acabar test\n");
        Scanner sc = new Scanner(System.in);
        int opcio = sc.nextInt();
        while (opcio != 12) {
            switch (opcio) {
                case 0:
                    System.out.println("Escull una opció:\n" +
                            "0.  Veure opcions\n" +
                            "1.  Afegir Node\n" +
                            "2.  Afegir Arc\n" +
                            "3.  Eliminar Node\n" +
                            "4.  Eliminar Arc\n" +
                            "5.  Obtenir tots els nodes\n" +
                            "6.  Obtenir tots els arcs\n" +
                            "7.  Obtenir nodes adjacents\n" +
                            "8.  Obtenir l'arc entre dos nodes\n" +
                            "9.  Existeix aquest node?\n" +
                            "10. Consultar mida\n" +
                            "11. Consultar ordre\n" +
                            "12. Acabar test\n");
                    break;
                case 1:
                    System.out.println("Escriu el nom del node");
                    nom = sc.next();
                    graf.afegirNode(nom);
                    break;
                case 2:
                    System.out.println("Escriu el nom dels dos nodes");
                    nom = sc.next();
                    nom2 = sc.next();
                    a = new Arc<String>(nom, nom2);
                    graf.afegirArc(a);
                    break;
                case 3:
                    System.out.println("Escriu el nom del node a eliminar");
                    nom = sc.next();
                    graf.eliminarNode(nom);
                    break;
                case 4:
                    System.out.println("Escriu el nom dels dos nodes del arc a eliminar");
                    nom = sc.next();
                    nom2 = sc.next();
                    a = graf.getArcEntre(nom, nom2);
                    graf.eliminarArc(a);
                    break;
                case 5:
                    System.out.println(graf.getNodes());
                    break;
                case 6:
                    System.out.println(graf.getArcs());
                    break;
                case 7:
                    System.out.println("Escriu el nom del node");
                    nom = sc.next();
                    System.out.println(graf.getNodesAdjacents(nom));
                    break;
                case 8:
                    System.out.println("Escriu el nom dels dos nodes");
                    nom = sc.next();
                    nom2 = sc.next();
                    System.out.println(graf.getArcEntre(nom, nom2));
                    break;
                case 9:
                    System.out.println("Escriu el nom del node");
                    nom = sc.next();
                    System.out.println(graf.existeixNode(nom));
                    break;
                case 10:
                    System.out.println(graf.mida());
                    break;
                case 11:
                    System.out.println(graf.ordre());
                    break;
            }
            opcio = sc.nextInt();
        }
    }
}
