package graf;

import domini.AlgorismeLouvain;
import domini.NodeWiki;
import org.grupwiki.graf.Algoritme;
import org.grupwiki.graf.ConjuntComunitats;
import org.grupwiki.graf.Graf;

/**
 * Created by gus on 16/04/15.
 */
public class MainDriver {

    public static void main(String[] args) {

        GrafParser parser = new GrafParser();

        // no se com es crea un Path, seria millor que agafes string crec..
        Graf<NodeWiki> grafWikipedia = null;

        System.out.println("Graf Parsejat:");
        System.out.println(grafWikipedia);






        System.out.println("Graf Transformat:");
        System.out.println(grafWikipedia);


        Algoritme<NodeWiki> algoritme = new AlgorismeLouvain<NodeWiki>();


        ConjuntComunitats<NodeWiki> nodeWikiConjuntComunitats = algoritme.cercarComunitats(grafWikipedia, 3, 1);
        System.out.println(nodeWikiConjuntComunitats);


    }

}
