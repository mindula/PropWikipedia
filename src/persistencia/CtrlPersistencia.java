package persistencia;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard.gascons
 * Data: 5/20/15
 */
public class CtrlPersistencia {

    private CtrlPersistencia() {}


    /**
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static ArrayList<String> importarGrafWikipedia(String path) throws IOException {
        return null;
    }
        /*
    public static ArrayList<String> getFitxer(String path) throws IOException {

        ArrayList<String> list = new ArrayList<String>();

        FileReader fReader = new FileReader(path);
        BufferedReader reader = new BufferedReader(fReader);

        String linia;
        while ((linia = reader.readLine()) != null) {
            list.add(linia);
        }

        reader.close();

        return list;
>>>>>>> 00bcd424368553333d87489b7a744996fe38d543
    }
    */

    /**
     *
     * @param path
     * @param list
     * @throws IOException
     */
    public static void setFitxer(String path, ArrayList<String> list) throws IOException {

        FileWriter writer = new FileWriter(path);

        for (String aList : list) {
            writer.write(aList);
            writer.write("\r\n");
        }
        writer.close();
    }


    /**
     *
     * @param path
     * @return
     */
    public static boolean esborrarFitxer(String path) {
        File file = new File(path);
        return file.delete();
    }

    /**
     *
     * @param path
     * @return
     */
    public static ArrayList<String> llistarFitxers(String path) {
        File file = new File(path);
        ArrayList<String> contingut = new ArrayList<String>();
        String[] llista = file.list();
        Collections.addAll(contingut, llista);
        return contingut;
    }



}
