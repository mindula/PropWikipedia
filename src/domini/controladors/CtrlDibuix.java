package domini.controladors;

import domini.modeldades.graf.GrafWikipedia;
import domini.modeldades.graf.NodeCategoria;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import prop.classescompartides.graf.Arc;
import prop.classescompartides.graf.Comunitat;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 29/05/15
 */
public class CtrlDibuix {
    private String[] color = {"red", "blue", "yellow", "brown", "purple"};

    public void DibuixarGraf(){
        Graph graf = new SingleGraph("Graf");
        graf.setStrict(false);
        for(NodeCategoria cat: CtrlWikipedia.getInstance().getGrafWiki().getCategories()){
            graf.addNode(cat.getNom());
            graf.getNode(cat.getNom()).addAttribute("ui.style", "fill-color: rgb(0,100,255);");
        }
        int i = 0;
        for (Arc<NodeCategoria> arc:  CtrlWikipedia.getInstance().getGrafWiki().getArcs())  {
            graf.addEdge(String.valueOf(i),arc.getNodeA().getNom(),arc.getNodeB().getNom());
            ++i;
        }
        for(Comunitat<NodeCategoria> com: CtrlWikipedia.getInstance().getConjuntsGenerats().getComunitats()){
            for (NodeCategoria cat: com.getNodes()){
                graf.getNode(cat.getNom()).addAttribute("ui.style", "fill-color:" + color[com.getId()] + ";");
            }
        }

        graf.display();
    }








}
