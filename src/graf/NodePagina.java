package graf;

/**
 * Grup 3: Wikipedia
 * User: eduard.casellas
 * Date: 18/04/15
 */

public class NodePagina extends NodeWiki {

    public NodePagina(String nom) {
        super(nom, false);
    }

    public String getUrl(){
       return "www.wikipedia.org/wiki/"+getNom().replaceAll(" ","_");
    }


}
