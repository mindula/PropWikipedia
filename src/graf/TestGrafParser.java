package graf;

import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Grup 3: Wikipedia
 * Usuari: aleix.paris
 * Data: 24/4/15
 */

public class TestGrafParser {
    public static void main (String[] args) {
        System.out.println("Escriu 0 pel test (misc/cats_test) o el path del fitxer:");
        Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();
        if(path.equals("0")) path = "misc/cats_test.txt";
        GrafWikipedia g = new GrafWikipedia();
        // TODO: canviar per
        // GrafWikipedia g = sessio.getInstance().getGraf..
        GrafParser grafParser = new GrafParser(g);
        grafParser.parse(Paths.get(path));
        //System.out.println(g);
    }
}
