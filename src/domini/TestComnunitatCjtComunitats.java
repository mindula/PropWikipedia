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
                "3. Finalitzar Test");
        Scanner sc = new Scanner(System.in);
        int opcio = sc.nextInt();
        while(opcio != 3){
            switch(opcio){
                case 1:
                    System.out.println("Escull opcio:" +
                            "1. ");
                    int opcio2 = sc.nextInt();

                    break;
                case 2:

                    break;
            }
        }

    }




}
