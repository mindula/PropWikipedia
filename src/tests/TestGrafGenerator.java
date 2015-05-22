package tests;

import domini.controladors.CtrlWikipedia;
import domini.modeldades.graf.GrafWikipedia;
import domini.modeldades.graf.NodeCategoria;
import domini.controladors.graf.grafgenerator.Criteris.*;
import domini.controladors.graf.grafgenerator.GrafGenerator;
import prop.classescompartides.graf.Graf;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * Grup 3: Wikipedia
 * Usuari: agusti.bau
 * Data: 02/05/15
 *
 */
public class TestGrafGenerator {
    public static void main(String[] args) {




        Scanner sc = new Scanner(System.in).useLocale(Locale.US);
        ArrayList<Criteri> criteris = getCriteris(sc);



        GrafWikipedia g = CtrlWikipedia.getInstance().getGrafWiki();

        GrafGenerator generator = new GrafGenerator();
        Graf<NodeCategoria> grafAlgoritme = generator.generate(g, criteris);
        CtrlWikipedia.getInstance().setGrafAlgoritme(grafAlgoritme);

        System.out.println("Vols mostrar el graf generat? 1 - si/2 - no");
        int mostrar = sc.nextInt();
        if( mostrar == 1 )
            System.out.println(grafAlgoritme);


    }

    public static ArrayList<Criteri> getCriteris(Scanner sc) {
        System.out.println("Escriu el numero del criteri a afegir, seguit de la seva ponderacio i -1 per executar la tranformacio del graf:\n" +
                "(prem -1 per acabar el test)");
        System.out.println("0: Criteri Nom");
        System.out.println("1: Criteri supercategories comunes");
        System.out.println("2: Criteri subcategories comunes");
        System.out.println("3: Criteri pagines comunes");
        System.out.println("\nper exemple (1 0.8) -> afegir criteri subcategories amb ponderacio 0.8");


        ArrayList<Criteri> criteris = new ArrayList<>() ;
        boolean continuar = true;
        while(continuar) {
            int opcio = sc.nextInt();
            double ponderacio = 0;

            if(opcio != -1)
                ponderacio= sc.nextDouble();

            Criteri c = null;
            switch (opcio) {
                case 0:
                    c= new CriteriNom(ponderacio);
                    break;

                case 1:
                    c = new CriteriSuperCategoriesComuns(ponderacio);
                    break;

                case 2:
                    c = new CriteriSubCategoriesComuns(ponderacio);
                    break;

                case 3:
                    c = new CriteriPaginesComuns(ponderacio);
                    break;

                case -1:
                    continuar = false;
                    break;

                default:
                    System.out.println("No es una opcio correcta");
                    continue;
            }
            if(continuar) {
                criteris.add(c);

            }
        }
        System.out.println(criteris.size()+ " Criteris afegits");
        return criteris;
    }
}
