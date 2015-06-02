package domini.modeldades.graf;

/**
 * Grup 3: Wikipedia
 * User: eduard.casellas
 * Date: 18/04/15
 */

import java.util.ArrayList;

/**
 * Node tipus categoria
 */
public class NodeCategoria extends NodeWiki{


    private ArrayList<NodePagina> pagines = new ArrayList<>();

    public NodeCategoria(String nom) {
        super(nom);
    }

    @Override
    public String toString() {
        String s = getNom() +"(C) amb "+pagines.size()+"p";
        return s;
    }

    public void afegirPagina(NodePagina pag){
        pagines.add(pag);
    }

    public String getUrl(){
        return "www.wikipedia.org/wiki/Category:"+getNom().replaceAll(" ","_");
    }

    public ArrayList<NodePagina> getPagines() {
        return pagines;
    }


}
