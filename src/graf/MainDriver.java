package graf;

import domini.Sessio;

/**
 * Created by gus on 16/04/15.
 */
public class MainDriver {

    public static void main(String[] args) {

        // Aleix: al parser li has de passar el graf de la sessio
        GrafParser parser = new GrafParser(Sessio.getInstance().getGrafWiki());

        // no se com es crea un Path, seria millor que agafes string crec..

        // Aleix: solucionat, es fa amb Paths.get(String path);

        /* Peta, comentat per fer tests

        GrafWikipedia grafWikipedia = parser.parse(Paths.get("misc/cats_test"));


        System.out.println("Graf Parsejat:");
        System.out.println(grafWikipedia);






        System.out.println("Graf Transformat:");
        System.out.println(grafWikipedia);


        Algoritme<NodeWiki> algoritme = new AlgorismeLouvain<NodeWiki>();


        ConjuntComunitats<NodeWiki> nodeWikiConjuntComunitats = algoritme.cercarComunitats(grafWikipedia, 3, 1);
        System.out.println(nodeWikiConjuntComunitats);
       */

    }

}
