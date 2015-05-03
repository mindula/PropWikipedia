package tests;

import domini.InformacioCjtComunitats;
import graf.NodeCategoria;

import java.util.Scanner;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 03/05/2015
 */
public class TestInformacioCjtComunitats {

    public static void main(String[] args) {
        InformacioCjtComunitats I;
        Scanner sc = new Scanner(System.in);
        System.out.println("Inicialitza la informacio: milisegons(long), numero de comunitats(int), Algoritme(string)," +
                "Criteri(string), i Mitjana de nombres per comunitat(long)");
        long mil = sc.nextLong();
        int nCom = sc.nextInt();
        String alg = sc.next();
        String cri = sc.next();
        long mitj = sc.nextLong();
        I = new InformacioCjtComunitats(mil,nCom,alg,cri,mitj);
        System.out.println("Escull una opcio:\n" +
                "0. Veure opcions\n" +
                "1. Obtenir el temps\n" +
                "2. Obtenir el nombre de comunitat\n" +
                "3. Obtenir el algoritme\n" +
                "4. Obtenri el criteri\n" +
                "5. Obtenir la mitjana de nodes per comunitat\n" +
                "6. Finalitzar Test\n");

        int opcio = sc.nextInt();
        NodeCategoria n = new NodeCategoria("nodecreat");
        String paraula;
        boolean b;
        while (opcio != 6) {
            switch (opcio) {
                case 0:
                    System.out.println("Escull una opcio:\n" +
                            "0. Veure opcions\n" +
                            "1. Obtenir el temps\n" +
                            "2. Obtenir el nombre de comunitat\ns" +
                            "3. Obtenir el algoritme\n" +
                            "4. Obtenri el criteri\n" +
                            "5. Obtenir la mitjana de nodes per comunitat\n" +
                            "6. Finalitzar Test\n");
                case 1:
                    System.out.println(I.getMilisegons());
                    break;
                case 2:
                    System.out.println(I.getNombreComunitats());
                    break;
                case 3:
                    System.out.println(I.getAlgoritme());
                    break;
                case 4:
                    System.out.println(I.getCriteri());
                    break;
                case 5:
                    System.out.println(I.getMitjanaNodesPerComunitat());
                    break;
            }
            opcio = sc.nextInt();
        }
    }
}