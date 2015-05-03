package domini;

import graf.NodeWiki;
import org.grupwiki.graf.Comunitat;

/**
 * Grup 3: Wikipedia
 * User: eduard.casellas
 * Date: 24/04/15
 */


public class ComunitatWiki extends Comunitat<NodeWiki> {
    private String nom;
    private String descripcio;

    /**
     * Constructor per defecte, a partir d'un identificador <tt>id</tt> inicialitza les estructures internes
     *
     */
    public ComunitatWiki() {
        super();
    }



    public String getNom() {
        return nom;
    }

    /**
     * Edita el nom de la comunitat, Cas d'ús Modificar tema
     * @param s
     */
    public void setNom(String s){
        nom = s;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public boolean teNode(String nom) {
        for (NodeWiki n : nodes) {
            if (nom.equals(n.getNom())) return true;
        }
        return false;
    }

    /**
     * Edita la descripció de la comunitat, Cas d'ús Modificar Tema
     * @param s
     */
    public void setDescripcio(String s){
        descripcio = s;
    }



}
