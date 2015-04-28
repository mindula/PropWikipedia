package domini;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard.gascons
 * Data: 15/4/15
 */

public class InfoCerca {
    private NodeWiki a;
    private String data;

    private InfoCerca() {
        super();
    }

    public InfoCerca(NodeWiki a, String data) {
        this.a = a;
        this.data = data;
    }

    public NodeWiki getResultat() {
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
