package domini.Controladors;

import domini.ComunitatWiki;
import domini.ConjuntComunitatWiki;
import graf.GrafWikipedia;
import graf.NodeCategoria;
import graf.NodeWiki;
import graf.OperacionsConjunts;
import prop.classescompartides.graf.Comunitat;
import prop.classescompartides.graf.ConjuntComunitats;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 21/05/15
 */
public class CtrlComunitat {
    public ConjuntComunitatWiki conjunt;
    public GrafWikipedia graf;

    public CtrlComunitat(){
        this.conjunt = new ConjuntComunitatWiki();
        this.graf = CtrlWikipedia.getInstance().getGrafWiki();
    }

    /**
     * Cas d'us Crear tema.
     */
    public void creaComunitat(String nom){
        ComunitatWiki c = new ComunitatWiki();
        c.setNom(nom);
        conjunt.afegirComunitat(c);
    }

    /**
     * Cas d'us Modificar tema. Canviar nom.
     */
    public void modNomComunitat(int id, String nomnou) throws  Exception{
        ComunitatWiki c = (ComunitatWiki) conjunt.getComunitats().get(id);
        c.setNom(nomnou);
    }

    /**
     * Cas d'us Modificar tema. Afegir Categoria.
     */
    public void afegirCatComunitat (int idComunitat, String nomCategoria) throws Exception {
        NodeCategoria categoria = graf.getNodeCat(nomCategoria);
        conjunt.getComunitat(idComunitat).afegirNode(categoria);
    }

    /**
     * Cas d'us Modificar tema. Eliminar Categoria.
     */
    public void eliminarCatComunitat(int idComunitat, String nomCategoria) throws Exception {
        NodeCategoria categoria = graf.getNodeCat(nomCategoria);
        conjunt.getComunitat(idComunitat).eliminarNode(categoria);
    }

    /**
     * Cas d'us Consultar tema.
     */
    public ConjuntComunitatWiki getConjunt() {
        return conjunt;
    }

    /**
     * Cas d'us Operació entre temes. Unio.
     */
    public Comunitat<NodeCategoria> unio (int idComunitat1, int idComunitat2) throws Exception{
        return OperacionsConjunts.unio(conjunt.getComunitat(idComunitat1), conjunt.getComunitat(idComunitat2));
    }

    /**
     * Cas d'us Operació entre temes. Interseccio.
     */
    public Comunitat<NodeCategoria> interseccio (int idComunitat1, int idComunitat2) throws Exception{
        return OperacionsConjunts.interseccio(conjunt.getComunitat(idComunitat1), conjunt.getComunitat(idComunitat2));
    }

    /**
     * Cas d'us Operació entre temes. Diferencia.
     */
    public Comunitat<NodeCategoria> diferencia (int idComunitat1, int idComunitat2) throws Exception{
        return OperacionsConjunts.diferencia(conjunt.getComunitat(idComunitat1), conjunt.getComunitat(idComunitat2));
    }

    /**
     * Cas d'us Eliminar comunitat.
     */
    public void eliminarComunitat (int idComunitat) throws Exception {
        conjunt.eliminarComunitat(conjunt.getComunitat(idComunitat));
    }

    //TODO: Metode OPCIONAL Assignar color a un tema
}
