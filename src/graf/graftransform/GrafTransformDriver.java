package graf.graftransform;

import domini.NodePagina;
import domini.NodeWiki;
import graf.GrafWikipedia;
import org.grupwiki.graf.Arc;
import org.grupwiki.graf.Graf;

/**
 * Created by gus on 26/04/15.
 */
public class GrafTransformDriver  {
    public static void main(String[] args) {
        //Graf<NodeWiki> graf = new GrafWikipedia();
        /* *Dona error i m'impedeix executar el driver, ho comento*
        NodePagina n1 = new NodePagina("Agus", true);
        NodePagina n2 = new NodePagina("Agusti", true);
        NodePagina n3 = new NodePagina("Babababa", true);
        NodePagina n4 = new NodePagina("Cacaca", true);
        NodePagina n5 = new NodePagina("Caca", true);
        NodePagina n6 = new NodePagina("Sheeet", true);

        graf.afegirNode(n1);
        graf.afegirNode(n2);
        graf.afegirNode(n3);
        graf.afegirNode(n4);
        graf.afegirNode(n5);
        graf.afegirNode(n6);

        graf.afegirArc(new Arc<NodeWiki>(n1, n2) );


        System.out.println("BEFORE:");
        System.out.println(graf);


        GrafTransform transform = new PlainTransform(new NomTransform());
        graf = transform.transform(graf);

        System.out.println("AFTER:");
        System.out.print(graf);

*/

    }
}
