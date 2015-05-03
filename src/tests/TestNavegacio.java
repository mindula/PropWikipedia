package tests;

import domini.Cerca;
import domini.Navegacio;
import domini.Sessio;
import graf.NodeCategoria;
import graf.NodePagina;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Grup 3: Wikipedia
 * Usuari: aleix.paris
 * Data: 03/05/2015
 */

public class TestNavegacio {
    public static void main(String[] args){
        Navegacio navegacio = new Navegacio(Sessio.getInstance().getGrafWiki());
        Scanner sc = new Scanner(System.in);
        int option;
        System.out.println("Escriu 0 per buscar una pagina o 1 per categoria. -1 per sortir:");
        option = sc.nextInt();
        while(option != -1) {
            System.out.println("Escriu el nom:");
            String nom = sc.nextLine();
            switch (option) {
                case 0:
                    // Cercar pag
                    NodePagina nodePagina = Cerca.cercaPag;
                    System.out.println("Pagina: " + nodePagina);
                    // Mostrar categories
                    HashSet<NodeCategoria> categories = navegacio.getCategories(nodePagina);
                    System.out.println("Aquesta pagina te les categories seguents:");
                    for (NodeCategoria categoria : categories)
                        System.out.println(categoria);
                    break;
                case 1:
                    // Cercar cat
                    NodeCategoria nodeCategoria = Cerca.cercaCat;
                    System.out.println("Categoria: " + nodeCategoria);
                    //Mostrar supercategories
                    HashSet<NodeCategoria> supercategories = navegacio.getSupercategories(nodeCategoria);
                    System.out.println("Aquesta categoria te les supercategories seguents:");
                    for (NodeCategoria supercategoria : supercategories)
                        System.out.println(supercategoria);
                    //Mostrar subcategories
                    HashSet<NodeCategoria> subcategories = navegacio.getSubcategories(nodeCategoria);
                    System.out.println("Aquesta categoria te les subcategories seguents:");
                    for (NodeCategoria subcategoria : subcategories)
                        System.out.println(subcategoria);
                    break;
            }
            System.out.println("Escriu 0 per buscar una pagina o 1 per categoria. -1 per sortir:");
            option = sc.nextInt();
        }
    }
}
