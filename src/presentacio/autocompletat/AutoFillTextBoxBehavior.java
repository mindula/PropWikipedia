package presentacio.autocompletat;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard
 * Data: 5/29/15
 */
import com.sun.javafx.scene.control.behavior.BehaviorBase;

/**
 *
 * @author Narayan G. Maharjan
 * @see <a href="http://www.blog.ngopal.com.np"> Blog </a>
 */
public class AutoFillTextBoxBehavior<T> extends BehaviorBase<AutoFillTextBox<T>>{

    public AutoFillTextBoxBehavior(AutoFillTextBox<T> textBox) {
        super(textBox);
    }
}