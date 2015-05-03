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
    private boolean creat;
    private boolean isCategoria;

    //private String color;

    public NodeWiki(String nom, boolean isCategoria){
        this.nom = nom;
        this.creat = false;
        this.isCategoria = isCategoria;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {        //modificar node
        this.nom = nom;
    }

    public void setCreat(boolean creat) {
        this.creat = creat;
    }

    public boolean getCreat() {
        return creat;
    }

    abstract public String getUrl();

    /**
     * Retorna cert si el node en questio es una categoria
     * @return cert si el node en questio es una categoria
     */
    public boolean esCategoria() {
        return isCategoria;
    }

    @Override
    public String toString() {
        return nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeWiki nodeWiki = (NodeWiki) o;

        if (!nom.equals(nodeWiki.nom)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return nom.hashCode();
    }


}