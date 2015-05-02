package graf;

import domini.NodeCategoria;
import domini.NodePagina;
import domini.NodeWiki;
import org.grupwiki.graf.Arc;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Grup 3: Wikipedia
 * Usuari: aleix.paris
 * Data: 23/4/15
 */

public class GrafParser {
    private GrafWikipedia grafWikipedia;

    public GrafParser(GrafWikipedia graf){
        grafWikipedia = graf;
    }

    public GrafWikipedia parse(Path path){
        try{
            List<String> l = Files.readAllLines(path, Charset.defaultCharset()); // canviar la implementacio, fitxers grans
            for(String s: l){
                parseLine(s);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
            //e.getCause();
            //e.getMessage();
        }
        return grafWikipedia;
    }

    private void parseLine(String s){
        String[] parts = s.split("\\t");
        // Tipus d’arc: "CsubC", "CsupC", "CP" i "PC"

        //System.out.println(parts[0] + " " + parts[2] + " " + parts[3])
        String elemA = parts[0];
        String elemB = parts[3];
        String tipusArc = parts[2];

        if(tipusArc.equals("CP")){
            NodeCategoria nodeA;
            NodePagina nodeB;
            if(!grafWikipedia.existeixNodeCat(elemA)){
                nodeA = new NodeCategoria(elemA);
                grafWikipedia.afegirNode(nodeA);
            }
            else nodeA = grafWikipedia.getNodeCat(elemA);
            if(!grafWikipedia.existeixNodePag(elemB)){
                nodeB = new NodePagina(elemB);
                grafWikipedia.afegirNode(nodeB);
            }
            else nodeB = grafWikipedia.getNodePag(elemB);
            grafWikipedia.afegirArcPC(nodeB, nodeA); // CAT-PAGINA
        }
        else if(tipusArc.equals("PC")){
            NodePagina nodeA;
            NodeCategoria nodeB;
            if(!grafWikipedia.existeixNodePag(elemA)){
                nodeA = new NodePagina(elemA);
                grafWikipedia.afegirNode(nodeA);
            }
            else nodeA = grafWikipedia.getNodePag(elemA);
            if(!grafWikipedia.existeixNodeCat(elemB)){
                nodeB = new NodeCategoria(elemB);
                grafWikipedia.afegirNode(nodeB);
            }
            else nodeB = grafWikipedia.getNodeCat(elemB);
            grafWikipedia.afegirArcPC(nodeA, nodeB); // PAGINA-CAT
        }
        else { // CsubC, CsupC
            NodeCategoria nodeA;
            NodeCategoria nodeB;
            if(!grafWikipedia.existeixNodeCat(elemA)){
                nodeA = new NodeCategoria(elemA);
                grafWikipedia.afegirNode(nodeA);
            }
            else nodeA = grafWikipedia.getNodeCat(elemA);
            if(!grafWikipedia.existeixNodeCat(elemB)){
                nodeB = new NodeCategoria(elemB);
                grafWikipedia.afegirNode(nodeB);
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
