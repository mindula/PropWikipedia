package domini.controladors;

import domini.modeldades.graf.GrafWikipedia;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 21/05/15
 */
public class CtrlCatPag {
    private GrafWikipedia grafWiki;

    //TODO aixo no es crida mai
    private CtrlCatPag(){
        grafWiki = CtrlWikipedia.getInstance().getGrafWiki();
    }

    /**
     * Cas d'us Modificar categoria
     */
    public void ModCat(String nomantic, String nounom){
        grafWiki.getNodeCat(nomantic).setNom(nounom);
    }

    /**
     * Cas d'us Modificar pagina
     */
    public void ModPag(String nomantic, String nounom){
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
