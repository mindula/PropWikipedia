package graf;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class GrafParser {
    //public GrafParser();

    public GrafWikipedia parse(Path path){
        GrafWikipedia g = new GrafWikipedia();
        try{
            List<String> l = Files.readAllLines(path, Charset.defaultCharset()); // canviar la implementació, fitxers grans
            for(String s: l){
                parseLine(s);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return g;
    }

    private void parseLine(String s){
        String[] parts = s.split("\\t");
        System.out.println(parts[0]);
        System.out.println(parts[1]);
        System.out.println(parts[2]);
        System.out.println(parts[3]);
        System.out.println(parts[4]);
        System.out.println(parts[5]);
        /*
        Pattern p = Pattern.compile("");
        Matcher m = p.matcher(s);*/
    }
}
