package persistencia;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

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
    public static ArrayList<String> carregarDades(String path) throws IOException {

        ArrayList<String> list = new ArrayList<String>();

        FileInputStream inputStream;
        Scanner sc;
        inputStream = new FileInputStream(path);
        sc = new Scanner(inputStream);

        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            list.add(s);
        }

        inputStream.close();
        sc.close();

        return list;
    }

    /**
     *
     * @param path
     * @param list
     * @throws IOException
     */
    public static void guardarDades(String path, ArrayList<String> list) throws IOException {

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

    /**
     *
     * @param path
     * @return
     */
    public static boolean existeixFitxer(String path) {
        File file = new File(path);
        file.getParentFile().mkdirs();
        return file.exists();
    }

    /**
     * Afegeix una nova linia al final del fitxer
     * @param path
     * @param novaLinia
     * @throws IOException
     */
    public static void afegirDada(String path, String novaLinia) throws IOException {
        FileWriter w = new FileWriter(path, true);
        w.write(novaLinia);
        w.write("\r\n");
        w.close();
    }
}
