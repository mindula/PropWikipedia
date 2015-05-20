package graf;

import graf.GrafWikipedia;
import graf.NodeCategoria;
import graf.NodePagina;
import persistencia.CtrlPersistencia;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
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
    private GrafWikipedia grafWikipedia;

    /**
     * Constructora per defecte
     * @param graf
     */
    public GrafParser(GrafWikipedia graf){
        grafWikipedia = graf;
    }

    /**
     * Construeix un graf a partir d'un arxiu de dades localitzat a un path determinat
     * @param path
     * @return el graf de la Wikipedia
     * @throws IOException
     */
    public GrafWikipedia parse(String path) throws IOException {
        ArrayList<String> array = CtrlPersistencia.carregarDades(path);
        for (String s : array) {
            parseLine(s);
        }
        return grafWikipedia;
    }

    private void parseLine(String s){
        String[] parts = s.split("\\t");
        // Tipus d'arc: "CsubC", "CsupC", "CP" i "PC"

        String elemA = parts[0];
        String elemB = parts[3];
        String tipusArc = parts[2];

        if(tipusArc.equals("CP")){
            NodeCategoria nodeA;
            NodePagina nodeB;
            if(!grafWikipedia.existeixNodeCat(elemA)){
                nodeA = new NodeCategoria(elemA);
                grafWikipedia.afegirCategoria(nodeA);
            }
            else nodeA = grafWikipedia.getNodeCat(elemA);
            if(!grafWikipedia.existeixNodePag(elemB)){
                nodeB = new NodePagina(elemB);
                grafWikipedia.afegirPagina(nodeB);
            }
            else nodeB = grafWikipedia.getNodePag(elemB);
            grafWikipedia.afegirArcPC(nodeB, nodeA); // CAT-PAGINA
        }
        else if(tipusArc.equals("PC")){
            NodePagina nodeA;
            NodeCategoria nodeB;
            if(!grafWikipedia.existeixNodePag(elemA)){
                nodeA = new NodePagina(elemA);
                grafWikipedia.afegirPagina(nodeA);
            }
            else nodeA = grafWikipedia.getNodePag(elemA);
            if(!grafWikipedia.existeixNodeCat(elemB)){
                nodeB = new NodeCategoria(elemB);
                grafWikipedia.afegirCategoria(nodeB);
            }
            else nodeB = grafWikipedia.getNodeCat(elemB);
            grafWikipedia.afegirArcPC(nodeA, nodeB); // PAGINA-CAT
        }
        else { // CsubC, CsupC
            NodeCategoria nodeA;
            NodeCategoria nodeB;
            if(!grafWikipedia.existeixNodeCat(elemA)){
                nodeA = new NodeCategoria(elemA);
                grafWikipedia.afegirCategoria(nodeA);
            }
            else nodeA = grafWikipedia.getNodeCat(elemA);
            if(!grafWikipedia.existeixNodeCat(elemB)){
                nodeB = new NodeCategoria(elemB);
                grafWikipedia.afegirCategoria(nodeB);
            }
            else nodeB = grafWikipedia.getNodeCat(elemB);
            if (tipusArc.equals("CsubC")) {
                grafWikipedia.afegirArcCsubC(nodeA, nodeB); // SUBCATEGORIA
            }
            else{ // CsupC
               grafWikipedia.afegirArcCsupC(nodeA, nodeB); // SUPERCATEGORIA
            }
        }
    }
}
