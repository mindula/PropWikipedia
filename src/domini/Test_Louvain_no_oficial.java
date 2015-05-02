package domini;

import org.grupwiki.graf.Arc;
import org.grupwiki.graf.ConjuntComunitats;
import org.grupwiki.graf.Graf;

public class Test_Louvain_no_oficial {
    public static void main(String args[]){
        Graf<Character> graf = new Graf<Character>();
        graf.afegirNode('a');
        graf.afegirNode('b');
        graf.afegirNode('c');
        graf.afegirNode('d');
        graf.afegirNode('e');

        graf.afegirArc(new Arc<Character>(30, 'a', 'b'));
        //graf.afegirArc(new Arc<Character>(1, 'b', 'e'));
        graf.afegirArc(new Arc<Character>(30, 'e', 'c'));
        graf.afegirArc(new Arc<Character>(30, 'e', 'd'));
        graf.afegirArc(new Arc<Character>(30, 'c', 'd'));

        AlgorismeLouvain<Character> algorismeLouvain = new AlgorismeLouvain<Character>();
        ConjuntComunitats<Character> resultat = algorismeLouvain.cercarComunitats(graf, 3, 0);
        System.out.println(resultat.toString());
    }
}
