package domini;

import graf.GrafWikipedia;
import org.grupwiki.graf.Arc;
import java.util.*;
import java.util.regex.Pattern;

public abstract class Cerca {

    static Pattern patro = Pattern.compile(Pattern.quote(a.getNodeB().getNom()),
            Pattern.CASE_INSENSITIVE);
    public static ArrayList<NodeWiki> cercarCategoria(GrafWikipedia<NodeWiki> g, String query, Date data) {
        //Encapsular query, data i ArrayList dins un objecte?
        ArrayList<NodeWiki> res = BFS(g, query);
        return res;
    }

    private static ArrayList<NodeWiki> BFS(GrafWikipedia<NodeWiki> g, String query) {
        ArrayList<NodeWiki> res = new ArrayList<NodeWiki>();
        Queue<NodeWiki> q = new LinkedList<NodeWiki>();
        Queue<NodeWiki> visited = new LinkedList<NodeWiki>();       //hi ha formes més eficients de fer-ho
        q.add(getRandomNode(g.getNodes()));
        while(!q.isEmpty()) {
            visited.add(q.peek());
            NodeWiki n = q.remove();
            Set<Arc<NodeWiki>> adjacents = g.getNodesAdjacents(n);
            for (Arc<NodeWiki> a : adjacents) {
                if (!visited.contains(a.getNodeB())) {
                    boolean match = Pattern.compile(Pattern.quote(a.getNodeB().getNom()),
                            Pattern.CASE_INSENSITIVE).matcher(query).find();
                    if (match) res.add(a.getNodeB());
                    q.add(a.getNodeB());
                    visited.add(a.getNodeB());
                }
            }
        }
        return res;
    }

    private static Queue<NodeWiki> cercaGraf (GrafWikipedia<NodeWiki> g, String query) {
        PriorityQueue<NodeWiki> pq = new PriorityQueue<NodeWiki>(g.ordre(), new Comparator<NodeWiki>() {
            @Override
            public int compare(NodeWiki nodeWiki, NodeWiki t1) {
                return nodeWiki.getNom().in
                return 0;
            }
        });
    }

    private static NodeWiki getRandomNode(Set<NodeWiki> s) {
        int size = s.size();
        int item = new Random().nextInt(size);
        int i = 0;
        for(NodeWiki node : s) {
            if (i == item)
                return node;
            ++i;
        }
        return null;
    }
}