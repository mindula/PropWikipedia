package domini;

import java.util.Date;

/**
 * Created by ricard on 3/19/15.
 */
public class Sessio {
    //singleton

    public enum Foo {
        INSTANCE;
    }

    private String nom;
    private Date dataCreacio;
}
