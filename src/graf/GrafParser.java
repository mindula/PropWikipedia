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
    public GrafParser(){}

    public GrafWikipedia parse(Path path){
        // TODO: quan estigui implementada Sessio.getGraf, no crear un nou graf
        GrafWikipedia grafWikipedia = new GrafWikipedia();
        try{
            List<String> l = Files.readAllLines(path, Charset.defaultCharset()); // canviar la implementacio, fitxers grans
            for(String s: l){
                parseLine(s, grafWikipedia);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return grafWikipedia;
    }

    private void parseLine(String s, GrafWikipedia grafWikipedia){
        String[] parts = s.split("\\t");
        // Tipus d’arc: "CsubC", "CsupC", "CP" i "PC"

        //DEBUG:
        System.out.println(parts[0] + " " + parts[2] + " " + parts[3]);
        if(parts[2].equals("CsubC")){
            NodeCategoria nodeA = new NodeCategoria(parts[0]);
            NodeCategoria nodeB = new NodeCategoria(parts[3]);
            grafWikipedia.afegirNode(nodeA);
            grafWikipedia.afegirNode(nodeB);
            grafWikipedia.afegirArc(new Arc<NodeWiki>(nodeA, nodeB)); // SUBCATEGORIA?
        }
        else if(parts[2].equals("CsupC")){
            NodeCategoria nodeA = new NodeCategoria(parts[0]);
            NodeCategoria nodeB = new NodeCategoria(parts[3]);
            grafWikipedia.afegirNode(nodeA);
            grafWikipedia.afegirNode(nodeB);
            grafWikipedia.afegirArc(new Arc<NodeWiki>(nodeA, nodeB)); // SUPERCATEGORIA?
        }
        else if(parts[2].equals("CP")){
            NodeCategoria nodeA = new NodeCategoria(parts[0]);
            NodePagina nodeB = new NodePagina(parts[3]);
            grafWikipedia.afegirNode(nodeA);
            grafWikipedia.afegirNode(nodeB);
            grafWikipedia.afegirArc(new Arc<NodeWiki>(nodeA, nodeB)); // CAT-PAGINA?
        }
        else{ // "PC"
            NodePagina nodeA = new NodePagina(parts[0]);
            NodeCategoria nodeB = new NodeCategoria(parts[3]);
            grafWikipedia.afegirNode(nodeA);
            grafWikipedia.afegirNode(nodeB);
            grafWikipedia.afegirArc(new Arc<NodeWiki>(nodeA, nodeB)); // PAGINA-CAT?
        }
    }
}
