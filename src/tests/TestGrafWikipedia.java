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
            System.out.println("Escull una opcio:\n" +
                    "0.  Verue opcions\n" +
                    "1.  Afegir NodeCategoria\n" +
                    "2.  Afegir NodePagina\n" +
                    "3.  Eliminar NodeCategoria\n" +
                    "4.  Eliminar NodePagina\n" +
                    "5.  Afegir ArcPC\n" +
                    "6.  Afegir ArcCsupC\n" +
                    "7.  Afegir ArcCsubC\n" +
                    "8.  Eliminar Arc PC\n" +
                    "9.  Eliminar Arc entre dos Categories" +
                    "10. Obtenir tots els nodes\n" +
                    "11. Obtenir tots els arcs\n" +
                    "12. Obtenir nodes adjacents d'una Categoria\n" +
                    "13. Obtenir nodes adjacents d'una Pagina\n" +
                    "14. Obtenir l'arc entre dos categories\n" +
                    "15. Obtenir l'arc entre una pagina i una categoria\n" +
                    "16. Consultar el grau d'una categoria\n" +
                    "17. Consultar el grau d'una pagina\n" +
                    "18. Existeix aquesta categoria?\n" +
                    "19. Existeix aquesta pagina?\n" +
                    "20. Existeix aquest node? (Depen de que funcioni el punt 18 i 19)\n" +
                    "21. Existeix aquest arc PC?\n" +
                    "22. Existeix aquest arc entre dos categories?\n" +
                    "23. Obtenir Categoria\n" +
                    "24. Obtenir Pagina\n" +
                    "25. Acabar test\n");
            Scanner sc = new Scanner(System.in);
            int opcio = sc.nextInt();
            while (opcio != 25) {
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
                                "8.  Eliminar Arc PC\n" +
                                "9.  Eliminar Arc entre dos Categories" +
                                "10. Obtenir tots els nodes\n" +
                                "11. Obtenir tots els arcs\n" +
                                "12. Obtenir nodes adjacents d'una Categoria\n" +
                                "13. Obtenir nodes adjacents d'una Pagina\n" +
                                "14. Obtenir l'arc entre dos categories\n" +
                                "15. Obtenir l'arc entre una pagina i una categoria\n" +
                                "16. Consultar el grau d'una categoria\n" +
                                "17. Consultar el grau d'una pagina\n" +
                                "18. Existeix aquesta categoria?\n" +
                                "19. Existeix aquesta pagina?\n" +
                                "20. Existeix aquest node? (Depen de que funcioni el punt 18 i 19)\n" +
                                "21. Existeix aquest arc PC?\n" +
                                "22. Existeix aquest arc entre dos categories?\n" +
                                "23. Obtenir Categoria\n" +
                                "24. Obtenir Pagina\n" +
                                "25. Acabar test\n");
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
                        System.out.println("Escriu el nom de la pagina i de la categoria");
                        nom = sc.next();
                        nom2 = sc.next();
                        graf.afegirArcPC(graf.getNodePag(nom), graf.getNodeCat(nom2));
                        break;
                    case 6:
                        System.out.println("Escriu el nom de les dos categories");
                        nom = sc.next();
                        nom2 = sc.next();
                        graf.afegirArcCsupC(graf.getNodeCat(nom), graf.getNodeCat(nom2));
                        break;
                    case 7:
                        System.out.println("Escriu el nom de les dos categories");
                        nom = sc.next();
                        nom2 = sc.next();
                        graf.afegirArcCsubC(graf.getNodeCat(nom), graf.getNodeCat(nom2));
                        break;
                    case 8:
                        System.out.println("Escriu el nom de les dos categories");
                        nom = sc.next();
                        nom2 = sc.next();
                        graf.eliminarArc(graf.getArcEntre(graf.getNodePag(nom), graf.getNodeCat(nom2)));
                        break;
                    case 9:
                        System.out.println("Escriu el nom de les dos categories");
                        nom = sc.next();
                        nom2 = sc.next();
                        graf.eliminarArc(graf.getArcEntre(graf.getNodeCat(nom), graf.getNodeCat(nom2)));
                        break;
                    case 10:
                        System.out.println(graf.getNodes());
                        break;
                    case 11:
                        System.out.println(graf.getArcs());
                        break;
                    case 12:
                        System.out.println("Escriu el nom de la categoria");
                        nom = sc.next();
                        System.out.println(graf.getNodesAdjacents(graf.getNodeCat(nom)));
                        break;
                    case 13:
                        System.out.println("Escriu el nom de la categoria");
                        nom = sc.next();
                        System.out.println(graf.getNodesAdjacents(graf.getNodePag(nom)));
                        break;
                    case 14:
                        System.out.println("Escriu el nom de les dos categories");
                        nom = sc.next();
                        nom2 = sc.next();
                        System.out.println(graf.getArcEntre(graf.getNodeCat(nom), graf.getNodeCat(nom2)));
                        break;
                    case 15:
                        System.out.println("Escriu el nom de la pagina i de la categoria");
                        nom = sc.next();
                        nom2 = sc.next();
                        System.out.println(graf.getArcEntre(graf.getNodePag(nom), graf.getNodeCat(nom2)));
                        break;
                    case 16:
                        System.out.println("Escriu el nom de la categoria");
                        nom = sc.next();
                        System.out.println(graf.getGrau(graf.getNodeCat(nom)));
                        break;
                    case 17:
                        System.out.println("Escriu el nom de la pagina");
                        nom = sc.next();
                        System.out.println(graf.getGrau(graf.getNodePag(nom)));
                        break;
                    case 18:
                        System.out.println("Escriu el nom de la categoria");
                        nom = sc.next();
                        System.out.println(graf.existeixNode(graf.getNodeCat(nom)));
                        break;
                    case 19:
                        System.out.println("Escriu el nom de la pagina");
                        nom = sc.next();
                        System.out.println(graf.existeixNode(graf.getNodePag(nom)));
                        break;
                    case 20:
                        System.out.println("Escriu el nom del node");
                        nom = sc.next();
                        if (graf.existeixNodeCat(nom))
                            System.out.println(graf.existeixNode(graf.getNodeCat(nom)));
                        else System.out.println(graf.existeixNode(graf.getNodePag(nom)));
                        break;
                    case 21:
                        System.out.println("Escriu el nom de la pagina i de la Categoria");
                        nom = sc.next();
                        nom2 = sc.next();
                        System.out.println(graf.existeixArc(graf.getNodePag(nom), graf.getNodeCat(nom2)));
                        break;
                    case 22:
                        System.out.println("Escriu el nom de les dos categories");
                        nom = sc.next();
                        nom2 = sc.next();
                        System.out.println(graf.existeixArc(graf.getNodeCat(nom), graf.getNodeCat(nom2)));
                        break;
                    case 23:
                        System.out.println("Escriu el nom de la categoria");
                        nom = sc.next();
                        System.out.println(graf.getNodeCat(nom));
                        break;
                    case 24:
                        System.out.println("Escriu el nom de la pagina");
                        nom = sc.next();
                        System.out.println(graf.getNodePag(nom));
                        break;

                }
                opcio = sc.nextInt();
            }
        }
}
