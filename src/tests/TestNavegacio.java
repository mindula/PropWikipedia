package tests;

import domini.controladors.CtrlWikipedia;
import domini.controladors.Navegacio;
import domini.modeldades.graf.GrafWikipedia;

import java.util.Scanner;

/**
 * Grup 3: Wikipedia
 * Usuari: aleix.paris
 * Data: 03/05/2015
 */

public class TestNavegacio {
    public static void main(String[] args){
        GrafWikipedia grafWikipedia = CtrlWikipedia.getInstance().getGrafWiki();
        Navegacio navegacio = new Navegacio(grafWikipedia);
        Scanner sc = new Scanner(System.in);
        int option;
        System.out.println("Escriu:\n" +
                "0 per buscar una pagina\n" +
                "1 per categoria\n" +
                "-1 per sortir:");
        option = sc.nextInt();
        while(option != -1) {
            System.out.println("Escriu el nom:");
            sc.nextLine();
            String nom = sc.nextLine();
            try {
                switch (option) {
                    case 0:
                        // Cercar pag
                        System.out.println("Ja no hi ha funcionalitat");

                        break;
                    case 1:
                        // Cercar cat
                        System.out.println("Ja no hi ha funcionalitat");
                        break;
                }
            }catch(Exception e){
                System.out.println("No existeix aquest node: " + e.getMessage());
            }


            System.out.println("Escriu 0 per buscar una pagina o 1 per categoria. -1 per sortir:");
            option = sc.nextInt();
        }
    }
}
