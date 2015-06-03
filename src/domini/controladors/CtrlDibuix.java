package domini.controladors;

import domini.modeldades.graf.NodeCategoria;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;
import prop.classescompartides.graf.Arc;
import prop.classescompartides.graf.Comunitat;

import java.util.Random;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 29/05/15
 */
public class CtrlDibuix {
    private String[] color = {"rgb(180,180,180)", "rgb(255,0,0)", "rgb(0,255,0)", "rgb(0,0,255)", "rgb(255,255,0)", "rgb(0,255,255)", "rgb(255,0,255)", "rgb(94,28,13)", "rgb(241,148,108)", "rgb(255,116,21)", "rgb(7,47,122)", "rgb(64,173,38)", "rgb(97,119,171)", "rgb(222,29,42)", "rgb(69,0,68)", "rgb(90,103,39)", "rgb(164,131,196)", "rgb(187,255,19)", "rgb(207,3,124)", "rgb(0,148,189)", "rgb(255,142,0)", "rgb(140,253,153)"};

    /**
     * Es dibuixa el graf que tenim en el programa
     */
    public void DibuixarGraf() {
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        Graph graf = new SingleGraph("Graf");
        graf.setStrict(false);
        for(NodeCategoria cat: CtrlWikipedia.getInstance().getGrafWiki().getCategories()){
            graf.addNode(cat.getNom());
        }
        int i = 0;
        for (Arc<NodeCategoria> arc:  CtrlWikipedia.getInstance().getGrafWiki().getArcs())  {
            graf.addEdge(String.valueOf(i),arc.getNodeA().getNom(),arc.getNodeB().getNom());
            ++i;
        }

        Viewer viewer =graf.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.CLOSE_VIEWER);
    }

    /**
     * Es dibuixa el graf que tenim en el programa diferenciant per colors el diferents nodes segons la comunitat a la
     * que es pertany
     */
    public void DibuixarGrafAmbComunitats(){
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        Graph graf = new SingleGraph("Graf");
        graf.setStrict(false);
        for(NodeCategoria cat: CtrlWikipedia.getInstance().getGrafWiki().getCategories()){
            graf.addNode(cat.getNom());
            graf.getNode(cat.getNom()).addAttribute("ui.style", "size: 15px;");
        }
        graf.addAttribute("ui.stylesheet", "edge {" +
                "    shape: line;" +
                "    fill-color: #222;" +
                "    z-index: 3;" +
                "}");
        int i = 0;
        for (Arc<NodeCategoria> arc:  CtrlWikipedia.getInstance().getGrafWiki().getArcs())  {
            graf.addEdge(String.valueOf(i), arc.getNodeA().getNom(), arc.getNodeB().getNom());
            ++i;
        }
        graf.addAttribute("ui.quality");
        graf.addAttribute("ui.antialias");
        Random rand = new Random();
        double r, g, b;
        boolean b1 = false;
        for(Comunitat<NodeCategoria> com: CtrlWikipedia.getInstance().getConjuntsGenerats().getCjtComunitats().getComunitats()){
            r = rand.nextDouble()*255;
            g = rand.nextDouble()*255;
            b = rand.nextDouble()*255;
            for (NodeCategoria cat: com.getNodes()){
                if (com.mida() > 1) {
                        graf.getNode(cat.getNom()).addAttribute("ui.style", "fill-color:rgb("+ (int)r +","+(int)g+","+(int)b+");");
                }
                else {
                    //Comprovem que el node no formi part d'una altre comunitat on no estigui nomes ell.
                    b1 = false;
                    for(Comunitat<NodeCategoria> c : CtrlWikipedia.getInstance().getConjuntsGenerats().getCjtComunitats().getComunitats()){
                        if (c.getId() >= com.getId()) break;
                        if (c.teNode(cat)) b1 = true;
                    }
                    if (b1) break;
                    graf.getNode(cat.getNom()).addAttribute("ui.style", "fill-color: black;");
                    graf.getNode(cat.getNom()).addAttribute("ui.style", "size: 3px;");
                }

            }
        }

        Viewer viewer = graf.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.CLOSE_VIEWER);
    }
}
