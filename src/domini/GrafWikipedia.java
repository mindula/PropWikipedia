package domini;

/*************************************************************************
 *  Compilació:   javac Graf.java
 *  Execució:     java Graf
 *  Dependències: Map.java Arc.java
 *
 *  Un digraf amb pesos a les arestes, implementat utilitzant llistes
 *  d'adjacències
 *
 *************************************************************************/

/**
 *  La classe <tt>Graf</tt> representa un digraf amb pesos als arcs,
 *  amb vèrtex parametritzats; cada arc és del tipus {@link Arc}
 *  i té un pes en el rang dels reals.
 *  La classe suporta un seguit d'opracions primaries: afegir un node,
 *  afegir un arc amb pes entre dos nodes del digraf, iterar sobre tots els arcs
 *  incidents d'un node donat, i les conseqüents operacions d'eliminar un node del
 *  digraf i eliminar els arcs entre dos nodes donats.
 *  A més la classe proporciona un seguit de mètodes per retornar el nombre
 *  de vèrtex <em>V</em> i el nombre d'arestes <em>E</em>, anomenats ordre i
 *  mida respectivament. També es pot consultar el nombre d'arcs adjacents a un
 *  node, consultar si existeix un node en concret dins el digraf i consultar
 *  si hi ha algun arc entre dos nodes donats.
 *  IMPORTANT: el paràmetre T ha d'implementar la funció equals que el programador
 *  trobi pertinent.
 *  <p>
 *  Aquesta implementació utilitza llistes d'adjacència per representar
 *  el digraf, què són un seguit de Sets continguts dins un Map.
 *  Degut a això, es permet afegir més d'un arc entre dos nodes donats.
 *  És responsabilitat del programador les possibles conseqüencies que això
 *  podria suposar.
 *  <p>
 */

import java.util.*;

public class GrafWikipedia<T> {

    private Map<T, Set<Arc<T>>> adjacencyMap;
    private int V, E;

    /**
     * Inicialitza un Graf buit
     */
    public Graf() {
        adjacencyMap = new HashMap<T, Set<Arc<T>>>();
        V = E = 0;
    }

    public Graf(Graf G) {
        Set<T> setNodes = G.getNodes();
        for (T t : setNodes) {
            this.afegirNode(t);
            Set<Arc<T>> setAdjacents = G.getNodesAdjacents(t);
            for (Arc<T> a : setAdjacents) {
                this.afegirArc(t, a.getNodeDesti(), a.getPes());
            }
        }
    }

    /**
     * Retorna el nombre d'arcs del graf
     * @return el nombre d'arcs del graf
     */
    public int mida() {
        return E;
    }

    /**
     * Retorna el nombre de vèrtex del graf
     * @return el nombre de vèrtex del graf
     */
    public int ordre() {
        return V;
    }

    /**
     * Afegeix un node <tt>node</tt> al graf
     * @param node a afegir.
     * @throws RuntimeException si el node ja es al graf previament
     */
    public void afegirNode(T node) {
        if(adjacencyMap.containsKey(node))
            throw new RuntimeException("No es pot inserir el mateix node multiples vegades al mateix graf");

        adjacencyMap.put(node, new LinkedHashSet<Arc<T>>());
        ++V;
    }

    /**
     * Afegeix un arc amb pes 0 del node <tt>nodeOrigen</tt> a <tt>nodeDesti</tt>. Veure el mètode afegirArc
     * de sota per més informació.
     * @param nodeOrigen
     * @param nodeDesti
     */
    public void afegirArc(T nodeOrigen, T nodeDesti) {
        afegirArc(nodeOrigen, nodeDesti, 0);
    }

    /**
     * Afegeix un arc entre del node <tt>nodeOrigen</tt> a <tt>nodeDesti</tt>.
     * @param nodeOrigen
     * @param nodeDesti
     * @param pes
     * @throws RuntimeException quan algun dels dos nodes no existeix al graf
     */
    public void afegirArc(T nodeOrigen, T nodeDesti, double pes) {
        if (!adjacencyMap.containsKey(nodeOrigen))
            throw new  RuntimeException("El node origen ha d'estar previament al graf");
        if (!adjacencyMap.containsKey(nodeDesti))
            throw new  RuntimeException("El node desti ha d'estar previament al graf");

        Arc<T> a = new Arc<T>(pes, nodeDesti);
        if(adjacencyMap.get(nodeOrigen).contains(a)){
            throw new  RuntimeException("L'arc ja existeix");
        }
        else{
            adjacencyMap.get(nodeOrigen).add(a);
            ++E;
        }

    }

