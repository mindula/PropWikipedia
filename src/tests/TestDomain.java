package tests;

import domini.controladors.CtrlAlgorisme;
import domini.controladors.CtrlCatPag;
import domini.controladors.CtrlWikipedia;
import domini.controladors.graf.grafgenerator.Criteris.Criteri;
import domini.modeldades.ConjuntComunitatWiki;
import domini.modeldades.TipusAlgorisme;
import domini.modeldades.graf.NodeCategoria;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import prop.classescompartides.graf.Arc;
import prop.classescompartides.graf.Comunitat;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 29/04/15
 */
public class TestDomain {

    public static void main(String args[]) throws Exception {


        Scanner sc = new Scanner(System.in).useLocale(Locale.US);
        int opcio = -1;
        while (opcio != 19) {


            System.out.println("Escull una opcio:\n" +
                    "1.  Test GrafWikipedia\n" +
                    "2.  Test GrafParser\n" +
                    "3.  Test GrafCompartit\n" +

                    "4.  Test GrafDirigit\n" +
                    "5.  Test GrafGenerator\n" +
                    "6.  Test ComunitatWiki i OpConjunt\n" +
                    "7.  Test CercaHistorial\n" +
                    "8.  Test Categoria\n" +
                    "9.  Test Pagina\n" +
                    "10. Test Navegacio\n" +
                    "11. Test CercarComunitats\n" +
                    "12. Test InformacioCjtComunitats\n" +
                    "13. Test InfoCerca\n" +
                    "14. Test Algoritme Louvain\n" +
                    "15. Test Jaro-Winkler\n" +
                    "16. Test Persistencia\n" +
                    "17. Dibuixar Comunitats\n" +
                    "18. Provar relacions categories del CtrlCatPag\n" +
                    "19. Finalitzar Test\n");
            opcio = sc.nextInt();

            switch (opcio) {
                case 1:
                    TestGrafWikipedia.main(null);
                    break;
                case 2:
                    TestGrafParser.main(null);
                    break;
                case 3:
                    TestGrafCompartit.main(null);
                    break;
                case 4:
                    TestGrafDirigit.main(null);
                    break;
                case 5:
                    TestGrafGenerator.main(null);
                    break;
                case 6:
                    //TestComunitatOpConjunts.main(null);
                    break;
                case 7:
                    TestCercaHistorial.main(null);
                    break;
                case 8:
                    TestNodeCategoria.main(null);
                    break;
                case 9:
                    TestNodePagina.main(null);
                    break;
                case 10:
                    TestNavegacio.main(null);
                    break;
                case 11:
                    System.out.println("Escull algorisme: Clique, Girvan o Louvain. " +
                                       "Escriu el seu nom tal com s'indica. Si no, s'utilitzara per defecte el Clique.");
                    String alg = sc.next();
                    TipusAlgorisme algorisme;
                    if(alg.toLowerCase().equals("louvain"))
                        algorisme = TipusAlgorisme.LOUVAIN;
                    else if(alg.toLowerCase().equals("girvan"))
                        algorisme = TipusAlgorisme.GIRVAN;
                    else algorisme = TipusAlgorisme.CLIQUE;

                    System.out.println("Algoritme : "+algorisme.name());
                    System.out.println("Escriu el parametre del Algorisme.");
                    double par1 = sc.nextDouble();


                    //Obtencio de criteris


                    ArrayList<Criteri> criteris = TestGrafGenerator.getCriteris(sc);

                    // Fi de la obtencio de criteris

                    CtrlAlgorisme c = new CtrlAlgorisme(CtrlWikipedia.getInstance().getGrafWiki(), algorisme, par1, criteris);
                    c.generarGraf();
                    ConjuntComunitatWiki comunitats = c.cercarComunitats();

                    //System.out.println(comunitats.getInformacio().toString());

                    CtrlWikipedia.getInstance().setConjuntsGenerats(comunitats);
                    System.out.println("Comunitats trobades:");
                    System.out.println(comunitats.getCjtComunitats());


                    break;
                case 12:
                    TestInformacioCjtComunitats.main(null);
                    break;
                case 13:
                    TestInfoCerca.main(null);
                    break;
                case 14:
                    TestAlgorismeLouvain.main(null);
                    break;
                case 15:
                    TestJaroWinkler.main(null);
                    break;
                case 16:
                    TestPersistencia.main(null);
                    break;
                case 17:
                    String[] color = {"rgb(180,180,180)", "rgb(255,0,0)", "rgb(0,255,0)", "rgb(0,0,255)", "rgb(255,255,0)", "rgb(0,255,255)", "rgb(255,0,255)", "rgb(94,28,13)", "rgb(241,148,108)", "rgb(255,116,21)", "rgb(7,47,122)", "rgb(64,173,38)", "rgb(97,119,171)", "rgb(222,29,42)", "rgb(69,0,68)", "rgb(90,103,39)", "rgb(164,131,196)", "rgb(187,255,19)", "rgb(207,3,124)", "rgb(0,148,189)", "rgb(255,142,0)", "rgb(140,253,153)"};

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
                    for(Comunitat<NodeCategoria> com: CtrlWikipedia.getInstance().getConjuntsGenerats().getCjtComunitats().getComunitats()){
                        for (NodeCategoria cat: com.getNodes()){
                            if (com.mida() > 1) {
                                graf.getNode(cat.getNom()).addAttribute("ui.style", "fill-color:" + color[com.getId() % 22] + ";");
                            }
                            else {
                                graf.getNode(cat.getNom()).addAttribute("ui.style", "fill-color: black;");
                                graf.getNode(cat.getNom()).addAttribute("ui.style", "size: 3px;");
                            }

                        }

                    }

                    graf.display();
                    break;
                case 18:
                    System.out.println("Obtenir les superCategories");
                    System.out.println("Esciure el nom de la categoria");
                    String s = sc.next();
                    System.out.println(CtrlCatPag.getInstance().getSuperCategories(s));
                    System.out.println("Obtenir les subCategories");
                    System.out.println("Esciure el nom de la categoria");
                    s = sc.next();
                    System.out.println(CtrlCatPag.getInstance().getSubCategories(s));
                    System.out.println("Obtenir les pagines");
                    System.out.println("Esciure el nom de la categoria");
                    s = sc.next();
                    System.out.println(CtrlCatPag.getInstance().getPagines(s));
                    System.out.println("Obtenir les temaCategories");
                    System.out.println("Esciure el nom de la categoria");
                    s = sc.next();
                    System.out.println(CtrlCatPag.getInstance().getCategoriesTema(s));
                    break;
            }


        }
    }
}
