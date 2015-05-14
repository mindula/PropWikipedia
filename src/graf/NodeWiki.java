package graf;

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

    @Override
    public String toString() {
        return super.toString();
    }

    public abstract String getUrl();

    public String getNom() {
        return nom;
    }

    public NodeWiki(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object obj) {
        return nom.equals(((NodeWiki)obj).nom);
    }
}
