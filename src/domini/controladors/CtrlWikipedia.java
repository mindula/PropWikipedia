package domini.controladors;

import domini.modeldades.ConjuntComunitatWiki;
import domini.modeldades.InfoCerca;
import domini.modeldades.InformacioCjtComunitats;
import domini.modeldades.graf.GrafWikipedia;
import domini.modeldades.graf.NodeCategoria;
import domini.modeldades.graf.NodePagina;
import persistencia.GrafParser;
import prop.classescompartides.graf.Graf;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 19/5/15
 */

/**
 * CtrlWikipedia de la Wikipedia // TODO: superdefinicion
 */

public class CtrlWikipedia implements Serializable{
    private static CtrlWikipedia INSTANCE;

    private String dataCreacio;
    private GrafWikipedia grafWiki;
    private Graf<NodeCategoria> grafAlgoritme;
    private ConjuntComunitatWiki conjuntsGenerats;
    private ArrayList<InfoCerca> llistatCerques;
    private ArrayList<InformacioCjtComunitats> informacioExecucions;

    private CtrlWikipedia() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dataCreacio = new Date();
        this.dataCreacio = dateFormat.format(dataCreacio);
        grafWiki = new GrafWikipedia();
        conjuntsGenerats = new ConjuntComunitatWiki();
        llistatCerques = new ArrayList<InfoCerca>();
        informacioExecucions = new ArrayList<>();
    }

    /**
     * /**
     * Retorna una instancia de CtrlWikipedia
     * @return una instancia de CtrlWikipedia
     */
    public static CtrlWikipedia getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CtrlWikipedia();
        }
        return INSTANCE;
    }

    public static void setInstance(CtrlWikipedia instance) {
        CtrlWikipedia.INSTANCE = instance;
    }

    /**
     * Retorna la data de creació de la sessió
     * @return la data de creació de la sessió
     */
    public String getDataCreacio() {
        return dataCreacio;
    }

    public void afegirInfoExecucio (InformacioCjtComunitats informacioCjtComunitats) {
        informacioExecucions.add(informacioCjtComunitats);
    }

    public String getInfoExecucio (int i) {
        InformacioCjtComunitats info = informacioExecucions.get(i);
        String res = String.valueOf(info.getTempsComunitats()+info.getTempsgenerar()) + "-" + String.valueOf(info.getNombreComunitats()) + "-"
                + String.valueOf(info.getAlgoritme()) + "-" + String.valueOf(info.getCriteri()) + "-" +
                String.valueOf(info.getMitjanaNodesPerComunitat());
        return res;
    }

    public int getNombreExecucions() {
        return informacioExecucions.size();
    }


    /**
     * Retorna el Graf de la Wikipedia
     * @return el graf de la Wikipedia
     */
    public GrafWikipedia getGrafWiki() {
        return grafWiki;
    }

    public void setGrafWiki(GrafWikipedia grafWiki) {
        this.grafWiki = grafWiki;
    }

    /**
     * Retorna el graf de categories pels algorismes
     * @return el graf de categories pels algorismes
     */
    //TODO: probablement ja no fa falta aqui, crec que te mes sentit a CtrlAlgorisme
    public Graf<NodeCategoria> getGrafAlgoritme() {
        return grafAlgoritme;
    }

    public void setGrafAlgoritme(Graf<NodeCategoria> grafAlgoritme) {
        this.grafAlgoritme = grafAlgoritme;
    }

    /**
     * Cas d'us Importar Fitxer
     */
    public void getGrafWikiFromFile(String path) throws IOException {


        GrafParser.parse(path, grafWiki);

        System.out.println(grafWiki);
        //TODO: faltaria guardar el nou graf (o subgraf) importat en el nostre format. No se si s'hauria de fer aqui o a CtrlSessio
    }

    /**
     * Cas d'us Afegir Categoria
     */
    public void afegirCat(String nom){
        NodeCategoria nodeC = new NodeCategoria(nom);
        grafWiki.afegirCategoria(nodeC);
    }

    /**
     * Cas d'us Afegir Pagina
     */
    public void afegirPag(String nom){
        NodePagina nodeC = new NodePagina(nom);
        grafWiki.afegirPagina(nodeC);
    }

    /**
     * Cas d'us Eliminar Categoria
     */
    public void elimCat(String nom){
        grafWiki.eliminarCategoria(grafWiki.getNodeCat(nom));
    }

    /**
     * Cas d'us Eliminar Pagina
     */
    public void elimPag(String nom){
        grafWiki.eliminarPagina(grafWiki.getNodePag(nom));
    }


    public ConjuntComunitatWiki getConjuntsGenerats() {
        return conjuntsGenerats;
    }

    public void setConjuntsGenerats(ConjuntComunitatWiki conjuntsGenerats) {
        this.conjuntsGenerats = conjuntsGenerats;
    }

    public ArrayList<String> getNomsCategories(){
        ArrayList<NodeCategoria> cats = grafWiki.getCategories();
        ArrayList<String> nomCats = new ArrayList<>();
        for (NodeCategoria cat : cats) {
            nomCats.add(cat.getNom());
        }
        return nomCats;
    }

    public ArrayList<String> getNomsPagines(){
        ArrayList<NodePagina> pags = grafWiki.getPagines();
        ArrayList<String> nomPags = new ArrayList<>();
        for (NodePagina pag : pags) {
            nomPags.add(pag.getNom());
        }
        return nomPags;
    }

    public ArrayList<InfoCerca> getLlistatCerques() {
        return llistatCerques;
    }

    @Override
    public String toString() {
        return "CtrlWikipedia{" +'\n' +
                "dataCreacio='" + dataCreacio + '\n' +
                ", grafWiki=" + grafWiki +'\n' +
                ", grafAlgoritme=" + grafAlgoritme +'\n' +
                ", conjuntsGenerats=" + conjuntsGenerats +'\n' +
                '}';
    }
}
