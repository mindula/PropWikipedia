package prop.classescompartides.algorismes;

import domini.Pair;
import prop.classescompartides.graf.*;

import java.util.*;

/**
 * Grup 3: Wikipedia
 * Usuari: aleix.paris, ricard.gascons
 * Data: 23/04/15
 */

/**
 * Algorisme Louvain per la generacio de Comunitats
 * @param <T>
 */
public class AlgorismeLouvain<T> extends Algoritme<T> {
    private ArrayList<Pair<Integer, Integer>> graphTree;
    private Graf<Integer> grafIntern;
    private int lastLevel = 0;
    private double minModularity;
    private int nPassades;
    private HashMap<Integer, T> traduccioIntegerT;

    /**
     *
     * @param grafOriginal representa el graf en el que es vol trobar les comunitats
     * @param criterioParada representa el nombre de passades de l'algorisme. Recomanat: |V|*2
     * @param nul no s'utilitza en aquest algorisme
     * @return un conjunt de comunitats, formades pels nodes del graf
     */
    @Override
    public ConjuntComunitats<T> cercarComunitats(Graf<T> grafOriginal, int criterioParada, int nul){
        graphTree = new ArrayList<Pair<Integer, Integer>>();
        this.nPassades = criterioParada;
        this.minModularity = 0.000001;
        traduccioIntegerT = new HashMap<Integer, T>();
        grafIntern = convertirGraf(grafOriginal);
        metodeLouvain();
        return formarComunitats(imprimirSolucio());
    }

