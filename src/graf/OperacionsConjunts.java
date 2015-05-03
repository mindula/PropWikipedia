package graf;

import org.grupwiki.graf.Comunitat;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 24/04/15
 */
public class OperacionsConjunts{


    public static<T> Comunitat<T> unio(Comunitat<T> a, Comunitat<T>  b){
        Comunitat<T>  c = new Comunitat<T>();
        c.getNodes().addAll(a.getNodes());
        c.getNodes().addAll(b.getNodes());
        return c;
    }



    public static<T> Comunitat<T> interseccio(Comunitat<T>  a, Comunitat<T> b){
        Comunitat<T> c = new Comunitat<T>();
        for (T n : a.getNodes()){
            if (b.getNodes().contains(n)){
                c.getNodes().add(n);
            }
        }
        return c;
    }

    public static<T> Comunitat<T>  diferencia(Comunitat<T>  a, Comunitat<T> b){
        Comunitat<T> c = new Comunitat<T> ();
        c.getNodes().addAll(a.getNodes());
        c.getNodes().removeAll(b.getNodes());
        return c;
    }
}
