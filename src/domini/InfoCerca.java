package domini;

import java.util.ArrayList;

public class InfoCerca {
    private ArrayList<NodeWiki> a;
    private String data;

    public InfoCerca(ArrayList<NodeWiki> a, String data) {
        this.a = a;
        this.data = data;
    }

    public ArrayList<NodeWiki> getResultats() {
        return a;
    }

    public String getDataCerca() {
        return data;
    }

    @Override
    public String toString() {
        return "InfoCerca{" +
                "a=" + a +
                ", data='" + data + '\'' +
                '}';
    }
}
