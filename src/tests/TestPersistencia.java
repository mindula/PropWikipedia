package tests;

import persistencia.CtrlPersistencia;

import java.io.IOException;
import java.util.Scanner;

/**
 * Grup 3: Wikipedia
 * Usuari: agusti.bau
 * Data: 27/05/15
 */
public class TestPersistencia {

    public static void main(String[] args) {
        System.out.println("Escull una opcio:");
        System.out.println("1. Carregar Sessio");
        System.out.println("2. Guardar Sessio");
        System.out.println("3. Sortir");

        boolean sortir = false;
        Scanner sc = new Scanner(System.in);
        String fileName;

        while(!sortir){
            int opcio = sc.nextInt();
            switch (opcio){
                case 1:
                    System.out.println("Escriu el nom del fitxer a carregar:");
                    fileName = sc.next();

                    try {
                        CtrlPersistencia.carregarSessio(fileName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("Escriu el nom del fitxer per guardar:");
                    fileName = sc.next();


                    try {
                        CtrlPersistencia.guardarSessio(fileName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    sortir = true;
                    break;
                default:
                    break;
            }
        }
    }
}
