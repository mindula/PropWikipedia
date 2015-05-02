package domini;

import org.grupwiki.graf.ConjuntComunitats;

import java.util.Scanner;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 29/04/15
 */
public class TestComnunitatCjtComunitats {

    public static void main (String[] args){
        System.out.println("Escull una opció:" +
                "1. Provar ComunitatWiki" +
                "2. Provar ConjuntComunitatWiki" +
                "3. Finalitzar el Test");
        Scanner sc = new Scanner(System.in);
        int opcio = sc.nextInt();
        while(opcio != 3){
            switch(opcio){
                case 1:
                    ComunitatWiki C = new ComunitatWiki();
                    System.out.println("Escull opcio:" +
                            "1. Afegir Node" +
                            "2. Afegir conjunt de Nodes" +
                            "3. Eliminar Node" +
                            "4. Obtenir la mida" +
                            "5. Obtenir tots els nodes" +
                            "6. Obtenir el identificador" +
                            "7. Canviar el identificador" +
                            "8. Consultar si es buida" +
                            "9. Consultar si té un Node n" +
                            "10. Obtenir el nom de la comunitat" +
                            "11. Canviar el nom de la comunitat" +
                            "12. Obtenir la descripció" +
                            "13. Editar la descripció" +
                            "14. Finalitzar el Test ");
                    int opcio2 = sc.nextInt();
                    while(opcio2 != 14){
                        switch(opcio2){
                            case 1:
                                System.out.println("Escriu el nom del node que volguis inserir");
                                String paraula = sc.nextLine();
                                C.afegirNode(new NodeCategoria(paraula));
                                break;
                            case 2:
                                System.out.println("");
                                break;
                            case 3:
                                System.out.println("Escriu el nom del node a eliminar");
                                break;
                            case 4:
                                System.out.println(C.mida());
                                break;
                            case 5:
                                System.out.println("");
                                break;
                            case 6:
                                System.out.println(C.getId());
                                break;
                            case 7:
                                System.out.println("Escriu el nou id(Integer)");
                                int ident = sc.nextInt();
                                C.setId(ident);
                                break;
                            case 8:
                                System.out.println(C.estaBuida());
                                break;
                            case 9:

                                break;
                            case 10:
                                System.out.println(C.getNom());
                                break;
                            case 11:
                                System.out.println("Esciu el nou nom");
                                String nom = sc.nextLine();
                                C.setNom(nom);
                                break;
                            case 12:
                                System.out.println(C.getDescripcio());
                                break;
                            case 13:
                                System.out.println("Esciu la nova descripció");
                                String desc = sc.nextLine();
                                C.setDescripcio(desc);
                                break;
                        }
                        opcio2 = sc.nextInt();
                    }
                    break;
                case 2:

                    break;
            }
            opcio = sc.nextInt();
        }

    }
}
