package domini;

/**
 * Created by gus on 26/04/15.
 */
public class GrafExeption extends Exception {
    public GrafExeption() {
    }

    public GrafExeption(String message) {
        super(message);
    }

    public GrafExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public GrafExeption(Throwable cause) {
        super(cause);
    }

    public GrafExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
