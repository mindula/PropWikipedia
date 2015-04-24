package domini;

public class NodePagina extends NodeWiki {
    //falta l'associacio 'té predecessor' .

    public NodePagina(String nom, boolean creat) {
        super(nom, creat);
    }

    public String getUrl(){
        if (getCreat()) System.out.println("El url probablement no existeix ja que es tracta d'una pàgina inventada") ;
        return "www.wikipedia.org/wiki/"+getNom().replaceAll(" ","_");
    }
}
