package tests;

import domini.Sessio;

import java.awt.peer.SystemTrayPeer;
import java.util.Scanner;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard.gascons
 * Data: 5/3/15
 */

public class TestSessio {
    public static void main(String[] args){
        Sessio s = Sessio.getInstance();
        System.out.println("Escriu l'opcio que vols realitzar:");
        System.out.println("1. Veure data de creacio");
        System.out.println("2. Acabar test");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        while (option != 2) {
            if (option == 1) {
                System.out.println(s.getDataCreacio());
            }
            option = sc.nextInt();
        }
    }
}
