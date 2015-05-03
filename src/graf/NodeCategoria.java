package graf;

/**
 * Grup 3: Wikipedia
 * User: eduard.casellas
 * Date: 18/04/15
 */

public class NodeCategoria extends NodeWiki {


    public NodeCategoria(String nom) {
        super(nom, true);
    }

    public String getUrl(){
        return "www.wikipedia.org/wiki/Category:"+getNom().replaceAll(" ","_");
    }

    @Override
    public String toString() {
        return this.getNom() + "(C)";
    }

}