    public void modificarPesArc(T nodeOrigen, T nodeDesti, double nouPes) {
        adjacencyMap.get(nodeOrigen).remove(new Arc<T>(nodeDesti));
        adjacencyMap.get(nodeOrigen).add(new Arc<T> (nouPes, nodeDesti));
    }

    /**
     * Elimina un node <tt>node</tt> del graf.
     * @param node
     * @throws RuntimeException si el node <tt>node</tt> no existeix al graf
     */
    public void eliminarNode(T node) {
        if(!adjacencyMap.containsKey(node))
            throw new RuntimeException("No es pot eliminar un node que no està dins el graf");

        for(T b : adjacencyMap.keySet()){
            Set<Arc<T>> bAdjacents = adjacencyMap.get(b);
            if (bAdjacents.remove(new Arc<T> (node))) --E;
        }

        Set<Arc<T>> nodeAdjacents = adjacencyMap.get(node);
        E -= nodeAdjacents.size();
        adjacencyMap.remove(node);
        --V;
    }

    /**
     * Elimina TOTS els arcs de <tt>nodeOrigen</tt> a <tt>nodeDesti</tt>.
     * @param nodeOrigen
     * @param nodeDesti
     * @throws RuntimeException si no existeix cap arc entre els nodes dins el graf
     */
    public void eliminarArc(T nodeOrigen, T nodeDesti) {
        Set<Arc<T>> bAdjacents = adjacencyMap.get(nodeOrigen);
        if (bAdjacents.remove(new Arc<T> (nodeDesti))) --E;
        else throw new RuntimeException("L'arc no existeix");
    }

    /**
     * Retorna un Set amb tots els nodes del graf
     * @return un Set amb tots els nodes del graf
     */
    public Set<T> getNodes() {
        return adjacencyMap.keySet();
    }

    /**
     * Retorna una Set amb els arcs que surten d'un node <tt>node</tt>
     * @param node
     * @return una Set amb els arcs que surten d'un node <tt>node</tt>
     */
    public Set<Arc<T>> getNodesAdjacents(T node) {
        return adjacencyMap.get(node);
    }

    /**
     * Retorna un nombre corresponent al nombre d'arcs que surten d'un node <tt>node</tt>
     * @param node
     * @return un nombre corresponent al nombre d'arcs que surten d'un node <tt>node</tt>
     */
    public int getGrau (T node) {
        return adjacencyMap.get(node).size();
    }

    /**
     * Retorna un valor indicant si existeix un node <tt>node</tt> dins el graf
     * @param node
     * @return un valor indicant si existeix un node <tt>node</tt> dins el graf
     */
    public boolean existeixNode(T node) {
        return adjacencyMap.containsKey(node);
    }

    /**
     * Retorna un valor indicant si existeix un arc del node <tt>nodeOrigen</tt> a
     * <tt>nodeDesti</tt>
     * @param nodeOrigen
     * @param nodeDesti
     * @return un valor indicant si existeix un arc del node <tt>nodeOrigen</tt> a
     * <tt>nodeDesti</tt>
     */
    public boolean existeixArc(T nodeOrigen, T nodeDesti) {
        if (!adjacencyMap.containsKey(nodeOrigen))
            throw new  RuntimeException("El node origen ha d'estar previament al graf");
        if (!adjacencyMap.containsKey(nodeDesti))
            throw new  RuntimeException("El node desti ha d'estar previament al graf");
        return adjacencyMap.get(nodeOrigen).contains(new Arc<T> (nodeDesti));
    }

    /**
     * Retorna una representació en String del graf
     * @return una representació en String del graf
     */
    @Override
    public String toString() {
        return adjacencyMap.toString();
    }

}
