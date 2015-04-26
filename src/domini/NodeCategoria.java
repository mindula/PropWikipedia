package domini;

/**
 * Grup 3: Wikipedia
 * User: eduard.casellas
 * Date: 18/04/15
 */

public class NodeCategoria extends NodeWiki {


    public NodeCategoria(String nom, boolean creat) {
        super(nom, creat);
    }

    public String getUrl(){
        if (getCreat())   System.out.println("El url probablement no existeix ja que es tracta d'una categoria inventada");
        return "www.wikipedia.org/wiki/Category:"+getNom().replaceAll(" ","_");
    }

}
