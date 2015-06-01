package domini.modeldades;

import domini.modeldades.graf.NodeCategoria;
import prop.classescompartides.graf.ConjuntComunitats;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 22/05/15
 */
public class ConjuntComunitatWiki implements Serializable{
    private ConjuntComunitats<NodeCategoria> cjtComunitats;

    private HashMap<Integer,String> noms;
    private HashMap<String,Integer> ids;
    private HashMap<Integer,String> descripcions;


    public ConjuntComunitatWiki(){
        cjtComunitats = new ConjuntComunitats<NodeCategoria>();
        noms = new HashMap<>();
        descripcions = new HashMap<>();
        ids = new HashMap<>();
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

    public void eliminarInfoComunitat(int id){
        ids.remove(getNom(id));
        noms.remove(id);
        descripcions.remove(id);
    }
}
