package tests;

import domini.AlgorismeLouvain;
import org.grupwiki.graf.Arc;
import org.grupwiki.graf.ConjuntComunitats;
import org.grupwiki.graf.Graf;

import java.util.Scanner;

/**
 * Grup 3: Wikipedia
 * Usuari: aleix.paris
 * Data: 02/05/2015
 */

public class TestAlgorismeLouvain{
    public static void main(String args[]){
        Graf<Character> graf = new Graf<Character>();
        System.out.println("Escriu el numero del test:");
        System.out.println("test 0: ");
        System.out.println("test 1: 6 nodes, agrupats de 3 en 3 (2 comunitats)");
        System.out.println("test 2: pdf Louvain");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();

        switch(option) {
            case 0:
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
                break;
            case 1:
                graf.afegirNode('a');
                graf.afegirNode('b');
                graf.afegirNode('c');
                graf.afegirNode('d');
                graf.afegirNode('e');
                graf.afegirNode('f');

                graf.afegirArc(new Arc<Character>(40, 'a', 'b'));
                graf.afegirArc(new Arc<Character>(5, 'a', 'c'));
                graf.afegirArc(new Arc<Character>(10, 'b', 'c'));

                graf.afegirArc(new Arc<Character>(1, 'c', 'e'));

                graf.afegirArc(new Arc<Character>(3, 'e', 'd'));
                graf.afegirArc(new Arc<Character>(50, 'e', 'f'));
                graf.afegirArc(new Arc<Character>(100, 'f', 'd'));
                break;
            case 2:

                break;
            default:
        }


        AlgorismeLouvain<Character> algorismeLouvain = new AlgorismeLouvain<Character>();
        ConjuntComunitats<Character> resultat = algorismeLouvain.cercarComunitats(graf, 10, 0);
        System.out.println(resultat.toString());
    }
}
