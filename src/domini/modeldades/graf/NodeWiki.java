package domini.modeldades.graf;

/**
 * Grup 3: Wikipedia
 * User: eduard.casellas
 * Date: 3/04/15
 */

/**
 * Node genèric de la Wikipedia
 */
public abstract class NodeWiki {

    private String nom;

    public NodeWiki(String nom) {
        this.nom = nom;
    }

    public void setNom(String nom) {        //modificar node
        this.nom = nom;
    }

    public abstract String getUrl();

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return nom.equals(((NodeWiki)obj).nom);
    }

    @Override
    public int hashCode() {
        return getNom().hashCode();
    }
}
