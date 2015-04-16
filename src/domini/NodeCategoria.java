package domini;

public class NodeCategoria extends NodeWiki {


    public NodeCategoria(String nom, boolean creat) {
        super(nom, creat);
    }

    public String getUrl(){
        if (getCreat())   System.out.println("El url probablement no existeix ja que es tracta d'una categoria inventada");
        return "www.wikipedia.org/wiki/Category:"+getNom().replaceAll(" ","_");
    }

}
