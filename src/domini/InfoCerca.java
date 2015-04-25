package domini;

public class InfoCerca {
    NodeWiki a;
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
