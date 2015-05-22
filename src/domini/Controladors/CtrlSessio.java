package domini.Controladors;

import persistencia.CtrlPersistencia;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard.gascons
 * Data: 5/22/15
 */
public class CtrlSessio {
    static private CtrlSessio INSTANCE;

    private static String directoriSessio;
    private static String nomSessio;

    private CtrlSessio() {}

    public static CtrlSessio getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CtrlSessio();
        }
        return INSTANCE;
    }

    public static ArrayList<String> getNomsSessions() throws IOException{
        return CtrlPersistencia.getFitxer("./data/sessions.users");
    }

    public boolean setNovaSessio(String usuari) throws IOException{
        ArrayList<String> nomsSessio = CtrlPersistencia.getFitxer("./data/sessions.users");
        for (String nom : nomsSessio) if (nom.equals(usuari)) return false;
        CtrlPersistencia.afegirDada("./data/sessions.users", usuari);
        CtrlPersistencia.crearDirectoriData(usuari);
        directoriSessio = "./data/" + usuari;
        nomSessio = usuari;
        return true;
    }

    public String getDirectoriSessio() {
        return directoriSessio;
    }

    public String getNomSessio() {
        return nomSessio;
    }

    public static void setSessio(String nSessio) {
        nomSessio = nSessio;
        directoriSessio = "./data/" + nSessio;
    }
}
