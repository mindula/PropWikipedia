package domini.controladors;

import domini.modeldades.graf.GrafWikipedia;
import domini.modeldades.graf.NodeCategoria;
import domini.modeldades.graf.NodePagina;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 21/05/15
 */
public class CtrlCatPag {
    private static CtrlCatPag INSTANCE;

    private GrafWikipedia grafWiki;

    public static CtrlCatPag getInstance() {
        if(INSTANCE == null) INSTANCE = new CtrlCatPag();
        return INSTANCE;
    }

    private CtrlCatPag(){
        grafWiki = CtrlWikipedia.getInstance().getGrafWiki();
    }

    public NodeCategoria obtenirCategoria(String nom){
        return grafWiki.getNodeCat(nom);
    }

    public NodePagina obtenirPagina(String nom){
        return grafWiki.getNodePag(nom);
    }

    public boolean existeixCategoria(String nom) { return grafWiki.existeixNodeCat(nom);}

    public boolean existeixPagina(String nom) {return grafWiki.existeixNodePag(nom);}


    /**
     * Cas d'us Modificar categoria
     */
    public void ModificarNomCat(String nomantic, String nounom){
        grafWiki.getNodeCat(nomantic).setNom(nounom);
    }

    /**
     * Cas d'us Modificar pagina
     */
    public void ModificarNomPag(String nomantic, String nounom){
        grafWiki.getNodePag(nomantic).setNom(nounom);
    }

    /**
     * Cas d'us Relacionar categories i pagines. Crea un arc CsubC
     */
    public void RelCsubC(String superC, String subC){
        grafWiki.afegirArcCsubC(grafWiki.getNodeCat(superC),grafWiki.getNodeCat(subC));
    }

    /**
     * Cas d'us Relacionar categories i pagines. Crea un arc CsupC
     */
    public void RelCsupC(String superC, String subC){
        grafWiki.afegirArcCsupC(grafWiki.getNodeCat(subC),grafWiki.getNodeCat(superC));
    }

    /**
     * Cas d'us Relacionar categories i pagines. Crea un arc PC
     */
    public void RelPC(String pag, String cat){
        grafWiki.afegirArcPC(grafWiki.getNodePag(pag),grafWiki.getNodeCat(cat));
    }

    /**
     * Cas d'us Relacionar categories i pagines. Eliminar arc
     */
    public void esborrarArc(String nom1, String nom2){
        grafWiki.eliminarArc(grafWiki.getArcEntre(grafWiki.getNodeCat(nom1),grafWiki.getNodeCat(nom2)));
    }

    /**
     * Cas d'us Relacionar categories i pagines. Eliminar arc PC
     */
    public void esborrarArcPC(String cat, String pag){
        grafWiki.eliminarArcPC(cat, pag, grafWiki.getNodeCat(cat),grafWiki.getNodePag(pag));
    }


}
