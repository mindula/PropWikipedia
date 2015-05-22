package domini.Controladors;

import persistencia.CtrlPersistencia;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard
 * Data: 5/22/15
 */
public class CtrlSessio {
    private static String directoriSessio;
    private static String nomSessio;

    public static ArrayList<String> getNomsSessions() throws IOException{
        return CtrlPersistencia.getFitxer("./data/sessions.users");
    }

    public static boolean setNouNomSessio(String usuari) throws IOException{
        ArrayList<String> nomsSessio = CtrlPersistencia.getFitxer("./data/sessions.users");
        for (String nom : nomsSessio) if (nom.equals(usuari)) return false;
        CtrlPersistencia.afegirDada("./data/sessions.users", usuari);
        CtrlPersistencia.crearDirectoriData(usuari);
        directoriSessio = "./data/" + usuari;
        nomSessio = usuari;
        return true;
    }

    public String getDirectoriSessio() {
        if (directoriSessio.isEmpty()) return "#";
        return directoriSessio;
    }

    public String getNomSessio() {
        if (nomSessio.isEmpty()) return "#";
        return nomSessio;
    }
}
