package tests;

import domini.Sessio;
import graf.GrafWikipedia;

import java.util.Scanner;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 03/05/2015.
 */
public class TestGrafWikipedia {
        public static void main(String[] args) {
            GrafWikipedia graf = Sessio.getInstance().getGrafWiki();
            String nom;
            String nom2;
            int n;
            System.out.println("Escull una opcio:\n" +
                    "0.  Verue opcions\n" +
                    "1.  Afegir Node\n" +
                    "2.  Eliminar Node\n" +
                    "3.  Afegir ArcPC\n" +
                    "4.  Afegir ArcCsupC\n" +
                    "5.  Afegir ArcCsubC\n" +
                    "6.  Eliminar Arc\n" +
                    "7.  Obtenir tots els nodes\n" +
                    "8.  Obtenir tots els arcs\n" +
                    "9.  Obtenir nodes adjacents\n" +
                    "10. Obtenir l'arc entre dos nodes\n" +
                    "11. Consultar el grau d'un node\n" +
                    "12. Existeix aquest node?\n" +
                    "13. Existeix aquesta categoria?\n" +
                    "14. Existeix aquesta pagina?\n" +
                    "15. Existeix aquest arc?\n" +
                    "16. Obtenir Categoria\n" +
                    "17. Obtenir Pagina\n" +
                    "18. Consultar mida\n" +
                    "19. Consultar ordre\n" +
                    "20. Acabar test\n");
            Scanner sc = new Scanner(System.in);
            int opcio = sc.nextInt();
            while (opcio != 21) {
                switch (opcio) {
                    case 0:
                        System.out.println("Escull una opcio:\n" +
                                "0.  Verue opcions\n" +
                                "1.  Afegir NodeCategoria\n" +
                                "2.  Afegir NodePagina\n" +
                                "3.  Eliminar NodeCategoria\n" +
                                "4.  Eliminar NodePagina\n" +
                                "5.  Afegir ArcPC\n" +
                                "6.  Afegir ArcCsupC\n" +
                                "7.  Afegir ArcCsubC\n" +
                                "8.  Eliminar Arc\n" +
                                "9.  Obtenir tots els nodes\n" +
                                "10.  Obtenir tots els arcs\n" +
                                "11.  Obtenir nodes adjacents\n" +
                                "11. Obtenir l'arc entre dos nodes\n" +
                                "12. Consultar el grau d'un node\n" +
                                "13. Existeix aquest node?\n" +
                                "14. Existeix aquesta categoria?\n" +
                                "15. Existeix aquesta pagina?\n" +
                                "16. Existeix aquest arc?\n" +
                                "17. Obtenir Categoria\n" +
                                "18. Obtenir Pagina\n" +
                                "19. Consultar mida\n" +
                                "20. Consultar ordre\n" +
                                "21. Acabar test\n");
                        break;
                    case 1:
                        System.out.println("Escriu el nom del node");
                        nom = sc.next();
                        graf.afegirNode(graf.getNodeCat(nom));
                        break;
                    case 2:
                        System.out.println("Escriu el nom del node");
                        nom = sc.next();
                        graf.afegirNode(graf.getNodePag(nom));
                        break;
                    case 3:
                        System.out.println("Escriu el nom del node");
                        nom = sc.next();
                        graf.eliminarNode(graf.getNodeCat(nom));
                        break;
                    case 4:
                        System.out.println("Escriu el nom del node");
                        nom = sc.next();
                        graf.eliminarNode(graf.getNodePag(nom));
                        break;
                    case 5:
                        System.out.println("Escriu el nom de la pagina ");
                        nom = sc.next();
                        nom2 = sc.next();
                        graf.afegirArcPC(graf.getNodePag(nom), graf.getNodeCat(nom2));
                        break;


                }
            }
        }
}
