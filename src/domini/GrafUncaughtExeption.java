package domini;

/**
 * Created by gus on 26/04/15.
 */
public class GrafUncaughtExeption extends RuntimeException{
    public GrafUncaughtExeption() {
    }

    public GrafUncaughtExeption(String message) {
        super(message);
    }

    public GrafUncaughtExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public GrafUncaughtExeption(Throwable cause) {
        super(cause);
    }

    public GrafUncaughtExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
