package graf;

/**
 * Grup 3: Wikipedia
 * User: eduard.casellas
 * Date: 18/04/15
 */

/**
 * Node tipus pagina
 */

public class NodePagina extends NodeWiki {

    public NodePagina(String nom) {
        super(nom, false);
    }

    public String getUrl(){
       return "www.wikipedia.org/wiki/"+getNom().replaceAll(" ","_");
    }

    @Override
    public String toString() {
        return this.getNom() + "(P)";
    }


}
