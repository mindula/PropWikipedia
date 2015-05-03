package domini;

import org.grupwiki.graf.Algoritme;
import org.grupwiki.graf.ConjuntComunitats;
import org.grupwiki.graf.Graf;

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
