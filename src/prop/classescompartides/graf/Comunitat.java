package prop.classescompartides.graf;

import java.io.Serializable;
import java.util.HashSet;

/**
 * Aquesta classe representa una Comunitat, la qual és un conjunt de nodes.
 * @param <T>
 */

public class Comunitat<T> implements Serializable {
    private int id;
    protected HashSet<T> nodes;

    /**
     * Constructor per defecte, a partir d'un identificador <tt>id</tt> inicialitza les estructures internes
     */
    public Comunitat() {
        nodes = new HashSet<T>();
    }

    public Comunitat(int id) {
        nodes = new HashSet<T>();
        this.id = id;
    }

    /**
     * Constructor on un node es la seva propia comunitat
     * @param node
     */
    public Comunitat(int id, T node) {
        this.nodes = new HashSet<T>();
        this.nodes.add(node);
        this.id = id;
    }

    /**
     * Afegeix un node <tt>node</tt> a la comunitat
     * @param node
     */
    public void afegirNode(T node){
        nodes.add(node);
    }

    public void afegirCjtNodes(Comunitat<T> c) {
        HashSet<T> s = c.getNodes();
        nodes.addAll(s);
    }

    public void eliminarNode(T node) {
        nodes.remove(node);
    }

    /**
     * Retorna la mida de la comunitat, és a dir, el nombre de nodes que la componen
     * @return la mida de la comunitat
     */
    public int mida(){
        return nodes.size();
    }


    public HashSet<T> getNodes() {
        return nodes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean estaBuida() {return nodes.isEmpty();}

    public boolean teNode(T node) {return nodes.contains(node);}

    @Override
    public String toString() {
        String s = "(";
        int i = 0;
        for(T t : nodes){
            s+= t.toString() + ", id:" + id;
            if(i != nodes.size()-1)
                s+=", ";
            ++i;
        }
        s+=")";
        return s;
    }

    public int getMida(){
        return nodes.size();
    }
}
