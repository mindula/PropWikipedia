package prop.classescompartides.graf;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard.gascons
 * Data: 10/3/15
 */

/*************************************************************************
 *  Compilació:   javac Graf.java
 *  Execució:     java Graf
 *  Dependències: Map.java Arc.java
 *
 *  Un graf no dirigit amb pesos a les arestes, implementat utilitzant un mapa
 *  d'adjacències
 *
 *************************************************************************/



/**
 *  La classe <tt>Graf</tt> representa un graf NO dirigit amb pesos als arcs,
 *  amb vèrtex parametritzats; cada arc és del tipus {@link Arc}
 *  i té un pes en el rang dels reals.
 *  La classe suporta un seguit d'opracions primaries: afegir un node,
 *  afegir un arc amb pes entre dos nodes del graf, iterar sobre tots els arcs
 *  incidents d'un node donat, i les conseqüents operacions d'eliminar un node del
 *  graf i eliminar els arcs entre dos nodes donats.
 *  A més la classe proporciona un seguit de mètodes per retornar el nombre
 *  de vèrtex <em>V</em> i el nombre d'arestes <em>E</em>, anomenats ordre i
 *  mida respectivament. També es pot consultar el nombre d'arcs adjacents a un
 *  node, consultar si existeix un node en concret dins el graf i consultar
 *  si hi ha algun arc entre dos nodes donats.
 *  IMPORTANT: el paràmetre T ha d'implementar la funció equals que el programador
 *  trobi pertinent. A més, es recomana tenir la classe Graf en un package
 *  individual, donat que un dels seus atributs és protected.
 *  <p>
 *  Aquesta implementació utilitza llistes d'adjacència per representar
 *  el graf, què són un seguit de Sets continguts dins un Map.
 *  Degut a això, es permet afegir més d'un arc entre dos nodes donats.
 *  És responsabilitat del programador les possibles conseqüencies que això
 *  podria suposar.
 *  <p>
 */

import java.util.*;

public class Graf<T> {

    protected HashMap<T, Map<T, Arc<T>>> adjacencyMap;
    protected ArrayList<Arc<T>> cjtArcs;
    protected HashSet<T> cjtNodes;
    protected int V, E;

    /**
     * Inicialitza un Graf buit
     */
    public Graf() {
        adjacencyMap = new HashMap<T, Map<T, Arc<T>>>();
        cjtArcs = new ArrayList<Arc<T>>();
        cjtNodes = new HashSet<T>();
        V = E = 0;
    }

    /**
     * Constructor còpia. Còpia un graf <tt>other</tt> al paràmetre implicit
     * @param other
     */
    public Graf(Graf<T> other) {
        adjacencyMap = new HashMap<T, Map<T, Arc<T>>>();
        cjtArcs = new ArrayList<Arc<T>>();
        for (T n : other.adjacencyMap.keySet()) {
            Map<T, Arc<T>> otherAdjacents = other.adjacencyMap.get(n);
            Map<T, Arc<T>> adjacents = new HashMap<T, Arc<T>>();
            for (Map.Entry<T, Arc<T>> otherArc : otherAdjacents.entrySet()){
                Arc<T> arc;
                if(adjacencyMap.containsKey(otherArc.getValue().getNodeA()) || adjacencyMap.containsKey(otherArc.getValue().getNodeA()) ){
                    arc = getArcEntre(otherArc.getValue().getNodeA(), otherArc.getValue().getNodeB());
                }
                else{
                    arc = new Arc<T>(otherArc.getValue());
                }
                adjacents.put(otherArc.getKey(), arc);
            }
            adjacencyMap.put(n, adjacents);
        }
        for (Arc<T> a : other.cjtArcs) {
            Arc<T> arc = new Arc<T>(a.getPes(),a.getNodeA(),a.getNodeB());
            cjtArcs.add(arc);
        }
        E = other.mida();
        V = other.ordre();
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

        adjacencyMap.put(node, new HashMap<T, Arc<T>>());
        cjtNodes.add(node);
        ++V;
    }

    /**
     * Afegeix un arc <tt>a</tt>. Si l'arc ja existeix, no passa res
     * @param arc
     * @throws RuntimeException quan algun dels dos nodes no existeix al graf
     */
    public void afegirArc(Arc<T> arc) {
        if (!cjtArcs.contains(arc)) {
            if (!adjacencyMap.containsKey(arc.getNodeA()))
                throw new RuntimeException("El node A ha d'estar previament al graf");
            if (!adjacencyMap.containsKey(arc.getNodeB()))
                throw new RuntimeException("El node B ha d'estar previament al graf");

            adjacencyMap.get(arc.getNodeA()).put(arc.getNodeB(), arc);
            adjacencyMap.get(arc.getNodeB()).put(arc.getNodeA(), arc);
            cjtArcs.add(arc);
            ++E;
        }

    }

