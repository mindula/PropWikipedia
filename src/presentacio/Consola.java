package presentacio;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard
 * Data: 5/31/15
 */
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.OutputStream;
public class Consola extends OutputStream {
    private TextArea op;
    public Consola(TextArea ta)
    {
        this.op = ta;
    }
    @Override
    public void write(int i) throws IOException
    {
        op.appendText(String.valueOf((char) i));
        //op.setText(String.valueOf((char) i));
    }
}
