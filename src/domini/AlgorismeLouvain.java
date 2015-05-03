package domini;

import org.grupwiki.graf.*;

import java.util.*;

/**
 * Grup 3: Wikipedia
 * Usuari: aleix.paris, ricard.gascons
 * Data: 23/4/15
 */
public class AlgorismeLouvain<T> extends Algoritme<T> {
    private ArrayList<Pair<Integer, Integer>> graphTree;
    private Graf<Integer> grafIntern;
    private int lastLevel = 0;
    private double minModularity;
    private int nPassades;
    private HashMap<Integer, T> traduccioIntegerT;


    @Override
    public ConjuntComunitats<T> cercarComunitats(Graf<T> grafOriginal, int nPassades, int nul){
        graphTree = new ArrayList<Pair<Integer, Integer>>();
        this.nPassades = nPassades;
        this.minModularity = 0.000001;
        traduccioIntegerT = new HashMap<Integer, T>();
        grafIntern = convertirGraf(grafOriginal);
        metodeLouvain();
        System.out.println(imprimirSolucio());
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

        System.out.println("xarxa : "
                + c.getGraf().ordre() + " nodes, "
                + c.getGraf().mida() + " arestes, ");

        double new_mod = c.oneLevel();

        System.out.println("la modularitat ha variat de " + mod + " a " + new_mod);

        graphTree.addAll(c.mostrarParticioActual());

        Graf<Integer> g = c.convertirParticioAGraf();

        while(new_mod-mod>minModularity) {
            mod=new_mod;
            ComunitatLouvain c1 = new ComunitatLouvain(g, -1, minModularity);

            System.out.println ( "\nxarxa : "
                    + c1.getGraf().ordre() + " nodes, "
                    + c1.getGraf().mida() + " arestes, ");

            new_mod = c1.oneLevel();

            System.out.println("la modularitat ha variat de " + mod + " to " + new_mod);


            graphTree.addAll(c1.mostrarParticioActual());

            g = c1.convertirParticioAGraf();

            System.out.println(g);

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
        // si val -1, es fan tantes passes com es vegi necessari per incrementar la modularitat
        private int nb_pass;

        // a new pass is computed if the last one has generated an increase
        // greater than min_modularity
        // if 0. even a minor increase is enough to go for one more pass
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

        // remove the node from its current community with which it has dnodecomm links
        public void remove(int node, int comm, double dnodecomm) {
            if (!(node>=0 && node<size))
                throw new RuntimeException("No s'ha complert que node>=0 && node<size");

            tot[comm] -= weightedDegree(node);
            in[comm]  -= 2*dnodecomm + numSelfLoops(node);
            n2c[node]  = -1;
        }

        // insert the node in comm with which it shares dnodecomm links
        public void insert(int node, int comm, double dnodecomm) {
            if (!(node>=0 && node<size))
                throw new RuntimeException("No s'ha complert que node>=0 && node<size");

            tot[comm] += weightedDegree(node);
            in[comm]  += 2*dnodecomm + numSelfLoops(node);
            n2c[node]=comm;
        }

        // compute the gain of modularity if node where inserted in comm
        // given that node has dnodecomm links to comm.  The formula is:
        // [(In(comm)+2d(node,comm))/2m - ((tot(comm)+deg(node))/2m)^2]-
        // [In(comm)/2m - (tot(comm)/2m)^2 - (deg(node)/2m)^2]
        // where In(comm)    = number of half-links strictly inside comm
        //       Tot(comm)   = number of half-links inside or outside comm (sum(degrees))
        //       d(node,com) = number of links from node to comm
        //       deg(node)   = node degree
        //       m           = number of links
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

        // compute the set of neighboring communities of node
        // for each community, gives the number of links from node to comm
        public HashMap<Integer,Double> neighCommunity(int node) {
            HashMap<Integer,Double> res = new HashMap<Integer, Double>();
            HashSet<Arc<Integer>> h = g.getNodesAdjacents(node);

            res.put(n2c[node],0.0);
            //make_pair(links, weights);
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

            // Compute communities
            int[][] comm_nodes = new int[Final][Final];
            int[] size_comm_nodes = new int[Final];
            for (int i = 0; i < Final; ++i) size_comm_nodes[i] = 0;
            for (int node=0 ; node<size ; node++) {
                comm_nodes[renumber[n2c[node]]][size_comm_nodes[renumber[n2c[node]]]] = node;
                ++size_comm_nodes[renumber[n2c[node]]];
            }

            Graf<Integer> g2 = new Graf<Integer>();
            for (int comm = 0; comm < Final; comm++) g2.afegirNode(comm);

            int comm_deg = Final;
            for (int comm=0 ; comm<comm_deg ; comm++) {
                HashMap<Integer,Double> m = new HashMap<Integer, Double>();

                int comm_size = size_comm_nodes[comm];
                for (int node=0 ; node<comm_size ; node++) {
                    HashSet<Arc<Integer>> h = g.getNodesAdjacents(comm_nodes[comm][node]);
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


            System.out.println(g2);
            return g2;
        }

        public double oneLevel() {
            boolean improvement = false;
            int nb_pass_done = 0;
            double new_mod   = modularity();
            double cur_mod   = new_mod;

            // repeat while
            //   there is an improvement of modularity
            //   or there is an improvement of modularity greater than a given epsilon
            //   or a predefined number of pass have been done

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

                // for each node: remove the node from its community and insert it in the best community
                for (int node_tmp=0 ; node_tmp<size ; node_tmp++) {
                    int node = node_tmp;

                    int node_comm = n2c[node];

                    // computation of all neighboring communities of current node
                    HashMap<Integer,Double> ncomm   = neighCommunity(node);

                    // remove node from its current community
                    remove(node, node_comm, ncomm.get(node_comm));

                    // compute the nearest community for node
                    // default choice for future insertion is the former community
                    int best_comm        = node_comm;
                    double best_nblinks     = 0;//ncomm.find(node_comm)->second;
                    double best_increase = 0.;//modularity_gain(node, best_comm, best_nblinks);
                    for (Map.Entry<Integer, Double> entry : ncomm.entrySet()) {
                        double increase = modularityGain(node, entry.getKey(), entry.getValue());
                        if (increase>best_increase) {
                            best_comm     = entry.getKey();
                            best_nblinks  = entry.getValue();
                            best_increase = increase;
                        }
                    }

                    // insert node in the nearest community
                    insert(node, best_comm, best_nblinks);

                    if (best_comm!=node_comm)
                        improvement=true;
                }

                new_mod = modularity();
                System.out.println("passada número " + nb_pass_done + " de " + nb_pass +
                        " : " + new_mod + " " + cur_mod);

            } while (improvement && new_mod-cur_mod>min_modularity && nb_pass_done!=nb_pass);

            return new_mod;
        }

        // shows the community of each node
        public void mostrarComunitats() {
            for (int i=0 ; i<size ; i++)
                System.out.println(i + "/" + n2c[i] + "/" + in[i] + "/" + tot[i]);
        }

        // shows the current partition (with communities renumbered from 0 to k-1)
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
                System.out.println(i + " " + renumber[n2c[i]]);
            }
            return graphtree;
        }

        // displays the graph of communities as computed by one_level
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
