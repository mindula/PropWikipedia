package prop.classescompartides.utils;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard.gascons
 * Data: 4/30/15
 */

/**
 * Conte un parell d'elements
 * @param <X>
 * @param <Y>
 */
public class Pair<X, Y> {
    X first;
    Y second;

    private Pair() {

    }

    /**
     * Constructor per defecte
     * @param first
     * @param second
     */
    public Pair(X first, Y second) {
        this.first = first;
        this.second = second;
    }

    public X getFirst() {
        return first;
    }

    public void setFirst(X first) {
        this.first = first;
    }

    public Y getSecond() {
        return second;
    }

    public void setSecond(Y second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return first.toString() + " " + second.toString();
    }
}
