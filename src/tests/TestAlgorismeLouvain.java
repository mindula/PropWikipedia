package tests;

import prop.classescompartides.algorismes.AlgorismeLouvain;
import prop.classescompartides.graf.Arc;
import prop.classescompartides.graf.Comunitat;
import prop.classescompartides.graf.ConjuntComunitats;
import prop.classescompartides.graf.Graf;

import java.util.ArrayList;
import java.util.HashSet;
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
        System.out.println("Test 0: 5 nodes");
        System.out.println("Test 1: 6 nodes");
        System.out.println("Test 2: Joc de proves de 'Fast unfolding of communities in large networks'");
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
                graf.afegirNode('0');
                graf.afegirNode('1');
                graf.afegirNode('2');
                graf.afegirNode('3');
                graf.afegirNode('4');
                graf.afegirNode('5');
                graf.afegirNode('6');
                graf.afegirNode('7');
                graf.afegirNode('8');
                graf.afegirNode('9');
                graf.afegirNode('A');
                graf.afegirNode('B');
                graf.afegirNode('C');
                graf.afegirNode('D');
                graf.afegirNode('E');
                graf.afegirNode('F');

                graf.afegirArc(new Arc<Character>(1, '0', '2'));
                graf.afegirArc(new Arc<Character>(1, '0', '3'));
                graf.afegirArc(new Arc<Character>(1, '0', '4'));
                graf.afegirArc(new Arc<Character>(1, '0', '5'));
                graf.afegirArc(new Arc<Character>(1, '1', '2'));
                graf.afegirArc(new Arc<Character>(1, '1', '4'));
                graf.afegirArc(new Arc<Character>(1, '1', '7'));
                graf.afegirArc(new Arc<Character>(1, '2', '0'));
                graf.afegirArc(new Arc<Character>(1, '2', '1'));
                graf.afegirArc(new Arc<Character>(1, '2', '4'));
                graf.afegirArc(new Arc<Character>(1, '2', '5'));
                graf.afegirArc(new Arc<Character>(1, '2', '6'));
                graf.afegirArc(new Arc<Character>(1, '3', '0'));
                graf.afegirArc(new Arc<Character>(1, '3', '7'));
                graf.afegirArc(new Arc<Character>(1, '4', '0'));
                graf.afegirArc(new Arc<Character>(1, '4', '1'));
                graf.afegirArc(new Arc<Character>(1, '4', '2'));
                graf.afegirArc(new Arc<Character>(1, '4', 'A'));
                graf.afegirArc(new Arc<Character>(1, '5', '0'));
                graf.afegirArc(new Arc<Character>(1, '5', '2'));
                graf.afegirArc(new Arc<Character>(1, '5', '7'));
                graf.afegirArc(new Arc<Character>(1, '5', 'B'));
                graf.afegirArc(new Arc<Character>(1, '6', '2'));
                graf.afegirArc(new Arc<Character>(1, '6', '7'));
                graf.afegirArc(new Arc<Character>(1, '6', 'B'));
                graf.afegirArc(new Arc<Character>(1, '7', '1'));
                graf.afegirArc(new Arc<Character>(1, '7', '3'));
                graf.afegirArc(new Arc<Character>(1, '7', '5'));
                graf.afegirArc(new Arc<Character>(1, '7', '6'));
                graf.afegirArc(new Arc<Character>(1, '8', '9'));
                graf.afegirArc(new Arc<Character>(1, '8', 'A'));
                graf.afegirArc(new Arc<Character>(1, '8', 'B'));
                graf.afegirArc(new Arc<Character>(1, '8', 'E'));
                graf.afegirArc(new Arc<Character>(1, '8', 'F'));
                graf.afegirArc(new Arc<Character>(1, '9', '8'));
                graf.afegirArc(new Arc<Character>(1, '9', 'C'));
                graf.afegirArc(new Arc<Character>(1, '9', 'E'));
                graf.afegirArc(new Arc<Character>(1, 'A', 'C'));
                graf.afegirArc(new Arc<Character>(1, 'A', 'E'));
                graf.afegirArc(new Arc<Character>(1, 'A', '4'));
                graf.afegirArc(new Arc<Character>(1, 'A', '8'));
                graf.afegirArc(new Arc<Character>(1, 'A', 'B'));
                graf.afegirArc(new Arc<Character>(1, 'A', 'D'));
                graf.afegirArc(new Arc<Character>(1, 'B', '6'));
                graf.afegirArc(new Arc<Character>(1, 'B', '5'));
                graf.afegirArc(new Arc<Character>(1, 'B', '8'));
                graf.afegirArc(new Arc<Character>(1, 'B', 'A'));
                graf.afegirArc(new Arc<Character>(1, 'B', 'D'));
                graf.afegirArc(new Arc<Character>(1, 'C', '9'));
                graf.afegirArc(new Arc<Character>(1, 'C', 'A'));
                graf.afegirArc(new Arc<Character>(1, 'D', 'A'));
                graf.afegirArc(new Arc<Character>(1, 'D', 'B'));
                graf.afegirArc(new Arc<Character>(1, 'E', '8'));
                graf.afegirArc(new Arc<Character>(1, 'E', '9'));
                graf.afegirArc(new Arc<Character>(1, 'E', 'D'));
                graf.afegirArc(new Arc<Character>(1, 'F', '8'));
                break;
            default:
        }
        AlgorismeLouvain<Character> algorismeLouvain = new AlgorismeLouvain<Character>();
        ConjuntComunitats<Character> resultat = algorismeLouvain.cercarComunitats(graf, 10, 2);
        imprimirComunitats(resultat);
    }

    private static void imprimirComunitats(ConjuntComunitats<Character> resultat) {
        ArrayList<Comunitat<Character>> ar = resultat.getComunitats();
        String s;
        for (Comunitat<Character> c : ar) {
            s = new String();
            s += "Comunitat " + c.getId() + ": ";
            HashSet<Character> hc = c.getNodes();
            for (Character chr : hc) {
                s += chr.toString() + " ";
            }
            System.out.println(s);
        }
    }
}
