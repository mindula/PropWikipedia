package tests;

import graf.NodeCategoria;
import graf.NodePagina;
import domini.Sessio;
import graf.GrafParser;
import graf.GrafWikipedia;

import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Grup 3: Wikipedia
 * Usuari: aleix.paris
 * Data: 24/4/15
 */

public class TestGrafParser {
    public static void main (String[] args) {
        System.out.println("Escriu el numero del test o el path del fitxer:");
        System.out.println("test 0: misc/cats_test.txt");
        System.out.println("test 1: misc/cats_small_test.txt");
        System.out.println("test 2: misc/cats.txt (fitxer molt gran)");
        Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();
        switch(Integer.parseInt(path)){
            case 0:
                path = "misc/cats_test.txt";
                break;
            case 1:
                path = "misc/cats_small_test.txt";
                break;
            case 2:
                path = "misc/cats.txt";
                break;
            default:
        }
        GrafWikipedia g = Sessio.getInstance().getGrafWiki();
        // Test extra:

        NodePagina nodep1 = new NodePagina("TESTP1");
        NodePagina nodep2 = new NodePagina("TESTP2");
        NodeCategoria nodec1 = new NodeCategoria("TESTC1");
        g.afegirNode(nodep1);
        g.afegirNode(nodep2);
        g.afegirNode(nodec1);
        g.afegirArcPC(nodep1, nodec1);
        g.afegirArcPC(nodep2, nodec1);
        g.afegirArcPC(nodep1, nodec1);

        GrafParser grafParser = new GrafParser(g);
        grafParser.parse(Paths.get(path));
        //System.out.println(g);
    }
}
