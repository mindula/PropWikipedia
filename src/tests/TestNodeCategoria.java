package tests;

import graf.NodeCategoria;

import java.util.Scanner;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 03/05/2015.
 */
public class TestNodeCategoria {
    public static void main(String[] args) {
        System.out.println("Escull una opcio:\n" +
                "1. Obtenir el nom\n" +
                "2. Canviar el nom\n" +
                "3. Obtenir si ha estat creat manualment\n" +
                "4. Definir si ha estat creat manualment\n" +
                "5. Obtenir el URL\n" +
                "6. Obtenir si es categoria\n" +
                "7. Finalitzar Test");
        Scanner sc = new Scanner(System.in);
        int opcio = sc.nextInt();
        NodeCategoria n = new NodeCategoria("nodecreat");
        String paraula;
        boolean b;
        while (opcio != 7) {
            switch (opcio) {
                case 1:
                    System.out.println(n.getNom());
                    break;
                case 2:
                    System.out.println("Escriu el nou nom");
                    paraula = sc.next();
                    n.setNom(paraula);
                    break;
                case 3:
                    System.out.println(n.getCreat());
                    break;
                case 4:
                    System.out.println("Escriu true o false");
                    b = sc.nextBoolean();
                    n.setCreat(b);
                    break;
                case 5:
                    System.out.println(n.getUrl());
                    break;
                case 6:
                    System.out.println(n.esCategoria());
                    break;
            }
            opcio = sc.nextInt();
        }
    }
}