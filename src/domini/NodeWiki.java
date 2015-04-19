package domini;


public abstract class NodeWiki {
    //////
    private String nom;
    private boolean creat;

    public NodeWiki(String nom, boolean creat){
        this.nom = nom;
        this.creat = creat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {        //modificar node
        this.nom = nom;
    }

    public boolean getCreat() {
        return creat;
    }

    abstract public String getUrl();




}