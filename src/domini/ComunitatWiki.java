package domini;

import graf.NodeWiki;
import prop.classescompartides.graf.Comunitat;

import java.util.HashSet;

/**
 * Grup 3: Wikipedia
 * User: eduard.casellas
 * Date: 24/04/15
 */

/**
 * Comunitat de nodes de la wikipedia. Aquesta classe exten una part de les seves funcions de Comunitat
 */

public class ComunitatWiki extends Comunitat<NodeWiki> {
    public String nom;
    private String descripcio;

    /**
     * Constructor per defecte, a partir d'un identificador <tt>id</tt> inicialitza les estructures internes
     */
    public ComunitatWiki() {
        super();
    }

    public ComunitatWiki(int id) {
        super(id);
    }

    /**
     * Retorna el nom de la comunitat
     * @return el nom de la comunitat
     */
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

    /**
     * Retorna la descripcio de la comunitat
     * @return la descripcio de la comunitat
     */
    public String getDescripcio() {
        return descripcio;
    }

    /**
     * Retorna cert si existeix un node amb un nom <tt>nom</tt>
     * @param nom
     * @return cert si existeix un node amb un nom <tt>nom</tt>
     */
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
