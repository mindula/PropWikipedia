package tests;


import domini.controladors.CtrlAlgorisme;
import domini.controladors.CtrlWikipedia;
import domini.controladors.graf.grafgenerator.Criteris.Criteri;
import domini.modeldades.ConjuntComunitatWiki;
import domini.modeldades.TipusAlgorisme;
import domini.modeldades.graf.NodeCategoria;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import prop.classescompartides.graf.Arc;
import prop.classescompartides.graf.ConjuntComunitats;
import prop.classescompartides.graf.Graf;

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
        while (opcio != 18) {


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
                    "18. Finalitzar Test\n");
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
                    ConjuntComunitatWiki comunitats = c.cercarComunitats();

                    System.out.println(comunitats.getInformacio().toString());

                    CtrlWikipedia.getInstance().setConjuntsGenerats(comunitats.getCjtComunitats());
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

                    Graph gr = new SingleGraph("prova");
                    gr.setStrict(false);
                    for(NodeCategoria cat: CtrlWikipedia.getInstance().getGrafWiki().getCategories()){
                        gr.addNode(cat.getNom());
                    }
                    int i = 0;
                    for (Arc<NodeCategoria> arc:  CtrlWikipedia.getInstance().getGrafWiki().getArcs())  {
                            gr.addEdge(String.valueOf(i),arc.getNodeA().getNom(),arc.getNodeB().getNom());
                        ++i;
                    }
                    gr.display();
                    break;
            }


        }
    }
}
