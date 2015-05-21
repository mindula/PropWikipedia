package tests;

import domini.Controladors.CtrlDomini;
import domini.InfoCerca;
import graf.NodeCategoria;

import java.util.Scanner;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 29/04/15
 */
public class TestInfoCerca {

    public static void main(String[] args){
        System.out.println("Escriu el nom del node i la data");
        Scanner sc = new Scanner(System.in);
        String nom = sc.next();
        NodeCategoria a = new NodeCategoria(nom);
        String data = sc.next();
        CtrlDomini s = CtrlDomini.getInstance();
        InfoCerca I = new InfoCerca(s.getGrafWiki().getCategories(),data);
        System.out.println("Escull una opcio:\n" +
                "0. Veure opcions\n" +
                "1. Obtenir el resultat\n" +
                "2. Obtenir la data de cerca\n" +
                "3. Finalitzar Test\n");
        int opcio = sc.nextInt();
        while (opcio != 3){
            switch (opcio){
                case 0:
                    System.out.println("Escull una opcio:\n" +
                            "0. Veure opcions\n" +
                            "1. Obtenir el resultat\n" +
                            "2. Obtenir la data de cerca\n" +
                            "3. Finalitzar Test\n");
                    break;
                case 1:
                    System.out.println(I.getResultats());
                    break;
                case 2:
                    System.out.println(I.getDataCerca());
                    break;
            }
            opcio = sc.nextInt();
        }

    }

}
