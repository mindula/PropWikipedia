package prop.classescompartides.algorismes.grupclique;

import prop.classescompartides.graf.*;

import java.util.*;

/**
 * Created by bernat on 27/04/15.
 *
 * Algoritmo Clique
 */
public class AlgoritmoClique<T> extends Algoritme<T> {

    private ArrayList<ArrayList<Integer>> adjacency;
    private ArrayList<T> nodes;
    private ConjuntComunitats<T> art;

    /**
     * Creadora
     */
    public AlgoritmoClique() {
    }


    /**
     * Funcion privada que transforma un graf en una matriz de adyacencia aplicandole una talla de aristas.
     * @param g Es un Graf.
     * @param l Es un Integer que represenat la talla.
     */
    private void transform(Graf<T> g, int l) {
        nodes = new ArrayList<>(g.getNodes());
        adjacency = new ArrayList<>();
        for (int i = 0; i < g.ordre(); ++i) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < g.ordre(); ++j) {
                row.add(0);
            }
            adjacency.add(row);
        }
        for (int i = 0; i < nodes.size(); ++i) {
            ArrayList<Arc<T>> sac = new ArrayList<>(g.getNodesAdjacents(nodes.get(i)));
            ArrayList<Integer> als = new ArrayList<>();
            for (int k = 0; k < g.ordre(); ++k) {
                als.add(0);
            }
            for (int j = 0; j < sac.size(); ++j) {
                if(sac.get(j).getPes() >= l) { // peso < l desaparece arista
                    if (sac.get(j).getNodeA().equals(nodes.get(i))) {
                        als.set(nodes.indexOf(sac.get(j).getNodeB()), 1);
                    } else {
                        als.set(nodes.indexOf(sac.get(j).getNodeA()), 1);
                    }
                }
            }
            adjacency.set(i, als);
        }
    }

    /**
     * Funcion que aplica el algoritmo Cilque sobre un graf y devuelve un ConjuntComunitats con las comunidades encontradas.
     * @param gr Es un Graf.
     * @param k2 Es un Integer que indica el la granularidad del algoritmo Clique.
     * @param l Es un Integer que indica la talla del peso de las aristas del Graf gr.
     * @return Devuelve un ConjuntComunitats com todas las comunidades que ha encontrado.
     */
    public ConjuntComunitats<T> cercarComunitats(Graf<T> gr, int k2, int l) {
        Integer n, K, edge, i, j;
        transform(gr,l); //Transforma g en un matriz de adyacencias y la rellena en el atributo privado
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        n = gr.ordre();
        /*
         * Transforma la matriz de adyacencias en su complementaria y la guarda en "graph" que es el que usara Clique
         */
        for (i = 0; i < n; ++i){
            ArrayList<Integer> row = new ArrayList<>();
            for (j = 0; j < n; ++j){
                edge = adjacency.get(i).get(j);
                if (edge == 0){
                    row.add(1);
                }
                else if (edge == 1){
                    row.add(0);
                }
            }
            graph.add(row);
        }
        K = k2+2;
        ArrayList<Pair<Integer,Integer>> aristasDeCliques = new ArrayList<>();
        boolean primero = true;
        Integer f = 0;
        ArrayList<ArrayList<Integer>> cliquesEncontrados = new ArrayList<>();
        while (primero || f < aristasDeCliques.size()) {
            if (!primero) {
                Pair<Integer,Integer> Ar = aristasDeCliques.get(f);
                if ( f > 0) {
                    Pair<Integer,Integer> Arnueva = aristasDeCliques.get(f-1);
                    Integer R = Arnueva.getR();
                    Integer L = Arnueva.getL();
                    ArrayList<Integer> newRow = graph.get(R);
                    newRow.set(L,0);
                    graph.set(R,newRow);
                    newRow = graph.get(L);
                    newRow.set(R,0);
                    graph.set(L,newRow);
                }
                Integer R = Ar.getR();
                Integer L = Ar.getL();
                ArrayList<Integer> newRow = graph.get(R);
                newRow.set(L,1);
                graph.set(R,newRow);
                newRow = graph.get(L);
                newRow.set(R,1);
                graph.set(L,newRow);
                ++f;
            }
            Set<Integer> nodosCliquesEncontrados = new TreeSet<>();
            Integer k, p, q, r, s, min, counter = 0;
            ArrayList<ArrayList<Integer>> vecinos = new ArrayList<>();
            for (i = 0; i < graph.size(); ++i){
                ArrayList<Integer> vecino = new ArrayList<>();
                for (j = 0; j < graph.get(i).size(); ++j){
                    if (graph.get(i).get(j) == 1) vecino.add(j);
                }
                vecinos.add(vecino);
            }
            k = n - K;
            boolean found = false;
            min = n + 1;
            ArrayList<ArrayList<Integer>> covers = new ArrayList<>();
            ArrayList<Integer> allcover = new ArrayList<>();
            for (i = 0; i < graph.size(); ++i) {
                allcover.add(1);
            }
            for (i = 0; i < allcover.size(); ++i) {
                if (nodosCliquesEncontrados.contains(i)) {
                    ++i;
                    while (nodosCliquesEncontrados.contains(i)) {
                        ++i;
                    }
                    if (i >= allcover.size()) break;
                }
                if (found) {
                    break;
                }
                counter++;
                ArrayList<Integer> cover = new ArrayList<>(allcover);
                cover.set(i, 0);
                cover = funcion1(vecinos, cover);
                s = cover_size(cover);
                if (s < min) min = s;
                if (s <= k) {
                    ArrayList<Integer> aux = new ArrayList<>();
                    for (j = 0; j < cover.size(); ++j) {
                        if (cover.get(j) == 0) {
                            //System.out.print((j + 1) + " .");
                            nodosCliquesEncontrados.add(j+1);
                            aux.add(j);
                        }
                    }
                    if (!aux.isEmpty() && aux.size()>=K-2) {
                        if (!cliquesEncontrados.contains(aux)) {
                            cliquesEncontrados.add(aux);
                            for(Integer g=0; g<aux.size(); ++g) {
                                for (Integer u = g+1; u<aux.size(); ++u){
                                    Pair<Integer,Integer> auxpair = new Pair<>();
                                    auxpair.setL(aux.get(g));
                                    auxpair.setR(aux.get(u));
                                    if (!aristasDeCliques.contains(auxpair)) {
                                        aristasDeCliques.add(auxpair);
                                    }
                                }
                            }
                        }
                    }
                    covers.add(cover);
                    found = true;
                    break;
                }
                for (j = 0; j < n - k; ++j) {
                    cover = funcion2(vecinos, cover, j);
                }
                s = cover_size(cover);
                if (s < min) {
                    min = s;
                }
                ArrayList<Integer> aux = new ArrayList<>();
                for (j = 0; j < cover.size(); ++j) {
                    if (cover.get(j) == 0) {
                        nodosCliquesEncontrados.add(j + 1);
                        aux.add(j);
                    }
                }
                if (!aux.isEmpty() && aux.size()>=K-2) {
                    if (!cliquesEncontrados.contains(aux)) {
                        cliquesEncontrados.add(aux);
                        for(Integer g=0; g<aux.size(); ++g) {
                            for (Integer u = g+1; u<aux.size(); ++u){
                                Pair<Integer,Integer> auxpair = new Pair<>();
                                auxpair.setL(aux.get(g));
                                auxpair.setR(aux.get(u));
                                if (!aristasDeCliques.contains(auxpair)) {
                                    aristasDeCliques.add(auxpair);
                                }
                            }
                        }
                    }
                }
                covers.add(cover);
                if (s <= k) {
                    found = true;
                    break;
                }
            }
            primero = false;
        }
        ConjuntComunitats<T> cjc;
        cjc = CC(cliquesEncontrados, k2);
        return cjc;
    }

    /**
     * Funcion privada para el algotitmo Clique
     */
    private ArrayList<Integer> funcion1(ArrayList<ArrayList<Integer>> vecinos, ArrayList<Integer> cover){
        ArrayList<Integer> temp = new ArrayList<>(cover);
        Integer r = 0;
        while (r!=-1){
            r = maximoEliminable(vecinos, temp);
            if (r!=-1) temp.set(r,0);
        }
        return temp;
    }

    /**
     * Funcion privada para el algotitmo Clique
     */
    private Integer maximoEliminable(ArrayList<ArrayList<Integer>> vecinos, ArrayList<Integer> cover){
        Integer r = -1;
        Integer max = -1;
        for (int i = 0; i < cover.size(); ++i){
            if (cover.get(i) == 1 && esEliminable(vecinos.get(i),cover)){
                ArrayList<Integer> temp = new ArrayList<>(cover);
                temp.set(i,0);
                Integer sum = 0;

                for (int j = 0; j < temp.size(); ++j){
                    if (temp.get(j) == 1 && esEliminable(vecinos.get(j), temp)){
                        sum++;
                    }
                }
                if (sum > max){
                    max = sum;
                    r = i;
                }
            }
        }
        return r;
    }

    /**
     * Funcion privada para el algotitmo Clique
     */
    private boolean esEliminable (ArrayList<Integer> vecino, ArrayList<Integer> cover){
        boolean check = true;
        for (int i = 0; i < vecino.size(); ++i){
            if (cover.get(vecino.get(i)) == 0) {
                check = false;
                break;
            }
        }
        return check;
    }


    /**
     * Funcion privada para el algotitmo Clique
     */
    private Integer cover_size(ArrayList<Integer> cover){
        int count = 0;
        for(int i = 0; i < cover.size(); ++i){
            if (cover.get(i) == 1){
                count++;
            }
        }
        return count;
    }

    /**
     * Funcion privada para el algotitmo Clique
     */
    private ArrayList<Integer> funcion2(ArrayList<ArrayList<Integer>> vecinos, ArrayList<Integer> cover, Integer k){
        Integer count = 0;
        ArrayList<Integer> temp_cover = new ArrayList<>(cover);
        Integer i = 0, j = 0;
        for (i = 0; i < temp_cover.size(); ++i){
            if (temp_cover.get(i) == 1){
                Integer sum = 0;
                Integer index = -1;
                for (j = 0 ; j < vecinos.get(i).size(); ++j){
                    if (temp_cover.get(vecinos.get(i).get(j)) == 0){
                        index = j;
                        ++sum;
                    }
                }
                if (sum == 1 && cover.get(vecinos.get(i).get(index)) == 0){
                    temp_cover.set(vecinos.get(i).get(index),1);
                    temp_cover.set(i,0);
                    temp_cover = funcion1(vecinos,temp_cover);
                    ++count;
                }
                if (count > k){
                    break;
                }
            }
        }
        return temp_cover;
    }

    /**
     * Funcion privada para el algotitmo Clique que comprueva si dos cliques son adyacentes.
     * Para serlo tienen que tener todos los nodos menos uno iguales
     */
    private Boolean cliquesAdjacents(ArrayList<Integer> c1, ArrayList<Integer> c2){
        Integer nodesAd = 0;
        if(c1.size()<=c2.size()){
            for(int i = 0; i<c1.size(); i++){
                for(int j = 0; j<c2.size(); ++j){
                    if(c1.get(i)==c2.get(j)) nodesAd++;
                }
            }
        }
        else{
            for(int i = 0; i<c2.size(); i++){
                for(int j = 0; j<c1.size(); ++j){
                    if(c2.get(i)==c1.get(j)) nodesAd++;
                }
            }
        }
        return ((nodesAd==c1.size()-1) && (nodesAd==c2.size()-1));
    }

    /**
     * Funcion privada para el algotitmo Clique para añadir un clique a una comunidad.
     */
    private Comunitat<T> afegirCliqueAComunitat(ArrayList<Integer> cli, Comunitat<T> com){
        /**
         * Recorre todos los elementos del clique y a traves de la lista 'nodos'
         * añade todos los nodos a la comunidad
         */
        for(int i=0; i<cli.size(); ++i){
            int b = cli.get(i);
            T a = nodes.get(b);
            com.afegirNode(a);
        }
        return com;
    }


    /**
     * Funcion privada para el algotitmo Clique que devuelve un conjunto de comunidades a partir de una lista de cliques
     */
    private ConjuntComunitats<T> CC(ArrayList<ArrayList<Integer>> cliques2, int k2){
        //ignorar de clique las filas que tengan size() diferente de k2
        ArrayList<ArrayList<Integer>> cliques = new ArrayList<>();
        for (ArrayList<Integer> ar : cliques2) {
            if (ar.size() == k2) {
                cliques.add(ar);
            }
        }
        ConjuntComunitats<T> cc = new ConjuntComunitats<>();
        Integer nCliques = cliques.size();

        //inicializamos a 0 el vector cliquesUsados
        ArrayList<Integer> cliquesUsados = new ArrayList<>();
        for(int k = 0; k<nCliques; ++k) {
            cliquesUsados.add(0);
        }

        Integer idCom = 0; //identificador de las comunidades que crearemos
        //Recorremos todos los cliques que no esten marcados (ya esten dentro de una comunidad)
        for(int i = 0; i<nCliques; ++i){
            if(cliquesUsados.get(i)==0){
                Comunitat c = new Comunitat();
                c.setId(idCom);

                //creamos una cola para poder buscar los cliques adyacentes de los cliques que son adyacentes
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                while(!q.isEmpty()){
                    int k = q.peek();
                    q.poll();
                    cliquesUsados.set(k, 1);
                    c=afegirCliqueAComunitat(cliques.get(k),c);
                    //recorremos todos los cliques y si no estan marcados y son adyacentes los añade a la cola
                    for (int j = 0; j < nCliques; j++) {
                        if (cliquesUsados.get(j)==0 && cliquesAdjacents(cliques.get(k), cliques.get(j))) {
                            q.add(j);
                        }
                    }
                }
                cc.afegirComunitat(c);
                ++idCom;
            }
        }
        return cc;
    }
}