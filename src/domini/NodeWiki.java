package domini;

/**
 * Grup 3: Wikipedia
 * User: eduard.casellas
 * Date: 3/04/15
 */


public abstract class NodeWiki {

    private String nom;
    /**
     * Creat: creat manualment (segueixo sense trobarli el sentit)
     */
    private boolean creat;

    /*
     * isCreat = True si el node és una Categoria, si és False és una pàgina
     *
    private boolean isCategoria; */
    //private String color;  //aixo anira en una extensio. crec yo vaya

    public NodeWiki(String nom, boolean creat){
        this.nom = nom;
        this.creat = creat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {        //modificar node
        this.nom = nom;
    }  //Modificar nom node

    public boolean getCreat() {
        return creat;
    }

    abstract public String getUrl();

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