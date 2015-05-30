package domini.modeldades;

import domini.modeldades.graf.NodeCategoria;
import prop.classescompartides.graf.ConjuntComunitats;

import java.util.Collection;
import java.util.HashMap;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 22/05/15
 */
public class ConjuntComunitatWiki {
    private InformacioCjtComunitats informacio;
    private ConjuntComunitats<NodeCategoria> cjtComunitats;

    private HashMap<Integer,String> noms;
    private HashMap<String,Integer> ids;
    private HashMap<Integer,String> descripcions;


    public ConjuntComunitatWiki(){
        cjtComunitats = new ConjuntComunitats<NodeCategoria>();
        noms = new HashMap<Integer,String>();
        descripcions = new HashMap<Integer,String>();
        ids = new HashMap<>();
    }


    public InformacioCjtComunitats getInformacio() {
        return informacio;
    }

    public void setInformacio(InformacioCjtComunitats informacio) {
        this.informacio = informacio;
    }

    public ConjuntComunitats<NodeCategoria> getCjtComunitats() {
        return cjtComunitats;
    }

    public void setCjtComunitats(ConjuntComunitats<NodeCategoria> cjtComunitats) {
        this.cjtComunitats = cjtComunitats;
    }

    public Collection<String> getNoms() {
        return noms.values();
    }

    public String getNom(Integer id){
        return noms.get(id);
    }

    public void setNom(Integer id, String nounom){
        noms.put(id, nounom);
    }

    public void setId(Integer id, String nom) {
        ids.put(nom, id);
    }

    public Integer getId(String nom) {
        return ids.get(nom);
    }

    public Collection<String> getDescripcions() {
        return descripcions.values();
    }

    public String getDescripcio(Integer id){
        return descripcions.get(id);
    }

    public void setDescripcio(Integer id, String descripcio){
        descripcions.put(id,descripcio);
    }
}
