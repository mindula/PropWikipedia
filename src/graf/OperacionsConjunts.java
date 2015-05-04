package graf;

import prop.classescompartides.graf.Comunitat;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 24/04/15
 */

/**
 * Realitza operacions de conjunts entre dues comunitats de nodes
 */
public class OperacionsConjunts{


    /**
     * Operacio d'unio
     * @param a
     * @param b
     * @param <T>
     * @return una comunitat que es la unio de <tt>a</tt> i <tt>b</tt>
     */
    public static<T> Comunitat<T> unio(Comunitat<T> a, Comunitat<T>  b){
        Comunitat<T>  c = new Comunitat<T>();
        c.getNodes().addAll(a.getNodes());
        for(T i: b.getNodes())
            if (!c.teNode(i)) {
                c.afegirNode(i);
            }

        c.getNodes().addAll(b.getNodes());
        return c;
    }

    /**
     * Operacio d'interseccio
     * @param a
     * @param b
     * @param <T>
     * @return una comunitat que es la interseccio de <tt>a</tt> i <tt>b</tt>
     */
    public static<T> Comunitat<T> interseccio(Comunitat<T>  a, Comunitat<T> b){
        Comunitat<T> c = new Comunitat<T>();
        for (T n : a.getNodes()){
            if (b.getNodes().contains(n)){
                c.getNodes().add(n);
            }
        }
        return c;
    }

    /**
     * Operacio de diferencia
     * @param a
     * @param b
     * @param <T>
     * @return una comunitat que es la diferencia de <tt>a</tt> i <tt>b</tt>
     */
    public static<T> Comunitat<T>  diferencia(Comunitat<T>  a, Comunitat<T> b){
        Comunitat<T> c = new Comunitat<T> ();
        c.getNodes().addAll(a.getNodes());
        c.getNodes().removeAll(b.getNodes());
        return c;
    }
}
