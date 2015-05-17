package prop.classescompartides.algorismes.grupclique;

/**
 * Created by Bernat
 */
public class CtrlAlgoritmoClique<T>  extends Algoritme<T> {

    @Override
    public ConjuntComunitats<T> cercarComunitats(Graf<T> graf, double k){
        AlgoritmoClique<T> alg = new AlgoritmoClique<>();
        return alg.executeClique(graf, k);
    }

}
