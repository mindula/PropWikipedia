package graf;

import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Grup 3: Wikipedia
 * Usuari: aleix.paris
 * Data: 24/4/15
 */

public class DriverGrafParser {
    public static void main (String[] args) {

        System.out.println("Escriu el path:");
        Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();
        GrafParser grafParser = new GrafParser();
        GrafWikipedia g = grafParser.parse(Paths.get(path));
        // TODO: comprovar ara que el graf sigui el correcte
    }

}
