package tests;

import domini.ComunitatWiki;
import domini.Sessio;
import graf.GrafWikipedia;
import graf.NodeCategoria;
import graf.NodeWiki;
import domini.OperacionsConjunts;
import org.grupwiki.graf.ConjuntComunitats;
import org.grupwiki.graf.Graf;
import java.util.Scanner;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 29/04/15
 */
public class TestComunitatCjtComunitats {

    public static void main (String[] args) throws Exception {
        GrafWikipedia graf = Sessio.getInstance().getGrafWiki();
        String paraula;
        int ident;
        int ident2;
        NodeCategoria n1;
        System.out.println("Escull una opció:\n" +
                "1. Provar ComunitatWiki\n" +
                "2. Provar ConjuntComunitatWiki\n" +
                "3. Finalitzar el Test");
        Scanner sc = new Scanner(System.in);
        int opcio = sc.nextInt();
        while(opcio != 3){
            switch(opcio){
                case 1:
                    ComunitatWiki C = new ComunitatWiki();
                    System.out.println("Escull opcio:\n" +
                            "0. Veure opcions\n" +
                            "1. Afegir Node\n" +
                            "2. Afegir conjunt de Nodes\n" +
                            "3. Eliminar Node\n" +
                            "4. Obtenir la mida\n" +
                            "5. Obtenir tots els nodes\n" +
                            "6. Obtenir el identificador\n" +
                            "7. Canviar el identificador\n" +
                            "8. Consultar si es buida\n" +
                            "9. Consultar si té un Node n\n" +
                            "10. Consultar si té un Node amb nom s\n" +
                            "11. Obtenir el nom de la comunitat\n" +
                            "12. Canviar el nom de la comunitat\n" +
                            "13. Obtenir la descripció\n" +
                            "14. Editar la descripció\n" +
                            "15. Finalitzar el Test");
                    int opcio2 = sc.nextInt();
                    while(opcio2 != 15){
                        switch(opcio2){
                            case 0:
                                System.out.println("Escull opcio:\n" +
                                        "0. Veure opcions\n" +
                                        "1. Afegir Node\n" +
                                        "2. Afegir conjunt de Nodes\n" +
                                        "3. Eliminar Node\n" +
                                        "4. Obtenir la mida\n" +
                                        "5. Obtenir tots els nodes\n" +
                                        "6. Obtenir el identificador\n" +
                                        "7. Canviar el identificador\n" +
                                        "8. Consultar si es buida\n" +
                                        "9. Consultar si té un Node n\n" +
                                        "10. Consultar si té un Node amb nom s\n" +
                                        "11. Obtenir el nom de la comunitat\n" +
                                        "12. Canviar el nom de la comunitat\n" +
                                        "13. Obtenir la descripció\n" +
                                        "14. Editar la descripció\n" +
                                        "15. Finalitzar el Test");
                                break;
                            case 1:
                                System.out.println("Escriu el nom del node que volguis inserir");
                                paraula = sc.next();
                                n1 = new NodeCategoria(paraula);
                                C.afegirNode(n1);
                                graf.afegirNode(n1);
                                break;
                            case 2:
                                System.out.println("Escriu la mida del conjunt de nodes");
                                int nNodes = sc.nextInt();
                                ComunitatWiki C2 = new ComunitatWiki();
                                for (int i = 0; i < nNodes; i++) {
                                    System.out.println("Escriu el nom del nou node al conjunt de node");
                                    paraula = sc.next();
                                    n1 = new NodeCategoria(paraula);
                                    C2.afegirNode(n1);
                                    graf.afegirNode(n1);
                                }
                                C.afegirCjtNodes(C2);
                                System.out.println("Conjunt de nodes afegit");
                                break;
                            case 3:
                                System.out.println("Escriu el nom del node a eliminar");
                                paraula = sc.next();
                                C.eliminarNode(graf.getNodeCat(paraula));
                                break;
                            case 4:
                                System.out.println(C.mida());
                                break;
                            case 5:
                                System.out.println(C.getNodes());
                                break;
                            case 6:
                                System.out.println(C.getId());
                                break;
                            case 7:
                                System.out.println("Escriu el nou id(Integer)");
                                ident = sc.nextInt();
                                C.setId(ident);
                                break;
                            case 8:
                                System.out.println(C.estaBuida());
                                break;
                            case 9:
                                System.out.println("Escriu el nopm del node");
                                paraula = sc.next();
                                System.out.println(C.teNode(graf.getNodeCat(paraula)));
                                break;
                            case 10:
                                System.out.println("Escriu el nom del node");
                                paraula = sc.next();
                                System.out.println(C.teNode(paraula));
                                break;
                            case 11:
                                System.out.println(C.getNom());
                                break;
                            case 12:
                                System.out.println("Esciu el nou nom");
                                paraula = sc.next();
                                C.setNom(paraula);
                                break;
                            case 13:
                                System.out.println(C.getDescripcio());
                                break;
                            case 14:
                                System.out.println("Escriu la nova descripció");
                                paraula = "holaaa";
                                while (sc.hasNextLine()) {
                                    paraula = sc.nextLine();
                                }
                                C.setDescripcio(paraula);
                                break;
                        }
                        opcio2 = sc.nextInt();
                    }
                    break;
                case 2:
                    ConjuntComunitats<NodeWiki> comunitats = new ConjuntComunitats<NodeWiki>();
                    System.out.println("Escull una opció:\n" +
                            "0. Veure opcions\n" +
                            "1. Afegir Comunitat\n" +
                            "2. Obtenir el nombre de Comunitats\n" +
                            "3. Obtenir la comunitat amb id s\n" +
                            "4. Eliminar una comunitat\n" +
                            "5. Unió de dos comunitats\n" +
                            "6. Intersecció de dos comunitats\n" +
                            "7. Diferència de dos comunitats\n" +
                            "8.Obtenir comunitats" +
                            "9. Finalitzar el test");
                    int opcio3 = sc.nextInt();
                    ComunitatWiki com;
                    while (opcio3 != 9){
                        switch (opcio3){
                            case 0:
                                System.out.println("Escull una opció:\n" +
                                        "0. Veure opcions\n" +
                                        "1. Afegir Comunitat\n" +
                                        "2. Obtenir el nombre de Comunitats\n" +
                                        "3. Obtenir la comunitat amb id s\n" +
                                        "4. Eliminar una comunitat\n" +
                                        "5. Unió de dos comunitats\n" +
                                        "6. Intersecció de dos comunitats\n" +
                                        "7. Diferència de dos comunitats\n" +
                                        "8. Obtenir comunitats" +
                                        "9. Finalitzar el test");
                                break;
                            case 1:
                                System.out.println("Escriu el id de la comunitat");
                                ident = sc.nextInt();
                                com = new ComunitatWiki();
                                comunitats.afegirComunitat(com);
                                break;
                            case 2:
                                System.out.println(comunitats.getNumComunitats());
                                break;
                            case 3:
                                System.out.println("Escriu el id de la comunitat");
                                ident = sc.nextInt();
                                System.out.println(comunitats.getComunitat(ident));
                                break;
                            case 4:
                                System.out.println("Escriu el id de la comunitat");
                                ident = sc.nextInt();
                                comunitats.eliminarComunitat(comunitats.getComunitat(ident));
                                break;
                            case 5:
                                System.out.println("Escriu els id de les dues comunitats");
                                ident = sc.nextInt();
                                ident2 = sc.nextInt();
                                System.out.println(OperacionsConjunts.unio(comunitats.getComunitat(ident), comunitats.getComunitat(ident2)));

                                break;
                            case 6:
                                System.out.println("Escriu els id de les dues comunitats");
                                ident = sc.nextInt();
                                ident2 = sc.nextInt();
                                System.out.println(OperacionsConjunts.interseccio(comunitats.getComunitat(ident), comunitats.getComunitat(ident2)));

                                break;
                            case 7:
                                System.out.println("Escriu els id de les dues comunitats");
                                ident = sc.nextInt();
                                ident2 = sc.nextInt();
                                System.out.println(OperacionsConjunts.diferencia(comunitats.getComunitat(ident), comunitats.getComunitat(ident2)));

                                break;
                            case 8:
                                System.out.println(comunitats.getComunitats());
                                break;
                        }
                        opcio3 = sc.nextInt();
                    }
                    break;
            }
            System.out.println("Escull una opció:\n" +
                    "1. Provar ComunitatWiki\n" +
                    "2. Provar ConjuntComunitatWiki\n" +
                    "3. Finalitzar el Test");
            opcio = sc.nextInt();
        }

    }
}
