package persistencia;


import domini.controladors.CtrlWikipedia;

import java.io.*;

/**
 * Grup 3: Wikipedia
 * Usuari: agusti.bau
 * Data: 5/20/15
 */
public class CtrlPersistencia {

    public static  void guardarSessio(String filePath) throws IOException {
        // TODO: aquest sistema nomes te el inconvenient que si camviem els atributs de la classe CtrlWiki
        // els fitxers deixen de ser compatibles
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(CtrlWikipedia.getInstance());
        objectOutputStream.close();
        fileOutputStream.close();
        System.out.println("Fitxer guardat a "+filePath);
    }

    public static void carregarSessio(String filepath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filepath);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        try {
            CtrlWikipedia sessio = (CtrlWikipedia) objectInputStream.readObject();
            CtrlWikipedia.setInstance(sessio);

            System.out.println("Fitxer carregat : "+sessio.toString());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




}
