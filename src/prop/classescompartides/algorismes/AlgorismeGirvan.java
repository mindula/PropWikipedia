package prop.classescompartides.algorismes;

import prop.classescompartides.graf.Algoritme;
import prop.classescompartides.graf.ConjuntComunitats;
import prop.classescompartides.graf.Graf;

/**
 * Grup 3: Wikipedia
 * Usuari: aleix.paris
 * Data: 03/05/2015
 */

/**
 * Stub de l'algorisme de Girvan-Newman
 * @param <T>
 */
public class AlgorismeGirvan<T> extends Algoritme<T> {

    @Override
    public ConjuntComunitats<T> cercarComunitats(Graf<T> graf, int k, int l) {
        return new ConjuntComunitats<T>();
    }
}