    private ConjuntComunitats<T> formarComunitats(ArrayList<Pair<Integer, Integer>> solucioIntegers) {
        ConjuntComunitats<T> cc = new ConjuntComunitats<T>();
        HashSet<Integer> hs = new HashSet<Integer>();
        for (Pair<Integer, Integer> p : solucioIntegers) hs.add(p.getSecond());
        for (Integer i : hs) {
            cc.afegirComunitat(new Comunitat<T>(i));
        }
        for (Pair<Integer, Integer> p : solucioIntegers) {
            try {
                Comunitat<T> c = cc.getComunitat(p.getSecond());
                c.afegirNode(traduccioIntegerT.get(p.getFirst()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cc;
    }

    private Graf<Integer> convertirGraf(Graf<T> grafOriginal){
        HashMap<T, Integer> traduccioTInteger = new HashMap<T, Integer>();
        HashSet<T> nodesOriginals = grafOriginal.getNodes();
        Graf<Integer> grafFinal = new Graf<Integer>();
        Integer i = 0;
        for(T nodeOriginal : nodesOriginals){
            traduccioIntegerT.put(i, nodeOriginal);
            traduccioTInteger.put(nodeOriginal, i);
            grafFinal.afegirNode(i);
            i++;
        }

        List<Arc<T>> arcs = grafOriginal.getArcs();
        for(Arc<T> arc : arcs){
            T AT = arc.getNodeA();
            T BT = arc.getNodeB();
            double pes = arc.getPes();
            Integer AInteger = traduccioTInteger.get(AT);
            Integer BInteger = traduccioTInteger.get(BT);
            Arc<Integer> nouArc = new Arc<Integer>(pes, AInteger, BInteger);
            grafFinal.afegirArc(nouArc);
        }
        return grafFinal;
    }

    private void metodeLouvain() {
        ComunitatLouvain c = new ComunitatLouvain(grafIntern, nPassades, minModularity);

        double mod = c.modularity();

        /*System.out.println("xarxa : "
                + c.getGraf().ordre() + " nodes, "
                + c.getGraf().mida() + " arestes, ");*/

        double new_mod = c.oneLevel();

        //System.out.println("la modularitat ha variat de " + mod + " a " + new_mod);

        graphTree.addAll(c.mostrarParticioActual());

        Graf<Integer> g = c.convertirParticioAGraf();

        while(new_mod-mod>minModularity) {
            mod=new_mod;
            ComunitatLouvain c1 = new ComunitatLouvain(g, -1, minModularity);

            /*System.out.println ( "\nxarxa : "
                    + c1.getGraf().ordre() + " nodes, "
                    + c1.getGraf().mida() + " arestes, ");*/

            new_mod = c1.oneLevel();

            //System.out.println("la modularitat ha variat de " + mod + " to " + new_mod);


            graphTree.addAll(c1.mostrarParticioActual());

            g = c1.convertirParticioAGraf();

            //System.out.println(g);

            ++lastLevel;

        }
    }

    private ArrayList<Pair<Integer, Integer>> imprimirSolucio () {
        ArrayList<Pair<Integer, Integer>> solucio = new ArrayList<Pair<Integer, Integer>>();
        ArrayList<ArrayList<Integer> >levels = new ArrayList<ArrayList<Integer>>();

        int l=-1;
        for (Pair<Integer, Integer> p : graphTree) {
            int node, nodecomm;
            node = p.getFirst();
            nodecomm = p.getSecond();

            if (node == 0) {
                l++;
                levels.add(new ArrayList<Integer>());
            } levels.get(l).add(nodecomm);
        }

        int []n2c = new int[levels.get(0).size()];

        for (int i=0 ; i< levels.get(0).size() ; i++)
            n2c[i]=i;

        for (l=0 ; l < lastLevel; l++)
            for (int node=0 ; node<levels.get(0).size() ; node++)
                n2c[node] = levels.get(l).get(n2c[node]);

        for (int node=0 ; node<levels.get(0).size() ; node++)
            solucio.add(new Pair<Integer, Integer>(node, n2c[node]));

        return solucio;
    }


    private class ComunitatLouvain {
        // Graf de comunitats
        private Graf<Integer> g;

        // Nombre de nodes del graf i mida de tots els vectors
        private int size;

        // Comunitat a la qual cada node pertany
        private int[] n2c;

        // S'utilitza per calcular la modularitat de cada comunitat
        private double[] in,tot;

        // Nombre de passades realitzades per nivell
        // si latribut val -1, es fan tantes passes com es vegi necessari per incrementar la modularitat
        private int nb_pass;

        // una nova passada es calcula si lultima ha generat un increment
        // major que min_modularity
        // si latribut val 0, un increment molt petit pot resultar en una nova passada
        private double min_modularity;

        //Els nodes estan anometats consecutivament desde 0
        public ComunitatLouvain (Graf<Integer> g, int nb_pass, double min_modularity) {
            this.g = g;
            size = g.ordre();
            n2c = new int[size];
            in = new double[size];
            tot = new double[size];
            for (int i=0 ; i<size ; i++) {
                n2c[i] = i;
                in[i]  = numSelfLoops(i);
                tot[i] = weightedDegree(i);
            }
            this.nb_pass = nb_pass;
            this.min_modularity = min_modularity;
        }

        private double numSelfLoops(int i) {
            double num = 0;
            HashSet<Arc<Integer>> h = g.getNodesAdjacents(i);
            for (Arc<Integer> a : h) {
                if (a.getNodeA().equals(a.getNodeB())) ++num;
            }
            return num;
        }

        private double weightedDegree (int i) {
            double weightNeigthbours = 0.0;
            HashSet<Arc<Integer>> h = g.getNodesAdjacents(i);
            for (Arc<Integer> a : h) {
                weightNeigthbours += a.getPes();
            }
            return weightNeigthbours;
        }

        // Elimina el node de la seva comunitat actual el qual comparteix dnodecomm arestes
        public void remove(int node, int comm, double dnodecomm) {
            if (!(node>=0 && node<size))
                throw new RuntimeException("No s'ha complert que node>=0 && node<size");

            tot[comm] -= weightedDegree(node);
            in[comm]  -= 2*dnodecomm + numSelfLoops(node);
            n2c[node]  = -1;
        }

        // Inserta un node a comm el qual comparteix dnodecomm arestes
        public void insert(int node, int comm, double dnodecomm) {
            if (!(node>=0 && node<size))
                throw new RuntimeException("No s'ha complert que node>=0 && node<size");

            tot[comm] += weightedDegree(node);
            in[comm]  += 2*dnodecomm + numSelfLoops(node);
            n2c[node]=comm;
        }

        // Computa el guany en modularitat en cas que un node fos inserit a comm
        // Donat que un node te dnodecomm arestes a comm, la formula es la seguent:
        // [(In(comm)+2d(node,comm))/2m - ((tot(comm)+deg(node))/2m)^2]-
        // [In(comm)/2m - (tot(comm)/2m)^2 - (deg(node)/2m)^2]
        // on    In(comm)    = nombre de mitges-arestes dins de comm
        //       tot(comm)   = nombre de mitges-arestes dins o fora de comm (sum(degrees))
        //       d(node,comm)= nombre de les arestes de node a comm
        //       deg(node)   = el nombre d'arestes que surten d'un node
        //       m           = numbre d'arestes
        public double modularityGain(int node, int comm, double dnodecomm) {
            double totc = tot[comm];
            double degc = weightedDegree(node);
            double m2   = totalWeight();
            double dnc  = dnodecomm;

            return (dnc - totc*degc/m2) ;
        }

        public double totalWeight() {
            double sumaArcs = 0;
            List<Arc<Integer>> arcs = g.getArcs();
            for (Arc<Integer> a : arcs) {
                sumaArcs += a.getPes();
            }
            return sumaArcs;
        }

        // Calcula el conjutnt de comunitats veines d'un node
        // Per cada comunitat, dona el nombre d'arestes d'un node a comm
        public HashMap<Integer,Double> neighCommunity(int node) {
            HashMap<Integer,Double> res = new HashMap<Integer, Double>();
            HashSet<Arc<Integer>> h = g.getNodesAdjacents(node);

            res.put(n2c[node],0.0);
            for (Arc<Integer> a : h) {
                int neigh = Graf.getNodeOposat(node, a);
                int neight_comm = n2c[neigh];
                double neigh_weight = a.getPes();
                if (neigh != node) {
                    if (res.containsKey(neight_comm)) {
                        double aux = res.get(neight_comm) + neigh_weight;
                        res.put(neight_comm, aux);
                    } else res.put(neight_comm, neigh_weight);
                }
            }
            return res;
        }

        public double modularity() {
            double q  = 0.;
            double m2 = totalWeight();

            for (int i=0 ; i < size ; i++) {
                if (tot[i] > 0)
                    q += in[i]/m2 - (tot[i]/m2)*(tot[i]/m2);
            }
            return q;
        }

        // Converteix la particio actual a un Graf d'Integers
        public Graf<Integer> convertirParticioAGraf() {
            int[] renumber = new int[size];
            for (int i = 0; i < size; ++i) {
                renumber[i] = -1;
            }
            for (int node=0 ; node<size ; node++) {
                renumber[n2c[node]]++;
            }

            int Final=0;
            for (int i=0 ; i<size ; i++)
                if (renumber[i]!=-1)
                    renumber[i]=Final++;

            // Calcula les comunitats
            ArrayList<Integer>[] comm_nodes = new ArrayList[size];
            for(int i = 0; i< size ; i++)
                comm_nodes[i] = new ArrayList<Integer>();


            for (int node=0 ; node<size ; node++) {
                comm_nodes[renumber[n2c[node]]].add(node);
            }




            Graf<Integer> g2 = new Graf<Integer>();
            for (int comm = 0; comm < Final; comm++) g2.afegirNode(comm);

            int comm_deg = Final;
            for (int comm=0 ; comm<comm_deg ; comm++) {
                HashMap<Integer,Double> m = new HashMap<Integer, Double>();

                int comm_size = comm_nodes[comm].size();
                for (int node=0 ; node<comm_size ; node++) {
                    HashSet<Arc<Integer>> h = g.getNodesAdjacents(comm_nodes[comm].get(node));
                    for (Arc<Integer> a: h) {
                        int neigh        = Graf.getNodeOposat(node, a);
                        int neigh_comm   = renumber[n2c[neigh]];
                        double neigh_weight = a.getPes();

                        if (!m.containsKey(neigh_comm)) {
                            m.put(neigh_comm, neigh_weight);
                        }
                        else {
                            double aux = m.get(neigh_comm);
                            aux += neigh_weight;
                            m.put(neigh_comm, aux);
                        }
                    }
                }

                for (Map.Entry<Integer, Double> entry : m.entrySet()) {
                    Arc<Integer> a = new Arc<Integer>(entry.getValue(), comm, entry.getKey());
                    g2.afegirArc(a);
                }
            }


            //System.out.println(g2);
            return g2;
        }

        public double oneLevel() {
            boolean improvement = false;
            int nb_pass_done = 0;
            double new_mod   = modularity();
            double cur_mod   = new_mod;

            // repetir mentres hi ha una millora en modularitat
            // o hi ha una millora de modularitat mes gran que epsilon
            // o un nombre predefinit de passades ha succeit
            int[] random_order = new int[size];
            for (int i=0 ; i<size ; i++)
                random_order[i]=i;
            for (int i=0 ; i<size-1 ; i++) {
                Random random = new Random();
                int rand_pos = random.nextInt()%(size-i)+i;
                if (rand_pos < 0) rand_pos *= -1;
                int tmp      = random_order[i];
                random_order[i] = random_order[rand_pos];
                random_order[rand_pos] = tmp;
            }

            do {
                cur_mod = new_mod;
                improvement = false;
                nb_pass_done++;

                // Per cada node: elimina un node de la seva comunitat i sinserta a la 'millor' comunitat
                for (int node_tmp=0 ; node_tmp<size ; node_tmp++) {
                    int node = node_tmp;

                    int node_comm = n2c[node];

                    // Calcul de totes les comunitats veines del node actual
                    HashMap<Integer,Double> ncomm   = neighCommunity(node);

                    // Elimina el node de la seva comunitat actual
                    remove(node, node_comm, ncomm.get(node_comm));

                    // Calcula la comunitat mes propera del node
                    // La opcio per defecte per una futura insersio es la comunitat on resideix actualment
                    int best_comm        = node_comm;
                    double best_nblinks     = 0;
                    double best_increase = 0.;
                    for (Map.Entry<Integer, Double> entry : ncomm.entrySet()) {
                        double increase = modularityGain(node, entry.getKey(), entry.getValue());
                        if (increase>best_increase) {
                            best_comm     = entry.getKey();
                            best_nblinks  = entry.getValue();
                            best_increase = increase;
                        }
                    }

                    // Inserta el node a la comunitat mes propera
                    insert(node, best_comm, best_nblinks);

                    if (best_comm!=node_comm)
                        improvement=true;
                }

                new_mod = modularity();
                /*System.out.println("passada número " + nb_pass_done + " de " + nb_pass +
                        " : " + new_mod + " " + cur_mod);*/

            } while (improvement && new_mod-cur_mod>min_modularity && nb_pass_done!=nb_pass);

            return new_mod;
        }

        // Mostra la comunitat a la qual cada node hi pertany
        public void mostrarComunitats() {
            for (int i=0 ; i<size ; i++)
                System.out.println(i + "/" + n2c[i] + "/" + in[i] + "/" + tot[i]);
        }

        // Mostra la particio actual. Les comunitats estan numerades desde 0 fins k-1
        public ArrayList<Pair<Integer, Integer>> mostrarParticioActual() {
            ArrayList<Pair<Integer, Integer>> graphtree = new ArrayList<Pair<Integer, Integer>>();
            int[] renumber = new int[size];
            for (int i = 0; i < size; ++i) renumber[i] = -1;
            for (int node=0 ; node<size ; node++) {
                renumber[n2c[node]]++;
            }

            int Final=0;
            for (int i=0 ; i<size ; i++)
                if (renumber[i]!=-1)
                    renumber[i]=Final++;

            for (int i=0 ; i<size ; i++) {
                graphtree.add(new Pair<Integer, Integer>(i, renumber[n2c[i]]));
                //System.out.println(i + " " + renumber[n2c[i]]);
            }
            return graphtree;
        }

        // Mostra el graf de comunitats
        public void DisplayPartitionToGraph() {
            int[] renumber = new int[size];
            for (int i = 0; i < size; ++i) renumber[i] = -1;
            for (int node=0 ; node<size ; node++) {
                renumber[n2c[node]]++;
            }

            int Final=0;
            for (int i=0 ; i<size ; i++)
                if (renumber[i]!=-1)
                    renumber[i]=Final++;


            for (int i=0 ; i<size ; i++) {
                HashSet<Arc<Integer>> h = g.getNodesAdjacents(i);

                for (Arc<Integer> a : h) {
                    int neigh = Graf.getNodeOposat(i, a);
                    System.out.println(renumber[n2c[i]] + " " + renumber[n2c[neigh]]);
                }
            }
        }

        public Graf<Integer> getGraf() {
            return g;
        }
    }
}
