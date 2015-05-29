package persistencia;

import domini.modeldades.graf.GrafWikipedia;
import domini.modeldades.graf.NodeCategoria;
import domini.modeldades.graf.NodePagina;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Grup 3: Wikipedia
 * Usuari: aleix.paris
 * Data: 23/4/15
 */

/**
 * A partir d'un input en un fitxer de dades, forma el graf de la Wikipedia
 */
public class GrafParser {

    private GrafParser(){}

    /**
     * Construeix un graf a partir d'un arxiu de dades localitzat a un path determinat
     * @param path
     * @return el graf de la Wikipedia
     * @throws IOException
     */
    public static void parse(String path, GrafWikipedia grafWikipedia) throws IOException{
        FileInputStream inputStream;
        Scanner sc;
        inputStream = new FileInputStream(path);
        sc = new Scanner(inputStream);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            parseLine(s, grafWikipedia);
        }
        /*
        List<String> l = Files.readAllLines(path, Charset.defaultCharset());
        for(String s: l)
            parseLine(s);
        */
        inputStream.close();
        sc.close();
    }

    private static void parseLine(String s, GrafWikipedia grafWiki){
        String[] parts = s.split("\\t");
        // Tipus d'arc: "CsubC", "CsupC", "CP" i "PC"

        String elemA = parts[0];
        String elemB = parts[3];
        String tipusArc = parts[2];

        if(tipusArc.equals("CP")){
            NodeCategoria nodeA;
            NodePagina nodeB;
            if(!grafWiki.existeixNodeCat(elemA)){
                nodeA = new NodeCategoria(elemA);
                grafWiki.afegirCategoria(nodeA);
            }
            else nodeA = grafWiki.getNodeCat(elemA);
            if(!grafWiki.existeixNodePag(elemB)){
                nodeB = new NodePagina(elemB);
                grafWiki.afegirPagina(nodeB);
            }
            else nodeB = grafWiki.getNodePag(elemB);
            grafWiki.afegirArcPC(nodeB, nodeA); // CAT-PAGINA
        }
        else if(tipusArc.equals("PC")){
            NodePagina nodeA;
            NodeCategoria nodeB;
            if(!grafWiki.existeixNodePag(elemA)){
                nodeA = new NodePagina(elemA);
                grafWiki.afegirPagina(nodeA);
            }
            else nodeA = grafWiki.getNodePag(elemA);
            if(!grafWiki.existeixNodeCat(elemB)){
                nodeB = new NodeCategoria(elemB);
                grafWiki.afegirCategoria(nodeB);
            }
            else nodeB = grafWiki.getNodeCat(elemB);
            grafWiki.afegirArcPC(nodeA, nodeB); // PAGINA-CAT
        }
        else { // CsubC, CsupC
            NodeCategoria nodeA;
            NodeCategoria nodeB;
            if(!grafWiki.existeixNodeCat(elemA)){
                nodeA = new NodeCategoria(elemA);
                grafWiki.afegirCategoria(nodeA);
            }
            else nodeA = grafWiki.getNodeCat(elemA);
            if(!grafWiki.existeixNodeCat(elemB)){
                nodeB = new NodeCategoria(elemB);
                grafWiki.afegirCategoria(nodeB);
            }
            else nodeB = grafWiki.getNodeCat(elemB);
            if (tipusArc.equals("CsubC")) {
                grafWiki.afegirArcCsubC(nodeA, nodeB); // SUBCATEGORIA
            }
            else{ // CsupC
               grafWiki.afegirArcCsupC(nodeA, nodeB); // SUPERCATEGORIA
            }
        }
    }
}
