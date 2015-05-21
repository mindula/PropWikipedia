package tests;

import domini.Cerca;
import domini.Controladors.CtrlDomini;
import domini.Navegacio;
import graf.GrafWikipedia;
import graf.NodeCategoria;
import graf.NodePagina;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Grup 3: Wikipedia
 * Usuari: aleix.paris
 * Data: 03/05/2015
 */

public class TestNavegacio {
    public static void main(String[] args){
        GrafWikipedia grafWikipedia = CtrlDomini.getInstance().getGrafWiki();
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
                        //TODO: possible fail
                        ArrayList<NodePagina> nodesPagina = (ArrayList<NodePagina>)
                                Cerca.cercarWikipediaP(grafWikipedia, nom).getResultats();
                        for (NodePagina np : nodesPagina) {
                            System.out.println("Pagina: " + np.getNom());
                            /*// Mostrar categories
                            HashSet<NodeCategoria> categories = navegacio.getCategories(np);
                            System.out.println("Aquesta pagina te les categories seguents:");
                            for (NodeCategoria categoria : categories)
                                System.out.println(categoria.getNom());*/
                        }

                        break;
                    case 1:
                        // Cercar cat
                        ArrayList<NodeCategoria> nodesCategoria = (ArrayList<NodeCategoria>)
                                Cerca.cercarWikipediaC(grafWikipedia, nom).getResultats();
                        for (NodeCategoria nodeCategoria : nodesCategoria) {
                            System.out.println("Categoria: " + nodeCategoria.getNom());
                            /*// Mostrar pagines
                            ArrayList<NodePagina> pagines = navegacio.getPagines(nodeCategoria);
                            System.out.println("Aquesta categoria te les pagines seguents:");
                            for (NodePagina pagina : pagines)
                                System.out.println(pagina.getNom());
                            //Mostrar supercategories
                            HashSet<NodeCategoria> supercategories = navegacio.getSupercategories(nodeCategoria);
                            System.out.println("Aquesta categoria te les supercategories seguents:");
                            for (NodeCategoria supercategoria : supercategories)
                                System.out.println(supercategoria.getNom());
                            //Mostrar subcategories
                            HashSet<NodeCategoria> subcategories = navegacio.getSubcategories(nodeCategoria);
                            System.out.println("Aquesta categoria te les subcategories seguents:");
                            for (NodeCategoria subcategoria : subcategories)
                                System.out.println(subcategoria.getNom());*/
                        }
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
