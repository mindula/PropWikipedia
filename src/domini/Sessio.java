package domini;

import java.util.Date;

//S'ha de tenir en compte que aquesta classe és un singleton
public class Sessio {
    private String nom;
    private Date dataCreacio;
    private int nombreCategories, nombrePagines;

    public Sessio (String nom, Date dataCreacio, int nombreCategories, int nombrePagines) {
        this.nom = nom;
        this.dataCreacio = dataCreacio;
        this.nombreCategories = nombreCategories;
        this.nombrePagines = nombrePagines;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDataCreacio() {
        return dataCreacio;
    }

    public void setDataCreacio(Date dataCreacio) {
        this.dataCreacio = dataCreacio;
    }

    public int getNombreCategories() {
        return nombreCategories;
    }

    public void setNombreCategories(int nombreCategories) {
        this.nombreCategories = nombreCategories;
    }

    public int getNombrePagines() {
        return nombrePagines;
    }

    public void setNombrePagines(int nombrePagines) {
        this.nombrePagines = nombrePagines;
    }
}
