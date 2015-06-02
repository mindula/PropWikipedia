package tests;

import domini.modeldades.InformacioCjtComunitats;
import domini.modeldades.TipusAlgorisme;
import domini.modeldades.graf.NodeCategoria;

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
        System.out.println("Inicialitza la informacio: tempsgenerar(long), milisegons(long), numero de comunitats(int), Algoritme(string)," +
                "Criteri(string), i Mitjana de nombres per comunitat(long)");
        long gen = sc.nextInt();
        long mil = sc.nextLong();
        int nCom = sc.nextInt();
        String alg = sc.next();
        TipusAlgorisme algorisme;
        if(alg.equals("Louvain"))
            algorisme = TipusAlgorisme.LOUVAIN;
        else if(alg.equals("Girvan"))
            algorisme = TipusAlgorisme.GIRVAN;
        else algorisme = TipusAlgorisme.CLIQUE;
        String cri = sc.next();
        long mitj = sc.nextLong();
        I = new InformacioCjtComunitats(gen,algorisme,cri);
        I.setMitjanaNodesPerComunitat(mitj);
        I.setNombreComunitats(nCom);
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
                            "2. Obtenir el nombre de comunitat\n" +
                            "3. Obtenir el algoritme\n" +
                            "4. Obtenri el criteri\n" +
                            "5. Obtenir la mitjana de nodes per comunitat\n" +
                            "6. Finalitzar Test\n");
                    break;
                case 1:
                    System.out.println(I.getTempsComunitats());
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