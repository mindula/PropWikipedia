package graf;

/**
 * Grup 3: Wikipedia
 * User: eduard.casellas
 * Date: 18/04/15
 */

import java.util.HashSet;

/**
 * Node tipus pagina
 */

public class NodePagina extends NodeWiki{

    private HashSet<NodeCategoria> pertanyA = new HashSet<>();

    public NodePagina(String nom) {
        super(nom);
    }

    @Override
    public String toString() {
        return getNom() +"(P)";
    }

    public void afegirCategoria(NodeCategoria cat){
        pertanyA.add(cat);
    }


    public String getUrl(){
        return "www.wikipedia.org/wiki/"+getNom().replaceAll(" ","_");
    }

    public HashSet<NodeCategoria> getCategories() {
        return pertanyA;
    }
}