    /**
     * Elimina un node <tt>node</tt> del graf.
     * @param node
     * @throws RuntimeException si el node <tt>node</tt> no existeix al graf
     */
    public void eliminarNode(T node) {
        if(!adjacencyMap.containsKey(node))
            throw new RuntimeException("No es pot eliminar un node que no està dins el graf");

        Set<T> s = adjacencyMap.keySet();
        for (T t : s) {
            if (adjacencyMap.get(t).containsKey(node)) {
                Arc<T> aux = adjacencyMap.get(t).get(node);
                adjacencyMap.get(t).remove(node);
                cjtArcs.remove(aux);
                --E;
            }
        }
        adjacencyMap.remove(node);
        cjtNodes.remove(node);
        --V;
    }

    /**
     * Elimina un arc <tt>arc</tt> del graf
     * @param arc
     * @throws RuntimeException quan l'arc no existeix dins el graf
     */

    public void eliminarArc(Arc<T> arc) {

        Collection<Arc<T>> aAdjacents = adjacencyMap.get(arc.getNodeA()).values();
        Collection<Arc<T>> bAdjacents = adjacencyMap.get(arc.getNodeB()).values();

        if(! aAdjacents.remove(arc) || !bAdjacents.remove(arc))
            throw new RuntimeException("L'arc no existeix");
        cjtArcs.remove(arc);
        --E;
    }

    /**
     * Retorna un Set amb tots els nodes del graf.
     * Els canvis realitzats al Graf tindran efecte sobre el Set, i també al contrari.
     * @return un Set amb tots els nodes del graf
     */
    public HashSet<T> getNodes() {
        return cjtNodes;
    }

    /**
     * Retorna una llista no modificable del conjunt d'arcs de tot el graf
     * @return una llista no modificable del conjunt d'arcs de tot el graf
     */
    public List<Arc<T>> getArcs() {
        return Collections.unmodifiableList(cjtArcs);
    }

    /**
     * Retorna una Set amb els arcs que surten d'un node <tt>node</tt>
     * Els canvis realitzats al Graf tindran efecte sobre el set, i també al contrari
     * @param node
     * @return una Set amb els arcs que surten d'un node <tt>node</tt>
     */
    public HashSet<Arc<T>> getNodesAdjacents(T node) {
        return new HashSet<Arc<T>>(adjacencyMap.get(node).values());
    }

    /**
     * Retorna l'arc entre <tt>nodeA</tt> i <tt>nodeB</tt>
     * @param nodeA
     * @param nodeB
     * @return l'arc entre <tt>nodeA</tt> i <tt>nodeB</tt>
     */
    public Arc<T> getArcEntre(T nodeA, T nodeB) {
        return adjacencyMap.get(nodeA).get(nodeB);
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
     * Retorna un valor indicant si existeix un arc del node <tt>nodeA</tt> a
     * <tt>nodeB</tt>
     * @param nodeA
     * @param nodeB
     * @return cert si existeix un arc del node <tt>nodeA</tt> a
     * <tt>nodeB</tt>
     */
    public boolean existeixArc(T nodeA, T nodeB) {
        if (!adjacencyMap.containsKey(nodeA) || !adjacencyMap.containsKey(nodeB))
            return false;

        return adjacencyMap.get(nodeA).containsKey(nodeB);
    }


    public static<T> T getNodeOposat(T nodeOrigen, Arc<T> arc){
        if(arc.getNodeA() == nodeOrigen)
            return arc.getNodeB();
        else
            return arc.getNodeA();
    }


    /**
     * Retorna una representació en String del graf
     * @return una representació en String del graf
     */
    @Override
    public String toString() {
        String s = "";
        for (T node : adjacencyMap.keySet()){
            s += node + ": { ";
            HashSet<Arc<T>> adjacents = getNodesAdjacents(node);
            for(Arc<T> adjacentAT : adjacents){
                T adjacent = getNodeOposat(node, adjacentAT);
                s+=adjacent+"("+adjacentAT.getPes()+") ";
            }
            s+="}\n";
        }
        return s;
    }

}
