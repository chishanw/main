package seedu.zerotoone.commons.util;

/**
 * A class created to store a Pair of values.
 * @param <T> The type of the first value.
 * @param <U> The type of the second value.
 */
public class Pair<T, U> {
    private T firstObject;
    private U secondObject;

    public Pair(T firstObject, U secondObject) {
        this.firstObject = firstObject;
        this.secondObject = secondObject;
    }

    public T getFirstObject() {
        return this.firstObject;
    }

    public U getSecondObject() {
        return this.secondObject;
    }
}
